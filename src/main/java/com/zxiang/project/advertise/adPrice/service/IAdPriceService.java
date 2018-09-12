package com.zxiang.project.advertise.adPrice.service;

import com.zxiang.project.advertise.adPrice.domain.AdPrice;
import java.util.List;

/**
 * 投放价格 服务层
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public interface IAdPriceService 
{
	/**
     * 查询投放价格信息
     * 
     * @param priceId 投放价格ID
     * @return 投放价格信息
     */
	public AdPrice selectAdPriceById(Integer priceId);
	
	/**
     * 查询投放价格列表
     * 
     * @param adPrice 投放价格信息
     * @return 投放价格集合
     */
	public List<AdPrice> selectAdPriceList(AdPrice adPrice);
	
	/**
     * 新增投放价格
     * 
     * @param adPrice 投放价格信息
     * @return 结果
     */
	public int insertAdPrice(AdPrice adPrice);
	
	/**
     * 修改投放价格
     * 
     * @param adPrice 投放价格信息
     * @return 结果
     */
	public int updateAdPrice(AdPrice adPrice);
		
	/**
     * 删除投放价格信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAdPriceByIds(String ids);
	
}
