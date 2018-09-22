package com.zxiang.project.client.agent.controller;

import java.util.ArrayList;
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
import com.zxiang.project.client.agent.domain.Agent;
import com.zxiang.project.client.agent.service.IAgentService;
import com.zxiang.project.system.area.domain.Area;
import com.zxiang.project.system.area.service.IAreaService;

/**
 * 代理商 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Controller
@RequestMapping("/client/agent")
public class AgentController extends BaseController
{
    private String prefix = "client/agent";
	
	@Autowired
	private IAgentService agentService;
	@Autowired
	private IAreaService areaService;
	
	@RequiresPermissions("client:agent:view")
	@GetMapping()
	public String agent()
	{
	    return prefix + "/agent";
	}
	
	/**
	 * 查询代理商列表
	 */
	@RequiresPermissions("client:agent:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Agent agent)
	{
		startPage();
        List<Agent> list = agentService.selectAgentList(agent);
		return getDataTable(list);
	}
	
	/**
	 * 新增代理商
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存代理商
	 */
	@RequiresPermissions("client:agent:add")
	@Log(title = "代理商", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Agent agent)
	{		
		return toAjax(agentService.insertAgent(agent));
	}

	/**
	 * 修改代理商
	 */
	@GetMapping("/edit/{agentId}")
	public String edit(@PathVariable("agentId") Integer agentId, ModelMap mmap)
	{
		Agent agent = agentService.selectAgentById(agentId);
		mmap.put("agent", agent);
		List<Area> provinceList = areaService.selectDropBoxList(0);
		mmap.put("provinceList", provinceList == null ? new ArrayList<Area>() : provinceList);
		if(agent.getProvince() != null) {
			List<Area> cityList = areaService.selectDropBoxList(agent.getProvince());
			mmap.put("cityList", cityList);
		}
		if(agent.getCity() != null) {
			List<Area> countyList = areaService.selectDropBoxList(agent.getCity());
			mmap.put("countyList", countyList == null ? new ArrayList<Area>() : countyList);
		}
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存代理商
	 */
	@RequiresPermissions("client:agent:edit")
	@Log(title = "代理商", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Agent agent)
	{		
		return toAjax(agentService.updateAgent(agent));
	}
	
	/**
	 * 删除代理商
	 */
	@RequiresPermissions("client:agent:remove")
	@Log(title = "代理商", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(agentService.deleteAgentByIds(ids));
	}
	
}
