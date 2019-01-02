package com.zxiang.project.business.deviceReleaseAudit.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zxiang.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 设备投放审核表 zx_device_release_audit
 * 
 * @author ZXiang
 * @date 2018-12-12
 */
public class DeviceReleaseAudit extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键ID */
	private Integer auditId;
	/** 投放设备 */
	private Integer deviceId;
	/** 投放场所 */
	private Integer placeId;
	/** 审核结果0待审核;1审核通过;2不通过 */
	private String approved;
	/** 审核意见 */
	private String approvedRemark;
	/** 审核人员 */
	private String approvedUser;
	/** 审核时间 */
	private Date auditDate;
	/** 创建时间 */
	private Date createDate;
	/** 是否删除 */
	private String delFlag;
	
	
	private String deviceSnName;
	private String placeName;
	private String approvedStatus;
	private String deviceSn;
	
	public void setAuditId(Integer auditId) 
	{
		this.auditId = auditId;
	}

	public Integer getAuditId() 
	{
		return auditId;
	}
	public void setDeviceId(Integer deviceId) 
	{
		this.deviceId = deviceId;
	}

	public Integer getDeviceId() 
	{
		return deviceId;
	}
	public void setPlaceId(Integer placeId) 
	{
		this.placeId = placeId;
	}

	public Integer getPlaceId() 
	{
		return placeId;
	}
	public void setApproved(String approved) 
	{
		this.approved = approved;
	}

	public String getApproved() 
	{
		return approved;
	}
	public void setApprovedRemark(String approvedRemark) 
	{
		this.approvedRemark = approvedRemark;
	}

	public String getApprovedRemark() 
	{
		return approvedRemark;
	}
	public void setApprovedUser(String approvedUser) 
	{
		this.approvedUser = approvedUser;
	}

	public String getApprovedUser() 
	{
		return approvedUser;
	}
	public void setAuditDate(Date auditDate) 
	{
		this.auditDate = auditDate;
	}

	public Date getAuditDate() 
	{
		return auditDate;
	}
	public void setCreateDate(Date createDate) 
	{
		this.createDate = createDate;
	}

	public Date getCreateDate() 
	{
		return createDate;
	}
	public void setDelFlag(String delFlag) 
	{
		this.delFlag = delFlag;
	}

	public String getDelFlag() 
	{
		return delFlag;
	}

    public String getDeviceSnName() {
		return deviceSnName;
	}

	public void setDeviceSnName(String deviceSnName) {
		this.deviceSnName = deviceSnName;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getApprovedStatus() {
		return approvedStatus;
	}

	public void setApprovedStatus(String approvedStatus) {
		this.approvedStatus = approvedStatus;
	}

	public String getDeviceSn() {
		return deviceSn;
	}

	public void setDeviceSn(String deviceSn) {
		this.deviceSn = deviceSn;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("auditId", getAuditId())
            .append("deviceId", getDeviceId())
            .append("placeId", getPlaceId())
            .append("approved", getApproved())
            .append("approvedRemark", getApprovedRemark())
            .append("approvedUser", getApprovedUser())
            .append("auditDate", getAuditDate())
            .append("createDate", getCreateDate())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
