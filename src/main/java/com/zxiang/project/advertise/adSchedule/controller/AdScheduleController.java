package com.zxiang.project.advertise.adSchedule.controller;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.PartSource;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jboss.logging.Logger;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zxiang.common.constant.UserConstants;
import com.zxiang.common.exception.RRException;
import com.zxiang.common.utils.StringUtils;
import com.zxiang.framework.aspectj.lang.annotation.DataFilter;
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
import com.zxiang.project.advertise.adSchedule.domain.MaterialResult;
import com.zxiang.project.advertise.adSchedule.domain.ThemeTemplate;
import com.zxiang.project.advertise.adSchedule.service.IAdScheduleService;
import com.zxiang.project.advertise.utils.AdHttpResult;
import com.zxiang.project.advertise.utils.SignUtil;
import com.zxiang.project.advertise.utils.Tools;
import com.zxiang.project.advertise.utils.constant.AdConstant;
import com.zxiang.project.business.device.domain.Device;
import com.zxiang.project.business.device.mapper.DeviceMapper;
import com.zxiang.project.business.place.service.IPlaceService;
import com.zxiang.project.client.advertise.domain.Advertise;
import com.zxiang.project.client.advertise.mapper.AdvertiseMapper;
import com.zxiang.project.system.area.domain.Area;
import com.zxiang.project.system.area.mapper.AreaMapper;
import com.zxiang.project.system.config.domain.Config;
import com.zxiang.project.system.config.mapper.ConfigMapper;
import com.zxiang.project.system.user.domain.User;
import com.zxiang.project.system.user.service.IUserService;

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
	Logger logger = Logger.getLogger(AdScheduleController.class);
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
	@Autowired
	private AreaMapper areaMapper;
	@Autowired
	private IUserService userService;
	@Autowired
	private ConfigMapper configMapper;
	 
	@RequiresPermissions("advertise:adSchedule:view")
	@GetMapping()
	public String adSchedule()
	{
	    return prefix + "/adSchedule";
	}
	
	/**
	 * 查询广告投放列表
	 */
	@DataFilter(placeAlias="b.place_id")
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
		//推荐人下拉框
		List<User> userList = userService.selectUserListByUserType(UserConstants.USER_TYPE_ADVERTISE,UserConstants.USER_TYPE_PARTNER,
				UserConstants.USER_TYPE_AGENT,UserConstants.USER_TYPE_JOIN,UserConstants.USER_TYPE_REPAIR);
		mmap.put("promotionerList", userList);
		
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
			if(StringUtils.isNotBlank(adSchedule.getQrUrl())) {
				adSchedule.setQrUrl(StringEscapeUtils.unescapeHtml(adSchedule.getQrUrl()));
			}
			
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
		if(StringUtils.isNotBlank(adSchedule.getQrUrl())) {
			adSchedule.setQrUrl(StringEscapeUtils.unescapeHtml(adSchedule.getQrUrl()));
		}
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
		try {
			return toAjax(adScheduleService.deleteAdScheduleByIds(ids));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return error("删除广告失败");
		}
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
	@RequiresPermissions("advertise:adSchedule:add")
	@Log(title = "广告素材上传保存", businessType = BusinessType.UPDATE)
	@RequestMapping(value = "/materialUploadSave", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult materialUploadSave(HttpServletRequest request)
    {
    	 try {
			String adScheduleId = request.getParameter("adScheduleId");
			String elementId = request.getParameter("elementId");
			String elementName = request.getParameter("elementName");//素材类型,保存在ad_material表remark字段用来判断价格
			String operatorUser = getUser().getUserName()+"("+getUserId()+")";	
			
			 List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
			 return toAjax(adScheduleService.materialUpload(files,adScheduleId,elementId,elementName,operatorUser));
		} catch (Exception e) {
			e.printStackTrace();
			return error();
		}
    }
	
	/**
     * 素材上传保存
     */
	@RequiresPermissions("advertise:adSchedule:add")
	@Log(title = "广告素材上传保存", businessType = BusinessType.UPDATE)
	@RequestMapping(value = "/materialUploadSave2", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult materialUploadSave2(HttpServletRequest request)
    {
    	 try {
			String adScheduleId = request.getParameter("adScheduleId");
			String materialText = request.getParameter("materialText");
			//String elementId = request.getParameter("elementId");
			//String elementName = request.getParameter("elementName");//素材类型,保存在ad_material表remark字段用来判断价格
			String operatorUser = getUser().getUserName()+"("+getUserId()+")";	
			
			 List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
			 
			if (!files.isEmpty()) {
				String result = uploadMaterial(files,operatorUser);
				// 调用上传文件的接口
				// 返回结果封装
				if(StringUtils.isBlank(result)) {
					return error("素材服务器上传异常");
				}else {
					
					JSONObject materialObject = JSONObject.parseObject("result");
					
					if ("0".equals(materialObject.getString("code"))) {
						JSONArray materialArray = materialObject.getJSONArray("materials");
						if(materialArray.size()>0) {
							List<MaterialResult> retMaterials = materialArray.toJavaList(MaterialResult.class);
							adScheduleService.materialUpload2(retMaterials,adScheduleId,operatorUser);
						}else {
							throw new Exception("不存在图片、视频素材上传");
						}
						
					
						return success("素材上传成功");
					}else {
						return error("素材上传失败");
					}
				}	
			}
			return error("素材文件不存在");
		} catch (Exception e) {
			e.printStackTrace();
			return error();
		}
    }
    	 
    private String uploadMaterial(List<MultipartFile> files,String operator) throws Exception {
    	Config config = new Config();
		config.setConfigKey("AD_MATERIAL_URL");
		Config retConfig = configMapper.selectConfig(config);
		String rootUrl = StringUtils.isNotNull(retConfig) ? retConfig.getConfigValue()
				: "http://mmedia.bp.zcloudtechs.cn";
		PostMethod postMethod = new PostMethod(rootUrl + AdConstant.AD_URL_MATERIAL);
		HttpClient client = new HttpClient();
		File file = null;
		try {
			List<Part> fileParts = new ArrayList<Part>();
			if(files!=null && files.size()>0) {
				//List<File> attachfiles = new ArrayList<File>();
				for(MultipartFile multipartFile : files) {
					InputStream ins = multipartFile.getInputStream();
					file = new File(multipartFile.getOriginalFilename());
					Tools.inputStreamToFile(ins, file);
					FilePart myUpload = new FilePart("Filedata", file);	
					fileParts.add(myUpload);
				}
			}
			

			// FilePart：用来上传文件的类
			fileParts.add(new StringPart("belong",operator));
			Part[] parts = (Part[])fileParts.toArray();

			MultipartRequestEntity mre = new MultipartRequestEntity(
					(org.apache.commons.httpclient.methods.multipart.Part[]) parts, postMethod.getParams());
			postMethod.setRequestEntity(mre);
			HashMap<String,String> headerMap = new HashMap<String,String>();
	        config.setConfigKey("AD_API_URL");
			Config cConfig = this.configMapper.selectConfig(config);
			config.setConfigKey("AD_API_APPID");
			cConfig = this.configMapper.selectConfig(config);
			String appId = cConfig.getConfigValue();
			headerMap.put("appid", appId);
			postMethod.setRequestHeader(new Header("appid",appId));
			config.setConfigKey("AD_API_SECRECT");
			String appSecrect = cConfig.getConfigValue();
			headerMap.put("appid", appId);
			String timestamp = new Date().getTime()+"";
			headerMap.put("timestamp", timestamp);
			postMethod.setRequestHeader(new Header("timestamp",timestamp));
			headerMap.put("nonce", appId+"_"+timestamp);
			postMethod.setRequestHeader(new Header("nonce",appId+"_"+timestamp));
			postMethod.setRequestHeader("sign", SignUtil.createSign(headerMap,appSecrect));
			client.getHttpConnectionManager().getParams().setConnectionTimeout(50000);// 设置连接时间
			int status = client.executeMethod(postMethod);
			if (status == HttpStatus.SC_OK) {
				String result = postMethod.getResponseBodyAsString();
				return result;
			}
		} catch (Exception e) {
			logger.error("addElement error:" + e);
			throw e;
		} finally {
			if (file.exists()) {
				file.delete();
			}
			// 释放连接
			postMethod.releaseConnection();
		}

		return null;
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
	@RequiresPermissions("advertise:adSchedule:add")
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
	 * 播放广告
	 */
	@RequiresPermissions("advertise:adSchedule:republish")
	@Log(title = "广告投放变更", businessType = BusinessType.UPDATE)
	@PostMapping("/republish")
	@ResponseBody
	public AjaxResult republish(AdSchedule adSchedule)
	{
		try {
			String operatorUser = getUser().getUserName()+"("+getUserId()+")";	
			return toAjax(adScheduleService.republish(adSchedule,operatorUser));
		} catch (Exception e) {
			e.printStackTrace();
			return error();
		}
	}
	/**
	 * 停播广告
	 * @param adSchedule
	 * @return
	 */
	@RequiresPermissions("advertise:adSchedule:removeAd")
	@Log(title = "广告下架", businessType = BusinessType.UPDATE)
	@PostMapping("/removeAd")
	@ResponseBody
	public AjaxResult removeAd(AdSchedule adSchedule)
	{
		try {
			String operatorUser = getUser().getUserName()+"("+getUserId()+")";	
			return toAjax(adScheduleService.removeAd(adSchedule,operatorUser));
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
		try {
			String operatorUser = getUser().getUserName()+"("+getUserId()+")";	
			adSchedule.setUpdateBy(operatorUser);
			adSchedule.setUpdateTime(new Date());
			
			int releaseNumber = adScheduleService.releaseOnlineSave(adSchedule);
			return success("成功发布 "+ releaseNumber + " 条广告");
		} catch (IOException e) {
			e.printStackTrace();
			return error();
		}
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
		mmap.put("placeList", placeService.selectDropBoxList());
		mmap.put("areaList", areaMapper.selectAreaList(new Area()));
		mmap.put("deviceList", deviceMapper.selectDeviceList(new Device()));
		
	    return prefix + "/adDetail";
	}
	
	/**
	 * 导出Excel
	 * 
	 */
	@DataFilter(placeAlias="b.place_id")
	@RequestMapping("/excelExport")
	public void excelExport(@RequestParam HashMap<String, String> params, 
			HttpServletResponse response,HttpServletRequest request){

		try {
			adScheduleService.queryExport(params, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
     * 获取视讯排期ID查看信息
     */
    @RequestMapping("/selectSxScheduleId/{adScheduleId}")
    @ResponseBody
    public String selectSxScheduleId(@PathVariable("adScheduleId") Integer adScheduleId) {
    	AdSchedule adSchedule = adScheduleService.selectAdScheduleById(adScheduleId);
        return adSchedule.getSxScheduleId();
    }
    
    /**
     * 获取广告数据信息
     */
    @RequestMapping("/selectInfo/{adScheduleId}")
    @ResponseBody
    public AdSchedule selectInfo(@PathVariable("adScheduleId") Integer adScheduleId) {
    	AdSchedule adSchedule = adScheduleService.selectAdScheduleById(adScheduleId);
        return adSchedule;
    }
    
    /**
     * 获取广告支付二维码
     */
    @GetMapping("/adPay/{adScheduleId}")
    public String adPay(@PathVariable("adScheduleId") Integer adScheduleId,ModelMap mmap)  {
    	
    	try {
			AdSchedule adSchedule = adScheduleService.selectAdScheduleById(adScheduleId);
			float totalFee = adSchedule.getTotalPay();
			
			String url = AdConstant.AD_PAY_PREFIX;
			Map<String, String> paramsMap = new HashMap<String, String>();
			paramsMap.put("total_fee", String.valueOf(totalFee));
			paramsMap.put("scheduleId", adScheduleId.toString());
			paramsMap.put("order_type", AdConstant.PAY_TYPE_ALL);
			
			String requst = Tools.paramsToString(paramsMap);
			String result = Tools.doPostForm(url, requst);
			AdHttpResult adHttp = Tools.analysisResult(result);
			JSONObject jsonResult =  (JSONObject) adHttp.get("data");
			String qrCode = jsonResult.getString("qrCode");
			if(StringUtils.isNotEmpty(qrCode)){
				mmap.put("qrCodeUrl", qrCode);
				return prefix + "/qrCode";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	throw new RRException("生成支付二维码失败!");
    }
    
    /**
	 * 根据推荐人或者投放人查询用户下拉框数据列表
	 */
	@RequestMapping("/getSearchUserList")
    @ResponseBody
    public TableDataInfo getSearchUserList(@RequestBody Map<String, Object> params) {
		List<User> list = new ArrayList<User>();
		String searchKey = (String) params.get("searchKey");
		if("releaser".equals(searchKey)){
			list= userService.selectUserListByUserType(UserConstants.USER_TYPE_ADVERTISE);
		}else if("promotioner".equals(searchKey)){
			list= userService.selectUserListByUserType(UserConstants.USER_TYPE_ADVERTISE,UserConstants.USER_TYPE_PARTNER,
					UserConstants.USER_TYPE_AGENT,UserConstants.USER_TYPE_JOIN,UserConstants.USER_TYPE_REPAIR);
		}
		return getDataTable(list);
    }
	
	
	/**
	 * 广告支付--通过广告商账户余额支付
	 */
	@Log(title = "广告费用通过钱包支付", businessType = BusinessType.UPDATE)
	@PostMapping("/adPayByAccount")
	@ResponseBody
	public AjaxResult adPayByAccount(Integer adScheduleId)
	{
		try {
			String operatorUser = getUser().getUserName()+"("+getUserId()+")";	
			adScheduleService.adPayByAccount(adScheduleId,operatorUser);
			return success("支付成功!");
		} catch (Exception e) {
			e.printStackTrace();
			return error(e.getMessage()+" ,操作失败!");
		}
	}
	
	/**
     * 校验广告投放名称是否唯一
     */
    @PostMapping("/checkNameUnique")
    @ResponseBody
    public String checkNameUnique(String scheduleName)
    {
        return adScheduleService.checkNameUnique(scheduleName);
    }
}
