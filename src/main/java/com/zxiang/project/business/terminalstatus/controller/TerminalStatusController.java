package com.zxiang.project.business.terminalstatus.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxiang.framework.aspectj.lang.annotation.Log;
import com.zxiang.framework.aspectj.lang.enums.BusinessType;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.domain.AjaxResult;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.project.business.terminalstatus.domain.TerminalStatus;
import com.zxiang.project.business.terminalstatus.service.ITerminalStatusService;

/**
 * 终端状态记录 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-10-14
 */
@Controller
@RequestMapping("/system/terminalStatus")
public class TerminalStatusController extends BaseController
{
    private String prefix = "system/terminalStatus";
	
	@Autowired
	private ITerminalStatusService terminalStatusService;
	
	@RequiresPermissions("system:terminalStatus:view")
	@GetMapping()
	public String terminalStatus()
	{
	    return prefix + "/terminalStatus";
	}
	
	/**
	 * 查询终端状态记录列表
	 */
	@RequiresPermissions("system:terminalStatus:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TerminalStatus terminalStatus)
	{
		startPage();
        List<TerminalStatus> list = terminalStatusService.selectTerminalStatusList(terminalStatus);
		return getDataTable(list);
	}
	
	/**
	 * 新增终端状态记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存终端状态记录
	 */
	@RequiresPermissions("system:terminalStatus:add")
	@Log(title = "终端状态记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TerminalStatus terminalStatus)
	{		
		return toAjax(terminalStatusService.insertTerminalStatus(terminalStatus));
	}

	/**
	 * 修改终端状态记录
	 */
	@GetMapping("/edit/{statusId}")
	public String edit(@PathVariable("statusId") Integer statusId, ModelMap mmap)
	{
		TerminalStatus terminalStatus = terminalStatusService.selectTerminalStatusById(statusId);
		mmap.put("terminalStatus", terminalStatus);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存终端状态记录
	 */
	@RequiresPermissions("system:terminalStatus:edit")
	@Log(title = "终端状态记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TerminalStatus terminalStatus)
	{		
		return toAjax(terminalStatusService.updateTerminalStatus(terminalStatus));
	}
	
	/**
	 * 删除终端状态记录
	 */
	@RequiresPermissions("system:terminalStatus:remove")
	@Log(title = "终端状态记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(terminalStatusService.deleteTerminalStatusByIds(ids));
	}
	
}
