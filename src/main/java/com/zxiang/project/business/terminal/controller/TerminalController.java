package com.zxiang.project.business.terminal.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zxiang.common.constant.UserConstants;
import com.zxiang.common.utils.excel.ExcelServiceUtil;
import com.zxiang.framework.aspectj.lang.annotation.DataFilter;
import com.zxiang.framework.aspectj.lang.annotation.Log;
import com.zxiang.framework.aspectj.lang.enums.BusinessType;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.domain.AjaxResult;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.project.business.device.service.IDeviceService;
import com.zxiang.project.business.place.service.IPlaceService;
import com.zxiang.project.business.terminal.domain.Terminal;
import com.zxiang.project.business.terminal.service.ITerminalService;
import com.zxiang.project.system.user.domain.User;

/**
 * 终端管理 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
@Controller
@RequestMapping("/business/terminal")
public class TerminalController extends BaseController
{
    private String prefix = "business/terminal";
	
	@Autowired
	private ITerminalService terminalService;
	@Autowired 
	private IPlaceService placeService;
	@Autowired
	private IDeviceService deviceService;
	
	@RequiresPermissions("business:terminal:view")
	@GetMapping()
	public String terminal()
	{
	    return prefix + "/terminal";
	}
	
	/**
	 * 查询终端管理列表
	 */
	@DataFilter(placeAlias="t.place_id")
	@RequiresPermissions("business:terminal:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Terminal terminal)
	{
		startPage();
		User user =getUser();
		String userType = user.getUserType();
		if(userType.equals(UserConstants.USER_TYPE_JOIN)) {
			terminal.setUserId(user.getUserId()+"");
		}
        List<Terminal> list = terminalService.selectTerminalList(terminal);
		return getDataTable(list);
	}
	
	/**
	 * 新增终端管理-界面跳转
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存终端管理
	 */
	@RequiresPermissions("business:terminal:add")
	@Log(title = "终端管理", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Terminal terminal)
	{		
		String createor = getUser().getUserName();
		long userId = getUserId();
		
		terminal.setCreateBy(createor+"("+userId+")");
		terminal.setCreateTime(new Date());
		return toAjax(terminalService.insertTerminal(terminal));
	}

	/**
	 * 修改终端管理-界面跳转
	 */
	@GetMapping("/edit/{terminalId}")
	public String edit(@PathVariable("terminalId") Integer terminalId, ModelMap mmap)
	{
		Terminal terminal = terminalService.selectTerminalById(terminalId);
		mmap.put("terminal", terminal);
		mmap.put("deviceDropBoxList", deviceService.selectDropBoxList(null));
		mmap.put("placeDropBoxList", placeService.selectDropBoxList());
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存终端管理
	 */
	@RequiresPermissions("business:terminal:edit")
	@Log(title = "终端管理", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Terminal terminal)
	{		
		String updateor = getUser().getUserName();
		long userId = getUserId();
		
		terminal.setUpdateBy(updateor+"("+userId+")");
		terminal.setUpdateTime(new Date());
		return toAjax(terminalService.updateTerminal(terminal));
	}
	
	/**
	 * 删除终端管理
	 */
	@RequiresPermissions("business:terminal:remove")
	@Log(title = "终端管理", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(terminalService.deleteTerminalByIds(ids));
	}
	
	/**
	 * 查询终端设备下拉列表
	 */
	@RequestMapping("/getDropBoxTerminalList")
    @ResponseBody
    public TableDataInfo getDropBoxTerminalList() {
		List<Terminal> list = terminalService.selectDropBoxList();
		return getDataTable(list);
    }
	
	/**
	 * 查询未被设备绑定的终端
	 */
	@RequestMapping("/getDropBoxValidlList")
    @ResponseBody
    public TableDataInfo getDropBoxValidlList() {
		List<Terminal> list = terminalService.getDropBoxValidlList();
		return getDataTable(list);
    }
	
	/**
     * 校验终端编号
     */
    @PostMapping("/checkTerminalCodeUnique")
    @ResponseBody
    public String checkTerminalCodeUnique(Terminal terminal)
    {
        return terminalService.checkTerminalCodeUnique(terminal.getTerminalCode());
    }
    
    /**
     * 查询终端参数详细
     */
    @RequiresPermissions("business:terminal:list")
    @GetMapping("/terminalParamDetail/{terminalId}")
    public String detail(@PathVariable("terminalId") Integer terminalId, ModelMap mmap)
    {
        mmap.put("terminal", terminalService.selectTerminalById(terminalId));
        mmap.put("terminalParamList", terminalService.selectDropBoxList());
        return "business/terminalParam/terminalParam";
    }
    
    /**
	 * 批量导入终端编号-界面跳转
	 */
	@GetMapping("/batchImport")
	public String batchImport()
	{
	    return prefix + "/batchImport";
	}
	
	/**
     * 批量导入终端保存
     */
	@Log(title = "批量导入终端保存", businessType = BusinessType.IMPORT)
    @RequestMapping(value = "/batchImport", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult batchImportSave(@RequestParam("fileUpload") MultipartFile file)
    {
    	int saveCount = 0;
    	try {
    		//解析Excel数据
			List<Object> sheetList = ExcelServiceUtil.importData(file);
			String operatorUser = getUser().getUserName()+"("+getUserId()+")";
			//保存终端编号
			saveCount = terminalService.saveBatchImport(sheetList,operatorUser);
			
		} catch (Exception e) {
			e.printStackTrace();
			return error("导入失败！");
		}    	

        return success("成功导入 "+ saveCount +" 条数据!");
    }
    
    /**
	 * 导出Excel
	 * 注意数据权限要与查询列表一致
	 */
    @Log(title = "终端导出Excel", businessType = BusinessType.EXPORT)
	@DataFilter(placeAlias="t.place_id")
	@RequestMapping("/excelExport")
	public void excelExport(@RequestParam HashMap<String, String> params, 
			HttpServletResponse response,HttpServletRequest request){
		try {
			User user =getUser();
			String userType = user.getUserType();
			if(userType.equals(UserConstants.USER_TYPE_JOIN)) {
				params.put("userId", user.getUserId()+"");
			}
			terminalService.queryExport(params, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 终端上报日志
	 */
	@Log(title = "终端上报日志", businessType = BusinessType.UPDATE)
	@PostMapping( "/reportLog")
	@ResponseBody
	public AjaxResult reportLog(Integer terminalId)
	{		
		try {
			// 调用接口下发命令 调知终端上报日志
			int number = terminalService.reportLog(terminalId);
			if(number > 0){
				return success("操作成功!");
			}else{
				return success("操作失败!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return error(e.getMessage()+" ,操作失败!");
		}
	}
	
	@Log(title = "终端重启", businessType = BusinessType.UPDATE)
	@PostMapping( "/resetTerminal")
	@ResponseBody
	public AjaxResult resetTerminal(Integer terminalId)
	{		
		try {
			// 调用接口下发命令 调知终端上报日志
			int number = terminalService.resetTerminal(terminalId);
			if(number > 0){
				return success("操作成功!");
			}else{
				return success("操作失败!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return error(e.getMessage()+" ,操作失败!");
		}
	}
	
	@Log(title = "终端重载广告资源", businessType = BusinessType.UPDATE)
	@PostMapping( "/reloadTerminal")
	@ResponseBody
	public AjaxResult reloadTerminal(Integer terminalId)
	{		
		try {
			// 调用接口下发命令 调知终端上报日志
			int number = terminalService.reloadTerminal(terminalId);
			if(number > 0){
				return success("操作成功!");
			}else{
				return success("操作失败!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return error(e.getMessage()+" ,操作失败!");
		}
	}

}
