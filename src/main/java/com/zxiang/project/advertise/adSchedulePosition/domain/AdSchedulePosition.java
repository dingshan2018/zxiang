package com.zxiang.project.advertise.adSchedulePosition.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zxiang.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 投放位置表 zx_ad_schedule_position
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public class AdSchedulePosition extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer schedulePositionId;
	/**  */
	private Integer scheduleId;
	/**  */
	private String position;
	/**  */
	private String createBy;
	/**  */
	private Date createTime;

	public void setSchedulePositionId(Integer schedulePositionId) 
	{
		this.schedulePositionId = schedulePositionId;
	}

	public Integer getSchedulePositionId() 
	{
		return schedulePositionId;
	}
	public void setScheduleId(Integer scheduleId) 
	{
		this.scheduleId = scheduleId;
	}

	public Integer getScheduleId() 
	{
		return scheduleId;
	}
	public void setPosition(String position) 
	{
		this.position = position;
	}

	public String getPosition() 
	{
		return position;
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
            .append("schedulePositionId", getSchedulePositionId())
            .append("scheduleId", getScheduleId())
            .append("position", getPosition())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}
