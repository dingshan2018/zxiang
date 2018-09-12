package com.zxiang.project.client.advertise.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zxiang.project.client.advertise.mapper.AdvertiseMapper;
import com.zxiang.project.client.advertise.domain.Advertise;
import com.zxiang.project.client.advertise.service.IAdvertiseService;
import com.zxiang.common.support.Convert;

/**
 * 广告商 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Service
public class AdvertiseServiceImpl implements IAdvertiseService 
{
	@Autowired
	private AdvertiseMapper advertiseMapper;

	/**
     * 查询广告商信息
     * 
     * @param advertiseId 广告商ID
     * @return 广告商信息
     */
    @Override
	public Advertise selectAdvertiseById(Integer advertiseId)
	{
	    return advertiseMapper.selectAdvertiseById(advertiseId);
	}
	
	/**
     * 查询广告商列表
     * 
     * @param advertise 广告商信息
     * @return 广告商集合
     */
	@Override
	public List<Advertise> selectAdvertiseList(Advertise advertise)
	{
	    return advertiseMapper.selectAdvertiseList(advertise);
	}
	
    /**
     * 新增广告商
     * 
     * @param advertise 广告商信息
     * @return 结果
     */
	@Override
	public int insertAdvertise(Advertise advertise)
	{
	    return advertiseMapper.insertAdvertise(advertise);
	}
	
	/**
     * 修改广告商
     * 
     * @param advertise 广告商信息
     * @return 结果
     */
	@Override
	public int updateAdvertise(Advertise advertise)
	{
	    return advertiseMapper.updateAdvertise(advertise);
	}

	/**
     * 删除广告商对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAdvertiseByIds(String ids)
	{
		return advertiseMapper.deleteAdvertiseByIds(Convert.toStrArray(ids));
	}
	
}
