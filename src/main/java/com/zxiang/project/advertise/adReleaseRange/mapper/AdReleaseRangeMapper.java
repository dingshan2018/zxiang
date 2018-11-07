package com.zxiang.project.advertise.adReleaseRange.mapper;

import java.util.List;

import com.zxiang.project.advertise.adReleaseRange.domain.AdReleaseRange;	

/**
 * 广告投放范围 数据层
 * 
 * @author ZXiang
 * @date 2018-11-07
 */
public interface AdReleaseRangeMapper 
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
     * 删除广告投放范围
     * 
     * @param adReleaseRangeId 广告投放范围ID
     * @return 结果
     */
	public int deleteAdReleaseRangeById(Integer adReleaseRangeId);
	
	/**
     * 批量删除广告投放范围
     * 
     * @param adReleaseRangeIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteAdReleaseRangeByIds(String[] adReleaseRangeIds);
	
}