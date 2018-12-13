package com.zxiang.project.business.deviceReleaseAudit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zxiang.project.business.deviceReleaseAudit.domain.DeviceReleaseAudit;	

/**
 * 设备投放审核 数据层
 * 
 * @author ZXiang
 * @date 2018-12-12
 */
public interface DeviceReleaseAuditMapper 
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
     * 删除设备投放审核
     * 
     * @param auditId 设备投放审核ID
     * @return 结果
     */
	public int deleteDeviceReleaseAuditById(Integer auditId);
	
	/**
     * 批量删除设备投放审核
     * 
     * @param auditIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteDeviceReleaseAuditByIds(String[] auditIds);

	/**
	 * 通过设备ID查询审批数据
	 * @param deviceId
	 * @return
	 */
	public DeviceReleaseAudit selectAuditByDeviceId(@Param("deviceId")Integer deviceId);

	/**
	 * 批量更新投放审核数据
	 * @param auditIds
	 * @param approved
	 * @param approvedRemark
	 * @param operatorUser 
	 * @return
	 */
	public int batchUpdateAudit(@Param("array")String[] auditIds, 
			@Param("approved")String approved, @Param("approvedRemark")String approvedRemark,@Param("approvedUser") String approvedUser);
	
	
}