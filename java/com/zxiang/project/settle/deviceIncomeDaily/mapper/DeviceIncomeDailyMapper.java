package com.zxiang.project.settle.deviceIncomeDaily.mapper;

import com.zxiang.project.settle.deviceIncomeDaily.domain.DeviceIncomeDaily;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;	

/**
 * 设备收入日统计 数据层
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public interface DeviceIncomeDailyMapper 
{
	/**
     * 查询设备收入日统计信息
     * 
     * @param incomeId 设备收入日统计ID
     * @return 设备收入日统计信息
     */
	public DeviceIncomeDaily selectDeviceIncomeDailyById(Integer incomeId);
	
	/**
     * 查询设备收入日统计列表
     * 
     * @param deviceIncomeDaily 设备收入日统计信息
     * @return 设备收入日统计集合
     */
	public List<DeviceIncomeDaily> selectDeviceIncomeDailyList(DeviceIncomeDaily deviceIncomeDaily);
	
	/**
     * 新增设备收入日统计
     * 
     * @param deviceIncomeDaily 设备收入日统计信息
     * @return 结果
     */
	public int insertDeviceIncomeDaily(DeviceIncomeDaily deviceIncomeDaily);
	
	/**
     * 修改设备收入日统计
     * 
     * @param deviceIncomeDaily 设备收入日统计信息
     * @return 结果
     */
	public int updateDeviceIncomeDaily(DeviceIncomeDaily deviceIncomeDaily);
	
	/**
     * 删除设备收入日统计
     * 
     * @param incomeId 设备收入日统计ID
     * @return 结果
     */
	public int deleteDeviceIncomeDailyById(Integer incomeId);
	
	/**
     * 批量删除设备收入日统计
     * 
     * @param incomeIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteDeviceIncomeDailyByIds(String[] incomeIds);
	
	/**
	 * 判断是否有数据
	 * 
	 * */
	public List<DeviceIncomeDaily> selectDeviceIncomeDaily(DeviceIncomeDaily deviceIncomeDaily);
	
	/** 
	 * 共享设备表查询
	 * */
	public List<HashMap<String, Object>> selectzxdevicelist(HashMap<String, Object> map);
	
	/** 
	 * 设备表销售表查询
	 * */
	public List<HashMap<String, Object>> selectzxdeviceorderlist(HashMap<String, Object> map);
	
	/** 
	 * 广告推广计划查询
	 * */
	public List<HashMap<String, Object>> selecadschedulelist(@Param("scheduleId")Integer scheduleId);
	
	/** 
	 * 广告播放价钱查询
	 * 
	 * */
	public List<HashMap<String, Object>> selectreleaserecordlist(HashMap<String, Object> map);
	
	/** 
	 * 出纸记录表查询
	 * */
	public List<HashMap<String, Object>> selectzxtissuerecordlist(HashMap<String, Object> map);
	
	public List<HashMap<String, Object>> selectzxplace(HashMap<String, Object> map);
	
}