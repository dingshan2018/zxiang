package com.zxiang.project.business.version.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxiang.common.support.Convert;
import com.zxiang.project.business.version.domain.Version;
import com.zxiang.project.business.version.mapper.VersionMapper;

/**
 * 版本 服务层实现
 * 
 * @author ZXiang
 * @date 2018-12-06
 */
@Service
public class VersionServiceImpl implements IVersionService 
{
	@Autowired
	private VersionMapper versionMapper;

	/**
     * 查询版本信息
     * 
     * @param sysVerId 版本ID
     * @return 版本信息
     */
    @Override
	public Version selectVersionById(Integer sysVerId)
	{
	    return versionMapper.selectVersionById(sysVerId);
	}
	
	/**
     * 查询版本列表
     * 
     * @param version 版本信息
     * @return 版本集合
     */
	@Override
	public List<Version> selectVersionList(Version version)
	{
	    return versionMapper.selectVersionList(version);
	}
	
    /**
     * 新增版本
     * 
     * @param version 版本信息
     * @return 结果
     */
	@Override
	public int insertVersion(Version version)
	{
	    return versionMapper.insertVersion(version);
	}
	
	/**
     * 修改版本
     * 
     * @param version 版本信息
     * @return 结果
     */
	@Override
	public int updateVersion(Version version)
	{
	    return versionMapper.updateVersion(version);
	}

	/**
     * 删除版本对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteVersionByIds(String ids)
	{
		return versionMapper.deleteVersionByIds(Convert.toStrArray(ids));
	}
	
}
