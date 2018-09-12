package com.zxiang.project.business.terminalTimer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxiang.common.support.Convert;
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
	    return terminalTimerMapper.insertTerminalTimer(terminalTimer);
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
	
}
