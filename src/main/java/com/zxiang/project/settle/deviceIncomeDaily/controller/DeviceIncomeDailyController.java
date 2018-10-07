package com.zxiang.project.settle.deviceIncomeDaily.controller;

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
import com.zxiang.project.settle.deviceIncomeDaily.domain.DeviceIncomeDaily;
import com.zxiang.project.settle.deviceIncomeDaily.service.IDeviceIncomeDailyService;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.framework.web.domain.AjaxResult;

/**
 * 设备收入日统计 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Controller
@RequestMapping("/settle/deviceIncomeDaily")
public class DeviceIncomeDailyController extends BaseController
{
    private String prefix = "settle/deviceIncomeDaily";
	
	@Autowired
	private IDeviceIncomeDailyService deviceIncomeDailyService;
	
	@RequiresPermissions("settle:deviceIncomeDaily:view")
	@GetMapping()
	public String deviceIncomeDaily()
	{
	    return prefix + "/deviceIncomeDaily";
	}
	
	/**
	 * 查询设备收入日统计列表
	 */
	@RequiresPermissions("settle:deviceIncomeDaily:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DeviceIncomeDaily deviceIncomeDaily)
	{
		startPage();
        List<DeviceIncomeDaily> list = deviceIncomeDailyService.selectDeviceIncomeDailyList(deviceIncomeDaily);
		return getDataTable(list);
	}
	
	/**
	 * 新增设备收入日统计
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存设备收入日统计
	 */
	@RequiresPermissions("settle:deviceIncomeDaily:add")
	@Log(title = "设备收入日统计", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(DeviceIncomeDaily deviceIncomeDaily)
	{		
		return toAjax(deviceIncomeDailyService.insertDeviceIncomeDaily(deviceIncomeDaily));
	}

	/**
	 * 修改设备收入日统计
	 */
	@GetMapping("/edit/{incomeId}")
	public String edit(@PathVariable("incomeId") Integer incomeId, ModelMap mmap)
	{
		DeviceIncomeDaily deviceIncomeDaily = deviceIncomeDailyService.selectDeviceIncomeDailyById(incomeId);
		mmap.put("deviceIncomeDaily", deviceIncomeDaily);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存设备收入日统计
	 */
	@RequiresPermissions("settle:deviceIncomeDaily:edit")
	@Log(title = "设备收入日统计", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(DeviceIncomeDaily deviceIncomeDaily)
	{		
		return toAjax(deviceIncomeDailyService.updateDeviceIncomeDaily(deviceIncomeDaily));
	}
	
	/**
	 * 删除设备收入日统计
	 */
	@RequiresPermissions("settle:deviceIncomeDaily:remove")
	@Log(title = "设备收入日统计", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(deviceIncomeDailyService.deleteDeviceIncomeDailyByIds(ids));
	}
	
	
	/**
	 * 每日统计数据
	 */
	@PostMapping( "/statisticaldata")
	@ResponseBody
	public void statisticaldata()
	{		
		deviceIncomeDailyService.statisticaldata();
	}
	
}
