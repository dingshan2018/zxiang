package com.zxiang.project.business.tissueRecord.service;

import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxiang.common.constant.UserConstants;
import com.zxiang.common.support.Convert;
import com.zxiang.common.utils.excel.EXCELObject;
import com.zxiang.common.utils.security.ShiroUtils;
import com.zxiang.framework.aspectj.lang.annotation.DataFilter;
import com.zxiang.project.business.tissueRecord.domain.TissueRecord;
import com.zxiang.project.business.tissueRecord.mapper.TissueRecordMapper;

/**
 * 出纸记录 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
@Service
public class TissueRecordServiceImpl implements ITissueRecordService 
{
	@Autowired
	private TissueRecordMapper tissueRecordMapper;

	/**
     * 查询出纸记录信息
     * 
     * @param tissueRecordId 出纸记录ID
     * @return 出纸记录信息
     */
    @Override
	public TissueRecord selectTissueRecordById(Integer tissueRecordId)
	{
	    return tissueRecordMapper.selectTissueRecordById(tissueRecordId);
	}
	
	/**
     * 查询出纸记录列表
     * 
     * @param tissueRecord 出纸记录信息
     * @return 出纸记录集合
     */
	@Override
	public List<TissueRecord> selectTissueRecordList(TissueRecord tissueRecord)
	{
	    return tissueRecordMapper.selectTissueRecordList(tissueRecord);
	}
	
    /**
     * 新增出纸记录
     * 
     * @param tissueRecord 出纸记录信息
     * @return 结果
     */
	@Override
	public int insertTissueRecord(TissueRecord tissueRecord)
	{
	    return tissueRecordMapper.insertTissueRecord(tissueRecord);
	}
	
	/**
     * 修改出纸记录
     * 
     * @param tissueRecord 出纸记录信息
     * @return 结果
     */
	@Override
	public int updateTissueRecord(TissueRecord tissueRecord)
	{
	    return tissueRecordMapper.updateTissueRecord(tissueRecord);
	}

	/**
     * 删除出纸记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTissueRecordByIds(String ids)
	{
		return tissueRecordMapper.deleteTissueRecordByIds(Convert.toStrArray(ids));
	}

	@Override
	public void queryExport(HashMap<String, String> params, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List erportList = tissueRecordMapper.queryExport(params);
      	String realPath = request.getSession().getServletContext().getRealPath("/file/temp");
  		EXCELObject s = new EXCELObject();
  		s.seteFilePath(realPath);
  		//表头名称
  		String[] titH = { "ID", "投放设备", "终端","场所名称","出纸价格","出纸长度","用户微信账号",
  				"微信昵称", "微信头像","出纸类型", "创建者","创建时间"};
  		//数据库字段名称
  		String[] titN = { "tissue_record_id","deviceSn","terminalCode","placeName","price","tissueLenName","open_id",
  				"nick_name", "headimgurl","tissue_channel","create_by","create_time"};
  		String[] width= 
  			   {"15","20","20","20","20","20","20",
  				"20","50","20","20","20"};
  		s.setWidth(width);
  		s.setFname("出纸记录"); // sheet栏名称
  		s.setTitle("出纸记录"); // Excel内容标题名称
  		s.setTitH(titH);
  		s.setTitN(titN);
  		s.setDataList(erportList);
  		File exportFile = null;
  		exportFile = s.setData();
  		//Excel文件名称
  		String excelName = "出纸记录" + System.currentTimeMillis() + ".xls";
  		s.exportExcel("出纸记录", excelName, exportFile, request, response);
		
	}

	/**
	 * 根据条件查询出纸数量及出纸金额统计
	 */
	@Override
	public Map<String, Object> tissueCount(HashMap<String, String> params) {
		Map<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> tissueCount = tissueRecordMapper.tissueCount(params);
		
		if(tissueCount != null){
			Long paperSum = 0L;
			if(tissueCount.get("paperSum") != null){
				paperSum =  (Long) tissueCount.get("paperSum");
			}
			Long validPaperSum = 0L;
			if(tissueCount.get("validPaperSum") != null){
				validPaperSum =  Long.parseLong(tissueCount.get("validPaperSum").toString());
			}
			Long invalidPaperSum = 0L;
			if(tissueCount.get("invalidPaperSum") != null){
				invalidPaperSum =  Long.parseLong(tissueCount.get("invalidPaperSum").toString());
			}
			String moneySum = "0";
			if(tissueCount.get("moneySum") != null){
				moneySum = new DecimalFormat("###,##0.00").format(tissueCount.get("moneySum"));
			}
			String validMoneySum = "0";
			if(tissueCount.get("validMoneySum") != null){
				validMoneySum = new DecimalFormat("###,##0.00").format(tissueCount.get("validMoneySum"));
			}
			result.put("paperSum", paperSum);
			result.put("moneySum", moneySum);
			result.put("validPaperSum", validPaperSum);
			result.put("invalidPaperSum", invalidPaperSum);
			result.put("validMoneySum", validMoneySum);
		}else{
			result.put("paperSum", "0");
			result.put("moneySum", "0");
			result.put("validPaperSum", "0");
			result.put("invalidPaperSum", "0");
			result.put("validMoneySum", "0.0");
		}
		
		return result;
	}
	@DataFilter(placeAlias="tr.place_id")
	@Override
	public int selectTotal(Map<String,Object> map) {
		String userType = ShiroUtils.getUser().getUserType();
		if(userType.equals(UserConstants.USER_TYPE_JOIN)) {
			map.put("userId", ShiroUtils.getUser().getUserId());
		}
		return tissueRecordMapper.selectTotal(map);
	}

	@Override
	public void queryExportData(HashMap<String, String> params, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List erportList = tissueRecordMapper.queryExportData(params);
      	String realPath = request.getSession().getServletContext().getRealPath("/file/temp");
  		EXCELObject s = new EXCELObject();
  		s.seteFilePath(realPath);
  		//表头名称
  		String[] titH = {"投放设备", "广告名称","统计日期","有效次数"};
  		//数据库字段名称
  		String[] titN = { "deviceCode","adName","tissueDate","tissueCnt"};
  		String[] width= 
  			   {"15","20","20","20"};
  		s.setWidth(width);
  		s.setFname("出纸记录"); // sheet栏名称
  		s.setTitle("出纸记录"); // Excel内容标题名称
  		s.setTitH(titH);
  		s.setTitN(titN);
  		s.setDataList(erportList);
  		File exportFile = null;
  		exportFile = s.setData();
  		//Excel文件名称
  		String excelName = "出纸记录" + System.currentTimeMillis() + ".xls";
  		s.exportExcel("出纸记录", excelName, exportFile, request, response);
		
	}

	@Override
	public List<HashMap<String, Object>> selectTissueDataList(TissueRecord tissueRecord) {
		
		return tissueRecordMapper.selectTissueDataList(tissueRecord);
	}
	
}
