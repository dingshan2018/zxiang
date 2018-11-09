package com.zxiang.project.advertise.adMaterial.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zxiang.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 广告投放素材表 zx_ad_material
 * 
 * @author ZXiang
 * @date 2018-11-08
 */
public class AdMaterial extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer adMaterialId;
	/** 广告投放ID */
	private Integer adScheduleId;
	/** 素材URL */
	private String preview;
	/** tEid */
	private String tEid;
	/** treSid */
	private String treSid;
	/** 第几次上传 */
	private Integer batch;
	/** 上传顺序 */
	private Integer sequence;
	/** 备注 */
	private String remark;
	/** 创建者 */
	private String createBy;
	/** 创建时间 */
	private Date createTime;

	public void setAdMaterialId(Integer adMaterialId) 
	{
		this.adMaterialId = adMaterialId;
	}

	public Integer getAdMaterialId() 
	{
		return adMaterialId;
	}
	public void setAdScheduleId(Integer adScheduleId) 
	{
		this.adScheduleId = adScheduleId;
	}

	public Integer getAdScheduleId() 
	{
		return adScheduleId;
	}
	public void setPreview(String preview) 
	{
		this.preview = preview;
	}

	public String getPreview() 
	{
		return preview;
	}
	public void setBatch(Integer batch) 
	{
		this.batch = batch;
	}

	public Integer getBatch() 
	{
		return batch;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
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

    public String gettEid() {
		return tEid;
	}

	public void settEid(String tEid) {
		this.tEid = tEid;
	}

	public String getTreSid() {
		return treSid;
	}

	public void setTreSid(String treSid) {
		this.treSid = treSid;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("adMaterialId", getAdMaterialId())
            .append("adScheduleId", getAdScheduleId())
            .append("preview", getPreview())
            .append("batch", getBatch())
            .append("sequence ", getSequence())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}
