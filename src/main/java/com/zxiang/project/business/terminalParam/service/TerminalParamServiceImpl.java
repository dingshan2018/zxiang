package com.zxiang.project.business.terminalParam.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zxiang.common.support.Convert;
import com.zxiang.project.business.server.service.IServerService;
import com.zxiang.project.business.terminal.domain.Terminal;
import com.zxiang.project.business.terminal.mapper.TerminalMapper;
import com.zxiang.project.business.terminalParam.domain.TerminalParam;
import com.zxiang.project.business.terminalParam.domain.TerminalParamDto;
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
		
		//下发命令操作
		for (String terminalParamId : idArray) {
			List<TerminalParamDto> list = new ArrayList<TerminalParamDto>();
			TerminalParam terminalParam = terminalParamMapper.selectTerminalParamById(Integer.parseInt(terminalParamId));
			if(terminalParam != null){
				TerminalParamDto paranDto = new TerminalParamDto();
				paranDto.setId(terminalParam.getId());
				paranDto.setTerminalId(terminalParam.getTerminalId());
				paranDto.setKey(terminalParam.getParamKey());
				paranDto.setValue1(terminalParam.getParamValue1());
				paranDto.setValue2(terminalParam.getParamValue2());
				paranDto.setValue3(terminalParam.getParamValue3());
				paranDto.setValue4(terminalParam.getParamValue4());
				
				list.add(paranDto);
			}
			
			
			Integer terminalId = terminalParam.getTerminalId();
			Terminal terminal = terminalMapper.selectTerminalById(terminalId);
			
			JSONObject reqJson = new JSONObject();
			reqJson.put("termCode",terminal.getTerminalCode());
			reqJson.put("terminalParams",list);
			reqJson.put("command","20");//参数下发命令0x14,转十进制为20
			
			serverService.issuedCommand(terminal,reqJson);
			issuedNum++;
		}
		return issuedNum;
	}
	
}
