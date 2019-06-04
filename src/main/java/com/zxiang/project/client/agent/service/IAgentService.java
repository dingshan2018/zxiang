package com.zxiang.project.client.agent.service;

import java.util.List;

import com.zxiang.project.client.agent.domain.Agent;

/**
 * 代理商 服务层
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public interface IAgentService {
	/**
	 * 查询代理商信息
	 * 
	 * @param agentId
	 *            代理商ID
	 * @return 代理商信息
	 */
	public Agent selectAgentById(Integer agentId);

	/**
	 * 查询代理商列表
	 * 
	 * @param agent
	 *            代理商信息
	 * @return 代理商集合
	 */
	public List<Agent> selectAgentList(Agent agent);

	/**
	 * 新增代理商
	 * 
	 * @param agent
	 *            代理商信息
	 * @return 结果
	 */
	public int insertAgent(Agent agent);

	/**
	 * 修改代理商
	 * 
	 * @param agent
	 *            代理商信息
	 * @return 结果
	 */
	public int updateAgent(Agent agent);

	/**
	 * 删除代理商信息
	 * 
	 * @param ids
	 *            需要删除的数据ID
	 * @return 结果
	 */
	public int deleteAgentByIds(String ids);

	/**
	 * 查找代理商下拉框数据
	 */
	public List<Agent> selectDropBoxList();

	public void batchEditParam(Agent agent);

	public int updateAgentParam(Agent agent);

}
