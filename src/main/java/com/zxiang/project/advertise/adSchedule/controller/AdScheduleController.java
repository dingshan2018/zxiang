package com.zxiang.project.advertise.adSchedule.controller;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.zxiang.framework.aspectj.lang.annotation.Log;
import com.zxiang.framework.aspectj.lang.enums.BusinessType;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.domain.AjaxResult;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.project.advertise.adReleaseRange.mapper.AdReleaseRangeMapper;
import com.zxiang.project.advertise.adReleaseTimer.domain.AdReleaseTimer;
import com.zxiang.project.advertise.adReleaseTimer.mapper.AdReleaseTimerMapper;
import com.zxiang.project.advertise.adSchedule.domain.AdSchedule;
import com.zxiang.project.advertise.adSchedule.domain.ElementType;
import com.zxiang.project.advertise.adSchedule.domain.ThemeTemplate;
import com.zxiang.project.advertise.adSchedule.service.IAdScheduleService;
import com.zxiang.project.advertise.utils.constant.AdConstant;
import com.zxiang.project.business.device.domain.Device;
import com.zxiang.project.business.device.mapper.DeviceMapper;
import com.zxiang.project.business.place.service.IPlaceService;
import com.zxiang.project.client.advertise.domain.Advertise;
import com.zxiang.project.client.advertise.mapper.AdvertiseMapper;

/**
 * 广告投放 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-24
 */
@Controller
@RequestMapping("/advertise/adSchedule")
public class AdScheduleController extends BaseController
{
    private String prefix = "advertise/adSchedule";
	
	@Autowired
	private IAdScheduleService adScheduleService;
	@Autowired
	private DeviceMapper deviceMapper;
	@Autowired 
	private IPlaceService placeService;
	@Autowired
	private AdvertiseMapper advertiseMapper;
	@Autowired
	private AdReleaseTimerMapper adReleaseTimerMapper;
	@Autowired
	private AdReleaseRangeMapper adReleaseRangeMapper;
	
	 //01待预约；02待审核；03待发布；04待播放；05已播放；06审核失败；07排期失败
    
	
	@RequiresPermissions("advertise:adSchedule:view")
	@GetMapping()
	public String adSchedule()
	{
	    return prefix + "/adSchedule";
	}
	
	/**
	 * 查询广告投放列表
	 */
	@RequiresPermissions("advertise:adSchedule:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(AdSchedule adSchedule)
	{
		startPage();
        List<AdSchedule> list = adScheduleService.selectAdScheduleList(adSchedule);
		return getDataTable(list);
	}
	
	/**
	 * 新增广告投放
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap)
	{
		List<ThemeTemplate> ThemeTemplateList = adScheduleService.getThemeList();
		mmap.put("ThemeTemplateList", ThemeTemplateList);
		mmap.put("advertiserList", advertiseMapper.selectAdvertiseList(new Advertise()));
		
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存广告投放
	 */
	@RequiresPermissions("advertise:adSchedule:add")
	@Log(title = "广告投放", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AdSchedule adSchedule)
	{		
		try {
			String operatorUser = getUser().getUserName()+"("+getUserId()+")";	
			adSchedule.setStatus(AdConstant.AD_WAIT_ORDER);//待预约
			adSchedule.setCreateBy(operatorUser);
			adSchedule.setCreateTime(new Date());
			adSchedule.setIsDel("0");
			
			return toAjax(adScheduleService.saveAdTemplates(adSchedule));
		} catch (Exception e) {
			e.printStackTrace();
			return error();
		}
	}

	/**
	 * 修改广告投放
	 */
	@GetMapping("/edit/{adScheduleId}")
	public String edit(@PathVariable("adScheduleId") Integer adScheduleId, ModelMap mmap)
	{
		AdSchedule adSchedule = adScheduleService.selectAdScheduleById(adScheduleId);
		mmap.put("adSchedule", adSchedule);
		List<ThemeTemplate> ThemeTemplateList = adScheduleService.getThemeList();
		mmap.put("ThemeTemplateList", ThemeTemplateList);
		mmap.put("advertiserList", advertiseMapper.selectAdvertiseList(new Advertise()));
		
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存广告投放
	 */
	@RequiresPermissions("advertise:adSchedule:edit")
	@Log(title = "广告投放", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(AdSchedule adSchedule)
	{		
		String operatorUser = getUser().getUserName()+"("+getUserId()+")";	
		adSchedule.setUpdateBy(operatorUser);
		adSchedule.setUpdateTime(new Date());
		
		return toAjax(adScheduleService.updateAdSchedule(adSchedule));
	}
	
	/**
	 * 删除广告投放
	 */
	@RequiresPermissions("advertise:adSchedule:remove")
	@Log(title = "广告投放", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(adScheduleService.deleteAdScheduleByIds(ids));
	}
	
	/**
	 * 素材上传
	 */
	@GetMapping("/materialUpload/{adScheduleId}")
	public String materialUpload(@PathVariable("adScheduleId") Integer adScheduleId, ModelMap mmap)
	{
		AdSchedule adSchedule = adScheduleService.selectAdScheduleById(adScheduleId);
		mmap.put("adSchedule", adSchedule);
		try {
			List<ElementType> elementList = adScheduleService.getElementList(adSchedule.getThemeTemplateId());
			mmap.put("elementList", elementList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	    return prefix + "/materialUpload";
	}
	
	/**
     * 素材上传保存
     */
	@RequestMapping(value = "/materialUploadSave", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult materialUploadSave(HttpServletRequest request)
    {
    	 try {
			String adScheduleId = request.getParameter("adScheduleId");
			String elementId = request.getParameter("elementId");
			String operatorUser = getUser().getUserName()+"("+getUserId()+")";	
			
			 List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
			 return toAjax(adScheduleService.materialUpload(files,adScheduleId,elementId,operatorUser));
		} catch (Exception e) {
			e.printStackTrace();
			return error();
		}
    }
	
	/**
	 * 广告投放预约
	 */
	@GetMapping("/order/{adScheduleId}")
	public String order(@PathVariable("adScheduleId") Integer adScheduleId, ModelMap mmap)
	{
		AdSchedule adSchedule = adScheduleService.selectAdScheduleById(adScheduleId);
		mmap.put("adSchedule", adSchedule);
		mmap.put("devices", deviceMapper.selectDeviceList(new Device()));
		mmap.put("placeDropBoxList", placeService.selectDropBoxList());
		
	    return prefix + "/order";
	}
	
	/**
	 * 广告投放预约保存
	 */
	@RequiresPermissions("advertise:adSchedule:edit")
	@Log(title = "广告投放预约保存", businessType = BusinessType.UPDATE)
	@PostMapping("/orderSave")
	@ResponseBody
	public AjaxResult orderSave(AdSchedule adSchedule)
	{
		try {
			String operatorUser = getUser().getUserName()+"("+getUserId()+")";	
			return toAjax(adScheduleService.orderSave(adSchedule,operatorUser));
		} catch (Exception e) {
			e.printStackTrace();
			return error();
		}
	}
	
	/**
	 * 广告投放审核
	 */
	@GetMapping("/audit/{adScheduleId}")
	public String audit(@PathVariable("adScheduleId") Integer adScheduleId, ModelMap mmap)
	{
		AdSchedule adSchedule = adScheduleService.selectAdScheduleById(adScheduleId);
		mmap.put("adSchedule", adSchedule);
		
	    return prefix + "/audit";
	}
	
	/**
	 * 广告投放审核保存
	 */
	@RequiresPermissions("advertise:adSchedule:audit")
	@Log(title = "广告投放审核保存", businessType = BusinessType.UPDATE)
	@PostMapping("/auditSave")
	@ResponseBody
	public AjaxResult auditSave(AdSchedule adSchedule)
	{
		try {
			String operatorUser = getUser().getUserName()+"("+getUserId()+")";	
			return toAjax(adScheduleService.auditSave(adSchedule,operatorUser));
		} catch (Exception e) {
			e.printStackTrace();
			return error();
		}
	}
	
	/**
	 * 广告投放发布保存
	 */
	@RequiresPermissions("advertise:adSchedule:releaseOnline")
	@Log(title = "广告投放发布保存", businessType = BusinessType.UPDATE)
	@PostMapping("/releaseOnlineSave")
	@ResponseBody
	public AjaxResult releaseOnlineSave(AdSchedule adSchedule)
	{
		String operatorUser = getUser().getUserName()+"("+getUserId()+")";	
		adSchedule.setUpdateBy(operatorUser);
		adSchedule.setUpdateTime(new Date());
		
		int releaseNumber = adScheduleService.releaseOnlineSave(adSchedule);
		return success("成功发布 "+ releaseNumber + " 条广告");
	}
	
	/**
	 * 广告素材预览
	 */
	@GetMapping("/preview/{adScheduleId}")
	public String preview(@PathVariable("adScheduleId") Integer adScheduleId, ModelMap mmap)
	{
		AdSchedule adSchedule = adScheduleService.selectAdScheduleById(adScheduleId);
		mmap.put("adSchedule", adSchedule);
		
	    return prefix + "/preview";
	}
	
	/**
	 * 广告详情
	 */
	@GetMapping("/adDetail/{adScheduleId}")
	public String adDetail(@PathVariable("adScheduleId") Integer adScheduleId, ModelMap mmap)
	{
		AdSchedule adSchedule = adScheduleService.selectAdScheduleById(adScheduleId);
		mmap.put("adSchedule", adSchedule);
		List<ThemeTemplate> ThemeTemplateList = adScheduleService.getThemeList();
		mmap.put("ThemeTemplateList", ThemeTemplateList);
		mmap.put("advertiserList", advertiseMapper.selectAdvertiseList(new Advertise()));
		
		AdReleaseTimer queryTimer = new AdReleaseTimer();
		queryTimer.setAdScheduleId(adScheduleId);
		mmap.put("adTimerList", adReleaseTimerMapper.selectAdReleaseTimerList(queryTimer));

		mmap.put("adRange", adReleaseRangeMapper.selectAdRangeByAd(adScheduleId));
		
	    return prefix + "/adDetail";
	}
	
	/**
	 * 导出Excel
	 * 
	 */
	@RequestMapping("/excelExport")
	public void excelExport(@RequestParam HashMap<String, String> params, 
			HttpServletResponse response,HttpServletRequest request){

		try {
			adScheduleService.queryExport(params, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
