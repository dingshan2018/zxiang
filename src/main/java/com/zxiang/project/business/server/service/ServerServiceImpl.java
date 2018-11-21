package com.zxiang.project.business.server.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zxiang.common.support.Convert;
import com.zxiang.project.advertise.utils.Tools;
import com.zxiang.project.business.server.domain.Server;
import com.zxiang.project.business.server.mapper.ServerMapper;
import com.zxiang.project.business.terminal.domain.Terminal;

/**
 * 接入服务器 服务层实现
 * 
 * @author ZXiang
 * @date 2018-11-20
 */
@Service
public class ServerServiceImpl implements IServerService 
{
	@Autowired
	private ServerMapper serverMapper;

	/**
     * 查询接入服务器信息
     * 
     * @param serverId 接入服务器ID
     * @return 接入服务器信息
     */
    @Override
	public Server selectServerById(Integer serverId)
	{
	    return serverMapper.selectServerById(serverId);
	}
	
	/**
     * 查询接入服务器列表
     * 
     * @param server 接入服务器信息
     * @return 接入服务器集合
     */
	@Override
	public List<Server> selectServerList(Server server)
	{
	    return serverMapper.selectServerList(server);
	}
	
    /**
     * 新增接入服务器
     * 
     * @param server 接入服务器信息
     * @return 结果
     */
	@Override
	public int insertServer(Server server)
	{
	    return serverMapper.insertServer(server);
	}
	
	/**
     * 修改接入服务器
     * 
     * @param server 接入服务器信息
     * @return 结果
     */
	@Override
	public int updateServer(Server server)
	{
	    return serverMapper.updateServer(server);
	}

	/**
     * 删除接入服务器对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteServerByIds(String ids)
	{
		return serverMapper.deleteServerByIds(Convert.toStrArray(ids));
	}

	/**
	 * 终端参数下发命令接口
	 * @throws IOException 
	 */
	@Override
	public String issuedCommand(Terminal terminal,JSONObject reqJson) throws IOException {
		String result = null;
		Server server = new Server();
		server.setAccessIp(terminal.getAccSysIp());
		server.setAccessPort(terminal.getAccSysPort());
		List<Server> servers = serverMapper.selectServerList(server);
		if(servers!=null && servers.size() > 0) {
			server = servers.get(0);
			
			String url = "http://" + server.getHttpIp() + ":" + server.getHttpPort()+ "/tm/terminal/sendCommand";
			//String requst = Tools.paramsToString(paramsMap);
			//需要使用json格式的参数
			String requst = JSONObject.toJSONString(reqJson);
			result = Tools.doPost(url, requst);
		}
		return result;
	}
	
}
