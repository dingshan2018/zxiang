package com.zxiang.project.advertise.adPriceTimer.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zxiang.project.advertise.adPriceTimer.mapper.AdPriceTimerMapper;
import com.zxiang.project.advertise.adPriceTimer.domain.AdPriceTimer;
import com.zxiang.project.advertise.adPriceTimer.service.IAdPriceTimerService;
import com.zxiang.common.support.Convert;

/**
 * 广告价格时段 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-24
 */
@Service
public class AdPriceTimerServiceImpl implements IAdPriceTimerService 
{
	@Autowired
	private AdPriceTimerMapper adPriceTimerMapper;

	/**
     * 查询广告价格时段信息
     * 
     * @param adPriceId 广告价格时段ID
     * @return 广告价格时段信息
     */
    @Override
	public AdPriceTimer selectAdPriceTimerById(Integer adPriceId)
	{
	    return adPriceTimerMapper.selectAdPriceTimerById(adPriceId);
	}
	
	/**
     * 查询广告价格时段列表
     * 
     * @param adPriceTimer 广告价格时段信息
     * @return 广告价格时段集合
     */
	@Override
	public List<AdPriceTimer> selectAdPriceTimerList(AdPriceTimer adPriceTimer)
	{
	    return adPriceTimerMapper.selectAdPriceTimerList(adPriceTimer);
	}
	
    /**
     * 新增广告价格时段
     * 
     * @param adPriceTimer 广告价格时段信息
     * @return 结果
     */
	@Override
	public int insertAdPriceTimer(AdPriceTimer adPriceTimer)
	{
	    return adPriceTimerMapper.insertAdPriceTimer(adPriceTimer);
	}
	
	/**
     * 修改广告价格时段
     * 
     * @param adPriceTimer 广告价格时段信息
     * @return 结果
     */
	@Override
	public int updateAdPriceTimer(AdPriceTimer adPriceTimer)
	{
	    return adPriceTimerMapper.updateAdPriceTimer(adPriceTimer);
	}

	/**
     * 删除广告价格时段对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAdPriceTimerByIds(String ids)
	{
		return adPriceTimerMapper.deleteAdPriceTimerByIds(Convert.toStrArray(ids));
	}
	
}
