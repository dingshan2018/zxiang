package com.zxiang.project.advertise.adPriceCfg.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zxiang.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 投放价格表 zx_ad_price_cfg
 * 
 * @author ZXiang
 * @date 2018-09-24
 */
public class AdPriceCfg extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer priceCfgId;
	/** 场所类型 */
	private String placeGrade;
	/** 时段类型 */
	private String dailyGrade;
	/** 地区等级 */
	private String areaGrade;
	/** 投放位置 */
	private String position;
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

	public void setPriceCfgId(Integer priceCfgId) 
	{
		this.priceCfgId = priceCfgId;
	}

	public Integer getPriceCfgId() 
	{
		return priceCfgId;
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
	public void setPosition(String position) 
	{
		this.position = position;
	}

	public String getPosition() 
	{
		return position;
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
            .append("priceCfgId", getPriceCfgId())
            .append("placeGrade", getPlaceGrade())
            .append("dailyGrade", getDailyGrade())
            .append("areaGrade", getAreaGrade())
            .append("position", getPosition())
            .append("isDel", getIsDel())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
