package com.zxiang.project.client.fundLog.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zxiang.common.support.Convert;
import com.zxiang.project.client.fundLog.domain.WithdrawDeposit;
import com.zxiang.project.client.fundLog.mapper.WithdrawDepositMapper;

/**
 * 提现记录 服务层实现
 * 
 * @author ZXiang
 * @date 2018-12-07
 */
@Service
public class WithdrawDepositServiceImpl implements IWithdrawDepositService 
{
	@Autowired
	private WithdrawDepositMapper withdrawDepositMapper;
	@Autowired
	private IFundLogService fundLogService;

	/**
     * 查询提现记录信息
     * 
     * @param id 提现记录ID
     * @return 提现记录信息
     */
    @Override
	public WithdrawDeposit selectWithdrawDepositById(Integer id)
	{
	    return withdrawDepositMapper.selectWithdrawDepositById(id);
	}
	
	/**
     * 查询提现记录列表
     * 
     * @param withdrawDeposit 提现记录信息
     * @return 提现记录集合
     */
	@Override
	public List<WithdrawDeposit> selectWithdrawDepositList(WithdrawDeposit withdrawDeposit)
	{
	    return withdrawDepositMapper.selectWithdrawDepositList(withdrawDeposit);
	}
	
    /**
     * 新增提现记录
     * 
     * @param withdrawDeposit 提现记录信息
     * @return 结果
     */
	@Override
	public int insertWithdrawDeposit(WithdrawDeposit withdrawDeposit)
	{
	    return withdrawDepositMapper.insertWithdrawDeposit(withdrawDeposit);
	}
	
	/**
     * 修改提现记录
     * 
     * @param withdrawDeposit 提现记录信息
     * @return 结果
     */
	@Override
	@Transactional
	public int updateWithdrawDeposit(WithdrawDeposit withdrawDeposit){
		fundLogService.sureClientWithdraw(withdrawDeposit.getId(),withdrawDeposit.getStatus(),withdrawDeposit.getRemark());
		withdrawDeposit.setUpdateTime(new Date());
		withdrawDeposit.setPayTime(new Date());
	    return withdrawDepositMapper.updateWithdrawDeposit(withdrawDeposit);
	}

	/**
     * 删除提现记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWithdrawDepositByIds(String ids)
	{
		return withdrawDepositMapper.deleteWithdrawDepositByIds(Convert.toStrArray(ids));
	}
	
}
