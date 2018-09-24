package com.zxiang.project.client.advertise.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxiang.common.constant.UserConstants;
import com.zxiang.common.support.Convert;
import com.zxiang.common.utils.security.ShiroUtils;
import com.zxiang.framework.shiro.service.PasswordService;
import com.zxiang.project.client.advertise.domain.Advertise;
import com.zxiang.project.client.advertise.mapper.AdvertiseMapper;
import com.zxiang.project.system.user.domain.User;
import com.zxiang.project.system.user.mapper.UserMapper;

/**
 * 广告商 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Service
public class AdvertiseServiceImpl implements IAdvertiseService 
{
	@Autowired
	private AdvertiseMapper advertiseMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
    private PasswordService passwordService;

	/**
     * 查询广告商信息
     * 
     * @param advertiseId 广告商ID
     * @return 广告商信息
     */
    @Override
	public Advertise selectAdvertiseById(Integer advertiseId)
	{
	    return advertiseMapper.selectAdvertiseById(advertiseId);
	}
	
	/**
     * 查询广告商列表
     * 
     * @param advertise 广告商信息
     * @return 广告商集合
     */
	@Override
	public List<Advertise> selectAdvertiseList(Advertise advertise)
	{
	    return advertiseMapper.selectAdvertiseList(advertise);
	}
	
    /**
     * 新增广告商
     * 
     * @param advertise 广告商信息
     * @return 结果
     */
	@Override
	public int insertAdvertise(Advertise advertise) {
		if(StringUtils.isNotBlank(advertise.getManagerPhone())) {
			// 根据管理者新增用户
			User user = userMapper.selectUserByPhoneNumber(advertise.getManagerPhone());
			if(user == null) {
				user = new User();
				user.randomSalt();
				user.setPhonenumber(advertise.getManagerPhone());
				user.setLoginName(advertise.getManagerPhone());
				user.setUserName(advertise.getManagerName());
				user.setPassword(passwordService.encryptPassword(user.getLoginName(), advertise.getManagerPhone(), user.getSalt()));
		        user.setCreateBy(ShiroUtils.getLoginName());
		        user.setUserType(UserConstants.USER_TYPE_ADVERTISE);
		        userMapper.insertUser(user);
		        advertise.setManagerId(user.getUserId().intValue());
			}
		}
		advertise.setCreateTime(new Date());
		advertise.setCreateBy(ShiroUtils.getLoginName());
	    return advertiseMapper.insertAdvertise(advertise);
	}
	
	/**
     * 修改广告商
     * 
     * @param advertise 广告商信息
     * @return 结果
     */
	@Override
	public int updateAdvertise(Advertise advertise) {
		if(advertise.getManagerId() == null) {
			if(StringUtils.isNotBlank(advertise.getManagerPhone())) {
				// 根据管理者新增用户
				User user = userMapper.selectUserByPhoneNumber(advertise.getManagerPhone());
				if(user == null) {
					user = new User();
					user.randomSalt();
					user.setPhonenumber(advertise.getManagerPhone());
					user.setLoginName(advertise.getManagerPhone());
					user.setUserName(advertise.getManagerName());
					user.setPassword(passwordService.encryptPassword(user.getLoginName(), advertise.getManagerPhone(), user.getSalt()));
					user.setCreateBy(ShiroUtils.getLoginName());
					user.setUserType(UserConstants.USER_TYPE_ADVERTISE);
					userMapper.insertUser(user);
					advertise.setManagerId(user.getUserId().intValue());
				}
			}
		}
		advertise.setUpdateTime(new Date());
		advertise.setUpdateBy(ShiroUtils.getLoginName());
	    return advertiseMapper.updateAdvertise(advertise);
	}

	/**
     * 删除广告商对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAdvertiseByIds(String ids)
	{
		return advertiseMapper.deleteAdvertiseByIds(Convert.toStrArray(ids));
	}
	
}
