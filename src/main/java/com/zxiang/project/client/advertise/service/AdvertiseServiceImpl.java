package com.zxiang.project.client.advertise.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxiang.common.constant.UserConstants;
import com.zxiang.common.exception.RRException;
import com.zxiang.common.support.Convert;
import com.zxiang.common.utils.security.ShiroUtils;
import com.zxiang.framework.shiro.service.PasswordService;
import com.zxiang.project.client.advertise.domain.Advertise;
import com.zxiang.project.client.advertise.mapper.AdvertiseMapper;
import com.zxiang.project.system.dept.domain.Dept;
import com.zxiang.project.system.dept.mapper.DeptMapper;
import com.zxiang.project.system.role.service.IRoleService;
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
	@Autowired
	private DeptMapper deptMapper;
	@Autowired
	private IRoleService iroleService;

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
		User user = null;
		if(StringUtils.isNotBlank(advertise.getManagerPhone())) {
			// 根据管理者新增用户
			user = userMapper.selectUserByPhoneNumber(advertise.getManagerPhone());
			if(user != null) {
				throw new RRException(String.format("该手机号[%s]对应的用户已存在", advertise.getManagerPhone()));
			}
			user = new User();
			user.randomSalt();
			user.setPhonenumber(advertise.getManagerPhone());
			user.setLoginName(advertise.getManagerPhone());
			user.setUserName(advertise.getManagerName());
			user.setPassword(passwordService.encryptPassword(user.getLoginName(), advertise.getManagerPhone(), user.getSalt()));
			user.setCreateBy(ShiroUtils.getLoginName());
			user.setUserType(UserConstants.USER_TYPE_ADVERTISE);
			
			Dept dept = new Dept();
			dept.setDeptName(UserConstants.DEPT_NAME);
			List<Dept> depts = deptMapper.selectDeptList(dept);
			if(depts != null && depts.size() > 0) {
				user.setDeptId(depts.get(0).getDeptId());
			}
			userMapper.insertUser(user);
			advertise.setManagerId(user.getUserId().intValue());
			// 设置默认角色
			iroleService.setDefaultRole(user, UserConstants.defaultRoleKey.get(UserConstants.USER_TYPE_ADVERTISE));
		}
		advertise.setCreateTime(new Date());
		advertise.setCreateBy(ShiroUtils.getLoginName());
		int i = advertiseMapper.insertAdvertise(advertise);
	    if(user != null) {
	    	user.setPuserId(advertise.getAdvertiseId());
	    	userMapper.updateUser(user);
	    }
	    return i;
	}
	
	/**
     * 修改广告商
     * 
     * @param advertise 广告商信息
     * @return 结果
     */
	@Override
	public int updateAdvertise(Advertise advertise) {
		/*if(advertise.getManagerId() == null) {
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
		}*/
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
	public int deleteAdvertiseByIds(String ids) {
		String[] idList = Convert.toStrArray(ids);
		Advertise advertise = null;
		User user = null;
		// 删除用户及对应的角色
		for (String id : idList) {
			advertise = advertiseMapper.selectAdvertiseById(Integer.valueOf(id));
			if(advertise != null && StringUtils.isNotBlank(advertise.getManagerPhone())) {
				user = userMapper.selectUserByPhoneNumber(advertise.getManagerPhone());
				if(user == null) {
					continue;
				}
				userMapper.deleteUserById(user.getUserId());
				iroleService.deleteRoleByUserId(user.getUserId());
			}
		}
		return advertiseMapper.deleteAdvertiseByIds(Convert.toStrArray(ids));
	}

	/**
	 * 查找广告商下拉框数据
	 */
	@Override
	public List<Advertise> selectDropBoxList() {
		return advertiseMapper.selectDropBoxList();
	}
	
}
