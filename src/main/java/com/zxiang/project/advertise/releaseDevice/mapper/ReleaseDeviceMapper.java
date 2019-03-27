package com.zxiang.project.advertise.releaseDevice.mapper;

import java.util.List;

import com.zxiang.project.advertise.releaseDevice.domain.ReleaseDevice;	

/**
 * 投放终端配置 数据层
 * 
 * @author ZXiang
 * @date 2018-11-22
 */
public interface ReleaseDeviceMapper 
{
	/**
     * 查询投放终端配置信息
     * 
     * @param releaseDeviceId 投放终端配置ID
     * @return 投放终端配置信息
     */
	public ReleaseDevice selectReleaseDeviceById(Integer releaseDeviceId);
	
	/**
     * 查询投放终端配置列表
     * 
     * @param releaseDevice 投放终端配置信息
     * @return 投放终端配置集合
     */
	public List<ReleaseDevice> selectReleaseDeviceList(ReleaseDevice releaseDevice);
	
	/**
     * 新增投放终端配置
     * 
     * @param releaseDevice 投放终端配置信息
     * @return 结果
     */
	public int insertReleaseDevice(ReleaseDevice releaseDevice);
	
	/**
     * 修改投放终端配置
     * 
     * @param releaseDevice 投放终端配置信息
     * @return 结果
     */
	public int updateReleaseDevice(ReleaseDevice releaseDevice);
	
	/**
     * 删除投放终端配置
     * 
     * @param releaseDeviceId 投放终端配置ID
     * @return 结果
     */
	public int deleteReleaseDeviceById(Integer releaseDeviceId);
	
	/**
     * 批量删除投放终端配置
     * 
     * @param releaseDeviceIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteReleaseDeviceByIds(String[] releaseDeviceIds);

	/**
	 * 批量插入ReleaseDevice表数据
	 * @param releaseDeviceList
	 * @return
	 */
	public int batchInsert(List<ReleaseDevice> releaseDeviceList);
	
	/**
	 * 删除广告投放数据表
	 * @param scheduleId
	 * @return
	 */
	public int deleteReleaseDeviceByScheduleId(Integer scheduleId);
	
}