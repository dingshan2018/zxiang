package com.zxiang.project.record.scheduleOrder.service;

import java.util.List;

import com.zxiang.project.record.scheduleOrder.domain.ScheduleOrder;

/**
 * 排期订单 服务层
 * 
 * @author ZXiang
 * @date 2018-11-15
 */
public interface IScheduleOrderService 
{
	/**
     * 查询排期订单信息
     * 
     * @param scheduleOrderId 排期订单ID
     * @return 排期订单信息
     */
	public ScheduleOrder selectScheduleOrderById(Integer scheduleOrderId);
	
	/**
     * 查询排期订单列表
     * 
     * @param scheduleOrder 排期订单信息
     * @return 排期订单集合
     */
	public List<ScheduleOrder> selectScheduleOrderList(ScheduleOrder scheduleOrder);
	
	/**
     * 新增排期订单
     * 
     * @param scheduleOrder 排期订单信息
     * @return 结果
     */
	public int insertScheduleOrder(ScheduleOrder scheduleOrder);
	
	/**
     * 修改排期订单
     * 
     * @param scheduleOrder 排期订单信息
     * @return 结果
     */
	public int updateScheduleOrder(ScheduleOrder scheduleOrder);
		
	/**
     * 删除排期订单信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteScheduleOrderByIds(String ids);
	
}
