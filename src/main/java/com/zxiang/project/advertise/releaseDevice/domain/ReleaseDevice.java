package com.zxiang.project.advertise.releaseDevice.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zxiang.framework.web.domain.BaseEntity;

/**
 * 投放终端配置表 zx_release_device
 * 
 * @author ZXiang
 * @date 2018-11-22
 */
public class ReleaseDevice extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Integer releaseDeviceId;
	/** 排期ID */
	private Integer scheduleId;
	/** 投放位置 */
	private String releasePosition;
	/** 设备ID */
	private Integer deviceId;

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
	public void setReleasePosition(String releasePosition) 
	{
		this.releasePosition = releasePosition;
	}

	public String getReleasePosition() 
	{
		return releasePosition;
	}
	public void setDeviceId(Integer deviceId) 
	{
		this.deviceId = deviceId;
	}

	public Integer getDeviceId() 
	{
		return deviceId;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("releaseDeviceId", getReleaseDeviceId())
            .append("scheduleId", getScheduleId())
            .append("releasePosition", getReleasePosition())
            .append("deviceId", getDeviceId())
            .toString();
    }
}
