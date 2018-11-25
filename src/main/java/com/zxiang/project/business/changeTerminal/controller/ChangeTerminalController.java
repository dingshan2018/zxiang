package com.zxiang.project.business.changeTerminal.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxiang.common.constant.UserConstants;
import com.zxiang.framework.aspectj.lang.annotation.DataFilter;
import com.zxiang.framework.aspectj.lang.annotation.Log;
import com.zxiang.framework.aspectj.lang.enums.BusinessType;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.domain.AjaxResult;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.project.business.changeTerminal.domain.ChangeTerminal;
import com.zxiang.project.business.changeTerminal.service.IChangeTerminalService;
import com.zxiang.project.business.terminal.service.ITerminalService;
import com.zxiang.project.system.user.domain.User;
import com.zxiang.project.system.user.service.IUserService;

/**
 * 终端更换记录 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
@Controller
@RequestMapping("/business/changeTerminal")
public class ChangeTerminalController extends BaseController
{
    private String prefix = "business/changeTerminal";
	
	@Autowired
	private IChangeTerminalService changeTerminalService;
	
	@Autowired
	private ITerminalService terminalService; 
	@Autowired
	private IUserService userService;
	
	@RequiresPermissions("business:changeTerminal:view")
	@GetMapping()
	public String changeTerminal()
	{
	    return prefix + "/changeTerminal";
	}
	
	/**
	 * 查询终端更换记录列表
	 */
	@DataFilter(personAlias="zct.changerId")
	@RequiresPermissions("business:changeTerminal:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ChangeTerminal changeTerminal)
	{
		startPage();
        List<ChangeTerminal> list = changeTerminalService.selectChangeTerminalList(changeTerminal);
		return getDataTable(list);
	}
	
	/**
	 * 新增终端更换记录
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap)
	{
		User queryUser = new User();
		queryUser.setUserType(UserConstants.USER_TYPE_REPAIR);
		List<User> userList = userService.selectUserList(queryUser);
		mmap.put("userList", userList);
		
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存终端更换记录
	 */
	@RequiresPermissions("business:changeTerminal:add")
	@Log(title = "终端更换记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ChangeTerminal changeTerminal)
	{		
		String createor = getUser().getUserName();
		long userId = getUserId();
		
		changeTerminal.setCreateBy(createor+"("+userId+")");
		changeTerminal.setCreateTime(new Date());
		return toAjax(changeTerminalService.insertChangeTerminal(changeTerminal));
	}

	/**
	 * 修改终端更换记录
	 */
	@GetMapping("/edit/{changeTerminalId}")
	public String edit(@PathVariable("changeTerminalId") Integer changeTerminalId, ModelMap mmap)
	{
		ChangeTerminal changeTerminal = changeTerminalService.selectChangeTerminalById(changeTerminalId);
		
		mmap.put("changeTerminal", changeTerminal);
		mmap.put("terminalDropBoxList", terminalService.selectDropBoxList());

		User queryUser = new User();
		queryUser.setUserType(UserConstants.USER_TYPE_REPAIR);
		List<User> userList = userService.selectUserList(queryUser);
		mmap.put("userList", userList);
		
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存终端更换记录
	 */
	@RequiresPermissions("business:changeTerminal:edit")
	@Log(title = "终端更换记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ChangeTerminal changeTerminal)
	{		
		String updateor = getUser().getUserName();
		long userId = getUserId();
		
		changeTerminal.setUpdateBy(updateor+"("+userId+")");
		changeTerminal.setUpdateTime(new Date());
		return toAjax(changeTerminalService.updateChangeTerminal(changeTerminal));
	}
	
	/**
	 * 删除终端更换记录
	 */
	@RequiresPermissions("business:changeTerminal:remove")
	@Log(title = "终端更换记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(changeTerminalService.deleteChangeTerminalByIds(ids));
	}
	
	/**
	 * 导出Excel
	 * 
	 */
	@DataFilter(personAlias="zct.changerId")
	@RequestMapping("/excelExport")
	public void excelExport(@RequestParam HashMap<String, String> params, 
			HttpServletResponse response,HttpServletRequest request){
		try {
			changeTerminalService.queryExport(params, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
