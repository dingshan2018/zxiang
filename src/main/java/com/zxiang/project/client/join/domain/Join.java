package com.zxiang.project.client.join.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zxiang.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 加盟商表 zx_join
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public class Join extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 加盟ID */
	private Integer joinId;
	/** 加盟商名称 */
	private String joinerName;
	/** 加盟机主ID */
	private Integer joinerId;
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
	/** 是否删除 */
	private String delFlag;
	/** 推广收益 */
	private Float promotionRate;
	/** 广告收益 */
	private Float adRate;
	/** 扫码收益 */
	private Float scanRate;
	/**  */
	private String createBy;
	/**  */
	private Date createTime;
	/**  */
	private String updateBy;
	/**  */
	private Date updateTime;

	public void setJoinId(Integer joinId) 
	{
		this.joinId = joinId;
	}

	public Integer getJoinId() 
	{
		return joinId;
	}
	public void setJoinerName(String joinerName) 
	{
		this.joinerName = joinerName;
	}

	public String getJoinerName() 
	{
		return joinerName;
	}
	public void setJoinerId(Integer joinerId) 
	{
		this.joinerId = joinerId;
	}

	public Integer getJoinerId() 
	{
		return joinerId;
	}
	public void setDeviceNum(Integer deviceNum) 
	{
		this.deviceNum = deviceNum;
	}

	public Integer getDeviceNum() 
	{
		return deviceNum;
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
	public void setDelFlag(String delFlag) 
	{
		this.delFlag = delFlag;
	}

	public String getDelFlag() 
	{
		return delFlag;
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
            .append("joinId", getJoinId())
            .append("joinerName", getJoinerName())
            .append("joinerId", getJoinerId())
            .append("deviceNum", getDeviceNum())
            .append("bankAccount", getBankAccount())
            .append("bankReceiver", getBankReceiver())
            .append("bankName", getBankName())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("promotionRate", getPromotionRate())
            .append("adRate", getAdRate())
            .append("scanRate", getScanRate())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
