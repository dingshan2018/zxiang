package com.zxiang.project.client.repair.domain;

import com.zxiang.framework.web.domain.BaseEntity;

/**
 * 服务网点地区 zx_repair_area
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public class RepairArea extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	private Integer repairAreaId;
	
	private Integer repairId;
	
	private Integer provinceId;
	
	private Integer cityId;
	
	private Integer countyId;
	
	private String provinceName;
	
	private String cityName;
	
	private String countyName;

	public Integer getRepairAreaId() {
		return repairAreaId;
	}

	public void setRepairAreaId(Integer repairAreaId) {
		this.repairAreaId = repairAreaId;
	}

	public Integer getRepairId() {
		return repairId;
	}

	public void setRepairId(Integer repairId) {
		this.repairId = repairId;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getCountyId() {
		return countyId;
	}

	public void setCountyId(Integer countyId) {
		this.countyId = countyId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	
}