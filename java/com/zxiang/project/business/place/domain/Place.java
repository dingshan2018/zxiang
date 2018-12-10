package com.zxiang.project.business.place.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.zxiang.framework.web.domain.BaseEntity;

/**
 * 场所管理表 zx_place
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
public class Place extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 场所ID */
	private Integer placeId;
	/** 父级场所ID */
	private Integer parentPlaceId;
	/** 场所编号（地区编号+序号） */
	private String placeCode;
	/** 场所类型（自营、代理、自购） */
	private String placeType;
	/** 场景(场所业态) */
	private String scene;
	/** 场所名称 */
	private String name;
	/** 场所简拼 */
	private String sname;
	/** 详细地址 */
	private String address;
	/** 省份 */
	private Integer province;
	/** 城市 */
	private Integer city;
	/** 地区 */
	private Integer county;
	/** 经度 */
	private String lon;
	/** 纬度 */
	private String lat;
	/** 扫码套餐（备用） */
	private Integer packet;
	/** 出纸价格（以设备为准，设备没有以场所为准） */
	private Float tissuePrice;
	/** 每次出纸长度 */
	private Integer tissueLen;
	/** 维修员 */
	private Integer repairId;
	/** 送纸员 */
	private Integer supplyId;
	/** 经办人 */
	private Integer operatorId;
	/** 机主 */
	private Integer ownerId;
	/** 投放设备数量 */
	private Integer deviceCount;
	/** 开户行（合同收款方） */
	private String ctBank;
	/** 收款账号 */
	private String ctAccount;
	/** 收款人 */
	private String ctReceiver;
	/** 收款人联系电话 */
	private String ctPhone;
	/** 收款人身份证 */
	private String ctIdentity;
	/** 商家分成比例 */
	private Float ctRate;
	/** 场所状态 */
	private String status;
	/** 是否删除 */
	private String delFlag;
	/** 备注 */
	private String note;
	/** 创建者 */
	private String createBy;
	/**  */
	private Date createTime;
	/**  */
	private String updateBy;
	/**  */
	private Date updateTime;

	
	private String parentPlaceName;
	private String provinceName;
	private String cityName;
	private String countyName;
	private String repairName;
	private String supplyName;
	private String operatorName;
	private String ownerName;
	private String sceneName;
	
	public void setPlaceId(Integer placeId) 
	{
		this.placeId = placeId;
	}

	public Integer getPlaceId() 
	{
		return placeId;
	}
	public void setParentPlaceId(Integer parentPlaceId) 
	{
		this.parentPlaceId = parentPlaceId;
	}

	public Integer getParentPlaceId() 
	{
		return parentPlaceId;
	}
	public void setPlaceCode(String placeCode) 
	{
		this.placeCode = placeCode;
	}

	public String getPlaceCode() 
	{
		return placeCode;
	}
	public void setPlaceType(String placeType) 
	{
		this.placeType = placeType;
	}

	public String getPlaceType() 
	{
		return placeType;
	}
	public void setScene(String scene) 
	{
		this.scene = scene;
	}

	public String getScene() 
	{
		return scene;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setSname(String sname) 
	{
		this.sname = sname;
	}

	public String getSname() 
	{
		return sname;
	}
	public void setAddress(String address) 
	{
		this.address = address;
	}

	public String getAddress() 
	{
		return address;
	}
	public void setProvince(Integer province) 
	{
		this.province = province;
	}

	public Integer getProvince() 
	{
		return province;
	}
	public void setCity(Integer city) 
	{
		this.city = city;
	}

	public Integer getCity() 
	{
		return city;
	}
	public void setCounty(Integer county) 
	{
		this.county = county;
	}

	public Integer getCounty() 
	{
		return county;
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
	public void setPacket(Integer packet) 
	{
		this.packet = packet;
	}

	public Integer getPacket() 
	{
		return packet;
	}
	public void setTissuePrice(Float tissuePrice) 
	{
		this.tissuePrice = tissuePrice;
	}

	public Float getTissuePrice() 
	{
		return tissuePrice;
	}
	public void setTissueLen(Integer tissueLen) 
	{
		this.tissueLen = tissueLen;
	}

	public Integer getTissueLen() 
	{
		return tissueLen;
	}
	public void setRepairId(Integer repairId) 
	{
		this.repairId = repairId;
	}

	public Integer getRepairId() 
	{
		return repairId;
	}
	public void setSupplyId(Integer supplyId) 
	{
		this.supplyId = supplyId;
	}

	public Integer getSupplyId() 
	{
		return supplyId;
	}
	public void setOperatorId(Integer operatorId) 
	{
		this.operatorId = operatorId;
	}

	public Integer getOperatorId() 
	{
		return operatorId;
	}
	public void setOwnerId(Integer ownerId) 
	{
		this.ownerId = ownerId;
	}

	public Integer getOwnerId() 
	{
		return ownerId;
	}
	public void setDeviceCount(Integer deviceCount) 
	{
		this.deviceCount = deviceCount;
	}

	public Integer getDeviceCount() 
	{
		return deviceCount;
	}
	public void setCtBank(String ctBank) 
	{
		this.ctBank = ctBank;
	}

	public String getCtBank() 
	{
		return ctBank;
	}
	public void setCtAccount(String ctAccount) 
	{
		this.ctAccount = ctAccount;
	}

	public String getCtAccount() 
	{
		return ctAccount;
	}
	public void setCtReceiver(String ctReceiver) 
	{
		this.ctReceiver = ctReceiver;
	}

	public String getCtReceiver() 
	{
		return ctReceiver;
	}
	public void setCtPhone(String ctPhone) 
	{
		this.ctPhone = ctPhone;
	}

	public String getCtPhone() 
	{
		return ctPhone;
	}
	public void setCtIdentity(String ctIdentity) 
	{
		this.ctIdentity = ctIdentity;
	}

	public String getCtIdentity() 
	{
		return ctIdentity;
	}
	public void setCtRate(Float ctRate) 
	{
		this.ctRate = ctRate;
	}

	public Float getCtRate() 
	{
		return ctRate;
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
	public void setNote(String note) 
	{
		this.note = note;
	}

	public String getNote() 
	{
		return note;
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

	public String getParentPlaceName() {
		return parentPlaceName;
	}

	public void setParentPlaceName(String parentPlaceName) {
		this.parentPlaceName = parentPlaceName;
	}

	public String getRepairName() {
		return repairName;
	}

	public void setRepairName(String repairName) {
		this.repairName = repairName;
	}

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getSceneName() {
		return sceneName;
	}

	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("placeId", getPlaceId())
            .append("parentPlaceId", getParentPlaceId())
            .append("placeCode", getPlaceCode())
            .append("placeType", getPlaceType())
            .append("scene", getScene())
            .append("name", getName())
            .append("sname", getSname())
            .append("address", getAddress())
            .append("province", getProvince())
            .append("city", getCity())
            .append("county", getCounty())
            .append("lon", getLon())
            .append("lat", getLat())
            .append("packet", getPacket())
            .append("tissuePrice", getTissuePrice())
            .append("tissueLen", getTissueLen())
            .append("repairId", getRepairId())
            .append("supplyId", getSupplyId())
            .append("operatorId", getOperatorId())
            .append("ownerId", getOwnerId())
            .append("deviceCount", getDeviceCount())
            .append("ctBank", getCtBank())
            .append("ctAccount", getCtAccount())
            .append("ctReceiver", getCtReceiver())
            .append("ctPhone", getCtPhone())
            .append("ctIdentity", getCtIdentity())
            .append("ctRate", getCtRate())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("note", getNote())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
