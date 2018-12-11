package com.zxiang.project.business.terminalTimer.mapper;

import com.zxiang.project.business.terminalTimer.domain.TerminalTimer;
import java.util.List;	

/**
 * 定时设置 数据层
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
public interface TerminalTimerMapper 
{
	/**
     * 查询定时设置信息
     * 
     * @param teminalTimerId 定时设置ID
     * @return 定时设置信息
     */
	public TerminalTimer selectTerminalTimerById(Integer teminalTimerId);
	
	/**
     * 查询定时设置列表
     * 
     * @param terminalTimer 定时设置信息
     * @return 定时设置集合
     */
	public List<TerminalTimer> selectTerminalTimerList(TerminalTimer terminalTimer);
	
	/**
     * 新增定时设置
     * 
     * @param terminalTimer 定时设置信息
     * @return 结果
     */
	public int insertTerminalTimer(TerminalTimer terminalTimer);
	
	/**
     * 修改定时设置
     * 
     * @param terminalTimer 定时设置信息
     * @return 结果
     */
	public int updateTerminalTimer(TerminalTimer terminalTimer);
	
	/**
     * 删除定时设置
     * 
     * @param teminalTimerId 定时设置ID
     * @return 结果
     */
	public int deleteTerminalTimerById(Integer teminalTimerId);
	
	/**
     * 批量删除定时设置
     * 
     * @param teminalTimerIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteTerminalTimerByIds(String[] teminalTimerIds);

	/**
	 *  通过终端ID查找定时设置数据
	 * @param teminalId
	 * @return
	 */
	public TerminalTimer selectByTerminalId(Integer teminalId);

	/**
	 * 通过终端ID删除数据
	 * @param ids
	 * @return
	 */
	public int deleteTimerByTerminalIds(String[] terminalIds);
	
}