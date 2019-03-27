package com.zxiang.project.advertise.adReleaseTimer.controller;

import java.io.IOException;
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
import com.zxiang.project.advertise.adReleaseTimer.domain.AdReleaseTimer;
import com.zxiang.project.advertise.adReleaseTimer.service.IAdReleaseTimerService;
import com.zxiang.project.advertise.adSchedule.domain.AdSchedule;
import com.zxiang.project.advertise.adSchedule.domain.ElementType;
import com.zxiang.project.advertise.adSchedule.service.IAdScheduleService;

/**
 * 广告投放时段 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-11-07
 */
@Controller
@RequestMapping("/settle/adReleaseTimer")
public class AdReleaseTimerController extends BaseController
{
    private String prefix = "settle/adReleaseTimer";
	
	@Autowired
	private IAdReleaseTimerService adReleaseTimerService;
	@Autowired
	private IAdScheduleService adScheduleService;
	
	@RequiresPermissions("settle:adReleaseTimer:view")
	@GetMapping()
	public String adReleaseTimer()
	{
	    return prefix + "/adReleaseTimer";
	}
	
	/**
	 * 查询广告投放时段列表
	 */
	@RequiresPermissions("settle:adReleaseTimer:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(AdReleaseTimer adReleaseTimer)
	{
		startPage();
        List<AdReleaseTimer> list = adReleaseTimerService.selectAdReleaseTimerList(adReleaseTimer);
		return getDataTable(list);
	}
	
	/**
	 * 查询单条广告投放时段列表
	 */
	@RequiresPermissions("settle:adReleaseTimer:editTimer")
	@GetMapping("/editTimer/{adScheduleId}")
	public String editTimer(@PathVariable("adScheduleId") Integer adScheduleId, ModelMap mmap)
	{

	    return prefix + "/adReleaseTimer";
	}
	
	/**
	 * 新增广告投放时段
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存广告投放时段
	 */
	@RequiresPermissions("settle:adReleaseTimer:add")
	@Log(title = "广告投放时段", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AdReleaseTimer adReleaseTimer)
	{		
		return toAjax(adReleaseTimerService.insertAdReleaseTimer(adReleaseTimer));
	}

	/**
	 * 修改广告投放时段
	 */
	@GetMapping("/edit/{adReleaseTimerId}")
	public String edit(@PathVariable("adReleaseTimerId") Integer adReleaseTimerId, ModelMap mmap)
	{
		AdReleaseTimer adReleaseTimer = adReleaseTimerService.selectAdReleaseTimerById(adReleaseTimerId);
		mmap.put("adReleaseTimer", adReleaseTimer);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存广告投放时段
	 */
	@RequiresPermissions("settle:adReleaseTimer:edit")
	@Log(title = "广告投放时段", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(AdReleaseTimer adReleaseTimer)
	{		
		return toAjax(adReleaseTimerService.updateAdReleaseTimer(adReleaseTimer));
	}
	
	/**
	 * 删除广告投放时段
	 */
	@RequiresPermissions("settle:adReleaseTimer:remove")
	@Log(title = "广告投放时段", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(adReleaseTimerService.deleteAdReleaseTimerByIds(ids));
	}
	
}
