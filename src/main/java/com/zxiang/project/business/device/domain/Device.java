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
	/** 每次出纸长度 */
	private Integer tissueLen;
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
	
	/** 投放申请:0可提交;1已申请;2申请通过;3申请失败 */
	private String releaseStatus;
	
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
	/** 机主ID */
	private Integer ownerId;

	private String terminalCode;
	private String placeName;
	private String sceneName;
	/** 省份 */
	private Integer province;
	private String provinceName;
	/** 城市 */
	private Integer city;
	private String cityName;
	/** 地区 */
	private Integer county;

	private String countyName;
	//终端换板字段
	private Integer newTerminalId;//新终端
	private String oldVolumn;//音量
	private Integer changerId;//换板人ID
	private Integer remainPaper;//剩余纸张数
	//设备补纸字段
	private Integer tissueCount;
	private Integer supplierId;
	
	private String statusName;
	private String ownerName;
	// 今日累积时长
	private Long curTime;
	// 累积时长
	private Long totalTime;
	
	private String userId ;
	
	private String servicePointName;//服务网点名称
	private String agentLevel1;//一级代理
	private String agentLevel2;//二级代理
	
	private Integer scheduleId;
	
	private String adUrl;
	
	private String wxNickname;//微信昵称
	
	private String releaseDeviceCondition;//广告投放设备查询条件，有值即过滤终端不为空的设备
	
	private Long totalCnt;//累计出纸次数
	private Long invalidCnt;//无效累计出纸巾
	private Long validCnt;//有效累计出纸
	
	private String address;
	
	private String build;
	
	private String mediaId;
	
	private Integer shopperId;
	
	private String shopperName;
	
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
	
	public Integer getTissueLen() {
		return tissueLen;
	}

	public void setTissueLen(Integer tissueLen) {
		this.tissueLen = tissueLen;
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

	public Integer getNewTerminalId() {
		return newTerminalId;
	}

	public void setNewTerminalId(Integer newTerminalId) {
		this.newTerminalId = newTerminalId;
	}

	public String getOldVolumn() {
		return oldVolumn;
	}

	public void setOldVolumn(String oldVolumn) {
		this.oldVolumn = oldVolumn;
	}

	public Integer getChangerId() {
		return changerId;
	}

	public void setChangerId(Integer changerId) {
		this.changerId = changerId;
	}

	public Integer getRemainPaper() {
		return remainPaper;
	}

	public void setRemainPaper(Integer remainPaper) {
		this.remainPaper = remainPaper;
	}

	public Integer getTissueCount() {
		return tissueCount;
	}

	public void setTissueCount(Integer tissueCount) {
		this.tissueCount = tissueCount;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getReleaseStatus() {
		return releaseStatus;
	}

	public void setReleaseStatus(String releaseStatus) {
		this.releaseStatus = releaseStatus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getServicePointName() {
		return servicePointName;
	}

	public void setServicePointName(String servicePointName) {
		this.servicePointName = servicePointName;
	}

	public String getAgentLevel1() {
		return agentLevel1;
	}

	public void setAgentLevel1(String agentLevel1) {
		this.agentLevel1 = agentLevel1;
	}

	public String getAgentLevel2() {
		return agentLevel2;
	}

	public void setAgentLevel2(String agentLevel2) {
		this.agentLevel2 = agentLevel2;
	}

	public String getSceneName() {
		return sceneName;
	}

	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
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
            .append("wxNickname", getWxNickname())
            .toString();
    }

	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getAdUrl() {
		return adUrl;
	}

	public void setAdUrl(String adUrl) {
		this.adUrl = adUrl;
	}

	public String getWxNickname() {
		return wxNickname;
	}

	public void setWxNickname(String wxNickname) {
		this.wxNickname = wxNickname;
	}

	public String getReleaseDeviceCondition() {
		return releaseDeviceCondition;
	}

	public void setReleaseDeviceCondition(String releaseDeviceCondition) {
		this.releaseDeviceCondition = releaseDeviceCondition;
	}

	public Long getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(Long totalCnt) {
		this.totalCnt = totalCnt;
	}

	public Long getInvalidCnt() {
		return invalidCnt;
	}

	public void setInvalidCnt(Long invalidCnt) {
		this.invalidCnt = invalidCnt;
	}

	public Long getValidCnt() {
		return validCnt;
	}

	public void setValidCnt(Long validCnt) {
		this.validCnt = validCnt;
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

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public Integer getShopperId() {
		return shopperId;
	}

	public void setShopperId(Integer shopperId) {
		this.shopperId = shopperId;
	}

	public String getShopperName() {
		return shopperName;
	}

	public void setShopperName(String shopperName) {
		this.shopperName = shopperName;
	}

	public Long getCurTime() {
		return curTime;
	}

	public void setCurTime(Long curTime) {
		this.curTime = curTime;
	}

	public Long getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(Long totalTime) {
		this.totalTime = totalTime;
	}
	
	
}
