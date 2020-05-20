package com.zxiang.project.record.onlinedevice.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxiang.common.support.Convert;
import com.zxiang.project.record.onlinedevice.domain.OnlineDevice;
import com.zxiang.project.record.onlinedevice.mapper.OnlineDeviceMapper;

/**
 * 设备在线时长 服务层实现
 * 
 * @author ZXiang
 * @date 2020-05-14
 */
@Service
public class OnlineDeviceServiceImpl implements IOnlineDeviceService 
{
	@Autowired
	private OnlineDeviceMapper onlineDeviceMapper;

	/**
     * 查询设备在线时长信息
     * 
     * @param deviceOnlineId 设备在线时长ID
     * @return 设备在线时长信息
     */
    @Override
	public OnlineDevice selectOnlineDeviceById(Integer deviceOnlineId)
	{
	    return onlineDeviceMapper.selectOnlineDeviceById(deviceOnlineId);
	}
	
	/**
     * 查询设备在线时长列表
     * 
     * @param onlineDevice 设备在线时长信息
     * @return 设备在线时长集合
     */
	@Override
	public List<OnlineDevice> selectOnlineDeviceList(OnlineDevice onlineDevice)
	{
	    return onlineDeviceMapper.selectOnlineDeviceList(onlineDevice);
	}
	
    /**
     * 新增设备在线时长
     * 
     * @param onlineDevice 设备在线时长信息
     * @return 结果
     */
	@Override
	public int insertOnlineDevice(OnlineDevice onlineDevice)
	{
	    return onlineDeviceMapper.insertOnlineDevice(onlineDevice);
	}
	
	/**
     * 修改设备在线时长
     * 
     * @param onlineDevice 设备在线时长信息
     * @return 结果
     */
	@Override
	public int updateOnlineDevice(OnlineDevice onlineDevice)
	{
	    return onlineDeviceMapper.updateOnlineDevice(onlineDevice);
	}

	/**
     * 删除设备在线时长对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteOnlineDeviceByIds(String ids)
	{
		return onlineDeviceMapper.deleteOnlineDeviceByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<HashMap<String, Object>> selectOnlineDeviceData(OnlineDevice onlineDevice) {
		return onlineDeviceMapper.selectOnlineDeviceData(onlineDevice);
	}
	
}
