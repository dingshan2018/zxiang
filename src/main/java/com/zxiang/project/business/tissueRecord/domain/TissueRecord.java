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
	/** 二维码推广计划 */
	private Integer qrScheduleId;
	/** 出纸设备 */
	private Integer deviceId;
	/** 出纸终端 */
	private Integer terminalId;
	/** 出纸场所 */
	private Integer placeId;
	/** 粉丝用户ID */
	private Integer wxUserId;
	/** 用户微信账号 */
	private String openId;
	/** 出纸状态 0：未出纸 1：出纸成功  2：出纸失败 */
	private String status;
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
	/** 推广计划 */
	private Integer scheduleId;
	/** 出纸价格 */
	private double price;
	/** 每次出纸长度 */
	private Integer tissueLen;
	
	private String userId;

	private String terminalCode;
	private String placeName;
	private String deviceCode;
	private String deviceSn;
	
	private Integer agentId;//代理商查询条件
	private Integer repairId;//代理商查询条件
	private Integer joinId;//代理商查询条件
	
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

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public Integer getRepairId() {
		return repairId;
	}

	public void setRepairId(Integer repairId) {
		this.repairId = repairId;
	}

	public Integer getJoinId() {
		return joinId;
	}

	public void setJoinId(Integer joinId) {
		this.joinId = joinId;
	}

	public String getDeviceSn() {
		return deviceSn;
	}

	public void setDeviceSn(String deviceSn) {
		this.deviceSn = deviceSn;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getTissueLen() {
		return tissueLen;
	}

	public void setTissueLen(Integer tissueLen) {
		this.tissueLen = tissueLen;
	}

	public Integer getQrScheduleId() {
		return qrScheduleId;
	}

	public void setQrScheduleId(Integer qrScheduleId) {
		this.qrScheduleId = qrScheduleId;
	}

	public Integer getWxUserId() {
		return wxUserId;
	}

	public void setWxUserId(Integer wxUserId) {
		this.wxUserId = wxUserId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("tissueRecordId", getTissueRecordId())
            .append("qrScheduleId", getQrScheduleId())
            .append("deviceId", getDeviceId())
            .append("terminalId", getTerminalId())
            .append("placeId", getPlaceId())
            .append("wxUserId", getWxUserId())
            .append("openId", getOpenId())
            .append("status", getStatus())
            .append("nickName", getNickName())
            .append("headimgurl", getHeadimgurl())
            .append("tissueChannel", getTissueChannel())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("scheduleId", getScheduleId())
            .append("price", getPrice())
            .append("tissueLen", getTissueLen())
            .toString();
    }
}
