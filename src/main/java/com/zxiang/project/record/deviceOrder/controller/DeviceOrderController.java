package com.zxiang.project.record.deviceOrder.controller;

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
import com.zxiang.project.record.deviceOrder.domain.DeviceOrder;
import com.zxiang.project.record.deviceOrder.service.IDeviceOrderService;

/**
 * 设备销售订单 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Controller
@RequestMapping("/record/deviceOrder")
public class DeviceOrderController extends BaseController
{
    private String prefix = "record/deviceOrder";
	
	@Autowired
	private IDeviceOrderService deviceOrderService;
	
	@RequiresPermissions("record:deviceOrder:view")
	@GetMapping(value = {"/{transactionId}",""})
	public String deviceOrder(@PathVariable(required = false) String transactionId,ModelMap mmap) {
		mmap.put("transactionId", transactionId == null ? null : transactionId);
	    return prefix + "/deviceOrder";
	}
	
	/**
	 * 查询设备销售订单列表
	 */
	@RequiresPermissions("record:deviceOrder:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DeviceOrder deviceOrder)
	{
		startPage();
        List<DeviceOrder> list = deviceOrderService.selectDeviceOrderList(deviceOrder);
		return getDataTable(list);
	}
	
	/**
	 * 新增设备销售订单
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存设备销售订单
	 */
	@RequiresPermissions("record:deviceOrder:add")
	@Log(title = "设备销售订单", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(DeviceOrder deviceOrder)
	{		
		return toAjax(deviceOrderService.insertDeviceOrder(deviceOrder));
	}

	/**
	 * 修改设备销售订单
	 */
	@GetMapping("/edit/{deviceOrderId}")
	public String edit(@PathVariable("deviceOrderId") Integer deviceOrderId, ModelMap mmap)
	{
		DeviceOrder deviceOrder = deviceOrderService.selectDeviceOrderById(deviceOrderId);
		mmap.put("deviceOrder", deviceOrder);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存设备销售订单
	 */
	@RequiresPermissions("record:deviceOrder:edit")
	@Log(title = "设备销售订单", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(DeviceOrder deviceOrder)
	{		
		return toAjax(deviceOrderService.updateDeviceOrder(deviceOrder));
	}
	
	/**
	 * 删除设备销售订单
	 */
	@RequiresPermissions("record:deviceOrder:remove")
	@Log(title = "设备销售订单", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(deviceOrderService.deleteDeviceOrderByIds(ids));
	}
	
	/**
     * 获取已发送设备数量
     */
    @PostMapping("/getSendNum")
    @ResponseBody
    public int getSendNum(Integer tradeOrderId)
    {
        return deviceOrderService.getSendNum(tradeOrderId);
    }
	
}
