package com.zxiang.project.business.artifactVersion.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zxiang.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 系统组件表 sys_artifact_version
 * 
 * @author ZXiang
 * @date 2018-12-06
 */
public class ArtifactVersion extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 组件版本号 */
	private Integer artifactVerId;
	/** 组件版本号 */
	private String artifactVerCode;
	/** 组件版本主题 */
	private String artifactVerSubject;
	/** 组件版本信息 */
	private String artifactVerInfo;
	/** 下载路径 */
	private String artifaceDownloadPath;
	/** 关联版本表ID */
	private Integer sysVerId;
	/** 启用时间 */
	private Date effDate;
	/** 失效时间 */
	private Date expDate;
	/** 文件MD5验证码 */
	private String md5Check;
	/** 类型，01：补丁，02：完整包 */
	private String artifactType;
	/**  */
	private String oldArtifactCode;
	/**  */
	private String fileName;
	/** 更新包大小 */
	private Float filesize;
	/** 创建者 */
	private String createBy;
	/** 创建时间 */
	private Date createDate;
	/** 备用字段 */
	private String remark;
	/** 是否删除 */
	private String delFlag;

	public void setArtifactVerId(Integer artifactVerId) 
	{
		this.artifactVerId = artifactVerId;
	}

	public Integer getArtifactVerId() 
	{
		return artifactVerId;
	}
	public void setArtifactVerCode(String artifactVerCode) 
	{
		this.artifactVerCode = artifactVerCode;
	}

	public String getArtifactVerCode() 
	{
		return artifactVerCode;
	}
	public void setArtifactVerSubject(String artifactVerSubject) 
	{
		this.artifactVerSubject = artifactVerSubject;
	}

	public String getArtifactVerSubject() 
	{
		return artifactVerSubject;
	}
	public void setArtifactVerInfo(String artifactVerInfo) 
	{
		this.artifactVerInfo = artifactVerInfo;
	}

	public String getArtifactVerInfo() 
	{
		return artifactVerInfo;
	}
	public void setArtifaceDownloadPath(String artifaceDownloadPath) 
	{
		this.artifaceDownloadPath = artifaceDownloadPath;
	}

	public String getArtifaceDownloadPath() 
	{
		return artifaceDownloadPath;
	}
	public void setSysVerId(Integer sysVerId) 
	{
		this.sysVerId = sysVerId;
	}

	public Integer getSysVerId() 
	{
		return sysVerId;
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
	public void setMd5Check(String md5Check) 
	{
		this.md5Check = md5Check;
	}

	public String getMd5Check() 
	{
		return md5Check;
	}
	public void setArtifactType(String artifactType) 
	{
		this.artifactType = artifactType;
	}

	public String getArtifactType() 
	{
		return artifactType;
	}
	public void setOldArtifactCode(String oldArtifactCode) 
	{
		this.oldArtifactCode = oldArtifactCode;
	}

	public String getOldArtifactCode() 
	{
		return oldArtifactCode;
	}
	public void setFileName(String fileName) 
	{
		this.fileName = fileName;
	}

	public String getFileName() 
	{
		return fileName;
	}
	public void setFilesize(Float filesize) 
	{
		this.filesize = filesize;
	}

	public Float getFilesize() 
	{
		return filesize;
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
            .append("artifactVerId", getArtifactVerId())
            .append("artifactVerCode", getArtifactVerCode())
            .append("artifactVerSubject", getArtifactVerSubject())
            .append("artifactVerInfo", getArtifactVerInfo())
            .append("artifaceDownloadPath", getArtifaceDownloadPath())
            .append("sysVerId", getSysVerId())
            .append("effDate", getEffDate())
            .append("expDate", getExpDate())
            .append("md5Check", getMd5Check())
            .append("artifactType", getArtifactType())
            .append("oldArtifactCode", getOldArtifactCode())
            .append("fileName", getFileName())
            .append("filesize", getFilesize())
            .append("createBy", getCreateBy())
            .append("createDate", getCreateDate())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
