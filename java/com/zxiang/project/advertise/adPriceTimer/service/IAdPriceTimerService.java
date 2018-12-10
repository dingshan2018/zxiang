package com.zxiang.project.advertise.adPriceTimer.service;

import com.zxiang.project.advertise.adPriceTimer.domain.AdPriceTimer;
import java.util.List;

/**
 * 广告价格时段 服务层
 * 
 * @author ZXiang
 * @date 2018-09-24
 */
public interface IAdPriceTimerService 
{
	/**
     * 查询广告价格时段信息
     * 
     * @param adPriceId 广告价格时段ID
     * @return 广告价格时段信息
     */
	public AdPriceTimer selectAdPriceTimerById(Integer adPriceId);
	
	/**
     * 查询广告价格时段列表
     * 
     * @param adPriceTimer 广告价格时段信息
     * @return 广告价格时段集合
     */
	public List<AdPriceTimer> selectAdPriceTimerList(AdPriceTimer adPriceTimer);
	
	/**
     * 新增广告价格时段
     * 
     * @param adPriceTimer 广告价格时段信息
     * @return 结果
     */
	public int insertAdPriceTimer(AdPriceTimer adPriceTimer);
	
	/**
     * 修改广告价格时段
     * 
     * @param adPriceTimer 广告价格时段信息
     * @return 结果
     */
	public int updateAdPriceTimer(AdPriceTimer adPriceTimer);
		
	/**
     * 删除广告价格时段信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAdPriceTimerByIds(String ids);
	
}