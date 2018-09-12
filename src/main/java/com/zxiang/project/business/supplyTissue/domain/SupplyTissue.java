package com.zxiang.project.business.supplyTissue.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.zxiang.framework.web.domain.BaseEntity;

/**
 * 补纸记录表 zx_supply_tissue
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
public class SupplyTissue extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer supplyTissueId;
	/** 补纸设备 */
	private Integer deviceId;
	/** 补纸数量 */
	private Integer tissueCount;
	/** 补纸场所 */
	private Integer placeId;
	/** 补纸人员 */
	private Integer supplierId;
	/**  */
	private String createBy;
	/**  */
	private Date createTime;

	public void setSupplyTissueId(Integer supplyTissueId) 
	{
		this.supplyTissueId = supplyTissueId;
	}

	public Integer getSupplyTissueId() 
	{
		return supplyTissueId;
	}
	public void setDeviceId(Integer deviceId) 
	{
		this.deviceId = deviceId;
	}

	public Integer getDeviceId() 
	{
		return deviceId;
	}
	public void setTissueCount(Integer tissueCount) 
	{
		this.tissueCount = tissueCount;
	}

	public Integer getTissueCount() 
	{
		return tissueCount;
	}
	public void setPlaceId(Integer placeId) 
	{
		this.placeId = placeId;
	}

	public Integer getPlaceId() 
	{
		return placeId;
	}
	public void setSupplierId(Integer supplierId) 
	{
		this.supplierId = supplierId;
	}

	public Integer getSupplierId() 
	{
		return supplierId;
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

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("supplyTissueId", getSupplyTissueId())
            .append("deviceId", getDeviceId())
            .append("tissueCount", getTissueCount())
            .append("placeId", getPlaceId())
            .append("supplierId", getSupplierId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}
