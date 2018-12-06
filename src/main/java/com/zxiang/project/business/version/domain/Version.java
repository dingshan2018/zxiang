package com.zxiang.project.business.version.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zxiang.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 版本表 sys_version
 * 
 * @author ZXiang
 * @date 2018-12-06
 */
public class Version extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 版本ID */
	private Integer sysVerId;
	/** 系统编码 */
	private String systemCode;
	/** 版本号 */
	private String sysVerCode;
	/** 版本主题 */
	private String sysVerSubject;
	/** 版本更新信息 */
	private String sysVerInfo;
	/** 启用时间 */
	private Date effDate;
	/** 失效时间 */
	private Date expDate;
	/** 升级类型，1000：强制升级；2000：可选升级 */
	private String subject;
	/** 创建者 */
	private String createBy;
	/** 创建时间 */
	private Date createDate;
	/** 备用字段 */
	private String remark;
	/** 是否删除 */
	private String delFlag;

	public void setSysVerId(Integer sysVerId) 
	{
		this.sysVerId = sysVerId;
	}

	public Integer getSysVerId() 
	{
		return sysVerId;
	}
	public void setSystemCode(String systemCode) 
	{
		this.systemCode = systemCode;
	}

	public String getSystemCode() 
	{
		return systemCode;
	}
	public void setSysVerCode(String sysVerCode) 
	{
		this.sysVerCode = sysVerCode;
	}

	public String getSysVerCode() 
	{
		return sysVerCode;
	}
	public void setSysVerSubject(String sysVerSubject) 
	{
		this.sysVerSubject = sysVerSubject;
	}

	public String getSysVerSubject() 
	{
		return sysVerSubject;
	}
	public void setSysVerInfo(String sysVerInfo) 
	{
		this.sysVerInfo = sysVerInfo;
	}

	public String getSysVerInfo() 
	{
		return sysVerInfo;
	}
	public void setEffDate(Date effDate) 
	{
		this.effDate = effDate;
	}

	public Date getEffDate() 
	{
		return effDate;
	}
	public void setExpDate(Date expDate) 
	{
		this.expDate = expDate;
	}

	public Date getExpDate() 
	{
		return expDate;
	}
	public void setSubject(String subject) 
	{
		this.subject = subject;
	}

	public String getSubject() 
	{
		return subject;
	}
	public void setCreateBy(String createBy) 
	{
		this.createBy = createBy;
	}

	public String getCreateBy() 
	{
		return createBy;
	}
	public void setCreateDate(Date createDate) 
	{
		this.createDate = createDate;
	}

	public Date getCreateDate() 
	{
		return createDate;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}
	public void setDelFlag(String delFlag) 
	{
		this.delFlag = delFlag;
	}

	public String getDelFlag() 
	{
		return delFlag;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sysVerId", getSysVerId())
            .append("systemCode", getSystemCode())
            .append("sysVerCode", getSysVerCode())
            .append("sysVerSubject", getSysVerSubject())
            .append("sysVerInfo", getSysVerInfo())
            .append("effDate", getEffDate())
            .append("expDate", getExpDate())
            .append("subject", getSubject())
            .append("createBy", getCreateBy())
            .append("createDate", getCreateDate())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
