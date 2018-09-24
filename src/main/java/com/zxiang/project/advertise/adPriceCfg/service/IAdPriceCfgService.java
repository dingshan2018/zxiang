package com.zxiang.project.advertise.adPriceCfg.service;

import com.zxiang.project.advertise.adPriceCfg.domain.AdPriceCfg;
import java.util.List;

/**
 * 投放价格 服务层
 * 
 * @author ZXiang
 * @date 2018-09-24
 */
public interface IAdPriceCfgService 
{
	/**
     * 查询投放价格信息
     * 
     * @param priceCfgId 投放价格ID
     * @return 投放价格信息
     */
	public AdPriceCfg selectAdPriceCfgById(Integer priceCfgId);
	
	/**
     * 查询投放价格列表
     * 
     * @param adPriceCfg 投放价格信息
     * @return 投放价格集合
     */
	public List<AdPriceCfg> selectAdPriceCfgList(AdPriceCfg adPriceCfg);
	
	/**
     * 新增投放价格
     * 
     * @param adPriceCfg 投放价格信息
     * @return 结果
     */
	public int insertAdPriceCfg(AdPriceCfg adPriceCfg);
	
	/**
     * 修改投放价格
     * 
     * @param adPriceCfg 投放价格信息
     * @return 结果
     */
	public int updateAdPriceCfg(AdPriceCfg adPriceCfg);
		
	/**
     * 删除投放价格信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAdPriceCfgByIds(String ids);
	
}
