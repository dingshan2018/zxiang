package com.zxiang.project.business.terminal.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.zxiang.common.support.Convert;
import com.zxiang.common.utils.excel.EXCELObject;
import com.zxiang.project.business.server.service.IServerService;
import com.zxiang.project.business.terminal.domain.Terminal;
import com.zxiang.project.business.terminal.mapper.TerminalMapper;
import com.zxiang.project.business.terminalParam.mapper.TerminalParamMapper;
import com.zxiang.project.business.terminalTimer.mapper.TerminalTimerMapper;

/**
 * 终端管理 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
@Service
public class TerminalServiceImpl implements ITerminalService 
{
	@Autowired
	private TerminalMapper terminalMapper;
	@Autowired
	private TerminalParamMapper terminalParamMapper;
	@Autowired
	private TerminalTimerMapper terminalTimerMapper;
	@Autowired
	private IServerService serverService;
	
	/**
     * 查询终端管理信息
     * 
     * @param terminalId 终端管理ID
     * @return 终端管理信息
     */
    @Override
	public Terminal selectTerminalById(Integer terminalId)
	{
	    return terminalMapper.selectTerminalById(terminalId);
	}
	
	/**
     * 查询终端管理列表
     * 
     * @param terminal 终端管理信息
     * @return 终端管理集合
     */
	@Override
	public List<Terminal> selectTerminalList(Terminal terminal)
	{
	    return terminalMapper.selectTerminalList(terminal);
	}
	
    /**
     * 新增终端管理
     * 
     * @param terminal 终端管理信息
     * @return 结果
     */
	@Override
	public int insertTerminal(Terminal terminal)
	{
	    return terminalMapper.insertTerminal(terminal);
	}
	
	/**
     * 修改终端管理
     * 
     * @param terminal 终端管理信息
     * @return 结果
     */
	@Override
	public int updateTerminal(Terminal terminal)
	{
	    return terminalMapper.updateTerminal(terminal);
	}

	/**
     * 删除终端管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTerminalByIds(String ids)
	{
		String[] terminalIds = Convert.toStrArray(ids);
		//TODO 删除终端参数表数据
		terminalParamMapper.deleteParamByTerminalIds(terminalIds);
		//删除终端定时表数据
		terminalTimerMapper.deleteTimerByTerminalIds(terminalIds);
		
		return terminalMapper.deleteTerminalByIds(terminalIds);
	}

	@Override
	public List<Terminal> selectDropBoxList() {
		return terminalMapper.selectDropBoxList();
	}

	@Override
	public List<Terminal> getDropBoxValidlList() {
		return terminalMapper.getDropBoxValidlList();
	}
	
	@Override
	public String checkTerminalCodeUnique(String terminalCode) {
		int count = terminalMapper.checkTerminalCodeUnique(terminalCode);
        if (count > 0)
        {
            return "1";
        }
        return "0";
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public int saveBatchImport(List<Object> sheetList,String operatorUser) {
		int saveCount = 0;
		//用于存放本批次已插入的数据进行保存
		List<String> hasCodeList = new ArrayList<>(); 
				
		for (Object object : sheetList) {
			List<Object> terminalList = (List<Object>) object;
			if(terminalList.size() > 0 && null != terminalList.get(0)){
				String terminalCode = (String) terminalList.get(0);
				if(org.apache.commons.lang.StringUtils.isNotBlank(terminalCode)){
					//终端编号去重,如果本Excel已经导入过这个设备编号就不再保存数据
					if(hasCodeList.contains(terminalCode)){
						continue;
					}
					
					int saveNum = checkAndSaveTerminalCode(terminalCode,operatorUser);
					saveCount = saveCount +  saveNum;
					if(saveNum == 1){
						hasCodeList.add(terminalCode);
					}
				}
			}
		}
		return saveCount;
	}
	
	public int checkAndSaveTerminalCode(String terminalCode,String operatorUser){
		String codeExist = checkTerminalCodeUnique(terminalCode);
		//终端编号不存在则保存,返回插入成功数量
		if("0".equals(codeExist)){
			Terminal terminal = new Terminal();
			terminal.setTerminalCode(terminalCode);
			terminal.setStatus("1");//生效
			terminal.setCreateBy(operatorUser);
			terminal.setCreateTime(new Date());
			return terminalMapper.insertTerminal(terminal);
		}
		//终端编号存在则不保存，直接返回0
		return 0;
	}

	@Override
	public int deleteTerminals(String ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void queryExport(HashMap<String, String> params, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List erportList = terminalMapper.queryExport(params);
      	String realPath = request.getSession().getServletContext().getRealPath("/file/temp");
  		EXCELObject s = new EXCELObject();
  		s.seteFilePath(realPath);
  		//表头名称
  		String[] titH = { "ID", "终端编号", "场所名称","设备资产编号","卡号",
  				"终端信号强度", "心跳时间","登录时间", "在线状态","终端状态",
  				"终端版本", "终端音量","板卡编号", "经度", "纬度"};
  		//SQL方法查询出的字段名称
  		String[] titN = { "terminal_id","terminal_code","placeName","deviceSn","iccId",
  				"rssi","last_heart_time","last_login_time","onlineStatusName","statusName",
  				"version","volumn","sn_code","lon","lat"};
  		String[] width= 
  			   {"15","20","20","20","20",
  				"15","20","20","15","15",
  				"15","15","20","20","20"};
  		s.setWidth(width);
  		s.setFname("终端管理"); // sheet栏名称
  		s.setTitle("终端管理"); // Excel内容标题名称
  		s.setTitH(titH);
  		s.setTitN(titN);
  		s.setDataList(erportList);
  		File exportFile = null;
  		exportFile = s.setData();
  		//Excel文件名称
  		String excelName = "终端管理" + System.currentTimeMillis() + ".xls";
  		s.exportExcel("终端管理", excelName, exportFile, request, response);
	}

	@Override
	public int reportLog(Integer terminalId) {
		int number = 0;
		Terminal terminal = terminalMapper.selectTerminalById(terminalId);
		if(terminal != null ){
			JSONObject reqJson = new JSONObject();
			reqJson.put("termCode",terminal.getTerminalCode());
			// 接口参数 进行封装
			reqJson.put("command","35");//参数下发命令0x23,转十进制为35
			try {
				serverService.issuedCommand(terminal,reqJson);
				number++;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		number++;
		return number;
	}

}
