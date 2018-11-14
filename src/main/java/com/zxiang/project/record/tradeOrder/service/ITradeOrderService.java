package com.zxiang.project.record.tradeOrder.service;

import java.util.List;

import com.zxiang.project.record.tradeOrder.domain.TradeOrder;

/**
 * 订单 服务层
 * 
 * @author ZXiang
 * @date 2018-11-15
 */
public interface ITradeOrderService 
{
	/**
     * 查询订单信息
     * 
     * @param tradeOrderId 订单ID
     * @return 订单信息
     */
	public TradeOrder selectTradeOrderById(Integer tradeOrderId);
	
	/**
     * 查询订单列表
     * 
     * @param tradeOrder 订单信息
     * @return 订单集合
     */
	public List<TradeOrder> selectTradeOrderList(TradeOrder tradeOrder);
	
	/**
     * 新增订单
     * 
     * @param tradeOrder 订单信息
     * @return 结果
     */
	public int insertTradeOrder(TradeOrder tradeOrder);
	
	/**
     * 修改订单
     * 
     * @param tradeOrder 订单信息
     * @return 结果
     */
	public int updateTradeOrder(TradeOrder tradeOrder);
		
	/**
     * 删除订单信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTradeOrderByIds(String ids);
	
}
