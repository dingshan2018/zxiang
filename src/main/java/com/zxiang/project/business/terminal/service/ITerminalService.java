package com.zxiang.project.business.terminal.service;

import com.zxiang.project.business.terminal.domain.Terminal;
import java.util.List;

/**
 * 终端管理 服务层
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
public interface ITerminalService 
{
	/**
     * 查询终端管理信息
     * 
     * @param terminalId 终端管理ID
     * @return 终端管理信息
     */
	public Terminal selectTerminalById(Integer terminalId);
	
	/**
     * 查询终端管理列表
     * 
     * @param terminal 终端管理信息
     * @return 终端管理集合
     */
	public List<Terminal> selectTerminalList(Terminal terminal);
	
	/**
     * 新增终端管理
     * 
     * @param terminal 终端管理信息
     * @return 结果
     */
	public int insertTerminal(Terminal terminal);
	
	/**
     * 修改终端管理
     * 
     * @param terminal 终端管理信息
     * @return 结果
     */
	public int updateTerminal(Terminal terminal);
		
	/**
     * 删除终端管理信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTerminalByIds(String ids);

	/**
	 * 查询终端设备下拉列表
	 */
	public List<Terminal> selectDropBoxList();
	
	/**
	 * 查询未被设备绑定的终端
	 */
	public List<Terminal> getDropBoxValidlList();
	
	/**
     * 校验终端编号
     */
	public String checkTerminalCodeUnique(String terminalCode);

	/**
	 * 保存Excel终端数据
	 * @param sheetList
	 * @return
	 */
	public int saveBatchImport(List<Object> sheetList,String operatorUser);

	
}
