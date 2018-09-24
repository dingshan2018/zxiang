package com.zxiang.project.advertise.adReleaseRecord.service;

import com.zxiang.project.advertise.adReleaseRecord.domain.AdReleaseRecord;
import java.util.List;

/**
 * 广告投放设备 服务层
 * 
 * @author ZXiang
 * @date 2018-09-25
 */
public interface IAdReleaseRecordService 
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
     * 删除广告投放设备信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAdReleaseRecordByIds(String ids);
	
}
