package com.zxiang.project.record.scheduleOrder.controller;

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
import com.zxiang.project.record.scheduleOrder.domain.ScheduleOrder;
import com.zxiang.project.record.scheduleOrder.service.IScheduleOrderService;

/**
 * 排期订单 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-11-15
 */
@Controller
@RequestMapping("/record/scheduleOrder")
public class ScheduleOrderController extends BaseController
{
    private String prefix = "record/scheduleOrder";
	
	@Autowired
	private IScheduleOrderService scheduleOrderService;
	
	@RequiresPermissions("record:scheduleOrder:view")
	@GetMapping(value = {"/{transactionId}",""})
	public String scheduleOrder(@PathVariable(required = false) String transactionId,ModelMap mmap) {
		mmap.put("transactionId", transactionId == null ? null : transactionId);
	    return prefix + "/scheduleOrder";
	}
	
	/**
	 * 查询排期订单列表
	 */
	@RequiresPermissions("record:scheduleOrder:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ScheduleOrder scheduleOrder)
	{
		startPage();
        List<ScheduleOrder> list = scheduleOrderService.selectScheduleOrderList(scheduleOrder);
		return getDataTable(list);
	}
	
	/**
	 * 新增排期订单
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存排期订单
	 */
	@RequiresPermissions("record:scheduleOrder:add")
	@Log(title = "排期订单", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ScheduleOrder scheduleOrder)
	{		
		return toAjax(scheduleOrderService.insertScheduleOrder(scheduleOrder));
	}

	/**
	 * 修改排期订单
	 */
	@GetMapping("/edit/{scheduleOrderId}")
	public String edit(@PathVariable("scheduleOrderId") Integer scheduleOrderId, ModelMap mmap)
	{
		ScheduleOrder scheduleOrder = scheduleOrderService.selectScheduleOrderById(scheduleOrderId);
		mmap.put("scheduleOrder", scheduleOrder);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存排期订单
	 */
	@RequiresPermissions("record:scheduleOrder:edit")
	@Log(title = "排期订单", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ScheduleOrder scheduleOrder)
	{		
		return toAjax(scheduleOrderService.updateScheduleOrder(scheduleOrder));
	}
	
	/**
	 * 删除排期订单
	 */
	@RequiresPermissions("record:scheduleOrder:remove")
	@Log(title = "排期订单", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(scheduleOrderService.deleteScheduleOrderByIds(ids));
	}
	
}
