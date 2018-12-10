package com.zxiang.project.advertise.adReleaseTimer.service;

import java.util.List;

import com.zxiang.project.advertise.adReleaseTimer.domain.AdReleaseTimer;

/**
 * 广告投放时段 服务层
 * 
 * @author ZXiang
 * @date 2018-11-07
 */
public interface IAdReleaseTimerService 
{
	/**
     * 查询广告投放时段信息
     * 
     * @param adReleaseTimerId 广告投放时段ID
     * @return 广告投放时段信息
     */
	public AdReleaseTimer selectAdReleaseTimerById(Integer adReleaseTimerId);
	
	/**
     * 查询广告投放时段列表
     * 
     * @param adReleaseTimer 广告投放时段信息
     * @return 广告投放时段集合
     */
	public List<AdReleaseTimer> selectAdReleaseTimerList(AdReleaseTimer adReleaseTimer);
	
	/**
     * 新增广告投放时段
     * 
     * @param adReleaseTimer 广告投放时段信息
     * @return 结果
     */
	public int insertAdReleaseTimer(AdReleaseTimer adReleaseTimer);
	
	/**
     * 修改广告投放时段
     * 
     * @param adReleaseTimer 广告投放时段信息
     * @return 结果
     */
	public int updateAdReleaseTimer(AdReleaseTimer adReleaseTimer);
		
	/**
     * 删除广告投放时段信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAdReleaseTimerByIds(String ids);
	
}
