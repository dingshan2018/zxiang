package com.zxiang.project.business.terminalalarm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zxiang.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 终端报警表 zx_terminal_alarm
 * 
 * @author ZXiang
 * @date 2018-10-14
 */
public class TerminalAlarm extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 报警ID */
	private Integer alarmId;
	/** 终端ID */
	private Integer terminalId;
	/** 报警类型 */
	private String alarmType;
	/** 报警等级 */
	private String alarmLevel;
	/** 报警详情 */
	private String alarmDesc;
	/** 报警状态 */
	private String status;
	/** 处理用户 */
	private Integer operator;
	/** 处理意见 */
	private String dealNote;
	/** 创建时间 */
	private Date createTime;
	/** 处理时间 */
	private Date updateTime;

	public void setAlarmId(Integer alarmId) 
	{
		this.alarmId = alarmId;
	}

	public Integer getAlarmId() 
	{
		return alarmId;
	}
	public void setTerminalId(Integer terminalId) 
	{
		this.terminalId = terminalId;
	}

	public Integer getTerminalId() 
	{
		return terminalId;
	}
	public void setAlarmType(String alarmType) 
	{
		this.alarmType = alarmType;
	}

	public String getAlarmType() 
	{
		return alarmType;
	}
	public void setAlarmLevel(String alarmLevel) 
	{
		this.alarmLevel = alarmLevel;
	}

	public String getAlarmLevel() 
	{
		return alarmLevel;
	}
	public void setAlarmDesc(String alarmDesc) 
	{
		this.alarmDesc = alarmDesc;
	}

	public String getAlarmDesc() 
	{
		return alarmDesc;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setOperator(Integer operator) 
	{
		this.operator = operator;
	}

	public Integer getOperator() 
	{
		return operator;
	}
	public void setDealNote(String dealNote) 
	{
		this.dealNote = dealNote;
	}

	public String getDealNote() 
	{
		return dealNote;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
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
            .append("alarmId", getAlarmId())
            .append("terminalId", getTerminalId())
            .append("alarmType", getAlarmType())
            .append("alarmLevel", getAlarmLevel())
            .append("alarmDesc", getAlarmDesc())
            .append("status", getStatus())
            .append("operator", getOperator())
            .append("dealNote", getDealNote())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
