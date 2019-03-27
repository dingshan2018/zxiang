package com.zxiang.project.advertise.releaseDevice.controller;

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
import com.zxiang.project.advertise.releaseDevice.domain.ReleaseDevice;
import com.zxiang.project.advertise.releaseDevice.service.IReleaseDeviceService;

/**
 * 投放终端配置 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-11-22
 */
@Controller
@RequestMapping("/advertise/releaseDevice")
public class ReleaseDeviceController extends BaseController
{
    private String prefix = "advertise/releaseDevice";
	
	@Autowired
	private IReleaseDeviceService releaseDeviceService;
	
	//@RequiresPermissions("advertise:releaseDevice:view")
	@GetMapping()
	public String releaseDevice()
	{
	    return prefix + "/releaseDevice";
	}
	
	@GetMapping("/editDevice/{adScheduleId}")
	public String editDevice(@PathVariable("adScheduleId") Integer adScheduleId, ModelMap mmap)
	{
		mmap.put("adScheduleId", adScheduleId);
	    return prefix + "/releaseDevice";
	}
	
	/**
	 * 查询投放终端配置列表
	 */
	//@RequiresPermissions("advertise:releaseDevice:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ReleaseDevice releaseDevice)
	{
		startPage();
        List<ReleaseDevice> list = releaseDeviceService.selectReleaseDeviceList(releaseDevice);
		return getDataTable(list);
	}
	
	/**
	 * 新增投放终端配置
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存投放终端配置
	 */
	//@RequiresPermissions("advertise:releaseDevice:add")
	@Log(title = "投放终端配置", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ReleaseDevice releaseDevice)
	{		
		return toAjax(releaseDeviceService.insertReleaseDevice(releaseDevice));
	}

	/**
	 * 修改投放终端配置
	 */
	@GetMapping("/edit/{releaseDeviceId}")
	public String edit(@PathVariable("releaseDeviceId") Integer releaseDeviceId, ModelMap mmap)
	{
		ReleaseDevice releaseDevice = releaseDeviceService.selectReleaseDeviceById(releaseDeviceId);
		mmap.put("releaseDevice", releaseDevice);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存投放终端配置
	 */
	//@RequiresPermissions("advertise:releaseDevice:edit")
	@Log(title = "投放终端配置", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ReleaseDevice releaseDevice)
	{		
		return toAjax(releaseDeviceService.updateReleaseDevice(releaseDevice));
	}
	
	/**
	 * 删除投放终端配置
	 */
	@RequiresPermissions("advertise:releaseDevice:remove")
	@Log(title = "投放终端配置", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(releaseDeviceService.deleteReleaseDeviceByIds(ids));
	}
	
}
