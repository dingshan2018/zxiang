package com.zxiang.project.client.join.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zxiang.project.client.join.domain.Join;	

/**
 * 机主 数据层
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public interface JoinMapper 
{
	/**
     * 查询机主信息
     * 
     * @param joinId 机主ID
     * @return 机主信息
     */
	public Join selectJoinById(Integer joinId);
	
	/**
     * 查询机主列表
     * 
     * @param join 机主信息
     * @return 机主集合
     */
	public List<Join> selectJoinList(Join join);
	
	/**
     * 新增机主
     * 
     * @param join 机主信息
     * @return 结果
     */
	public int insertJoin(Join join);
	
	/**
     * 修改机主
     * 
     * @param join 机主信息
     * @return 结果
     */
	public int updateJoin(Join join);
	
	/**
     * 删除机主
     * 
     * @param joinId 机主ID
     * @return 结果
     */
	public int deleteJoinById(Integer joinId);
	
	/**
     * 批量删除机主
     * 
     * @param joinIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteJoinByIds(String[] joinIds);

	/**
	 * 查找机主下拉框数据
	 */
	public List<Join> selectDropBoxList();
	/**
	 * 更新余额
	 * @param advertiseId
	 * @param balance
	 * @param frozenBalance
	 * @return
	 */
	public int updateBalance(@Param("joinId")Integer joinId,@Param("balance")BigDecimal balance,@Param("frozenBalance")BigDecimal frozenBalance);
	
}