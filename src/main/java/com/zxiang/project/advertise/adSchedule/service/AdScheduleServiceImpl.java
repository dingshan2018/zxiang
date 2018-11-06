package com.zxiang.project.advertise.adSchedule.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.zxiang.common.support.Convert;
import com.zxiang.project.advertise.adSchedule.domain.AdSchedule;
import com.zxiang.project.advertise.adSchedule.domain.ThemeTemplate;
import com.zxiang.project.advertise.adSchedule.mapper.AdScheduleMapper;
import com.zxiang.project.advertise.utils.AdHttpResult;
import com.zxiang.project.advertise.utils.Tools;
import com.zxiang.project.advertise.utils.constant.AdConstant;

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
			
	@Autowired
	private AdScheduleMapper adScheduleMapper;

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
	@Transactional
	public int orderSave(AdSchedule adSchedule) {
		
		// TODO 预约设备后广告状态变为待审核
		adSchedule.setStatus(AdConstant.AD_WAIT_ADUIT);
		
		//插入广告与设备关系表 一对多
		Long[] deviceIds = adSchedule.getDeviceIds();
		for (int i = 0; i < deviceIds.length; i++) {
			System.out.println("deviceIds:"+deviceIds[i]);
		}
		
		//插入广告与时间范围关系表 一对多
		String timeSlotArr = adSchedule.getTimeSlotArr();

		try {
			JSONArray timeSlotJsonArray = new JSONArray(timeSlotArr);
			for(int i=0 ; i < timeSlotJsonArray.length() ;i++)
			{
				org.json.JSONObject time = timeSlotJsonArray.getJSONObject(i);
				System.out.println("beginTime="+time.getString("beginTime"));
				System.out.println("endTime="+time.getString("endTime"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		};
		
		//插入广告与投放方式关系表 一对一
		
		
		return updateAdSchedule(adSchedule);
	}
	
	@Override
	@Transactional
	public int auditSave(AdSchedule adSchedule,String operatorUser) {
		try {
			//审核通过下发排期计划
			if(AdConstant.AD_ADUIT_PASS.equals(adSchedule.getApproved())){
				adSchedule.setStatus(AdConstant.AD_WAIT_PUBLISH);
				String result = publishSchedule(adSchedule.getAdScheduleId());
				//返回结果封装
				AdHttpResult adHttp = Tools.analysisResult(result);
				if(AdConstant.RESPONSE_CODE_SUCCESS.equals(adHttp.getCode())){
					JSONObject data = (JSONObject) adHttp.get("data");
					List<JSONObject> adUrls = (List<JSONObject>) data.get("adUrls");
					for (JSONObject jsonObject : adUrls) {
						String addUrl = jsonObject.getString("adUrl");
						String terminalId = jsonObject.getString("terminalId");
						//TODO 保存广告URL链接
						logger.info("addUrl:"+addUrl+",terminalId:"+terminalId);
						
					}
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
			e.printStackTrace();
			logger.error("auditSave error:"+e.getMessage());
			return 0;
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
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ThemeTemplateList;
	}

	@Override
	public int saveAdTemplates(AdSchedule adSchedule) {
		
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
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("saveAdTemplates error:"+e.getMessage());
		}
		
		//2.保存Advertise表数据
		return adScheduleMapper.insertAdSchedule(adSchedule);
	}

	@Override
	@Transactional
	public int materialUpload(List<MultipartFile> files, String adScheduleId) {

		try {
			int saveNum = 0;
			
			AdSchedule adSchedule = adScheduleMapper.selectAdScheduleById(Integer.parseInt(adScheduleId));
			String tId = adSchedule.gettId();
			String eId = adSchedule.getThemeTemplateId();
			
			for (int i = 0; i < files.size(); ++i) {
				MultipartFile file = files.get(i);
			    //TODO 保存文件动作
			    if (!file.isEmpty()) {
			    	String result = addElement(tId, eId);
			    	
			    	saveNum++; 
			    } else {
			        logger.error("文件不能为空!");
			    }
			}
			
			return saveNum;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("materialUpload error: "+e.getMessage());
			return 0;
		}
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
	 * @return
	 * @throws IOException 
	 */
	public String addElement(String tid,String eid) throws IOException{
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("tid", tid);
		paramsMap.put("eid", eid);
		
		String param = Tools.paramsToString(paramsMap);
		String result = Tools.doPostMultipart(AdConstant.AD_URL_ADDELEMENT, param);
		
		return result;
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
		paramsMap.put("terminalId", terminalId);
		paramsMap.put("terminalName", terminalName);
		paramsMap.put("termCode", termCode);
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
	private String publishSchedule(Integer adScheduleId) throws IOException {
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("scheduleId", adScheduleId.toString());
		String param = Tools.paramsToString(paramsMap);
		
		String result = Tools.doPostForm(AdConstant.AD_URL_PUBLISHSCHEDULE, param);
		return result;
	}
	

}
