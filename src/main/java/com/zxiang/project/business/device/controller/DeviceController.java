package com.zxiang.project.business.device.controller;

import java.util.Date;
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
import com.zxiang.project.business.device.domain.Device;
import com.zxiang.project.business.device.service.IDeviceService;
import com.zxiang.project.business.place.service.IPlaceService;
import com.zxiang.project.business.terminal.service.ITerminalService;

/**
 * 共享设备 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
@Controller
@RequestMapping("/business/device")
public class DeviceController extends BaseController
{
    private String prefix = "business/device";
	
	@Autowired
	private IDeviceService deviceService;
	@Autowired
	private ITerminalService terminalService; 
	@Autowired 
	private IPlaceService placeService;
	
	@RequiresPermissions("business:device:view")
	@GetMapping()
	public String device()
	{
	    return prefix + "/device";
	}
	
	/**
	 * 查询共享设备列表
	 */
	@RequiresPermissions("business:device:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Device device)
	{
		startPage();
        List<Device> list = deviceService.selectDeviceList(device);
		return getDataTable(list);
	}
	
	/**
	 * 新增共享设备
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存共享设备
	 */
	@RequiresPermissions("business:device:add")
	@Log(title = "共享设备", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Device device)
	{		
		String createor = getUser().getUserName();
		long userId = getUserId();
		
		device.setCreateBy(createor+"("+userId+")");
		device.setCreateTime(new Date());
		return toAjax(deviceService.insertDevice(device));
	}

	/**
	 * 修改共享设备
	 */
	@GetMapping("/edit/{deviceId}")
	public String edit(@PathVariable("deviceId") Integer deviceId, ModelMap mmap)
	{
		Device device = deviceService.selectDeviceById(deviceId);
		mmap.put("device", device);
		mmap.put("terminalDropBoxList", terminalService.selectDropBoxList());
		mmap.put("placeDropBoxList", placeService.selectDropBoxList());
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存共享设备
	 */
	@RequiresPermissions("business:device:edit")
	@Log(title = "共享设备", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Device device)
	{		
		String updateor = getUser().getUserName();
		long userId = getUserId();
		
		device.setUpdateBy(updateor+"("+userId+")");
		device.setUpdateTime(new Date());
		return toAjax(deviceService.updateDevice(device));
	}
	
	/**
	 * 删除共享设备
	 */
	@RequiresPermissions("business:device:remove")
	@Log(title = "共享设备", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(deviceService.deleteDeviceByIds(ids));
	}
	
	/**
	 * 查找设备下拉框数据
	 */
	@RequestMapping("/getDropBoxDeviceList")
    @ResponseBody
    public TableDataInfo getDropBoxDeviceList() {
		List<Device> list = deviceService.selectDropBoxList();
		return getDataTable(list);
    }
	
	/**
	 * 投放设备
	 */
	@GetMapping("/release/{deviceId}")
	public String release(@PathVariable("deviceId") Integer deviceId, ModelMap mmap)
	{
		Device device = deviceService.selectDeviceById(deviceId);
		mmap.put("device", device);
		mmap.put("placeDropBoxList", placeService.selectDropBoxList());
		
	    return prefix + "/release";
	}
	
	/**
	 *投放保存共享设备
	 */
	//@RequiresPermissions("business:device:release")
	@Log(title = "投放共享设备", businessType = BusinessType.UPDATE)
	@PostMapping("/release")
	@ResponseBody
	public AjaxResult releaseSave(Device device)
	{		
		String updateor = getUser().getUserName();
		long userId = getUserId();
		device.setUpdateBy(updateor+"("+userId+")");
		device.setUpdateTime(new Date());
		
		return toAjax(deviceService.updateDevice(device));
	}
	
}
