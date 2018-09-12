package com.zxiang.project.advertise.adPrice.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zxiang.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 投放价格表 zx_ad_price
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public class AdPrice extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer priceId;
	/**  */
	private Float price;
	/**  */
	private String placeGrade;
	/**  */
	private String dailyGrade;
	/**  */
	private String areaGrade;
	/**  */
	private String isDel;
	/**  */
	private String createBy;
	/**  */
	private Date createTime;
	/**  */
	private String updateBy;
	/**  */
	private Date updateTime;

	public void setPriceId(Integer priceId) 
	{
		this.priceId = priceId;
	}

	public Integer getPriceId() 
	{
		return priceId;
	}
	public void setPrice(Float price) 
	{
		this.price = price;
	}

	public Float getPrice() 
	{
		return price;
	}
	public void setPlaceGrade(String placeGrade) 
	{
		this.placeGrade = placeGrade;
	}

	public String getPlaceGrade() 
	{
		return placeGrade;
	}
	public void setDailyGrade(String dailyGrade) 
	{
		this.dailyGrade = dailyGrade;
	}

	public String getDailyGrade() 
	{
		return dailyGrade;
	}
	public void setAreaGrade(String areaGrade) 
	{
		this.areaGrade = areaGrade;
	}

	public String getAreaGrade() 
	{
		return areaGrade;
	}
	public void setIsDel(String isDel) 
	{
		this.isDel = isDel;
	}

	public String getIsDel() 
	{
		return isDel;
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
            .append("priceId", getPriceId())
            .append("price", getPrice())
            .append("placeGrade", getPlaceGrade())
            .append("dailyGrade", getDailyGrade())
            .append("areaGrade", getAreaGrade())
            .append("isDel", getIsDel())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
