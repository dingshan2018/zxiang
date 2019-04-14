package com.zxiang.project.client.fundLog.domain;

import java.util.Date;

import com.zxiang.framework.web.domain.BaseEntity;

/**
 * 资金流水表 zx_fund_log
 * 
 * @author ZXiang
 * @date 2018-12-05
 */
public class FundLog extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer payId;
	/** 主体id */
	private Integer clientId;
	/** 主体类型 */
	private String clientType;
	/** 流水号 */
	private String serial;
	/** 金额 */
	private String totalFee;
	/** 余额 */
	private String balance;
	/** 内容 */
	private String content;
	/** 类型：1:充值,2:广告付费,3:提现 */
	private String type;
	/** 1:成功,2:失败 */
	private String status;
	/** 创建时间 */
	private Date createTime;
	/** 备注 */
	private String remark;
	
	/**
	 * 本期总金额
	 */
	private String totalBalance;
	
	/**
	 * 本期冻结金额
	 */
	private String freezeBalance;
	
//	/**
//	 * 差异可用余额
//	 */
//	private String deltaBalance;
//	
//	/**
//	 * 差异当期金额
//	 */
//	private String deltaTotalBalance;
//	
//	/**
//	 * 当期差异的冻结金额
//	 */
//	private String deltaFreezeBalance;
	
	private Date beginTime;
	
	private Date endTime;

	public void setPayId(Integer payId) 
	{
		this.payId = payId;
	}

	public Integer getPayId() 
	{
		return payId;
	}
	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public void setSerial(String serial) 
	{
		this.serial = serial;
	}

	public String getSerial() 
	{
		return serial;
	}
	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public void setContent(String content) 
	{
		this.content = content;
	}

	public String getContent() 
	{
		return content;
	}
	public void setType(String type) 
	{
		this.type = type;
	}

	public String getType() 
	{
		return type;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}

	public String getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(String totalBalance) {
		this.totalBalance = totalBalance;
	}

	public String getFreezeBalance() {
		return freezeBalance;
	}

	public void setFreezeBanlance(String freezeBalance) {
		this.freezeBalance = freezeBalance;
	}

//	public String getDeltaBalance() {
//		return deltaBalance;
//	}
//
//	public void setDeltaBalance(String deltaBalance) {
//		this.deltaBalance = deltaBalance;
//	}
//
//	public String getDeltaTotalBalance() {
//		return deltaTotalBalance;
//	}
//
//	public void setDeltaTotalBalance(String deltaTotalBalance) {
//		this.deltaTotalBalance = deltaTotalBalance;
//	}
//
//	public String getDeltaFreezeBalance() {
//		return deltaFreezeBalance;
//	}
//
//	public void setDeltaFreezeBalance(String deltaFreezeBalance) {
//		this.deltaFreezeBalance = deltaFreezeBalance;
//	}

	public void setFreezeBalance(String freezeBalance) {
		this.freezeBalance = freezeBalance;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	
	
}
