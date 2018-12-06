package com.zxiang.project.client.fundLog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxiang.common.support.Convert;
import com.zxiang.project.client.fundLog.domain.FundLog;
import com.zxiang.project.client.fundLog.mapper.FundLogMapper;

/**
 * 资金流水 服务层实现
 * 
 * @author ZXiang
 * @date 2018-12-05
 */
@Service
public class FundLogServiceImpl implements IFundLogService 
{
	@Autowired
	private FundLogMapper fundLogMapper;

	/**
     * 查询资金流水信息
     * 
     * @param payId 资金流水ID
     * @return 资金流水信息
     */
    @Override
	public FundLog selectFundLogById(Integer payId)
	{
	    return fundLogMapper.selectFundLogById(payId);
	}
	
	/**
     * 查询资金流水列表
     * 
     * @param fundLog 资金流水信息
     * @return 资金流水集合
     */
	@Override
	public List<FundLog> selectFundLogList(FundLog fundLog)
	{
	    return fundLogMapper.selectFundLogList(fundLog);
	}
	
    /**
     * 新增资金流水
     * 
     * @param fundLog 资金流水信息
     * @return 结果
     */
	@Override
	public int insertFundLog(FundLog fundLog)
	{
	    return fundLogMapper.insertFundLog(fundLog);
	}
	
	/**
     * 修改资金流水
     * 
     * @param fundLog 资金流水信息
     * @return 结果
     */
	@Override
	public int updateFundLog(FundLog fundLog)
	{
	    return fundLogMapper.updateFundLog(fundLog);
	}

	/**
     * 删除资金流水对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteFundLogByIds(String ids)
	{
		return fundLogMapper.deleteFundLogByIds(Convert.toStrArray(ids));
	}
	
}
