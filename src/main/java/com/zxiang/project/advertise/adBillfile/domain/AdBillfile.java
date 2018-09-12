package com.zxiang.project.advertise.adBillfile.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zxiang.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 节目单所生成的文件表 zx_ad_billfile
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public class AdBillfile extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键ID
 */
	private String billfileId;
	/** 节目单id
 */
	private String playbillId;
	/** 复制包中元素id
 */
	private String eid;
	/** 素材id
 */
	private String resid;
	/** 素材名称 */
	private String resname;
	/** 文件名称 */
	private String filename;
	/** 类型名称 */
	private String type;
	/** 素材大小 */
	private String ressize;
	/** 文件全路径 */
	private String fullfileurl;
	/** 文件类型标识 */
	private String filetype;
	/** 是否有天气预报的分屏 */
	private String isweather;
	/** 0是节目单 1是插播的节目单 */
	private String playbilltype;
	/** 是否有汇率 有汇率存放汇率的样式1,2,3  没有为空 */
	private String hasforex;
	/** 是否有基金 有基金存放基金的样式1,2,3  没有为空 */
	private String hasopenfund;
	/** 是否有存款利率 有存款存款利率的样式1,2,3  没有为空 */
	private String hasdeposit;
	/** 是否有贷款利率 有贷款存款利率的样式1,2,3  没有为空 */
	private String hasloan;
	/** 是否有深证成指走势图：有为1 没有为空 */
	private String hasszczst;
	/** 是否有上证综指走势图：有为1 没有为空 */
	private String hasszzzst;
	/** 是否有外汇存款利率 有外汇存款利率的样式1,2,3  没有为空 */
	private String hasforeign;
	/** 终端ID */
	private String terminalid;
	/**  */
	private String createBy;
	/**  */
	private Date createTime;
	/**  */
	private String updateBy;
	/**  */
	private Date updateTime;

	public void setBillfileId(String billfileId) 
	{
		this.billfileId = billfileId;
	}

	public String getBillfileId() 
	{
		return billfileId;
	}
	public void setPlaybillId(String playbillId) 
	{
		this.playbillId = playbillId;
	}

	public String getPlaybillId() 
	{
		return playbillId;
	}
	public void setEid(String eid) 
	{
		this.eid = eid;
	}

	public String getEid() 
	{
		return eid;
	}
	public void setResid(String resid) 
	{
		this.resid = resid;
	}

	public String getResid() 
	{
		return resid;
	}
	public void setResname(String resname) 
	{
		this.resname = resname;
	}

	public String getResname() 
	{
		return resname;
	}
	public void setFilename(String filename) 
	{
		this.filename = filename;
	}

	public String getFilename() 
	{
		return filename;
	}
	public void setType(String type) 
	{
		this.type = type;
	}

	public String getType() 
	{
		return type;
	}
	public void setRessize(String ressize) 
	{
		this.ressize = ressize;
	}

	public String getRessize() 
	{
		return ressize;
	}
	public void setFullfileurl(String fullfileurl) 
	{
		this.fullfileurl = fullfileurl;
	}

	public String getFullfileurl() 
	{
		return fullfileurl;
	}
	public void setFiletype(String filetype) 
	{
		this.filetype = filetype;
	}

	public String getFiletype() 
	{
		return filetype;
	}
	public void setIsweather(String isweather) 
	{
		this.isweather = isweather;
	}

	public String getIsweather() 
	{
		return isweather;
	}
	public void setPlaybilltype(String playbilltype) 
	{
		this.playbilltype = playbilltype;
	}

	public String getPlaybilltype() 
	{
		return playbilltype;
	}
	public void setHasforex(String hasforex) 
	{
		this.hasforex = hasforex;
	}

	public String getHasforex() 
	{
		return hasforex;
	}
	public void setHasopenfund(String hasopenfund) 
	{
		this.hasopenfund = hasopenfund;
	}

	public String getHasopenfund() 
	{
		return hasopenfund;
	}
	public void setHasdeposit(String hasdeposit) 
	{
		this.hasdeposit = hasdeposit;
	}

	public String getHasdeposit() 
	{
		return hasdeposit;
	}
	public void setHasloan(String hasloan) 
	{
		this.hasloan = hasloan;
	}

	public String getHasloan() 
	{
		return hasloan;
	}
	public void setHasszczst(String hasszczst) 
	{
		this.hasszczst = hasszczst;
	}

	public String getHasszczst() 
	{
		return hasszczst;
	}
	public void setHasszzzst(String hasszzzst) 
	{
		this.hasszzzst = hasszzzst;
	}

	public String getHasszzzst() 
	{
		return hasszzzst;
	}
	public void setHasforeign(String hasforeign) 
	{
		this.hasforeign = hasforeign;
	}

	public String getHasforeign() 
	{
		return hasforeign;
	}
	public void setTerminalid(String terminalid) 
	{
		this.terminalid = terminalid;
	}

	public String getTerminalid() 
	{
		return terminalid;
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
            .append("billfileId", getBillfileId())
            .append("playbillId", getPlaybillId())
            .append("eid", getEid())
            .append("resid", getResid())
            .append("resname", getResname())
            .append("filename", getFilename())
            .append("type", getType())
            .append("ressize", getRessize())
            .append("fullfileurl", getFullfileurl())
            .append("filetype", getFiletype())
            .append("isweather", getIsweather())
            .append("playbilltype", getPlaybilltype())
            .append("hasforex", getHasforex())
            .append("hasopenfund", getHasopenfund())
            .append("hasdeposit", getHasdeposit())
            .append("hasloan", getHasloan())
            .append("hasszczst", getHasszczst())
            .append("hasszzzst", getHasszzzst())
            .append("hasforeign", getHasforeign())
            .append("terminalid", getTerminalid())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
