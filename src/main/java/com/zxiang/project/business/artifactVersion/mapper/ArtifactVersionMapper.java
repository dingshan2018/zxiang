package com.zxiang.project.business.artifactVersion.mapper;

import java.util.List;

import com.zxiang.project.business.artifactVersion.domain.ArtifactVersion;	

/**
 * 系统组件 数据层
 * 
 * @author ZXiang
 * @date 2018-12-06
 */
public interface ArtifactVersionMapper 
{
	/**
     * 查询系统组件信息
     * 
     * @param artifactVerId 系统组件ID
     * @return 系统组件信息
     */
	public ArtifactVersion selectArtifactVersionById(Integer artifactVerId);
	
	/**
     * 查询系统组件列表
     * 
     * @param artifactVersion 系统组件信息
     * @return 系统组件集合
     */
	public List<ArtifactVersion> selectArtifactVersionList(ArtifactVersion artifactVersion);
	
	/**
     * 新增系统组件
     * 
     * @param artifactVersion 系统组件信息
     * @return 结果
     */
	public int insertArtifactVersion(ArtifactVersion artifactVersion);
	
	/**
     * 修改系统组件
     * 
     * @param artifactVersion 系统组件信息
     * @return 结果
     */
	public int updateArtifactVersion(ArtifactVersion artifactVersion);
	
	/**
     * 删除系统组件
     * 
     * @param artifactVerId 系统组件ID
     * @return 结果
     */
	public int deleteArtifactVersionById(Integer artifactVerId);
	
	/**
     * 批量删除系统组件
     * 
     * @param artifactVerIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteArtifactVersionByIds(String[] artifactVerIds);
	
}