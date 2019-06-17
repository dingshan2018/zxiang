package com.zxiang.project.client.fundLog.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.zxiang.framework.web.domain.BaseEntity;

/**
 * 提现记录表 zx_withdraw_deposit
 * 
 * @author ZXiang
 * @date 2018-12-07
 */
public class WithdrawDeposit extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 资金流水ID */
	private Integer fundLogId;
	/** 流水号 */
	private String serial;
	/** 主体id */
	private Integer clientId;
	/** 主体类型 */
	private String clientType;
	/** 主体名称 */
	private String clientName;
	/** 提现金额 */
	private BigDecimal money;
	/** 余额 */
	private BigDecimal balance;
	/** 管理者电话 */
	private String managerPhone;
	/** 收款账号 */
	private String bankAccount;
	/** 收款人 */
	private String bankReceiver;
	/** 开户行 */
	private String bankName;
	/** 1:提现中,2:转账成功,3:转账失败 */
	private String status;
	/** 创建时间 */
	private Date createTime;
	/** 付款者 */
	private String payer;
	/** 付款时间 */
	private Date payTime;
	/** 备注 */
	private String remark;
	private String beginTime;
	private String endTime;
	private String startPayTime;
	private String endPayTime;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setFundLogId(Integer fundLogId) 
	{
		this.fundLogId = fundLogId;
	}

	public Integer getFundLogId() 
	{
		return fundLogId;
	}
	public void setSerial(String serial) 
	{
		this.serial = serial;
	}

	public String getSerial() 
	{
		return serial;
	}
	public void setClientId(Integer clientId) 
	{
		this.clientId = clientId;
	}

	public Integer getClientId() 
	{
		return clientId;
	}
	public void setClientType(String clientType) 
	{
		this.clientType = clientType;
	}

	public String getClientType() 
	{
		return clientType;
	}
	public void setClientName(String clientName) 
	{
		this.clientName = clientName;
	}

	public String getClientName() 
	{
		return clientName;
	}
	public void setManagerPhone(String managerPhone) 
	{
		this.managerPhone = managerPhone;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getManagerPhone() 
	{
		return managerPhone;
	}
	public void setBankAccount(String bankAccount) 
	{
		this.bankAccount = bankAccount;
	}

	public String getBankAccount() 
	{
		return bankAccount;
	}
	public void setBankReceiver(String bankReceiver) 
	{
		this.bankReceiver = bankReceiver;
	}

	public String getBankReceiver() 
	{
		return bankReceiver;
	}
	public void setBankName(String bankName) 
	{
		this.bankName = bankName;
	}

	public String getBankName() 
	{
		return bankName;
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
	public void setPayer(String payer) 
	{
		this.payer = payer;
	}

	public String getPayer() 
	{
		return payer;
	}
	public void setPayTime(Date payTime) 
	{
		this.payTime = payTime;
	}

	public Date getPayTime() 
	{
		return payTime;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}

    public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartPayTime() {
		return startPayTime;
	}

	public void setStartPayTime(String startPayTime) {
		this.startPayTime = startPayTime;
	}

	public String getEndPayTime() {
		return endPayTime;
	}

	public void setEndPayTime(String endPayTime) {
		this.endPayTime = endPayTime;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("fundLogId", getFundLogId())
            .append("serial", getSerial())
            .append("clientId", getClientId())
            .append("clientType", getClientType())
            .append("clientName", getClientName())
            .append("money", getMoney())
            .append("balance", getBalance())
            .append("managerPhone", getManagerPhone())
            .append("bankAccount", getBankAccount())
            .append("bankReceiver", getBankReceiver())
            .append("bankName", getBankName())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("payer", getPayer())
            .append("payTime", getPayTime())
            .append("remark", getRemark())
            .toString();
    }
}
