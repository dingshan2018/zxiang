package com.zxiang.project.advertise.adPlaybill.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zxiang.framework.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 节目单表 zx_ad_playbill
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public class AdPlaybill extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 节目单ID */
	private String playbillId;
	/** 节目单编号 */
	private String playbillNo;
	/** 节目单名称 */
	private String playbillName;
	/** 节目单状态1:未完成";2:待审核";3:审核不通过";4:审核通过" */
	private String status;
	/** 标识(0:有效，1:终止) */
	private String flag;
	/** 节目单负责人 */
	private String publisher;
	/** 节目单开始日期(格式:yyyy-MM-dd)
 */
	private String begindate;
	/** 节目单结束日期(格式:yyyy-MM-dd)
 */
	private String enddate;
	/** 适合播放的屏幕类型(0:横屏;1:竖屏)
 */
	private String screentype;
	/** 节目单工作流ID */
	private String flowid;
	/** 备注 */
	private String remark;
	/** 节目单的生成xml路径(审核成功后才有的) */
	private String xmlpath;
	/** 总时长(单位:分钟) */
	private String totallen;
	/** 指定初审人名称 */
	private String checkmannames;
	/** 指定初审人empcode */
	private String checkmanids;
	/** 指定终审人名称 */
	private String checkmannames2;
	/** 指定终审人empcode */
	private String checkmanids2;
	/** 插播标识 1是插播的单子 */
	private String isinsertplay;
	/** 插播审核人名称 */
	private String checkmannames3;
	/** 插播审核人empcode */
	private String checkmanids3;
	/** 插播单指向原节目单pid */
	private String fpid;
	/** 节目单生成的FTP服务器id */
	private String ftpid;
	/** 节目单的大小 */
	private String psize;
	/** 模版ID */
	private String themetemplateId;
	/** 栏目ID */
	private String cid;
	/** 播放开始时间 */
	private String begintime;
	/** 播放结束时间 */
	private String endtime;
	/** 切换策略的值 */
	private String switchtype;
	/** 是否重复播放 */
	private String isloop;
	/** 是否已删除（0：删除，其他都是正常） */
	private BigDecimal isdel;
	/** 分辨率 */
	private String resolpower;
	/** 0内容模版，1页面布局 */
	private String islayout;
	/**  */
	private String createBy;
	/**  */
	private Date createTime;
	/**  */
	private String updateBy;
	/**  */
	private Date updateTime;

	public void setPlaybillId(String playbillId) 
	{
		this.playbillId = playbillId;
	}

	public String getPlaybillId() 
	{
		return playbillId;
	}
	public void setPlaybillNo(String playbillNo) 
	{
		this.playbillNo = playbillNo;
	}

	public String getPlaybillNo() 
	{
		return playbillNo;
	}
	public void setPlaybillName(String playbillName) 
	{
		this.playbillName = playbillName;
	}

	public String getPlaybillName() 
	{
		return playbillName;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setFlag(String flag) 
	{
		this.flag = flag;
	}

	public String getFlag() 
	{
		return flag;
	}
	public void setPublisher(String publisher) 
	{
		this.publisher = publisher;
	}

	public String getPublisher() 
	{
		return publisher;
	}
	public void setBegindate(String begindate) 
	{
		this.begindate = begindate;
	}

	public String getBegindate() 
	{
		return begindate;
	}
	public void setEnddate(String enddate) 
	{
		this.enddate = enddate;
	}

	public String getEnddate() 
	{
		return enddate;
	}
	public void setScreentype(String screentype) 
	{
		this.screentype = screentype;
	}

	public String getScreentype() 
	{
		return screentype;
	}
	public void setFlowid(String flowid) 
	{
		this.flowid = flowid;
	}

	public String getFlowid() 
	{
		return flowid;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}
	public void setXmlpath(String xmlpath) 
	{
		this.xmlpath = xmlpath;
	}

	public String getXmlpath() 
	{
		return xmlpath;
	}
	public void setTotallen(String totallen) 
	{
		this.totallen = totallen;
	}

	public String getTotallen() 
	{
		return totallen;
	}
	public void setCheckmannames(String checkmannames) 
	{
		this.checkmannames = checkmannames;
	}

	public String getCheckmannames() 
	{
		return checkmannames;
	}
	public void setCheckmanids(String checkmanids) 
	{
		this.checkmanids = checkmanids;
	}

	public String getCheckmanids() 
	{
		return checkmanids;
	}
	public void setCheckmannames2(String checkmannames2) 
	{
		this.checkmannames2 = checkmannames2;
	}

	public String getCheckmannames2() 
	{
		return checkmannames2;
	}
	public void setCheckmanids2(String checkmanids2) 
	{
		this.checkmanids2 = checkmanids2;
	}

	public String getCheckmanids2() 
	{
		return checkmanids2;
	}
	public void setIsinsertplay(String isinsertplay) 
	{
		this.isinsertplay = isinsertplay;
	}

	public String getIsinsertplay() 
	{
		return isinsertplay;
	}
	public void setCheckmannames3(String checkmannames3) 
	{
		this.checkmannames3 = checkmannames3;
	}

	public String getCheckmannames3() 
	{
		return checkmannames3;
	}
	public void setCheckmanids3(String checkmanids3) 
	{
		this.checkmanids3 = checkmanids3;
	}

	public String getCheckmanids3() 
	{
		return checkmanids3;
	}
	public void setFpid(String fpid) 
	{
		this.fpid = fpid;
	}

	public String getFpid() 
	{
		return fpid;
	}
	public void setFtpid(String ftpid) 
	{
		this.ftpid = ftpid;
	}

	public String getFtpid() 
	{
		return ftpid;
	}
	public void setPsize(String psize) 
	{
		this.psize = psize;
	}

	public String getPsize() 
	{
		return psize;
	}
	public void setThemetemplateId(String themetemplateId) 
	{
		this.themetemplateId = themetemplateId;
	}

	public String getThemetemplateId() 
	{
		return themetemplateId;
	}
	public void setCid(String cid) 
	{
		this.cid = cid;
	}

	public String getCid() 
	{
		return cid;
	}
	public void setBegintime(String begintime) 
	{
		this.begintime = begintime;
	}

	public String getBegintime() 
	{
		return begintime;
	}
	public void setEndtime(String endtime) 
	{
		this.endtime = endtime;
	}

	public String getEndtime() 
	{
		return endtime;
	}
	public void setSwitchtype(String switchtype) 
	{
		this.switchtype = switchtype;
	}

	public String getSwitchtype() 
	{
		return switchtype;
	}
	public void setIsloop(String isloop) 
	{
		this.isloop = isloop;
	}

	public String getIsloop() 
	{
		return isloop;
	}
	public void setIsdel(BigDecimal isdel) 
	{
		this.isdel = isdel;
	}

	public BigDecimal getIsdel() 
	{
		return isdel;
	}
	public void setResolpower(String resolpower) 
	{
		this.resolpower = resolpower;
	}

	public String getResolpower() 
	{
		return resolpower;
	}
	public void setIslayout(String islayout) 
	{
		this.islayout = islayout;
	}

	public String getIslayout() 
	{
		return islayout;
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
            .append("playbillId", getPlaybillId())
            .append("playbillNo", getPlaybillNo())
            .append("playbillName", getPlaybillName())
            .append("status", getStatus())
            .append("flag", getFlag())
            .append("publisher", getPublisher())
            .append("begindate", getBegindate())
            .append("enddate", getEnddate())
            .append("screentype", getScreentype())
            .append("flowid", getFlowid())
            .append("remark", getRemark())
            .append("xmlpath", getXmlpath())
            .append("totallen", getTotallen())
            .append("checkmannames", getCheckmannames())
            .append("checkmanids", getCheckmanids())
            .append("checkmannames2", getCheckmannames2())
            .append("checkmanids2", getCheckmanids2())
            .append("isinsertplay", getIsinsertplay())
            .append("checkmannames3", getCheckmannames3())
            .append("checkmanids3", getCheckmanids3())
            .append("fpid", getFpid())
            .append("ftpid", getFtpid())
            .append("psize", getPsize())
            .append("themetemplateId", getThemetemplateId())
            .append("cid", getCid())
            .append("begintime", getBegintime())
            .append("endtime", getEndtime())
            .append("switchtype", getSwitchtype())
            .append("isloop", getIsloop())
            .append("isdel", getIsdel())
            .append("resolpower", getResolpower())
            .append("islayout", getIslayout())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
