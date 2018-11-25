package com.zxiang.project.business.changeTerminal.mapper;

import java.util.HashMap;
import java.util.List;

import com.zxiang.project.business.changeTerminal.domain.ChangeTerminal;	

/**
 * 终端更换记录 数据层
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
public interface ChangeTerminalMapper 
{
	/**
     * 查询终端更换记录信息
     * 
     * @param changeTerminalId 终端更换记录ID
     * @return 终端更换记录信息
     */
	public ChangeTerminal selectChangeTerminalById(Integer changeTerminalId);
	
	/**
     * 查询终端更换记录列表
     * 
     * @param changeTerminal 终端更换记录信息
     * @return 终端更换记录集合
     */
	public List<ChangeTerminal> selectChangeTerminalList(ChangeTerminal changeTerminal);
	
	/**
     * 新增终端更换记录
     * 
     * @param changeTerminal 终端更换记录信息
     * @return 结果
     */
	public int insertChangeTerminal(ChangeTerminal changeTerminal);
	
	/**
     * 修改终端更换记录
     * 
     * @param changeTerminal 终端更换记录信息
     * @return 结果
     */
	public int updateChangeTerminal(ChangeTerminal changeTerminal);
	
	/**
     * 删除终端更换记录
     * 
     * @param changeTerminalId 终端更换记录ID
     * @return 结果
     */
	public int deleteChangeTerminalById(Integer changeTerminalId);
	
	/**
     * 批量删除终端更换记录
     * 
     * @param changeTerminalIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteChangeTerminalByIds(String[] changeTerminalIds);

	public List queryExport(HashMap<String, String> params);
	
}