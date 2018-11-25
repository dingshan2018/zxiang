package com.zxiang.project.advertise.adReleaseRecord.mapper;

import com.zxiang.project.advertise.adReleaseRecord.domain.AdReleaseRecord;

import java.util.HashMap;
import java.util.List;	

/**
 * 广告投放设备 数据层
 * 
 * @author ZXiang
 * @date 2018-09-25
 */
public interface AdReleaseRecordMapper 
{
	/**
     * 查询广告投放设备信息
     * 
     * @param releaseDeviceId 广告投放设备ID
     * @return 广告投放设备信息
     */
	public AdReleaseRecord selectAdReleaseRecordById(Integer releaseDeviceId);
	
	/**
     * 查询广告投放设备列表
     * 
     * @param adReleaseRecord 广告投放设备信息
     * @return 广告投放设备集合
     */
	public List<AdReleaseRecord> selectAdReleaseRecordList(AdReleaseRecord adReleaseRecord);
	
	/**
     * 新增广告投放设备
     * 
     * @param adReleaseRecord 广告投放设备信息
     * @return 结果
     */
	public int insertAdReleaseRecord(AdReleaseRecord adReleaseRecord);
	
	/**
     * 修改广告投放设备
     * 
     * @param adReleaseRecord 广告投放设备信息
     * @return 结果
     */
	public int updateAdReleaseRecord(AdReleaseRecord adReleaseRecord);
	
	/**
     * 删除广告投放设备
     * 
     * @param releaseDeviceId 广告投放设备ID
     * @return 结果
     */
	public int deleteAdReleaseRecordById(Integer releaseDeviceId);
	
	/**
     * 批量删除广告投放设备
     * 
     * @param releaseDeviceIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteAdReleaseRecordByIds(String[] releaseDeviceIds);

	/**
	 * 查询导出excel的数据
	 * @param params
	 * @return
	 */
	public List queryExport(HashMap<String, String> params);
	
}