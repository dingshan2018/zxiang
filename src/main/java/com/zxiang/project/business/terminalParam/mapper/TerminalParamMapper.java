package com.zxiang.project.business.terminalParam.mapper;

import com.zxiang.project.business.terminalParam.domain.TerminalParam;
import java.util.List;	

/**
 * 终端参数 数据层
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
public interface TerminalParamMapper 
{
	/**
     * 查询终端参数信息
     * 
     * @param id 终端参数ID
     * @return 终端参数信息
     */
	public TerminalParam selectTerminalParamById(Integer id);
	
	/**
     * 查询终端参数列表
     * 
     * @param terminalParam 终端参数信息
     * @return 终端参数集合
     */
	public List<TerminalParam> selectTerminalParamList(TerminalParam terminalParam);
	
	/**
     * 新增终端参数
     * 
     * @param terminalParam 终端参数信息
     * @return 结果
     */
	public int insertTerminalParam(TerminalParam terminalParam);
	
	/**
     * 修改终端参数
     * 
     * @param terminalParam 终端参数信息
     * @return 结果
     */
	public int updateTerminalParam(TerminalParam terminalParam);
	
	/**
     * 删除终端参数
     * 
     * @param id 终端参数ID
     * @return 结果
     */
	public int deleteTerminalParamById(Integer id);
	
	/**
     * 批量删除终端参数
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTerminalParamByIds(String[] ids);

	/**
	 * 通过终端ID删除数据
	 * @param ids
	 * @return
	 */
	public int deleteParamByTerminalIds(String[] terminalIds);
	
}