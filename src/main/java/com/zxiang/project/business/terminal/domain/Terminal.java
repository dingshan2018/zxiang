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
	/** 物联网卡号 */
	private String iccId; 
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
	/** 在线标志（0：离线 1：在线）*/
	private String onlineStatus;
	/** 终端状态（1：待绑定 2：待激活 3：激活 4：故障*/
	private String status;
	/** 终端音量 */
	private Float volumn;
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
	/**出纸密钥  */
	private String offerKey;
	
	private String logUrl;//终端日志URL

	private String deviceCode;
	private String deviceSn;
	private String placeName;
	private String statusName;
	private String onlineStatusName;
	
	private String userId;
	
	private String deviceType;
	
	private String address;
	
	private String build;
	
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

	public String getOfferKey() {
		return offerKey;
	}

	public void setOfferKey(String offerKey) {
		this.offerKey = offerKey;
	}

	public String getIccId() {
		return iccId;
	}

	public void setIccId(String iccId) {
		this.iccId = iccId;
	}

	public String getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(String onlineStatus) {
		this.onlineStatus = onlineStatus;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getOnlineStatusName() {
		return onlineStatusName;
	}

	public void setOnlineStatusName(String onlineStatusName) {
		this.onlineStatusName = onlineStatusName;
	}

	public String getDeviceSn() {
		return deviceSn;
	}

	public void setDeviceSn(String deviceSn) {
		this.deviceSn = deviceSn;
	}
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLogUrl() {
		return logUrl;
	}

	public void setLogUrl(String logUrl) {
		this.logUrl = logUrl;
	}

	@Override
	public String toString() {
		return "Terminal [terminalId=" + terminalId + ", terminalCode=" + terminalCode + ", deviceId=" + deviceId
				+ ", placeId=" + placeId + ", rssi=" + rssi + ", iccId=" + iccId + ", lastHeartTime=" + lastHeartTime
				+ ", lastLoginTime=" + lastLoginTime + ", accSysIp=" + accSysIp + ", accSysPort=" + accSysPort
				+ ", snCode=" + snCode + ", onlineStatus=" + onlineStatus + ", status=" + status + ", volumn=" + volumn
				+ ", delFlag=" + delFlag + ", lon=" + lon + ", lat=" + lat + ", version=" + version + ", createBy="
				+ createBy + ", createTime=" + createTime + ", updateBy=" + updateBy + ", updateTime=" + updateTime
				+ ", offerKey=" + offerKey + ", logUrl=" + logUrl + ", deviceCode=" + deviceCode + ", deviceSn="
				+ deviceSn + ", placeName=" + placeName + ", statusName=" + statusName + ", onlineStatusName="
				+ onlineStatusName + ", userId=" + userId + "]";
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBuild() {
		return build;
	}

	public void setBuild(String build) {
		this.build = build;
	}
    
}
