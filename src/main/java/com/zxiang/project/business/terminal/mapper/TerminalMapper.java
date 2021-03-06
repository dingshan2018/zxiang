package com.zxiang.project.business.terminal.mapper;

import com.zxiang.project.business.terminal.domain.Terminal;
import java.util.List;	

/**
 * 终端管理 数据层
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
public interface TerminalMapper 
{
	/**
     * 查询终端管理信息
     * 
     * @param terminalId 终端管理ID
     * @return 终端管理信息
     */
	public Terminal selectTerminalById(Integer terminalId);
	
	/**
     * 查询终端管理列表
     * 
     * @param terminal 终端管理信息
     * @return 终端管理集合
     */
	public List<Terminal> selectTerminalList(Terminal terminal);
	
	/**
     * 新增终端管理
     * 
     * @param terminal 终端管理信息
     * @return 结果
     */
	public int insertTerminal(Terminal terminal);
	
	/**
     * 修改终端管理
     * 
     * @param terminal 终端管理信息
     * @return 结果
     */
	public int updateTerminal(Terminal terminal);
	
	/**
     * 删除终端管理
     * 
     * @param terminalId 终端管理ID
     * @return 结果
     */
	public int deleteTerminalById(Integer terminalId);
	
	/**
     * 批量删除终端管理
     * 
     * @param terminalIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteTerminalByIds(String[] terminalIds);
	
}