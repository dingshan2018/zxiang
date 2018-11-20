package com.zxiang.project.business.server.mapper;

import java.util.List;

import com.zxiang.project.business.server.domain.Server;	

/**
 * 接入服务器 数据层
 * 
 * @author ZXiang
 * @date 2018-11-20
 */
public interface ServerMapper 
{
	/**
     * 查询接入服务器信息
     * 
     * @param serverId 接入服务器ID
     * @return 接入服务器信息
     */
	public Server selectServerById(Integer serverId);
	
	/**
     * 查询接入服务器列表
     * 
     * @param server 接入服务器信息
     * @return 接入服务器集合
     */
	public List<Server> selectServerList(Server server);
	
	/**
     * 新增接入服务器
     * 
     * @param server 接入服务器信息
     * @return 结果
     */
	public int insertServer(Server server);
	
	/**
     * 修改接入服务器
     * 
     * @param server 接入服务器信息
     * @return 结果
     */
	public int updateServer(Server server);
	
	/**
     * 删除接入服务器
     * 
     * @param serverId 接入服务器ID
     * @return 结果
     */
	public int deleteServerById(Integer serverId);
	
	/**
     * 批量删除接入服务器
     * 
     * @param serverIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteServerByIds(String[] serverIds);
	
}