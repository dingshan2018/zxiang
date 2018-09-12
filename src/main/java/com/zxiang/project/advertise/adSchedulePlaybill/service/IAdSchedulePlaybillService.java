package com.zxiang.project.advertise.adSchedulePlaybill.service;

import com.zxiang.project.advertise.adSchedulePlaybill.domain.AdSchedulePlaybill;
import java.util.List;

/**
 * 推广计划明细 服务层
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public interface IAdSchedulePlaybillService 
{
	/**
     * 查询推广计划明细信息
     * 
     * @param schedulePlaybillId 推广计划明细ID
     * @return 推广计划明细信息
     */
	public AdSchedulePlaybill selectAdSchedulePlaybillById(Integer schedulePlaybillId);
	
	/**
     * 查询推广计划明细列表
     * 
     * @param adSchedulePlaybill 推广计划明细信息
     * @return 推广计划明细集合
     */
	public List<AdSchedulePlaybill> selectAdSchedulePlaybillList(AdSchedulePlaybill adSchedulePlaybill);
	
	/**
     * 新增推广计划明细
     * 
     * @param adSchedulePlaybill 推广计划明细信息
     * @return 结果
     */
	public int insertAdSchedulePlaybill(AdSchedulePlaybill adSchedulePlaybill);
	
	/**
     * 修改推广计划明细
     * 
     * @param adSchedulePlaybill 推广计划明细信息
     * @return 结果
     */
	public int updateAdSchedulePlaybill(AdSchedulePlaybill adSchedulePlaybill);
		
	/**
     * 删除推广计划明细信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAdSchedulePlaybillByIds(String ids);
	
}
