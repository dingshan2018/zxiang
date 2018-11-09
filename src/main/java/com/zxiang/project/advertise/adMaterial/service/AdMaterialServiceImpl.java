package com.zxiang.project.advertise.adMaterial.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxiang.common.support.Convert;
import com.zxiang.project.advertise.adMaterial.domain.AdMaterial;
import com.zxiang.project.advertise.adMaterial.mapper.AdMaterialMapper;

/**
 * 广告投放素材 服务层实现
 * 
 * @author ZXiang
 * @date 2018-11-08
 */
@Service
public class AdMaterialServiceImpl implements IAdMaterialService 
{
	@Autowired
	private AdMaterialMapper adMaterialMapper;

	/**
     * 查询广告投放素材信息
     * 
     * @param adMaterialId 广告投放素材ID
     * @return 广告投放素材信息
     */
    @Override
	public AdMaterial selectAdMaterialById(Integer adMaterialId)
	{
	    return adMaterialMapper.selectAdMaterialById(adMaterialId);
	}
	
	/**
     * 查询广告投放素材列表
     * 
     * @param adMaterial 广告投放素材信息
     * @return 广告投放素材集合
     */
	@Override
	public List<AdMaterial> selectAdMaterialList(AdMaterial adMaterial)
	{
	    return adMaterialMapper.selectAdMaterialList(adMaterial);
	}
	
    /**
     * 新增广告投放素材
     * 
     * @param adMaterial 广告投放素材信息
     * @return 结果
     */
	@Override
	public int insertAdMaterial(AdMaterial adMaterial)
	{
	    return adMaterialMapper.insertAdMaterial(adMaterial);
	}
	
	/**
     * 修改广告投放素材
     * 
     * @param adMaterial 广告投放素材信息
     * @return 结果
     */
	@Override
	public int updateAdMaterial(AdMaterial adMaterial)
	{
	    return adMaterialMapper.updateAdMaterial(adMaterial);
	}

	/**
     * 删除广告投放素材对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAdMaterialByIds(String ids)
	{
		return adMaterialMapper.deleteAdMaterialByIds(Convert.toStrArray(ids));
	}

	@Override
	public int getMaxBatch(Integer adScheduleId) {
		return adMaterialMapper.selectMaxBatch(adScheduleId);
	}

	@Override
	public List<AdMaterial> selectListByAdSchId(Integer adScheduleId) {
		return adMaterialMapper.selectListByAdSchId(adScheduleId);
	}
	
}
