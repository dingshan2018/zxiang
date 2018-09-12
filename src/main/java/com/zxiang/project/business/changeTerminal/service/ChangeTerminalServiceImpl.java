package com.zxiang.project.business.changeTerminal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxiang.common.support.Convert;
import com.zxiang.project.business.changeTerminal.domain.ChangeTerminal;
import com.zxiang.project.business.changeTerminal.mapper.ChangeTerminalMapper;

/**
 * 终端更换记录 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
@Service
public class ChangeTerminalServiceImpl implements IChangeTerminalService 
{
	@Autowired
	private ChangeTerminalMapper changeTerminalMapper;

	/**
     * 查询终端更换记录信息
     * 
     * @param changeTerminalId 终端更换记录ID
     * @return 终端更换记录信息
     */
    @Override
	public ChangeTerminal selectChangeTerminalById(Integer changeTerminalId)
	{
	    return changeTerminalMapper.selectChangeTerminalById(changeTerminalId);
	}
	
	/**
     * 查询终端更换记录列表
     * 
     * @param changeTerminal 终端更换记录信息
     * @return 终端更换记录集合
     */
	@Override
	public List<ChangeTerminal> selectChangeTerminalList(ChangeTerminal changeTerminal)
	{
	    return changeTerminalMapper.selectChangeTerminalList(changeTerminal);
	}
	
    /**
     * 新增终端更换记录
     * 
     * @param changeTerminal 终端更换记录信息
     * @return 结果
     */
	@Override
	public int insertChangeTerminal(ChangeTerminal changeTerminal)
	{
	    return changeTerminalMapper.insertChangeTerminal(changeTerminal);
	}
	
	/**
     * 修改终端更换记录
     * 
     * @param changeTerminal 终端更换记录信息
     * @return 结果
     */
	@Override
	public int updateChangeTerminal(ChangeTerminal changeTerminal)
	{
	    return changeTerminalMapper.updateChangeTerminal(changeTerminal);
	}

	/**
     * 删除终端更换记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteChangeTerminalByIds(String ids)
	{
		return changeTerminalMapper.deleteChangeTerminalByIds(Convert.toStrArray(ids));
	}
	
}
