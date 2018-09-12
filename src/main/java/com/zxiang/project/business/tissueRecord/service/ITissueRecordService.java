package com.zxiang.project.business.tissueRecord.service;

import com.zxiang.project.business.tissueRecord.domain.TissueRecord;
import java.util.List;

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
	
}
