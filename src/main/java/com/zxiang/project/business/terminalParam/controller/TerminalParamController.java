package com.zxiang.project.business.terminalParam.controller;

import java.util.Date;
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
import com.zxiang.project.business.terminal.service.ITerminalService;
import com.zxiang.project.business.terminalParam.domain.TerminalParam;
import com.zxiang.project.business.terminalParam.service.ITerminalParamService;

/**
 * 终端参数 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
@Controller
@RequestMapping("/business/terminalParam")
public class TerminalParamController extends BaseController
{
    private String prefix = "business/terminalParam";
	
	@Autowired
	private ITerminalParamService terminalParamService;
	@Autowired
	private ITerminalService terminalService; 
	
	@RequiresPermissions("business:terminalParam:view")
	@GetMapping()
	public String terminalParam()
	{
	    return prefix + "/terminalParam";
	}
	
	/**
	 * 查询终端参数列表
	 */
	@RequiresPermissions("business:terminalParam:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TerminalParam terminalParam)
	{
		startPage();
        List<TerminalParam> list = terminalParamService.selectTerminalParamList(terminalParam);
		return getDataTable(list);
	}
	
	/**
	 * 新增终端参数
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存终端参数
	 */
	@RequiresPermissions("business:terminalParam:add")
	@Log(title = "终端参数", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TerminalParam terminalParam)
	{		
		String createor = getUser().getUserName();
		long userId = getUserId();

		terminalParam.setCreateBy(createor+"("+userId+")");
		terminalParam.setCreateTime(new Date());
		return toAjax(terminalParamService.insertTerminalParam(terminalParam));
	}

	/**
	 * 修改终端参数
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		TerminalParam terminalParam = terminalParamService.selectTerminalParamById(id);
		mmap.put("terminalParam", terminalParam);
		mmap.put("terminalDropBoxList", terminalService.selectDropBoxList());
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存终端参数
	 */
	@RequiresPermissions("business:terminalParam:edit")
	@Log(title = "终端参数", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TerminalParam terminalParam)
	{		
		return toAjax(terminalParamService.updateTerminalParam(terminalParam));
	}
	
	/**
	 * 删除终端参数
	 */
	@RequiresPermissions("business:terminalParam:remove")
	@Log(title = "终端参数", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(terminalParamService.deleteTerminalParamByIds(ids));
	}
	
}
