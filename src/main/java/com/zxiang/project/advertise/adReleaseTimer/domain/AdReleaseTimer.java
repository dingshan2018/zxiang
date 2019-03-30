package com.zxiang.project.advertise.adReleaseTimer.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zxiang.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 广告投放时段表 zx_ad_release_timer
 * 
 * @author ZXiang
 * @date 2018-11-07
 */
public class AdReleaseTimer extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer adReleaseTimerId;
	/** 广告投放ID */
	private Integer adScheduleId;
	/** 投放开始时间 */
	private Date releaseBeginTime;
	/** 投放结束时间 */
	private Date releaseEndTime;
	/** 备注 */
	private String remark;
	/** 创建者 */
	private String createBy;
	/** 创建时间 */
	private Date createTime;
	/** 修改者 */
	private String updateBy;
	/** 修改时间 */
	private Date updateTime;
	
	private String adScheduleName;
	
	private String beginTime;
	
	private String endTime;

	public void setAdReleaseTimerId(Integer adReleaseTimerId) 
	{
		this.adReleaseTimerId = adReleaseTimerId;
	}

	public Integer getAdReleaseTimerId() 
	{
		return adReleaseTimerId;
	}
	public void setAdScheduleId(Integer adScheduleId) 
	{
		this.adScheduleId = adScheduleId;
	}

	public Integer getAdScheduleId() 
	{
		return adScheduleId;
	}
	public void setReleaseBeginTime(Date releaseBeginTime) 
	{
		this.releaseBeginTime = releaseBeginTime;
	}

	public Date getReleaseBeginTime() 
	{
		return releaseBeginTime;
	}
	public void setReleaseEndTime(Date releaseEndTime) 
	{
		this.releaseEndTime = releaseEndTime;
	}

	public Date getReleaseEndTime() 
	{
		return releaseEndTime;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
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
	
	

    public String getAdScheduleName() {
		return adScheduleName;
	}

	public void setAdScheduleName(String adScheduleName) {
		this.adScheduleName = adScheduleName;
	}

	
	
	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("adReleaseTimerId", getAdReleaseTimerId())
            .append("adScheduleId", getAdScheduleId())
            .append("releaseBeginTime", getReleaseBeginTime())
            .append("releaseEndTime", getReleaseEndTime())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
