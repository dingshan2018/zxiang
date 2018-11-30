package com.zxiang.project.settle.settlementParam.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zxiang.project.settle.settlementParam.mapper.SettlementParamMapper;
import com.zxiang.project.settle.settlementParam.domain.SettlementParam;
import com.zxiang.project.settle.settlementParam.service.ISettlementParamService;
import com.zxiang.common.support.Convert;

/**
 * 结算系数配置 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-25
 */
@Service
public class SettlementParamServiceImpl implements ISettlementParamService 
{
	@Autowired
	private SettlementParamMapper settlementParamMapper;

	/**
     * 查询结算系数配置信息
     * 
     * @param settlementParamId 结算系数配置ID
     * @return 结算系数配置信息
     */
    @Override
	public SettlementParam selectSettlementParamById(Integer settlementParamId)
	{
	    return settlementParamMapper.selectSettlementParamById(settlementParamId);
	}
	
	/**
     * 查询结算系数配置列表
     * 
     * @param settlementParam 结算系数配置信息
     * @return 结算系数配置集合
     */
	@Override
	public List<SettlementParam> selectSettlementParamList(SettlementParam settlementParam)
	{
	    return settlementParamMapper.selectSettlementParamList(settlementParam);
	}
	
    /**
     * 新增结算系数配置
     * 
     * @param settlementParam 结算系数配置信息
     * @return 结果
     */
	@Override
	public int insertSettlementParam(SettlementParam settlementParam)
	{
	    return settlementParamMapper.insertSettlementParam(settlementParam);
	}
	
	/**
     * 修改结算系数配置
     * 
     * @param settlementParam 结算系数配置信息
     * @return 结果
     */
	@Override
	public int updateSettlementParam(SettlementParam settlementParam)
	{
	    return settlementParamMapper.updateSettlementParam(settlementParam);
	}

	/**
     * 删除结算系数配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSettlementParamByIds(String ids)
	{
		return settlementParamMapper.deleteSettlementParamByIds(Convert.toStrArray(ids));
	}

	
	
	
	
	/***********************************手机数据接口*****************************************************/
	
	
	@Override
	public List<HashMap<String, Object>> selectzxplace(Map<String, Object> map) {
		return settlementParamMapper.selectzxplace(map);
	}
	
	@Override
	public int queryplaceTotal(Map<String, Object> map) {
		return settlementParamMapper.queryplaceTotal(map);
	}

	@Override
	public List<HashMap<String, Object>> selecadschedulelist(Map<String, Object> map) {
		return settlementParamMapper.selecadschedulelist(map);
	}

	@Override
	public List<HashMap<String, Object>> selectzxtissuerecordlist(Map<String, Object> map) {
		return settlementParamMapper.selectzxtissuerecordlist(map);
	}

	@Override
	public int queryadscheduleTotal(Map<String, Object> map) {
		return settlementParamMapper.queryadscheduleTotal(map);
	}

	@Override
	public int querytissuerecordTotal(Map<String, Object> map) {
		return settlementParamMapper.querytissuerecordTotal(map);
	}
	
	
	@Override
	public List<HashMap<String, Object>> selecuserincomelist(Map<String, Object> map) {
		return settlementParamMapper.selecuserincomelist(map);
	}
	
	@Override
	public int queryuserincomeTotal(Map<String, Object> map) {
		return settlementParamMapper.queryuserincomeTotal(map);
	}

	@Override
	public List<HashMap<String, Object>> scheduleStatistics(Map<String, Object> map) {
		return settlementParamMapper.scheduleStatistics(map);
	}

	@Override
	public List<HashMap<String, Object>> tissuerecordStatistics(Map<String, Object> map) {
		return settlementParamMapper.tissuerecordStatistics(map);
	}

	@Override
	public List<HashMap<String, Object>> selecdevicelist(Map<String, Object> map) {
		return settlementParamMapper.selecdevicelist(map);
	}

	@Override
	public int querydeviceTotal(Map<String, Object> map) {
		return settlementParamMapper.querydeviceTotal(map);
	}

	@Override
	public List<HashMap<String, Object>> selectdeviceAll(Map<String, Object> map) {
		return settlementParamMapper.selectdeviceAll(map);
	}

	@Override
	public List<HashMap<String, Object>> selectplaceAll(Map<String, Object> map) {
		return settlementParamMapper.selectplaceAll(map);
	}

	@Override
	public int updatedevice(String deviceId,String status) {
		return settlementParamMapper.updatedevice(deviceId,status);
	}

	@Override
	public int updateplace(String placeId) {
		return settlementParamMapper.updateplace(placeId);
	}

	@Override
	public int deviceSave(Map<String, Object> map) {
		int t=updatedevice(map.get("deviceId")+"","02");
		if(t==0) {
			return 0;
		}
		int i=updateplace(map.get("placeId")+"");
		if(i==0) {
			return 0;
		}
		return 1;
	}

}
