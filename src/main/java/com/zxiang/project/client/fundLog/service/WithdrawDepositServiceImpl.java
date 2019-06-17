package com.zxiang.project.client.fundLog.service;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zxiang.common.support.Convert;
import com.zxiang.common.utils.excel.EXCELObject;
import com.zxiang.common.utils.security.ShiroUtils;
import com.zxiang.framework.aspectj.lang.annotation.DataFilter;
import com.zxiang.project.client.fundLog.domain.WithdrawDeposit;
import com.zxiang.project.client.fundLog.mapper.WithdrawDepositMapper;

/**
 * 提现记录 服务层实现
 * 
 * @author ZXiang
 * @date 2018-12-07
 */
@Service
public class WithdrawDepositServiceImpl implements IWithdrawDepositService 
{
	@Autowired
	private WithdrawDepositMapper withdrawDepositMapper;
	@Autowired
	private IFundLogService fundLogService;

	/**
     * 查询提现记录信息
     * 
     * @param id 提现记录ID
     * @return 提现记录信息
     */
    @Override
	public WithdrawDeposit selectWithdrawDepositById(Integer id)
	{
	    return withdrawDepositMapper.selectWithdrawDepositById(id);
	}
	
	/**
     * 查询提现记录列表
     * 
     * @param withdrawDeposit 提现记录信息
     * @return 提现记录集合
     */
	@Override
	public List<WithdrawDeposit> selectWithdrawDepositList(WithdrawDeposit withdrawDeposit)
	{
	    return withdrawDepositMapper.selectWithdrawDepositList(withdrawDeposit);
	}
	
    /**
     * 新增提现记录
     * 
     * @param withdrawDeposit 提现记录信息
     * @return 结果
     */
	@Override
	public int insertWithdrawDeposit(WithdrawDeposit withdrawDeposit)
	{
	    return withdrawDepositMapper.insertWithdrawDeposit(withdrawDeposit);
	}
	
	/**
     * 修改提现记录
     * 
     * @param withdrawDeposit 提现记录信息
     * @return 结果
     */
	@Override
	@Transactional
	public int updateWithdrawDeposit(WithdrawDeposit withdrawDeposit){
		fundLogService.sureClientWithdraw(withdrawDeposit.getId(),withdrawDeposit.getStatus(),withdrawDeposit.getRemark());
		withdrawDeposit.setUpdateTime(new Date());
		withdrawDeposit.setPayTime(new Date());
		withdrawDeposit.setUpdateBy(ShiroUtils.getLoginName());
	    return withdrawDepositMapper.updateWithdrawDeposit(withdrawDeposit);
	}

	/**
     * 删除提现记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWithdrawDepositByIds(String ids)
	{
		return withdrawDepositMapper.deleteWithdrawDepositByIds(Convert.toStrArray(ids));
	}

	@SuppressWarnings("rawtypes")
	@DataFilter(personAlias="b.user_id")
	@Override
	public void queryExport(Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List erportList = withdrawDepositMapper.queryExport(params);
      	String realPath = request.getSession().getServletContext().getRealPath("/file/temp");
  		EXCELObject s = new EXCELObject();
  		s.seteFilePath(realPath);
  		//表头名称
  		String[] titH = { "客户名称", "提现金额"};
  		//数据库字段名称
  		String[] titN = { "client_name","money"};
  		String[] width= 
  			   {"15","20"};
  		s.setWidth(width);
  		s.setFname("补纸记录"); // sheet栏名称
  		s.setTitle("补纸记录"); // Excel内容标题名称
  		s.setTitH(titH);
  		s.setTitN(titN);
  		s.setDataList(erportList);
  		File exportFile = null;
  		exportFile = s.setData();
  		//Excel文件名称
  		String excelName = "提现记录" + System.currentTimeMillis() + ".xls";
  		s.exportExcel("提现记录", excelName, exportFile, request, response);
	}
	
}
