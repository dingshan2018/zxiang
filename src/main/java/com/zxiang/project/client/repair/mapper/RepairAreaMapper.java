package com.zxiang.project.client.repair.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zxiang.project.client.repair.domain.RepairArea;	

/**
 * 服务网点地区 服务商 数据层 
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public interface RepairAreaMapper  {
	
	/**
     * 查询服务网点地区列表
     */
	public List<RepairArea> selectRepairAreaList(@Param("repairId")Integer repairId);

	public int saveRepairArea(RepairArea repairArea);

	public void deleteRepairArea(@Param("repairAreaId")Integer repairAreaId);
	
}