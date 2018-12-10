package com.zxiang.project.system.area.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zxiang.framework.web.domain.BaseEntity;

/**
 * 公共：区域表 sys_area
 * 
 * @author ZXiang
 * @date 2018-09-21
 */
public class Area extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 区域编码 */
	private Long id;
	/** 父级ID */
	private Long pid;
	/** 城市编码 */
	private Integer homecity;
	/** 层级 */
	private Integer level;
	/** 排序号 */
	private Integer porder;
	/** 区域名称 */
	private String pname;
	/** 区域简称 */
	private String psname;
	/** 经度 */
	private Double lon;
	/** 纬度 */
	private Double lat;
	/** 有效标识：1、有效；0、无效 */
	private Integer isValid;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setPid(Long pid) 
	{
		this.pid = pid;
	}

	public Long getPid() 
	{
		return pid;
	}
	public void setHomecity(Integer homecity) 
	{
		this.homecity = homecity;
	}

	public Integer getHomecity() 
	{
		return homecity;
	}
	public void setLevel(Integer level) 
	{
		this.level = level;
	}

	public Integer getLevel() 
	{
		return level;
	}
	public void setPorder(Integer porder) 
	{
		this.porder = porder;
	}

	public Integer getPorder() 
	{
		return porder;
	}
	public void setPname(String pname) 
	{
		this.pname = pname;
	}

	public String getPname() 
	{
		return pname;
	}
	public void setPsname(String psname) 
	{
		this.psname = psname;
	}

	public String getPsname() 
	{
		return psname;
	}
	public void setLon(Double lon) 
	{
		this.lon = lon;
	}

	public Double getLon() 
	{
		return lon;
	}
	public void setLat(Double lat) 
	{
		this.lat = lat;
	}

	public Double getLat() 
	{
		return lat;
	}
	public void setIsValid(Integer isValid) 
	{
		this.isValid = isValid;
	}

	public Integer getIsValid() 
	{
		return isValid;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("pid", getPid())
            .append("homecity", getHomecity())
            .append("level", getLevel())
            .append("porder", getPorder())
            .append("pname", getPname())
            .append("psname", getPsname())
            .append("lon", getLon())
            .append("lat", getLat())
            .append("isValid", getIsValid())
            .toString();
    }
}
