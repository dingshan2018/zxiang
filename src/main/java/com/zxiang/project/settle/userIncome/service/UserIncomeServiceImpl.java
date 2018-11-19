package com.zxiang.project.settle.userIncome.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zxiang.project.settle.userIncome.mapper.UserIncomeMapper;
import com.zxiang.project.settle.userIncome.domain.UserIncome;
import com.zxiang.project.settle.userIncome.service.IUserIncomeService;
import com.zxiang.common.support.Convert;
import com.zxiang.common.utils.DateUtils;
import com.zxiang.common.utils.excel.EXCELObject;
import com.zxiang.framework.aspectj.lang.annotation.DataFilter;

/**
 * 客户收入日统计 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Service
public class UserIncomeServiceImpl implements IUserIncomeService 
{
	@Autowired
	private UserIncomeMapper userIncomeMapper;

	/**
     * 查询客户收入日统计信息
     * 
     * @param incomeId 客户收入日统计ID
     * @return 客户收入日统计信息
     */
    @Override
	public UserIncome selectUserIncomeById(Integer incomeId)
	{
	    return userIncomeMapper.selectUserIncomeById(incomeId);
	}
	
	/**
     * 查询客户收入日统计列表
     * 
     * @param userIncome 客户收入日统计信息
     * @return 客户收入日统计集合
     */
	@Override
	public List<UserIncome> selectUserIncomeList(UserIncome userIncome)
	{
	    return userIncomeMapper.selectUserIncomeList(userIncome);
	}
	
    /**
     * 新增客户收入日统计
     * 
     * @param userIncome 客户收入日统计信息
     * @return 结果
     */
	@Override
	public int insertUserIncome(UserIncome userIncome)
	{
	    return userIncomeMapper.insertUserIncome(userIncome);
	}
	
	/**
     * 修改客户收入日统计
     * 
     * @param userIncome 客户收入日统计信息
     * @return 结果
     */
	@Override
	public int updateUserIncome(UserIncome userIncome)
	{
	    return userIncomeMapper.updateUserIncome(userIncome);
	}

	/**
     * 删除客户收入日统计对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteUserIncomeByIds(String ids)
	{
		return userIncomeMapper.deleteUserIncomeByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<UserIncome> selectUserIncome(UserIncome userIncome) {
		// TODO Auto-generated method stub
		return userIncomeMapper.selectUserIncome(userIncome);
	}

	@Override
	public HashMap<String, Object> selectzxsellerlist(String sellerId) {
		List<HashMap<String, Object>> list = userIncomeMapper.selectzxsellerlist(sellerId);
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<HashMap<String, Object>> selectzxagentlist(HashMap<String, Object> map) {
		return userIncomeMapper.selectzxagentlist(map);
	}
	
	@Override
	public List<HashMap<String, Object>> selectzxagent(HashMap<String, Object> map) {
		return userIncomeMapper.selectzxagent(map);
	}

	@Override
	public List<HashMap<String, Object>> selectzxjoinlist(HashMap<String, Object> map) {
		return userIncomeMapper.selectzxjoinlist(map);
	}

	@Override
	public List<HashMap<String, Object>> selectzxrepairlist(HashMap<String, Object> map) {
		return userIncomeMapper.selectzxrepairlist(map);
	}

	@Override
	public List<HashMap<String, Object>> selectzxrepairarealist(HashMap<String, Object> map) {
		return userIncomeMapper.selectzxrepairarealist(map);
	}

	@Override
	public HashMap<String, Object> selectuserbypuserId(String sellerId) {
		List<HashMap<String, Object>> list = userIncomeMapper.selectuserbypuserId(sellerId);
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	
	@Override
	@DataFilter(personAlias="coperator_id")
	public void queryExport(HashMap<String, String> params, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List userIncomeList = userIncomeMapper.queryExport(params);
		String realPath = request.getSession().getServletContext().getRealPath("/file/temp");
		EXCELObject s = new EXCELObject();
		s.seteFilePath(realPath);
		String[] titH = {"日期", "姓名", "合作类型", "视频广告基数","轮播广告基数","推广二维码广告基数", "推广广告基数","直推机子数量","间推机子数量",
				          "出纸数量", "直推代理基数", "招商金额","视频广告系数", "轮播广告系数","二维码广告系数", "直推机子分润系数","间推机子分润系数",
				          "推广二维码广告系数","推广广告系数","直推代理分润系数","服务出纸系数","办公补贴", "广告收入值","推广收入值","扫码收入值"};
		String[] titN = { "sum_date","coperator_name","coperator_type","ad_income","ad_carousel_income","prom_paper_income","promotion_income","prom_direct_income","prom_indirect_income",
				           "paper_income","direct_agent_income","subsidy_income","ad_rate","ad_carousel_rate","scan_rate","prom_direct_rate","prom_indirect_rate",
				           "prom_paper_rate","promotion_rate","direct_agent_rate","serve_rate","subsidy_rate","ad_income_rate","promotion_income_rate","scan_income_rate"};
		
		//String[] width= {"15","20","25","25","15","25","25","15","15","25","15","25"};
		//s.setWidth(width);
		s.setFname("客户结算统计 "); // 临时文件名
		s.setTitle("客户结算统计"); // 大标题名称
		s.setTitH(titH);
		s.setTitN(titN);
		s.setDataList(userIncomeList);
		File exportFile = null;
		exportFile = s.setData();
		String excelName = "客户结算统计" + DateUtils.getCurrentTime() + ".xls";
		s.exportExcel("客户结算统计", excelName, exportFile, request, response);
	}
	
}
