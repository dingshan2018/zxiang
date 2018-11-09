package com.zxiang.project.advertise.adReleaseRange.service;

import java.util.List;

import com.zxiang.project.advertise.adReleaseRange.domain.AdReleaseRange;

/**
 * 广告投放范围 服务层
 * 
 * @author ZXiang
 * @date 2018-11-07
 */
public interface IAdReleaseRangeService 
{
	/**
     * 查询广告投放范围信息
     * 
     * @param adReleaseRangeId 广告投放范围ID
     * @return 广告投放范围信息
     */
	public AdReleaseRange selectAdReleaseRangeById(Integer adReleaseRangeId);
	
	/**
     * 查询广告投放范围列表
     * 
     * @param adReleaseRange 广告投放范围信息
     * @return 广告投放范围集合
     */
	public List<AdReleaseRange> selectAdReleaseRangeList(AdReleaseRange adReleaseRange);
	
	/**
     * 新增广告投放范围
     * 
     * @param adReleaseRange 广告投放范围信息
     * @return 结果
     */
	public int insertAdReleaseRange(AdReleaseRange adReleaseRange);
	
	/**
     * 修改广告投放范围
     * 
     * @param adReleaseRange 广告投放范围信息
     * @return 结果
     */
	public int updateAdReleaseRange(AdReleaseRange adReleaseRange);
		
	/**
     * 删除广告投放范围信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAdReleaseRangeByIds(String ids);

	/**
	 * 查询是否有投放设备,有则预约过
	 * @param adScheduleId
	 * @return
	 */
	public int getOrderNum(Integer adScheduleId);
	
}
