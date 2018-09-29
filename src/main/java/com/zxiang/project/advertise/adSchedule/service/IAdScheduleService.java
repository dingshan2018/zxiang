package com.zxiang.project.advertise.adSchedule.service;

import com.zxiang.project.advertise.adSchedule.domain.AdSchedule;
import java.util.List;

/**
 * 广告投放 服务层
 * 
 * @author ZXiang
 * @date 2018-09-24
 */
public interface IAdScheduleService 
{
	/**
     * 查询广告投放信息
     * 
     * @param adScheduleId 广告投放ID
     * @return 广告投放信息
     */
	public AdSchedule selectAdScheduleById(Integer adScheduleId);
	
	/**
     * 查询广告投放列表
     * 
     * @param adSchedule 广告投放信息
     * @return 广告投放集合
     */
	public List<AdSchedule> selectAdScheduleList(AdSchedule adSchedule);
	
	/**
     * 新增广告投放
     * 
     * @param adSchedule 广告投放信息
     * @return 结果
     */
	public int insertAdSchedule(AdSchedule adSchedule);
	
	/**
     * 修改广告投放
     * 
     * @param adSchedule 广告投放信息
     * @return 结果
     */
	public int updateAdSchedule(AdSchedule adSchedule);
		
	/**
     * 删除广告投放信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAdScheduleByIds(String ids);
	
}
