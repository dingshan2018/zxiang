package com.zxiang.project.business.supplyTissue.service;

import com.zxiang.project.business.supplyTissue.domain.SupplyTissue;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 补纸记录 服务层
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
public interface ISupplyTissueService 
{
	/**
     * 查询补纸记录信息
     * 
     * @param supplyTissueId 补纸记录ID
     * @return 补纸记录信息
     */
	public SupplyTissue selectSupplyTissueById(Integer supplyTissueId);
	
	/**
     * 查询补纸记录列表
     * 
     * @param supplyTissue 补纸记录信息
     * @return 补纸记录集合
     */
	public List<SupplyTissue> selectSupplyTissueList(SupplyTissue supplyTissue);
	
	/**
     * 新增补纸记录
     * 
     * @param supplyTissue 补纸记录信息
     * @return 结果
     */
	public int insertSupplyTissue(SupplyTissue supplyTissue);
	
	/**
     * 修改补纸记录
     * 
     * @param supplyTissue 补纸记录信息
     * @return 结果
     */
	public int updateSupplyTissue(SupplyTissue supplyTissue);
		
	/**
     * 删除补纸记录信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSupplyTissueByIds(String ids);

	public void queryExport(HashMap<String, String> params, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
