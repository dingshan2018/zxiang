package com.zxiang.project.record.scheduleOrder.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.zxiang.framework.web.domain.BaseEntity;

/**
 * 排期订单表 zx_schedule_order
 * 
 * @author ZXiang
 * @date 2018-11-15
 */
public class ScheduleOrder extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer scheduleOrderId;
	/** 排期ID */
	private Integer scheduleId;
	/** 订单ID */
	private Integer tradeOrderId;
	/** 订单状态 */
	private String orderStatus;
	/**  */
	private String createBy;
	// 排期投放名称
	private String scheduleName;
	/**  */
	private Date createTime;
	private String outTradeOrder;

	public void setScheduleOrderId(Integer scheduleOrderId) 
	{
		this.scheduleOrderId = scheduleOrderId;
	}

	public Integer getScheduleOrderId() 
	{
		return scheduleOrderId;
	}
	public void setScheduleId(Integer scheduleId) 
	{
		this.scheduleId = scheduleId;
	}

	public Integer getScheduleId() 
	{
		return scheduleId;
	}
	public void setTradeOrderId(Integer tradeOrderId) 
	{
		this.tradeOrderId = tradeOrderId;
	}

	public Integer getTradeOrderId() 
	{
		return tradeOrderId;
	}
	public void setOrderStatus(String orderStatus) 
	{
		this.orderStatus = orderStatus;
	}

	public String getOrderStatus() 
	{
		return orderStatus;
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

	public String getOutTradeOrder() {
		return outTradeOrder;
	}

	public void setOutTradeOrder(String outTradeOrder) {
		this.outTradeOrder = outTradeOrder;
	}

	public String getScheduleName() {
		return scheduleName;
	}

	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("scheduleOrderId", getScheduleOrderId())
            .append("scheduleId", getScheduleId())
            .append("tradeOrderId", getTradeOrderId())
            .append("orderStatus", getOrderStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}
