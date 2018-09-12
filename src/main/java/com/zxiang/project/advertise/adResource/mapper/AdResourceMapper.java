package com.zxiang.project.advertise.adResource.mapper;

import com.zxiang.project.advertise.adResource.domain.AdResource;
import java.util.List;	

/**
 * 素材维护 数据层
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public interface AdResourceMapper 
{
	/**
     * 查询素材维护信息
     * 
     * @param resourceId 素材维护ID
     * @return 素材维护信息
     */
	public AdResource selectAdResourceById(String resourceId);
	
	/**
     * 查询素材维护列表
     * 
     * @param adResource 素材维护信息
     * @return 素材维护集合
     */
	public List<AdResource> selectAdResourceList(AdResource adResource);
	
	/**
     * 新增素材维护
     * 
     * @param adResource 素材维护信息
     * @return 结果
     */
	public int insertAdResource(AdResource adResource);
	
	/**
     * 修改素材维护
     * 
     * @param adResource 素材维护信息
     * @return 结果
     */
	public int updateAdResource(AdResource adResource);
	
	/**
     * 删除素材维护
     * 
     * @param resourceId 素材维护ID
     * @return 结果
     */
	public int deleteAdResourceById(String resourceId);
	
	/**
     * 批量删除素材维护
     * 
     * @param resourceIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteAdResourceByIds(String[] resourceIds);
	
}