package com.zxiang.project.client.repair.controller;

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
import com.zxiang.project.client.repair.domain.Repair;
import com.zxiang.project.client.repair.service.IRepairService;

/**
 * 服务商 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Controller
@RequestMapping("/client/repair")
public class RepairController extends BaseController
{
    private String prefix = "client/repair";
	
	@Autowired
	private IRepairService repairService;
	
	@RequiresPermissions("client:repair:view")
	@GetMapping()
	public String repair()
	{
	    return prefix + "/repair";
	}
	
	/**
	 * 查询服务商列表
	 */
	@RequiresPermissions("client:repair:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Repair repair)
	{
		startPage();
        List<Repair> list = repairService.selectRepairList(repair);
		return getDataTable(list);
	}
	
	/**
	 * 新增服务商
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存服务商
	 */
	@RequiresPermissions("client:repair:add")
	@Log(title = "服务商", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Repair repair)
	{		
		return toAjax(repairService.insertRepair(repair));
	}

	/**
	 * 修改服务商
	 */
	@GetMapping("/edit/{repairId}")
	public String edit(@PathVariable("repairId") Integer repairId, ModelMap mmap)
	{
		Repair repair = repairService.selectRepairById(repairId);
		mmap.put("repair", repair);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存服务商
	 */
	@RequiresPermissions("client:repair:edit")
	@Log(title = "服务商", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Repair repair)
	{		
		return toAjax(repairService.updateRepair(repair));
	}
	/**
	 * 修改服务商参数配置
	 */
	@GetMapping("/editParam/{repairId}")
	public String editParam(@PathVariable("repairId") Integer repairId, ModelMap mmap)
	{
		Repair repair = repairService.selectRepairById(repairId);
		mmap.put("repair", repair);
	    return prefix + "/editParam";
	}
	/**
	 * 删除服务商
	 */
	@RequiresPermissions("client:repair:remove")
	@Log(title = "服务商", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(repairService.deleteRepairByIds(ids));
	}
	
	/**
	 * 查找服务商下拉框数据
	 */
	@RequestMapping("/getDropBoxRepairList")
    @ResponseBody
    public TableDataInfo getDropBoxRepairList() {
		List<Repair> list = repairService.selectDropBoxList();
		return getDataTable(list);
    }
}
