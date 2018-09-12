package com.zxiang.project.advertise.adSchedule.controller;

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
import com.zxiang.project.advertise.adSchedule.domain.AdSchedule;
import com.zxiang.project.advertise.adSchedule.service.IAdScheduleService;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.framework.web.domain.AjaxResult;

/**
 * 广告推广计划 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Controller
@RequestMapping("/advertise/adSchedule")
public class AdScheduleController extends BaseController
{
    private String prefix = "advertise/adSchedule";
	
	@Autowired
	private IAdScheduleService adScheduleService;
	
	@RequiresPermissions("advertise:adSchedule:view")
	@GetMapping()
	public String adSchedule()
	{
	    return prefix + "/adSchedule";
	}
	
	/**
	 * 查询广告推广计划列表
	 */
	@RequiresPermissions("advertise:adSchedule:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(AdSchedule adSchedule)
	{
		startPage();
        List<AdSchedule> list = adScheduleService.selectAdScheduleList(adSchedule);
		return getDataTable(list);
	}
	
	/**
	 * 新增广告推广计划
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存广告推广计划
	 */
	@RequiresPermissions("advertise:adSchedule:add")
	@Log(title = "广告推广计划", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AdSchedule adSchedule)
	{		
		return toAjax(adScheduleService.insertAdSchedule(adSchedule));
	}

	/**
	 * 修改广告推广计划
	 */
	@GetMapping("/edit/{scheduleId}")
	public String edit(@PathVariable("scheduleId") Integer scheduleId, ModelMap mmap)
	{
		AdSchedule adSchedule = adScheduleService.selectAdScheduleById(scheduleId);
		mmap.put("adSchedule", adSchedule);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存广告推广计划
	 */
	@RequiresPermissions("advertise:adSchedule:edit")
	@Log(title = "广告推广计划", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(AdSchedule adSchedule)
	{		
		return toAjax(adScheduleService.updateAdSchedule(adSchedule));
	}
	
	/**
	 * 删除广告推广计划
	 */
	@RequiresPermissions("advertise:adSchedule:remove")
	@Log(title = "广告推广计划", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(adScheduleService.deleteAdScheduleByIds(ids));
	}
	
}
