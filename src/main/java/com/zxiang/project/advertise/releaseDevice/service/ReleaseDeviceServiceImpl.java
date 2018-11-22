package com.zxiang.project.advertise.releaseDevice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxiang.common.support.Convert;
import com.zxiang.project.advertise.releaseDevice.domain.ReleaseDevice;
import com.zxiang.project.advertise.releaseDevice.mapper.ReleaseDeviceMapper;

/**
 * 投放终端配置 服务层实现
 * 
 * @author ZXiang
 * @date 2018-11-22
 */
@Service
public class ReleaseDeviceServiceImpl implements IReleaseDeviceService 
{
	@Autowired
	private ReleaseDeviceMapper releaseDeviceMapper;

	/**
     * 查询投放终端配置信息
     * 
     * @param releaseDeviceId 投放终端配置ID
     * @return 投放终端配置信息
     */
    @Override
	public ReleaseDevice selectReleaseDeviceById(Integer releaseDeviceId)
	{
	    return releaseDeviceMapper.selectReleaseDeviceById(releaseDeviceId);
	}
	
	/**
     * 查询投放终端配置列表
     * 
     * @param releaseDevice 投放终端配置信息
     * @return 投放终端配置集合
     */
	@Override
	public List<ReleaseDevice> selectReleaseDeviceList(ReleaseDevice releaseDevice)
	{
	    return releaseDeviceMapper.selectReleaseDeviceList(releaseDevice);
	}
	
    /**
     * 新增投放终端配置
     * 
     * @param releaseDevice 投放终端配置信息
     * @return 结果
     */
	@Override
	public int insertReleaseDevice(ReleaseDevice releaseDevice)
	{
	    return releaseDeviceMapper.insertReleaseDevice(releaseDevice);
	}
	
	/**
     * 修改投放终端配置
     * 
     * @param releaseDevice 投放终端配置信息
     * @return 结果
     */
	@Override
	public int updateReleaseDevice(ReleaseDevice releaseDevice)
	{
	    return releaseDeviceMapper.updateReleaseDevice(releaseDevice);
	}

	/**
     * 删除投放终端配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteReleaseDeviceByIds(String ids)
	{
		return releaseDeviceMapper.deleteReleaseDeviceByIds(Convert.toStrArray(ids));
	}
	
}
