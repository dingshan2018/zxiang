package com.zxiang.project.settle.coefficient.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zxiang.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 系数配置表 zx_coefficient
 * 
 * @author ZXiang
 * @date 2019-05-27
 */
public class Coefficient extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键ID */
	private Integer id;
	/** 类型 01:机主 02:一级代理  03:二级代理  04:服务商 */
	private String type;
	/** 是否删除 */
	private String delFlag;
	/** 视频广告系数 */
	private Float adRate;
	/** 轮播广告系数 */
	private Float adCarouselRate;
	/** 扫码收益 */
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

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setDelFlag(String delFlag) 
	{
		this.delFlag = delFlag;
	}

	public String getDelFlag() 
	{
		return delFlag;
	}
	public void setAdRate(Float adRate) 
	{
		this.adRate = adRate;
	}

	public Float getAdRate() 
	{
		return adRate;
	}
	public void setAdCarouselRate(Float adCarouselRate) 
	{
		this.adCarouselRate = adCarouselRate;
	}

	public Float getAdCarouselRate() 
	{
		return adCarouselRate;
	}
	public void setScanRate(Float scanRate) 
	{
		this.scanRate = scanRate;
	}

	public Float getScanRate() 
	{
		return scanRate;
	}
	public void setPromDirectRate(Float promDirectRate) 
	{
		this.promDirectRate = promDirectRate;
	}

	public Float getPromDirectRate() 
	{
		return promDirectRate;
	}
	public void setPromIndirectRate(Float promIndirectRate) 
	{
		this.promIndirectRate = promIndirectRate;
	}

	public Float getPromIndirectRate() 
	{
		return promIndirectRate;
	}
	public void setPromPaperRate(Float promPaperRate) 
	{
		this.promPaperRate = promPaperRate;
	}

	public Float getPromPaperRate() 
	{
		return promPaperRate;
	}
	public void setPromotionRate(Float promotionRate) 
	{
		this.promotionRate = promotionRate;
	}

	public Float getPromotionRate() 
	{
		return promotionRate;
	}
	public void setDirectAgentRate(Float directAgentRate) 
	{
		this.directAgentRate = directAgentRate;
	}

	public Float getDirectAgentRate() 
	{
		return directAgentRate;
	}
	public void setServeRate(Float serveRate) 
	{
		this.serveRate = serveRate;
	}

	public Float getServeRate() 
	{
		return serveRate;
	}
	public void setSubsidyRate(Float subsidyRate) 
	{
		this.subsidyRate = subsidyRate;
	}

	public Float getSubsidyRate() 
	{
		return subsidyRate;
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
            .append("id", getId())
            .append("type", getType())
            .append("delFlag", getDelFlag())
            .append("adRate", getAdRate())
            .append("adCarouselRate", getAdCarouselRate())
            .append("scanRate", getScanRate())
            .append("promDirectRate", getPromDirectRate())
            .append("promIndirectRate", getPromIndirectRate())
            .append("promPaperRate", getPromPaperRate())
            .append("promotionRate", getPromotionRate())
            .append("directAgentRate", getDirectAgentRate())
            .append("serveRate", getServeRate())
            .append("subsidyRate", getSubsidyRate())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
