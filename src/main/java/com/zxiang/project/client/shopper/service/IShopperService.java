package com.zxiang.project.client.shopper.service;

import java.util.List;

import com.zxiang.project.client.join.domain.Join;
import com.zxiang.project.client.shopper.domain.Shopper;

/**
 * 机主 服务层
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public interface IShopperService {
	/**
	 * 查询机主信息
	 * 
	 * @param joinId
	 *            机主ID
	 * @return 机主信息
	 */
	public Shopper selectShopperById(Integer joinId);

	/**
	 * 查询机主列表
	 * 
	 * @param join
	 *            机主信息
	 * @return 机主集合
	 */
	public List<Shopper> selectShopperList(Shopper join);

	/**
	 * 新增机主
	 * 
	 * @param join
	 *            机主信息
	 * @return 结果
	 */
	public int insertShopper(Shopper join);

	/**
	 * 修改机主
	 * 
	 * @param join
	 *            机主信息
	 * @return 结果
	 */
	public int updateShopper(Shopper join);

	/**
	 * 删除机主信息
	 * 
	 * @param ids
	 *            需要删除的数据ID
	 * @return 结果
	 */
	public int deleteShopperByIds(String ids);

	/**
	 * 查找机主下拉框数据
	 */
	public List<Shopper> selectDropBoxList();

	public void batchEditParam(Shopper join);

}
