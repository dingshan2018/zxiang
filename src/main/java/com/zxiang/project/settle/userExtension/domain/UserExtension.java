package com.zxiang.project.settle.userExtension.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zxiang.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 客户推广日统计表 zx_user_extension
 * 
 * @author ZXiang
 * @date 2018-11-26
 */
public class UserExtension extends BaseEntity
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
	/** 直推机子数量 */
	private Integer promDirectIncome;
	/** 间推机子数量 */
	private Integer promIndirectIncome;
	/** 推广二维码广告基数 */
	private Integer promPaperIncome;
	/** 推广广告基数 */
	private Double promotionIncome;
	/** 直推代理基数 */
	private Double directAgentIncome;
	/**  */
	private String createBy;
	/**  */
	private Date createTime;
	/**  */
	private String updateBy;
	/**  */
	private Date updateTime;
	/** 客户名称 */
	private String coperatorName;
	
	private Integer puserId;
	
	private String puserName;

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
	public void setPromDirectIncome(Integer promDirectIncome) 
	{
		this.promDirectIncome = promDirectIncome;
	}

	public Integer getPromDirectIncome() 
	{
		return promDirectIncome;
	}
	public void setPromIndirectIncome(Integer promIndirectIncome) 
	{
		this.promIndirectIncome = promIndirectIncome;
	}

	public Integer getPromIndirectIncome() 
	{
		return promIndirectIncome;
	}
	public void setPromPaperIncome(Integer promPaperIncome) 
	{
		this.promPaperIncome = promPaperIncome;
	}

	public Integer getPromPaperIncome() 
	{
		return promPaperIncome;
	}
	public void setPromotionIncome(Double promotionIncome) 
	{
		this.promotionIncome = promotionIncome;
	}

	public Double getPromotionIncome() 
	{
		return promotionIncome;
	}
	public void setDirectAgentIncome(Double directAgentIncome) 
	{
		this.directAgentIncome = directAgentIncome;
	}

	public Double getDirectAgentIncome() 
	{
		return directAgentIncome;
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
	public void setCoperatorName(String coperatorName) 
	{
		this.coperatorName = coperatorName;
	}

	public String getCoperatorName() 
	{
		return coperatorName;
	}
	

	public Integer getPuserId() {
		return puserId;
	}

	public void setPuserId(Integer puserId) {
		this.puserId = puserId;
	}

	public String getPuserName() {
		return puserName;
	}

	public void setPuserName(String puserName) {
		this.puserName = puserName;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("incomeId", getIncomeId())
            .append("coperatorId", getCoperatorId())
            .append("coperatorType", getCoperatorType())
            .append("sumDate", getSumDate())
            .append("promDirectIncome", getPromDirectIncome())
            .append("promIndirectIncome", getPromIndirectIncome())
            .append("promPaperIncome", getPromPaperIncome())
            .append("promotionIncome", getPromotionIncome())
            .append("directAgentIncome", getDirectAgentIncome())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("coperatorName", getCoperatorName())
            .toString();
    }
}
