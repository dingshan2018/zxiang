package com.zxiang.project.business.deviceReleaseAudit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxiang.common.support.Convert;
import com.zxiang.project.business.deviceReleaseAudit.domain.DeviceReleaseAudit;
import com.zxiang.project.business.deviceReleaseAudit.mapper.DeviceReleaseAuditMapper;

/**
 * 设备投放审核 服务层实现
 * 
 * @author ZXiang
 * @date 2018-12-12
 */
@Service
public class DeviceReleaseAuditServiceImpl implements IDeviceReleaseAuditService 
{
	@Autowired
	private DeviceReleaseAuditMapper deviceReleaseAuditMapper;

	/**
     * 查询设备投放审核信息
     * 
     * @param auditId 设备投放审核ID
     * @return 设备投放审核信息
     */
    @Override
	public DeviceReleaseAudit selectDeviceReleaseAuditById(Integer auditId)
	{
	    return deviceReleaseAuditMapper.selectDeviceReleaseAuditById(auditId);
	}
	
	/**
     * 查询设备投放审核列表
     * 
     * @param deviceReleaseAudit 设备投放审核信息
     * @return 设备投放审核集合
     */
	@Override
	public List<DeviceReleaseAudit> selectDeviceReleaseAuditList(DeviceReleaseAudit deviceReleaseAudit)
	{
	    return deviceReleaseAuditMapper.selectDeviceReleaseAuditList(deviceReleaseAudit);
	}
	
    /**
     * 新增设备投放审核
     * 
     * @param deviceReleaseAudit 设备投放审核信息
     * @return 结果
     */
	@Override
	public int insertDeviceReleaseAudit(DeviceReleaseAudit deviceReleaseAudit)
	{
	    return deviceReleaseAuditMapper.insertDeviceReleaseAudit(deviceReleaseAudit);
	}
	
	/**
     * 修改设备投放审核
     * 
     * @param deviceReleaseAudit 设备投放审核信息
     * @return 结果
     */
	@Override
	public int updateDeviceReleaseAudit(DeviceReleaseAudit deviceReleaseAudit)
	{
	    return deviceReleaseAuditMapper.updateDeviceReleaseAudit(deviceReleaseAudit);
	}

	/**
     * 删除设备投放审核对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDeviceReleaseAuditByIds(String ids)
	{
		return deviceReleaseAuditMapper.deleteDeviceReleaseAuditByIds(Convert.toStrArray(ids));
	}
	
}
