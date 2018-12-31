package com.zxiang.project.client.join.service;

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
import com.zxiang.project.client.join.domain.Join;
import com.zxiang.project.client.join.mapper.JoinMapper;
import com.zxiang.project.system.dept.domain.Dept;
import com.zxiang.project.system.dept.mapper.DeptMapper;
import com.zxiang.project.system.role.service.IRoleService;
import com.zxiang.project.system.user.domain.User;
import com.zxiang.project.system.user.mapper.UserMapper;

/**
 * 机主 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Service
public class JoinServiceImpl implements IJoinService 
{
	@Autowired
	private JoinMapper joinMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
    private PasswordService passwordService;
	@Autowired
	private DeptMapper deptMapper;
	@Autowired
	private IRoleService iroleService;

	/**
     * 查询机主信息
     * 
     * @param joinId 机主ID
     * @return 机主信息
     */
    @Override
	public Join selectJoinById(Integer joinId)
	{
	    return joinMapper.selectJoinById(joinId);
	}
	
	/**
     * 查询机主列表
     * 
     * @param join 机主信息
     * @return 机主集合
     */
	@Override
	public List<Join> selectJoinList(Join join)
	{
	    return joinMapper.selectJoinList(join);
	}
	
    /**
     * 新增机主
     * 
     * @param join 机主信息
     * @return 结果
     */
	@Override
	public int insertJoin(Join join) {
		User user = null;
		if(StringUtils.isNotBlank(join.getManagerPhone())) {
			// 根据管理者新增用户
			user = userMapper.selectUserByPhoneNumber(join.getManagerPhone());
			if(user != null) {
				throw new RRException(String.format("该手机号[%s]对应的用户已存在", join.getManagerPhone()));
			}
			user = new User();
			user.randomSalt();
			user.setPhonenumber(join.getManagerPhone());
			user.setLoginName(join.getManagerPhone());
			user.setUserName(join.getManagerName());
			user.setPassword(passwordService.encryptPassword(user.getLoginName(), join.getManagerPhone(), user.getSalt()));
			user.setCreateBy(ShiroUtils.getLoginName());
			user.setUserType(UserConstants.USER_TYPE_JOIN);
			
			Dept dept = new Dept();
			dept.setDeptName(UserConstants.DEPT_NAME);
			List<Dept> depts = deptMapper.selectDeptList(dept);
			if(depts != null && depts.size() > 0) {
				user.setDeptId(depts.get(0).getDeptId());
			}
			userMapper.insertUser(user);
			join.setJoinerId(user.getUserId().intValue());
			// 设置默认角色
			iroleService.setDefaultRole(user, UserConstants.ROLE_NAME_JOIN);
		}
		join.setCreateTime(new Date());
		join.setCreateBy(ShiroUtils.getLoginName());
		// 设置默认参数
		join.setAdRate(0.4f);
		join.setAdCarouselRate(0.4f);
		join.setScanRate(0.3f);
		join.setPromDirectRate(1000f);
		join.setPromIndirectRate(500f);
		join.setPromPaperRate(0.7f);
		join.setPromotionRate(0.15f);
		join.setServeRate(0.025f);
	    int i = joinMapper.insertJoin(join);
	    if(user != null) {
	    	user.setPuserId(join.getJoinId());
	    	userMapper.updateUser(user);
	    }
	    return i;
	}
	
	/**
     * 修改机主
     * 
     * @param join 机主信息
     * @return 结果
     */
	@Override
	public int updateJoin(Join join) {
		/*if(StringUtils.isNotBlank(join.getManagerPhone())) {
			// 根据管理者新增用户
			User user = userMapper.selectUserByPhoneNumber(join.getManagerPhone());
			if(user == null) {
				user = new User();
				user.randomSalt();
				user.setPhonenumber(join.getManagerPhone());
				user.setLoginName(join.getManagerPhone());
				user.setUserName(join.getManagerName());
				user.setPassword(passwordService.encryptPassword(user.getLoginName(), join.getManagerPhone(), user.getSalt()));
				user.setCreateBy(ShiroUtils.getLoginName());
				user.setUserType(UserConstants.USER_TYPE_JOIN);
				userMapper.insertUser(user);
				join.setJoinerId(user.getUserId().intValue());
			}
		}*/
		join.setUpdateTime(new Date());
		join.setUpdateBy(ShiroUtils.getLoginName());
	    return joinMapper.updateJoin(join);
	}

	/**
     * 删除机主对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteJoinByIds(String ids) {
		String[] idList = Convert.toStrArray(ids);
		Join join = null;
		User user = null;
		for (String id : idList) {
			join = joinMapper.selectJoinById(Integer.valueOf(id));
			if(join != null && StringUtils.isNotBlank(join.getManagerPhone())) {
				user = userMapper.selectUserByPhoneNumber(join.getManagerPhone());
				if(user == null) {
					continue;
				}
				userMapper.deleteUserById(user.getUserId());
				iroleService.deleteRoleByUserId(user.getUserId());
			}
		}
		return joinMapper.deleteJoinByIds(idList);
	}

	@Override
	public List<Join> selectDropBoxList() {
		return joinMapper.selectDropBoxList();
	}
	
}
