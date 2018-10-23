package com.zxiang.project.client.agent.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.zxiang.framework.web.domain.BaseEntity;

/**
 * 代理商表 zx_agent
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public class Agent extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 代理商ID */
	private Integer agentId;
	/** 父代理商ID */
	private Integer pagentId;
	/** 代理商名称 */
	private String agentName;
	/** 代理省份 */
	private Integer province;
	/** 代理城市 */
	private Integer city;
	/** 代理地区 */
	private Integer county;
	/** 代理等级 1 一级代理  2 二级代理 */
	private Integer level;
	/** 设备数量 */
	private Integer deviceNum;
	/** 是否删除 */
	private String delFlag;
	/** 状态 */
	private String status;
	/** 管理者ID */
	private Integer managerId;
	/** 管理者姓名 */
	private String managerName;
	/** 管理电话 */
	private String managerPhone;
	/** 收款人 */
	private String bankReceiver;
	/** 收款账号 */
	private String bankAccount;
	/** 收款人姓名 */
	private String bankName;
	/** 直推人 */
	private Integer promotorId;
	/** 视频广告系数 */
	private Float adRate;
	/** 轮播广告系数 */
	private Float adCarouselRate;
	/** 二维码广告出纸收益 */
	private Float scanRate;
	/** 直推机子分润 */
	private Float promDirectRate;
	/** 间推机子分润 */
	private Float promIndirectRate;
	/** 推广出纸收益 */
	private Float promPaperRate;
	/** 推广广告系数 */
	private Float promotionRate;
	/** 直推代理分润系数 */
	private Float directAgentRate;
	/** 服务出纸收益 */
	private Float serveRate;
	/** 办公补贴 */
	private Float subsidyRate;
	/**  */
	private String createBy;
	/**  */
	private Date createTime;
	/**  */
	private String updateBy;
	/**  */
	private Date updateTime;
	private String provinceName;
	private String cityName;
	private String countyName;

	public void setAgentId(Integer agentId) 
	{
		this.agentId = agentId;
	}

	public Integer getAgentId() 
	{
		return agentId;
	}
	public void setPagentId(Integer pagentId) 
	{
		this.pagentId = pagentId;
	}

	public Integer getPagentId() 
	{
		return pagentId;
	}
	public void setAgentName(String agentName) 
	{
		this.agentName = agentName;
	}

	public String getAgentName() 
	{
		return agentName;
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
	public void setLevel(Integer level) 
	{
		this.level = level;
	}

	public Integer getLevel() 
	{
		return level;
	}
	public void setDeviceNum(Integer deviceNum) 
	{
		this.deviceNum = deviceNum;
	}

	public Integer getDeviceNum() 
	{
		return deviceNum;
	}
	public void setDelFlag(String delFlag) 
	{
		this.delFlag = delFlag;
	}

	public String getDelFlag() 
	{
		return delFlag;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setManagerId(Integer managerId) 
	{
		this.managerId = managerId;
	}

	public Integer getManagerId() 
	{
		return managerId;
	}
	public void setManagerName(String managerName) 
	{
		this.managerName = managerName;
	}

	public String getManagerName() 
	{
		return managerName;
	}
	public void setManagerPhone(String managerPhone) 
	{
		this.managerPhone = managerPhone;
	}

	public String getManagerPhone() 
	{
		return managerPhone;
	}
	public void setBankReceiver(String bankReceiver) 
	{
		this.bankReceiver = bankReceiver;
	}

	public String getBankReceiver() 
	{
		return bankReceiver;
	}
	public void setBankAccount(String bankAccount) 
	{
		this.bankAccount = bankAccount;
	}

	public String getBankAccount() 
	{
		return bankAccount;
	}
	public void setBankName(String bankName) 
	{
		this.bankName = bankName;
	}

	public String getBankName() 
	{
		return bankName;
	}
	public void setPromotorId(Integer promotorId) 
	{
		this.promotorId = promotorId;
	}

	public Integer getPromotorId() 
	{
		return promotorId;
	}
	public void setAdRate(Float adRate) 
	{
		this.adRate = adRate;
	}

	public Float getAdRate() 
	{
		return adRate;
	}
	public Float getAdCarouselRate() {
		return adCarouselRate;
	}

	public void setAdCarouselRate(Float adCarouselRate) {
		this.adCarouselRate = adCarouselRate;
	}

	public Float getPromDirectRate() {
		return promDirectRate;
	}

	public void setPromDirectRate(Float promDirectRate) {
		this.promDirectRate = promDirectRate;
	}

	public Float getPromIndirectRate() {
		return promIndirectRate;
	}

	public void setPromIndirectRate(Float promIndirectRate) {
		this.promIndirectRate = promIndirectRate;
	}

	public Float getPromPaperRate() {
		return promPaperRate;
	}

	public void setPromPaperRate(Float promPaperRate) {
		this.promPaperRate = promPaperRate;
	}

	public Float getDirectAgentRate() {
		return directAgentRate;
	}

	public void setDirectAgentRate(Float directAgentRate) {
		this.directAgentRate = directAgentRate;
	}

	public Float getServeRate() {
		return serveRate;
	}

	public void setServeRate(Float serveRate) {
		this.serveRate = serveRate;
	}

	public Float getSubsidyRate() {
		return subsidyRate;
	}

	public void setSubsidyRate(Float subsidyRate) {
		this.subsidyRate = subsidyRate;
	}

	public void setScanRate(Float scanRate) 
	{
		this.scanRate = scanRate;
	}

	public Float getScanRate() 
	{
		return scanRate;
	}
	public void setPromotionRate(Float promotionRate) 
	{
		this.promotionRate = promotionRate;
	}

	public Float getPromotionRate() 
	{
		return promotionRate;
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

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("agentId", getAgentId())
            .append("pagentId", getPagentId())
            .append("agentName", getAgentName())
            .append("province", getProvince())
            .append("city", getCity())
            .append("county", getCounty())
            .append("level", getLevel())
            .append("deviceNum", getDeviceNum())
            .append("delFlag", getDelFlag())
            .append("status", getStatus())
            .append("managerId", getManagerId())
            .append("managerName", getManagerName())
            .append("managerPhone", getManagerPhone())
            .append("bankReceiver", getBankReceiver())
            .append("bankAccount", getBankAccount())
            .append("bankName", getBankName())
            .append("promotorId", getPromotorId())
            .append("adRate", getAdRate())
            .append("scanRate", getScanRate())
            .append("promotionRate", getPromotionRate())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
