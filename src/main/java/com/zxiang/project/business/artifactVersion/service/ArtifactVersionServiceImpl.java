package com.zxiang.project.business.artifactVersion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxiang.common.support.Convert;
import com.zxiang.project.business.artifactVersion.domain.ArtifactVersion;
import com.zxiang.project.business.artifactVersion.mapper.ArtifactVersionMapper;

/**
 * 系统组件 服务层实现
 * 
 * @author ZXiang
 * @date 2018-12-06
 */
@Service
public class ArtifactVersionServiceImpl implements IArtifactVersionService 
{
	@Autowired
	private ArtifactVersionMapper artifactVersionMapper;

	/**
     * 查询系统组件信息
     * 
     * @param artifactVerId 系统组件ID
     * @return 系统组件信息
     */
    @Override
	public ArtifactVersion selectArtifactVersionById(Integer artifactVerId)
	{
	    return artifactVersionMapper.selectArtifactVersionById(artifactVerId);
	}
	
	/**
     * 查询系统组件列表
     * 
     * @param artifactVersion 系统组件信息
     * @return 系统组件集合
     */
	@Override
	public List<ArtifactVersion> selectArtifactVersionList(ArtifactVersion artifactVersion)
	{
	    return artifactVersionMapper.selectArtifactVersionList(artifactVersion);
	}
	
    /**
     * 新增系统组件
     * 
     * @param artifactVersion 系统组件信息
     * @return 结果
     */
	@Override
	public int insertArtifactVersion(ArtifactVersion artifactVersion)
	{
	    return artifactVersionMapper.insertArtifactVersion(artifactVersion);
	}
	
	/**
     * 修改系统组件
     * 
     * @param artifactVersion 系统组件信息
     * @return 结果
     */
	@Override
	public int updateArtifactVersion(ArtifactVersion artifactVersion)
	{
	    return artifactVersionMapper.updateArtifactVersion(artifactVersion);
	}

	/**
     * 删除系统组件对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteArtifactVersionByIds(String ids)
	{
		return artifactVersionMapper.deleteArtifactVersionByIds(Convert.toStrArray(ids));
	}
	
}
