package com.zxiang.project.advertise.adSchedule.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zxiang.project.advertise.adSchedule.mapper.AdScheduleMapper;
import com.zxiang.project.advertise.adSchedule.domain.AdSchedule;
import com.zxiang.project.advertise.adSchedule.service.IAdScheduleService;
import com.zxiang.project.advertise.constant.AdConstant;
import com.zxiang.common.support.Convert;

/**
 * 广告投放 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-24
 */
@Service
public class AdScheduleServiceImpl implements IAdScheduleService 
{
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
		
		//预约设备后广告状态变为待审核
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
				JSONObject time = timeSlotJsonArray.getJSONObject(i);
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
	public int auditSave(AdSchedule adSchedule,String operatorUser) {
		if(AdConstant.AD_ADUIT_PASS.equals(adSchedule.getApproved())){
			adSchedule.setStatus(AdConstant.AD_WAIT_PUBLISH);
		}else if(AdConstant.AD_ADUIT_NO_PASS.equals(adSchedule.getApproved())){
			adSchedule.setStatus(AdConstant.AD_ADUIT_FAIL);
		}else{
			return 0;//必须有审核结果,否则视为异常
		}
		
		adSchedule.setApprovedUser(operatorUser);
		
		return updateAdSchedule(adSchedule);
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

}
