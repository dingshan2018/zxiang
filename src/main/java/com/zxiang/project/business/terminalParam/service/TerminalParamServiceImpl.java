package com.zxiang.project.business.terminalParam.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxiang.common.support.Convert;
import com.zxiang.project.business.server.service.IServerService;
import com.zxiang.project.business.terminal.domain.Terminal;
import com.zxiang.project.business.terminal.mapper.TerminalMapper;
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
	@Autowired
	private IServerService serverService;
	@Autowired
	private TerminalMapper terminalMapper;
	

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

	/**
	 * 终端参数下发
	 * @throws IOException 
	 */
	@Override
	public int paramIssued(String ids) throws IOException {
		int issuedNum = 0;
		String[] idArray = Convert.toStrArray(ids);
		List<TerminalParam> list = new ArrayList<TerminalParam>();
		//下发命令操作
		for (String terminalParamId : idArray) {
			TerminalParam terminalParam = terminalParamMapper.selectTerminalParamById(Integer.parseInt(terminalParamId));
			list.add(terminalParam);
			
			Integer terminalId = terminalParam.getTerminalId();
			Terminal terminal = terminalMapper.selectTerminalById(terminalId);
			
			Map<String, String> paramsMap = new HashMap<String,String>();
			paramsMap.put("termCode",terminal.getTerminalCode());
			paramsMap.put("terminalParams",list.toString());
			
			serverService.issuedCommand(terminal,paramsMap);
			issuedNum++;
		}
		return issuedNum;
	}
	
}
