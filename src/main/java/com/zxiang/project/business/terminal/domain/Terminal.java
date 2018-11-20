package com.zxiang.project.business.terminal.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.zxiang.framework.web.domain.BaseEntity;

/**
 * 终端管理表 zx_terminal
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
public class Terminal extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 终端ID */
	private Integer terminalId;
	/** 终端编号 */
	private String terminalCode;
	/** 绑定设备（换板完，就没有这个） */
	private Integer deviceId;
	/** 场所ID */
	private Integer placeId;
	/** 终端信号强度 */
	private Float rssi;
	/** 最后心跳时间 */
	private Date lastHeartTime;
	/** 最后登陆时间 */
	private Date lastLoginTime;
	/**accSysIp*/
	private String accSysIp;
	/** accSysPort */
	private String accSysPort;
	/** 板卡编号*/
	private String snCode;
	/** 终端音量 */
	private Float volumn;
	/** 终端状态 */
	private String status;
	/** 是否删除 */
	private String delFlag;
	/** 经度 */
	private String lon;
	/** 纬度 */
	private String lat;
	/** 终端版本 */
	private String version;
	/**  */
	private String createBy;
	/**  */
	private Date createTime;
	/**  */
	private String updateBy;
	/**  */
	private Date updateTime;

	private String deviceCode;
	private String placeName;
	
	public void setTerminalId(Integer terminalId) 
	{
		this.terminalId = terminalId;
	}

	public Integer getTerminalId() 
	{
		return terminalId;
	}
	public void setTerminalCode(String terminalCode) 
	{
		this.terminalCode = terminalCode;
	}

	public String getTerminalCode() 
	{
		return terminalCode;
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
	public void setRssi(Float rssi) 
	{
		this.rssi = rssi;
	}

	public Float getRssi() 
	{
		return rssi;
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
	public void setVolumn(Float volumn) 
	{
		this.volumn = volumn;
	}

	public Float getVolumn() 
	{
		return volumn;
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
	public void setVersion(String version) 
	{
		this.version = version;
	}

	public String getVersion() 
	{
		return version;
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
	

    public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getAccSysIp() {
		return accSysIp;
	}

	public void setAccSysIp(String accSysIp) {
		this.accSysIp = accSysIp;
	}

	public String getAccSysPort() {
		return accSysPort;
	}

	public void setAccSysPort(String accSysPort) {
		this.accSysPort = accSysPort;
	}

	public String getSnCode() {
		return snCode;
	}

	public void setSnCode(String snCode) {
		this.snCode = snCode;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("terminalId", getTerminalId())
            .append("terminalCode", getTerminalCode())
            .append("deviceId", getDeviceId())
            .append("placeId", getPlaceId())
            .append("rssi", getRssi())
            .append("lastHeartTime", getLastHeartTime())
            .append("lastLoginTime", getLastLoginTime())
            .append("volumn", getVolumn())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("lon", getLon())
            .append("lat", getLat())
            .append("version", getVersion())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
