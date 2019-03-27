package com.zxiang.project.advertise.adSchedule.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zxiang.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 广告投放表 zx_ad_schedule
 * 
 * @author ZXiang
 * @date 2018-09-24
 */
public class AdSchedule extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer adScheduleId;
	/** 投放名称 */
	private String scheduleName;
	/** 投放方式 */
	private String releaseType;
	/** 投放位置 */
	private String releasePosition;
	/** 排期ID */
	private String sxScheduleId;
	/**  */
	private String status;
	/** 总价 */
	private Float totalPay;
	/** 押金 */
	private Float prepay;
	/** 投放终端数 */
	private Integer releaseTermNum;
	/** 投放天数*/
	private Integer releaseDays;
	
	private Integer advertiser;
	private Integer promotioner;
	private Integer releaser;
	
	/**  */
	private String isDel;
	/** 投放备注 */
	private String releaseNote;
	/**  */
	private String createBy;
	/**  */
	private Date createTime;
	/**  */
	private String updateBy;
	/**  */
	private Date updateTime;
	/**节目单ID  */
	private String pId;
	/**模板主题ID  */
	private String themeTemplateId;
	/**模板元素ID  */
	private String elementId;
	/**审核结果  */
	private String approved;
	/**审核意见  */
	private String approvedRemark;
	/**审核人员  */
	private String approvedUser;
	/**总共播放时长  */
	private String totalTime;
	/**支付状态:0未支付；1已全部支付；2押金已支付  */
	private String payStatus;
	/**支付状态:0未发布；1已发布； */
	private String releaseStatus;
	/**H5广告URL  */
	private String qrUrl;
	
	/**HTTP接口返回的tid  */
	private String tId;
	
	private String deviceIds;
	private String timeSlotArr;

	private Integer province; 
	private Integer city; 
	private Integer county; 
	private Integer placeGrade; 
	
	private String releaseTypeName;
	private String releasePositionName;
	private String statusName;
	private String advertiseName;
	private String approvedName;
	private String promotionerName;
	private String releaserName;
	
	public void setAdScheduleId(Integer adScheduleId) 
	{
		this.adScheduleId = adScheduleId;
	}

	public Integer getAdScheduleId() 
	{
		return adScheduleId;
	}
	public void setScheduleName(String scheduleName) 
	{
		this.scheduleName = scheduleName;
	}

	public String getScheduleName() 
	{
		return scheduleName;
	}
	public void setReleaseType(String releaseType) 
	{
		this.releaseType = releaseType;
	}

	public String getReleaseType() 
	{
		return releaseType;
	}
	public void setReleasePosition(String releasePosition) 
	{
		this.releasePosition = releasePosition;
	}

	public String getReleasePosition() 
	{
		return releasePosition;
	}
	public void setSxScheduleId(String sxScheduleId) 
	{
		this.sxScheduleId = sxScheduleId;
	}

	public String getSxScheduleId() 
	{
		return sxScheduleId;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setTotalPay(Float totalPay) 
	{
		this.totalPay = totalPay;
	}

	public Float getTotalPay() 
	{
		return totalPay;
	}
	public void setPrepay(Float prepay) 
	{
		this.prepay = prepay;
	}

	public Float getPrepay() 
	{
		return prepay;
	}
	public void setReleaseTermNum(Integer releaseTermNum) 
	{
		this.releaseTermNum = releaseTermNum;
	}

	public Integer getReleaseTermNum() 
	{
		return releaseTermNum;
	}
	public void setIsDel(String isDel) 
	{
		this.isDel = isDel;
	}

	public String getIsDel() 
	{
		return isDel;
	}
	public void setReleaseNote(String releaseNote) 
	{
		this.releaseNote = releaseNote;
	}

	public String getReleaseNote() 
	{
		return releaseNote;
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

    public String getReleaseTypeName() {
		return releaseTypeName;
	}

	public void setReleaseTypeName(String releaseTypeName) {
		this.releaseTypeName = releaseTypeName;
	}

	public String getReleasePositionName() {
		return releasePositionName;
	}

	public void setReleasePositionName(String releasePositionName) {
		this.releasePositionName = releasePositionName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getDeviceIds() {
		return deviceIds;
	}

	public void setDeviceIds(String deviceIds) {
		this.deviceIds = deviceIds;
	}

	public String getTimeSlotArr() {
		return timeSlotArr;
	}

	public void setTimeSlotArr(String timeSlotArr) {
		this.timeSlotArr = timeSlotArr;
	}

	public String getApproved() {
		return approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public String getApprovedRemark() {
		return approvedRemark;
	}

	public void setApprovedRemark(String approvedRemark) {
		this.approvedRemark = approvedRemark;
	}

	public String getApprovedUser() {
		return approvedUser;
	}

	public void setApprovedUser(String approvedUser) {
		this.approvedUser = approvedUser;
	}

	public String getThemeTemplateId() {
		return themeTemplateId;
	}

	public void setThemeTemplateId(String themeTemplateId) {
		this.themeTemplateId = themeTemplateId;
	}

	public String getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(String playTotalTime) {
		this.totalTime = playTotalTime;
	}

	public String gettId() {
		return tId;
	}

	public void settId(String tId) {
		this.tId = tId;
	}

	public String getAdvertiseName() {
		return advertiseName;
	}

	public void setAdvertiseName(String advertiseName) {
		this.advertiseName = advertiseName;
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

	public Integer getPlaceGrade() {
		return placeGrade;
	}

	public void setPlaceGrade(Integer placeGrade) {
		this.placeGrade = placeGrade;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getQrUrl() {
		return qrUrl;
	}

	public void setQrUrl(String qrUrl) {
		this.qrUrl = qrUrl;
	}

	public String getApprovedName() {
		return approvedName;
	}

	public void setApprovedName(String approvedName) {
		this.approvedName = approvedName;
	}

	public Integer getAdvertiser() {
		return advertiser;
	}

	public void setAdvertiser(Integer advertiser) {
		this.advertiser = advertiser;
	}

	public Integer getPromotioner() {
		return promotioner;
	}

	public void setPromotioner(Integer promotioner) {
		this.promotioner = promotioner;
	}

	public Integer getReleaser() {
		return releaser;
	}

	public void setReleaser(Integer releaser) {
		this.releaser = releaser;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("adScheduleId", getAdScheduleId())
            .append("scheduleName", getScheduleName())
            .append("releaseType", getReleaseType())
            .append("releasePosition", getReleasePosition())
            .append("sxScheduleId", getSxScheduleId())
            .append("status", getStatus())
            .append("totalPay", getTotalPay())
            .append("prepay", getPrepay())
            .append("releaseTermNum", getReleaseTermNum())
            .append("advertiser", getAdvertiser())
            .append("promotioner", getPromotioner())
            .append("releaser", getReleaser())
            .append("isDel", getIsDel())
            .append("releaseNote", getReleaseNote())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }

	public Integer getReleaseDays() {
		return releaseDays;
	}

	public void setReleaseDays(Integer releaseDays) {
		this.releaseDays = releaseDays;
	}

	public String getPromotionerName() {
		return promotionerName;
	}

	public void setPromotionerName(String promotionerName) {
		this.promotionerName = promotionerName;
	}

	public String getReleaserName() {
		return releaserName;
	}

	public void setReleaserName(String releaserName) {
		this.releaserName = releaserName;
	}

	public String getReleaseStatus() {
		return releaseStatus;
	}

	public void setReleaseStatus(String releaseStatus) {
		this.releaseStatus = releaseStatus;
	}
	
}
