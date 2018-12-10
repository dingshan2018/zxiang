package com.zxiang.project.advertise.adReleaseRange.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxiang.common.support.Convert;
import com.zxiang.project.advertise.adReleaseRange.domain.AdReleaseRange;
import com.zxiang.project.advertise.adReleaseRange.mapper.AdReleaseRangeMapper;

/**
 * 广告投放范围 服务层实现
 * 
 * @author ZXiang
 * @date 2018-11-07
 */
@Service
public class AdReleaseRangeServiceImpl implements IAdReleaseRangeService 
{
	@Autowired
	private AdReleaseRangeMapper adReleaseRangeMapper;

	/**
     * 查询广告投放范围信息
     * 
     * @param adReleaseRangeId 广告投放范围ID
     * @return 广告投放范围信息
     */
    @Override
	public AdReleaseRange selectAdReleaseRangeById(Integer adReleaseRangeId)
	{
	    return adReleaseRangeMapper.selectAdReleaseRangeById(adReleaseRangeId);
	}
	
	/**
     * 查询广告投放范围列表
     * 
     * @param adReleaseRange 广告投放范围信息
     * @return 广告投放范围集合
     */
	@Override
	public List<AdReleaseRange> selectAdReleaseRangeList(AdReleaseRange adReleaseRange)
	{
	    return adReleaseRangeMapper.selectAdReleaseRangeList(adReleaseRange);
	}
	
    /**
     * 新增广告投放范围
     * 
     * @param adReleaseRange 广告投放范围信息
     * @return 结果
     */
	@Override
	public int insertAdReleaseRange(AdReleaseRange adReleaseRange)
	{
	    return adReleaseRangeMapper.insertAdReleaseRange(adReleaseRange);
	}
	
	/**
     * 修改广告投放范围
     * 
     * @param adReleaseRange 广告投放范围信息
     * @return 结果
     */
	@Override
	public int updateAdReleaseRange(AdReleaseRange adReleaseRange)
	{
	    return adReleaseRangeMapper.updateAdReleaseRange(adReleaseRange);
	}

	/**
     * 删除广告投放范围对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAdReleaseRangeByIds(String ids)
	{
		return adReleaseRangeMapper.deleteAdReleaseRangeByIds(Convert.toStrArray(ids));
	}

	@Override
	public int getOrderNum(Integer adScheduleId) {
		return adReleaseRangeMapper.getOrderNum(adScheduleId);
	}
	
}
