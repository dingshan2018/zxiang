package com.zxiang.project.advertise.adScheduleRelease.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zxiang.project.advertise.adScheduleRelease.mapper.AdScheduleReleaseMapper;
import com.zxiang.project.advertise.adScheduleRelease.domain.AdScheduleRelease;
import com.zxiang.project.advertise.adScheduleRelease.service.IAdScheduleReleaseService;
import com.zxiang.common.support.Convert;

/**
 * 投放时间 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Service
public class AdScheduleReleaseServiceImpl implements IAdScheduleReleaseService 
{
	@Autowired
	private AdScheduleReleaseMapper adScheduleReleaseMapper;

	/**
     * 查询投放时间信息
     * 
     * @param scheduleReleaseId 投放时间ID
     * @return 投放时间信息
     */
    @Override
	public AdScheduleRelease selectAdScheduleReleaseById(Integer scheduleReleaseId)
	{
	    return adScheduleReleaseMapper.selectAdScheduleReleaseById(scheduleReleaseId);
	}
	
	/**
     * 查询投放时间列表
     * 
     * @param adScheduleRelease 投放时间信息
     * @return 投放时间集合
     */
	@Override
	public List<AdScheduleRelease> selectAdScheduleReleaseList(AdScheduleRelease adScheduleRelease)
	{
	    return adScheduleReleaseMapper.selectAdScheduleReleaseList(adScheduleRelease);
	}
	
    /**
     * 新增投放时间
     * 
     * @param adScheduleRelease 投放时间信息
     * @return 结果
     */
	@Override
	public int insertAdScheduleRelease(AdScheduleRelease adScheduleRelease)
	{
	    return adScheduleReleaseMapper.insertAdScheduleRelease(adScheduleRelease);
	}
	
	/**
     * 修改投放时间
     * 
     * @param adScheduleRelease 投放时间信息
     * @return 结果
     */
	@Override
	public int updateAdScheduleRelease(AdScheduleRelease adScheduleRelease)
	{
	    return adScheduleReleaseMapper.updateAdScheduleRelease(adScheduleRelease);
	}

	/**
     * 删除投放时间对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAdScheduleReleaseByIds(String ids)
	{
		return adScheduleReleaseMapper.deleteAdScheduleReleaseByIds(Convert.toStrArray(ids));
	}
	
}
