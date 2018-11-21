package com.zxiang.project.record.tradeOrder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zxiang.project.record.tradeOrder.domain.TradeOrder;	

/**
 * 订单 数据层
 * 
 * @author ZXiang
 * @date 2018-11-15
 */
public interface TradeOrderMapper 
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
     * 删除订单
     * 
     * @param tradeOrderId 订单ID
     * @return 结果
     */
	public int deleteTradeOrderById(Integer tradeOrderId);
	
	/**
     * 批量删除订单
     * 
     * @param tradeOrderIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteTradeOrderByIds(String[] tradeOrderIds);

	/**
	 *  根据机主ID查询未发货或部分发货的订单且支付完成且订单类型为购机订单的数据
	 * @return
	 */
	public List<TradeOrder> selectUnSendList(@Param("userId")Integer userId);
	
}