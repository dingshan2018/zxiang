package com.zxiang.project.settle.userIncome.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zxiang.project.settle.userIncome.mapper.UserIncomeMapper;
import com.zxiang.project.settle.userIncome.domain.UserIncome;
import com.zxiang.project.settle.userIncome.service.IUserIncomeService;
import com.zxiang.common.support.Convert;

/**
 * 客户收入日统计 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Service
public class UserIncomeServiceImpl implements IUserIncomeService 
{
	@Autowired
	private UserIncomeMapper userIncomeMapper;

	/**
     * 查询客户收入日统计信息
     * 
     * @param incomeId 客户收入日统计ID
     * @return 客户收入日统计信息
     */
    @Override
	public UserIncome selectUserIncomeById(Integer incomeId)
	{
	    return userIncomeMapper.selectUserIncomeById(incomeId);
	}
	
	/**
     * 查询客户收入日统计列表
     * 
     * @param userIncome 客户收入日统计信息
     * @return 客户收入日统计集合
     */
	@Override
	public List<UserIncome> selectUserIncomeList(UserIncome userIncome)
	{
	    return userIncomeMapper.selectUserIncomeList(userIncome);
	}
	
    /**
     * 新增客户收入日统计
     * 
     * @param userIncome 客户收入日统计信息
     * @return 结果
     */
	@Override
	public int insertUserIncome(UserIncome userIncome)
	{
	    return userIncomeMapper.insertUserIncome(userIncome);
	}
	
	/**
     * 修改客户收入日统计
     * 
     * @param userIncome 客户收入日统计信息
     * @return 结果
     */
	@Override
	public int updateUserIncome(UserIncome userIncome)
	{
	    return userIncomeMapper.updateUserIncome(userIncome);
	}

	/**
     * 删除客户收入日统计对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteUserIncomeByIds(String ids)
	{
		return userIncomeMapper.deleteUserIncomeByIds(Convert.toStrArray(ids));
	}
	
}
