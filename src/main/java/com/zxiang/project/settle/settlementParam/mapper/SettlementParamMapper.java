package com.zxiang.project.settle.settlementParam.mapper;

import com.zxiang.project.settle.settlementParam.domain.SettlementParam;
import java.util.List;	

/**
 * 结算系数配置 数据层
 * 
 * @author ZXiang
 * @date 2018-09-25
 */
public interface SettlementParamMapper 
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
     * 删除结算系数配置
     * 
     * @param settlementParamId 结算系数配置ID
     * @return 结果
     */
	public int deleteSettlementParamById(Integer settlementParamId);
	
	/**
     * 批量删除结算系数配置
     * 
     * @param settlementParamIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteSettlementParamByIds(String[] settlementParamIds);
	
}