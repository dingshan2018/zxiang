package com.zxiang.project.advertise.adPriceCfg.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zxiang.project.advertise.adPriceCfg.mapper.AdPriceCfgMapper;
import com.zxiang.project.advertise.adPriceCfg.domain.AdPriceCfg;
import com.zxiang.project.advertise.adPriceCfg.service.IAdPriceCfgService;
import com.zxiang.common.support.Convert;

/**
 * 投放价格 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-25
 */
@Service
public class AdPriceCfgServiceImpl implements IAdPriceCfgService 
{
	@Autowired
	private AdPriceCfgMapper adPriceCfgMapper;

	/**
     * 查询投放价格信息
     * 
     * @param priceCfgId 投放价格ID
     * @return 投放价格信息
     */
    @Override
	public AdPriceCfg selectAdPriceCfgById(Integer priceCfgId)
	{
	    return adPriceCfgMapper.selectAdPriceCfgById(priceCfgId);
	}
	
	/**
     * 查询投放价格列表
     * 
     * @param adPriceCfg 投放价格信息
     * @return 投放价格集合
     */
	@Override
	public List<AdPriceCfg> selectAdPriceCfgList(AdPriceCfg adPriceCfg)
	{
	    return adPriceCfgMapper.selectAdPriceCfgList(adPriceCfg);
	}
	
    /**
     * 新增投放价格
     * 
     * @param adPriceCfg 投放价格信息
     * @return 结果
     */
	@Override
	public int insertAdPriceCfg(AdPriceCfg adPriceCfg)
	{
	    return adPriceCfgMapper.insertAdPriceCfg(adPriceCfg);
	}
	
	/**
     * 修改投放价格
     * 
     * @param adPriceCfg 投放价格信息
     * @return 结果
     */
	@Override
	public int updateAdPriceCfg(AdPriceCfg adPriceCfg)
	{
	    return adPriceCfgMapper.updateAdPriceCfg(adPriceCfg);
	}

	/**
     * 删除投放价格对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAdPriceCfgByIds(String ids)
	{
		return adPriceCfgMapper.deleteAdPriceCfgByIds(Convert.toStrArray(ids));
	}
	
}
