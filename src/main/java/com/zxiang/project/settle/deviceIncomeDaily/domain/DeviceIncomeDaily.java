package com.zxiang.project.settle.deviceIncomeDaily.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zxiang.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 设备收入日统计表 zx_device_income_daily
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public class DeviceIncomeDaily extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer incomeId;
	/** 设备ID */
	private Integer deviceId;
	/** 终端ID（板卡ID） */
	private Integer terminalId;
	/** 场所Id */
	private Integer placeId;
	/** 销售收入 */
	private Double sellIncome;
	/** 广告收入 */
	private Double adIncome;
	/** 扫码收入 */
	private Double scanIncome;
	/** 合算时间 */
	private Date sumDate;
	/** 退款收入 */
	private Double refundIncome;
	/** 退货数量 */
	private Integer refundNum;
	/**  */
	private String createBy;
	/**  */
	private Date createTime;
	/**  */
	private String updateBy;
	/**  */
	private Date updateTime;

	public void setIncomeId(Integer incomeId) 
	{
		this.incomeId = incomeId;
	}

	public Integer getIncomeId() 
	{
		return incomeId;
	}
	public void setDeviceId(Integer deviceId) 
	{
		this.deviceId = deviceId;
	}

	public Integer getDeviceId() 
	{
		return deviceId;
	}
	public void setTerminalId(Integer terminalId) 
	{
		this.terminalId = terminalId;
	}

	public Integer getTerminalId() 
	{
		return terminalId;
	}
	public void setPlaceId(Integer placeId) 
	{
		this.placeId = placeId;
	}

	public Integer getPlaceId() 
	{
		return placeId;
	}
	public void setSellIncome(Double sellIncome) 
	{
		this.sellIncome = sellIncome;
	}

	public Double getSellIncome() 
	{
		return sellIncome;
	}
	public void setAdIncome(Double adIncome) 
	{
		this.adIncome = adIncome;
	}

	public Double getAdIncome() 
	{
		return adIncome;
	}
	public void setScanIncome(Double scanIncome) 
	{
		this.scanIncome = scanIncome;
	}

	public Double getScanIncome() 
	{
		return scanIncome;
	}
	public void setSumDate(Date sumDate) 
	{
		this.sumDate = sumDate;
	}

	public Date getSumDate() 
	{
		return sumDate;
	}
	public void setRefundIncome(Double refundIncome) 
	{
		this.refundIncome = refundIncome;
	}

	public Double getRefundIncome() 
	{
		return refundIncome;
	}
	public void setRefundNum(Integer refundNum) 
	{
		this.refundNum = refundNum;
	}

	public Integer getRefundNum() 
	{
		return refundNum;
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
            .append("incomeId", getIncomeId())
            .append("deviceId", getDeviceId())
            .append("terminalId", getTerminalId())
            .append("placeId", getPlaceId())
            .append("sellIncome", getSellIncome())
            .append("adIncome", getAdIncome())
            .append("scanIncome", getScanIncome())
            .append("sumDate", getSumDate())
            .append("refundIncome", getRefundIncome())
            .append("refundNum", getRefundNum())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
