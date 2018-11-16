package com.zxiang.project.record.deviceOrder.mapper;

import com.zxiang.project.record.deviceOrder.domain.DeviceOrder;
import java.util.List;	

/**
 * 设备销售订单 数据层
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public interface DeviceOrderMapper 
{
	/**
     * 查询设备销售订单信息
     * 
     * @param deviceOrderId 设备销售订单ID
     * @return 设备销售订单信息
     */
	public DeviceOrder selectDeviceOrderById(Integer deviceOrderId);
	
	/**
     * 查询设备销售订单列表
     * 
     * @param deviceOrder 设备销售订单信息
     * @return 设备销售订单集合
     */
	public List<DeviceOrder> selectDeviceOrderList(DeviceOrder deviceOrder);
	
	/**
     * 新增设备销售订单
     * 
     * @param deviceOrder 设备销售订单信息
     * @return 结果
     */
	public int insertDeviceOrder(DeviceOrder deviceOrder);
	
	/**
     * 修改设备销售订单
     * 
     * @param deviceOrder 设备销售订单信息
     * @return 结果
     */
	public int updateDeviceOrder(DeviceOrder deviceOrder);
	
	/**
     * 删除设备销售订单
     * 
     * @param deviceOrderId 设备销售订单ID
     * @return 结果
     */
	public int deleteDeviceOrderById(Integer deviceOrderId);
	
	/**
     * 批量删除设备销售订单
     * 
     * @param deviceOrderIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteDeviceOrderByIds(String[] deviceOrderIds);

	/**
	 * 通过订单号ID判断出了多少台设备
	 * @param tradeOrderId
	 * @return
	 */
	public int selectByTradeId(Integer tradeOrderId);

	/**
	 * 批量插入销售订单表
	 * @param deviceOrderList
	 */
	public void batchInsert(List<DeviceOrder> deviceOrderList);
	
}