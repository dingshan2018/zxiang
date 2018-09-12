package com.zxiang.project.advertise.adSchedulePosition.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zxiang.project.advertise.adSchedulePosition.mapper.AdSchedulePositionMapper;
import com.zxiang.project.advertise.adSchedulePosition.domain.AdSchedulePosition;
import com.zxiang.project.advertise.adSchedulePosition.service.IAdSchedulePositionService;
import com.zxiang.common.support.Convert;

/**
 * 投放位置 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Service
public class AdSchedulePositionServiceImpl implements IAdSchedulePositionService 
{
	@Autowired
	private AdSchedulePositionMapper adSchedulePositionMapper;

	/**
     * 查询投放位置信息
     * 
     * @param schedulePositionId 投放位置ID
     * @return 投放位置信息
     */
    @Override
	public AdSchedulePosition selectAdSchedulePositionById(Integer schedulePositionId)
	{
	    return adSchedulePositionMapper.selectAdSchedulePositionById(schedulePositionId);
	}
	
	/**
     * 查询投放位置列表
     * 
     * @param adSchedulePosition 投放位置信息
     * @return 投放位置集合
     */
	@Override
	public List<AdSchedulePosition> selectAdSchedulePositionList(AdSchedulePosition adSchedulePosition)
	{
	    return adSchedulePositionMapper.selectAdSchedulePositionList(adSchedulePosition);
	}
	
    /**
     * 新增投放位置
     * 
     * @param adSchedulePosition 投放位置信息
     * @return 结果
     */
	@Override
	public int insertAdSchedulePosition(AdSchedulePosition adSchedulePosition)
	{
	    return adSchedulePositionMapper.insertAdSchedulePosition(adSchedulePosition);
	}
	
	/**
     * 修改投放位置
     * 
     * @param adSchedulePosition 投放位置信息
     * @return 结果
     */
	@Override
	public int updateAdSchedulePosition(AdSchedulePosition adSchedulePosition)
	{
	    return adSchedulePositionMapper.updateAdSchedulePosition(adSchedulePosition);
	}

	/**
     * 删除投放位置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAdSchedulePositionByIds(String ids)
	{
		return adSchedulePositionMapper.deleteAdSchedulePositionByIds(Convert.toStrArray(ids));
	}
	
}
