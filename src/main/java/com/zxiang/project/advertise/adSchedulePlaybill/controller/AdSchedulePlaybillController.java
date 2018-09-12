package com.zxiang.project.advertise.adSchedulePlaybill.controller;

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
import com.zxiang.project.advertise.adSchedulePlaybill.domain.AdSchedulePlaybill;
import com.zxiang.project.advertise.adSchedulePlaybill.service.IAdSchedulePlaybillService;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.framework.web.domain.AjaxResult;

/**
 * 推广计划明细 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Controller
@RequestMapping("/advertise/adSchedulePlaybill")
public class AdSchedulePlaybillController extends BaseController
{
    private String prefix = "advertise/adSchedulePlaybill";
	
	@Autowired
	private IAdSchedulePlaybillService adSchedulePlaybillService;
	
	@RequiresPermissions("advertise:adSchedulePlaybill:view")
	@GetMapping()
	public String adSchedulePlaybill()
	{
	    return prefix + "/adSchedulePlaybill";
	}
	
	/**
	 * 查询推广计划明细列表
	 */
	@RequiresPermissions("advertise:adSchedulePlaybill:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(AdSchedulePlaybill adSchedulePlaybill)
	{
		startPage();
        List<AdSchedulePlaybill> list = adSchedulePlaybillService.selectAdSchedulePlaybillList(adSchedulePlaybill);
		return getDataTable(list);
	}
	
	/**
	 * 新增推广计划明细
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存推广计划明细
	 */
	@RequiresPermissions("advertise:adSchedulePlaybill:add")
	@Log(title = "推广计划明细", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AdSchedulePlaybill adSchedulePlaybill)
	{		
		return toAjax(adSchedulePlaybillService.insertAdSchedulePlaybill(adSchedulePlaybill));
	}

	/**
	 * 修改推广计划明细
	 */
	@GetMapping("/edit/{schedulePlaybillId}")
	public String edit(@PathVariable("schedulePlaybillId") Integer schedulePlaybillId, ModelMap mmap)
	{
		AdSchedulePlaybill adSchedulePlaybill = adSchedulePlaybillService.selectAdSchedulePlaybillById(schedulePlaybillId);
		mmap.put("adSchedulePlaybill", adSchedulePlaybill);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存推广计划明细
	 */
	@RequiresPermissions("advertise:adSchedulePlaybill:edit")
	@Log(title = "推广计划明细", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(AdSchedulePlaybill adSchedulePlaybill)
	{		
		return toAjax(adSchedulePlaybillService.updateAdSchedulePlaybill(adSchedulePlaybill));
	}
	
	/**
	 * 删除推广计划明细
	 */
	@RequiresPermissions("advertise:adSchedulePlaybill:remove")
	@Log(title = "推广计划明细", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(adSchedulePlaybillService.deleteAdSchedulePlaybillByIds(ids));
	}
	
}
