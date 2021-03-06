package com.zxiang.project.business.tissueRecord.controller;

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
import com.zxiang.project.business.tissueRecord.domain.TissueRecord;
import com.zxiang.project.business.tissueRecord.service.ITissueRecordService;

/**
 * 出纸记录 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
@Controller
@RequestMapping("/business/tissueRecord")
public class TissueRecordController extends BaseController
{
    private String prefix = "business/tissueRecord";
	
	@Autowired
	private ITissueRecordService tissueRecordService;
	
	@RequiresPermissions("business:tissueRecord:view")
	@GetMapping()
	public String tissueRecord()
	{
	    return prefix + "/tissueRecord";
	}
	
	/**
	 * 查询出纸记录列表
	 */
	@RequiresPermissions("business:tissueRecord:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TissueRecord tissueRecord)
	{
		startPage();
        List<TissueRecord> list = tissueRecordService.selectTissueRecordList(tissueRecord);
		return getDataTable(list);
	}
	
	/**
	 * 新增出纸记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存出纸记录
	 */
	@RequiresPermissions("business:tissueRecord:add")
	@Log(title = "出纸记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TissueRecord tissueRecord)
	{		
		return toAjax(tissueRecordService.insertTissueRecord(tissueRecord));
	}

	/**
	 * 修改出纸记录
	 */
	@GetMapping("/edit/{tissueRecordId}")
	public String edit(@PathVariable("tissueRecordId") Integer tissueRecordId, ModelMap mmap)
	{
		TissueRecord tissueRecord = tissueRecordService.selectTissueRecordById(tissueRecordId);
		mmap.put("tissueRecord", tissueRecord);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存出纸记录
	 */
	@RequiresPermissions("business:tissueRecord:edit")
	@Log(title = "出纸记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TissueRecord tissueRecord)
	{		
		return toAjax(tissueRecordService.updateTissueRecord(tissueRecord));
	}
	
	/**
	 * 删除出纸记录
	 */
	@RequiresPermissions("business:tissueRecord:remove")
	@Log(title = "出纸记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(tissueRecordService.deleteTissueRecordByIds(ids));
	}
	
}
