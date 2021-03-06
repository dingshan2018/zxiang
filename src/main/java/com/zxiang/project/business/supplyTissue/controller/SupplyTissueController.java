package com.zxiang.project.business.supplyTissue.controller;

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
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.domain.AjaxResult;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.project.business.supplyTissue.domain.SupplyTissue;
import com.zxiang.project.business.supplyTissue.service.ISupplyTissueService;

/**
 * 补纸记录 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
@Controller
@RequestMapping("/business/supplyTissue")
public class SupplyTissueController extends BaseController
{
    private String prefix = "business/supplyTissue";
	
	@Autowired
	private ISupplyTissueService supplyTissueService;
	
	@RequiresPermissions("business:supplyTissue:view")
	@GetMapping()
	public String supplyTissue()
	{
	    return prefix + "/supplyTissue";
	}
	
	/**
	 * 查询补纸记录列表
	 */
	@RequiresPermissions("business:supplyTissue:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SupplyTissue supplyTissue)
	{
		startPage();
        List<SupplyTissue> list = supplyTissueService.selectSupplyTissueList(supplyTissue);
		return getDataTable(list);
	}
	
	/**
	 * 新增补纸记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存补纸记录
	 */
	@RequiresPermissions("business:supplyTissue:add")
	@Log(title = "补纸记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SupplyTissue supplyTissue)
	{		
		return toAjax(supplyTissueService.insertSupplyTissue(supplyTissue));
	}

	/**
	 * 修改补纸记录
	 */
	@GetMapping("/edit/{supplyTissueId}")
	public String edit(@PathVariable("supplyTissueId") Integer supplyTissueId, ModelMap mmap)
	{
		SupplyTissue supplyTissue = supplyTissueService.selectSupplyTissueById(supplyTissueId);
		mmap.put("supplyTissue", supplyTissue);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存补纸记录
	 */
	@RequiresPermissions("business:supplyTissue:edit")
	@Log(title = "补纸记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SupplyTissue supplyTissue)
	{		
		return toAjax(supplyTissueService.updateSupplyTissue(supplyTissue));
	}
	
	/**
	 * 删除补纸记录
	 */
	@RequiresPermissions("business:supplyTissue:remove")
	@Log(title = "补纸记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(supplyTissueService.deleteSupplyTissueByIds(ids));
	}
	
}
