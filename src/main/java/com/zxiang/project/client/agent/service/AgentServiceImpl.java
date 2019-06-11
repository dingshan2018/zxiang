package com.zxiang.project.client.agent.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxiang.common.constant.UserConstants;
import com.zxiang.common.exception.RRException;
import com.zxiang.common.support.Convert;
import com.zxiang.common.utils.PinyinUtil;
import com.zxiang.common.utils.security.ShiroUtils;
import com.zxiang.framework.shiro.service.PasswordService;
import com.zxiang.project.client.agent.domain.Agent;
import com.zxiang.project.client.agent.mapper.AgentMapper;
import com.zxiang.project.settle.coefficient.domain.Coefficient;
import com.zxiang.project.settle.coefficient.service.ICoefficientService;
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
public class AgentServiceImpl implements IAgentService {
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
	
	@Autowired
	private ICoefficientService coefficientService;

	/**
	 * 查询代理商信息
	 * 
	 * @param agentId
	 *            代理商ID
	 * @return 代理商信息
	 */
	@Override
	public Agent selectAgentById(Integer agentId) {
		return agentMapper.selectAgentById(agentId);
	}

	/**
	 * 查询代理商列表
	 * 
	 * @param agent
	 *            代理商信息
	 * @return 代理商集合
	 */
	@Override
	public List<Agent> selectAgentList(Agent agent) {
		return agentMapper.selectAgentList(agent);
	}

	/**
	 * 新增代理商
	 * 
	 * @param agent
	 *            代理商信息
	 * @return 结果
	 */
	@Override
	public int insertAgent(Agent agent) {
		User user = null;
		if (agent.getCounty() != null) {
			// 设置父代理商
			Agent parentAgent = agentMapper.queryParentAgent(agent.getCity());
			agent.setPagentId(parentAgent == null ? null : parentAgent.getAgentId());
		}
		if (StringUtils.isNotBlank(agent.getManagerPhone())) {
			// 根据管理者新增用户
			user = userMapper.selectUserByPhoneNumber(agent.getManagerPhone());
			if (user != null) {
				throw new RRException(String.format("该手机号[%s]对应的用户已存在", agent.getManagerPhone()));
			}
			user = new User();
			user.randomSalt();
			user.setPhonenumber(agent.getManagerPhone());
			user.setLoginName(agent.getManagerPhone());
			user.setUserName(agent.getManagerName());
			String password = PinyinUtil.getPinYinHeadChar(agent.getManagerName()) + agent.getManagerPhone();
			user.setPassword(passwordService.encryptPassword(user.getLoginName(), password, user.getSalt()));
			user.setCreateBy(ShiroUtils.getLoginName());
			user.setUserType(UserConstants.USER_TYPE_AGENT);

			Dept dept = new Dept();
			dept.setDeptName(UserConstants.DEPT_NAME);
			List<Dept> depts = deptMapper.selectDeptList(dept);
			if (depts != null && depts.size() > 0) {
				user.setDeptId(depts.get(0).getDeptId());
			}
			userMapper.insertUser(user);
			agent.setManagerId(user.getUserId().intValue());
			// 设置默认角色
			iroleService.setDefaultRole(user, UserConstants.defaultRoleKey.get(UserConstants.USER_TYPE_AGENT));
		}
		agent.setCreateTime(new Date());
		agent.setCreateBy(ShiroUtils.getLoginName());
		Coefficient coefficient = new Coefficient();
		// 设置默认参数
		if (agent.getLevel() == 1) {
			coefficient = coefficientService.selectCoefficientByType("2");
			/*agent.setAdRate(0f);
			agent.setAdCarouselRate(0f);
			agent.setScanRate(0.05f);
			agent.setSubsidyRate(0f);
			agent.setServeRate(0.005f);*/
		}
		if (agent.getLevel() == 2) {
			coefficient = coefficientService.selectCoefficientByType("3");
			/*agent.setAdRate(0f);
			agent.setAdCarouselRate(0f);
			agent.setScanRate(0.02f);
			agent.setSubsidyRate(0f);
			agent.setServeRate(0.002f);*/
		}
		agent.setAdRate(coefficient.getAdRate());
		agent.setAdCarouselRate(coefficient.getAdCarouselRate());
		agent.setScanRate(coefficient.getScanRate());
		agent.setSubsidyRate(coefficient.getSubsidyRate());
		agent.setServeRate(coefficient.getServeRate());
		
		agent.setPromDirectRate(coefficient.getPromDirectRate());
		agent.setPromIndirectRate(coefficient.getPromIndirectRate());
		agent.setPromPaperRate(coefficient.getPromPaperRate());
		agent.setPromotionRate(coefficient.getPromotionRate());
		agent.setDirectAgentRate(coefficient.getDirectAgentRate());
		int i = agentMapper.insertAgent(agent);
		if (user != null) {
			user.setPuserId(agent.getAgentId());
			userMapper.updateUser(user);
		}
		return i;
	}

	/**
	 * 修改代理商
	 * 
	 * @param agent
	 *            代理商信息
	 * @return 结果
	 */
	@Override
	public int updateAgent(Agent agent) {
		if (agent.getCounty() != null) {
			Agent parentAgent = agentMapper.queryParentAgent(agent.getCity());
			agent.setPagentId(parentAgent == null ? null : parentAgent.getAgentId());
		} else {
			agent.setPagentId(null);
		}
		/*
		 * if(StringUtils.isNotBlank(agent.getManagerPhone())) { // 根据管理者新增用户 User user
		 * = userMapper.selectUserByPhoneNumber(agent.getManagerPhone()); if(user ==
		 * null) { user = new User(); user.randomSalt();
		 * user.setPhonenumber(agent.getManagerPhone());
		 * user.setLoginName(agent.getManagerPhone());
		 * user.setUserName(agent.getManagerName());
		 * user.setPassword(passwordService.encryptPassword(user.getLoginName(),
		 * agent.getManagerPhone(), user.getSalt()));
		 * user.setCreateBy(ShiroUtils.getLoginName());
		 * user.setUserType(UserConstants.USER_TYPE_AGENT); userMapper.insertUser(user);
		 * agent.setManagerId(user.getUserId().intValue()); } }
		 */
		agent.setUpdateTime(new Date());
		agent.setUpdateBy(ShiroUtils.getLoginName());
		return agentMapper.updateAgent(agent);
	}

	/**
	 * 删除代理商对象
	 * 
	 * @param ids
	 *            需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteAgentByIds(String ids) {
		String[] idList = Convert.toStrArray(ids);
		Agent agent = null;
		User user = null;
		for (String id : idList) {
			agent = agentMapper.selectAgentById(Integer.valueOf(id));
			if (agent != null && StringUtils.isNotBlank(agent.getManagerPhone())) {
				user = userMapper.selectUserByPhoneNumber(agent.getManagerPhone());
				if (user == null) {
					continue;
				}
				userMapper.deleteUserById(user.getUserId());
				iroleService.deleteRoleByUserId(user.getUserId());
			}
		}
		return agentMapper.deleteAgentByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<Agent> selectDropBoxList() {
		return agentMapper.selectDropBoxList();
	}

	@Override
	public void batchEditParam(Agent agent) {
		String[] idList = Convert.toStrArray(agent.getIds());
		for (String id : idList) {
			agent.setAgentId(Integer.valueOf(id));
			agentMapper.updateAgentParam(agent);
		}
	}

	@Override
	public int updateAgentParam(Agent agent) {
		return agentMapper.updateAgentParam(agent);
	}

}
