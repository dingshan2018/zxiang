package com.zxiang.project.business.device.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.zxiang.framework.web.domain.BaseEntity;

/**
 * 共享设备表 zx_device
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
public class Device extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer deviceId;
	/** 设备编号 （场所编号+序号） */
	private String deviceCode;
	/** 设备资产编号（钢印编号） */
	private String deviceSn;
	/** 设备类型 */
	private String deviceType;
	/** 终端ID（板卡ID） */
	private Integer terminalId;
	/** 场所Id */
	private String placeId;
	/** 出纸价格 */
	private Float price;
	/**  */
	private String lon;
	/**  */
	private String lat;
	/** 投放时间 */
	private Date releaseTime;
	/** 撤机时间 */
	private Date removeTime;
	/** 累计出纸 */
	private Integer totalLen;
	/** 剩余出纸 */
	private Integer remainLen;
	/** 最后心跳时间 */
	private Date lastHeartTime;
	/** 最后登陆时间 */
	private Date lastLoginTime;
	/** 最后扫码时间 */
	private Date lastScanTime;
	/** 备注 */
	private String note;
	/** 设备状态(1 下线  2 上线  3 故障 ) */
	private String status;
	/** 是否删除 */
	private String delFlag;
	/**  */
	private String createBy;
	/**  */
	private Date createTime;
	/**  */
	private String updateBy;
	/**  */
	private Date updateTime;

	public void setDeviceId(Integer deviceId) 
	{
		this.deviceId = deviceId;
	}

	public Integer getDeviceId() 
	{
		return deviceId;
	}
	public void setDeviceCode(String deviceCode) 
	{
		this.deviceCode = deviceCode;
	}

	public String getDeviceCode() 
	{
		return deviceCode;
	}
	public void setDeviceSn(String deviceSn) 
	{
		this.deviceSn = deviceSn;
	}

	public String getDeviceSn() 
	{
		return deviceSn;
	}
	public void setDeviceType(String deviceType) 
	{
		this.deviceType = deviceType;
	}

	public String getDeviceType() 
	{
		return deviceType;
	}
	public void setTerminalId(Integer terminalId) 
	{
		this.terminalId = terminalId;
	}

	public Integer getTerminalId() 
	{
		return terminalId;
	}
	public void setPlaceId(String placeId) 
	{
		this.placeId = placeId;
	}

	public String getPlaceId() 
	{
		return placeId;
	}
	public void setPrice(Float price) 
	{
		this.price = price;
	}

	public Float getPrice() 
	{
		return price;
	}
	public void setLon(String lon) 
	{
		this.lon = lon;
	}

	public String getLon() 
	{
		return lon;
	}
	public void setLat(String lat) 
	{
		this.lat = lat;
	}

	public String getLat() 
	{
		return lat;
	}
	public void setReleaseTime(Date releaseTime) 
	{
		this.releaseTime = releaseTime;
	}

	public Date getReleaseTime() 
	{
		return releaseTime;
	}
	public void setRemoveTime(Date removeTime) 
	{
		this.removeTime = removeTime;
	}

	public Date getRemoveTime() 
	{
		return removeTime;
	}
	public void setTotalLen(Integer totalLen) 
	{
		this.totalLen = totalLen;
	}

	public Integer getTotalLen() 
	{
		return totalLen;
	}
	public void setRemainLen(Integer remainLen) 
	{
		this.remainLen = remainLen;
	}

	public Integer getRemainLen() 
	{
		return remainLen;
	}
	public void setLastHeartTime(Date lastHeartTime) 
	{
		this.lastHeartTime = lastHeartTime;
	}

	public Date getLastHeartTime() 
	{
		return lastHeartTime;
	}
	public void setLastLoginTime(Date lastLoginTime) 
	{
		this.lastLoginTime = lastLoginTime;
	}

	public Date getLastLoginTime() 
	{
		return lastLoginTime;
	}
	public void setLastScanTime(Date lastScanTime) 
	{
		this.lastScanTime = lastScanTime;
	}

	public Date getLastScanTime() 
	{
		return lastScanTime;
	}
	public void setNote(String note) 
	{
		this.note = note;
	}

	public String getNote() 
	{
		return note;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setDelFlag(String delFlag) 
	{
		this.delFlag = delFlag;
	}

	public String getDelFlag() 
	{
		return delFlag;
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
            .append("deviceId", getDeviceId())
            .append("deviceCode", getDeviceCode())
            .append("deviceSn", getDeviceSn())
            .append("deviceType", getDeviceType())
            .append("terminalId", getTerminalId())
            .append("placeId", getPlaceId())
            .append("price", getPrice())
            .append("lon", getLon())
            .append("lat", getLat())
            .append("releaseTime", getReleaseTime())
            .append("removeTime", getRemoveTime())
            .append("totalLen", getTotalLen())
            .append("remainLen", getRemainLen())
            .append("lastHeartTime", getLastHeartTime())
            .append("lastLoginTime", getLastLoginTime())
            .append("lastScanTime", getLastScanTime())
            .append("note", getNote())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
