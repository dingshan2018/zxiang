package com.zxiang.project.settle.coefficient.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zxiang.framework.aspectj.lang.annotation.Log;
import com.zxiang.framework.aspectj.lang.enums.BusinessType;
import com.zxiang.project.settle.coefficient.domain.Coefficient;
import com.zxiang.project.settle.coefficient.service.ICoefficientService;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.framework.web.domain.AjaxResult;

/**
 * 系数配置 信息操作处理
 * 
 * @author ZXiang
 * @date 2019-05-27
 */
@Controller
@RequestMapping("/settle/coefficient")
public class CoefficientController extends BaseController
{
    private String prefix = "settle/coefficient";
	
	@Autowired
	private ICoefficientService coefficientService;
	
	@RequiresPermissions("settle:coefficient:view")
	@GetMapping()
	public String coefficient()
	{
	    return prefix + "/coefficient";
	}
	
	/**
	 * 查询系数配置列表
	 */
	@RequiresPermissions("settle:coefficient:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Coefficient coefficient)
	{
		startPage();
        List<Coefficient> list = coefficientService.selectCoefficientList(coefficient);
		return getDataTable(list);
	}
	
	/**
	 * 新增系数配置
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存系数配置
	 */
	@RequiresPermissions("settle:coefficient:add")
	@Log(title = "系数配置", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Coefficient coefficient)
	{		
		return toAjax(coefficientService.insertCoefficient(coefficient));
	}

	/**
	 * 修改系数配置
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Coefficient coefficient = coefficientService.selectCoefficientById(id);
		mmap.put("coefficient", coefficient);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存系数配置
	 */
	@RequiresPermissions("settle:coefficient:edit")
	@Log(title = "系数配置", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Coefficient coefficient)
	{		
		return toAjax(coefficientService.updateCoefficient(coefficient));
	}
	
	/**
	 * 删除系数配置
	 */
	@RequiresPermissions("settle:coefficient:remove")
	@Log(title = "系数配置", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(coefficientService.deleteCoefficientByIds(ids));
	}
	
}
