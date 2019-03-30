package com.zxiang.project.advertise.adSchedule.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.jboss.logging.Logger;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zxiang.common.exception.RRException;
import com.zxiang.common.support.Convert;
import com.zxiang.common.utils.StringUtils;
import com.zxiang.common.utils.excel.EXCELObject;
import com.zxiang.project.advertise.adMaterial.domain.AdMaterial;
import com.zxiang.project.advertise.adMaterial.mapper.AdMaterialMapper;
import com.zxiang.project.advertise.adPriceCfg.domain.AdPriceCfg;
import com.zxiang.project.advertise.adPriceCfg.mapper.AdPriceCfgMapper;
import com.zxiang.project.advertise.adReleaseRange.domain.AdReleaseRange;
import com.zxiang.project.advertise.adReleaseRange.mapper.AdReleaseRangeMapper;
import com.zxiang.project.advertise.adReleaseRecord.mapper.AdReleaseRecordMapper;
import com.zxiang.project.advertise.adReleaseTimer.domain.AdReleaseTimer;
import com.zxiang.project.advertise.adReleaseTimer.mapper.AdReleaseTimerMapper;
import com.zxiang.project.advertise.adSchedule.domain.AdSchedule;
import com.zxiang.project.advertise.adSchedule.domain.ElementType;
import com.zxiang.project.advertise.adSchedule.domain.ThemeTemplate;
import com.zxiang.project.advertise.adSchedule.mapper.AdScheduleMapper;
import com.zxiang.project.advertise.releaseDevice.domain.ReleaseDevice;
import com.zxiang.project.advertise.releaseDevice.mapper.ReleaseDeviceMapper;
import com.zxiang.project.advertise.utils.AdHttpResult;
import com.zxiang.project.advertise.utils.Tools;
import com.zxiang.project.advertise.utils.constant.AdConstant;
import com.zxiang.project.business.device.domain.Device;
import com.zxiang.project.business.device.mapper.DeviceMapper;
import com.zxiang.project.business.server.service.IServerService;
import com.zxiang.project.business.terminal.domain.Terminal;
import com.zxiang.project.business.terminal.mapper.TerminalMapper;
import com.zxiang.project.client.advertise.domain.Advertise;
import com.zxiang.project.client.advertise.mapper.AdvertiseMapper;
import com.zxiang.project.client.advertise.service.IAdvertiseService;
import com.zxiang.project.client.fundLog.domain.FundLog;
import com.zxiang.project.client.fundLog.mapper.FundLogMapper;
import com.zxiang.project.client.fundLog.service.IFundLogService;
import com.zxiang.project.system.config.domain.Config;
import com.zxiang.project.system.config.mapper.ConfigMapper;

/**
 * 广告投放 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-24
 */
@Service
public class AdScheduleServiceImpl implements IAdScheduleService {
	Logger logger = Logger.getLogger(AdScheduleServiceImpl.class);
	private SimpleDateFormat yyyyMMddHHmmSFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private SimpleDateFormat yyyyMMddSFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	private AdScheduleMapper adScheduleMapper;
	@Autowired
	private AdReleaseRangeMapper adReleaseRangeMapper;
	@Autowired
	private AdReleaseTimerMapper adReleaseTimerMapper;
	@Autowired
	private DeviceMapper deviceMapper;
	@Autowired
	private AdMaterialMapper adMaterialMapper;
	@Autowired
	private AdPriceCfgMapper adPriceCfgMapper;
	@Autowired
	private IServerService serverService;
	@Autowired
	private TerminalMapper terminalMapper;
	@Autowired
	private ReleaseDeviceMapper releaseDeviceMapper;
	@Autowired
	private AdvertiseMapper advertiseMapper;
	@Autowired
	private IFundLogService fundLogService;
	@Autowired
	private IAdvertiseService advertiseService;
	@Autowired
	private ConfigMapper configMapper;
	@Autowired
	private AdReleaseRecordMapper releaseRecordMapper;
	@Autowired
	private FundLogMapper fundLogMapper;

	/**
	 * 查询广告投放信息
	 * 
	 * @param adScheduleId
	 *            广告投放ID
	 * @return 广告投放信息
	 */
	@Override
	public AdSchedule selectAdScheduleById(Integer adScheduleId) {
		return adScheduleMapper.selectAdScheduleById(adScheduleId);
	}

	/**
	 * 查询广告投放列表
	 * 
	 * @param adSchedule
	 *            广告投放信息
	 * @return 广告投放集合
	 */
	@Override
	public List<AdSchedule> selectAdScheduleList(AdSchedule adSchedule) {
		return adScheduleMapper.selectAdScheduleList(adSchedule);
	}

	/**
	 * 新增广告投放
	 * 
	 * @param adSchedule
	 *            广告投放信息
	 * @return 结果
	 */
	@Override
	public int insertAdSchedule(AdSchedule adSchedule) {
		return adScheduleMapper.insertAdSchedule(adSchedule);
	}

	/**
	 * 修改广告投放
	 * 
	 * @param adSchedule
	 *            广告投放信息
	 * @return 结果
	 */
	@Override
	public int updateAdSchedule(AdSchedule adSchedule) {
		return adScheduleMapper.updateAdSchedule(adSchedule);
	}

	/**
	 * 删除广告投放对象
	 * 
	 * @param ids
	 *            需要删除的数据ID
	 * @return 结果
	 * @throws IOException 
	 */
	@Override
	@Transactional
	public int deleteAdScheduleByIds(String ids) throws Exception {
		String[] adIds = Convert.toStrArray(ids);
		// 删除关联表数据
		// 1.删除zx_ad_release_range表关联数据
		int delRangeNum = adReleaseRangeMapper.deleteRangeByAdIds(adIds);
		// 2.删除zx_ad_release_timer表关联数据
		int delTimerNum = adReleaseTimerMapper.deleteTimerByAdIds(adIds);
		// 3.删除zx_ad_material表关联数据
		int delMaterialNum = adMaterialMapper.deleteMaterialByAdIds(adIds);
		// 4.最后删除广告表数据
		
		
		
		
		int delReleaseDevice = releaseDeviceMapper.deleteReleaseDeviceByAdIds(adIds);
		return adScheduleMapper.deleteAdScheduleByIds(adIds);
	}

	@Override
	public int saveAdTemplates(AdSchedule adSchedule) throws Exception {

		logger.info("adSchedule:" + adSchedule.toString());
		try {
			String scheduleName = adSchedule.getScheduleName();
			String releasePosition = adSchedule.getReleasePosition();
			// 是终端广告还是页面广告，页面H5广告不需要模板不需要审核不需要支付
			if (AdConstant.RELEASE_TYPE_TERMINAL.equals(releasePosition)) {
				// 如果是终端广告有广告商，投放人就是广告商的managerID
				Advertise advertise = advertiseMapper.selectAdvertiseById(adSchedule.getAdvertiser());
				if (advertise != null) {
					adSchedule.setReleaser(advertise.getManagerId());
				}

				String templateId = adSchedule.getThemeTemplateId();
				Integer advertiser = adSchedule.getAdvertiser();
				String totalTime = adSchedule.getTotalTime();

				String result = savePlaybill(scheduleName, templateId, advertiser.toString(), totalTime);
				// 返回结果封装
				AdHttpResult adHttp = Tools.analysisResult(result);
				if (AdConstant.RESPONSE_CODE_SUCCESS.equals(adHttp.getCode())) {
					JSONObject jsonResult = (JSONObject) adHttp.get("data");
					String pId = jsonResult.getString("pid");
					String tId = jsonResult.getString("tid");

					adSchedule.setpId(pId);
					adSchedule.settId(tId);
				} else {
					logger.error("调用新增推广计划接口失败!" + adHttp.toString());
					throw new RRException("调用新增推广计划接口失败!");
				}
			} else if (AdConstant.RELEASE_TYPE_H5.equals(releasePosition)) {
				// 页面H5广告不需要模板不需要审核不需要支付
				// adSchedule.setPayStatus("1");

			} else {
				logger.error("广告投放位置类型错误!" + releasePosition);
				throw new RRException("广告投放位置类型错误!");
			}
		} catch (Exception e) {
			logger.error("saveAdTemplates error:" + e);
			throw e;
		}

		// 2.保存Advertise表数据
		return adScheduleMapper.insertAdSchedule(adSchedule);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void queryExport(HashMap<String, String> params, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		List erportList = adScheduleMapper.queryExport(params);
		String realPath = request.getSession().getServletContext().getRealPath("/file/temp");
		EXCELObject s = new EXCELObject();
		s.seteFilePath(realPath);
		// 表头名称
		String[] titH = { "ID", "投放名称", "投放方式", "投放位置", "广告商", "排期编号", "节目单ID", "TID", "模板ID", "投放状态", "总价", "押金",
				"投放终端数", "审核结果", "审核意见", "审核人", "是否删除", "投放备注", "总播放时长", "创建者", "创建时间", "修改者", "修改时间" };
		// 数据库字段名称
		String[] titN = { "ad_schedule_id", "schedule_name", "releaseTypeName", "releasePositionName", "advertiseName",
				"sx_schedule_id", "pid", "tid", "theme_template_id", "statusName", "total_pay", "prepay",
				"release_term_num", "approved", "approved_remark", "approved_user", "is_del", "release_note",
				"total_time", "create_by", "create_time", "update_by", "update_time" };
		String[] width = { "15", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20",
				"20", "20", "20", "20", "20", "20", "20", "20" };
		s.setWidth(width);
		s.setFname("广告投放"); // sheet栏名称
		s.setTitle("广告投放"); // Excel内容标题名称
		s.setTitH(titH);
		s.setTitN(titN);
		s.setDataList(erportList);
		File exportFile = null;
		exportFile = s.setData();
		// Excel文件名称
		String excelName = "广告投放" + System.currentTimeMillis() + ".xls";
		s.exportExcel("广告投放", excelName, exportFile, request, response);
	}

	@Override
	@Transactional
	public int materialUpload(List<MultipartFile> files, String adScheduleId, String elementId, String elementName,
			String operatorUser) throws Exception {

		int saveNum = 0;
		try {

			AdSchedule adSchedule = adScheduleMapper.selectAdScheduleById(Integer.parseInt(adScheduleId));
			String tId = adSchedule.gettId();
			// 这里的eid是模板元素的ID，而不是模板ID
			// String elementId = adSchedule.getElementId();
			logger.info("elementId:" + elementId);

			// 查询当前最大批次
			int maxBatch = adMaterialMapper.selectMaxBatch(Integer.parseInt(adScheduleId));
			++maxBatch;
			// 1.上传素材文件
			for (int i = 0; i < files.size(); ++i) {
				// 保存文件动作
				MultipartFile multipartFile = files.get(i);
				if (!multipartFile.isEmpty()) {
					// 调用上传文件的接口
					String result = addElement(tId, elementId, multipartFile);
					// 返回结果封装
					AdHttpResult adHttp = Tools.analysisResult(result);
					if (AdConstant.RESPONSE_CODE_SUCCESS.equals(adHttp.getCode())) {
						JSONObject jsonResult = (JSONObject) adHttp.get("data");
						String preview = jsonResult.getString("preview");
						String teid = jsonResult.getString("teid");
						String tresid = jsonResult.getString("tresid");
						logger.info("teid:" + teid + ",tresid:" + tresid + ",\tpreview:" + preview);
						// 业务处理 保存素材文件
						saveNum += insertMaterial(adSchedule.getAdScheduleId(), teid, tresid, preview, maxBatch,
								(i + 1), operatorUser, elementName);

					} else {
						logger.error("调用上传素材信息接口失败!" + adHttp.toString());
						throw new RRException("调用上传素材信息接口失败!");
					}
				} else {
					logger.error("上传素材文件不能为空!");
					throw new RRException("上传素材文件不能为空!");
				}
			}
			logger.info("成功上传: " + saveNum + " 份文件");
			return saveNum;

		} catch (Exception e) {
			logger.error("materialUpload error: " + e);
			throw e;
		}
	}

	/**
	 * 保存预览素材URL,在最大批次之上增加1,即保存第N+1批次上传记录
	 * 
	 * @param adScheduleId
	 *            投放广告ID
	 * @param teid
	 * @param tresid
	 * @param preview
	 *            素材预览URL
	 * @param batch
	 *            上传批次号
	 * @param sequence
	 *            上传顺序
	 * @param operator
	 *            操作者
	 * @param elementName
	 *            素材类型,保存在ad_material表remark字段用来判断价格
	 * @return
	 */
	private int insertMaterial(Integer adScheduleId, String teid, String tresid, String preview, int batch,
			int sequence, String operator, String elementName) {
		AdMaterial adMaterial = new AdMaterial();
		adMaterial.setAdScheduleId(adScheduleId);
		adMaterial.setPreview(preview);
		adMaterial.settEid(teid);
		adMaterial.setTreSid(tresid);
		adMaterial.setBatch(batch);
		adMaterial.setSequence(sequence);
		adMaterial.setCreateBy(operator);
		adMaterial.setCreateTime(new Date());
		adMaterial.setRemark(elementName);
		return adMaterialMapper.insertAdMaterial(adMaterial);
	}

	@Override
	@Transactional
	public int orderSave(AdSchedule adSchedule, String operatorUser) throws Exception {

		try {
			Integer adScheduleId = adSchedule.getAdScheduleId();
			Date createDate = new Date();
			// 1.插入广告投放范围表 一对一
			AdReleaseRange adReleaseRange = new AdReleaseRange();
			adReleaseRange.setAdScheduleId(adScheduleId);
			String releaseType = adSchedule.getReleaseType();
			adReleaseRange.setReleaseType(releaseType);
			adReleaseRange.setCreateBy(operatorUser);
			adReleaseRange.setCreateTime(createDate);
			// 根据投放方式判断保存哪些字段:投放类型:0全部；1按地区；2按场所
			if ("0".equals(releaseType)) {

			} else if ("1".equals(releaseType)) {
				adReleaseRange.setProvince(adSchedule.getProvince());
				adReleaseRange.setCity(adSchedule.getCity());
				adReleaseRange.setCounty(adSchedule.getCounty());
			} else if ("2".equals(releaseType)) {
				adReleaseRange.setPlaceGrade(adSchedule.getPlaceGrade());
			}
			String deviceIds = adSchedule.getDeviceIds();
			int deviceNum = deviceIds.split(",").length;

			adReleaseRange.setDevices(deviceIds);
			adReleaseRangeMapper.insertAdReleaseRange(adReleaseRange);

			// 2.插入广告与时间范围关系表 一对多
			List<String> timeSlots = new ArrayList<>();
			Date deadLineDate = null;
			int days = 0;
			String timeSlotArr = adSchedule.getTimeSlotArr();

			JSONArray timeSlotJsonArray = new JSONArray(timeSlotArr);
			for (int i = 0; i < timeSlotJsonArray.length(); i++) {
				org.json.JSONObject time = timeSlotJsonArray.getJSONObject(i);
				String beginTime = time.getString("beginTime");
				String lastTime = time.getString("endTime");

				AdReleaseTimer adReleaseTimer = new AdReleaseTimer();
				adReleaseTimer.setAdScheduleId(adScheduleId);
				adReleaseTimer.setReleaseBeginTime(yyyyMMddSFormat.parse(beginTime));
				adReleaseTimer.setReleaseEndTime(yyyyMMddSFormat.parse(lastTime));
				adReleaseTimer.setCreateBy(operatorUser);
				adReleaseTimer.setCreateTime(createDate);
				adReleaseTimerMapper.insertAdReleaseTimer(adReleaseTimer);

				/*
				 * //处理时间 String startDate = beginTime.substring(0, beginTime.lastIndexOf(" "));
				 * String startTime = beginTime.substring(beginTime.lastIndexOf(" ") + 1);
				 * String endDate = lastTime.substring(0, lastTime.lastIndexOf(" ")); String
				 * endTime = lastTime.substring(lastTime.lastIndexOf(" ") + 1);
				 */
				// 现在原来的yyyymmddhhmm改成yyyymmdd,开始时间默认00:00,结束时间2359

				HashMap<String, Object> timeSlot = new HashMap<>();
				timeSlot.put("startDate", beginTime);
				timeSlot.put("endDate", lastTime);
				timeSlot.put("startTime", "00:00");
				timeSlot.put("endTime", "23:59");
				// Map转换成JSON
				String jsonTimeSlot = JSON.toJSONString(timeSlot);
				timeSlots.add(jsonTimeSlot);

				if (deadLineDate == null) {
					deadLineDate = yyyyMMddSFormat.parse(lastTime);
				}

				// 取最后日期
				deadLineDate = compareDate(deadLineDate, yyyyMMddSFormat.parse(lastTime));

				// 获取时间段之间的相差天数
				days += differentDaysByMillisecond(yyyyMMddSFormat.parse(beginTime), yyyyMMddSFormat.parse(lastTime));
			}

			// 3.终端广告需要生成排期,页面H5广告不需要生成排期
			String releasePosition = adSchedule.getReleasePosition();
			String priceType = null;
			if (AdConstant.RELEASE_TYPE_TERMINAL.equals(releasePosition)) {
				// 终端广告预约设备后广告状态变为待审核
				adSchedule.setStatus(AdConstant.AD_WAIT_ADUIT);
				// 终端广告预约设备后计算价格,按照素材表显性文字展示判断,图片/视频/图片视频
				priceType = getPriceType(adScheduleId);

				// 这边的参数tid是设备的ID，而不是广告表的tid，需要修改!
				String pIds = adSchedule.getpId();
				String result = addSchedule(pIds, deviceIds, timeSlots.toString(),
						yyyyMMddSFormat.format(deadLineDate));
				// 返回结果封装
				AdHttpResult adHttp = Tools.analysisResult(result);
				// 保存排期ID
				if (AdConstant.RESPONSE_CODE_SUCCESS.equals(adHttp.getCode())) {
					JSONObject data = (JSONObject) adHttp.get("data");
					String scheduleId = (String) data.get("scheduleId");
					adSchedule.setSxScheduleId(scheduleId);

				} else {
					logger.error("调用排期接口失败!" + adHttp.toString());
					throw new RRException("调用排期接口失败!");
				}
			} else if (AdConstant.RELEASE_TYPE_H5.equals(releasePosition)) {
				// 页面H5广告预约设备后广告状态变为待发布
				adSchedule.setStatus(AdConstant.AD_WAIT_PUBLISH);
				// 页面H5广告预约设备后计算价格;
				priceType = AdConstant.AD_TYPE_H5;
			}

			// 计算总价和押金 总价= 计费类型*days*播放设备数量,押金=总价*比率
			if (StringUtils.isNotEmpty(priceType)) {
				AdPriceCfg adPriceCfg = adPriceCfgMapper.getPriceByType(priceType);
				if (adPriceCfg != null) {
					float totalPay = adPriceCfg.getDailyPrice() * days * deviceNum;
					float prepay = totalPay * AdConstant.PREPAY;
					adSchedule.setTotalPay(totalPay);
					adSchedule.setPrepay(prepay);
					adSchedule.setReleaseDays(days);
				}
			} else {
				adSchedule.setTotalPay((float) 0);
				adSchedule.setPrepay((float) 0);
				adSchedule.setReleaseDays(days);
			}

			// 4.插入zx_release_device表
			List<ReleaseDevice> releaseDeviceList = new ArrayList<ReleaseDevice>();
			String[] devices = Convert.toStrArray(deviceIds);
			for (String deviceId : devices) {
				ReleaseDevice releaseDevice = new ReleaseDevice();
				releaseDevice.setScheduleId(adScheduleId);
				releaseDevice.setReleasePosition(releasePosition);
				releaseDevice.setDeviceId(Integer.parseInt(deviceId));
				releaseDeviceList.add(releaseDevice);
			}
			// 批量插入ReleaseDevice表数据
			releaseDeviceMapper.batchInsert(releaseDeviceList);

			// 5.修改广告计划数据
			// 投放终端数
			adSchedule.setReleaseTermNum(deviceNum);

			return updateAdSchedule(adSchedule);

		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 获取终端广告的计费价格类型 包含图片01;包含视频02;包含图片和视频返回04
	 * 
	 * @param adScheduleId
	 * @return
	 */
	public String getPriceType(Integer adScheduleId) {

		String priceType = null;
		List<String> typeList = new ArrayList<String>();

		List<AdMaterial> materialList = adMaterialMapper.getDistinctList(adScheduleId);
		if (materialList != null && materialList.size() > 0) {
			for (AdMaterial adMaterial : materialList) {
				String materialType = adMaterial.getRemark();
				typeList.add(materialType);
			}
			if (typeList.contains(AdConstant.MATERIAL_TYPE_PHOTO)) {
				priceType = AdConstant.AD_TYPE_PHOTO;
			}
			if (typeList.contains(AdConstant.MATERIAL_TYPE_VIDEO)) {
				priceType = AdConstant.AD_TYPE_VIDEO;
			}
			if (typeList.contains(AdConstant.MATERIAL_TYPE_PHOTO)
					&& typeList.contains(AdConstant.MATERIAL_TYPE_VIDEO)) {
				priceType = AdConstant.AD_TYPE_PHOTO_VIDEO;
			}
		}

		return priceType;
	}

	@Override
	@Transactional
	public int auditSave(AdSchedule adSchedule, String operatorUser) throws Exception {
		try {
			if (AdConstant.AD_ADUIT_PASS.equals(adSchedule.getApproved())) {
				adSchedule.setStatus(AdConstant.AD_WAIT_PUBLISH);

			} else if (AdConstant.AD_ADUIT_NO_PASS.equals(adSchedule.getApproved())) {
				// 审核不通过则不下发排期计划
				adSchedule.setStatus(AdConstant.AD_ADUIT_FAIL);
			} else {
				return 0;// 必须有审核结果,否则视为异常
			}

			adSchedule.setApprovedUser(operatorUser);

			return updateAdSchedule(adSchedule);
		} catch (Exception e) {
			logger.error("auditSave error:" + e);
			throw e;
		}
	}

	@Override
	@Transactional
	public int releaseOnlineSave(AdSchedule adSchedule) throws IOException {

		// 如果是H5广告不需要下发排期计划
		Integer adScheduleId = adSchedule.getAdScheduleId();
		// 获取最新的广告数据
		AdSchedule ad = adScheduleMapper.selectAdScheduleById(adScheduleId);

		String releasePosition = ad.getReleasePosition();
		String qrUrl = ad.getQrUrl();

		if (AdConstant.RELEASE_TYPE_TERMINAL.equals(releasePosition)) {
			// 1.发布时下发排期计划
			String result = publishSchedule(ad.getSxScheduleId());
			// 返回结果封装
			AdHttpResult adHttp = Tools.analysisResult(result);
			if (AdConstant.RESPONSE_CODE_SUCCESS.equals(adHttp.getCode())) {
				JSONObject data = (JSONObject) adHttp.get("data");
				List<JSONObject> adUrls = (List<JSONObject>) data.get("adUrls");
				for (JSONObject jsonObject : adUrls) {
					String adUrl = jsonObject.getString("adUrl");
					String terminalId = jsonObject.getString("terminalId");
					// 2.下发更改广告主题
					String[] deviceIds = Convert.toStrArray(terminalId);
					if (deviceIds.length > 0) {
						// 保存广告URL链接
						int updateNum = deviceMapper.updateAdUrlByTid(adUrl, deviceIds);
						logger.info("成功更新:" + updateNum + " 条设备adUrl数据");
						for (String deviceId : deviceIds) {
							try {
								// 下发广告更新主题命令
								adIssued(deviceId, adUrl);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			} else {
				logger.error("调用审核通过下发排期计划接口失败!" + adHttp.toString());
				throw new RRException("调用排期接口失败!");
			}

			// 广告商累计投放广告次数加1
			if (ad.getAdvertiser() != null) {
				Advertise advertise = advertiseService.selectAdvertiseById(ad.getAdvertiser());
				if (advertise != null) {
					Integer releaseNum = advertise.getReleaseNum();
					if (releaseNum != null) {
						++releaseNum;
					} else {
						releaseNum = 1;
					}
					advertise.setReleaseNum(releaseNum);
					advertiseService.updateAdvertise(advertise);
				} else {
					logger.warn("插入广告商累计投放广告次数失败!广告id：" + ad.getAdScheduleId());
				}
			}

		} else if (AdConstant.RELEASE_TYPE_H5.equals(releasePosition)) {
			// 更新H5的二维码URL
			// 保存qrURL链接,deviceIds通过设备投放范围关联
			AdReleaseRange range = adReleaseRangeMapper.selectAdRangeByAd(adScheduleId);
			if (range != null) {
				String devices = range.getDevices();
				String[] deviceIds = Convert.toStrArray(devices);
				int updateQrUrl = deviceMapper.updateQrUrl(qrUrl, deviceIds, adScheduleId);
				logger.info("成功更新:" + updateQrUrl + " 条设备qrUrl数据");
				for (String deviceId : deviceIds) {
					try {
						// 下发更新终端二维码
						qrCodeIssued(deviceId, qrUrl, adScheduleId);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		// 若广告的status已经为04则已经发布过不再更新，若没有发布则进行发布操作
		if (AdConstant.AD_WAIT_PLAY.equals(ad.getStatus())) {
			return 0;
		} else {
			ad.setStatus(AdConstant.AD_WAIT_PLAY);
			ad.setReleaseStatus(AdConstant.AD_REPUBLISH);
			return adScheduleMapper.updateAdSchedule(ad);
		}

	}

	/**
	 * 更改广告主题下发命令 参数封装方法
	 * 
	 * @param deviceId
	 * @param adUrl
	 * @throws IOException
	 */
	private void adIssued(String deviceId, String adUrl) throws IOException {

		Terminal terminal = terminalMapper.selectTerByDeviceId(Integer.parseInt(deviceId));
		if (terminal != null) {
			JSONObject reqJson = new JSONObject();
			reqJson.put("termCode", terminal.getTerminalCode());
			reqJson.put("adUrl", adUrl);
			reqJson.put("command", "25");// 参数下发命令0x19,转十进制为25

			serverService.issuedCommand(terminal, reqJson);
		}
	}

	/**
	 * 下发更新终端二维码 下发命令 参数封装方法
	 * 
	 * @param deviceId
	 * @param qrCodeUrl
	 * @param adScheduleId
	 * @throws IOException
	 */
	private void qrCodeIssued(String deviceId, String qrCodeUrl, Integer adScheduleId) throws IOException {

		Terminal terminal = terminalMapper.selectTerByDeviceId(Integer.parseInt(deviceId));
		if (terminal != null) {
			JSONObject reqJson = new JSONObject();
			reqJson.put("termCode", terminal.getTerminalCode());
			String offerKey = terminal.getOfferKey();
			// offerKey空的也要传，如果是空的json不会传所以赋字符串
			if (StringUtils.isBlank(offerKey)) {
				offerKey = "   ";
			}
			reqJson.put("offerKey", offerKey);
			reqJson.put("qrUrl", qrCodeUrl);
			reqJson.put("scheduleId", adScheduleId);
			reqJson.put("command", "18");// 参数下发命令0x12,转十进制为18

			serverService.issuedCommand(terminal, reqJson);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ThemeTemplate> getThemeList() {

		List<ThemeTemplate> ThemeTemplateList = new ArrayList<ThemeTemplate>();
		try {
			String result = getThemeListAction();
			// 返回结果封装
			AdHttpResult adHttp = Tools.analysisResult(result);
			if (AdConstant.RESPONSE_CODE_SUCCESS.equals(adHttp.getCode())) {
				List<HashMap<String, Object>> data = (List<HashMap<String, Object>>) adHttp.get("data");
				for (HashMap<String, Object> themebject : data) {
					String themeTemplateId = (String) themebject.get("THEMETEMPLATEID");
					String themeName = (String) themebject.get("THEMENAME");

					ThemeTemplate theme = new ThemeTemplate();
					theme.setThemeTemplateId(themeTemplateId);
					theme.setThemeName(themeName);
					ThemeTemplateList.add(theme);
				}
			} else {
				logger.error("调用获取模板信息列表接口失败!" + adHttp.toString());
				throw new RRException("调用获取模板信息列表接口失败!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ThemeTemplateList;
	}

	@Override
	public List<ElementType> getElementList(String themeTemplateId) throws IOException {
		List<ElementType> elementList = new ArrayList<>();
		try {
			String result = getThemeListAction();
			// 返回结果封装
			AdHttpResult adHttp = Tools.analysisResult(result);
			if (AdConstant.RESPONSE_CODE_SUCCESS.equals(adHttp.getCode())) {

				List<HashMap<String, Object>> data = (List<HashMap<String, Object>>) adHttp.get("data");
				for (HashMap<String, Object> themebject : data) {
					String templateId = (String) themebject.get("THEMETEMPLATEID");
					if (themeTemplateId.equals(templateId)) {
						JSONObject details = (JSONObject) themebject.get("details");
						List<JSONObject> themeElementBeanCollection = (List<JSONObject>) details
								.get("themeElementBeanCollection");
						for (JSONObject themeElement : themeElementBeanCollection) {
							String id = themeElement.getString("id");
							JSONObject elementType = (JSONObject) themeElement.get("elementType");
							String elementTypeID = elementType.getString("elementTypeID");
							String elementName = elementType.getString("elementName");
							String tagName = elementType.getString("tagName");
							ElementType element = new ElementType(id, elementTypeID, elementName, tagName);
							logger.info(element.toString());
							elementList.add(element);
						}
						break;
					} else {
						continue;
					}
				}
			} else {
				logger.error("调用获取模板信息列表接口失败!" + adHttp.toString());
				throw new RRException("调用获取模板信息列表接口失败!");
			}
		} catch (IOException e) {
			logger.error("获取模板元素列表失败!" + e);
			throw e;
		}

		return elementList;
	}

	/**
	 * 接口1：获取模板信息列表 HTTP 接口
	 * 
	 * @throws IOException
	 */
	private String getThemeListAction() throws IOException {
		// 获取模板信息列表接口
		Map<String, String> paramsMap = new HashMap<String, String>();
		// 请求参数封装
		// paramsMap.put("isLayout", "0");
		String param = Tools.paramsToString(paramsMap);
		Config config = new Config();
		config.setConfigKey("AD_SCHEDULE_URL");
		Config retConfig = configMapper.selectConfig(config);
		String rootUrl = StringUtils.isNotNull(retConfig) ? retConfig.getConfigValue()
				: "http://mmedia.bp.zcloudtechs.cn";
		String result = Tools.doPost(rootUrl + AdConstant.AD_URL_GETTHEMELIST, param);
		return result;
	}

	/**
	 * 接口2：调用上传素材信息HTTP Multipart接口
	 * 
	 * @param tId
	 * @param eId
	 * @param multipartFile
	 * @return
	 */
	public String addElement(String tId, String eId, MultipartFile multipartFile) throws Exception {
		Config config = new Config();
		config.setConfigKey("AD_SCHEDULE_URL");
		Config retConfig = configMapper.selectConfig(config);
		String rootUrl = StringUtils.isNotNull(retConfig) ? retConfig.getConfigValue()
				: "http://mmedia.bp.zcloudtechs.cn";
		PostMethod postMethod = new PostMethod(rootUrl + AdConstant.AD_URL_ADDELEMENT);
		HttpClient client = new HttpClient();
		File file = null;
		try {
			// MultipartFile转file
			/*
			 * CommonsMultipartFile cf= (CommonsMultipartFile) multipartFile;
			 * //这个myfile是MultipartFile的 DiskFileItem fi = (DiskFileItem)cf.getFileItem();
			 * File file = fi.getStoreLocation();
			 */

			InputStream ins = multipartFile.getInputStream();
			file = new File(multipartFile.getOriginalFilename());
			Tools.inputStreamToFile(ins, file);

			// FilePart：用来上传文件的类
			FilePart myUpload = new FilePart("myUpload", file);
			// StringPart:普通文本参数
			StringPart tid = new StringPart("tid", tId);
			StringPart eid = new StringPart("eid", eId);
			Part[] parts = { (Part) tid, (Part) eid, (Part) myUpload };

			MultipartRequestEntity mre = new MultipartRequestEntity(
					(org.apache.commons.httpclient.methods.multipart.Part[]) parts, postMethod.getParams());
			postMethod.setRequestEntity(mre);

			client.getHttpConnectionManager().getParams().setConnectionTimeout(50000);// 设置连接时间
			int status = client.executeMethod(postMethod);
			if (status == HttpStatus.SC_OK) {
				String result = postMethod.getResponseBodyAsString();
				return result;
			}
		} catch (Exception e) {
			logger.error("addElement error:" + e);
			throw e;
		} finally {
			if (file.exists()) {
				file.delete();
			}
			// 释放连接
			postMethod.releaseConnection();
		}

		return null;
	}

	/**
	 * 接口3：新增推广计划 HTTP application/x-www-form-urlencoded接口
	 * 
	 * @param scheduleName
	 *            推广计划名称
	 * @param templateId
	 *            模板ID
	 * @param advertiser
	 *            广告主体名称
	 * @param totalTime
	 *            总共播放时长
	 * @return
	 * @throws IOException
	 */
	public String savePlaybill(String scheduleName, String templateId, String advertiser, String totalTime)
			throws IOException {
		// 1.调用接口参数
		Map<String, String> paramsMap = new HashMap<String, String>();
		// 请求参数封装
		paramsMap.put("scheduleName", scheduleName);
		paramsMap.put("templateId", templateId);
		paramsMap.put("advertiser", advertiser);
		paramsMap.put("totalTime", totalTime);

		String param = Tools.paramsToString(paramsMap);
		Config config = new Config();
		config.setConfigKey("AD_SCHEDULE_URL");
		Config retConfig = configMapper.selectConfig(config);
		String rootUrl = StringUtils.isNotNull(retConfig) ? retConfig.getConfigValue()
				: "http://mmedia.bp.zcloudtechs.cn";
		String result = Tools.doPostForm(rootUrl + AdConstant.AD_URL_SAVEPLAYBILL, param);

		return result;
	}

	/**
	 * 接口4：新增广告终端 HTTP application/x-www-form-urlencoded接口
	 * 
	 * @param terminalId
	 *            平台终端ID
	 * @param terminalName
	 *            终端设备名称
	 * @param termCode
	 *            终端编号
	 * @param province
	 *            省份
	 * @param city
	 *            城市
	 * @return
	 * @throws IOException
	 */
	public String saveTerminal(String terminalId, String terminalName, String termCode, String province, String city)
			throws IOException {
		// 请求参数封装
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("deviceId", terminalId);
		paramsMap.put("deviceName", terminalName);
		paramsMap.put("devCode", termCode);
		paramsMap.put("province", province);
		paramsMap.put("city", city);

		String param = Tools.paramsToString(paramsMap);
		Config config = new Config();
		config.setConfigKey("AD_SCHEDULE_URL");
		Config retConfig = configMapper.selectConfig(config);
		String rootUrl = StringUtils.isNotNull(retConfig) ? retConfig.getConfigValue()
				: "http://mmedia.bp.zcloudtechs.cn";
		String result = Tools.doPostForm(rootUrl + AdConstant.AD_URL_SAVETERMINAL, param);

		return result;
	}

	/**
	 * 接口5：删除广告终端 HTTP application/x-www-form-urlencoded接口
	 * 
	 * @param terminalId
	 *            平台终端ID
	 * @return
	 * @throws IOException
	 */
	public String deleteTerminal(String terminalId) throws IOException {
		// 请求参数封装
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("terminalId", terminalId);

		String param = Tools.paramsToString(paramsMap);
		Config config = new Config();
		config.setConfigKey("AD_SCHEDULE_URL");
		Config retConfig = configMapper.selectConfig(config);
		String rootUrl = StringUtils.isNotNull(retConfig) ? retConfig.getConfigValue()
				: "http://mmedia.bp.zcloudtechs.cn";
		String result = Tools.doPostForm(rootUrl + AdConstant.AD_URL_DELETETERMINAL, param);

		return result;
	}

	/**
	 * 接口6：生成排期计划 HTTP application/x-www-form-urlencoded接口
	 * 
	 * @param pids
	 *            播放清单id
	 * @param tids
	 *            播放终端ID
	 * @param timeSlots
	 *            播放时间范围
	 * @param deadLine
	 *            播放最后时间
	 * @return
	 * @throws IOException
	 */
	public String addSchedule(String pids, String tids, String timeSlots, String deadLine) throws IOException {
		// 请求参数封装
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("pids", pids);
		paramsMap.put("tids", tids);
		paramsMap.put("timeSlots", timeSlots);
		paramsMap.put("deadLine", deadLine);

		String param = Tools.paramsToString(paramsMap);
		Config config = new Config();
		config.setConfigKey("AD_SCHEDULE_URL");
		Config retConfig = configMapper.selectConfig(config);
		String rootUrl = StringUtils.isNotNull(retConfig) ? retConfig.getConfigValue()
				: "http://mmedia.bp.zcloudtechs.cn";
		String result = Tools.doPostForm(rootUrl + AdConstant.AD_URL_ADDSCHEDULE, param);

		return result;
	}

	/**
	 * 接口7：调用下发排期计划 HTTP application/x-www-form-urlencoded接口
	 * 
	 * @param adScheduleId
	 *            排期计划ID
	 * @throws IOException
	 */
	private String publishSchedule(String adScheduleId) throws IOException {
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("scheduleId", adScheduleId);
		String param = Tools.paramsToString(paramsMap);
		Config config = new Config();
		config.setConfigKey("AD_SCHEDULE_URL");
		Config retConfig = configMapper.selectConfig(config);
		String rootUrl = StringUtils.isNotNull(retConfig) ? retConfig.getConfigValue()
				: "http://mmedia.bp.zcloudtechs.cn";
		String result = Tools.doPostForm(rootUrl + AdConstant.AD_URL_PUBLISHSCHEDULE, param);
		return result;
	}

	/**
	 * 接口7：调用重新下发排期计划 HTTP application/x-www-form-urlencoded接口
	 * 
	 * @param adScheduleId
	 *            排期计划ID opType 排期更新动作 UPDATE:更新终端 DELETE：下架广告
	 * @throws IOException
	 */
	private String republishSchedule(String adScheduleId, String tids, String timeSlots, String deadLine, String opType)
			throws IOException {
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("scheduleId", adScheduleId);
		paramsMap.put("tids", tids);
		paramsMap.put("timeSlots", timeSlots);
		paramsMap.put("deadLine", deadLine);
		paramsMap.put("opType", opType);
		String param = Tools.paramsToString(paramsMap);
		Config config = new Config();
		config.setConfigKey("AD_SCHEDULE_URL");
		Config retConfig = configMapper.selectConfig(config);
		String rootUrl = StringUtils.isNotNull(retConfig) ? retConfig.getConfigValue()
				: "http://mmedia.bp.zcloudtechs.cn";
		//String rootUrl = "http://127.0.0.1:8080";
		String result = Tools.doPostForm(rootUrl + AdConstant.AD_URL_REPUBLISHSCHEDULE, param);
		return result;
	}

	/**
	 * 日期比较,返回日期大的Date
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Date compareDate(Date date1, Date date2) {
		if (date1.getTime() >= date2.getTime())
			return date1;
		else
			return date2;
	}

	/**
	 * 通过时间秒毫秒数判断两个时间的间隔
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int differentDaysByMillisecond(Date date1, Date date2) {
		int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
		// 在获得的绝对差天数上加1天
		return days + 1;
	}

	/**
	 * 广告支付--通过广告商账户余额支付
	 */
	@Override
	@Transactional
	public int adPayByAccount(Integer adScheduleId, String operatorUser) {
		logger.info("通过广告钱包支付费用,操作者:" + operatorUser + ",adScheduleId:" + adScheduleId);
		
		AdSchedule adSchedule = selectAdScheduleById(adScheduleId);
		if (adSchedule != null) {
			//区分修改投放元素重支付
			String status = adSchedule.getStatus();
			int days = 0;
			Integer deviceNum = 0;
			if(status.equals("04") || status.equals("05")) {//重新支付
				// 计算已经使用金额 ad_release_record sum(price)
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("scheduleId", adSchedule.getAdScheduleId());
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				param.put("endTime", df.format(new Date()));
				Date createDate = new Date();
				Float hasPay = this.releaseRecordMapper.getAdTotalPay(param);
				// total_pay - sum(price) 为目前冻结金额
				AdSchedule oldSchedule = this.adScheduleMapper.selectAdScheduleById(adSchedule.getAdScheduleId());
				Date deadLineDate = null;
				Date today;
				try {
					today = yyyyMMddSFormat.parse(yyyyMMddSFormat.format(new Date()));
				
					AdReleaseTimer timerParam = new AdReleaseTimer();
					timerParam.setAdScheduleId(adScheduleId);
					List<AdReleaseTimer> releaseTimers = this.adReleaseTimerMapper.selectAdReleaseTimerList(timerParam);
					if(releaseTimers!=null && releaseTimers.size()>0) {
						for(AdReleaseTimer timer:releaseTimers) {
							if (deadLineDate == null) {
								deadLineDate = timer.getReleaseEndTime();
							}
							// 取最后日期
							deadLineDate = compareDate(deadLineDate, timer.getReleaseEndTime());
							if(deadLineDate.getTime()<today.getTime()) {
								continue;
							}
							// 获取时间段之间的相差天数
							Date beginTime = timer.getReleaseBeginTime();
							if(timer.getReleaseBeginTime().getTime()<today.getTime()) {
								beginTime = today;
							}
							days += differentDaysByMillisecond(beginTime, timer.getReleaseEndTime());
						}
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ReleaseDevice deviceParam = new ReleaseDevice();
				deviceParam.setScheduleId(adScheduleId);
				List<ReleaseDevice> releaseDevices = this.releaseDeviceMapper.selectReleaseDeviceList(deviceParam);
				if(releaseDevices!=null) {
					deviceNum = releaseDevices.size();
				}
				// 计算当前终端（计算当天需要修改）
				// 投放终端数
				adSchedule.setReleaseTermNum(deviceNum);
				float ounpay = adSchedule.getTotalPay() - hasPay;
				// 计算本次应冻结金额
				float perpay = adSchedule.getTotalPay() / adSchedule.getReleaseDays() / adSchedule.getReleaseTermNum();
				// unpay = 本次应冻结-上次已冻结
				float unpay = perpay * deviceNum * days;
				Advertise advertise = this.advertiseMapper.selectAdvertiseById(adSchedule.getAdvertiser());
				if (unpay - ounpay > 0) {
					// 如果unpay大于0,并且钱包金额足够，那么直接支付
					float balance = advertise.getBalance().subtract(advertise.getFrozenBalance()).floatValue();
					if (balance < (unpay - ounpay)) {
						return 0;
					} 
				}
				adSchedule.setTotalPay(adSchedule.getTotalPay()+(unpay-ounpay));
				fundLogService.adPublishFrozen(adSchedule.getAdvertiser(),
						new BigDecimal(Float.toString(unpay - ounpay)));
			}else {//首次发布
				fundLogService.adPublishFrozen(adSchedule.getAdvertiser(),
						new BigDecimal(Float.toString(adSchedule.getTotalPay())));
			}
			adSchedule.setPayStatus("1");
			adSchedule.setReleaseDays(days);
			adSchedule.setReleaseTermNum(deviceNum);
			
			adSchedule.setUpdateBy(operatorUser);
			adSchedule.setUpdateTime(new Date());
			return updateAdSchedule(adSchedule);
		}
		// 否则抛异常
		throw new RRException("广告信息已失效");
	}

	/**
	 * 校验广告投放名称是否唯一
	 */
	@Override
	public String checkNameUnique(String scheduleName) {
		int count = adScheduleMapper.checkNameUnique(scheduleName);
		if (count > 0) {
			return "1";
		}
		return "0";
	}
	
	
	@Override
	public int republish(AdSchedule adSchedule, String operatorUser) throws Exception {
		// 计算当前时间（广告是当天就结账）
		Integer adScheduleId = adSchedule.getAdScheduleId();
		adSchedule = this.adScheduleMapper.selectAdScheduleById(adScheduleId);
		List<String> timeSlots = new ArrayList<>();
		Date deadLineDate = null;
		AdReleaseTimer timerParam = new AdReleaseTimer();
		timerParam.setAdScheduleId(adScheduleId);
		// 判断时间段是否已经有播放记录，那么就修正releaseRecord
		Boolean todayRelease = false;
		// 修改当天播放时间
		AdReleaseTimer paramTimer = new AdReleaseTimer();
		paramTimer.setAdScheduleId(adScheduleId);
		List<AdReleaseTimer> releaseTimers = this.adReleaseTimerMapper.selectAdReleaseTimerList(paramTimer);
		if (releaseTimers != null && releaseTimers.size() > 0) {
			for(AdReleaseTimer releaseTimer:releaseTimers) {
				
				HashMap<String, Object> timeSlot = new HashMap<>();
				timeSlot.put("startDate", yyyyMMddSFormat.format(releaseTimer.getReleaseBeginTime()));
				timeSlot.put("endDate", yyyyMMddSFormat.format(releaseTimer.getReleaseEndTime()));
				timeSlot.put("startTime", "00:00");
				timeSlot.put("endTime", "23:59");
				//Map转换成JSON
				String jsonTimeSlot = JSON.toJSONString(timeSlot); 
				timeSlots.add(jsonTimeSlot);
				if(deadLineDate == null){
					deadLineDate = yyyyMMddSFormat.parse(yyyyMMddSFormat.format(releaseTimer.getReleaseEndTime()));
				}
				
				//取最后日期
				deadLineDate = compareDate(deadLineDate, yyyyMMddSFormat.parse(yyyyMMddSFormat.format(releaseTimer.getReleaseEndTime())));
			
				if(releaseTimer.getReleaseBeginTime().getTime()<=new Date().getTime() && releaseTimer.getReleaseEndTime().getTime()>=new Date().getTime()) {
					todayRelease = true;
				}
			}
		}else {
			return 0;
		}
		ReleaseDevice deviceParam = new ReleaseDevice();
		deviceParam.setScheduleId(adScheduleId);
		List<ReleaseDevice> releaseDevices = this.releaseDeviceMapper.selectReleaseDeviceList(deviceParam);
		String deviceIds = "";
		if(releaseDevices != null && releaseDevices.size()>0) {
			for(ReleaseDevice releaseDevice:releaseDevices) {
				deviceIds += ","+releaseDevice.getDeviceId();
			}
			deviceIds = deviceIds.substring(1);
		}
		
		// 5.修改广告计划数据
		if ("1".equals(adSchedule.getPayStatus()) && todayRelease) {
			String retJson = republishSchedule(adSchedule.getSxScheduleId() + "", deviceIds, timeSlots.toString(),
					yyyyMMddSFormat.format(deadLineDate), "UPDATE");
			AdHttpResult adHttp = Tools.analysisResult(retJson);
			if (AdConstant.RESPONSE_CODE_SUCCESS.equals(adHttp.getCode())) {
				JSONObject data = (JSONObject) adHttp.get("data");
				List<JSONObject> adUrls = (List<JSONObject>) data.get("adUrls");
				for (JSONObject jsonObject : adUrls) {
					String adUrl = jsonObject.getString("adUrl");
					String terminalId = jsonObject.getString("terminalId");
					// 2.下发更改广告主题
					String[] ndeviceIds = Convert.toStrArray(terminalId);
					if (ndeviceIds.length > 0) {
						// 保存广告URL链接
						int updateNum = deviceMapper.updateAdUrlByTid(adUrl, ndeviceIds);
						logger.info("成功更新:" + updateNum + " 条设备adUrl数据");
						for (String deviceId : ndeviceIds) {
							try {
								// 下发广告更新主题命令
								adIssued(deviceId, adUrl);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
				adSchedule.setReleaseStatus(AdConstant.AD_REPUBLISH);// 已发布
			} else {
				logger.error("调用审核通过下发排期计划接口失败!" + adHttp.toString());
				throw new RRException("调用排期接口失败!");
			}
		} else {
			adSchedule.setReleaseStatus(AdConstant.AD_WAIT_REPUBLISH);// 未发布
		}

		return this.adScheduleMapper.updateAdSchedule(adSchedule);
	}

	/**
	 * 变更广告终端
	 */
//	@Override
//	public int republish(AdSchedule adSchedule, String operatorUser) throws Exception {
//
//		// 判断钱包金额是否足够
//		// 计算已经使用金额 ad_release_record sum(price)
//		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("scheduleId", adSchedule.getAdScheduleId());
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		param.put("endTime", df.format(new Date()));
//		Date createDate = new Date();
//		Float hasPay = this.releaseRecordMapper.getAdTotalPay(param);
//		// total_pay - sum(price) 为目前冻结金额
//		AdSchedule oldSchedule = this.adScheduleMapper.selectAdScheduleById(adSchedule.getAdScheduleId());
//		Float totalPay = oldSchedule.getTotalPay();
//		Float perPay = oldSchedule.getTotalPay() / oldSchedule.getReleaseTermNum() / oldSchedule.getReleaseDays();
//		// 计算当前时间（广告是当天就结账）
//		Integer adScheduleId = adSchedule.getAdScheduleId();
//
//		List<String> timeSlots = new ArrayList<>();
//		Date deadLineDate = null;
//		int days = 0;
//		String timeSlotArr = adSchedule.getTimeSlotArr();
//		// 判断时间段是否已经有播放记录，那么就修正releaseRecord
//		JSONArray timeSlotJsonArray = new JSONArray(timeSlotArr);
//		Boolean todayRelease = false;
//		List<AdReleaseTimer> newTimers = new ArrayList<AdReleaseTimer>();
//		for (int i = 0; i < timeSlotJsonArray.length(); i++) {
//			org.json.JSONObject time = timeSlotJsonArray.getJSONObject(i);
//			String beginTime = time.getString("beginTime");
//			String lastTime = time.getString("endTime");
//
//			AdReleaseTimer adReleaseTimer = new AdReleaseTimer();
//			adReleaseTimer.setAdScheduleId(adScheduleId);
//			adReleaseTimer.setReleaseBeginTime(yyyyMMddSFormat.parse(beginTime));
//			adReleaseTimer.setReleaseEndTime(yyyyMMddSFormat.parse(lastTime));
//			adReleaseTimer.setCreateBy(operatorUser);
//			adReleaseTimer.setCreateTime(new Date());
//			if (yyyyMMddSFormat.parse(beginTime).getTime() <= createDate.getTime()) {
//				todayRelease = true;
//			}
//			newTimers.add(adReleaseTimer);
//			HashMap<String, Object> timeSlot = new HashMap<>();
//			timeSlot.put("startDate", beginTime);
//			timeSlot.put("endDate", lastTime);
//			timeSlot.put("startTime", "00:00");
//			timeSlot.put("endTime", "23:59");
//			// Map转换成JSON
//			String jsonTimeSlot = JSON.toJSONString(timeSlot);
//			timeSlots.add(jsonTimeSlot);
//
//			if (deadLineDate == null) {
//				deadLineDate = yyyyMMddSFormat.parse(lastTime);
//			}
//
//			// 取最后日期
//			deadLineDate = compareDate(deadLineDate, yyyyMMddSFormat.parse(lastTime));
//
//			// 获取时间段之间的相差天数
//			days += differentDaysByMillisecond(yyyyMMddSFormat.parse(beginTime), yyyyMMddSFormat.parse(lastTime));
//		}
//		// 修改当天播放时间
//		AdReleaseTimer paramTimer = new AdReleaseTimer();
//		paramTimer.setAdScheduleId(adScheduleId);
//		Calendar cal = Calendar.getInstance();
//		List<AdReleaseTimer> releaseTimers = this.adReleaseTimerMapper.selectAdReleaseTimerList(paramTimer);
//		if (releaseTimers != null && releaseTimers.size() > 0) {
//			for (AdReleaseTimer oldTimer : releaseTimers) {
//				Date releaseBeginTime = oldTimer.getReleaseBeginTime();
//				Date releaseEndTime = oldTimer.getReleaseEndTime();
//				if (releaseBeginTime.getTime() < createDate.getTime()) {
//					if (releaseEndTime.getTime() >= new Date().getTime()) {
//						cal.setTime(new Date());
//						if (todayRelease) {// 修改时间段包含今天，需要立即发布
//							cal.add(Calendar.DAY_OF_YEAR, -1);
//						}
//						releaseEndTime = yyyyMMddSFormat.parse(df.format(cal.getTime()));
//						oldTimer.setReleaseEndTime(releaseEndTime);
//						this.adReleaseTimerMapper.updateAdReleaseTimer(oldTimer);
//					}
//				} else {// 非已经播放的区间，删除操作
//					this.adReleaseTimerMapper.deleteAdReleaseTimerById(oldTimer.getAdReleaseTimerId());
//				}
//			}
//		}
//		adReleaseTimerMapper.batchInsert(newTimers);
//		// 1.插入广告投放范围表 一对一
//		// 删除旧的投放范围：
//		List<String> scheduleIds = new ArrayList<String>();
//		scheduleIds.add(adScheduleId + "");
//		this.adReleaseRangeMapper.deleteRangeByAdIds((String[]) scheduleIds.toArray());
//		AdReleaseRange adReleaseRange = new AdReleaseRange();
//		adReleaseRange.setAdScheduleId(adScheduleId);
//		String releaseType = adSchedule.getReleaseType();
//		adReleaseRange.setReleaseType(releaseType);
//		adReleaseRange.setCreateBy(operatorUser);
//		adReleaseRange.setCreateTime(new Date());
//		// 根据投放方式判断保存哪些字段:投放类型:0全部；1按地区；2按场所
//		if ("0".equals(releaseType)) {
//
//		} else if ("1".equals(releaseType)) {
//			adReleaseRange.setProvince(adSchedule.getProvince());
//			adReleaseRange.setCity(adSchedule.getCity());
//			adReleaseRange.setCounty(adSchedule.getCounty());
//		} else if ("2".equals(releaseType)) {
//			adReleaseRange.setPlaceGrade(adSchedule.getPlaceGrade());
//		}
//
//		String deviceIds = adSchedule.getDeviceIds();
//		int deviceNum = deviceIds.split(",").length;
//
//		adReleaseRange.setDevices(deviceIds);
//		adReleaseRangeMapper.insertAdReleaseRange(adReleaseRange);
//		// 4.插入zx_release_device表
//		this.releaseDeviceMapper.deleteReleaseDeviceByScheduleId(adScheduleId);
//		List<ReleaseDevice> releaseDeviceList = new ArrayList<ReleaseDevice>();
//		String[] devices = Convert.toStrArray(deviceIds);
//		for (String deviceId : devices) {
//			ReleaseDevice releaseDevice = new ReleaseDevice();
//			releaseDevice.setScheduleId(adScheduleId);
//			releaseDevice.setReleasePosition(adSchedule.getReleasePosition());
//			releaseDevice.setDeviceId(Integer.parseInt(deviceId));
//			releaseDeviceList.add(releaseDevice);
//		}
//		// 批量插入ReleaseDevice表数据
//		releaseDeviceMapper.batchInsert(releaseDeviceList);
//		// 计算当前终端（计算当天需要修改）
//		// 投放终端数
//		adSchedule.setReleaseTermNum(deviceNum);
//		float ounpay = adSchedule.getTotalPay() - hasPay;
//		// 计算本次应冻结金额
//		float perpay = adSchedule.getTotalPay() / adSchedule.getReleaseDays() / adSchedule.getReleaseTermNum();
//		// unpay = 本次应冻结-上次已冻结
//		float unpay = perpay * deviceNum * days;
//		Advertise advertise = this.advertiseMapper.selectAdvertiseById(adSchedule.getAdvertiser());
//		if (unpay - ounpay > 0) {
//			// 如果unpay大于0,并且钱包金额足够，那么直接支付
//			float balance = advertise.getBalance().subtract(advertise.getFrozenBalance()).floatValue();
//			if (balance > (unpay - ounpay)) {
//				// 插入支付金额
//				advertise.setFrozenBalance(advertise.getFrozenBalance().add(new BigDecimal(unpay - ounpay)));
//				advertise.setUpdateBy(operatorUser);
//				advertise.setUpdateTime(new Date());
//				this.advertiseMapper.updateAdvertise(advertise);
//				adSchedule.setPayStatus("1");
//				FundLog fundLog = new FundLog();
//				fundLog.setBalance(new DecimalFormat("#.##")
//						.format(advertise.getBalance().subtract(advertise.getFrozenBalance())));
//				fundLog.setClientId(adSchedule.getAdvertiser());
//				fundLog.setClientType("05");
//				fundLog.setContent("广告发布资金冻结");
//				fundLog.setCreateBy("调整广告投放");
//				fundLog.setCreateTime(new Date());
//				fundLog.setTotalFee(perPay + "");
//				fundLog.setType("5");
//				fundLog.setStatus("1");
//				SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMddHHmmss");
//				fundLog.setSerial("5_" + adSchedule.getAdvertiser() + "_" + df1.format(new Date()));
//				this.fundLogMapper.insertFundLog(fundLog);
//
//			} else {
//
//				adSchedule.setPayStatus("0");
//			}
//		} else if (unpay - ounpay < 0) {
//			// 如果unpay<0,将冻结金额解冻unpay金额
//			advertise.setFrozenBalance(advertise.getFrozenBalance().subtract(new BigDecimal(unpay - ounpay)));
//			advertise.setUpdateBy(operatorUser);
//			advertise.setUpdateTime(new Date());
//			this.advertiseMapper.updateAdvertise(advertise);
//			adSchedule.setPayStatus("1");
//			FundLog fundLog = new FundLog();
//			fundLog.setBalance(
//					new DecimalFormat("#.##").format(advertise.getBalance().subtract(advertise.getFrozenBalance())));
//			fundLog.setClientId(adSchedule.getAdvertiser());
//			fundLog.setClientType("05");
//			fundLog.setContent("广告发布资金解冻");
//			fundLog.setCreateBy("调整广告投放");
//			fundLog.setCreateTime(new Date());
//			fundLog.setTotalFee(perPay + "");
//			fundLog.setType("5");
//			fundLog.setStatus("1");
//			SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMddHHmmss");
//			fundLog.setSerial("5_" + adSchedule.getAdvertiser() + "_" + df1.format(new Date()));
//			this.fundLogMapper.insertFundLog(fundLog);
//			adSchedule.setPayStatus("1");
//		}
//
//		// 5.修改广告计划数据
//		if ("1".equals(adSchedule.getPayStatus()) && todayRelease) {
//			String retJson = republishSchedule(adSchedule.getAdScheduleId() + "", deviceIds, timeSlots.toString(),
//					yyyyMMddSFormat.format(deadLineDate), "UPDATE");
//			AdHttpResult adHttp = Tools.analysisResult(retJson);
//			if (AdConstant.RESPONSE_CODE_SUCCESS.equals(adHttp.getCode())) {
//				JSONObject data = (JSONObject) adHttp.get("data");
//				List<JSONObject> adUrls = (List<JSONObject>) data.get("adUrls");
//				for (JSONObject jsonObject : adUrls) {
//					String adUrl = jsonObject.getString("adUrl");
//					String terminalId = jsonObject.getString("terminalId");
//					// 2.下发更改广告主题
//					String[] ndeviceIds = Convert.toStrArray(terminalId);
//					if (ndeviceIds.length > 0) {
//						// 保存广告URL链接
//						int updateNum = deviceMapper.updateAdUrlByTid(adUrl, ndeviceIds);
//						logger.info("成功更新:" + updateNum + " 条设备adUrl数据");
//						for (String deviceId : ndeviceIds) {
//							try {
//								// 下发广告更新主题命令
//								adIssued(deviceId, adUrl);
//							} catch (Exception e) {
//								e.printStackTrace();
//							}
//						}
//					}
//				}
//				adSchedule.setReleaseStatus("1");// 已发布
//			} else {
//				logger.error("调用审核通过下发排期计划接口失败!" + adHttp.toString());
//				throw new RRException("调用排期接口失败!");
//			}
//		} else {
//			adSchedule.setReleaseStatus("0");// 未发布
//		}
//
//		return this.adScheduleMapper.updateAdSchedule(adSchedule);
//	}

	/**
	 * 每天定时扫描未投放广告
	 */
	@Override
	public void releaseSchdule() throws Exception{
		//如果今天日期在releaseTimer中，那么开始发布
		//AdSchedule scheduleParam = new AdSchedule();
		List<AdSchedule> needReleaseSchedules = this.adScheduleMapper.selectNeedReleaseSchedule();
		for(AdSchedule releaseSchedule : needReleaseSchedules) {
			//如果广告排期未投放
			AdReleaseTimer timerParam = new AdReleaseTimer();
			timerParam.setAdScheduleId(releaseSchedule.getAdScheduleId());
			List<AdReleaseTimer> releaseTimers = this.adReleaseTimerMapper.selectAdReleaseTimerList(timerParam);
			List<String> timeSlots = new ArrayList<String>();
			Date deadLineDate = null;
			if(releaseTimers!=null && releaseTimers.size()>0) {
				for(AdReleaseTimer releaseTimer:releaseTimers) {
				
					HashMap<String, Object> timeSlot = new HashMap<>();
					timeSlot.put("startDate", yyyyMMddSFormat.format(releaseTimer.getReleaseBeginTime()));
					timeSlot.put("endDate", yyyyMMddSFormat.format(releaseTimer.getReleaseEndTime()));
					timeSlot.put("startTime", "00:00");
					timeSlot.put("endTime", "23:59");
					//Map转换成JSON
					String jsonTimeSlot = JSON.toJSONString(timeSlot); 
					timeSlots.add(jsonTimeSlot);
					if(deadLineDate == null){
						deadLineDate = yyyyMMddSFormat.parse(yyyyMMddSFormat.format(releaseTimer.getReleaseEndTime()));
					}
					
					//取最后日期
					deadLineDate = compareDate(deadLineDate, yyyyMMddSFormat.parse(yyyyMMddSFormat.format(releaseTimer.getReleaseEndTime())));
				}
			}
			//收集广告开始投放
			ReleaseDevice deviceParam = new ReleaseDevice();
			deviceParam.setScheduleId(releaseSchedule.getAdScheduleId());
			List<ReleaseDevice> releaseDevices = this.releaseDeviceMapper.selectReleaseDeviceList(deviceParam);
			String deviceIds = "";
			if(releaseDevices != null && releaseDevices.size()>0) {
				for(ReleaseDevice releaseDevice:releaseDevices) {
					deviceIds += ","+releaseDevice.getDeviceId();
				}
				deviceIds = deviceIds.substring(1);
			}
			//遍历时间段，遍历设备开始发布
			String retJson = republishSchedule(releaseSchedule.getAdScheduleId()+"", deviceIds, timeSlots.toString(), yyyyMMddSFormat.format(deadLineDate),"UPDATE");
			AdHttpResult adHttp = Tools.analysisResult(retJson);
			if(AdConstant.RESPONSE_CODE_SUCCESS.equals(adHttp.getCode())){
				JSONObject data = (JSONObject) adHttp.get("data");
				List<JSONObject> adUrls = (List<JSONObject>) data.get("adUrls");
				for (JSONObject jsonObject : adUrls) {
					String adUrl = jsonObject.getString("adUrl");
					String terminalId = jsonObject.getString("terminalId");
					//2.下发更改广告主题	
					String[] ndeviceIds = Convert.toStrArray(terminalId);
					if(ndeviceIds.length > 0){
						//保存广告URL链接
						int updateNum = deviceMapper.updateAdUrlByTid(adUrl,ndeviceIds);
						logger.info("成功更新:"+updateNum+" 条设备adUrl数据");
						for (String deviceId : ndeviceIds) {
							try {
								//下发广告更新主题命令
								adIssued(deviceId, adUrl);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
				releaseSchedule.setReleaseStatus("1");//已发布
				releaseSchedule.setUpdateBy("定时发布");
				releaseSchedule.setUpdateTime(new Date());

				this.adScheduleMapper.updateAdSchedule(releaseSchedule);
		}
	}
	}

	/**
	 * 每天定时扫描已经投放广告
	 */
	@Override
	public void stopSchdule() throws Exception{
		// 如果今天日期不在releaseTimer中并且已经发布的，那么删除广告投
		List<AdSchedule> needReleaseSchedules = this.adScheduleMapper.selectNeedDeleteSchedule();
		for (AdSchedule releaseSchedule : needReleaseSchedules) {
			// 删除投放
			String retJson = republishSchedule(releaseSchedule.getAdScheduleId() + "", null, null, null, "DELETE");
			AdHttpResult adHttp = Tools.analysisResult(retJson);
			if (AdConstant.RESPONSE_CODE_SUCCESS.equals(adHttp.getCode())) {
				JSONObject data = (JSONObject) adHttp.get("data");
				List<JSONObject> adUrls = (List<JSONObject>) data.get("adUrls");
				for (JSONObject jsonObject : adUrls) {
					String adUrl = jsonObject.getString("adUrl");
					String terminalId = jsonObject.getString("terminalId");
					// 2.下发更改广告主题
					String[] ndeviceIds = Convert.toStrArray(terminalId);
					if (ndeviceIds.length > 0) {
						// 保存广告URL链接
						int updateNum = deviceMapper.updateAdUrlByTid(adUrl, ndeviceIds);
						logger.info("成功更新:" + updateNum + " 条设备adUrl数据");
						for (String deviceId : ndeviceIds) {
							try {
								// 下发广告更新主题命令
								adIssued(deviceId, adUrl);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
				releaseSchedule.setReleaseStatus("0");// 未发布
				releaseSchedule.setUpdateBy("定时取消发布");
				releaseSchedule.setUpdateTime(new Date());

				this.adScheduleMapper.updateAdSchedule(releaseSchedule);
			}
		}
	}

	@Override
	public int removeAd(AdSchedule adSchedule1, String operatorUser) throws Exception {
		Integer adScheduleId = adSchedule1.getAdScheduleId();
			ReleaseDevice deviceParam = new ReleaseDevice();
			deviceParam.setScheduleId(adScheduleId);
			List<ReleaseDevice> releaseDevices = this.releaseDeviceMapper.selectReleaseDeviceList(deviceParam);
			String deviceIds = "";
			if(releaseDevices != null && releaseDevices.size()>0) {
				for(ReleaseDevice releaseDevice:releaseDevices) {
					deviceIds += ","+releaseDevice.getDeviceId();
				}
				deviceIds = deviceIds.substring(1);
			}
			AdSchedule adSchedule = adScheduleMapper.selectAdScheduleById(adScheduleId);
			String retJson = republishSchedule(adSchedule.getSxScheduleId() + "", deviceIds, null,
					null, "DELETE");
			AdHttpResult adHttp = Tools.analysisResult(retJson);
			if (AdConstant.RESPONSE_CODE_SUCCESS.equals(adHttp.getCode())) {
				JSONObject data = (JSONObject) adHttp.get("data");
				List<JSONObject> adUrls = (List<JSONObject>) data.get("adUrls");
				if(adUrls!=null && adUrls.size()>0) {
					for (JSONObject jsonObject : adUrls) {
						String adUrl = jsonObject.getString("adUrl");
						String terminalId = jsonObject.getString("terminalId");
						// 2.下发更改广告主题
						String[] ndeviceIds = Convert.toStrArray(terminalId);
						if (ndeviceIds.length > 0) {
							// 保存广告URL链接
							int updateNum = deviceMapper.updateAdUrlByTid(adUrl, ndeviceIds);
							logger.info("成功更新:" + updateNum + " 条设备adUrl数据");
							for (String deviceId : ndeviceIds) {
								try {
									// 下发广告更新主题命令
									adIssued(deviceId, adUrl);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
				}else {
					for(ReleaseDevice rDevice : releaseDevices) {
						Device device = this.deviceMapper.selectDeviceById(rDevice.getDeviceId());
						device.setAdUrl("");
						this.deviceMapper.updateDevice(device);
						try {
							// 下发广告更新主题命令
							adIssued(device.getDeviceId()+"", "");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				
//				adSchedule.setReleaseStatus("1");// 已发布
				//todo 如果排期未完成，解凍金額
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("scheduleId", adSchedule.getAdScheduleId());
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				param.put("endTime", df.format(new Date()));
				Float hasPay = this.releaseRecordMapper.getAdTotalPay(param);
				fundLogService.adPublishFrozen(adSchedule.getAdvertiser(),
						new BigDecimal(Float.toString(hasPay-adSchedule.getTotalPay())));
				adSchedule.setReleaseStatus(AdConstant.AD_STOP_REPUBLISH);
				return this.adScheduleMapper.updateAdSchedule(adSchedule);
			} else {
				logger.error("调用审核通过下发排期计划接口失败!" + adHttp.toString());
				throw new RRException("调用排期接口失败!");
			}
		
	}

}
