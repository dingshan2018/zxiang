package com.zxiang.project.advertise.adSchedule.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zxiang.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 广告投放表 zx_ad_schedule
 * 
 * @author ZXiang
 * @date 2018-09-24
 */
public class AdSchedule extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer adScheduleId;
	/** 投放名称 */
	private String scheduleName;
	/** 投放方式 */
	private String releaseType;
	/** 投放位置 */
	private String releasePosition;
	/** 视讯排期ID */
	private Integer sxScheduleId;
	/**  */
	private String status;
	/** 总价 */
	private Float totalPay;
	/** 押金 */
	private Float prepay;
	/** 投放终端数 */
	private Integer releaseTermNum;
	/**  */
	private String isDel;
	/** 投放备注 */
	private String releaseNote;
	/**  */
	private String createBy;
	/**  */
	private Date createTime;
	/**  */
	private String updateBy;
	/**  */
	private Date updateTime;
	/**节目单ID  */
	private String pId;

	private String releaseTypeName;
	private String releasePositionName;
	private String statusName;
	
	public void setAdScheduleId(Integer adScheduleId) 
	{
		this.adScheduleId = adScheduleId;
	}

	public Integer getAdScheduleId() 
	{
		return adScheduleId;
	}
	public void setScheduleName(String scheduleName) 
	{
		this.scheduleName = scheduleName;
	}

	public String getScheduleName() 
	{
		return scheduleName;
	}
	public void setReleaseType(String releaseType) 
	{
		this.releaseType = releaseType;
	}

	public String getReleaseType() 
	{
		return releaseType;
	}
	public void setReleasePosition(String releasePosition) 
	{
		this.releasePosition = releasePosition;
	}

	public String getReleasePosition() 
	{
		return releasePosition;
	}
	public void setSxScheduleId(Integer sxScheduleId) 
	{
		this.sxScheduleId = sxScheduleId;
	}

	public Integer getSxScheduleId() 
	{
		return sxScheduleId;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setTotalPay(Float totalPay) 
	{
		this.totalPay = totalPay;
	}

	public Float getTotalPay() 
	{
		return totalPay;
	}
	public void setPrepay(Float prepay) 
	{
		this.prepay = prepay;
	}

	public Float getPrepay() 
	{
		return prepay;
	}
	public void setReleaseTermNum(Integer releaseTermNum) 
	{
		this.releaseTermNum = releaseTermNum;
	}

	public Integer getReleaseTermNum() 
	{
		return releaseTermNum;
	}
	public void setIsDel(String isDel) 
	{
		this.isDel = isDel;
	}

	public String getIsDel() 
	{
		return isDel;
	}
	public void setReleaseNote(String releaseNote) 
	{
		this.releaseNote = releaseNote;
	}

	public String getReleaseNote() 
	{
		return releaseNote;
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

    public String getReleaseTypeName() {
		return releaseTypeName;
	}

	public void setReleaseTypeName(String releaseTypeName) {
		this.releaseTypeName = releaseTypeName;
	}

	public String getReleasePositionName() {
		return releasePositionName;
	}

	public void setReleasePositionName(String releasePositionName) {
		this.releasePositionName = releasePositionName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("adScheduleId", getAdScheduleId())
            .append("scheduleName", getScheduleName())
            .append("releaseType", getReleaseType())
            .append("releasePosition", getReleasePosition())
            .append("sxScheduleId", getSxScheduleId())
            .append("status", getStatus())
            .append("totalPay", getTotalPay())
            .append("prepay", getPrepay())
            .append("releaseTermNum", getReleaseTermNum())
            .append("isDel", getIsDel())
            .append("releaseNote", getReleaseNote())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
