package com.zxiang.project.client.agent.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxiang.common.constant.UserConstants;
import com.zxiang.framework.aspectj.lang.annotation.DataFilter;
import com.zxiang.framework.aspectj.lang.annotation.Log;
import com.zxiang.framework.aspectj.lang.enums.BusinessType;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.domain.AjaxResult;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.project.client.agent.domain.Agent;
import com.zxiang.project.client.agent.service.IAgentService;
import com.zxiang.project.system.area.domain.Area;
import com.zxiang.project.system.area.service.IAreaService;
import com.zxiang.project.system.user.domain.User;
import com.zxiang.project.system.user.service.IUserService;

/**
 * 代理商 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Controller
@RequestMapping("/client/agent")
public class AgentController extends BaseController {
	private String prefix = "client/agent";

	@Autowired
	private IAgentService agentService;
	@Autowired
	private IAreaService areaService;
	@Autowired
	private IUserService userService;

	@RequiresPermissions("client:agent:view")
	@GetMapping()
	public String agent() {
		return prefix + "/agent";
	}

	/**
	 * 查询代理商列表
	 */
	@DataFilter(personAlias = "b.user_id")
	@RequiresPermissions("client:agent:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Agent agent) {
		// agent.setUserId(getUserId());
		startPage();
		List<Agent> list = agentService.selectAgentList(agent);
		return getDataTable(list);
	}

	/**
	 * 新增代理商
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap) {
		List<User> agentUserList = userService.selectUserListByUserType(UserConstants.USER_TYPE_AGENT);
		mmap.put("agentUserList", agentUserList); // 代理直推人

		List<User> payUserList = userService.selectUserListByUserType(UserConstants.USER_TYPE_ADVERTISE,
				UserConstants.USER_TYPE_AGENT, UserConstants.USER_TYPE_JOIN, UserConstants.USER_TYPE_REPAIR);
		mmap.put("payUserList", payUserList); // 购机推荐人
		return prefix + "/add";
	}

	/**
	 * 新增保存代理商
	 */
	@RequiresPermissions("client:agent:add")
	@Log(title = "代理商", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Agent agent) {
		return toAjax(agentService.insertAgent(agent));
	}

	/**
	 * 修改代理商
	 */
	@GetMapping("/edit/{agentId}")
	public String edit(@PathVariable("agentId") Integer agentId, ModelMap mmap) {
		Agent agent = agentService.selectAgentById(agentId);
		mmap.put("agent", agent);
		List<Area> provinceList = areaService.selectDropBoxList(0);
		mmap.put("provinceList", provinceList == null ? new ArrayList<Area>() : provinceList);
		if (agent.getProvince() != null) {
			List<Area> cityList = areaService.selectDropBoxList(agent.getProvince());
			mmap.put("cityList", cityList);
		}
		if (agent.getCity() != null) {
			List<Area> countyList = areaService.selectDropBoxList(agent.getCity());
			mmap.put("countyList", countyList == null ? new ArrayList<Area>() : countyList);
		}
		List<User> userList = userService.selectUserListByUserType(UserConstants.USER_TYPE_ADVERTISE,
				UserConstants.USER_TYPE_AGENT, UserConstants.USER_TYPE_JOIN, UserConstants.USER_TYPE_REPAIR);
		mmap.put("userList", userList);
		List<User> payUserList = userService.selectUserListByUserType(UserConstants.USER_TYPE_ADVERTISE,
				UserConstants.USER_TYPE_AGENT, UserConstants.USER_TYPE_JOIN, UserConstants.USER_TYPE_REPAIR);
		mmap.put("payUserList", payUserList); // 购机推荐人
		return prefix + "/edit";
	}

	/**
	 * 修改保存代理商
	 */
	@RequiresPermissions("client:agent:edit")
	@Log(title = "代理商", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Agent agent) {
		return toAjax(agentService.updateAgent(agent));
	}
	/**
	 * 修改保存代理商参数
	 */
	@RequiresPermissions("client:agent:edit")
	@Log(title = "代理商", businessType = BusinessType.UPDATE)
	@PostMapping("/saveEditParam")
	@ResponseBody
	public AjaxResult saveEditParam(Agent agent) {
		return toAjax(agentService.updateAgentParam(agent));
	}

	/**
	 * 修改代理商参数配置
	 */
	@GetMapping("/editParam/{agentId}")
	public String editParam(@PathVariable("agentId") Integer agentId, ModelMap mmap) {
		Agent agent = agentService.selectAgentById(agentId);
		mmap.put("agent", agent);
		return prefix + "/editParam";
	}

	/**
	 * 删除代理商
	 */
	@RequiresPermissions("client:agent:remove")
	@Log(title = "代理商", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(agentService.deleteAgentByIds(ids));
	}

	/**
	 * 查找代理商下拉框数据
	 */
	@RequestMapping("/getDropBoxAgentList")
	@ResponseBody
	public TableDataInfo getDropBoxAgentList() {
		List<Agent> list = agentService.selectDropBoxList();
		return getDataTable(list);
	}

	/**
	 * 批量修改机主参数配置弹框
	 */
	@GetMapping("/toBatchEditParam/{userType}")
	public String toBatchEditParam(@PathVariable("userType") String userType, HttpServletRequest requset,
			ModelMap mmap) {
		String ids = requset.getParameter("ids");
		mmap.put("ids", ids);
		return prefix + "/batchEditParam";
	}

	/**
	 * 批量修改机主参数配置
	 */
	@RequiresPermissions("client:agent:batchParamSet")
	@PostMapping("/batchEditParam")
	@ResponseBody
	public AjaxResult batchEditParam(Agent agent) {
		try {
			agentService.batchEditParam(agent);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error();
		}
		return AjaxResult.success();
	}
}
