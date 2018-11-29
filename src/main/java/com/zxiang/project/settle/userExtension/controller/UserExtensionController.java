package com.zxiang.project.settle.userExtension.controller;

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
import com.zxiang.project.settle.userExtension.domain.UserExtension;
import com.zxiang.project.settle.userExtension.service.IUserExtensionService;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.framework.web.domain.AjaxResult;

/**
 * 客户推广日统计 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-11-26
 */
@Controller
@RequestMapping("/settle/userExtension")
public class UserExtensionController extends BaseController
{
    private String prefix = "settle/userExtension";
	
	@Autowired
	private IUserExtensionService userExtensionService;
	
	@RequiresPermissions("settle:userExtension:view")
	@GetMapping()
	public String userExtension()
	{
	    return prefix + "/userExtension";
	}
	
	/**
	 * 查询客户推广日统计列表
	 */
	@DataFilter(personAlias="coperator_id")
	@RequiresPermissions("settle:userExtension:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(UserExtension userExtension)
	{
		startPage();
        List<UserExtension> list = userExtensionService.selectUserExtensionList(userExtension);
		return getDataTable(list);
	}
	
	/**
	 * 新增客户推广日统计
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存客户推广日统计
	 */
	@RequiresPermissions("settle:userExtension:add")
	@Log(title = "客户推广日统计", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(UserExtension userExtension)
	{		
		return toAjax(userExtensionService.insertUserExtension(userExtension));
	}

	/**
	 * 修改客户推广日统计
	 */
	@GetMapping("/edit/{incomeId}")
	public String edit(@PathVariable("incomeId") Integer incomeId, ModelMap mmap)
	{
		UserExtension userExtension = userExtensionService.selectUserExtensionById(incomeId);
		mmap.put("userExtension", userExtension);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存客户推广日统计
	 */
	@RequiresPermissions("settle:userExtension:edit")
	@Log(title = "客户推广日统计", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(UserExtension userExtension)
	{		
		return toAjax(userExtensionService.updateUserExtension(userExtension));
	}
	
	/**
	 * 删除客户推广日统计
	 */
	@RequiresPermissions("settle:userExtension:remove")
	@Log(title = "客户推广日统计", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(userExtensionService.deleteUserExtensionByIds(ids));
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
        	 userExtensionService.queryExport(query, request, response);
 		} catch (Exception e) {
 		  e.printStackTrace();
 		}
    }
	
}
