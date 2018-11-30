package com.zxiang.project.settle.settlementParam.service;

import com.zxiang.project.settle.settlementParam.domain.SettlementParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 结算系数配置 服务层
 * 
 * @author ZXiang
 * @date 2018-09-25
 */
public interface ISettlementParamService 
{
	/**
     * 查询结算系数配置信息
     * 
     * @param settlementParamId 结算系数配置ID
     * @return 结算系数配置信息
     */
	public SettlementParam selectSettlementParamById(Integer settlementParamId);
	
	/**
     * 查询结算系数配置列表
     * 
     * @param settlementParam 结算系数配置信息
     * @return 结算系数配置集合
     */
	public List<SettlementParam> selectSettlementParamList(SettlementParam settlementParam);
	
	/**
     * 新增结算系数配置
     * 
     * @param settlementParam 结算系数配置信息
     * @return 结果
     */
	public int insertSettlementParam(SettlementParam settlementParam);
	
	/**
     * 修改结算系数配置
     * 
     * @param settlementParam 结算系数配置信息
     * @return 结果
     */
	public int updateSettlementParam(SettlementParam settlementParam);
		
	/**
     * 删除结算系数配置信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSettlementParamByIds(String ids);
	
	
	
	
	/***********************************手机数据接口*****************************************************/
	
	public List<HashMap<String, Object>> selectzxplace(Map<String, Object> map);
	int queryplaceTotal(Map<String, Object> map);
	
	public List<HashMap<String, Object>> selecadschedulelist(Map<String, Object> map);
	int queryadscheduleTotal(Map<String, Object> map);
	
	public List<HashMap<String, Object>> selectzxtissuerecordlist(Map<String, Object> map);
	int querytissuerecordTotal(Map<String, Object> map);
	
	public List<HashMap<String, Object>> selecuserincomelist(Map<String, Object> map);
	int queryuserincomeTotal(Map<String, Object> map);
	
    public List<HashMap<String, Object>> scheduleStatistics(Map<String, Object> map);
	
	public List<HashMap<String, Object>> tissuerecordStatistics(Map<String, Object> map);
	
	public List<HashMap<String, Object>> selecdevicelist(Map<String, Object> map);
	int querydeviceTotal(Map<String, Object> map);
	
    public List<HashMap<String, Object>> selectdeviceAll(Map<String, Object> map);
	
	public List<HashMap<String, Object>> selectplaceAll(Map<String, Object> map);
	
    public int updatedevice(String deviceId,String status);
	
	public int updateplace(String placeId);
	
	public int deviceSave(Map<String, Object> map);
}
