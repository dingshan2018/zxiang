package com.zxiang.project.client.agent.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zxiang.project.client.agent.mapper.AgentMapper;
import com.zxiang.project.client.agent.domain.Agent;
import com.zxiang.project.client.agent.service.IAgentService;
import com.zxiang.common.support.Convert;

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
	public int insertAgent(Agent agent)
	{
	    return agentMapper.insertAgent(agent);
	}
	
	/**
     * 修改代理商
     * 
     * @param agent 代理商信息
     * @return 结果
     */
	@Override
	public int updateAgent(Agent agent)
	{
	    return agentMapper.updateAgent(agent);
	}

	/**
     * 删除代理商对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAgentByIds(String ids)
	{
		return agentMapper.deleteAgentByIds(Convert.toStrArray(ids));
	}
	
}
