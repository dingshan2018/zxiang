package com.zxiang.project.business.version.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.zxiang.project.business.version.domain.Version;

/**
 * 版本 服务层
 * 
 * @author ZXiang
 * @date 2018-12-06
 */
public interface IVersionService 
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
     * 删除版本信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteVersionByIds(String ids);

	/**
	 * 更新包上传保存
	 * @param request
	 * @param files
	 * @param operatorUser
	 * @return
	 */
	public int uploadSave(HttpServletRequest request, List<MultipartFile> files, String operatorUser);
	
}
