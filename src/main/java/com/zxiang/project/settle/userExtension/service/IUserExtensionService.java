package com.zxiang.project.settle.userExtension.service;

import com.zxiang.project.settle.userExtension.domain.UserExtension;
import java.util.List;

/**
 * 客户推广日统计 服务层
 * 
 * @author ZXiang
 * @date 2018-11-26
 */
public interface IUserExtensionService 
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
     * 删除客户推广日统计信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteUserExtensionByIds(String ids);
	
}
