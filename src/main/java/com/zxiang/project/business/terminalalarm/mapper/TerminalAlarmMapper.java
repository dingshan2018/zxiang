package com.zxiang.project.business.terminalalarm.mapper;

import java.util.List;

import com.zxiang.project.business.terminalalarm.domain.TerminalAlarm;	

/**
 * 终端报警 数据层
 * 
 * @author ZXiang
 * @date 2018-10-14
 */
public interface TerminalAlarmMapper 
{
	/**
     * 查询终端报警信息
     * 
     * @param alarmId 终端报警ID
     * @return 终端报警信息
     */
	public TerminalAlarm selectTerminalAlarmById(Integer alarmId);
	
	/**
     * 查询终端报警列表
     * 
     * @param terminalAlarm 终端报警信息
     * @return 终端报警集合
     */
	public List<TerminalAlarm> selectTerminalAlarmList(TerminalAlarm terminalAlarm);
	
	/**
     * 新增终端报警
     * 
     * @param terminalAlarm 终端报警信息
     * @return 结果
     */
	public int insertTerminalAlarm(TerminalAlarm terminalAlarm);
	
	/**
     * 修改终端报警
     * 
     * @param terminalAlarm 终端报警信息
     * @return 结果
     */
	public int updateTerminalAlarm(TerminalAlarm terminalAlarm);
	
	/**
     * 删除终端报警
     * 
     * @param alarmId 终端报警ID
     * @return 结果
     */
	public int deleteTerminalAlarmById(Integer alarmId);
	
	/**
     * 批量删除终端报警
     * 
     * @param alarmIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteTerminalAlarmByIds(String[] alarmIds);
	
}