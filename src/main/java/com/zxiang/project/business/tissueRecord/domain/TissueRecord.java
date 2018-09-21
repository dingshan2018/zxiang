package com.zxiang.project.business.tissueRecord.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.zxiang.framework.web.domain.BaseEntity;

/**
 * 出纸记录表 zx_tissue_record
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
public class TissueRecord extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer tissueRecordId;
	/** 出纸设备 */
	private Integer deviceId;
	/** 出纸终端 */
	private Integer terminalId;
	/** 出纸场所 */
	private Integer placeId;
	/** 用户微信账号 */
	private String openId;
	/** 微信昵称 */
	private String nickName;
	/** 微信头像 */
	private String headimgurl;
	/** 出纸类型 */
	private String tissueChannel;
	/** 使用人 */
	private String createBy;
	/** 出纸时间 */
	private Date createTime;

	private String terminalCode;
	private String placeName;
	private String deviceCode;
	
	
	public void setTissueRecordId(Integer tissueRecordId) 
	{
		this.tissueRecordId = tissueRecordId;
	}

	public Integer getTissueRecordId() 
	{
		return tissueRecordId;
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
	public void setPlaceId(Integer placeId) 
	{
		this.placeId = placeId;
	}

	public Integer getPlaceId() 
	{
		return placeId;
	}
	public void setOpenId(String openId) 
	{
		this.openId = openId;
	}

	public String getOpenId() 
	{
		return openId;
	}
	public void setNickName(String nickName) 
	{
		this.nickName = nickName;
	}

	public String getNickName() 
	{
		return nickName;
	}
	public void setHeadimgurl(String headimgurl) 
	{
		this.headimgurl = headimgurl;
	}

	public String getHeadimgurl() 
	{
		return headimgurl;
	}
	public void setTissueChannel(String tissueChannel) 
	{
		this.tissueChannel = tissueChannel;
	}

	public String getTissueChannel() 
	{
		return tissueChannel;
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

    public String getTerminalCode() {
		return terminalCode;
	}

	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("tissueRecordId", getTissueRecordId())
            .append("deviceId", getDeviceId())
            .append("terminalId", getTerminalId())
            .append("placeId", getPlaceId())
            .append("openId", getOpenId())
            .append("nickName", getNickName())
            .append("headimgurl", getHeadimgurl())
            .append("tissueChannel", getTissueChannel())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}
