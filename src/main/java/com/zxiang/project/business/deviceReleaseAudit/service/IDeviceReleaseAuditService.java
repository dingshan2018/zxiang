package com.zxiang.project.business.deviceReleaseAudit.service;

import java.util.List;

import com.zxiang.project.business.deviceReleaseAudit.domain.DeviceReleaseAudit;

/**
 * 设备投放审核 服务层
 * 
 * @author ZXiang
 * @date 2018-12-12
 */
public interface IDeviceReleaseAuditService 
{
	/**
     * 查询设备投放审核信息
     * 
     * @param auditId 设备投放审核ID
     * @return 设备投放审核信息
     */
	public DeviceReleaseAudit selectDeviceReleaseAuditById(Integer auditId);
	
	/**
     * 查询设备投放审核列表
     * 
     * @param deviceReleaseAudit 设备投放审核信息
     * @return 设备投放审核集合
     */
	public List<DeviceReleaseAudit> selectDeviceReleaseAuditList(DeviceReleaseAudit deviceReleaseAudit);
	
	/**
     * 新增设备投放审核
     * 
     * @param deviceReleaseAudit 设备投放审核信息
     * @return 结果
     */
	public int insertDeviceReleaseAudit(DeviceReleaseAudit deviceReleaseAudit);
	
	/**
     * 修改设备投放审核
     * 
     * @param deviceReleaseAudit 设备投放审核信息
     * @return 结果
     */
	public int updateDeviceReleaseAudit(DeviceReleaseAudit deviceReleaseAudit);
		
	/**
     * 删除设备投放审核信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDeviceReleaseAuditByIds(String ids);
	
}
