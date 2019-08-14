package com.zxiang.project.advertise.adSchedule.domain;

public class MaterialResult {
	private String type;
	private String materialName;
	private String materialId;
	private Long duration;
	private String previewUrl;
	private Long fileSize;
	private String materialText;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	public Long getDuration() {
		return duration;
	}
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	public String getPreviewUrl() {
		return previewUrl;
	}
	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	public String getMaterialText() {
		return materialText;
	}
	public void setMaterialText(String materialText) {
		this.materialText = materialText;
	}

}
