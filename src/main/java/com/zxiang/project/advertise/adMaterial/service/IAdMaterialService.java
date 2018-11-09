package com.zxiang.project.advertise.adMaterial.service;

import java.util.List;

import com.zxiang.project.advertise.adMaterial.domain.AdMaterial;

/**
 * 广告投放素材 服务层
 * 
 * @author ZXiang
 * @date 2018-11-08
 */
public interface IAdMaterialService 
{
	/**
     * 查询广告投放素材信息
     * 
     * @param adMaterialId 广告投放素材ID
     * @return 广告投放素材信息
     */
	public AdMaterial selectAdMaterialById(Integer adMaterialId);
	
	/**
     * 查询广告投放素材列表
     * 
     * @param adMaterial 广告投放素材信息
     * @return 广告投放素材集合
     */
	public List<AdMaterial> selectAdMaterialList(AdMaterial adMaterial);
	
	/**
     * 新增广告投放素材
     * 
     * @param adMaterial 广告投放素材信息
     * @return 结果
     */
	public int insertAdMaterial(AdMaterial adMaterial);
	
	/**
     * 修改广告投放素材
     * 
     * @param adMaterial 广告投放素材信息
     * @return 结果
     */
	public int updateAdMaterial(AdMaterial adMaterial);
		
	/**
     * 删除广告投放素材信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAdMaterialByIds(String ids);

	/**
	 * 获取广告投放素材最大批次号
	 * @param adScheduleId
	 * @return
	 */
	public int getMaxBatch(Integer adScheduleId);

	/**
	 * 根据广告ID查询最新批次素材列表
	 * @param adScheduleId
	 * @return
	 */
	public List<AdMaterial> selectListByAdSchId(Integer adScheduleId);
	
}
