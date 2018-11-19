package com.zxiang.project.business.terminal.controller;

import java.util.Date;
import java.util.List;


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

import com.zxiang.common.utils.excel.ExcelServiceUtil;
import com.zxiang.framework.aspectj.lang.annotation.Log;
import com.zxiang.framework.aspectj.lang.enums.BusinessType;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.domain.AjaxResult;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.project.business.device.service.IDeviceService;
import com.zxiang.project.business.place.service.IPlaceService;
import com.zxiang.project.business.terminal.domain.Terminal;
import com.zxiang.project.business.terminal.service.ITerminalService;

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
	@RequiresPermissions("business:terminal:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Terminal terminal)
	{
		startPage();
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
}
