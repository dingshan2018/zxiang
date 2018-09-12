package com.zxiang.project.advertise.adSchedulePosition.mapper;

import com.zxiang.project.advertise.adSchedulePosition.domain.AdSchedulePosition;
import java.util.List;	

/**
 * 投放位置 数据层
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public interface AdSchedulePositionMapper 
{
	/**
     * 查询投放位置信息
     * 
     * @param schedulePositionId 投放位置ID
     * @return 投放位置信息
     */
	public AdSchedulePosition selectAdSchedulePositionById(Integer schedulePositionId);
	
	/**
     * 查询投放位置列表
     * 
     * @param adSchedulePosition 投放位置信息
     * @return 投放位置集合
     */
	public List<AdSchedulePosition> selectAdSchedulePositionList(AdSchedulePosition adSchedulePosition);
	
	/**
     * 新增投放位置
     * 
     * @param adSchedulePosition 投放位置信息
     * @return 结果
     */
	public int insertAdSchedulePosition(AdSchedulePosition adSchedulePosition);
	
	/**
     * 修改投放位置
     * 
     * @param adSchedulePosition 投放位置信息
     * @return 结果
     */
	public int updateAdSchedulePosition(AdSchedulePosition adSchedulePosition);
	
	/**
     * 删除投放位置
     * 
     * @param schedulePositionId 投放位置ID
     * @return 结果
     */
	public int deleteAdSchedulePositionById(Integer schedulePositionId);
	
	/**
     * 批量删除投放位置
     * 
     * @param schedulePositionIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteAdSchedulePositionByIds(String[] schedulePositionIds);
	
}