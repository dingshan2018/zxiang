package com.zxiang.project.business.terminalstatus.service;

import java.util.List;

import com.zxiang.project.business.terminalstatus.domain.TerminalStatus;

/**
 * 终端状态记录 服务层
 * 
 * @author ZXiang
 * @date 2018-10-14
 */
public interface ITerminalStatusService 
{
	/**
     * 查询终端状态记录信息
     * 
     * @param statusId 终端状态记录ID
     * @return 终端状态记录信息
     */
	public TerminalStatus selectTerminalStatusById(Integer statusId);
	
	/**
     * 查询终端状态记录列表
     * 
     * @param terminalStatus 终端状态记录信息
     * @return 终端状态记录集合
     */
	public List<TerminalStatus> selectTerminalStatusList(TerminalStatus terminalStatus);
	
	/**
     * 新增终端状态记录
     * 
     * @param terminalStatus 终端状态记录信息
     * @return 结果
     */
	public int insertTerminalStatus(TerminalStatus terminalStatus);
	
	/**
     * 修改终端状态记录
     * 
     * @param terminalStatus 终端状态记录信息
     * @return 结果
     */
	public int updateTerminalStatus(TerminalStatus terminalStatus);
		
	/**
     * 删除终端状态记录信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTerminalStatusByIds(String ids);
	
}
