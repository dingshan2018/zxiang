package com.zxiang.project.advertise.adSchedule.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zxiang.project.advertise.adSchedule.mapper.AdScheduleMapper;
import com.zxiang.project.advertise.adSchedule.domain.AdSchedule;
import com.zxiang.project.advertise.adSchedule.service.IAdScheduleService;
import com.zxiang.common.support.Convert;

/**
 * 广告推广计划 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Service
public class AdScheduleServiceImpl implements IAdScheduleService 
{
	@Autowired
	private AdScheduleMapper adScheduleMapper;

	/**
     * 查询广告推广计划信息
     * 
     * @param scheduleId 广告推广计划ID
     * @return 广告推广计划信息
     */
    @Override
	public AdSchedule selectAdScheduleById(Integer scheduleId)
	{
	    return adScheduleMapper.selectAdScheduleById(scheduleId);
	}
	
	/**
     * 查询广告推广计划列表
     * 
     * @param adSchedule 广告推广计划信息
     * @return 广告推广计划集合
     */
	@Override
	public List<AdSchedule> selectAdScheduleList(AdSchedule adSchedule)
	{
	    return adScheduleMapper.selectAdScheduleList(adSchedule);
	}
	
    /**
     * 新增广告推广计划
     * 
     * @param adSchedule 广告推广计划信息
     * @return 结果
     */
	@Override
	public int insertAdSchedule(AdSchedule adSchedule)
	{
	    return adScheduleMapper.insertAdSchedule(adSchedule);
	}
	
	/**
     * 修改广告推广计划
     * 
     * @param adSchedule 广告推广计划信息
     * @return 结果
     */
	@Override
	public int updateAdSchedule(AdSchedule adSchedule)
	{
	    return adScheduleMapper.updateAdSchedule(adSchedule);
	}

	/**
     * 删除广告推广计划对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAdScheduleByIds(String ids)
	{
		return adScheduleMapper.deleteAdScheduleByIds(Convert.toStrArray(ids));
	}
	
}
