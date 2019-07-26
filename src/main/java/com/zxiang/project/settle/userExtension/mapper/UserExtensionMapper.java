package com.zxiang.project.settle.userExtension.mapper;

import com.zxiang.project.settle.userExtension.domain.UserExtension;
import com.zxiang.project.settle.userIncome.domain.UserIncome;

import java.util.HashMap;
import java.util.List;	

/**
 * 客户推广日统计 数据层
 * 
 * @author ZXiang
 * @date 2018-11-26
 */
public interface UserExtensionMapper 
{
	/**
     * 查询客户推广日统计信息
     * 
     * @param incomeId 客户推广日统计ID
     * @return 客户推广日统计信息
     */
	public UserExtension selectUserExtensionById(Integer incomeId);
	
	/**
     * 查询客户推广日统计列表
     * 
     * @param userExtension 客户推广日统计信息
     * @return 客户推广日统计集合
     */
	public List<UserExtension> selectUserExtensionList(UserExtension userExtension);
	
	/**
     * 新增客户推广日统计
     * 
     * @param userExtension 客户推广日统计信息
     * @return 结果
     */
	public int insertUserExtension(UserExtension userExtension);
	
	/**
     * 修改客户推广日统计
     * 
     * @param userExtension 客户推广日统计信息
     * @return 结果
     */
	public int updateUserExtension(UserExtension userExtension);
	
	
	public List<UserExtension> selectUserExtension(UserExtension userExtension);
	
	/**
	 * 获取历史用户推广收益
	 * @param userExtension
	 * @return
	 */
	public List<UserExtension> selectCurUserExtension(UserExtension userExtension);
	
	
	/**
     * 删除客户推广日统计
     * 
     * @param incomeId 客户推广日统计ID
     * @return 结果
     */
	public int deleteUserExtensionById(Integer incomeId);
	
	/**
     * 批量删除客户推广日统计
     * 
     * @param incomeIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteUserExtensionByIds(String[] incomeIds);
	
	List<?> queryExport(HashMap<String, String> map);
	
	/**
	 * 刪除錯誤統計
	 * @param userExtension
	 * @return
	 */
	public int deleteWrongExtendIncome(UserExtension userExtension);
	
}