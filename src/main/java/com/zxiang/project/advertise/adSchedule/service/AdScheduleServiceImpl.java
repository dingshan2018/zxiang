package com.zxiang.project.advertise.adSchedule.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.jboss.logging.Logger;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zxiang.common.exception.RRException;
import com.zxiang.common.support.Convert;
import com.zxiang.project.advertise.adMaterial.domain.AdMaterial;
import com.zxiang.project.advertise.adMaterial.mapper.AdMaterialMapper;
import com.zxiang.project.advertise.adReleaseRange.domain.AdReleaseRange;
import com.zxiang.project.advertise.adReleaseRange.mapper.AdReleaseRangeMapper;
import com.zxiang.project.advertise.adReleaseTimer.domain.AdReleaseTimer;
import com.zxiang.project.advertise.adReleaseTimer.mapper.AdReleaseTimerMapper;
import com.zxiang.project.advertise.adSchedule.domain.AdSchedule;
import com.zxiang.project.advertise.adSchedule.domain.ElementType;
import com.zxiang.project.advertise.adSchedule.domain.ThemeTemplate;
import com.zxiang.project.advertise.adSchedule.mapper.AdScheduleMapper;
import com.zxiang.project.advertise.utils.AdHttpResult;
import com.zxiang.project.advertise.utils.Tools;
import com.zxiang.project.advertise.utils.constant.AdConstant;
import com.zxiang.project.business.device.mapper.DeviceMapper;

/**
 * 广告投放 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-24
 */
@Service
public class AdScheduleServiceImpl implements IAdScheduleService 
{
	Logger logger = Logger.getLogger(AdScheduleServiceImpl.class);
	private SimpleDateFormat yyyyMMddHHmmSFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private SimpleDateFormat yyyyMMddSFormat = new SimpleDateFormat("yyyy-MM-dd");
			
	@Autowired
	private AdScheduleMapper adScheduleMapper;
	@Autowired
	private AdReleaseRangeMapper adReleaseRangeMapper;
	@Autowired
	private AdReleaseTimerMapper adReleaseTimerMapper;
	@Autowired
	private DeviceMapper deviceMapper;
	@Autowired
	private AdMaterialMapper adMaterialMapper;

	/**
     * 查询广告投放信息
     * 
     * @param adScheduleId 广告投放ID
     * @return 广告投放信息
     */
    @Override
	public AdSchedule selectAdScheduleById(Integer adScheduleId)
	{
	    return adScheduleMapper.selectAdScheduleById(adScheduleId);
	}
	
	/**
     * 查询广告投放列表
     * 
     * @param adSchedule 广告投放信息
     * @return 广告投放集合
     */
	@Override
	public List<AdSchedule> selectAdScheduleList(AdSchedule adSchedule)
	{
	    return adScheduleMapper.selectAdScheduleList(adSchedule);
	}
	
    /**
     * 新增广告投放
     * 
     * @param adSchedule 广告投放信息
     * @return 结果
     */
	@Override
	public int insertAdSchedule(AdSchedule adSchedule)
	{
	    return adScheduleMapper.insertAdSchedule(adSchedule);
	}
	
	/**
     * 修改广告投放
     * 
     * @param adSchedule 广告投放信息
     * @return 结果
     */
	@Override
	public int updateAdSchedule(AdSchedule adSchedule)
	{
	    return adScheduleMapper.updateAdSchedule(adSchedule);
	}

	/**
     * 删除广告投放对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAdScheduleByIds(String ids)
	{
		return adScheduleMapper.deleteAdScheduleByIds(Convert.toStrArray(ids));
	}


	@Override
	public int saveAdTemplates(AdSchedule adSchedule) throws Exception{
		
		logger.info("adSchedule:"+adSchedule.toString());
		try {
			String scheduleName = adSchedule.getScheduleName();
			String templateId = adSchedule.getThemeTemplateId();
			Integer advertiser = adSchedule.getAdvertiseId();
			String totalTime = adSchedule.getTotalTime();
			
			String result = savePlaybill(scheduleName, templateId, advertiser.toString(), totalTime);
			//返回结果封装
			AdHttpResult adHttp = Tools.analysisResult(result);
			if(AdConstant.RESPONSE_CODE_SUCCESS.equals(adHttp.getCode())){
				JSONObject jsonResult =  (JSONObject) adHttp.get("data");
				String pId = jsonResult.getString("pid");
				String tId = jsonResult.getString("tid");

				adSchedule.setpId(pId);
				adSchedule.settId(tId);
			}else{
				logger.error("调用新增推广计划接口失败!" + adHttp.toString());
				throw new RRException("调用新增推广计划接口失败!");
			}
			
		} catch (Exception e) {
			logger.error("saveAdTemplates error:" + e);
			throw e;
		}
		
		//2.保存Advertise表数据
		return adScheduleMapper.insertAdSchedule(adSchedule);
	}

	@Override
	@Transactional
	public int materialUpload(List<MultipartFile> files,String adScheduleId,
			String elementId,String operatorUser) throws Exception{

		int saveNum = 0;
		try {
			
			AdSchedule adSchedule = adScheduleMapper.selectAdScheduleById(Integer.parseInt(adScheduleId));
			String tId = adSchedule.gettId();
			//TODO 这里的eid是模板元素的ID，而不是模板ID
			//String elementId = adSchedule.getElementId();
			logger.info("elementId:"+elementId);
			
			//查询当前最大批次
			int maxBatch = adMaterialMapper.selectMaxBatch(Integer.parseInt(adScheduleId));
			++maxBatch;
			//1.上传素材文件
			for (int i = 0; i < files.size(); ++i) {
				//保存文件动作
				MultipartFile multipartFile = files.get(i);
			    if (!multipartFile.isEmpty()) {
			    	//调用上传文件的接口
			    	String result = addElement(tId, elementId, multipartFile);
			    	//返回结果封装
					AdHttpResult adHttp = Tools.analysisResult(result);
					if(AdConstant.RESPONSE_CODE_SUCCESS.equals(adHttp.getCode())){
						JSONObject jsonResult =  (JSONObject) adHttp.get("data");
						String preview = jsonResult.getString("preview");
						String teid = jsonResult.getString("teid");
						String tresid = jsonResult.getString("tresid");
						logger.info("teid:"+teid+",tresid:"+tresid+",\tpreview:"+preview);
						//业务处理 保存素材文件
						saveNum += insertMaterial(adSchedule.getAdScheduleId(),teid,
								tresid,preview,maxBatch,(i+1),operatorUser);
						
					}else{
						logger.error("调用上传素材信息接口失败!" + adHttp.toString());
						throw new RRException("调用上传素材信息接口失败!");
					} 
			    } else {
			        logger.error("上传素材文件不能为空!");
			        throw new RRException("上传素材文件不能为空!");
			    }
			}
			logger.info("成功上传: "+ saveNum + " 份文件");
			return saveNum;
			
		} catch (Exception e) {
			logger.error("materialUpload error: " + e);
			throw e;
		}
	}
	
	/**
	 * 保存预览素材URL,在最大批次之上增加1,即保存第N+1批次上传记录
	 * @param adScheduleId	投放广告ID
	 * @param teid	
	 * @param tresid	
	 * @param preview	素材预览URL
	 * @param batch	上传批次号
	 * @param sequence	上传顺序
	 * @param operator	操作者
	 * @return
	 */
	private int insertMaterial(Integer adScheduleId, String teid, 
			String tresid, String preview,int batch,int sequence,String operator) {
		AdMaterial adMaterial = new AdMaterial();
		adMaterial.setAdScheduleId(adScheduleId);
		adMaterial.setPreview(preview);
		adMaterial.settEid(teid);
		adMaterial.setTreSid(tresid);
		adMaterial.setBatch(batch);
		adMaterial.setSequence(sequence);
		adMaterial.setCreateBy(operator);
		adMaterial.setCreateTime(new Date());
		return adMaterialMapper.insertAdMaterial(adMaterial);
	}

	@Override
	@Transactional
	public int orderSave(AdSchedule adSchedule,String operatorUser) throws Exception{
		
		try {
			Date createDate = new Date();
			//1.插入广告投放范围表	 一对一
			AdReleaseRange adReleaseRange = new AdReleaseRange();
			adReleaseRange.setAdScheduleId(adSchedule.getAdScheduleId());
			String releaseType = adSchedule.getReleaseType();
			adReleaseRange.setReleaseType(releaseType);
			adReleaseRange.setCreateBy(operatorUser);
			adReleaseRange.setCreateTime(createDate);
			//根据投放方式判断保存哪些字段:投放类型:0全部；1按地区；2按场所
			if("0".equals(releaseType)){
				
			}else if("1".equals(releaseType)){
				adReleaseRange.setProvince(adSchedule.getProvince());
				adReleaseRange.setCity(adSchedule.getCity());
				adReleaseRange.setCounty(adSchedule.getCounty());
			}else if("2".equals(releaseType)){
				adReleaseRange.setPlaceGrade(adSchedule.getPlaceGrade());
			}
			String deviceIds = adSchedule.getDeviceIds();
			adReleaseRange.setDevices(deviceIds);
			adReleaseRangeMapper.insertAdReleaseRange(adReleaseRange);
			
			//2.插入广告与时间范围关系表 	一对多
			List<String> timeSlots = new ArrayList<>();
			Date deadLineDate = null;
			String timeSlotArr = adSchedule.getTimeSlotArr();
			
			JSONArray timeSlotJsonArray = new JSONArray(timeSlotArr);
			for(int i=0 ; i < timeSlotJsonArray.length() ;i++)
			{
				org.json.JSONObject time = timeSlotJsonArray.getJSONObject(i);
				String beginTime = time.getString("beginTime");
				String lastTime = time.getString("endTime");
				
				AdReleaseTimer adReleaseTimer = new AdReleaseTimer();
				adReleaseTimer.setAdScheduleId(adSchedule.getAdScheduleId());
				adReleaseTimer.setReleaseBeginTime(yyyyMMddHHmmSFormat.parse(beginTime));
				adReleaseTimer.setReleaseEndTime(yyyyMMddHHmmSFormat.parse(lastTime));
				adReleaseTimer.setCreateBy(operatorUser);
				adReleaseTimer.setCreateTime(createDate);
				adReleaseTimerMapper.insertAdReleaseTimer(adReleaseTimer);
				
				//处理时间
				String startDate = beginTime.substring(0, beginTime.lastIndexOf(" "));
				String startTime = beginTime.substring(beginTime.lastIndexOf(" ") + 1);

				String endDate = lastTime.substring(0, lastTime.lastIndexOf(" "));
				String endTime = lastTime.substring(lastTime.lastIndexOf(" ") + 1);
				
				HashMap<String, Object> timeSlot = new HashMap<>();
				timeSlot.put("startDate", startDate);
				timeSlot.put("endDate", endDate);
				timeSlot.put("startTime", startTime);
				timeSlot.put("endTime", endTime);
				//Map转换成JSON
				String jsonTimeSlot = JSON.toJSONString(timeSlot); 
				timeSlots.add(jsonTimeSlot);
				
				if(deadLineDate == null){
					deadLineDate = yyyyMMddSFormat.parse(endDate);
				}
				//取最后日期
				deadLineDate = compareDate(deadLineDate, yyyyMMddSFormat.parse(endDate));
			}
			
			//这边的参数tid是设备的ID，而不是广告表的tid，需要修改!
			String pIds = adSchedule.getpId();
			String result = addSchedule(pIds, deviceIds, timeSlots.toString(), yyyyMMddSFormat.format(deadLineDate));
			//返回结果封装
			AdHttpResult adHttp = Tools.analysisResult(result);
			//保存排期ID
			if(AdConstant.RESPONSE_CODE_SUCCESS.equals(adHttp.getCode())){
				JSONObject data = (JSONObject) adHttp.get("data");
				String scheduleId = (String) data.get("scheduleId");
				adSchedule.setSxScheduleId(scheduleId);

			}else{
				logger.error("调用排期接口失败!" + adHttp.toString());
				throw new RRException("调用排期接口失败!");
			}
			
			//预约设备后广告状态变为待审核
			adSchedule.setStatus(AdConstant.AD_WAIT_ADUIT);
			
			return updateAdSchedule(adSchedule);
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	@Transactional
	public int auditSave(AdSchedule adSchedule,String operatorUser) throws Exception{
		try {
			//审核通过下发排期计划
			if(AdConstant.AD_ADUIT_PASS.equals(adSchedule.getApproved())){
				adSchedule.setStatus(AdConstant.AD_WAIT_PUBLISH);
				String result = publishSchedule(adSchedule.getSxScheduleId());
				//返回结果封装
				AdHttpResult adHttp = Tools.analysisResult(result);
				if(AdConstant.RESPONSE_CODE_SUCCESS.equals(adHttp.getCode())){
					JSONObject data = (JSONObject) adHttp.get("data");
					List<JSONObject> adUrls = (List<JSONObject>) data.get("adUrls");
					for (JSONObject jsonObject : adUrls) {
						String adUrl = jsonObject.getString("adUrl");
						String terminalId = jsonObject.getString("terminalId");
						//保存广告URL链接
						
						int updateNum = deviceMapper.updateAdUrlByTid(adUrl,Convert.toStrArray(terminalId));
						logger.info("成功更新:"+updateNum+" 条设备adUrl数据");
					}
				} else{
					logger.error("调用审核通过下发排期计划接口失败!" + adHttp.toString());
					throw new RRException("调用排期接口失败!");
				}
				
			}else if(AdConstant.AD_ADUIT_NO_PASS.equals(adSchedule.getApproved())){
				//审核不通过则不下发排期计划
				adSchedule.setStatus(AdConstant.AD_ADUIT_FAIL);
			}else{
				return 0;//必须有审核结果,否则视为异常
			}
			
			adSchedule.setApprovedUser(operatorUser);
			
			return updateAdSchedule(adSchedule);
		} catch (Exception e) {
			logger.error("auditSave error:" + e);
			throw e;
		}
	}
	

	@Override
	public int releaseOnlineSave(AdSchedule adSchedule) {
		//若广告的status已经为04则已经发布过不再更新，若没有发布则进行发布操作
		AdSchedule ad  = adScheduleMapper.selectAdScheduleById(adSchedule.getAdScheduleId());
		if(AdConstant.AD_WAIT_PLAY.equals(ad.getStatus())){
			return 0;
		}else{
			adSchedule.setStatus(AdConstant.AD_WAIT_PLAY);
			return adScheduleMapper.updateAdSchedule(adSchedule);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ThemeTemplate> getThemeList() {
		
		List<ThemeTemplate> ThemeTemplateList = new ArrayList<ThemeTemplate>();
		try {
			String result = getThemeListAction();
			//返回结果封装
			AdHttpResult adHttp = Tools.analysisResult(result);
			if(AdConstant.RESPONSE_CODE_SUCCESS.equals(adHttp.getCode())){
				List<HashMap<String, Object>> data = (List<HashMap<String, Object>>) adHttp.get("data");
				for (HashMap<String, Object> themebject : data) {
					String themeTemplateId = (String) themebject.get("THEMETEMPLATEID");
					String themeName = (String) themebject.get("THEMENAME");
					
					ThemeTemplate theme = new ThemeTemplate();
					theme.setThemeTemplateId(themeTemplateId);
					theme.setThemeName(themeName);
					ThemeTemplateList.add(theme);
				}
			}else{
				logger.error("调用获取模板信息列表接口失败!" + adHttp.toString());
				throw new RRException("调用获取模板信息列表接口失败!");
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ThemeTemplateList;
	}
	
	@Override
	public List<ElementType> getElementList(String themeTemplateId) throws IOException {
		List<ElementType> elementList = new ArrayList<>();
		try {
			String result = getThemeListAction();
			//返回结果封装
			AdHttpResult adHttp = Tools.analysisResult(result);
			if(AdConstant.RESPONSE_CODE_SUCCESS.equals(adHttp.getCode())){
				
				List<HashMap<String, Object>> data = (List<HashMap<String, Object>>) adHttp.get("data");
				for (HashMap<String, Object> themebject : data) {
					String templateId = (String) themebject.get("THEMETEMPLATEID");
					if(themeTemplateId.equals(templateId)){
						JSONObject details =  (JSONObject) themebject.get("details");
						List<JSONObject> themeElementBeanCollection = (List<JSONObject>) details.get("themeElementBeanCollection");
						for (JSONObject themeElement : themeElementBeanCollection) {
							String id = themeElement.getString("id");
							JSONObject elementType =  (JSONObject) themeElement.get("elementType");
							String elementTypeID = elementType.getString("elementTypeID");
							String elementName = elementType.getString("elementName");
							String tagName = elementType.getString("tagName");
							ElementType element = new ElementType(id, elementTypeID, elementName, tagName);
							logger.info(element.toString());
							elementList.add(element);
						}
						break;
					}else{
						continue;
					}
				}
			}else{
				logger.error("调用获取模板信息列表接口失败!" + adHttp.toString());
				throw new RRException("调用获取模板信息列表接口失败!");
			}
		} catch (IOException e) {
			logger.error("获取模板元素列表失败!" + e);
			throw e;
		} 
		
		return elementList;
	}
	
	/**
	 * 接口1：获取模板信息列表  HTTP 接口
	 * @throws IOException 
	 */
	private String getThemeListAction() throws IOException {
		//获取模板信息列表接口
		Map<String, String> paramsMap = new HashMap<String, String>();
		//请求参数封装
		//paramsMap.put("isLayout", "0");
		String param = Tools.paramsToString(paramsMap);
		
		String result = Tools.doPost(AdConstant.AD_URL_GETTHEMELIST, param);
		return result;
	}
	
	/**
	 * 接口2：调用上传素材信息HTTP Multipart接口
	 * @param tId
	 * @param eId
	 * @param multipartFile
	 * @return
	 */
	public String addElement(String tId,String eId, MultipartFile multipartFile) throws Exception{
		
		PostMethod postMethod = new PostMethod(AdConstant.AD_URL_ADDELEMENT);
    	HttpClient client = new HttpClient();
    	File file = null;
        try {
        	//MultipartFile转file
        	/*CommonsMultipartFile cf= (CommonsMultipartFile) multipartFile; //这个myfile是MultipartFile的
            DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
            File file = fi.getStoreLocation();*/

        	InputStream ins = multipartFile.getInputStream();
        	file=new File(multipartFile.getOriginalFilename());
		    Tools.inputStreamToFile(ins, file);
            
            //FilePart：用来上传文件的类
        	FilePart myUpload = new FilePart("myUpload", file);
        	//StringPart:普通文本参数
        	StringPart tid = new StringPart("tid", tId);
        	StringPart eid = new StringPart("eid", eId);
        	Part[] parts = {(Part) tid, (Part) eid,(Part) myUpload};
        	
        	MultipartRequestEntity mre = new MultipartRequestEntity((org.apache.commons.httpclient.methods.multipart.Part[]) 
        			parts, postMethod.getParams());
        	postMethod.setRequestEntity(mre);
        	
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
        	if(file.exists()){
        		file.delete();
        	}
            //释放连接
            postMethod.releaseConnection();
        }
        
		return null;
	}
	
	/**
	 *  接口3：新增推广计划  HTTP application/x-www-form-urlencoded接口
	 * @param scheduleName	推广计划名称
	 * @param templateId	模板ID
	 * @param advertiser	广告主体名称
	 * @param totalTime		总共播放时长
	 * @return
	 * @throws IOException
	 */
	public String savePlaybill(String scheduleName,String templateId,String advertiser,String totalTime) throws IOException{
		//1.调用接口参数
		Map<String, String> paramsMap = new HashMap<String, String>();
		//请求参数封装
		paramsMap.put("scheduleName", scheduleName);
		paramsMap.put("templateId", templateId);
		paramsMap.put("advertiser", advertiser);
		paramsMap.put("totalTime", totalTime);
		
		String param = Tools.paramsToString(paramsMap);
		String result = Tools.doPostForm(AdConstant.AD_URL_SAVEPLAYBILL, param);
		
		return result;
	}
	
	/**
	 * 接口4：新增广告终端  HTTP application/x-www-form-urlencoded接口
	 * @param terminalId 	平台终端ID
	 * @param terminalName	终端设备名称
	 * @param termCode	终端编号
	 * @param province	省份
	 * @param city	城市
	 * @return
	 * @throws IOException
	 */
	public String saveTerminal(String terminalId,String terminalName,
			String termCode,String province,String city) throws IOException{
		//请求参数封装
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("deviceId", terminalId);
		paramsMap.put("deviceName", terminalName);
		paramsMap.put("devCode", termCode);
		paramsMap.put("province", province);
		paramsMap.put("city", city);
		
		String param = Tools.paramsToString(paramsMap);
		String result = Tools.doPostForm(AdConstant.AD_URL_SAVETERMINAL, param);
		
		return result;
	}
	
	/**
	 * 接口5：删除广告终端  HTTP application/x-www-form-urlencoded接口
	 * @param terminalId 	平台终端ID
	 * @return
	 * @throws IOException
	 */
	public String deleteTerminal(String terminalId) throws IOException{
		//请求参数封装
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("terminalId", terminalId);
		
		String param = Tools.paramsToString(paramsMap);
		String result = Tools.doPostForm(AdConstant.AD_URL_DELETETERMINAL, param);
		
		return result;
	}
	
	/**
	 * 接口6：生成排期计划  HTTP application/x-www-form-urlencoded接口
	 * @param pids		播放清单id
	 * @param tids 		播放终端ID
	 * @param timeSlots	播放时间范围
	 * @param deadLine	播放最后时间
	 * @return
	 * @throws IOException
	 */
	public String addSchedule(String pids,String tids,String timeSlots,String deadLine) throws IOException{
		//请求参数封装
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("pids", pids);
		paramsMap.put("tids", tids);
		paramsMap.put("timeSlots", timeSlots);
		paramsMap.put("deadLine", deadLine);
		
		String param = Tools.paramsToString(paramsMap);
		String result = Tools.doPostForm(AdConstant.AD_URL_ADDSCHEDULE, param);
		
		return result;
	}
	
	/**
	 * 接口7：调用下发排期计划  HTTP application/x-www-form-urlencoded接口
	 * @param adScheduleId 排期计划ID
	 * @throws IOException 
	 */
	private String publishSchedule(String adScheduleId) throws IOException {
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("scheduleId", adScheduleId);
		String param = Tools.paramsToString(paramsMap);
		
		String result = Tools.doPostForm(AdConstant.AD_URL_PUBLISHSCHEDULE, param);
		return result;
	}
	
	/**
	 * 日期比较,返回日期大的Date
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Date compareDate(Date date1, Date date2) {
        if (date1.getTime() >= date2.getTime())
            return date1;       
        else
            return date2;
    }

}
