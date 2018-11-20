package com.zxiang.project.business.device.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zxiang.common.constant.UserConstants;
import com.zxiang.common.utils.excel.ExcelServiceUtil;
import com.zxiang.framework.aspectj.lang.annotation.DataFilter;
import com.zxiang.framework.aspectj.lang.annotation.Log;
import com.zxiang.framework.aspectj.lang.enums.BusinessType;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.domain.AjaxResult;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.project.business.device.domain.Device;
import com.zxiang.project.business.device.service.IDeviceService;
import com.zxiang.project.business.place.service.IPlaceService;
import com.zxiang.project.business.terminal.service.ITerminalService;
import com.zxiang.project.system.user.domain.User;
import com.zxiang.project.system.user.service.IUserService;

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
	@Autowired
	private IUserService userService;
	
	@RequiresPermissions("business:device:view")
	@GetMapping()
	public String device()
	{
	    return prefix + "/device";
	}
	
	/**
	 * 查询共享设备列表
	 */
	@DataFilter(placeAlias="place_id")
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
	 * 修改共享设备
	 */
	@GetMapping("/edit/{deviceId}")
	public String edit(@PathVariable("deviceId") Integer deviceId, ModelMap mmap)
	{
		Device device = deviceService.selectDeviceById(deviceId);
		mmap.put("device", device);
		mmap.put("terminalDropBoxList", terminalService.selectDropBoxList());
		mmap.put("placeDropBoxList", placeService.selectDropBoxList());
		User queryUser = new User();
		queryUser.setUserType(UserConstants.USER_TYPE_JOIN);
		List<User> userListJoin = userService.selectUserList(queryUser);
		mmap.put("userList", userListJoin);
		
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
    public TableDataInfo getDropBoxDeviceList(@RequestBody Map<String, Object> params) {
		String status = (String) params.get("status");
		System.out.println("status:"+status);
		List<Device> list = deviceService.selectDropBoxList(status);
		return getDataTable(list);
    }
	
	/**
	 * 设备投放
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
	 *投放保存
	 */
	@RequiresPermissions("business:device:release")
	@Log(title = "投放共享设备保存", businessType = BusinessType.UPDATE)
	@PostMapping("/release")
	@ResponseBody
	public AjaxResult releaseSave(Device device)
	{		
		String updateor = getUser().getUserName();
		long userId = getUserId();
		device.setUpdateBy(updateor+"("+userId+")");
		device.setUpdateTime(new Date());
		
		return toAjax(deviceService.releaseUpdateDevice(device));
	}
	
	/**
	 * 设备撤机
	 */
	@GetMapping("/removeDevice/{deviceId}")
	public String removeDevice(@PathVariable("deviceId") Integer deviceId, ModelMap mmap)
	{
		Device device = deviceService.selectDeviceById(deviceId);
		mmap.put("device", device);
		
	    return prefix + "/removeDevice";
	}
	
	/**
	 *撤机保存
	 */
	@RequiresPermissions("business:device:removeDevice")
	@Log(title = "撤机共享设备保存", businessType = BusinessType.UPDATE)
	@PostMapping("/removeDevice")
	@ResponseBody
	public AjaxResult removeDeviceSave(Device device)
	{		
		String updateor = getUser().getUserName();
		long userId = getUserId();
		device.setUpdateBy(updateor+"("+userId+")");
		device.setUpdateTime(new Date());
		
		return toAjax(deviceService.removeDeviceUpdate(device));
	}
	
	/**
	 * 设备换板
	 */
	@GetMapping("/changeDevice/{deviceId}")
	public String changeDevice(@PathVariable("deviceId") Integer deviceId, ModelMap mmap)
	{
		Device device = deviceService.selectDeviceById(deviceId);
		mmap.put("device", device);
		mmap.put("terminalDropBoxList", terminalService.getDropBoxValidlList());
		
		User queryUser = new User();
		queryUser.setUserType(UserConstants.USER_TYPE_REPAIR);
		List<User> userList = userService.selectUserList(queryUser);
		mmap.put("userList", userList);
		
	    return prefix + "/changeDevice";
	}
	
	/**
	 *换板保存
	 */
	@RequiresPermissions("business:device:changeDevice")
	@Log(title = "设备换板保存", businessType = BusinessType.UPDATE)
	@PostMapping("/changeDevice")
	@ResponseBody
	public AjaxResult changeDevice(Device device)
	{		
		String updateor = getUser().getUserName();
		long userId = getUserId();
		String operatorUser = updateor+"("+userId+")";
		device.setUpdateBy(operatorUser);
		device.setUpdateTime(new Date());
		
		return toAjax(deviceService.changeDevice(device,operatorUser));
	}
	
	/**
	 * 设备补纸
	 */
	@GetMapping("/supplyTissueAdd/{deviceId}")
	public String supplyTissueAdd(@PathVariable("deviceId") Integer deviceId, ModelMap mmap)
	{
		Device device = deviceService.selectDeviceById(deviceId);
		mmap.put("device", device);
		mmap.put("placeDropBoxList", placeService.selectDropBoxList());
		
		User queryUser = new User();
		queryUser.setUserType(UserConstants.USER_TYPE_REPAIR);
		List<User> userList = userService.selectUserList(queryUser);
		mmap.put("userList", userList);
		
	    return prefix + "/supplyTissueAdd";
	}
	
	/**
	 *补纸保存
	 */
	@RequiresPermissions("business:device:supplyTissueAdd")
	@Log(title = "设备补纸保存", businessType = BusinessType.UPDATE)
	@PostMapping("/supplyTissueAdd")
	@ResponseBody
	public AjaxResult supplyTissueAdd(Device device)
	{		
		String updateor = getUser().getUserName();
		long userId = getUserId();
		String operatorUser = updateor+"("+userId+")";
		device.setUpdateBy(operatorUser);
		device.setUpdateTime(new Date());
		
		return toAjax(deviceService.supplyTissueAdd(device,operatorUser));
	}
	
	/**
	 * 根据ID查找设备
	 */
	@RequestMapping("/getDeviceById")
    @ResponseBody
    public TableDataInfo getDeviceById(@RequestBody Map<String, Object> params) {
		String deviceId = (String) params.get("deviceId");
		Device device = deviceService.selectDeviceById(Integer.parseInt(deviceId));
		List<Device> list = new ArrayList<>();
		list.add(device);
		return getDataTable(list);
    }
	
	/**
	 * 根据场所ID查找已投放设备列表
	 */
	@RequestMapping("/getDeviceByPlaceId")
    @ResponseBody
    public TableDataInfo getDeviceByPlaceId(@RequestBody Map<String, Object> params) {
		String placeId = (String) params.get("placeId");
		List<Device> list = deviceService.getDeviceByPlaceId(placeId);
		return getDataTable(list);
    }
	
	/**
	 * 根据地区ID查找所有已投放设备列表
	 */
	@RequestMapping("/getDeviceByareaId")
    @ResponseBody
    public TableDataInfo getDeviceByareaId(@RequestBody Map<String, Object> params) {
		String province = (String) params.get("province");
		String city = (String) params.get("city");
		String county = (String) params.get("county");
		
		List<Device> list = deviceService.getDeviceByareaId(province,city,county);
		return getDataTable(list);
    }
	
	/**
	 * 批量导入设备资产编号-界面跳转
	 */
	@GetMapping("/batchImport")
	public String batchImport()
	{
	    return prefix + "/batchImport";
	}
	
	/**
     * 批量导入设备资产编号保存
     */
    @RequestMapping(value = "/batchImport", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult batchImportSave(@RequestParam("fileUpload") MultipartFile file)
    {
    	int saveCount = 0;
    	try {
    		//解析Excel数据
			List<Object> sheetList = ExcelServiceUtil.importData(file);
			String operatorUser = getUser().getUserName()+"("+getUserId()+")";
			//保存终端编号
			saveCount = deviceService.saveBatchImport(sheetList,operatorUser);
			
		} catch (Exception e) {
			e.printStackTrace();
			return error("导入失败！");
		}    	

        return success("成功导入 "+ saveCount +" 条数据!");
    }
}
