package com.zxiang.project.advertise.releaseDevice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zxiang.common.utils.excel.ExcelServiceUtil;
import com.zxiang.framework.aspectj.lang.annotation.Log;
import com.zxiang.framework.aspectj.lang.enums.BusinessType;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.domain.AjaxResult;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.project.advertise.adSchedule.domain.AdSchedule;
import com.zxiang.project.advertise.adSchedule.service.IAdScheduleService;
import com.zxiang.project.advertise.releaseDevice.domain.ReleaseDevice;
import com.zxiang.project.advertise.releaseDevice.mapper.ReleaseDeviceMapper;
import com.zxiang.project.advertise.releaseDevice.service.IReleaseDeviceService;
import com.zxiang.project.business.device.domain.Device;
import com.zxiang.project.business.device.service.IDeviceService;

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
	
	@Autowired
	private IAdScheduleService adScheduleService;
	
	@Autowired
	private IDeviceService deviceService;
	
	
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
	 * 批量导入终端编号-界面跳转
	 */
	@GetMapping("/batchImport/{adScheduleId}")
	public String batchImport(@PathVariable("adScheduleId") Integer adScheduleId,ModelMap mmap)
	{
		mmap.put("scheduleId", adScheduleId);
	    return prefix + "/batchImport";
	}
	
	/**
     * 批量导入广告设备
     */
	@Log(title = "批量导入广告设备", businessType = BusinessType.IMPORT)
    @RequestMapping(value = "/batchImport", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult batchImportSave(@RequestParam("fileUpload") MultipartFile file,HttpServletRequest request)
    {
    	int saveCount = 0;
    	try {
    		//解析Excel数据
    		String scheduleId = request.getParameter("scheduleId");
    		if(StringUtils.isBlank(scheduleId)) {
    			return AjaxResult.error("广告Id为空");
    		}
    		AdSchedule schedule = this.adScheduleService.selectAdScheduleById(Integer.parseInt(scheduleId));
    		if(schedule == null) {
    			return AjaxResult.error("广告不存在");
    		}
			List<Object> sheetList = ExcelServiceUtil.importData(file);
			String operatorUser = getUser().getUserName()+"("+getUserId()+")";
			//保存设备编号
			List<ReleaseDevice> releaseDevices = new ArrayList<ReleaseDevice>();
			if(sheetList!=null && sheetList.size()>0) {
				for(Object deviceCode :sheetList) {
					Device device = new Device();
					device.setDeviceCode(deviceCode.toString());
					List<Device> searchDevices = this.deviceService.selectDeviceList(device);
					if(searchDevices!=null && searchDevices.size()>0) {
						Device searchDevice = searchDevices.get(0);
						ReleaseDevice releaseDeviceParam = new ReleaseDevice();
						releaseDeviceParam.setScheduleId(schedule.getAdScheduleId());
						releaseDeviceParam.setDeviceId(searchDevice.getDeviceId());
						List<ReleaseDevice> releaseDeviceList = this.releaseDeviceService.selectReleaseDeviceList(releaseDeviceParam);
						if(!(releaseDeviceList!=null && releaseDeviceList.size()>0)) {
							ReleaseDevice release = new ReleaseDevice();
							release.setScheduleId(schedule.getAdScheduleId());
							release.setDeviceId(searchDevice.getDeviceId());
							release.setReleasePosition(schedule.getReleasePosition());
							releaseDevices.add(release);
						}
					}
					
				}
			}
			if(releaseDevices!=null && releaseDevices.size()>0) {
				return toAjax(releaseDeviceService.batchInsert(releaseDevices));
			}else {
				return AjaxResult.error("未投放设备未找到");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return error("导入失败！");
		}    	
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
	
	@PostMapping("/list2/{adScheduleId}")
	@ResponseBody
	public TableDataInfo list2(@PathVariable("adScheduleId") Integer adScheduleId, ReleaseDevice releaseDevice)
	{
		startPage();
		releaseDevice.setScheduleId(adScheduleId);
        List<ReleaseDevice> list = releaseDeviceService.selectReleaseDeviceList(releaseDevice);
		return getDataTable(list);
	}
	
	/**
	 * 新增投放终端配置
	 */
	@GetMapping("/add/{adScheduleId}")
	public String add(@PathVariable("adScheduleId") Integer adScheduleId, ModelMap mmap)
	{
		mmap.put("adScheduleId", adScheduleId);
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

	@Log(title = "批量投放终端配置", businessType = BusinessType.INSERT)
	@PostMapping("/batchAdd")
	@ResponseBody
	public AjaxResult batchAdd(ReleaseDevice releaseDevice)
	{	
		String deviceIds = releaseDevice.getDeviceIds();
		String[] deviceIdArr = deviceIds.split(",");
		List<ReleaseDevice> releaseDevices = new ArrayList<ReleaseDevice>();
		AdSchedule adSchedule = this.adScheduleService.selectAdScheduleById(releaseDevice.getScheduleId());
		for(String deviceId : deviceIdArr) {
			ReleaseDevice release = new ReleaseDevice();
			release.setScheduleId(releaseDevice.getScheduleId());
			release.setDeviceId(Integer.parseInt(deviceId));
			release.setReleasePosition(adSchedule.getReleasePosition());
			releaseDevices.add(release);
		}
		return toAjax(releaseDeviceService.batchInsert(releaseDevices));
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
//	@RequiresPermissions("advertise:releaseDevice:remove")
	@Log(title = "投放终端配置", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(releaseDeviceService.deleteReleaseDeviceByIds(ids));
	}
	
}
