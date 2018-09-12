package com.zxiang.project.advertise.adScheduleRelease.controller;

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
import com.zxiang.project.advertise.adScheduleRelease.domain.AdScheduleRelease;
import com.zxiang.project.advertise.adScheduleRelease.service.IAdScheduleReleaseService;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.framework.web.domain.AjaxResult;

/**
 * 投放时间 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Controller
@RequestMapping("/advertise/adScheduleRelease")
public class AdScheduleReleaseController extends BaseController
{
    private String prefix = "advertise/adScheduleRelease";
	
	@Autowired
	private IAdScheduleReleaseService adScheduleReleaseService;
	
	@RequiresPermissions("advertise:adScheduleRelease:view")
	@GetMapping()
	public String adScheduleRelease()
	{
	    return prefix + "/adScheduleRelease";
	}
	
	/**
	 * 查询投放时间列表
	 */
	@RequiresPermissions("advertise:adScheduleRelease:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(AdScheduleRelease adScheduleRelease)
	{
		startPage();
        List<AdScheduleRelease> list = adScheduleReleaseService.selectAdScheduleReleaseList(adScheduleRelease);
		return getDataTable(list);
	}
	
	/**
	 * 新增投放时间
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存投放时间
	 */
	@RequiresPermissions("advertise:adScheduleRelease:add")
	@Log(title = "投放时间", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AdScheduleRelease adScheduleRelease)
	{		
		return toAjax(adScheduleReleaseService.insertAdScheduleRelease(adScheduleRelease));
	}

	/**
	 * 修改投放时间
	 */
	@GetMapping("/edit/{scheduleReleaseId}")
	public String edit(@PathVariable("scheduleReleaseId") Integer scheduleReleaseId, ModelMap mmap)
	{
		AdScheduleRelease adScheduleRelease = adScheduleReleaseService.selectAdScheduleReleaseById(scheduleReleaseId);
		mmap.put("adScheduleRelease", adScheduleRelease);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存投放时间
	 */
	@RequiresPermissions("advertise:adScheduleRelease:edit")
	@Log(title = "投放时间", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(AdScheduleRelease adScheduleRelease)
	{		
		return toAjax(adScheduleReleaseService.updateAdScheduleRelease(adScheduleRelease));
	}
	
	/**
	 * 删除投放时间
	 */
	@RequiresPermissions("advertise:adScheduleRelease:remove")
	@Log(title = "投放时间", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(adScheduleReleaseService.deleteAdScheduleReleaseByIds(ids));
	}
	
}
