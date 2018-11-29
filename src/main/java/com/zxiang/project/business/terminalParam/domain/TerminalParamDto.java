package com.zxiang.project.business.terminalParam.domain;

import java.util.Date;

/**
 * 下发终端参数命令传输类
 * @author Administrator
 *
 */
public class TerminalParamDto {

	/**  */
	private Integer id;
	/** 终端ID */
	private Integer terminalId;
	/** 终端参数KEY */
	private String key;
	/** 参数值1 */
	private String value1;
	/** 参数值2 */
	private String value2;
	/** 参数值3 */
	private String value3;
	/** 参数值4 */
	private String value4;
	/**  */
	private String createBy;
	/**  */
	private Date createTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(Integer terminalId) {
		this.terminalId = terminalId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue1() {
		return value1;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	public String getValue2() {
		return value2;
	}
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	public String getValue3() {
		return value3;
	}
	public void setValue3(String value3) {
		this.value3 = value3;
	}
	public String getValue4() {
		return value4;
	}
	public void setValue4(String value4) {
		this.value4 = value4;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
