package com.zxiang.project.settle.userIncome.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zxiang.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 客户收入日统计表 zx_user_income
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public class UserIncome extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer incomeId;
	/** 合作客户ID */
	private Integer coperatorId;
	/** 合作类型 */
	private String coperatorType;
	/** 统计日期 */
	private Date sumDate;
	/** 广告基数 */
	private Double adIncome;
	/** 推广基数 */
	private Double promotionIncome;
	/** 扫码基数 */
	private Double scanIncome;
	/** 广告收入值 */
	private Double adIncomeRate;
	/** 推广收入值 */
	private Double promotionIncomeRate;
	/** 扫码收入值 */
	private Double scanIncomeRate;
	/** 广告收入系数 */
	private Float adRate;
	/** 推广收入系数 */
	private Float promotionRate;
	/** 扫码收入系数 */
	private Float scanRate;
	/** 退款收入 */
	private Double refundIncome;
	/** 退款收入值 */
	private Double refundIncomeRate;
	/**  */
	private String createBy;
	/**  */
	private Date createTime;
	/**  */
	private String updateBy;
	/**  */
	private Date updateTime;

	public void setIncomeId(Integer incomeId) 
	{
		this.incomeId = incomeId;
	}

	public Integer getIncomeId() 
	{
		return incomeId;
	}
	public void setCoperatorId(Integer coperatorId) 
	{
		this.coperatorId = coperatorId;
	}

	public Integer getCoperatorId() 
	{
		return coperatorId;
	}
	public void setCoperatorType(String coperatorType) 
	{
		this.coperatorType = coperatorType;
	}

	public String getCoperatorType() 
	{
		return coperatorType;
	}
	public void setSumDate(Date sumDate) 
	{
		this.sumDate = sumDate;
	}

	public Date getSumDate() 
	{
		return sumDate;
	}
	public void setAdIncome(Double adIncome) 
	{
		this.adIncome = adIncome;
	}

	public Double getAdIncome() 
	{
		return adIncome;
	}
	public void setPromotionIncome(Double promotionIncome) 
	{
		this.promotionIncome = promotionIncome;
	}

	public Double getPromotionIncome() 
	{
		return promotionIncome;
	}
	public void setScanIncome(Double scanIncome) 
	{
		this.scanIncome = scanIncome;
	}

	public Double getScanIncome() 
	{
		return scanIncome;
	}
	public void setAdIncomeRate(Double adIncomeRate) 
	{
		this.adIncomeRate = adIncomeRate;
	}

	public Double getAdIncomeRate() 
	{
		return adIncomeRate;
	}
	public void setPromotionIncomeRate(Double promotionIncomeRate) 
	{
		this.promotionIncomeRate = promotionIncomeRate;
	}

	public Double getPromotionIncomeRate() 
	{
		return promotionIncomeRate;
	}
	public void setScanIncomeRate(Double scanIncomeRate) 
	{
		this.scanIncomeRate = scanIncomeRate;
	}

	public Double getScanIncomeRate() 
	{
		return scanIncomeRate;
	}
	public void setAdRate(Float adRate) 
	{
		this.adRate = adRate;
	}

	public Float getAdRate() 
	{
		return adRate;
	}
	public void setPromotionRate(Float promotionRate) 
	{
		this.promotionRate = promotionRate;
	}

	public Float getPromotionRate() 
	{
		return promotionRate;
	}
	public void setScanRate(Float scanRate) 
	{
		this.scanRate = scanRate;
	}

	public Float getScanRate() 
	{
		return scanRate;
	}
	public void setRefundIncome(Double refundIncome) 
	{
		this.refundIncome = refundIncome;
	}

	public Double getRefundIncome() 
	{
		return refundIncome;
	}
	public void setRefundIncomeRate(Double refundIncomeRate) 
	{
		this.refundIncomeRate = refundIncomeRate;
	}

	public Double getRefundIncomeRate() 
	{
		return refundIncomeRate;
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
            .append("incomeId", getIncomeId())
            .append("coperatorId", getCoperatorId())
            .append("coperatorType", getCoperatorType())
            .append("sumDate", getSumDate())
            .append("adIncome", getAdIncome())
            .append("promotionIncome", getPromotionIncome())
            .append("scanIncome", getScanIncome())
            .append("adIncomeRate", getAdIncomeRate())
            .append("promotionIncomeRate", getPromotionIncomeRate())
            .append("scanIncomeRate", getScanIncomeRate())
            .append("adRate", getAdRate())
            .append("promotionRate", getPromotionRate())
            .append("scanRate", getScanRate())
            .append("refundIncome", getRefundIncome())
            .append("refundIncomeRate", getRefundIncomeRate())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
