package com.zxiang.project.business.terminalstatus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxiang.common.support.Convert;
import com.zxiang.project.business.terminalstatus.domain.TerminalStatus;
import com.zxiang.project.business.terminalstatus.mapper.TerminalStatusMapper;

/**
 * 终端状态记录 服务层实现
 * 
 * @author ZXiang
 * @date 2018-10-14
 */
@Service
public class TerminalStatusServiceImpl implements ITerminalStatusService 
{
	@Autowired
	private TerminalStatusMapper terminalStatusMapper;

	/**
     * 查询终端状态记录信息
     * 
     * @param statusId 终端状态记录ID
     * @return 终端状态记录信息
     */
    @Override
	public TerminalStatus selectTerminalStatusById(Integer statusId)
	{
	    return terminalStatusMapper.selectTerminalStatusById(statusId);
	}
	
	/**
     * 查询终端状态记录列表
     * 
     * @param terminalStatus 终端状态记录信息
     * @return 终端状态记录集合
     */
	@Override
	public List<TerminalStatus> selectTerminalStatusList(TerminalStatus terminalStatus)
	{
	    return terminalStatusMapper.selectTerminalStatusList(terminalStatus);
	}
	
    /**
     * 新增终端状态记录
     * 
     * @param terminalStatus 终端状态记录信息
     * @return 结果
     */
	@Override
	public int insertTerminalStatus(TerminalStatus terminalStatus)
	{
	    return terminalStatusMapper.insertTerminalStatus(terminalStatus);
	}
	
	/**
     * 修改终端状态记录
     * 
     * @param terminalStatus 终端状态记录信息
     * @return 结果
     */
	@Override
	public int updateTerminalStatus(TerminalStatus terminalStatus)
	{
	    return terminalStatusMapper.updateTerminalStatus(terminalStatus);
	}

	/**
     * 删除终端状态记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTerminalStatusByIds(String ids)
	{
		return terminalStatusMapper.deleteTerminalStatusByIds(Convert.toStrArray(ids));
	}
	
}
