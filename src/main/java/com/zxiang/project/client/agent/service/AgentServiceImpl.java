package com.zxiang.project.client.agent.service;

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
import com.zxiang.project.client.agent.domain.Agent;
import com.zxiang.project.client.agent.mapper.AgentMapper;
import com.zxiang.project.client.join.domain.Join;
import com.zxiang.project.system.dept.domain.Dept;
import com.zxiang.project.system.dept.mapper.DeptMapper;
import com.zxiang.project.system.role.service.IRoleService;
import com.zxiang.project.system.user.domain.User;
import com.zxiang.project.system.user.mapper.UserMapper;

/**
 * 代理商 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Service
public class AgentServiceImpl implements IAgentService 
{
	@Autowired
	private AgentMapper agentMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
    private PasswordService passwordService;
	@Autowired
	private DeptMapper deptMapper;
	@Autowired
	private IRoleService iroleService;

	/**
     * 查询代理商信息
     * 
     * @param agentId 代理商ID
     * @return 代理商信息
     */
    @Override
	public Agent selectAgentById(Integer agentId)
	{
	    return agentMapper.selectAgentById(agentId);
	}
	
	/**
     * 查询代理商列表
     * 
     * @param agent 代理商信息
     * @return 代理商集合
     */
	@Override
	public List<Agent> selectAgentList(Agent agent)
	{
	    return agentMapper.selectAgentList(agent);
	}
	
    /**
     * 新增代理商
     * 
     * @param agent 代理商信息
     * @return 结果
     */
	@Override
	public int insertAgent(Agent agent) {
		User user = null;
		if(agent.getCounty() != null) {
			// 设置父代理商
			Agent parentAgent = agentMapper.queryParentAgent(agent.getCity());
			agent.setPagentId(parentAgent == null ? null : parentAgent.getAgentId());
		}
		if(StringUtils.isNotBlank(agent.getManagerPhone())) {
			// 根据管理者新增用户
			user = userMapper.selectUserByPhoneNumber(agent.getManagerPhone());
			if(user != null) {
				throw new RRException(String.format("该手机号[%s]对应的用户已存在", agent.getManagerPhone()));
			}
			user = new User();
			user.randomSalt();
			user.setPhonenumber(agent.getManagerPhone());
			user.setLoginName(agent.getManagerPhone());
			user.setUserName(agent.getManagerName());
			user.setPassword(passwordService.encryptPassword(user.getLoginName(), agent.getManagerPhone(), user.getSalt()));
			user.setCreateBy(ShiroUtils.getLoginName());
			user.setUserType(UserConstants.USER_TYPE_AGENT);
			
			Dept dept = new Dept();
			dept.setDeptName(UserConstants.DEPT_NAME);
			List<Dept> depts = deptMapper.selectDeptList(dept);
			if(depts != null && depts.size() > 0) {
				user.setDeptId(depts.get(0).getDeptId());
			}
			userMapper.insertUser(user);
			agent.setManagerId(user.getUserId().intValue());
			// 设置默认角色
			iroleService.setDefaultRole(user, UserConstants.ROLE_NAME_AGENT);
		}
		agent.setCreateTime(new Date());
		agent.setCreateBy(ShiroUtils.getLoginName());
		int i = agentMapper.insertAgent(agent);
	    if(user != null) {
	    	user.setPuserId(agent.getAgentId());
	    	userMapper.updateUser(user);
	    }
	    return i;
	}
	
	/**
     * 修改代理商
     * 
     * @param agent 代理商信息
     * @return 结果
     */
	@Override
	public int updateAgent(Agent agent) {
		if(agent.getCounty() != null) {
			Agent parentAgent = agentMapper.queryParentAgent(agent.getCity());
			agent.setPagentId(parentAgent == null ? null : parentAgent.getAgentId());
		}else {
			agent.setPagentId(null);
		}
		/*if(StringUtils.isNotBlank(agent.getManagerPhone())) {
			// 根据管理者新增用户
			User user = userMapper.selectUserByPhoneNumber(agent.getManagerPhone());
			if(user == null) {
				user = new User();
				user.randomSalt();
				user.setPhonenumber(agent.getManagerPhone());
				user.setLoginName(agent.getManagerPhone());
				user.setUserName(agent.getManagerName());
				user.setPassword(passwordService.encryptPassword(user.getLoginName(), agent.getManagerPhone(), user.getSalt()));
				user.setCreateBy(ShiroUtils.getLoginName());
				user.setUserType(UserConstants.USER_TYPE_AGENT);
				userMapper.insertUser(user);
				agent.setManagerId(user.getUserId().intValue());
			}
		}*/
		agent.setUpdateTime(new Date());
		agent.setUpdateBy(ShiroUtils.getLoginName());
	    return agentMapper.updateAgent(agent);
	}

	/**
     * 删除代理商对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAgentByIds(String ids) {
		String[] idList = Convert.toStrArray(ids);
		Agent agent = null;
		User user = null;
		for (String id : idList) {
			agent = agentMapper.selectAgentById(Integer.valueOf(id));
			if(agent != null && StringUtils.isNotBlank(agent.getManagerPhone())) {
				user = userMapper.selectUserByPhoneNumber(agent.getManagerPhone());
				if(user == null) {
					continue;
				}
				userMapper.deleteUserById(user.getUserId());
				iroleService.deleteRoleByUserId(user.getUserId());
			}
		}
		return agentMapper.deleteAgentByIds(Convert.toStrArray(ids));
	}
	
}
