package com.zxiang.project.client.advertise.service;

import com.zxiang.project.client.advertise.domain.Advertise;
import java.util.List;

/**
 * 广告商 服务层
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public interface IAdvertiseService 
{
	/**
     * 查询广告商信息
     * 
     * @param advertiseId 广告商ID
     * @return 广告商信息
     */
	public Advertise selectAdvertiseById(Integer advertiseId);
	
	/**
     * 查询广告商列表
     * 
     * @param advertise 广告商信息
     * @return 广告商集合
     */
	public List<Advertise> selectAdvertiseList(Advertise advertise);
	
	/**
     * 新增广告商
     * 
     * @param advertise 广告商信息
     * @return 结果
     */
	public int insertAdvertise(Advertise advertise);
	
	/**
     * 修改广告商
     * 
     * @param advertise 广告商信息
     * @return 结果
     */
	public int updateAdvertise(Advertise advertise);
		
	/**
     * 删除广告商信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAdvertiseByIds(String ids);
	
}
