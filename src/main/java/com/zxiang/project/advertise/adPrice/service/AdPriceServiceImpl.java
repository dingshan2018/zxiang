package com.zxiang.project.advertise.adPrice.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zxiang.project.advertise.adPrice.mapper.AdPriceMapper;
import com.zxiang.project.advertise.adPrice.domain.AdPrice;
import com.zxiang.project.advertise.adPrice.service.IAdPriceService;
import com.zxiang.common.support.Convert;

/**
 * 投放价格 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Service
public class AdPriceServiceImpl implements IAdPriceService 
{
	@Autowired
	private AdPriceMapper adPriceMapper;

	/**
     * 查询投放价格信息
     * 
     * @param priceId 投放价格ID
     * @return 投放价格信息
     */
    @Override
	public AdPrice selectAdPriceById(Integer priceId)
	{
	    return adPriceMapper.selectAdPriceById(priceId);
	}
	
	/**
     * 查询投放价格列表
     * 
     * @param adPrice 投放价格信息
     * @return 投放价格集合
     */
	@Override
	public List<AdPrice> selectAdPriceList(AdPrice adPrice)
	{
	    return adPriceMapper.selectAdPriceList(adPrice);
	}
	
    /**
     * 新增投放价格
     * 
     * @param adPrice 投放价格信息
     * @return 结果
     */
	@Override
	public int insertAdPrice(AdPrice adPrice)
	{
	    return adPriceMapper.insertAdPrice(adPrice);
	}
	
	/**
     * 修改投放价格
     * 
     * @param adPrice 投放价格信息
     * @return 结果
     */
	@Override
	public int updateAdPrice(AdPrice adPrice)
	{
	    return adPriceMapper.updateAdPrice(adPrice);
	}

	/**
     * 删除投放价格对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAdPriceByIds(String ids)
	{
		return adPriceMapper.deleteAdPriceByIds(Convert.toStrArray(ids));
	}
	
}
