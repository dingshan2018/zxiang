package com.zxiang.project.advertise.adReleaseRecord.controller;

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
import com.zxiang.project.advertise.adReleaseRecord.domain.AdReleaseRecord;
import com.zxiang.project.advertise.adReleaseRecord.service.IAdReleaseRecordService;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.framework.web.domain.AjaxResult;

/**
 * 广告投放设备 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-25
 */
@Controller
@RequestMapping("/advertise/adReleaseRecord")
public class AdReleaseRecordController extends BaseController
{
    private String prefix = "advertise/adReleaseRecord";
	
	@Autowired
	private IAdReleaseRecordService adReleaseRecordService;
	
	@RequiresPermissions("advertise:adReleaseRecord:view")
	@GetMapping()
	public String adReleaseRecord()
	{
	    return prefix + "/adReleaseRecord";
	}
	
	/**
	 * 查询广告投放设备列表
	 */
	@RequiresPermissions("advertise:adReleaseRecord:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(AdReleaseRecord adReleaseRecord)
	{
		startPage();
        List<AdReleaseRecord> list = adReleaseRecordService.selectAdReleaseRecordList(adReleaseRecord);
		return getDataTable(list);
	}
	
	/**
	 * 新增广告投放设备
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存广告投放设备
	 */
	@RequiresPermissions("advertise:adReleaseRecord:add")
	@Log(title = "广告投放设备", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AdReleaseRecord adReleaseRecord)
	{		
		return toAjax(adReleaseRecordService.insertAdReleaseRecord(adReleaseRecord));
	}

	/**
	 * 修改广告投放设备
	 */
	@GetMapping("/edit/{releaseDeviceId}")
	public String edit(@PathVariable("releaseDeviceId") Integer releaseDeviceId, ModelMap mmap)
	{
		AdReleaseRecord adReleaseRecord = adReleaseRecordService.selectAdReleaseRecordById(releaseDeviceId);
		mmap.put("adReleaseRecord", adReleaseRecord);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存广告投放设备
	 */
	@RequiresPermissions("advertise:adReleaseRecord:edit")
	@Log(title = "广告投放设备", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(AdReleaseRecord adReleaseRecord)
	{		
		return toAjax(adReleaseRecordService.updateAdReleaseRecord(adReleaseRecord));
	}
	
	/**
	 * 删除广告投放设备
	 */
	@RequiresPermissions("advertise:adReleaseRecord:remove")
	@Log(title = "广告投放设备", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(adReleaseRecordService.deleteAdReleaseRecordByIds(ids));
	}
	
}
