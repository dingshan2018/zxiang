package com.zxiang.project.advertise.adSchedule.domain;

import java.util.List;

/**
 * 广告模板类
 * @author Administrator
 *
 */
public class ThemeTemplate {

	private String themeTemplateId;
	private String themeName;
	private List<ElementType> elementList;
	
	public String getThemeTemplateId() {
		return themeTemplateId;
	}
	public void setThemeTemplateId(String themeTemplateId) {
		this.themeTemplateId = themeTemplateId;
	}
	public String getThemeName() {
		return themeName;
	}
	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}
	public List<ElementType> getElementList() {
		return elementList;
	}
	public void setElementList(List<ElementType> elementList) {
		this.elementList = elementList;
	}
	
}
