package com.zxiang.project.business.terminal.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zxiang.common.support.Convert;
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

}
