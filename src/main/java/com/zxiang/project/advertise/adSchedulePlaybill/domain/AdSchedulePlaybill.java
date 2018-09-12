package com.zxiang.project.advertise.adSchedulePlaybill.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zxiang.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 推广计划明细表 zx_ad_schedule_playbill
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public class AdSchedulePlaybill extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer schedulePlaybillId;
	/**  */
	private Integer playbillId;
	/**  */
	private String isDel;
	/**  */
	private String createBy;
	/**  */
	private Date createTime;

	public void setSchedulePlaybillId(Integer schedulePlaybillId) 
	{
		this.schedulePlaybillId = schedulePlaybillId;
	}

	public Integer getSchedulePlaybillId() 
	{
		return schedulePlaybillId;
	}
	public void setPlaybillId(Integer playbillId) 
	{
		this.playbillId = playbillId;
	}

	public Integer getPlaybillId() 
	{
		return playbillId;
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
            .append("schedulePlaybillId", getSchedulePlaybillId())
            .append("playbillId", getPlaybillId())
            .append("isDel", getIsDel())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}
