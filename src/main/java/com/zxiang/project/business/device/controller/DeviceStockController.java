package com.zxiang.project.business.device.controller;

import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxiang.common.constant.UserConstants;
import com.zxiang.framework.aspectj.lang.annotation.Log;
import com.zxiang.framework.aspectj.lang.enums.BusinessType;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.domain.AjaxResult;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.project.business.device.domain.Device;
import com.zxiang.project.business.device.service.IDeviceService;
import com.zxiang.project.system.user.domain.User;
import com.zxiang.project.system.user.service.IUserService;

/**
 * 共享设备 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
@Controller
@RequestMapping("/business/deviceStock")
public class DeviceStockController extends BaseController
{
    private String prefix = "business/device";
    
	@Autowired
	private IDeviceService deviceService;
	@Autowired
	private IUserService userService;
	
	@RequiresPermissions("business:deviceStock:view")
	@GetMapping()
	public String deviceStock()
	{
	    return prefix + "/deviceStock";
	}
	
	/**
	 * 查询设备库存列表
	 */
	@RequiresPermissions("business:device:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Device device)
	{
		startPage();
		
        List<Device> list = deviceService.selectDeviceStockList(device);
		return getDataTable(list);
	}
	
	/**
	 * 新增共享设备
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap)
	{
		User queryUser = new User();
		queryUser.setUserType(UserConstants.USER_TYPE_JOIN);
		List<User> userListJoin = userService.selectUserList(queryUser);
		mmap.put("userList", userListJoin);
		
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
	 * 库存设备出库
	 */
	@RequiresPermissions("business:deviceStock:outStock")
	@Log(title = "库存设备出库", businessType = BusinessType.UPDATE)
	@PostMapping("/outStock")
	@ResponseBody
	public AjaxResult outStock(String ids)
	{
		int number = deviceService.outStock(ids);
		return success("成功出库" + number + " 台设备");
	}
}
