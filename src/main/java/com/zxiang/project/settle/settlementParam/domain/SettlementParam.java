package com.zxiang.project.settle.settlementParam.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zxiang.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 结算系数配置表 zx_settlement_param
 * 
 * @author ZXiang
 * @date 2018-09-25
 */
public class SettlementParam extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer settlementParamId;
	/** 主体类型 */
	private String settlementType;
	/** 结算用户 */
	private Integer settlementer;
	/** 结算参数 */
	private String settlementParam;
	/** 配置状态 */
	private String status;
	/** 是否删除 */
	private String delFlag;
	/**  */
	private String createBy;
	/**  */
	private Date createTime;
	/**  */
	private String updateBy;
	/**  */
	private Date updateTime;

	public void setSettlementParamId(Integer settlementParamId) 
	{
		this.settlementParamId = settlementParamId;
	}

	public Integer getSettlementParamId() 
	{
		return settlementParamId;
	}
	public void setSettlementType(String settlementType) 
	{
		this.settlementType = settlementType;
	}

	public String getSettlementType() 
	{
		return settlementType;
	}
	public void setSettlementer(Integer settlementer) 
	{
		this.settlementer = settlementer;
	}

	public Integer getSettlementer() 
	{
		return settlementer;
	}
	public void setSettlementParam(String settlementParam) 
	{
		this.settlementParam = settlementParam;
	}

	public String getSettlementParam() 
	{
		return settlementParam;
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
            .append("settlementParamId", getSettlementParamId())
            .append("settlementType", getSettlementType())
            .append("settlementer", getSettlementer())
            .append("settlementParam", getSettlementParam())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
