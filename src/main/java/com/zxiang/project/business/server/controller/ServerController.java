package com.zxiang.project.business.server.controller;

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
import com.zxiang.project.business.server.domain.Server;
import com.zxiang.project.business.server.service.IServerService;

/**
 * 接入服务器 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-11-20
 */
@Controller
@RequestMapping("/settle/server")
public class ServerController extends BaseController
{
    private String prefix = "settle/server";
	
	@Autowired
	private IServerService serverService;
	
	@RequiresPermissions("settle:server:view")
	@GetMapping()
	public String server()
	{
	    return prefix + "/server";
	}
	
	/**
	 * 查询接入服务器列表
	 */
	@RequiresPermissions("settle:server:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Server server)
	{
		startPage();
        List<Server> list = serverService.selectServerList(server);
		return getDataTable(list);
	}
	
	/**
	 * 新增接入服务器
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存接入服务器
	 */
	@RequiresPermissions("settle:server:add")
	@Log(title = "接入服务器", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Server server)
	{		
		return toAjax(serverService.insertServer(server));
	}

	/**
	 * 修改接入服务器
	 */
	@GetMapping("/edit/{serverId}")
	public String edit(@PathVariable("serverId") Integer serverId, ModelMap mmap)
	{
		Server server = serverService.selectServerById(serverId);
		mmap.put("server", server);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存接入服务器
	 */
	@RequiresPermissions("settle:server:edit")
	@Log(title = "接入服务器", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Server server)
	{		
		return toAjax(serverService.updateServer(server));
	}
	
	/**
	 * 删除接入服务器
	 */
	@RequiresPermissions("settle:server:remove")
	@Log(title = "接入服务器", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(serverService.deleteServerByIds(ids));
	}
	
}
