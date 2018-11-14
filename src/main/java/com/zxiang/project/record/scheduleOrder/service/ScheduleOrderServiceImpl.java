package com.zxiang.project.record.scheduleOrder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxiang.common.support.Convert;
import com.zxiang.project.record.scheduleOrder.domain.ScheduleOrder;
import com.zxiang.project.record.scheduleOrder.mapper.ScheduleOrderMapper;

/**
 * 排期订单 服务层实现
 * 
 * @author ZXiang
 * @date 2018-11-15
 */
@Service
public class ScheduleOrderServiceImpl implements IScheduleOrderService 
{
	@Autowired
	private ScheduleOrderMapper scheduleOrderMapper;

	/**
     * 查询排期订单信息
     * 
     * @param scheduleOrderId 排期订单ID
     * @return 排期订单信息
     */
    @Override
	public ScheduleOrder selectScheduleOrderById(Integer scheduleOrderId)
	{
	    return scheduleOrderMapper.selectScheduleOrderById(scheduleOrderId);
	}
	
	/**
     * 查询排期订单列表
     * 
     * @param scheduleOrder 排期订单信息
     * @return 排期订单集合
     */
	@Override
	public List<ScheduleOrder> selectScheduleOrderList(ScheduleOrder scheduleOrder)
	{
	    return scheduleOrderMapper.selectScheduleOrderList(scheduleOrder);
	}
	
    /**
     * 新增排期订单
     * 
     * @param scheduleOrder 排期订单信息
     * @return 结果
     */
	@Override
	public int insertScheduleOrder(ScheduleOrder scheduleOrder)
	{
	    return scheduleOrderMapper.insertScheduleOrder(scheduleOrder);
	}
	
	/**
     * 修改排期订单
     * 
     * @param scheduleOrder 排期订单信息
     * @return 结果
     */
	@Override
	public int updateScheduleOrder(ScheduleOrder scheduleOrder)
	{
	    return scheduleOrderMapper.updateScheduleOrder(scheduleOrder);
	}

	/**
     * 删除排期订单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteScheduleOrderByIds(String ids)
	{
		return scheduleOrderMapper.deleteScheduleOrderByIds(Convert.toStrArray(ids));
	}
	
}
