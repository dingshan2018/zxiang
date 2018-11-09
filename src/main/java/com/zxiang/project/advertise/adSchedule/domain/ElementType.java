package com.zxiang.project.advertise.adSchedule.domain;

/**
 * 模板元素类
 * @author Administrator
 *
 */
public class ElementType {

	private String id;
	private String elementTypeID;
	private String elementName;
	private String tagName;
	
	
	
	public ElementType() {
	}

	public ElementType(String id, String elementTypeID, String elementName, String tagName) {
		this.id = id;
		this.elementTypeID = elementTypeID;
		this.elementName = elementName;
		this.tagName = tagName;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getElementTypeID() {
		return elementTypeID;
	}
	public void setElementTypeID(String elementTypeID) {
		this.elementTypeID = elementTypeID;
	}
	public String getElementName() {
		return elementName;
	}
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	@Override
	public String toString() {
		return "ElementType [id=" + id + ", elementTypeID=" + elementTypeID + ", elementName=" + elementName
				+ ", tagName=" + tagName + "]";
	}
	
}
