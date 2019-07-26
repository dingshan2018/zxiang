package com.zxiang.project.advertise.adReleaseTimer.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxiang.common.support.Convert;
import com.zxiang.common.utils.security.ShiroUtils;
import com.zxiang.project.advertise.adReleaseTimer.domain.AdReleaseTimer;
import com.zxiang.project.advertise.adReleaseTimer.mapper.AdReleaseTimerMapper;
import com.zxiang.project.advertise.adSchedule.domain.AdSchedule;
import com.zxiang.project.advertise.adSchedule.mapper.AdScheduleMapper;
import com.zxiang.project.advertise.utils.constant.AdConstant;

/**
 * 广告投放时段 服务层实现
 * 
 * @author ZXiang
 * @date 2018-11-07
 */
@Service
public class AdReleaseTimerServiceImpl implements IAdReleaseTimerService 
{
	@Autowired
	private AdReleaseTimerMapper adReleaseTimerMapper;
	@Autowired
	private AdScheduleMapper adScheduleMapper;
	/**
     * 查询广告投放时段信息
     * 
     * @param adReleaseTimerId 广告投放时段ID
     * @return 广告投放时段信息
     */
    @Override
	public AdReleaseTimer selectAdReleaseTimerById(Integer adReleaseTimerId)
	{
	    return adReleaseTimerMapper.selectAdReleaseTimerById(adReleaseTimerId);
	}
	
	/**
     * 查询广告投放时段列表
     * 
     * @param adReleaseTimer 广告投放时段信息
     * @return 广告投放时段集合
     */
	@Override
	public List<AdReleaseTimer> selectAdReleaseTimerList(AdReleaseTimer adReleaseTimer)
	{
	    return adReleaseTimerMapper.selectAdReleaseTimerList(adReleaseTimer);
	}
	
    /**
     * 新增广告投放时段
     * 
     * @param adReleaseTimer 广告投放时段信息
     * @return 结果
     */
	@Override
	public int insertAdReleaseTimer(AdReleaseTimer adReleaseTimer)
	{
		AdSchedule schedule = adScheduleMapper.selectAdScheduleById(adReleaseTimer.getAdScheduleId());
		if("01".equals(schedule.getReleasePosition())) {
			schedule.setPayStatus(AdConstant.AD_WAIT_PAY);
			schedule.setReleaseStatus(AdConstant.AD_REPUBLISH);
			schedule.setUpdateBy(ShiroUtils.getLoginName());
			schedule.setUpdateTime(new Date());
			adScheduleMapper.updateAdSchedule(schedule);
		}
	    return adReleaseTimerMapper.insertAdReleaseTimer(adReleaseTimer);
	}
	
	/**
     * 修改广告投放时段
     * 
     * @param adReleaseTimer 广告投放时段信息
     * @return 结果
     */
	@Override
	public int updateAdReleaseTimer(AdReleaseTimer adReleaseTimer)
	{
		AdSchedule schedule = adScheduleMapper.selectAdScheduleById(adReleaseTimer.getAdScheduleId());
		if("01".equals(schedule.getReleasePosition())) {
			schedule.setPayStatus(AdConstant.AD_WAIT_PAY);
			schedule.setReleaseStatus(AdConstant.AD_WAIT_REPUBLISH);
			schedule.setUpdateBy(ShiroUtils.getLoginName());
			schedule.setUpdateTime(new Date());
			adScheduleMapper.updateAdSchedule(schedule);
		}
	    return adReleaseTimerMapper.updateAdReleaseTimer(adReleaseTimer);
	}

	/**
     * 删除广告投放时段对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAdReleaseTimerByIds(String ids)
	{
		return adReleaseTimerMapper.deleteAdReleaseTimerByIds(Convert.toStrArray(ids));
	}
	
}
