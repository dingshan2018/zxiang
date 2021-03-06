package com.zxiang.project.record.deviceOrder.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zxiang.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 设备销售订单表 zx_device_order
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public class DeviceOrder extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer deviceOrderId;
	/** 设备ID */
	private Integer deviceId;
	/** 终端ID */
	private Integer terminalId;
	/** 推荐人ID */
	private Integer promotionerId;
	/** 售价 */
	private Double price;
	/** 折扣 */
	private Float cutOff;
	/** 销售员ID */
	private Integer sellerId;
	/** 状态 */
	private String status;
	/** 机主ID */
	private Integer buyerId;
	/** 机主名称 */
	private String buyerName;
	/** 机主openID */
	private String buyerOpenId;
	/**  */
	private String createBy;
	/**  */
	private Date createTime;
	/**  */
	private String updateBy;
	/**  */
	private Date updateTime;

	public void setDeviceOrderId(Integer deviceOrderId) 
	{
		this.deviceOrderId = deviceOrderId;
	}

	public Integer getDeviceOrderId() 
	{
		return deviceOrderId;
	}
	public void setDeviceId(Integer deviceId) 
	{
		this.deviceId = deviceId;
	}

	public Integer getDeviceId() 
	{
		return deviceId;
	}
	public void setTerminalId(Integer terminalId) 
	{
		this.terminalId = terminalId;
	}

	public Integer getTerminalId() 
	{
		return terminalId;
	}
	public void setPromotionerId(Integer promotionerId) 
	{
		this.promotionerId = promotionerId;
	}

	public Integer getPromotionerId() 
	{
		return promotionerId;
	}
	public void setPrice(Double price) 
	{
		this.price = price;
	}

	public Double getPrice() 
	{
		return price;
	}
	public void setCutOff(Float cutOff) 
	{
		this.cutOff = cutOff;
	}

	public Float getCutOff() 
	{
		return cutOff;
	}
	public void setSellerId(Integer sellerId) 
	{
		this.sellerId = sellerId;
	}

	public Integer getSellerId() 
	{
		return sellerId;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setBuyerId(Integer buyerId) 
	{
		this.buyerId = buyerId;
	}

	public Integer getBuyerId() 
	{
		return buyerId;
	}
	public void setBuyerName(String buyerName) 
	{
		this.buyerName = buyerName;
	}

	public String getBuyerName() 
	{
		return buyerName;
	}
	public void setBuyerOpenId(String buyerOpenId) 
	{
		this.buyerOpenId = buyerOpenId;
	}

	public String getBuyerOpenId() 
	{
		return buyerOpenId;
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

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("deviceOrderId", getDeviceOrderId())
            .append("deviceId", getDeviceId())
            .append("terminalId", getTerminalId())
            .append("promotionerId", getPromotionerId())
            .append("price", getPrice())
            .append("cutOff", getCutOff())
            .append("sellerId", getSellerId())
            .append("status", getStatus())
            .append("buyerId", getBuyerId())
            .append("buyerName", getBuyerName())
            .append("buyerOpenId", getBuyerOpenId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
