package com.zxiang.project.client.advertise.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zxiang.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 广告商表 zx_advertise
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public class Advertise extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer advertiseId;
	/**  */
	private String advertisorName;
	/**  */
	private Integer managerId;
	/**  */
	private String managerName;
	/**  */
	private String managerPhone;
	/**  */
	private String business;
	/**  */
	private Integer releaseNum;
	/**  */
	private Integer lastScheduler;
	/**  */
	private String status;
	/**  */
	private String delFlag;
	/**  */
	private String grade;
	/**  */
	private Float cutOff;
	/**  */
	private String createBy;
	/**  */
	private Date createTime;
	/**  */
	private String updateBy;
	/**  */
	private Date updateTime;

	public void setAdvertiseId(Integer advertiseId) 
	{
		this.advertiseId = advertiseId;
	}

	public Integer getAdvertiseId() 
	{
		return advertiseId;
	}
	public void setAdvertisorName(String advertisorName) 
	{
		this.advertisorName = advertisorName;
	}

	public String getAdvertisorName() 
	{
		return advertisorName;
	}
	public void setManagerId(Integer managerId) 
	{
		this.managerId = managerId;
	}

	public Integer getManagerId() 
	{
		return managerId;
	}
	public void setManagerName(String managerName) 
	{
		this.managerName = managerName;
	}

	public String getManagerName() 
	{
		return managerName;
	}
	public void setManagerPhone(String managerPhone) 
	{
		this.managerPhone = managerPhone;
	}

	public String getManagerPhone() 
	{
		return managerPhone;
	}
	public void setBusiness(String business) 
	{
		this.business = business;
	}

	public String getBusiness() 
	{
		return business;
	}
	public void setReleaseNum(Integer releaseNum) 
	{
		this.releaseNum = releaseNum;
	}

	public Integer getReleaseNum() 
	{
		return releaseNum;
	}
	public void setLastScheduler(Integer lastScheduler) 
	{
		this.lastScheduler = lastScheduler;
	}

	public Integer getLastScheduler() 
	{
		return lastScheduler;
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
	public void setGrade(String grade) 
	{
		this.grade = grade;
	}

	public String getGrade() 
	{
		return grade;
	}
	public void setCutOff(Float cutOff) 
	{
		this.cutOff = cutOff;
	}

	public Float getCutOff() 
	{
		return cutOff;
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
            .append("advertiseId", getAdvertiseId())
            .append("advertisorName", getAdvertisorName())
            .append("managerId", getManagerId())
            .append("managerName", getManagerName())
            .append("managerPhone", getManagerPhone())
            .append("business", getBusiness())
            .append("releaseNum", getReleaseNum())
            .append("lastScheduler", getLastScheduler())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("grade", getGrade())
            .append("cutOff", getCutOff())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
