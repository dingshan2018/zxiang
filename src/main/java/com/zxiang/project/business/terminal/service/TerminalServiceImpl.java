package com.zxiang.project.business.terminal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxiang.common.support.Convert;
import com.zxiang.project.business.terminal.domain.Terminal;
import com.zxiang.project.business.terminal.mapper.TerminalMapper;

/**
 * 终端管理 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
@Service
public class TerminalServiceImpl implements ITerminalService 
{
	@Autowired
	private TerminalMapper terminalMapper;

	/**
     * 查询终端管理信息
     * 
     * @param terminalId 终端管理ID
     * @return 终端管理信息
     */
    @Override
	public Terminal selectTerminalById(Integer terminalId)
	{
	    return terminalMapper.selectTerminalById(terminalId);
	}
	
	/**
     * 查询终端管理列表
     * 
     * @param terminal 终端管理信息
     * @return 终端管理集合
     */
	@Override
	public List<Terminal> selectTerminalList(Terminal terminal)
	{
	    return terminalMapper.selectTerminalList(terminal);
	}
	
    /**
     * 新增终端管理
     * 
     * @param terminal 终端管理信息
     * @return 结果
     */
	@Override
	public int insertTerminal(Terminal terminal)
	{
	    return terminalMapper.insertTerminal(terminal);
	}
	
	/**
     * 修改终端管理
     * 
     * @param terminal 终端管理信息
     * @return 结果
     */
	@Override
	public int updateTerminal(Terminal terminal)
	{
	    return terminalMapper.updateTerminal(terminal);
	}

	/**
     * 删除终端管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTerminalByIds(String ids)
	{
		return terminalMapper.deleteTerminalByIds(Convert.toStrArray(ids));
	}
	
}
