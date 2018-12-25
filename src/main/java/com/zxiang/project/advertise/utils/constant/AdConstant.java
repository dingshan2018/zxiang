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
	public final static String 	AD_URL_GETTHEMELIST = "http://mmedia.bp.zcloudtechs.cn/mmedia/adpublish/api/getThemeList.action";
	/** 新增推广计划URL	 */
	public final static String 	AD_URL_SAVEPLAYBILL = "http://mmedia.bp.zcloudtechs.cn/mmedia/adpublish/api/savePlaybill.action";
	/** 上传素材信息URL	 */
	public final static String 	AD_URL_ADDELEMENT = "http://mmedia.bp.zcloudtechs.cn/mmedia/adpublish/api/addElement.action";
	/** 新增广告终端URL	 */
	public final static String 	AD_URL_SAVETERMINAL = "http://mmedia.bp.zcloudtechs.cn/mmedia/adpublish/api/saveTerminal.action";
	/** 删除广告终端URL	 */
	public final static String 	AD_URL_DELETETERMINAL = "http://mmedia.bp.zcloudtechs.cn/mmedia/adpublish/api/deleteTerminal.action";
	/** 生成排期计划URL	 */
	public final static String 	AD_URL_ADDSCHEDULE = "http://mmedia.bp.zcloudtechs.cn/mmedia/adpublish/api/addSchedule.action";
	/** 审核通过下发排期计划URL	 */
	public final static String 	AD_URL_PUBLISHSCHEDULE = "http://mmedia.bp.zcloudtechs.cn/mmedia/adpublish/api/publishSchedule.action";
	/******************** 广告HTTP接口返回状态	*******************/
	
	/** 接口返回成功码	 */
	public final static String 	RESPONSE_CODE_SUCCESS = "0000";
	
	
	/******************** 广告押金百分比	*******************/
	
	public final static float PREPAY = 0.3f;
	
	
	/******************** 广告订单支付	*******************/
	/** 生成广告支付二维码前缀	 */
	public final static String AD_PAY_PREFIX = "http://mp.bp.zcloudtechs.cn/wx/wxpay/dingshanScheduleCode";
	/** 广告支付类型 全额支付 2	 */
	public final static String PAY_TYPE_ALL = "2";
	/** 广告支付类型 押金支付 3	 */
	public final static String PAY_TYPE_PRE = "3";
	/** 广告支付类型  充值 4	 */
	public final static String PAY_TYPE_TO_UP = "4";
	
	/******************** 广告投放位置（类型）	*******************/
	/** 终端广告	 */
	public final static String RELEASE_TYPE_TERMINAL = "01";
	/** 页面广告H5 */
	public final static String RELEASE_TYPE_H5 = "02";
	
	
	/******************** 广告类型（字典表ad_type）	*******************/
	/** 终端轮播广告	 */
	public final static String AD_TYPE_PHOTO = "01";
	/** 终端视频广告 */
	public final static String AD_TYPE_VIDEO = "02";
	/** 页面广告H5 */
	public final static String AD_TYPE_H5 = "03";
	/** 终端页面和轮播两种类型广告 */
	public final static String AD_TYPE_PHOTO_VIDEO = "04";
	
	
	/******************** 广告素材包含的文件类型	*******************/
	/** 图片 */
	public final static String MATERIAL_TYPE_PHOTO = "图片";
	/** 视频 */
	public final static String MATERIAL_TYPE_VIDEO = "视频";
	
	
	
	
	
	
}
