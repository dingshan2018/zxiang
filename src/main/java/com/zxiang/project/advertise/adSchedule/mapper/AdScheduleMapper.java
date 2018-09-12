package com.zxiang.project.advertise.adSchedule.mapper;

import com.zxiang.project.advertise.adSchedule.domain.AdSchedule;
import java.util.List;	

/**
 * 广告推广计划 数据层
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public interface AdScheduleMapper 
{
	/**
     * 查询广告推广计划信息
     * 
     * @param scheduleId 广告推广计划ID
     * @return 广告推广计划信息
     */
	public AdSchedule selectAdScheduleById(Integer scheduleId);
	
	/**
     * 查询广告推广计划列表
     * 
     * @param adSchedule 广告推广计划信息
     * @return 广告推广计划集合
     */
	public List<AdSchedule> selectAdScheduleList(AdSchedule adSchedule);
	
	/**
     * 新增广告推广计划
     * 
     * @param adSchedule 广告推广计划信息
     * @return 结果
     */
	public int insertAdSchedule(AdSchedule adSchedule);
	
	/**
     * 修改广告推广计划
     * 
     * @param adSchedule 广告推广计划信息
     * @return 结果
     */
	public int updateAdSchedule(AdSchedule adSchedule);
	
	/**
     * 删除广告推广计划
     * 
     * @param scheduleId 广告推广计划ID
     * @return 结果
     */
	public int deleteAdScheduleById(Integer scheduleId);
	
	/**
     * 批量删除广告推广计划
     * 
     * @param scheduleIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteAdScheduleByIds(String[] scheduleIds);
	
}