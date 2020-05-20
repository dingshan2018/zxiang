package com.zxiang.project.record.onlinedevice.service;

import java.util.HashMap;
import java.util.List;

import com.zxiang.project.record.onlinedevice.domain.OnlineDevice;

/**
 * 设备在线时长 服务层
 * 
 * @author ZXiang
 * @date 2020-05-14
 */
public interface IOnlineDeviceService 
{
	/**
     * 查询设备在线时长信息
     * 
     * @param deviceOnlineId 设备在线时长ID
     * @return 设备在线时长信息
     */
	public OnlineDevice selectOnlineDeviceById(Integer deviceOnlineId);
	
	/**
     * 查询设备在线时长列表
     * 
     * @param onlineDevice 设备在线时长信息
     * @return 设备在线时长集合
     */
	public List<OnlineDevice> selectOnlineDeviceList(OnlineDevice onlineDevice);
	
	/**
     * 新增设备在线时长
     * 
     * @param onlineDevice 设备在线时长信息
     * @return 结果
     */
	public int insertOnlineDevice(OnlineDevice onlineDevice);
	
	/**
     * 修改设备在线时长
     * 
     * @param onlineDevice 设备在线时长信息
     * @return 结果
     */
	public int updateOnlineDevice(OnlineDevice onlineDevice);
		
	/**
     * 删除设备在线时长信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteOnlineDeviceByIds(String ids);

	public List<HashMap<String, Object>> selectOnlineDeviceData(OnlineDevice onlineDevice);
	
}
