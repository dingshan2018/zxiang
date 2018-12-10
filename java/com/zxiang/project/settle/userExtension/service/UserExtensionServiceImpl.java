package com.zxiang.project.settle.userExtension.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zxiang.project.settle.userExtension.mapper.UserExtensionMapper;
import com.zxiang.project.settle.userExtension.domain.UserExtension;
import com.zxiang.project.settle.userExtension.service.IUserExtensionService;
import com.zxiang.common.support.Convert;
import com.zxiang.common.utils.DateUtils;
import com.zxiang.common.utils.excel.EXCELObject;
import com.zxiang.framework.aspectj.lang.annotation.DataFilter;

/**
 * 客户推广日统计 服务层实现
 * 
 * @author ZXiang
 * @date 2018-11-26
 */
@Service
public class UserExtensionServiceImpl implements IUserExtensionService 
{
	@Autowired
	private UserExtensionMapper userExtensionMapper;

	/**
     * 查询客户推广日统计信息
     * 
     * @param incomeId 客户推广日统计ID
     * @return 客户推广日统计信息
     */
    @Override
	public UserExtension selectUserExtensionById(Integer incomeId)
	{
	    return userExtensionMapper.selectUserExtensionById(incomeId);
	}
	
	/**
     * 查询客户推广日统计列表
     * 
     * @param userExtension 客户推广日统计信息
     * @return 客户推广日统计集合
     */
	@Override
	public List<UserExtension> selectUserExtensionList(UserExtension userExtension)
	{
	    return userExtensionMapper.selectUserExtensionList(userExtension);
	}
	
    /**
     * 新增客户推广日统计
     * 
     * @param userExtension 客户推广日统计信息
     * @return 结果
     */
	@Override
	public int insertUserExtension(UserExtension userExtension)
	{
	    return userExtensionMapper.insertUserExtension(userExtension);
	}
	
	/**
     * 修改客户推广日统计
     * 
     * @param userExtension 客户推广日统计信息
     * @return 结果
     */
	@Override
	public int updateUserExtension(UserExtension userExtension)
	{
	    return userExtensionMapper.updateUserExtension(userExtension);
	}
	
	@Override
	public List<UserExtension> selectUserExtension(UserExtension userExtension) {
		return userExtensionMapper.selectUserExtension(userExtension);
	}

	/**
     * 删除客户推广日统计对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteUserExtensionByIds(String ids)
	{
		return userExtensionMapper.deleteUserExtensionByIds(Convert.toStrArray(ids));
	}

	@Override
	@DataFilter(personAlias="coperator_id")
	public void queryExport(HashMap<String, String> params, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List userIncomeList = userExtensionMapper.queryExport(params);
		String realPath = request.getSession().getServletContext().getRealPath("/file/temp");
		EXCELObject s = new EXCELObject();
		s.seteFilePath(realPath);
		String[] titH = { "日期","合作客户 ", "合作类型 ","经营主体","推广广告基数","推广二维码广告基数","直推机子数量","间推机子数量","直推代理基数"};
		String[] titN = {"sum_date","coperator_name","coperator_type","puser_name","promotion_income","prom_paper_income","prom_direct_income","prom_indirect_income","direct_agent_income"};
		s.setFname("客户推广统计 "); // 临时文件名
		s.setTitle("客户推广统计"); // 大标题名称
		s.setTitH(titH);
		s.setTitN(titN);
		s.setDataList(userIncomeList);
		File exportFile = null;
		exportFile = s.setData();
		String excelName = "客户推广统计" + DateUtils.getCurrentTime() + ".xls";
		s.exportExcel("客户推广统计", excelName, exportFile, request, response);
	}
	
}
