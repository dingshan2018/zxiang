package com.zxiang.project.client.fundLog.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zxiang.project.client.fundLog.domain.WithdrawDeposit;

/**
 * 提现记录 服务层
 * 
 * @author ZXiang
 * @date 2018-12-07
 */
public interface IWithdrawDepositService 
{
	/**
     * 查询提现记录信息
     * 
     * @param id 提现记录ID
     * @return 提现记录信息
     */
	public WithdrawDeposit selectWithdrawDepositById(Integer id);
	
	/**
     * 查询提现记录列表
     * 
     * @param withdrawDeposit 提现记录信息
     * @return 提现记录集合
     */
	public List<WithdrawDeposit> selectWithdrawDepositList(WithdrawDeposit withdrawDeposit);
	
	/**
     * 新增提现记录
     * 
     * @param withdrawDeposit 提现记录信息
     * @return 结果
     */
	public int insertWithdrawDeposit(WithdrawDeposit withdrawDeposit);
	
	/**
     * 修改提现记录
     * 
     * @param withdrawDeposit 提现记录信息
     * @return 结果
     */
	public int updateWithdrawDeposit(WithdrawDeposit withdrawDeposit);
		
	/**
     * 删除提现记录信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWithdrawDepositByIds(String ids);

	public void queryExport(Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
