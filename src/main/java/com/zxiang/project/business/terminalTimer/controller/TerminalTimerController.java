package com.zxiang.project.business.terminalTimer.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxiang.framework.aspectj.lang.annotation.Log;
import com.zxiang.framework.aspectj.lang.enums.BusinessType;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.domain.AjaxResult;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.project.business.terminalTimer.domain.TerminalTimer;
import com.zxiang.project.business.terminalTimer.service.ITerminalTimerService;

/**
 * 定时设置 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
@Controller
@RequestMapping("/business/terminalTimer")
public class TerminalTimerController extends BaseController
{
    private String prefix = "business/terminalTimer";
	
	@Autowired
	private ITerminalTimerService terminalTimerService;
	
	@RequiresPermissions("business:terminalTimer:view")
	@GetMapping()
	public String terminalTimer()
	{
	    return prefix + "/terminalTimer";
	}
	
	/**
	 * 查询定时设置列表
	 */
	@RequiresPermissions("business:terminalTimer:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TerminalTimer terminalTimer)
	{
		startPage();
        List<TerminalTimer> list = terminalTimerService.selectTerminalTimerList(terminalTimer);
		return getDataTable(list);
	}
	
	/**
	 * 新增定时设置
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存定时设置
	 */
	@RequiresPermissions("business:terminalTimer:add")
	@Log(title = "定时设置", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TerminalTimer terminalTimer)
	{		
		String createor = getUser().getUserName();
		terminalTimer.setCreateBy(createor);
		terminalTimer.setCreateTime(new Date());
		return toAjax(terminalTimerService.insertTerminalTimer(terminalTimer));
	}

	/**
	 * 修改定时设置
	 */
	@GetMapping("/edit/{teminalTimerId}")
	public String edit(@PathVariable("teminalTimerId") Integer teminalTimerId, ModelMap mmap)
	{
		TerminalTimer terminalTimer = terminalTimerService.selectTerminalTimerById(teminalTimerId);
		mmap.put("terminalTimer", terminalTimer);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存定时设置
	 */
	@RequiresPermissions("business:terminalTimer:edit")
	@Log(title = "定时设置", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TerminalTimer terminalTimer)
	{		
		String updateor = getUser().getUserName();
		terminalTimer.setUpdateBy(updateor);
		terminalTimer.setUpdateTime(new Date());
		return toAjax(terminalTimerService.updateTerminalTimer(terminalTimer));
	}
	
	/**
	 * 删除定时设置
	 */
	@RequiresPermissions("business:terminalTimer:remove")
	@Log(title = "定时设置", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(terminalTimerService.deleteTerminalTimerByIds(ids));
	}
	
}
