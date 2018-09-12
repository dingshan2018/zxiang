package com.zxiang.project.client.repair.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zxiang.project.client.repair.mapper.RepairMapper;
import com.zxiang.project.client.repair.domain.Repair;
import com.zxiang.project.client.repair.service.IRepairService;
import com.zxiang.common.support.Convert;

/**
 * 服务商 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Service
public class RepairServiceImpl implements IRepairService 
{
	@Autowired
	private RepairMapper repairMapper;

	/**
     * 查询服务商信息
     * 
     * @param repairId 服务商ID
     * @return 服务商信息
     */
    @Override
	public Repair selectRepairById(Integer repairId)
	{
	    return repairMapper.selectRepairById(repairId);
	}
	
	/**
     * 查询服务商列表
     * 
     * @param repair 服务商信息
     * @return 服务商集合
     */
	@Override
	public List<Repair> selectRepairList(Repair repair)
	{
	    return repairMapper.selectRepairList(repair);
	}
	
    /**
     * 新增服务商
     * 
     * @param repair 服务商信息
     * @return 结果
     */
	@Override
	public int insertRepair(Repair repair)
	{
	    return repairMapper.insertRepair(repair);
	}
	
	/**
     * 修改服务商
     * 
     * @param repair 服务商信息
     * @return 结果
     */
	@Override
	public int updateRepair(Repair repair)
	{
	    return repairMapper.updateRepair(repair);
	}

	/**
     * 删除服务商对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteRepairByIds(String ids)
	{
		return repairMapper.deleteRepairByIds(Convert.toStrArray(ids));
	}
	
}
