package com.zxiang.project.advertise.adResource.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zxiang.framework.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 素材维护表 zx_ad_resource
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public class AdResource extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 素材ID */
	private String resourceId;
	/** 素材名称 */
	private String resourceName;
	/** 素材类型
 WHEN '49' THEN '功能图'
 WHEN '40' THEN '背景图' 
WHEN '41' THEN '视频文件' 
WHEN '42' THEN '图片文件'
 WHEN '43' THEN '文本文件'
 WHEN '48' THEN '直播频道'
 WHEN '47' THEN '音频' */
	private String resourceType;
	/** 素材资源文件名 */
	private String resourceFilename;
	/** 素材大小 */
	private String resourceSize;
	/** 视频播放时长 */
	private String videoLen;
	/** 说明 */
	private String remark;
	/** 原始视频播放时长 */
	private String realVideoLen;
	/** 素材编码 */
	private String resourceCode;
	/** 动态文字(提供播放情况使用) */
	private String autotxt;
	/** 素材类别 */
	private String resourceSort;
	/** 客户ID */
	private String clientUnit;
	/** 客户联系人 */
	private String clientName;
	/** 客户联系电话 */
	private String clientTel;
	/** 是否通用素材 1为通用，0为分组使用。 */
	private String isNormal;
	/** 素材状态(2初审 3 终审 4 完成) */
	private String state;
	/** 标识 素材是否可用(1 可用 0不可用) */
	private String flag;
	/** 指定初审人员id列表,分开 */
	private String checkmanids;
	/** 指定初审人员姓名列表,分开 */
	private String checkmannames;
	/** 指定终审人员id列表,分开 */
	private String checkmanids2;
	/** 指定终审人员姓名列表,分开 */
	private String checkmannames2;
	/** 任务工作流 */
	private String workflowid;
	/** 素材给哪个分屏使用，通用2，横屏0，竖屏1 */
	private String resourceScreen;
	/** 文件缩略图URL */
	private String smallPicurl;
	/** 素材有效期开始时间 */
	private String beginDate;
	/** 素材有效期结束时间 */
	private String endDate;
	/** 是否已删除（0：删除，其他都是正常） */
	private BigDecimal isDel;
	/** 功能关联的布局模版 */
	private Integer templateId;
	/**  */
	private String createBy;
	/**  */
	private Date createTime;
	/**  */
	private String updateBy;
	/**  */
	private Date updateTime;

	public void setResourceId(String resourceId) 
	{
		this.resourceId = resourceId;
	}

	public String getResourceId() 
	{
		return resourceId;
	}
	public void setResourceName(String resourceName) 
	{
		this.resourceName = resourceName;
	}

	public String getResourceName() 
	{
		return resourceName;
	}
	public void setResourceType(String resourceType) 
	{
		this.resourceType = resourceType;
	}

	public String getResourceType() 
	{
		return resourceType;
	}
	public void setResourceFilename(String resourceFilename) 
	{
		this.resourceFilename = resourceFilename;
	}

	public String getResourceFilename() 
	{
		return resourceFilename;
	}
	public void setResourceSize(String resourceSize) 
	{
		this.resourceSize = resourceSize;
	}

	public String getResourceSize() 
	{
		return resourceSize;
	}
	public void setVideoLen(String videoLen) 
	{
		this.videoLen = videoLen;
	}

	public String getVideoLen() 
	{
		return videoLen;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}
	public void setRealVideoLen(String realVideoLen) 
	{
		this.realVideoLen = realVideoLen;
	}

	public String getRealVideoLen() 
	{
		return realVideoLen;
	}
	public void setResourceCode(String resourceCode) 
	{
		this.resourceCode = resourceCode;
	}

	public String getResourceCode() 
	{
		return resourceCode;
	}
	public void setAutotxt(String autotxt) 
	{
		this.autotxt = autotxt;
	}

	public String getAutotxt() 
	{
		return autotxt;
	}
	public void setResourceSort(String resourceSort) 
	{
		this.resourceSort = resourceSort;
	}

	public String getResourceSort() 
	{
		return resourceSort;
	}
	public void setClientUnit(String clientUnit) 
	{
		this.clientUnit = clientUnit;
	}

	public String getClientUnit() 
	{
		return clientUnit;
	}
	public void setClientName(String clientName) 
	{
		this.clientName = clientName;
	}

	public String getClientName() 
	{
		return clientName;
	}
	public void setClientTel(String clientTel) 
	{
		this.clientTel = clientTel;
	}

	public String getClientTel() 
	{
		return clientTel;
	}
	public void setIsNormal(String isNormal) 
	{
		this.isNormal = isNormal;
	}

	public String getIsNormal() 
	{
		return isNormal;
	}
	public void setState(String state) 
	{
		this.state = state;
	}

	public String getState() 
	{
		return state;
	}
	public void setFlag(String flag) 
	{
		this.flag = flag;
	}

	public String getFlag() 
	{
		return flag;
	}
	public void setCheckmanids(String checkmanids) 
	{
		this.checkmanids = checkmanids;
	}

	public String getCheckmanids() 
	{
		return checkmanids;
	}
	public void setCheckmannames(String checkmannames) 
	{
		this.checkmannames = checkmannames;
	}

	public String getCheckmannames() 
	{
		return checkmannames;
	}
	public void setCheckmanids2(String checkmanids2) 
	{
		this.checkmanids2 = checkmanids2;
	}

	public String getCheckmanids2() 
	{
		return checkmanids2;
	}
	public void setCheckmannames2(String checkmannames2) 
	{
		this.checkmannames2 = checkmannames2;
	}

	public String getCheckmannames2() 
	{
		return checkmannames2;
	}
	public void setWorkflowid(String workflowid) 
	{
		this.workflowid = workflowid;
	}

	public String getWorkflowid() 
	{
		return workflowid;
	}
	public void setResourceScreen(String resourceScreen) 
	{
		this.resourceScreen = resourceScreen;
	}

	public String getResourceScreen() 
	{
		return resourceScreen;
	}
	public void setSmallPicurl(String smallPicurl) 
	{
		this.smallPicurl = smallPicurl;
	}

	public String getSmallPicurl() 
	{
		return smallPicurl;
	}
	public void setBeginDate(String beginDate) 
	{
		this.beginDate = beginDate;
	}

	public String getBeginDate() 
	{
		return beginDate;
	}
	public void setEndDate(String endDate) 
	{
		this.endDate = endDate;
	}

	public String getEndDate() 
	{
		return endDate;
	}
	public void setIsDel(BigDecimal isDel) 
	{
		this.isDel = isDel;
	}

	public BigDecimal getIsDel() 
	{
		return isDel;
	}
	public void setTemplateId(Integer templateId) 
	{
		this.templateId = templateId;
	}

	public Integer getTemplateId() 
	{
		return templateId;
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
            .append("resourceId", getResourceId())
            .append("resourceName", getResourceName())
            .append("resourceType", getResourceType())
            .append("resourceFilename", getResourceFilename())
            .append("resourceSize", getResourceSize())
            .append("videoLen", getVideoLen())
            .append("remark", getRemark())
            .append("realVideoLen", getRealVideoLen())
            .append("resourceCode", getResourceCode())
            .append("autotxt", getAutotxt())
            .append("resourceSort", getResourceSort())
            .append("clientUnit", getClientUnit())
            .append("clientName", getClientName())
            .append("clientTel", getClientTel())
            .append("isNormal", getIsNormal())
            .append("state", getState())
            .append("flag", getFlag())
            .append("checkmanids", getCheckmanids())
            .append("checkmannames", getCheckmannames())
            .append("checkmanids2", getCheckmanids2())
            .append("checkmannames2", getCheckmannames2())
            .append("workflowid", getWorkflowid())
            .append("resourceScreen", getResourceScreen())
            .append("smallPicurl", getSmallPicurl())
            .append("beginDate", getBeginDate())
            .append("endDate", getEndDate())
            .append("isDel", getIsDel())
            .append("templateId", getTemplateId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
