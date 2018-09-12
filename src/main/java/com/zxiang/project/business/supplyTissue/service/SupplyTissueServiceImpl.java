package com.zxiang.project.business.supplyTissue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxiang.common.support.Convert;
import com.zxiang.project.business.supplyTissue.domain.SupplyTissue;
import com.zxiang.project.business.supplyTissue.mapper.SupplyTissueMapper;

/**
 * 补纸记录 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
@Service
public class SupplyTissueServiceImpl implements ISupplyTissueService 
{
	@Autowired
	private SupplyTissueMapper supplyTissueMapper;

	/**
     * 查询补纸记录信息
     * 
     * @param supplyTissueId 补纸记录ID
     * @return 补纸记录信息
     */
    @Override
	public SupplyTissue selectSupplyTissueById(Integer supplyTissueId)
	{
	    return supplyTissueMapper.selectSupplyTissueById(supplyTissueId);
	}
	
	/**
     * 查询补纸记录列表
     * 
     * @param supplyTissue 补纸记录信息
     * @return 补纸记录集合
     */
	@Override
	public List<SupplyTissue> selectSupplyTissueList(SupplyTissue supplyTissue)
	{
	    return supplyTissueMapper.selectSupplyTissueList(supplyTissue);
	}
	
    /**
     * 新增补纸记录
     * 
     * @param supplyTissue 补纸记录信息
     * @return 结果
     */
	@Override
	public int insertSupplyTissue(SupplyTissue supplyTissue)
	{
	    return supplyTissueMapper.insertSupplyTissue(supplyTissue);
	}
	
	/**
     * 修改补纸记录
     * 
     * @param supplyTissue 补纸记录信息
     * @return 结果
     */
	@Override
	public int updateSupplyTissue(SupplyTissue supplyTissue)
	{
	    return supplyTissueMapper.updateSupplyTissue(supplyTissue);
	}

	/**
     * 删除补纸记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSupplyTissueByIds(String ids)
	{
		return supplyTissueMapper.deleteSupplyTissueByIds(Convert.toStrArray(ids));
	}
	
}
