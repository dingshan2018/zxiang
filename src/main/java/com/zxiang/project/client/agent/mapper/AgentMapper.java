package com.zxiang.project.client.agent.mapper;

import com.zxiang.project.client.agent.domain.Agent;
import java.util.List;	

/**
 * 代理商 数据层
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public interface AgentMapper 
{
	/**
     * 查询代理商信息
     * 
     * @param agentId 代理商ID
     * @return 代理商信息
     */
	public Agent selectAgentById(Integer agentId);
	
	/**
     * 查询代理商列表
     * 
     * @param agent 代理商信息
     * @return 代理商集合
     */
	public List<Agent> selectAgentList(Agent agent);
	
	/**
     * 新增代理商
     * 
     * @param agent 代理商信息
     * @return 结果
     */
	public int insertAgent(Agent agent);
	
	/**
     * 修改代理商
     * 
     * @param agent 代理商信息
     * @return 结果
     */
	public int updateAgent(Agent agent);
	
	/**
     * 删除代理商
     * 
     * @param agentId 代理商ID
     * @return 结果
     */
	public int deleteAgentById(Integer agentId);
	
	/**
     * 批量删除代理商
     * 
     * @param agentIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteAgentByIds(String[] agentIds);
	
}