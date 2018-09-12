package com.zxiang.project.client.repair.service;

import com.zxiang.project.client.repair.domain.Repair;
import java.util.List;

/**
 * 服务商 服务层
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public interface IRepairService 
{
	/**
     * 查询服务商信息
     * 
     * @param repairId 服务商ID
     * @return 服务商信息
     */
	public Repair selectRepairById(Integer repairId);
	
	/**
     * 查询服务商列表
     * 
     * @param repair 服务商信息
     * @return 服务商集合
     */
	public List<Repair> selectRepairList(Repair repair);
	
	/**
     * 新增服务商
     * 
     * @param repair 服务商信息
     * @return 结果
     */
	public int insertRepair(Repair repair);
	
	/**
     * 修改服务商
     * 
     * @param repair 服务商信息
     * @return 结果
     */
	public int updateRepair(Repair repair);
		
	/**
     * 删除服务商信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteRepairByIds(String ids);
	
}
