package com.zxiang.project.business.terminalstatus.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.zxiang.framework.web.domain.BaseEntity;

/**
 * 终端状态记录表 zx_terminal_status
 * 
 * @author ZXiang
 * @date 2018-10-14
 */
public class TerminalStatus extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 状态ID */
	private Integer statusId;
	/** 终端ID */
	private Integer terminalId;
	/** 终端版本 */
	private String version;
	/** 信号强度 */
	private String rssi;
	/** 4G卡号 */
	private String iccid;
	/** 经度 */
	private String lon;
	/** 纬度 */
	private String lat;
	/** 接入IP */
	private String accSysIp;
	/** 接入端口 */
	private String accSysPort;
	/** 更新时间 */
	private Date createTime;
	
	private String address;
	
	private String build;
	
	private String terminalCode;
	
	private String deviceCode;

	public void setStatusId(Integer statusId) 
	{
		this.statusId = statusId;
	}

	public Integer getStatusId() 
	{
		return statusId;
	}
	public void setTerminalId(Integer terminalId) 
	{
		this.terminalId = terminalId;
	}

	public Integer getTerminalId() 
	{
		return terminalId;
	}
	public void setVersion(String version) 
	{
		this.version = version;
	}

	public String getVersion() 
	{
		return version;
	}
	public void setRssi(String rssi) 
	{
		this.rssi = rssi;
	}

	public String getRssi() 
	{
		return rssi;
	}
	public void setIccid(String iccid) 
	{
		this.iccid = iccid;
	}

	public String getIccid() 
	{
		return iccid;
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
	public void setAccSysIp(String accSysIp) 
	{
		this.accSysIp = accSysIp;
	}

	public String getAccSysIp() 
	{
		return accSysIp;
	}
	public void setAccSysPort(String accSysPort) 
	{
		this.accSysPort = accSysPort;
	}

	public String getAccSysPort() 
	{
		return accSysPort;
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
            .append("statusId", getStatusId())
            .append("terminalId", getTerminalId())
            .append("version", getVersion())
            .append("rssi", getRssi())
            .append("iccid", getIccid())
            .append("lon", getLon())
            .append("lat", getLat())
            .append("accSysIp", getAccSysIp())
            .append("accSysPort", getAccSysPort())
            .append("createTime", getCreateTime())
            .toString();
    }

	public String getBuild() {
		return build;
	}

	public void setBuild(String build) {
		this.build = build;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getTerminalCode() {
		return terminalCode;
	}

	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
