package com.zxiang.project.business.deviceReleaseAudit.controller;

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
import com.zxiang.project.business.deviceReleaseAudit.domain.DeviceReleaseAudit;
import com.zxiang.project.business.deviceReleaseAudit.service.IDeviceReleaseAuditService;

/**
 * 设备投放审核 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-12-12
 */
@Controller
@RequestMapping("/business/deviceReleaseAudit")
public class DeviceReleaseAuditController extends BaseController
{
    private String prefix = "business/deviceReleaseAudit";
	
	@Autowired
	private IDeviceReleaseAuditService deviceReleaseAuditService;
	
	@RequiresPermissions("business:deviceReleaseAudit:view")
	@GetMapping()
	public String deviceReleaseAudit()
	{
	    return prefix + "/deviceReleaseAudit";
	}
	
	/**
	 * 查询设备投放审核列表
	 */
	@RequiresPermissions("business:deviceReleaseAudit:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DeviceReleaseAudit deviceReleaseAudit)
	{
		startPage();
        List<DeviceReleaseAudit> list = deviceReleaseAuditService.selectDeviceReleaseAuditList(deviceReleaseAudit);
		return getDataTable(list);
	}
	
	/**
	 * 新增设备投放审核
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存设备投放审核
	 */
	@RequiresPermissions("business:deviceReleaseAudit:add")
	@Log(title = "设备投放审核", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(DeviceReleaseAudit deviceReleaseAudit)
	{		
		return toAjax(deviceReleaseAuditService.insertDeviceReleaseAudit(deviceReleaseAudit));
	}

	/**
	 * 修改设备投放审核
	 */
	@GetMapping("/edit/{auditId}")
	public String edit(@PathVariable("auditId") Integer auditId, ModelMap mmap)
	{
		DeviceReleaseAudit deviceReleaseAudit = deviceReleaseAuditService.selectDeviceReleaseAuditById(auditId);
		mmap.put("deviceReleaseAudit", deviceReleaseAudit);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存设备投放审核
	 */
	@RequiresPermissions("business:deviceReleaseAudit:edit")
	@Log(title = "设备投放审核", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(DeviceReleaseAudit deviceReleaseAudit)
	{		
		return toAjax(deviceReleaseAuditService.updateDeviceReleaseAudit(deviceReleaseAudit));
	}
	
	/**
	 * 删除设备投放审核
	 */
	@RequiresPermissions("business:deviceReleaseAudit:remove")
	@Log(title = "设备投放审核", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(deviceReleaseAuditService.deleteDeviceReleaseAuditByIds(ids));
	}
	
}
