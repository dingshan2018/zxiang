package com.zxiang.project.business.changeTerminal.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.zxiang.framework.web.domain.BaseEntity;

/**
 * 终端更换记录表 zx_change_terminal
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
public class ChangeTerminal extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 换板记录ID */
	private Integer changeTerminalId;
	/** 旧终端 */
	private Integer oldTerminalId;
	/** 新终端 */
	private Integer newTerminalId;
	/** 旧音量设置 */
	private String oldVolumn;
	/** 换班人id */
	private Integer changerId;
	/**  */
	private String createBy;
	/**  */
	private Date createTime;
	/**  */
	private String updateBy;
	/**  */
	private Date updateTime;

	private String oldTerminalCode;
	private String newTerminalCode;
	private String changeUserName;
	
	public void setChangeTerminalId(Integer changeTerminalId) 
	{
		this.changeTerminalId = changeTerminalId;
	}

	public Integer getChangeTerminalId() 
	{
		return changeTerminalId;
	}
	public void setOldTerminalId(Integer oldTerminalId) 
	{
		this.oldTerminalId = oldTerminalId;
	}

	public Integer getOldTerminalId() 
	{
		return oldTerminalId;
	}
	public void setNewTerminalId(Integer newTerminalId) 
	{
		this.newTerminalId = newTerminalId;
	}

	public Integer getNewTerminalId() 
	{
		return newTerminalId;
	}
	public void setOldVolumn(String oldVolumn) 
	{
		this.oldVolumn = oldVolumn;
	}

	public String getOldVolumn() 
	{
		return oldVolumn;
	}
	public void setChangerId(Integer changerId) 
	{
		this.changerId = changerId;
	}

	public Integer getChangerId() 
	{
		return changerId;
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

    public String getOldTerminalCode() {
		return oldTerminalCode;
	}

	public void setOldTerminalCode(String oldTerminalCode) {
		this.oldTerminalCode = oldTerminalCode;
	}

	public String getNewTerminalCode() {
		return newTerminalCode;
	}

	public void setNewTerminalCode(String newTerminalCode) {
		this.newTerminalCode = newTerminalCode;
	}

	public String getChangeUserName() {
		return changeUserName;
	}

	public void setChangeUserName(String changeUserName) {
		this.changeUserName = changeUserName;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("changeTerminalId", getChangeTerminalId())
            .append("oldTerminalId", getOldTerminalId())
            .append("newTerminalId", getNewTerminalId())
            .append("oldVolumn", getOldVolumn())
            .append("changerId", getChangerId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
