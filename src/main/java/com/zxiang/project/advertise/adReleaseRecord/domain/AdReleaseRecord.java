package com.zxiang.project.advertise.adReleaseRecord.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zxiang.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 广告投放设备表 zx_ad_release_record
 * 
 * @author ZXiang
 * @date 2018-09-25
 */
public class AdReleaseRecord extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer releaseDeviceId;
	/** 推广计划 */
	private Integer scheduleId;
	/** 视讯排期 */
	private Integer sxScheduleId;
	/** 投放设备 */
	private Integer deviceId;
	/** 下载时间 */
	private Date downTime;
	/** 播放时间 */
	private Date playTime;
	/** 播放价格 */
	private Float price;
	/**  */
	private String createBy;
	/**  */
	private Date createTime;
	/**  */
	private String updateBy;
	/**  */
	private Date updateTime;

	private String scheduleName;
	private String deviceSn;
	
	
	public void setReleaseDeviceId(Integer releaseDeviceId) 
	{
		this.releaseDeviceId = releaseDeviceId;
	}

	public Integer getReleaseDeviceId() 
	{
		return releaseDeviceId;
	}
	public void setScheduleId(Integer scheduleId) 
	{
		this.scheduleId = scheduleId;
	}

	public Integer getScheduleId() 
	{
		return scheduleId;
	}
	public void setSxScheduleId(Integer sxScheduleId) 
	{
		this.sxScheduleId = sxScheduleId;
	}

	public Integer getSxScheduleId() 
	{
		return sxScheduleId;
	}
	public void setDeviceId(Integer deviceId) 
	{
		this.deviceId = deviceId;
	}

	public Integer getDeviceId() 
	{
		return deviceId;
	}
	public void setDownTime(Date downTime) 
	{
		this.downTime = downTime;
	}

	public Date getDownTime() 
	{
		return downTime;
	}
	public void setPlayTime(Date playTime) 
	{
		this.playTime = playTime;
	}

	public Date getPlayTime() 
	{
		return playTime;
	}
	public void setPrice(Float price) 
	{
		this.price = price;
	}

	public Float getPrice() 
	{
		return price;
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

    public String getDeviceSn() {
		return deviceSn;
	}

	public void setDeviceSn(String deviceSn) {
		this.deviceSn = deviceSn;
	}

	public String getScheduleName() {
		return scheduleName;
	}

	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("releaseDeviceId", getReleaseDeviceId())
            .append("scheduleId", getScheduleId())
            .append("sxScheduleId", getSxScheduleId())
            .append("deviceId", getDeviceId())
            .append("downTime", getDownTime())
            .append("playTime", getPlayTime())
            .append("price", getPrice())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
