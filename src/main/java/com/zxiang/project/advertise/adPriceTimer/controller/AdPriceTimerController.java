package com.zxiang.project.advertise.adPriceTimer.controller;

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
import com.zxiang.project.advertise.adPriceTimer.domain.AdPriceTimer;
import com.zxiang.project.advertise.adPriceTimer.service.IAdPriceTimerService;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.framework.web.domain.AjaxResult;

/**
 * 广告价格时段 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-24
 */
@Controller
@RequestMapping("/advertise/adPriceTimer")
public class AdPriceTimerController extends BaseController
{
    private String prefix = "advertise/adPriceTimer";
	
	@Autowired
	private IAdPriceTimerService adPriceTimerService;
	
	@RequiresPermissions("advertise:adPriceTimer:view")
	@GetMapping()
	public String adPriceTimer()
	{
	    return prefix + "/adPriceTimer";
	}
	
	/**
	 * 查询广告价格时段列表
	 */
	@RequiresPermissions("advertise:adPriceTimer:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(AdPriceTimer adPriceTimer)
	{
		startPage();
        List<AdPriceTimer> list = adPriceTimerService.selectAdPriceTimerList(adPriceTimer);
		return getDataTable(list);
	}
	
	/**
	 * 新增广告价格时段
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存广告价格时段
	 */
	@RequiresPermissions("advertise:adPriceTimer:add")
	@Log(title = "广告价格时段", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AdPriceTimer adPriceTimer)
	{		
		return toAjax(adPriceTimerService.insertAdPriceTimer(adPriceTimer));
	}

	/**
	 * 修改广告价格时段
	 */
	@GetMapping("/edit/{adPriceId}")
	public String edit(@PathVariable("adPriceId") Integer adPriceId, ModelMap mmap)
	{
		AdPriceTimer adPriceTimer = adPriceTimerService.selectAdPriceTimerById(adPriceId);
		mmap.put("adPriceTimer", adPriceTimer);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存广告价格时段
	 */
	@RequiresPermissions("advertise:adPriceTimer:edit")
	@Log(title = "广告价格时段", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(AdPriceTimer adPriceTimer)
	{		
		return toAjax(adPriceTimerService.updateAdPriceTimer(adPriceTimer));
	}
	
	/**
	 * 删除广告价格时段
	 */
	@RequiresPermissions("advertise:adPriceTimer:remove")
	@Log(title = "广告价格时段", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(adPriceTimerService.deleteAdPriceTimerByIds(ids));
	}
	
}
