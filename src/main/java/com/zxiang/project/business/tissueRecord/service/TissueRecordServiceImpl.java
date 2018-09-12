package com.zxiang.project.business.tissueRecord.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxiang.common.support.Convert;
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
	
}
