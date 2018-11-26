package com.zxiang.project.settle.userExtension.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zxiang.project.settle.userExtension.mapper.UserExtensionMapper;
import com.zxiang.project.settle.userExtension.domain.UserExtension;
import com.zxiang.project.settle.userExtension.service.IUserExtensionService;
import com.zxiang.common.support.Convert;

/**
 * 客户推广日统计 服务层实现
 * 
 * @author ZXiang
 * @date 2018-11-26
 */
@Service
public class UserExtensionServiceImpl implements IUserExtensionService 
{
	@Autowired
	private UserExtensionMapper userExtensionMapper;

	/**
     * 查询客户推广日统计信息
     * 
     * @param incomeId 客户推广日统计ID
     * @return 客户推广日统计信息
     */
    @Override
	public UserExtension selectUserExtensionById(Integer incomeId)
	{
	    return userExtensionMapper.selectUserExtensionById(incomeId);
	}
	
	/**
     * 查询客户推广日统计列表
     * 
     * @param userExtension 客户推广日统计信息
     * @return 客户推广日统计集合
     */
	@Override
	public List<UserExtension> selectUserExtensionList(UserExtension userExtension)
	{
	    return userExtensionMapper.selectUserExtensionList(userExtension);
	}
	
    /**
     * 新增客户推广日统计
     * 
     * @param userExtension 客户推广日统计信息
     * @return 结果
     */
	@Override
	public int insertUserExtension(UserExtension userExtension)
	{
	    return userExtensionMapper.insertUserExtension(userExtension);
	}
	
	/**
     * 修改客户推广日统计
     * 
     * @param userExtension 客户推广日统计信息
     * @return 结果
     */
	@Override
	public int updateUserExtension(UserExtension userExtension)
	{
	    return userExtensionMapper.updateUserExtension(userExtension);
	}
	
	@Override
	public List<UserExtension> selectUserExtension(UserExtension userExtension) {
		return userExtensionMapper.selectUserExtension(userExtension);
	}

	/**
     * 删除客户推广日统计对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteUserExtensionByIds(String ids)
	{
		return userExtensionMapper.deleteUserExtensionByIds(Convert.toStrArray(ids));
	}

	
	
}
