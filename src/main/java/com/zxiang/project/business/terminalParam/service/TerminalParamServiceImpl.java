package com.zxiang.project.business.terminalParam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxiang.common.support.Convert;
import com.zxiang.project.business.terminalParam.domain.TerminalParam;
import com.zxiang.project.business.terminalParam.mapper.TerminalParamMapper;

/**
 * 终端参数 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
@Service
public class TerminalParamServiceImpl implements ITerminalParamService 
{
	@Autowired
	private TerminalParamMapper terminalParamMapper;

	/**
     * 查询终端参数信息
     * 
     * @param id 终端参数ID
     * @return 终端参数信息
     */
    @Override
	public TerminalParam selectTerminalParamById(Integer id)
	{
	    return terminalParamMapper.selectTerminalParamById(id);
	}
	
	/**
     * 查询终端参数列表
     * 
     * @param terminalParam 终端参数信息
     * @return 终端参数集合
     */
	@Override
	public List<TerminalParam> selectTerminalParamList(TerminalParam terminalParam)
	{
	    return terminalParamMapper.selectTerminalParamList(terminalParam);
	}
	
    /**
     * 新增终端参数
     * 
     * @param terminalParam 终端参数信息
     * @return 结果
     */
	@Override
	public int insertTerminalParam(TerminalParam terminalParam)
	{
	    return terminalParamMapper.insertTerminalParam(terminalParam);
	}
	
	/**
     * 修改终端参数
     * 
     * @param terminalParam 终端参数信息
     * @return 结果
     */
	@Override
	public int updateTerminalParam(TerminalParam terminalParam)
	{
	    return terminalParamMapper.updateTerminalParam(terminalParam);
	}

	/**
     * 删除终端参数对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTerminalParamByIds(String ids)
	{
		return terminalParamMapper.deleteTerminalParamByIds(Convert.toStrArray(ids));
	}
	
}
