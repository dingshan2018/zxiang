package com.zxiang.project.business.terminal.controller;

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
import com.zxiang.project.business.terminal.domain.Terminal;
import com.zxiang.project.business.terminal.service.ITerminalService;

/**
 * 终端管理 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
@Controller
@RequestMapping("/business/terminal")
public class TerminalController extends BaseController
{
    private String prefix = "business/terminal";
	
	@Autowired
	private ITerminalService terminalService;
	
	@RequiresPermissions("business:terminal:view")
	@GetMapping()
	public String terminal()
	{
	    return prefix + "/terminal";
	}
	
	/**
	 * 查询终端管理列表
	 */
	@RequiresPermissions("business:terminal:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Terminal terminal)
	{
		startPage();
        List<Terminal> list = terminalService.selectTerminalList(terminal);
		return getDataTable(list);
	}
	
	/**
	 * 新增终端管理
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存终端管理
	 */
	@RequiresPermissions("business:terminal:add")
	@Log(title = "终端管理", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Terminal terminal)
	{		
		return toAjax(terminalService.insertTerminal(terminal));
	}

	/**
	 * 修改终端管理
	 */
	@GetMapping("/edit/{terminalId}")
	public String edit(@PathVariable("terminalId") Integer terminalId, ModelMap mmap)
	{
		Terminal terminal = terminalService.selectTerminalById(terminalId);
		mmap.put("terminal", terminal);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存终端管理
	 */
	@RequiresPermissions("business:terminal:edit")
	@Log(title = "终端管理", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Terminal terminal)
	{		
		return toAjax(terminalService.updateTerminal(terminal));
	}
	
	/**
	 * 删除终端管理
	 */
	@RequiresPermissions("business:terminal:remove")
	@Log(title = "终端管理", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(terminalService.deleteTerminalByIds(ids));
	}
	
}
