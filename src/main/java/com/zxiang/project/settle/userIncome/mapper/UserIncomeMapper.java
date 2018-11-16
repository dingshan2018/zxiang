package com.zxiang.project.settle.userIncome.mapper;

import com.zxiang.project.settle.userIncome.domain.UserIncome;

import java.util.HashMap;
import java.util.List;	

/**
 * 客户收入日统计 数据层
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public interface UserIncomeMapper 
{
	/**
     * 查询客户收入日统计信息
     * 
     * @param incomeId 客户收入日统计ID
     * @return 客户收入日统计信息
     */
	public UserIncome selectUserIncomeById(Integer incomeId);
	
	/**
     * 查询客户收入日统计列表
     * 
     * @param userIncome 客户收入日统计信息
     * @return 客户收入日统计集合
     */
	public List<UserIncome> selectUserIncomeList(UserIncome userIncome);
	
	/**
     * 新增客户收入日统计
     * 
     * @param userIncome 客户收入日统计信息
     * @return 结果
     */
	public int insertUserIncome(UserIncome userIncome);
	
	/**
     * 修改客户收入日统计
     * 
     * @param userIncome 客户收入日统计信息
     * @return 结果
     */
	public int updateUserIncome(UserIncome userIncome);
	
	/**
     * 删除客户收入日统计
     * 
     * @param incomeId 客户收入日统计ID
     * @return 结果
     */
	public int deleteUserIncomeById(Integer incomeId);
	
	/**
     * 批量删除客户收入日统计
     * 
     * @param incomeIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteUserIncomeByIds(String[] incomeIds);
	
	/**
	 * 判断是否有记录
	 * 
	 * */
	public List<UserIncome> selectUserIncome(UserIncome userIncome);
	/**
	 * 获取销售人员类型
	 * */
	public List<HashMap<String, Object>> selectzxsellerlist(String sellerId);
	
	/**
	 * 通过主体获取人员信息
	 * */
	public List<HashMap<String, Object>> selectuserbypuserId(String sellerId);
	/**
	 * 代理商
	 * */
	public List<HashMap<String, Object>> selectzxagentlist(HashMap<String, Object> map);
	
	/**
	 * 代理商
	 * */
	public List<HashMap<String, Object>> selectzxagent(HashMap<String, Object> map);
	/**
	 * 加盟商
	 * */
	public List<HashMap<String, Object>> selectzxjoinlist(HashMap<String, Object> map);
	/**
	 * 服务商
	 * */
	public List<HashMap<String, Object>> selectzxrepairlist(HashMap<String, Object> map);
	
	public List<HashMap<String, Object>> selectzxrepairarealist(HashMap<String, Object> map);
	
	List<?> queryExport(HashMap<String, String> map);
	
}