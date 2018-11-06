package com.zxiang.project.advertise.utils.constant;

public class AdConstant {

	/******************** 广告投放状态常量	*******************/
	
	/** 待预约	 */
	public final static String AD_WAIT_ORDER = "01";
	/** 待审核 */
	public final static String AD_WAIT_ADUIT = "02";
	/** 待发布	 */
	public final static String AD_WAIT_PUBLISH = "03";
	/** 待播放	 */
	public final static String AD_WAIT_PLAY = "04";
	/** 已播放	 */
	public final static String AD_HAS_PLAY = "05";
	/** 审核失败 */
	public final static String AD_ADUIT_FAIL = "06";
	/** 排期失败	 */
	public final static String AD_ARRANGE_FAIL = "07";
	
	
	
	/******************** 广告投放审核结果常量	*******************/
	
	/** 审核通过	 */
	public final static String AD_ADUIT_PASS = "1";
	/** 审核不通过	 */
	public final static String AD_ADUIT_NO_PASS = "0";
	
	
	
	/******************** 广告HTTP接口URL常量	*******************/
	
	/** 获取模板详细信息列表URL	 */
	public final static String 	AD_URL_GETTHEMELIST = "http://ad.bp.zcloudtechs.cn/web-doorplate/adpublish/api/getThemeList.action";
	/** 新增推广计划URL	 */
	public final static String 	AD_URL_SAVEPLAYBILL = "http://ad.bp.zcloudtechs.cn/web-doorplate/adpublish/api/savePlaybill.action";
	/** 上传素材信息URL	 */
	public final static String 	AD_URL_ADDELEMENT = "http://ad.bp.zcloudtechs.cn/web-doorplate/adpublish/api/addElement.action";
	/** 新增广告终端URL	 */
	public final static String 	AD_URL_SAVETERMINAL = "http://ad.bp.zcloudtechs.cn/web-doorplate/adpublish/api/saveTerminal.action";
	/** 删除广告终端URL	 */
	public final static String 	AD_URL_DELETETERMINAL = "http://ad.bp.zcloudtechs.cn/web-doorplate/adpublish/api/deleteTerminal.action";
	/** 生成排期计划URL	 */
	public final static String 	AD_URL_ADDSCHEDULE = "http://ad.bp.zcloudtechs.cn/web-doorplate/adpublish/api/addSchedule.action";
	/** 审核通过下发排期计划URL	 */
	public final static String 	AD_URL_PUBLISHSCHEDULE = "http://ad.bp.zcloudtechs.cn/web-doorplate/adpublish/api/publishSchedule.action";
	
	
	/******************** 广告HTTP接口返回状态	*******************/
	
	/** 审核通过下发排期计划URL	 */
	public final static String 	RESPONSE_CODE_SUCCESS = "0000";
	
	
	
	
}
