package com.zxiang.project.business.tissueRecord.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zxiang.project.business.tissueRecord.domain.TissueRecord;	

/**
 * 出纸记录 数据层
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
public interface TissueRecordMapper 
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
     * 删除出纸记录
     * 
     * @param tissueRecordId 出纸记录ID
     * @return 结果
     */
	public int deleteTissueRecordById(Integer tissueRecordId);
	
	/**
     * 批量删除出纸记录
     * 
     * @param tissueRecordIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteTissueRecordByIds(String[] tissueRecordIds);

	public int selectTotal(Map<String,Object> map);

	public List queryExport(HashMap<String, String> params);

	/**
	 * 根据条件查询出纸数量及出纸金额统计
	 * @param params
	 * @return
	 */
	public HashMap<String, Object> tissueCount(HashMap<String, String> params);
	
}