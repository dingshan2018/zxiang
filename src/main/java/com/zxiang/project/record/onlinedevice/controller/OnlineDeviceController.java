package com.zxiang.project.record.onlinedevice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxiang.common.utils.StringUtils;
import com.zxiang.framework.aspectj.lang.annotation.Log;
import com.zxiang.framework.aspectj.lang.enums.BusinessType;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.domain.AjaxResult;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.project.record.onlinedevice.domain.OnlineDevice;
import com.zxiang.project.record.onlinedevice.service.IOnlineDeviceService;

/**
 * 设备在线时长 信息操作处理
 * 
 * @author ZXiang
 * @date 2020-05-14
 */
@Controller
@RequestMapping("/record/onlineDevice")
public class OnlineDeviceController extends BaseController
{
    private String prefix = "record/onlineDevice";
	
	@Autowired
	private IOnlineDeviceService onlineDeviceService;
	
	@RequiresPermissions("settle:onlineDevice:view")
	@GetMapping()
	public String onlineDevice()
	{
	    return prefix + "/onlineDevice";
	}
	
	@RequiresPermissions("settle:onlineDevice:view")
	@GetMapping("/detail/{deviceCode}")
	public String onlineDeviceDetail(@PathVariable String deviceCode,String onlineTime, ModelMap mmap)
	{
		mmap.put("deviceCode", deviceCode);
		if(StringUtils.isNotEmpty(onlineTime)) {
			mmap.put("onlineTime", onlineTime);
		}
	    return prefix + "/onlineDeviceDetail";
	}
	
	/**
	 * 查询设备在线统计时长列表
	 */
	@RequiresPermissions("settle:onlineDevice:data")
	@PostMapping("/data")
	@ResponseBody
	public TableDataInfo data(OnlineDevice onlineDevice)
	{
		startPage();
		if(onlineDevice.getParams() != null && !onlineDevice.getParams().containsKey("onlineTime") ) {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("onlineTime", "");
			onlineDevice.setParams(params);
		}
		
        List<HashMap<String,Object>> list = onlineDeviceService.selectOnlineDeviceData(onlineDevice);
		return getDataTable(list);
	}
	
	/**
	 * 查询设备在线时长列表
	 */
	@RequiresPermissions("settle:onlineDevice:list")
	@PostMapping("/list/{deviceCode}")
	@ResponseBody
	public TableDataInfo list(OnlineDevice onlineDevice,String statTime)
	{
		startPage();
		if(StringUtils.isNotBlank(statTime)) {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("onlineTime", statTime);
			onlineDevice.setParams(params);
		}
		
        List<OnlineDevice> list = onlineDeviceService.selectOnlineDeviceList(onlineDevice);
		return getDataTable(list);
	}
	
	/**
	 * 新增设备在线时长
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存设备在线时长
	 */
	@RequiresPermissions("settle:onlineDevice:add")
	@Log(title = "设备在线时长", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(OnlineDevice onlineDevice)
	{		
		return toAjax(onlineDeviceService.insertOnlineDevice(onlineDevice));
	}

	/**
	 * 修改设备在线时长
	 */
	@GetMapping("/edit/{deviceOnlineId}")
	public String edit(@PathVariable("deviceOnlineId") Integer deviceOnlineId, ModelMap mmap)
	{
		OnlineDevice onlineDevice = onlineDeviceService.selectOnlineDeviceById(deviceOnlineId);
		mmap.put("onlineDevice", onlineDevice);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存设备在线时长
	 */
	@RequiresPermissions("settle:onlineDevice:edit")
	@Log(title = "设备在线时长", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(OnlineDevice onlineDevice)
	{		
		return toAjax(onlineDeviceService.updateOnlineDevice(onlineDevice));
	}
	
	/**
	 * 删除设备在线时长
	 */
	@RequiresPermissions("settle:onlineDevice:remove")
	@Log(title = "设备在线时长", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(onlineDeviceService.deleteOnlineDeviceByIds(ids));
	}
	
}
