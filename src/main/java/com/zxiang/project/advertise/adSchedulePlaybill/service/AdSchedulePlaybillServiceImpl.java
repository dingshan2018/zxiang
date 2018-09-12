package com.zxiang.project.advertise.adSchedulePlaybill.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zxiang.project.advertise.adSchedulePlaybill.mapper.AdSchedulePlaybillMapper;
import com.zxiang.project.advertise.adSchedulePlaybill.domain.AdSchedulePlaybill;
import com.zxiang.project.advertise.adSchedulePlaybill.service.IAdSchedulePlaybillService;
import com.zxiang.common.support.Convert;

/**
 * 推广计划明细 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Service
public class AdSchedulePlaybillServiceImpl implements IAdSchedulePlaybillService 
{
	@Autowired
	private AdSchedulePlaybillMapper adSchedulePlaybillMapper;

	/**
     * 查询推广计划明细信息
     * 
     * @param schedulePlaybillId 推广计划明细ID
     * @return 推广计划明细信息
     */
    @Override
	public AdSchedulePlaybill selectAdSchedulePlaybillById(Integer schedulePlaybillId)
	{
	    return adSchedulePlaybillMapper.selectAdSchedulePlaybillById(schedulePlaybillId);
	}
	
	/**
     * 查询推广计划明细列表
     * 
     * @param adSchedulePlaybill 推广计划明细信息
     * @return 推广计划明细集合
     */
	@Override
	public List<AdSchedulePlaybill> selectAdSchedulePlaybillList(AdSchedulePlaybill adSchedulePlaybill)
	{
	    return adSchedulePlaybillMapper.selectAdSchedulePlaybillList(adSchedulePlaybill);
	}
	
    /**
     * 新增推广计划明细
     * 
     * @param adSchedulePlaybill 推广计划明细信息
     * @return 结果
     */
	@Override
	public int insertAdSchedulePlaybill(AdSchedulePlaybill adSchedulePlaybill)
	{
	    return adSchedulePlaybillMapper.insertAdSchedulePlaybill(adSchedulePlaybill);
	}
	
	/**
     * 修改推广计划明细
     * 
     * @param adSchedulePlaybill 推广计划明细信息
     * @return 结果
     */
	@Override
	public int updateAdSchedulePlaybill(AdSchedulePlaybill adSchedulePlaybill)
	{
	    return adSchedulePlaybillMapper.updateAdSchedulePlaybill(adSchedulePlaybill);
	}

	/**
     * 删除推广计划明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAdSchedulePlaybillByIds(String ids)
	{
		return adSchedulePlaybillMapper.deleteAdSchedulePlaybillByIds(Convert.toStrArray(ids));
	}
	
}
