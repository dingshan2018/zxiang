package com.zxiang.project.advertise.adScheduleRelease.service;

import com.zxiang.project.advertise.adScheduleRelease.domain.AdScheduleRelease;
import java.util.List;

/**
 * 投放时间 服务层
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public interface IAdScheduleReleaseService 
{
	/**
     * 查询投放时间信息
     * 
     * @param scheduleReleaseId 投放时间ID
     * @return 投放时间信息
     */
	public AdScheduleRelease selectAdScheduleReleaseById(Integer scheduleReleaseId);
	
	/**
     * 查询投放时间列表
     * 
     * @param adScheduleRelease 投放时间信息
     * @return 投放时间集合
     */
	public List<AdScheduleRelease> selectAdScheduleReleaseList(AdScheduleRelease adScheduleRelease);
	
	/**
     * 新增投放时间
     * 
     * @param adScheduleRelease 投放时间信息
     * @return 结果
     */
	public int insertAdScheduleRelease(AdScheduleRelease adScheduleRelease);
	
	/**
     * 修改投放时间
     * 
     * @param adScheduleRelease 投放时间信息
     * @return 结果
     */
	public int updateAdScheduleRelease(AdScheduleRelease adScheduleRelease);
		
	/**
     * 删除投放时间信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAdScheduleReleaseByIds(String ids);
	
}
