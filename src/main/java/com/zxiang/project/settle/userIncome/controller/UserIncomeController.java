package com.zxiang.project.settle.userIncome.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxiang.framework.aspectj.lang.annotation.DataFilter;
import com.zxiang.framework.aspectj.lang.annotation.Log;
import com.zxiang.framework.aspectj.lang.enums.BusinessType;
import com.zxiang.project.settle.userIncome.domain.UserIncome;
import com.zxiang.project.settle.userIncome.service.IUserIncomeService;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.framework.web.domain.AjaxResult;

/**
 * 客户收入日统计 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Controller
@RequestMapping("/settle/userIncome")
public class UserIncomeController extends BaseController
{
    private String prefix = "settle/userIncome";
	
	@Autowired
	private IUserIncomeService userIncomeService;
	
	@RequiresPermissions("settle:userIncome:view")
	@GetMapping()
	public String userIncome()
	{
	    return prefix + "/userIncome";
	}
	
	/**
	 * 查询客户收入日统计列表
	 */
	@DataFilter(personAlias="b.user_id")
	@RequiresPermissions("settle:userIncome:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(UserIncome userIncome)
	{
		startPage();
        List<UserIncome> list = userIncomeService.selectUserIncomeList(userIncome);
		return getDataTable(list);
	}
	
	/**
	 * 新增客户收入日统计
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存客户收入日统计
	 */
	@RequiresPermissions("settle:userIncome:add")
	@Log(title = "客户收入日统计", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(UserIncome userIncome)
	{		
		return toAjax(userIncomeService.insertUserIncome(userIncome));
	}

	/**
	 * 修改客户收入日统计
	 */
	@GetMapping("/edit/{incomeId}")
	public String edit(@PathVariable("incomeId") Integer incomeId, ModelMap mmap)
	{
		UserIncome userIncome = userIncomeService.selectUserIncomeById(incomeId);
		mmap.put("userIncome", userIncome);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存客户收入日统计
	 */
	@RequiresPermissions("settle:userIncome:edit")
	@Log(title = "客户收入日统计", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(UserIncome userIncome)
	{		
		return toAjax(userIncomeService.updateUserIncome(userIncome));
	}
	
	/**
	 * 删除客户收入日统计
	 */
	@RequiresPermissions("settle:userIncome:remove")
	@Log(title = "客户收入日统计", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(userIncomeService.deleteUserIncomeByIds(ids));
	}
	
    /**
     * 导出
     * @throws Exception 
     */
	@GetMapping("/excelExport")
	@ResponseBody
    public void excelExport(HttpServletResponse response,HttpServletRequest request) throws Exception {
    	 String bgTime = request.getParameter("startTime");
    	 String edTime = request.getParameter("endTime");
    	 String coperatorName = request.getParameter("coperatorName");
    	 
    	 HashMap<String, String> query = new HashMap<String, String>();
         query.put("bgTime", bgTime);
         query.put("edTime", edTime);
         query.put("coperatorName", coperatorName);
         try {
        	 userIncomeService.queryExport(query, request, response);
 		} catch (Exception e) {
 		  e.printStackTrace();
 		}
    }
	
}
