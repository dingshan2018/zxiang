package com.zxiang.project.business.changeTerminal.service;

import com.zxiang.project.business.changeTerminal.domain.ChangeTerminal;
import java.util.List;

/**
 * 终端更换记录 服务层
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
public interface IChangeTerminalService 
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
     * 删除终端更换记录信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteChangeTerminalByIds(String ids);
	
}
