package com.zxiang.project.business.version.mapper;

import java.util.List;

import com.zxiang.project.business.version.domain.Version;	

/**
 * 版本 数据层
 * 
 * @author ZXiang
 * @date 2018-12-06
 */
public interface VersionMapper 
{
	/**
     * 查询版本信息
     * 
     * @param sysVerId 版本ID
     * @return 版本信息
     */
	public Version selectVersionById(Integer sysVerId);
	
	/**
     * 查询版本列表
     * 
     * @param version 版本信息
     * @return 版本集合
     */
	public List<Version> selectVersionList(Version version);
	
	/**
     * 新增版本
     * 
     * @param version 版本信息
     * @return 结果
     */
	public int insertVersion(Version version);
	
	/**
     * 修改版本
     * 
     * @param version 版本信息
     * @return 结果
     */
	public int updateVersion(Version version);
	
	/**
     * 删除版本
     * 
     * @param sysVerId 版本ID
     * @return 结果
     */
	public int deleteVersionById(Integer sysVerId);
	
	/**
     * 批量删除版本
     * 
     * @param sysVerIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteVersionByIds(String[] sysVerIds);

	/**
	 * 校验版本编号唯一
	 * @param sysVerCode
	 * @return
	 */
	public int checkCodeUnique(String sysVerCode);
	
}