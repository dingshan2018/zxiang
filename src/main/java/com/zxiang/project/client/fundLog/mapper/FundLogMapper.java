package com.zxiang.project.client.fundLog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zxiang.project.client.fundLog.domain.FundLog;	

/**
 * 资金流水 数据层
 * 
 * @author ZXiang
 * @date 2018-12-05
 */
public interface FundLogMapper 
{
	/**
     * 查询资金流水信息
     * 
     * @param payId 资金流水ID
     * @return 资金流水信息
     */
	public FundLog selectFundLogById(Integer payId);
	
	/**
     * 查询资金流水列表
     * 
     * @param fundLog 资金流水信息
     * @return 资金流水集合
     */
	public List<FundLog> selectFundLogList(FundLog fundLog);
	
	/**
	 * 查詢錯誤流水
	 * @param fundLog
	 * @return
	 */
	public List<FundLog> selectWrongFundLogList(FundLog fundLog);
	
	/**
     * 新增资金流水
     * 
     * @param fundLog 资金流水信息
     * @return 结果
     */
	public int insertFundLog(FundLog fundLog);
	
	/**
     * 修改资金流水
     * 
     * @param fundLog 资金流水信息
     * @return 结果
     */
	public int updateFundLog(FundLog fundLog);
	
	/**
     * 删除资金流水
     * 
     * @param payId 资金流水ID
     * @return 结果
     */
	public int deleteFundLogById(Integer payId);
	
	/**
     * 批量删除资金流水
     * 
     * @param payIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteFundLogByIds(String[] payIds);
	
	public int updateStatus(@Param("payId")Integer payId,@Param("status")String status,@Param("remark")String remark);
	
	/**
	 * 删除错误流水
	 * @param fundLog
	 * @return
	 */
	public int deleteWrongFundLog(FundLog fundLog);
	
	/**
	 * 取出昨天最后一条流水
	 * @param fundLog
	 * @return
	 */
	public FundLog selectLastFundLog(FundLog fundLog); 
	
	/**
	 * 调整流水
	 * @param fundLog
	 * @return
	 */
	public int recalcFundLog(FundLog fundLog);
	
}