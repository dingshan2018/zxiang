package com.zxiang.project.business.terminalalarm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxiang.common.support.Convert;
import com.zxiang.project.business.terminalalarm.domain.TerminalAlarm;
import com.zxiang.project.business.terminalalarm.mapper.TerminalAlarmMapper;

/**
 * 终端报警 服务层实现
 * 
 * @author ZXiang
 * @date 2018-10-14
 */
@Service
public class TerminalAlarmServiceImpl implements ITerminalAlarmService 
{
	@Autowired
	private TerminalAlarmMapper terminalAlarmMapper;

	/**
     * 查询终端报警信息
     * 
     * @param alarmId 终端报警ID
     * @return 终端报警信息
     */
    @Override
	public TerminalAlarm selectTerminalAlarmById(Integer alarmId)
	{
	    return terminalAlarmMapper.selectTerminalAlarmById(alarmId);
	}
	
	/**
     * 查询终端报警列表
     * 
     * @param terminalAlarm 终端报警信息
     * @return 终端报警集合
     */
	@Override
	public List<TerminalAlarm> selectTerminalAlarmList(TerminalAlarm terminalAlarm)
	{
	    return terminalAlarmMapper.selectTerminalAlarmList(terminalAlarm);
	}
	
    /**
     * 新增终端报警
     * 
     * @param terminalAlarm 终端报警信息
     * @return 结果
     */
	@Override
	public int insertTerminalAlarm(TerminalAlarm terminalAlarm)
	{
	    return terminalAlarmMapper.insertTerminalAlarm(terminalAlarm);
	}
	
	/**
     * 修改终端报警
     * 
     * @param terminalAlarm 终端报警信息
     * @return 结果
     */
	@Override
	public int updateTerminalAlarm(TerminalAlarm terminalAlarm)
	{
	    return terminalAlarmMapper.updateTerminalAlarm(terminalAlarm);
	}

	/**
     * 删除终端报警对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTerminalAlarmByIds(String ids)
	{
		return terminalAlarmMapper.deleteTerminalAlarmByIds(Convert.toStrArray(ids));
	}
	
}
