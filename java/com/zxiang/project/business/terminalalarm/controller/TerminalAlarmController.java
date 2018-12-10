package com.zxiang.project.business.terminalalarm.controller;

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
import com.zxiang.project.business.terminalalarm.domain.TerminalAlarm;
import com.zxiang.project.business.terminalalarm.service.ITerminalAlarmService;

/**
 * 终端报警 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-10-14
 */
@Controller
@RequestMapping("/system/terminalAlarm")
public class TerminalAlarmController extends BaseController
{
    private String prefix = "system/terminalAlarm";
	
	@Autowired
	private ITerminalAlarmService terminalAlarmService;
	
	@RequiresPermissions("system:terminalAlarm:view")
	@GetMapping()
	public String terminalAlarm()
	{
	    return prefix + "/terminalAlarm";
	}
	
	/**
	 * 查询终端报警列表
	 */
	@RequiresPermissions("system:terminalAlarm:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TerminalAlarm terminalAlarm)
	{
		startPage();
        List<TerminalAlarm> list = terminalAlarmService.selectTerminalAlarmList(terminalAlarm);
		return getDataTable(list);
	}
	
	/**
	 * 新增终端报警
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存终端报警
	 */
	@RequiresPermissions("system:terminalAlarm:add")
	@Log(title = "终端报警", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TerminalAlarm terminalAlarm)
	{		
		return toAjax(terminalAlarmService.insertTerminalAlarm(terminalAlarm));
	}

	/**
	 * 修改终端报警
	 */
	@GetMapping("/edit/{alarmId}")
	public String edit(@PathVariable("alarmId") Integer alarmId, ModelMap mmap)
	{
		TerminalAlarm terminalAlarm = terminalAlarmService.selectTerminalAlarmById(alarmId);
		mmap.put("terminalAlarm", terminalAlarm);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存终端报警
	 */
	@RequiresPermissions("system:terminalAlarm:edit")
	@Log(title = "终端报警", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TerminalAlarm terminalAlarm)
	{		
		return toAjax(terminalAlarmService.updateTerminalAlarm(terminalAlarm));
	}
	
	/**
	 * 删除终端报警
	 */
	@RequiresPermissions("system:terminalAlarm:remove")
	@Log(title = "终端报警", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(terminalAlarmService.deleteTerminalAlarmByIds(ids));
	}
	
}
