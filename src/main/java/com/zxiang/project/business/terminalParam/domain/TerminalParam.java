package com.zxiang.project.business.terminalParam.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.zxiang.framework.web.domain.BaseEntity;

/**
 * 终端参数表 zx_terminal_param
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
public class TerminalParam extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 终端ID */
	private Integer terminalId;
	/** 终端参数KEY */
	private String paramKey;
	/** 参数值1 */
	private String paramValue1;
	/** 参数值2 */
	private String paramValue2;
	/** 参数值3 */
	private String paramValue3;
	/** 参数值4 */
	private String paramValue4;
	/**  */
	private String createBy;
	/**  */
	private Date createTime;

	private String terminalCode;
	private String paramKeyName;
	
	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setTerminalId(Integer terminalId) 
	{
		this.terminalId = terminalId;
	}

	public Integer getTerminalId() 
	{
		return terminalId;
	}
	public void setParamKey(String paramKey) 
	{
		this.paramKey = paramKey;
	}

	public String getParamKey() 
	{
		return paramKey;
	}
	public void setParamValue1(String paramValue1) 
	{
		this.paramValue1 = paramValue1;
	}

	public String getParamValue1() 
	{
		return paramValue1;
	}
	public void setParamValue2(String paramValue2) 
	{
		this.paramValue2 = paramValue2;
	}

	public String getParamValue2() 
	{
		return paramValue2;
	}
	public void setParamValue3(String paramValue3) 
	{
		this.paramValue3 = paramValue3;
	}

	public String getParamValue3() 
	{
		return paramValue3;
	}
	public void setParamValue4(String paramValue4) 
	{
		this.paramValue4 = paramValue4;
	}

	public String getParamValue4() 
	{
		return paramValue4;
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

    public String getTerminalCode() {
		return terminalCode;
	}

	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}

	public String getParamKeyName() {
		return paramKeyName;
	}

	public void setParamKeyName(String paramKeyName) {
		this.paramKeyName = paramKeyName;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("terminalId", getTerminalId())
            .append("paramKey", getParamKey())
            .append("paramValue1", getParamValue1())
            .append("paramValue2", getParamValue2())
            .append("paramValue3", getParamValue3())
            .append("paramValue4", getParamValue4())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}
