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
	
	private String deviceCode;
	private String deviceSn;
	private String placeName;
	private String supplierName;
	private String userId;

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

    public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getDeviceSn() {
		return deviceSn;
	}

	public void setDeviceSn(String deviceSn) {
		this.deviceSn = deviceSn;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "SupplyTissue [supplyTissueId=" + supplyTissueId + ", deviceId=" + deviceId + ", tissueCount="
				+ tissueCount + ", placeId=" + placeId + ", supplierId=" + supplierId + ", createBy=" + createBy
				+ ", createTime=" + createTime + ", deviceCode=" + deviceCode + ", deviceSn=" + deviceSn
				+ ", placeName=" + placeName + ", supplierName=" + supplierName + ", userId=" + userId + "]";
	}
}
