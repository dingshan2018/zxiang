package com.zxiang.project.business.changeTerminal.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxiang.common.support.Convert;
import com.zxiang.common.utils.excel.EXCELObject;
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

	@Override
	public void queryExport(HashMap<String, String> params, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List erportList = changeTerminalMapper.queryExport(params);
      	String realPath = request.getSession().getServletContext().getRealPath("/file/temp");
  		EXCELObject s = new EXCELObject();
  		s.seteFilePath(realPath);
  		//表头名称
  		String[] titH = { "ID", "旧终端", "新终端","终端音量","换板人",
  				"创建者", "创建时间"};
  		//数据库字段名称
  		String[] titN = { "change_terminal_id","oldTerminalCode","newTerminalCode","old_volumn","changeUserName",
  				"create_by", "create_time"};
  		String[] width= 
  			   {"15","20","20","20","20",
  				"20","20"};
  		s.setWidth(width);
  		s.setFname("终端换板记录"); // sheet栏名称
  		s.setTitle("终端换板记录"); // Excel内容标题名称
  		s.setTitH(titH);
  		s.setTitN(titN);
  		s.setDataList(erportList);
  		File exportFile = null;
  		exportFile = s.setData();
  		//Excel文件名称
  		String excelName = "终端换板记录" + System.currentTimeMillis() + ".xls";
  		s.exportExcel("终端换板记录", excelName, exportFile, request, response);
	}
	
}
