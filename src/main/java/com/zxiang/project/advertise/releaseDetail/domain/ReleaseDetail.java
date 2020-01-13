package com.zxiang.project.advertise.releaseDetail.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zxiang.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 广告播放明细表 zx_release_detail
 * 
 * @author ZXiang
 * @date 2020-01-11
 */
public class ReleaseDetail extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer releaseDetailId;
	/** 广告ID */
	private Integer scheduleId;
	/** 广告名称 */
	private String scheduleName;
	/** 设备ID */
	private Integer deviceId;
	/** 终端ID */
	private Integer terminalId;
	/** 设备编码 */
	private String deviceCode;
	private Integer province;
	private Integer city;
	private Integer county;
	/**  */
	private String address;
	/**  */
	private Integer ownerId;
	/**  */
	private String ownerName;
	/** 维修员ID */
	private Integer repairId;
	/** 维修员 */
	private String repairName;
	/** 代理商ID */
	private Integer agentId;
	/** 代理商 */
	private String agentName;
	/** 播放时间 */
	private Date createTime;
	private String provinceName;
	private String cityName;
	private String countyName;

	public void setReleaseDetailId(Integer releaseDetailId) 
	{
		this.releaseDetailId = releaseDetailId;
	}

	public Integer getReleaseDetailId() 
	{
		return releaseDetailId;
	}
	public void setScheduleId(Integer scheduleId) 
	{
		this.scheduleId = scheduleId;
	}

	public Integer getScheduleId() 
	{
		return scheduleId;
	}
	public void setScheduleName(String scheduleName) 
	{
		this.scheduleName = scheduleName;
	}

	public String getScheduleName() 
	{
		return scheduleName;
	}
	public void setDeviceId(Integer deviceId) 
	{
		this.deviceId = deviceId;
	}

	public Integer getDeviceId() 
	{
		return deviceId;
	}
	public void setTerminalId(Integer terminalId) 
	{
		this.terminalId = terminalId;
	}

	public Integer getTerminalId() 
	{
		return terminalId;
	}
	public void setDeviceCode(String deviceCode) 
	{
		this.deviceCode = deviceCode;
	}

	public String getDeviceCode() 
	{
		return deviceCode;
	}
	public void setAddress(String address) 
	{
		this.address = address;
	}

	public String getAddress() 
	{
		return address;
	}
	public void setOwnerId(Integer ownerId) 
	{
		this.ownerId = ownerId;
	}

	public Integer getOwnerId() 
	{
		return ownerId;
	}
	public void setOwnerName(String ownerName) 
	{
		this.ownerName = ownerName;
	}

	public String getOwnerName() 
	{
		return ownerName;
	}
	public Integer getRepairId() {
		return repairId;
	}
	public void setRepairId(Integer repairId) {
		this.repairId = repairId;
	}
	public String getRepairName() {
		return repairName;
	}

	public void setRepairName(String repairName) {
		this.repairName = repairName;
	}

	public void setAgentId(Integer agentId) 
	{
		this.agentId = agentId;
	}

	public Integer getAgentId() 
	{
		return agentId;
	}
	public void setAgentName(String agentName) 
	{
		this.agentName = agentName;
	}

	public String getAgentName() 
	{
		return agentName;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	
    public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public Integer getCounty() {
		return county;
	}

	public void setCounty(Integer county) {
		this.county = county;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("releaseDetailId", getReleaseDetailId())
            .append("scheduleId", getScheduleId())
            .append("scheduleName", getScheduleName())
            .append("deviceId", getDeviceId())
            .append("terminalId", getTerminalId())
            .append("deviceCode", getDeviceCode())
            .append("address", getAddress())
            .append("ownerId", getOwnerId())
            .append("ownerName", getOwnerName())
            .append("repairUserId", getRepairId())
            .append("repairUserName", getRepairName())
            .append("agentId", getAgentId())
            .append("agentName", getAgentName())
            .append("createTime", getCreateTime())
            .toString();
    }

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	
}
