package com.zxiang.project.client.advertise.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.zxiang.framework.web.domain.BaseEntity;

/**
 * 广告商表 zx_advertise
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public class Advertise extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer advertiseId;
	/**  */
	private String advertisorName;
	/**  */
	private Integer managerId;
	/**  */
	private String managerName;
	/**  */
	private String managerPhone;
	/**  */
	private String business;
	/**  */
	private Integer releaseNum;
	/**  */
	private Integer lastScheduler;
	/**  */
	private String status;
	/**  */
	private String delFlag;
	/**  */
	private String grade;
	/**  */
	private Float cutOff;
	/**  */
	private String createBy;
	/**  */
	private Date createTime;
	/**  */
	private String updateBy;
	/**  */
	private Date updateTime;
	/** 收款人 */
	private String bankReceiver;
	/** 收款账号 */
	private String bankAccount;
	/** 收款人姓名 */
	private String bankName;
	/** 余额 **/
	private BigDecimal balance; 
	/** 冻结余额 **/
	private BigDecimal frozenBalance; 
	

	public void setAdvertiseId(Integer advertiseId) 
	{
		this.advertiseId = advertiseId;
	}

	public Integer getAdvertiseId() 
	{
		return advertiseId;
	}
	public void setAdvertisorName(String advertisorName) 
	{
		this.advertisorName = advertisorName;
	}

	public String getAdvertisorName() 
	{
		return advertisorName;
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
	public void setBusiness(String business) 
	{
		this.business = business;
	}

	public String getBusiness() 
	{
		return business;
	}
	public void setReleaseNum(Integer releaseNum) 
	{
		this.releaseNum = releaseNum;
	}

	public Integer getReleaseNum() 
	{
		return releaseNum;
	}
	public void setLastScheduler(Integer lastScheduler) 
	{
		this.lastScheduler = lastScheduler;
	}

	public Integer getLastScheduler() 
	{
		return lastScheduler;
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
	public void setGrade(String grade) 
	{
		this.grade = grade;
	}

	public String getGrade() 
	{
		return grade;
	}
	public void setCutOff(Float cutOff) 
	{
		this.cutOff = cutOff;
	}

	public Float getCutOff() 
	{
		return cutOff;
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

	public String getBankReceiver() {
		return bankReceiver;
	}

	public void setBankReceiver(String bankReceiver) {
		this.bankReceiver = bankReceiver;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}


}
