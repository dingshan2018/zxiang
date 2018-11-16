package com.zxiang.project.record.deviceOrder.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zxiang.project.record.deviceOrder.mapper.DeviceOrderMapper;
import com.zxiang.project.record.deviceOrder.domain.DeviceOrder;
import com.zxiang.project.record.deviceOrder.service.IDeviceOrderService;
import com.zxiang.common.support.Convert;

/**
 * 设备销售订单 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Service
public class DeviceOrderServiceImpl implements IDeviceOrderService 
{
	@Autowired
	private DeviceOrderMapper deviceOrderMapper;

	/**
     * 查询设备销售订单信息
     * 
     * @param deviceOrderId 设备销售订单ID
     * @return 设备销售订单信息
     */
    @Override
	public DeviceOrder selectDeviceOrderById(Integer deviceOrderId)
	{
	    return deviceOrderMapper.selectDeviceOrderById(deviceOrderId);
	}
	
	/**
     * 查询设备销售订单列表
     * 
     * @param deviceOrder 设备销售订单信息
     * @return 设备销售订单集合
     */
	@Override
	public List<DeviceOrder> selectDeviceOrderList(DeviceOrder deviceOrder)
	{
	    return deviceOrderMapper.selectDeviceOrderList(deviceOrder);
	}
	
    /**
     * 新增设备销售订单
     * 
     * @param deviceOrder 设备销售订单信息
     * @return 结果
     */
	@Override
	public int insertDeviceOrder(DeviceOrder deviceOrder)
	{
	    return deviceOrderMapper.insertDeviceOrder(deviceOrder);
	}
	
	/**
     * 修改设备销售订单
     * 
     * @param deviceOrder 设备销售订单信息
     * @return 结果
     */
	@Override
	public int updateDeviceOrder(DeviceOrder deviceOrder)
	{
	    return deviceOrderMapper.updateDeviceOrder(deviceOrder);
	}

	/**
     * 删除设备销售订单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDeviceOrderByIds(String ids)
	{
		return deviceOrderMapper.deleteDeviceOrderByIds(Convert.toStrArray(ids));
	}

	@Override
	public int getSendNum(Integer tradeOrderId) {
		return deviceOrderMapper.selectByTradeId(tradeOrderId);
	}
	
}
