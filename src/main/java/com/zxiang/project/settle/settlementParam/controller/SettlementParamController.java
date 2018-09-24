package com.zxiang.project.settle.settlementParam.controller;

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
import com.zxiang.project.settle.settlementParam.domain.SettlementParam;
import com.zxiang.project.settle.settlementParam.service.ISettlementParamService;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.framework.web.domain.AjaxResult;

/**
 * 结算系数配置 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-25
 */
@Controller
@RequestMapping("/settle/settlementParam")
public class SettlementParamController extends BaseController
{
    private String prefix = "settle/settlementParam";
	
	@Autowired
	private ISettlementParamService settlementParamService;
	
	@RequiresPermissions("settle:settlementParam:view")
	@GetMapping()
	public String settlementParam()
	{
	    return prefix + "/settlementParam";
	}
	
	/**
	 * 查询结算系数配置列表
	 */
	@RequiresPermissions("settle:settlementParam:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SettlementParam settlementParam)
	{
		startPage();
        List<SettlementParam> list = settlementParamService.selectSettlementParamList(settlementParam);
		return getDataTable(list);
	}
	
	/**
	 * 新增结算系数配置
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存结算系数配置
	 */
	@RequiresPermissions("settle:settlementParam:add")
	@Log(title = "结算系数配置", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SettlementParam settlementParam)
	{		
		return toAjax(settlementParamService.insertSettlementParam(settlementParam));
	}

	/**
	 * 修改结算系数配置
	 */
	@GetMapping("/edit/{settlementParamId}")
	public String edit(@PathVariable("settlementParamId") Integer settlementParamId, ModelMap mmap)
	{
		SettlementParam settlementParam = settlementParamService.selectSettlementParamById(settlementParamId);
		mmap.put("settlementParam", settlementParam);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存结算系数配置
	 */
	@RequiresPermissions("settle:settlementParam:edit")
	@Log(title = "结算系数配置", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SettlementParam settlementParam)
	{		
		return toAjax(settlementParamService.updateSettlementParam(settlementParam));
	}
	
	/**
	 * 删除结算系数配置
	 */
	@RequiresPermissions("settle:settlementParam:remove")
	@Log(title = "结算系数配置", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(settlementParamService.deleteSettlementParamByIds(ids));
	}
	
}
