package com.zxiang.project.client.join.mapper;

import com.zxiang.project.client.join.domain.Join;
import java.util.List;	

/**
 * 加盟商 数据层
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public interface JoinMapper 
{
	/**
     * 查询加盟商信息
     * 
     * @param joinId 加盟商ID
     * @return 加盟商信息
     */
	public Join selectJoinById(Integer joinId);
	
	/**
     * 查询加盟商列表
     * 
     * @param join 加盟商信息
     * @return 加盟商集合
     */
	public List<Join> selectJoinList(Join join);
	
	/**
     * 新增加盟商
     * 
     * @param join 加盟商信息
     * @return 结果
     */
	public int insertJoin(Join join);
	
	/**
     * 修改加盟商
     * 
     * @param join 加盟商信息
     * @return 结果
     */
	public int updateJoin(Join join);
	
	/**
     * 删除加盟商
     * 
     * @param joinId 加盟商ID
     * @return 结果
     */
	public int deleteJoinById(Integer joinId);
	
	/**
     * 批量删除加盟商
     * 
     * @param joinIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteJoinByIds(String[] joinIds);

	/**
	 * 查找加盟商下拉框数据
	 */
	public List<Join> selectDropBoxList();
	
}