package com.zxiang.project.advertise.adPriceTimer.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zxiang.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 广告价格时段表 zx_ad_price_timer
 * 
 * @author ZXiang
 * @date 2018-09-24
 */
public class AdPriceTimer extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer adPriceId;
	/**  */
	private Integer priceCfgId;
	/**  */
	private Date releaseBeginTime;
	/**  */
	private Date releaseEndTime;
	/**  */
	private Float price;
	/**  */
	private String createBy;
	/**  */
	private Date createTime;
	/**  */
	private String updateBy;
	/**  */
	private Date updateTime;

	public void setAdPriceId(Integer adPriceId) 
	{
		this.adPriceId = adPriceId;
	}

	public Integer getAdPriceId() 
	{
		return adPriceId;
	}
	public void setPriceCfgId(Integer priceCfgId) 
	{
		this.priceCfgId = priceCfgId;
	}

	public Integer getPriceCfgId() 
	{
		return priceCfgId;
	}
	public void setReleaseBeginTime(Date releaseBeginTime) 
	{
		this.releaseBeginTime = releaseBeginTime;
	}

	public Date getReleaseBeginTime() 
	{
		return releaseBeginTime;
	}
	public void setReleaseEndTime(Date releaseEndTime) 
	{
		this.releaseEndTime = releaseEndTime;
	}

	public Date getReleaseEndTime() 
	{
		return releaseEndTime;
	}
	public void setPrice(Float price) 
	{
		this.price = price;
	}

	public Float getPrice() 
	{
		return price;
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
            .append("adPriceId", getAdPriceId())
            .append("priceCfgId", getPriceCfgId())
            .append("releaseBeginTime", getReleaseBeginTime())
            .append("releaseEndTime", getReleaseEndTime())
            .append("price", getPrice())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
