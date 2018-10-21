package com.zxiang.project.client.repair.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.zxiang.framework.web.domain.BaseEntity;

/**
 * 服务商表 zx_repair
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public class Repair extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 服务网点ID */
	private Integer repairId;
	/** 服务网点名称 */
	private String repairName;
	/** 负责人名称 */
	private String managerName;
	/** 负责人ID */
	private Integer managerId;
	/** 负责人电话 */
	private String managerPhone;
	/** 收款人 */
	private String bankReceiver;
	/** 收款账号 */
	private String bankAccount;
	/** 开户行 */
	private String bankName;
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
	/** 办公补贴 */
	private Float subsidyRate;
	/** 状态 */
	private String status;
	/** 是否删除 */
	private String delFlag;
	/** 服务设备数量 */
	private Integer deviceNum;
	/**  */
	private String createBy;
	/**  */
	private Date createTime;
	/**  */
	private String updateBy;
	/**  */
	private Date updateTime;

	public void setRepairId(Integer repairId) 
	{
		this.repairId = repairId;
	}

	public Integer getRepairId() 
	{
		return repairId;
	}
	public void setRepairName(String repairName) 
	{
		this.repairName = repairName;
	}

	public String getRepairName() 
	{
		return repairName;
	}
	public void setManagerName(String managerName) 
	{
		this.managerName = managerName;
	}

	public String getManagerName() 
	{
		return managerName;
	}
	public void setManagerId(Integer managerId) 
	{
		this.managerId = managerId;
	}

	public Integer getManagerId() 
	{
		return managerId;
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

	public Float getSubsidyRate() {
		return subsidyRate;
	}

	public void setSubsidyRate(Float subsidyRate) {
		this.subsidyRate = subsidyRate;
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
	public void setPromotionRate(Float promotionRate) 
	{
		this.promotionRate = promotionRate;
	}

	public Float getPromotionRate() 
	{
		return promotionRate;
	}
	public void setAdRate(Float adRate) 
	{
		this.adRate = adRate;
	}

	public Float getAdRate() 
	{
		return adRate;
	}
	public void setScanRate(Float scanRate) 
	{
		this.scanRate = scanRate;
	}

	public Float getScanRate() 
	{
		return scanRate;
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
	public void setDeviceNum(Integer deviceNum) 
	{
		this.deviceNum = deviceNum;
	}

	public Integer getDeviceNum() 
	{
		return deviceNum;
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

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("repairId", getRepairId())
            .append("repairName", getRepairName())
            .append("managerName", getManagerName())
            .append("managerId", getManagerId())
            .append("managerPhone", getManagerPhone())
            .append("bankReceiver", getBankReceiver())
            .append("bankAccount", getBankAccount())
            .append("bankName", getBankName())
            .append("promotionRate", getPromotionRate())
            .append("adRate", getAdRate())
            .append("scanRate", getScanRate())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("deviceNum", getDeviceNum())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
