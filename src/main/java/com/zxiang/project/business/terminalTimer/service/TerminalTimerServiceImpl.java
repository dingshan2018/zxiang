package com.zxiang.project.business.terminalTimer.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zxiang.common.support.Convert;
import com.zxiang.project.business.server.service.IServerService;
import com.zxiang.project.business.terminal.domain.Terminal;
import com.zxiang.project.business.terminal.mapper.TerminalMapper;
import com.zxiang.project.business.terminalTimer.domain.TerminalTimer;
import com.zxiang.project.business.terminalTimer.mapper.TerminalTimerMapper;

/**
 * 定时设置 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
@Service
public class TerminalTimerServiceImpl implements ITerminalTimerService 
{
	@Autowired
	private TerminalTimerMapper terminalTimerMapper;
	@Autowired
	private IServerService serverService;
	@Autowired
	private TerminalMapper terminalMapper;

	/**
     * 查询定时设置信息
     * 
     * @param teminalTimerId 定时设置ID
     * @return 定时设置信息
     */
    @Override
	public TerminalTimer selectTerminalTimerById(Integer teminalTimerId)
	{
	    return terminalTimerMapper.selectTerminalTimerById(teminalTimerId);
	}
	
	/**
     * 查询定时设置列表
     * 
     * @param terminalTimer 定时设置信息
     * @return 定时设置集合
     */
	@Override
	public List<TerminalTimer> selectTerminalTimerList(TerminalTimer terminalTimer)
	{
	    return terminalTimerMapper.selectTerminalTimerList(terminalTimer);
	}
	
    /**
     * 新增定时设置
     * 
     * @param terminalTimer 定时设置信息
     * @return 结果
     */
	@Override
	public int insertTerminalTimer(TerminalTimer terminalTimer)
	{
		int insert = terminalTimerMapper.insertTerminalTimer(terminalTimer);
		try {
			Terminal terminal = terminalMapper.selectTerminalById(terminalTimer.getTerminalId());
			timerIssued(terminal,terminalTimer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    return insert;
	}
	
	/**
     * 修改定时设置
     * 
     * @param terminalTimer 定时设置信息
     * @return 结果
     */
	@Override
	public int updateTerminalTimer(TerminalTimer terminalTimer)
	{
	    return terminalTimerMapper.updateTerminalTimer(terminalTimer);
	}

	/**
	 * 修改定时设置保存和下发
	 * @throws IOException 
	 */
	@Override
	public int updateAndIssued(TerminalTimer terminalTimer){
		int update = terminalTimerMapper.updateTerminalTimer(terminalTimer);
		
		try {
			Terminal terminal = terminalMapper.selectTerminalById(terminalTimer.getTerminalId());
			timerIssued(terminal,terminalTimer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return update;
	}
	
	/**
	 * 终端定时设置下发命令
	 * 参数封装方法
	 * @param terminal
	 * @param terminalTimer
	 * @throws IOException
	 */
	private void timerIssued(Terminal terminal,TerminalTimer terminalTimer) throws IOException{
		
		List<TerminalTimer> list = new ArrayList<TerminalTimer>();
		list.add(terminalTimer);
		
		JSONObject reqJson = new JSONObject();
		reqJson.put("termCode",terminal.getTerminalCode());
		reqJson.put("terminalTimers",list);
		reqJson.put("command","21");//参数下发命令0x15,转十进制为21
		
		serverService.issuedCommand(terminal,reqJson);
	}
	
	/**
     * 删除定时设置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTerminalTimerByIds(String ids)
	{
		return terminalTimerMapper.deleteTerminalTimerByIds(Convert.toStrArray(ids));
	}

	/**
	 * 通过终端ID查找定时设置数据
	 */
	@Override
	public TerminalTimer selectByTerminalId(Integer teminalId) {
		return terminalTimerMapper.selectByTerminalId(teminalId);
	}

	
}
