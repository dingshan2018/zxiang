package com.zxiang.project.record.onlinedevice.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zxiang.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 设备在线时长表 zx_online_device
 * 
 * @author ZXiang
 * @date 2020-05-14
 */
public class OnlineDevice extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer deviceOnlineId;
	/** 统计日期 */
	private Date sumDate;
	/** 设备ID */
	private Integer deviceId;
	/** 设备编码 */
	private String deviceCode;
	/** 终端ID */
	private Integer terminalId;
	/** GPS地址 */
	private String address;
	/** 投放地址 */
	private String releaseAddress;
	/** 代理商ID */
	private Integer agentId;
	/** 机主 */
	private Integer joinId;
	/** 维护ID */
	private Integer repairId;
	/** 店主ID */
	private Integer shopperId;
	/** 在线时长 */
	private Long onlineTime;
	private String totalTime;
	/** 创建时间 */
	private Date createTime;
	/** 修改时间 */
	private Date updateTime;

	private String userType;
	
	private String userName;
	
	
	
	public void setDeviceOnlineId(Integer deviceOnlineId) 
	{
		this.deviceOnlineId = deviceOnlineId;
	}

	public Integer getDeviceOnlineId() 
	{
		return deviceOnlineId;
	}
	public void setSumDate(Date sumDate) 
	{
		this.sumDate = sumDate;
	}

	public Date getSumDate() 
	{
		return sumDate;
	}
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
	public void setTerminalId(Integer terminalId) 
	{
		this.terminalId = terminalId;
	}

	public Integer getTerminalId() 
	{
		return terminalId;
	}
	public void setAddress(String address) 
	{
		this.address = address;
	}

	public String getAddress() 
	{
		return address;
	}
	public void setReleaseAddress(String releaseAddress) 
	{
		this.releaseAddress = releaseAddress;
	}

	public String getReleaseAddress() 
	{
		return releaseAddress;
	}
	public void setAgentId(Integer agentId) 
	{
		this.agentId = agentId;
	}

	public Integer getAgentId() 
	{
		return agentId;
	}
	public void setJoinId(Integer joinId) 
	{
		this.joinId = joinId;
	}

	public Integer getJoinId() 
	{
		return joinId;
	}
	public void setRepairId(Integer repairId) 
	{
		this.repairId = repairId;
	}

	public Integer getRepairId() 
	{
		return repairId;
	}
	public void setShopperId(Integer shopperId) 
	{
		this.shopperId = shopperId;
	}

	public Integer getShopperId() 
	{
		return shopperId;
	}
	public void setOnlineTime(Long onlineTime) 
	{
		this.onlineTime = onlineTime;
	}

	public Long getOnlineTime() 
	{
		return onlineTime;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setUpdateTime(Date updateTime) 
	{
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() 
	{
		return updateTime;
	}
	
    public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("deviceOnlineId", getDeviceOnlineId())
            .append("sumDate", getSumDate())
            .append("deviceId", getDeviceId())
            .append("deviceCode", getDeviceCode())
            .append("terminalId", getTerminalId())
            .append("address", getAddress())
            .append("releaseAddress", getReleaseAddress())
            .append("agentId", getAgentId())
            .append("joinId", getJoinId())
            .append("repairId", getRepairId())
            .append("shopperId", getShopperId())
            .append("onlineTime", getOnlineTime())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }

	public String getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}
}
