package com.zxiang.project.advertise.adScheduleRelease.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zxiang.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 投放时间表 zx_ad_schedule_release
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public class AdScheduleRelease extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer scheduleReleaseId;
	/**  */
	private Integer scheduleId;
	/**  */
	private String releaseType;
	/**  */
	private String releasePlaceType;
	/**  */
	private String releaseUserType;
	/**  */
	private Integer releasePlaceId;
	/**  */
	private Integer releaseDeviceId;
	/**  */
	private String isDel;
	/**  */
	private String createBy;
	/**  */
	private Date createTime;

	public void setScheduleReleaseId(Integer scheduleReleaseId) 
	{
		this.scheduleReleaseId = scheduleReleaseId;
	}

	public Integer getScheduleReleaseId() 
	{
		return scheduleReleaseId;
	}
	public void setScheduleId(Integer scheduleId) 
	{
		this.scheduleId = scheduleId;
	}

	public Integer getScheduleId() 
	{
		return scheduleId;
	}
	public void setReleaseType(String releaseType) 
	{
		this.releaseType = releaseType;
	}

	public String getReleaseType() 
	{
		return releaseType;
	}
	public void setReleasePlaceType(String releasePlaceType) 
	{
		this.releasePlaceType = releasePlaceType;
	}

	public String getReleasePlaceType() 
	{
		return releasePlaceType;
	}
	public void setReleaseUserType(String releaseUserType) 
	{
		this.releaseUserType = releaseUserType;
	}

	public String getReleaseUserType() 
	{
		return releaseUserType;
	}
	public void setReleasePlaceId(Integer releasePlaceId) 
	{
		this.releasePlaceId = releasePlaceId;
	}

	public Integer getReleasePlaceId() 
	{
		return releasePlaceId;
	}
	public void setReleaseDeviceId(Integer releaseDeviceId) 
	{
		this.releaseDeviceId = releaseDeviceId;
	}

	public Integer getReleaseDeviceId() 
	{
		return releaseDeviceId;
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

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("scheduleReleaseId", getScheduleReleaseId())
            .append("scheduleId", getScheduleId())
            .append("releaseType", getReleaseType())
            .append("releasePlaceType", getReleasePlaceType())
            .append("releaseUserType", getReleaseUserType())
            .append("releasePlaceId", getReleasePlaceId())
            .append("releaseDeviceId", getReleaseDeviceId())
            .append("isDel", getIsDel())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}
