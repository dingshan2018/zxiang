package com.zxiang.project.advertise.adMaterial.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zxiang.project.advertise.adMaterial.domain.AdMaterial;	

/**
 * 广告投放素材 数据层
 * 
 * @author ZXiang
 * @date 2018-11-08
 */
public interface AdMaterialMapper 
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
     * 删除广告投放素材
     * 
     * @param adMaterialId 广告投放素材ID
     * @return 结果
     */
	public int deleteAdMaterialById(Integer adMaterialId);
	
	/**
     * 批量删除广告投放素材
     * 
     * @param adMaterialIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteAdMaterialByIds(String[] adMaterialIds);

	/**
	 * 查询素材表最大上传批次号
	 * @param adScheduleId
	 * @return
	 */
	public int selectMaxBatch(@Param("adScheduleId") Integer adScheduleId);
	
	/**
	 * 根据广告ID查询最新批次素材列表
	 * @param adMaterial
	 * @return
	 */
	public List<AdMaterial> selectMaxListByAdSchId(@Param("adScheduleId") Integer adScheduleId);
	
	/**
	 * 根据广告ID查询该广告下所有素材列表
	 * @param adMaterial
	 * @return
	 */
	public List<AdMaterial> selectListByAdSchId(@Param("adScheduleId") Integer adScheduleId);

	/**
	 * 根据广告ID查询该广告下不同的素材类型
	 * @param adScheduleId
	 * @return
	 */
	public List<AdMaterial> getDistinctList(@Param("adScheduleId")Integer adScheduleId);
	
	/**
	 * 通过广告ID删除数据
	 * @param adIds
	 * @return
	 */
	public int deleteMaterialByAdIds(String[] adIds);

	
}