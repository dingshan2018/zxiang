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
	    
	    /** 合作客户名称 */
	    private String coperatorName;
	    /** 合作类型 */
	    private String coperatorType;
	    /** 统计日期 */
	    private Date sumDate;
	    /** 视频广告基数 */
	    private Double adIncome;
	    /** 轮播广告基数 */
	    private Double adCarouselIncome;
	    /** 扫码基数 */
	    private Double scanIncome;
	    /** 直推机子数量 */
	    private Integer promDirectIncome;
	    /** 间推机子数量 */
	    private Integer promIndirectIncome;
	    
	    /** 推广广告基数 */
	    private Double promotionIncome;
	    
	    /** 出纸数量 */
	    private Integer paperIncome;
	    /** 推广二维码广告基数 */
	    private Integer promPaperIncome;
	    /** 直推代理基数 */
	    private Double directAgentIncome;
	    /** 招商金额 */
	    private Double subsidyIncome;
	    /** 广告收入值 */
	    private Double adIncomeRate;
	    /** 推广收入值 */
	    private Double promotionIncomeRate;
	    /** 扫码收入值 */
	    private Double scanIncomeRate;
	    /** 办公补贴值 */
	    private Double subsidyIncomeRate;
	    /** 视频广告系数 */
	    private Float adRate;
	    /** 轮播广告系数 */
	    private Float adCarouselRate;
	    /** 二维码广告系数 */
	    private Float scanRate;
	    /** 直推机子分润 */
	    private Float promDirectRate;
	    /** 间推机子分润 */
	    private Float promIndirectRate;
	    /** 推广二维码广告系数 */
	    private Float promPaperRate;
	    /** 推广广告系数 */
	    private Float promotionRate;
	    /** 直推代理分润系数 */
	    private Float directAgentRate;
	    /** 服务出纸系数 */
	    private Float serveRate;
	    /** 办公补贴 */
	    private Float subsidyRate;
	    /** 退款收入 */
	    private Double refundIncome;
	    /** 退款收入值 */
	    private Double refundIncomeRate;

	    private String createBy;

	    private Date createTime;

	    private String updateBy;

	    private Date updateTime;
	    
	    public Integer getIncomeId() {
	        return incomeId;
	    }

	    public void setIncomeId(Integer incomeId) {
	        this.incomeId = incomeId;
	    }

	    public Integer getCoperatorId() {
	        return coperatorId;
	    }

	    public void setCoperatorId(Integer coperatorId) {
	        this.coperatorId = coperatorId;
	    }

	    public String getCoperatorType() {
	        return coperatorType;
	    }

	    public void setCoperatorType(String coperatorType) {
	        this.coperatorType = coperatorType == null ? null : coperatorType.trim();
	    }

	    public Date getSumDate() {
	        return sumDate;
	    }

	    public void setSumDate(Date sumDate) {
	        this.sumDate = sumDate;
	    }

	    public Double getAdIncome() {
	        return adIncome;
	    }

	    public void setAdIncome(Double adIncome) {
	        this.adIncome = adIncome;
	    }

	    public Double getAdCarouselIncome() {
	        return adCarouselIncome;
	    }

	    public void setAdCarouselIncome(Double adCarouselIncome) {
	        this.adCarouselIncome = adCarouselIncome;
	    }

	    public Double getScanIncome() {
	        return scanIncome;
	    }

	    public void setScanIncome(Double scanIncome) {
	        this.scanIncome = scanIncome;
	    }

	    public Integer getPromDirectIncome() {
	        return promDirectIncome;
	    }

	    public void setPromDirectIncome(Integer promDirectIncome) {
	        this.promDirectIncome = promDirectIncome;
	    }

	    public Integer getPromIndirectIncome() {
	        return promIndirectIncome;
	    }

	    public void setPromIndirectIncome(Integer promIndirectIncome) {
	        this.promIndirectIncome = promIndirectIncome;
	    }

	    public Integer getPaperIncome() {
	        return paperIncome;
	    }

	    public void setPaperIncome(Integer paperIncome) {
	        this.paperIncome = paperIncome;
	    }

	

	    public Double getDirectAgentIncome() {
			return directAgentIncome;
		}

		public void setDirectAgentIncome(Double directAgentIncome) {
			this.directAgentIncome = directAgentIncome;
		}

		public Double getSubsidyIncome() {
	        return subsidyIncome;
	    }

	    public void setSubsidyIncome(Double subsidyIncome) {
	        this.subsidyIncome = subsidyIncome;
	    }

	    public Double getAdIncomeRate() {
	        return adIncomeRate;
	    }

	    public void setAdIncomeRate(Double adIncomeRate) {
	        this.adIncomeRate = adIncomeRate;
	    }

	    public Double getPromotionIncomeRate() {
	        return promotionIncomeRate;
	    }

	    public void setPromotionIncomeRate(Double promotionIncomeRate) {
	        this.promotionIncomeRate = promotionIncomeRate;
	    }

	    public Double getScanIncomeRate() {
	        return scanIncomeRate;
	    }

	    public void setScanIncomeRate(Double scanIncomeRate) {
	        this.scanIncomeRate = scanIncomeRate;
	    }

	    public Float getAdRate() {
	        return adRate;
	    }

	    public void setAdRate(Float adRate) {
	        this.adRate = adRate;
	    }

	    public Float getAdCarouselRate() {
	        return adCarouselRate;
	    }

	    public void setAdCarouselRate(Float adCarouselRate) {
	        this.adCarouselRate = adCarouselRate;
	    }

	    public Float getScanRate() {
	        return scanRate;
	    }

	    public void setScanRate(Float scanRate) {
	        this.scanRate = scanRate;
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

	    public Float getPromotionRate() {
	        return promotionRate;
	    }

	    public void setPromotionRate(Float promotionRate) {
	        this.promotionRate = promotionRate;
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

	    public Double getRefundIncome() {
	        return refundIncome;
	    }

	    public void setRefundIncome(Double refundIncome) {
	        this.refundIncome = refundIncome;
	    }

	    public Double getRefundIncomeRate() {
	        return refundIncomeRate;
	    }

	    public void setRefundIncomeRate(Double refundIncomeRate) {
	        this.refundIncomeRate = refundIncomeRate;
	    }

	    public String getCreateBy() {
	        return createBy;
	    }

	    public void setCreateBy(String createBy) {
	        this.createBy = createBy == null ? null : createBy.trim();
	    }

	    public Date getCreateTime() {
	        return createTime;
	    }

	    public void setCreateTime(Date createTime) {
	        this.createTime = createTime;
	    }

	    public String getUpdateBy() {
	        return updateBy;
	    }

	    public void setUpdateBy(String updateBy) {
	        this.updateBy = updateBy == null ? null : updateBy.trim();
	    }

	    public Date getUpdateTime() {
	        return updateTime;
	    }

	    public void setUpdateTime(Date updateTime) {
	        this.updateTime = updateTime;
	    }

		public Double getPromotionIncome() {
			return promotionIncome;
		}

		public void setPromotionIncome(Double promotionIncome) {
			this.promotionIncome = promotionIncome;
		}

		public Integer getPromPaperIncome() {
			return promPaperIncome;
		}

		public void setPromPaperIncome(Integer promPaperIncome) {
			this.promPaperIncome = promPaperIncome;
		}

		public Double getSubsidyIncomeRate() {
			return subsidyIncomeRate;
		}

		public void setSubsidyIncomeRate(Double subsidyIncomeRate) {
			this.subsidyIncomeRate = subsidyIncomeRate;
		}

		public String getCoperatorName() {
			return coperatorName;
		}

		public void setCoperatorName(String coperatorName) {
			this.coperatorName = coperatorName;
		}

	
		
		

}
