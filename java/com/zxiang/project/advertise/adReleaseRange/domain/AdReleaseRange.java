package com.zxiang.project.advertise.adReleaseRange.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zxiang.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 广告投放范围表 zx_ad_release_range
 * 
 * @author ZXiang
 * @date 2018-11-07
 */
public class AdReleaseRange extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer adReleaseRangeId;
	/** 广告投放ID */
	private Integer adScheduleId;
	/** 投放类型0全部；1按地区；2按场所 */
	private String releaseType;
	/** 省份 */
	private Integer province;
	/** 城市 */
	private Integer city;
	/** 区县 */
	private Integer county;
	/** 场所 */
	private Integer placeGrade;
	/** 投放设备 */
	private String devices;
	/** 备注 */
	private String remark;
	/** 创建者 */
	private String createBy;
	/** 创建时间 */
	private Date createTime;
	/** 修改者 */
	private String updateBy;
	/** 修改时间 */
	private Date updateTime;

	public void setAdReleaseRangeId(Integer adReleaseRangeId) 
	{
		this.adReleaseRangeId = adReleaseRangeId;
	}

	public Integer getAdReleaseRangeId() 
	{
		return adReleaseRangeId;
	}
	public void setAdScheduleId(Integer adScheduleId) 
	{
		this.adScheduleId = adScheduleId;
	}

	public Integer getAdScheduleId() 
	{
		return adScheduleId;
	}
	public void setReleaseType(String releaseType) 
	{
		this.releaseType = releaseType;
	}

	public String getReleaseType() 
	{
		return releaseType;
	}
	public void setProvince(Integer province) 
	{
		this.province = province;
	}

	public Integer getProvince() 
	{
		return province;
	}
	public void setCity(Integer city) 
	{
		this.city = city;
	}

	public Integer getCity() 
	{
		return city;
	}
	public void setCounty(Integer county) 
	{
		this.county = county;
	}

	public Integer getCounty() 
	{
		return county;
	}
	public void setPlaceGrade(Integer placeGrade) 
	{
		this.placeGrade = placeGrade;
	}

	public Integer getPlaceGrade() 
	{
		return placeGrade;
	}
	public void setDevices(String devices) 
	{
		this.devices = devices;
	}

	public String getDevices() 
	{
		return devices;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
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
            .append("adReleaseRangeId", getAdReleaseRangeId())
            .append("adScheduleId", getAdScheduleId())
            .append("releaseType", getReleaseType())
            .append("province", getProvince())
            .append("city", getCity())
            .append("county", getCounty())
            .append("placeGrade", getPlaceGrade())
            .append("devices", getDevices())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
