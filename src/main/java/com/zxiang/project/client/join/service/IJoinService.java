package com.zxiang.project.client.join.service;

import java.util.List;

import com.zxiang.project.client.join.domain.Join;

/**
 * 机主 服务层
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public interface IJoinService {
	/**
	 * 查询机主信息
	 * 
	 * @param joinId
	 *            机主ID
	 * @return 机主信息
	 */
	public Join selectJoinById(Integer joinId);

	/**
	 * 查询机主列表
	 * 
	 * @param join
	 *            机主信息
	 * @return 机主集合
	 */
	public List<Join> selectJoinList(Join join);

	/**
	 * 新增机主
	 * 
	 * @param join
	 *            机主信息
	 * @return 结果
	 */
	public int insertJoin(Join join);

	/**
	 * 修改机主
	 * 
	 * @param join
	 *            机主信息
	 * @return 结果
	 */
	public int updateJoin(Join join);

	/**
	 * 删除机主信息
	 * 
	 * @param ids
	 *            需要删除的数据ID
	 * @return 结果
	 */
	public int deleteJoinByIds(String ids);

	/**
	 * 查找机主下拉框数据
	 */
	public List<Join> selectDropBoxList();

	public void batchEditParam(Join join);

}
