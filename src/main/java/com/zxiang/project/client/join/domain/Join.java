package com.zxiang.project.client.join.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.zxiang.framework.web.domain.BaseEntity;

/**
 * 机主表 zx_join
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public class Join extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 机主ID */
	private Integer joinId;
	/** 机主名称 */
	private String joinerName;
	/** 机主机主ID */
	private Integer joinerId;
	/** 管理者姓名 */
	private String managerName;
	/** 管理电话 */
	private String managerPhone;
	/** 投放设备数量 */
	private Integer deviceNum;
	/** 收款账号 */
	private String bankAccount;
	/** 收款人 */
	private String bankReceiver;
	/** 开户行 */
	private String bankName;
	/** 状态 */
	private String status;
	/** 购机推荐人 **/
	private Integer buyerId;
	/** 购机推荐人主体id **/
	private Integer puserId;
	/** 购机推荐人类型 **/
	private String userType;
	/** 是否删除 */
	private String delFlag;
	/** 余额 **/
	private BigDecimal balance;
	/** 冻结余额 **/
	private BigDecimal frozenBalance;
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
	/** 服务出纸收益 */
	private Float serveRate;

	/**  */
	private String createBy;
	/**  */
	private Date createTime;
	/**  */
	private String updateBy;
	/**  */
	private Date updateTime;
	/** 直推人 **/
	private String buyerName;

	public void setJoinId(Integer joinId) {
		this.joinId = joinId;
	}

	public Integer getJoinId() {
		return joinId;
	}

	public void setJoinerName(String joinerName) {
		this.joinerName = joinerName;
	}

	public String getJoinerName() {
		return joinerName;
	}

	public void setJoinerId(Integer joinerId) {
		this.joinerId = joinerId;
	}

	public Integer getJoinerId() {
		return joinerId;
	}

	public void setDeviceNum(Integer deviceNum) {
		this.deviceNum = deviceNum;
	}

	public Integer getDeviceNum() {
		return deviceNum;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
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

	public Float getServeRate() {
		return serveRate;
	}

	public void setServeRate(Float serveRate) {
		this.serveRate = serveRate;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankReceiver(String bankReceiver) {
		this.bankReceiver = bankReceiver;
	}

	public String getBankReceiver() {
		return bankReceiver;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setPromotionRate(Float promotionRate) {
		this.promotionRate = promotionRate;
	}

	public Float getPromotionRate() {
		return promotionRate;
	}

	public void setAdRate(Float adRate) {
		this.adRate = adRate;
	}

	public Float getAdRate() {
		return adRate;
	}

	public void setScanRate(Float scanRate) {
		this.scanRate = scanRate;
	}

	public Float getScanRate() {
		return scanRate;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getFrozenBalance() {
		return frozenBalance;
	}

	public void setFrozenBalance(BigDecimal frozenBalance) {
		this.frozenBalance = frozenBalance;
	}

	public Integer getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}

	public Integer getPuserId() {
		return puserId;
	}

	public void setPuserId(Integer puserId) {
		this.puserId = puserId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	@Override
	public String toString() {
		return "Join [joinId=" + joinId + ", joinerName=" + joinerName + ", joinerId=" + joinerId + ", managerName="
				+ managerName + ", managerPhone=" + managerPhone + ", deviceNum=" + deviceNum + ", bankAccount="
				+ bankAccount + ", bankReceiver=" + bankReceiver + ", bankName=" + bankName + ", status=" + status
				+ ", buyerId=" + buyerId + ", puserId=" + puserId + ", userType=" + userType + ", delFlag=" + delFlag
				+ ", balance=" + balance + ", frozenBalance=" + frozenBalance + ", adRate=" + adRate
				+ ", adCarouselRate=" + adCarouselRate + ", scanRate=" + scanRate + ", promDirectRate=" + promDirectRate
				+ ", promIndirectRate=" + promIndirectRate + ", promPaperRate=" + promPaperRate + ", promotionRate="
				+ promotionRate + ", serveRate=" + serveRate + ", createBy=" + createBy + ", createTime=" + createTime
				+ ", updateBy=" + updateBy + ", updateTime=" + updateTime + ", buyerName=" + buyerName + "]";
	}
}
