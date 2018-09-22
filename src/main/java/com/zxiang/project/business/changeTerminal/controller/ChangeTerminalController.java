package com.zxiang.project.business.changeTerminal.controller;

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
import com.zxiang.project.business.changeTerminal.domain.ChangeTerminal;
import com.zxiang.project.business.changeTerminal.service.IChangeTerminalService;
import com.zxiang.project.business.terminal.service.ITerminalService;

/**
 * 终端更换记录 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
@Controller
@RequestMapping("/business/changeTerminal")
public class ChangeTerminalController extends BaseController
{
    private String prefix = "business/changeTerminal";
	
	@Autowired
	private IChangeTerminalService changeTerminalService;
	
	@Autowired
	private ITerminalService terminalService; 
	
	@RequiresPermissions("business:changeTerminal:view")
	@GetMapping()
	public String changeTerminal()
	{
	    return prefix + "/changeTerminal";
	}
	
	/**
	 * 查询终端更换记录列表
	 */
	@RequiresPermissions("business:changeTerminal:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ChangeTerminal changeTerminal)
	{
		startPage();
        List<ChangeTerminal> list = changeTerminalService.selectChangeTerminalList(changeTerminal);
		return getDataTable(list);
	}
	
	/**
	 * 新增终端更换记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存终端更换记录
	 */
	@RequiresPermissions("business:changeTerminal:add")
	@Log(title = "终端更换记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ChangeTerminal changeTerminal)
	{		
		String createor = getUser().getUserName();
		long userId = getUserId();
		
		changeTerminal.setCreateBy(createor+"("+userId+")");
		changeTerminal.setCreateTime(new Date());
		return toAjax(changeTerminalService.insertChangeTerminal(changeTerminal));
	}

	/**
	 * 修改终端更换记录
	 */
	@GetMapping("/edit/{changeTerminalId}")
	public String edit(@PathVariable("changeTerminalId") Integer changeTerminalId, ModelMap mmap)
	{
		ChangeTerminal changeTerminal = changeTerminalService.selectChangeTerminalById(changeTerminalId);
		
		mmap.put("changeTerminal", changeTerminal);
		mmap.put("terminalDropBoxList", terminalService.selectDropBoxList());
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存终端更换记录
	 */
	@RequiresPermissions("business:changeTerminal:edit")
	@Log(title = "终端更换记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ChangeTerminal changeTerminal)
	{		
		String updateor = getUser().getUserName();
		long userId = getUserId();
		
		changeTerminal.setUpdateBy(updateor+"("+userId+")");
		changeTerminal.setUpdateTime(new Date());
		return toAjax(changeTerminalService.updateChangeTerminal(changeTerminal));
	}
	
	/**
	 * 删除终端更换记录
	 */
	@RequiresPermissions("business:changeTerminal:remove")
	@Log(title = "终端更换记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(changeTerminalService.deleteChangeTerminalByIds(ids));
	}
	
}
