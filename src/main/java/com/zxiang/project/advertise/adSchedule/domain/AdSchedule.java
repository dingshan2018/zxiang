package com.zxiang.project.advertise.adSchedule.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zxiang.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 广告推广计划表 zx_ad_schedule
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public class AdSchedule extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 推广计划ID */
	private Integer scheduleId;
	/**  */
	private String scheduleName;
	/**  */
	private String scheduleType;
	/**  */
	private String scheduleStatus;
	/**  */
	private String isIssued;
	/**  */
	private String isDel;
	/**  */
	private String createBy;
	/**  */
	private Date createTime;
	/**  */
	private String updateBy;
	/**  */
	private Date updateTime;

	public void setScheduleId(Integer scheduleId) 
	{
		this.scheduleId = scheduleId;
	}

	public Integer getScheduleId() 
	{
		return scheduleId;
	}
	public void setScheduleName(String scheduleName) 
	{
		this.scheduleName = scheduleName;
	}

	public String getScheduleName() 
	{
		return scheduleName;
	}
	public void setScheduleType(String scheduleType) 
	{
		this.scheduleType = scheduleType;
	}

	public String getScheduleType() 
	{
		return scheduleType;
	}
	public void setScheduleStatus(String scheduleStatus) 
	{
		this.scheduleStatus = scheduleStatus;
	}

	public String getScheduleStatus() 
	{
		return scheduleStatus;
	}
	public void setIsIssued(String isIssued) 
	{
		this.isIssued = isIssued;
	}

	public String getIsIssued() 
	{
		return isIssued;
	}
	public void setIsDel(String isDel) 
	{
		this.isDel = isDel;
	}

	public String getIsDel() 
	{
		return isDel;
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
            .append("scheduleId", getScheduleId())
            .append("scheduleName", getScheduleName())
            .append("scheduleType", getScheduleType())
            .append("scheduleStatus", getScheduleStatus())
            .append("isIssued", getIsIssued())
            .append("isDel", getIsDel())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
