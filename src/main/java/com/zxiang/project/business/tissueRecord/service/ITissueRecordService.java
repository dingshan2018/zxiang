package com.zxiang.project.business.tissueRecord.service;

import com.zxiang.project.business.tissueRecord.domain.TissueRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 出纸记录 服务层
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
public interface ITissueRecordService 
{
	/**
     * 查询出纸记录信息
     * 
     * @param tissueRecordId 出纸记录ID
     * @return 出纸记录信息
     */
	public TissueRecord selectTissueRecordById(Integer tissueRecordId);
	
	/**
     * 查询出纸记录列表
     * 
     * @param tissueRecord 出纸记录信息
     * @return 出纸记录集合
     */
	public List<TissueRecord> selectTissueRecordList(TissueRecord tissueRecord);
	
	/**
     * 新增出纸记录
     * 
     * @param tissueRecord 出纸记录信息
     * @return 结果
     */
	public int insertTissueRecord(TissueRecord tissueRecord);
	
	/**
     * 修改出纸记录
     * 
     * @param tissueRecord 出纸记录信息
     * @return 结果
     */
	public int updateTissueRecord(TissueRecord tissueRecord);
		
	/**
     * 删除出纸记录信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTissueRecordByIds(String ids);

	public void queryExport(HashMap<String, String> params, HttpServletRequest request, HttpServletResponse response) throws Exception;

	/**
	 * 根据条件查询出纸数量及出纸金额统计
	 * @param params
	 * @return
	 */
	public Map<String, Object> tissueCount(HashMap<String, String> params);
	
}
