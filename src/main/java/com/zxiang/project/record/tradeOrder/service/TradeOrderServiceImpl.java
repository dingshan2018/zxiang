package com.zxiang.project.record.tradeOrder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxiang.common.support.Convert;
import com.zxiang.project.record.tradeOrder.domain.TradeOrder;
import com.zxiang.project.record.tradeOrder.mapper.TradeOrderMapper;

/**
 * 订单 服务层实现
 * 
 * @author ZXiang
 * @date 2018-11-15
 */
@Service
public class TradeOrderServiceImpl implements ITradeOrderService 
{
	@Autowired
	private TradeOrderMapper tradeOrderMapper;

	/**
     * 查询订单信息
     * 
     * @param tradeOrderId 订单ID
     * @return 订单信息
     */
    @Override
	public TradeOrder selectTradeOrderById(Integer tradeOrderId)
	{
	    return tradeOrderMapper.selectTradeOrderById(tradeOrderId);
	}
	
	/**
     * 查询订单列表
     * 
     * @param tradeOrder 订单信息
     * @return 订单集合
     */
	@Override
	public List<TradeOrder> selectTradeOrderList(TradeOrder tradeOrder)
	{
	    return tradeOrderMapper.selectTradeOrderList(tradeOrder);
	}
	
    /**
     * 新增订单
     * 
     * @param tradeOrder 订单信息
     * @return 结果
     */
	@Override
	public int insertTradeOrder(TradeOrder tradeOrder)
	{
	    return tradeOrderMapper.insertTradeOrder(tradeOrder);
	}
	
	/**
     * 修改订单
     * 
     * @param tradeOrder 订单信息
     * @return 结果
     */
	@Override
	public int updateTradeOrder(TradeOrder tradeOrder)
	{
	    return tradeOrderMapper.updateTradeOrder(tradeOrder);
	}

	/**
     * 删除订单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTradeOrderByIds(String ids)
	{
		return tradeOrderMapper.deleteTradeOrderByIds(Convert.toStrArray(ids));
	}
	
}
