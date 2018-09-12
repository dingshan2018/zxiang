package com.zxiang.project.advertise.adSchedulePosition.controller;

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
import com.zxiang.project.advertise.adSchedulePosition.domain.AdSchedulePosition;
import com.zxiang.project.advertise.adSchedulePosition.service.IAdSchedulePositionService;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.framework.web.domain.AjaxResult;

/**
 * 投放位置 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Controller
@RequestMapping("/advertise/adSchedulePosition")
public class AdSchedulePositionController extends BaseController
{
    private String prefix = "advertise/adSchedulePosition";
	
	@Autowired
	private IAdSchedulePositionService adSchedulePositionService;
	
	@RequiresPermissions("advertise:adSchedulePosition:view")
	@GetMapping()
	public String adSchedulePosition()
	{
	    return prefix + "/adSchedulePosition";
	}
	
	/**
	 * 查询投放位置列表
	 */
	@RequiresPermissions("advertise:adSchedulePosition:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(AdSchedulePosition adSchedulePosition)
	{
		startPage();
        List<AdSchedulePosition> list = adSchedulePositionService.selectAdSchedulePositionList(adSchedulePosition);
		return getDataTable(list);
	}
	
	/**
	 * 新增投放位置
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存投放位置
	 */
	@RequiresPermissions("advertise:adSchedulePosition:add")
	@Log(title = "投放位置", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AdSchedulePosition adSchedulePosition)
	{		
		return toAjax(adSchedulePositionService.insertAdSchedulePosition(adSchedulePosition));
	}

	/**
	 * 修改投放位置
	 */
	@GetMapping("/edit/{schedulePositionId}")
	public String edit(@PathVariable("schedulePositionId") Integer schedulePositionId, ModelMap mmap)
	{
		AdSchedulePosition adSchedulePosition = adSchedulePositionService.selectAdSchedulePositionById(schedulePositionId);
		mmap.put("adSchedulePosition", adSchedulePosition);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存投放位置
	 */
	@RequiresPermissions("advertise:adSchedulePosition:edit")
	@Log(title = "投放位置", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(AdSchedulePosition adSchedulePosition)
	{		
		return toAjax(adSchedulePositionService.updateAdSchedulePosition(adSchedulePosition));
	}
	
	/**
	 * 删除投放位置
	 */
	@RequiresPermissions("advertise:adSchedulePosition:remove")
	@Log(title = "投放位置", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(adSchedulePositionService.deleteAdSchedulePositionByIds(ids));
	}
	
}
