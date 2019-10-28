package com.zxiang.project.client.shopper.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zxiang.project.client.join.domain.Join;
import com.zxiang.project.client.shopper.domain.Shopper;

/**
 * 机主 数据层
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public interface ShopperMapper {
	/**
	 * 查询机主信息
	 * 
	 * @param joinId
	 *            机主ID
	 * @return 机主信息
	 */
	public Shopper selectShopperById(Integer shopperId);

	/**
	 * 查询机主列表
	 * 
	 * @param join
	 *            机主信息
	 * @return 机主集合
	 */
	public List<Shopper> selectShopperList(Shopper shopper);

	/**
	 * 新增机主
	 * 
	 * @param shopper
	 *            机主信息
	 * @return 结果
	 */
	public int insertShopper(Shopper shopper);

	/**
	 * 修改机主
	 * 
	 * @param shopper
	 *            机主信息
	 * @return 结果
	 */
	public int updateShopper(Shopper join);

	/**
	 * 删除机主
	 * 
	 * @param joinId
	 *            机主ID
	 * @return 结果
	 */
	public int deleteShopperById(Integer joinId);

	/**
	 * 批量删除机主
	 * 
	 * @param joinIds
	 *            需要删除的数据ID
	 * @return 结果
	 */
	public int deleteShopperByIds(String[] joinIds);

	/**
	 * 查找机主下拉框数据
	 */
	public List<Shopper> selectDropBoxList();

	/**
	 * 更新余额
	 * 
	 * @param advertiseId
	 * @param balance
	 * @param frozenBalance
	 * @return
	 */
	public int updateBalance(@Param("shopperId") Integer joinId, @Param("balance") BigDecimal balance,
			@Param("frozenBalance") BigDecimal frozenBalance);

	public int batchEditParam(Shopper shopper);

}