package com.zxiang.project.business.terminalTimer.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.zxiang.framework.web.domain.BaseEntity;

/**
 * 定时设置表 zx_terminal_timer
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
public class TerminalTimer extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 定时ID */
	private Integer teminalTimerId;
	/** 终端ID */
	private Integer terminalId;
	/** 定时类型（1 一次  3 按天 2 按周 3 按月 ） */
	private String timerType;
	/** 开机时间 */
	private Date powerOnTime;
	/** 关机时间 */
	private Date powerOffTime;
	/** 状态 1：生效 2：禁用 */
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
	
	private String terminalCode;

	public void setTeminalTimerId(Integer teminalTimerId) 
	{
		this.teminalTimerId = teminalTimerId;
	}

	public Integer getTeminalTimerId() 
	{
		return teminalTimerId;
	}
	public void setTerminalId(Integer terminalId) 
	{
		this.terminalId = terminalId;
	}

	public Integer getTerminalId() 
	{
		return terminalId;
	}
	public void setTimerType(String timerType) 
	{
		this.timerType = timerType;
	}

	public String getTimerType() 
	{
		return timerType;
	}
	public void setPowerOnTime(Date powerOnTime) 
	{
		this.powerOnTime = powerOnTime;
	}

	public Date getPowerOnTime() 
	{
		return powerOnTime;
	}
	public void setPowerOffTime(Date powerOffTime) 
	{
		this.powerOffTime = powerOffTime;
	}

	public Date getPowerOffTime() 
	{
		return powerOffTime;
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

    public String getTerminalCode() {
		return terminalCode;
	}

	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("teminalTimerId", getTeminalTimerId())
            .append("terminalId", getTerminalId())
            .append("timerType", getTimerType())
            .append("powerOnTime", getPowerOnTime())
            .append("powerOffTime", getPowerOffTime())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
