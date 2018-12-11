package com.zxiang.project.record.tradeOrder.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.zxiang.framework.web.domain.BaseEntity;

/**
 * 订单表 zx_trade_order
 * 
 * @author ZXiang
 * @date 2018-11-15
 */
public class TradeOrder extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Integer tradeOrderId;
	/** 商户订单号 */
	private String outTradeOrder;
	/** 交易订单号 */
	private String transactionId;
	/** 退款订单号 */
	private String refundId;
	/** 机主微信账号 */
	private String openId;
	/** 机主id */
	private Integer userId;
	/** 机主 */
	private String userName;
	/** 支付金额 */
	private Double totalFee;
	/** 订单数量 */
	private Integer totalCnt;
	/** 订单类型（1 购机订单  2 广告订单 ） */
	private String orderType;
	/** 订单状态（0 待支付 1 已支付 2 支付失败 3 已退款 4 退款失败） */
	private String orderStatus;
	/** 发货状态（0 未发货 1 已发货 2 部分发货 ） */
	private String sendStatus;
	/** 订单备注 */
	private String remark;
	/** 支付时间 */
	private String payTime;
	/**  */
	private String createBy;
	/**  */
	private Date createTime;
	/**  */
	private String updateBy;
	/**  */
	private Date updateTime;

	public void setTradeOrderId(Integer tradeOrderId) 
	{
		this.tradeOrderId = tradeOrderId;
	}

	public Integer getTradeOrderId() 
	{
		return tradeOrderId;
	}
	public void setOutTradeOrder(String outTradeOrder) 
	{
		this.outTradeOrder = outTradeOrder;
	}

	public String getOutTradeOrder() 
	{
		return outTradeOrder;
	}
	public void setTransactionId(String transactionId) 
	{
		this.transactionId = transactionId;
	}

	public String getTransactionId() 
	{
		return transactionId;
	}
	public void setRefundId(String refundId) 
	{
		this.refundId = refundId;
	}

	public String getRefundId() 
	{
		return refundId;
	}
	public void setOpenId(String openId) 
	{
		this.openId = openId;
	}

	public String getOpenId() 
	{
		return openId;
	}
	public void setUserId(Integer userId) 
	{
		this.userId = userId;
	}

	public Integer getUserId() 
	{
		return userId;
	}
	public void setTotalFee(Double totalFee) 
	{
		this.totalFee = totalFee;
	}

	public Double getTotalFee() 
	{
		return totalFee;
	}
	public void setTotalCnt(Integer totalCnt) 
	{
		this.totalCnt = totalCnt;
	}

	public Integer getTotalCnt() 
	{
		return totalCnt;
	}
	public void setOrderType(String orderType) 
	{
		this.orderType = orderType;
	}

	public String getOrderType() 
	{
		return orderType;
	}
	public void setOrderStatus(String orderStatus) 
	{
		this.orderStatus = orderStatus;
	}

	public String getOrderStatus() 
	{
		return orderStatus;
	}
	public void setSendStatus(String sendStatus) 
	{
		this.sendStatus = sendStatus;
	}

	public String getSendStatus() 
	{
		return sendStatus;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}
	public void setPayTime(String payTime) 
	{
		this.payTime = payTime;
	}

	public String getPayTime() 
	{
		return payTime;
	}
	public void setCreateBy(String createBy) 
	{
		this.createBy = createBy;
	}

	public String getCreateBy() 
	{
		return createBy;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setUpdateBy(String updateBy) 
	{
		this.updateBy = updateBy;
	}

	public String getUpdateBy() 
	{
		return updateBy;
	}
	public void setUpdateTime(Date updateTime) 
	{
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() 
	{
		return updateTime;
	}

    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("tradeOrderId", getTradeOrderId())
            .append("outTradeOrder", getOutTradeOrder())
            .append("transactionId", getTransactionId())
            .append("refundId", getRefundId())
            .append("openId", getOpenId())
            .append("userId", getUserId())
            .append("totalFee", getTotalFee())
            .append("totalCnt", getTotalCnt())
            .append("orderType", getOrderType())
            .append("orderStatus", getOrderStatus())
            .append("sendStatus", getSendStatus())
            .append("remark", getRemark())
            .append("payTime", getPayTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
