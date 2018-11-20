package com.zxiang.project.business.server.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zxiang.project.business.server.domain.Server;
import com.zxiang.project.business.terminal.domain.Terminal;

/**
 * 接入服务器 服务层
 * 
 * @author ZXiang
 * @date 2018-11-20
 */
public interface IServerService 
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
     * 删除接入服务器信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteServerByIds(String ids);
	
	/**
	 * 下发命令接口
	 * @param terminal
	 * @param date
	 * @return
	 * @throws IOException 
	 */
	public String issuedCommand(Terminal terminal,Map<String, String> paramsMap) throws IOException;
	
}
