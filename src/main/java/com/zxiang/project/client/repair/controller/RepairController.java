package com.zxiang.project.client.repair.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxiang.common.constant.UserConstants;
import com.zxiang.framework.aspectj.lang.annotation.DataFilter;
import com.zxiang.framework.aspectj.lang.annotation.Log;
import com.zxiang.framework.aspectj.lang.enums.BusinessType;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.domain.AjaxResult;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.project.client.repair.domain.Repair;
import com.zxiang.project.client.repair.domain.RepairArea;
import com.zxiang.project.client.repair.service.IRepairService;
import com.zxiang.project.system.user.domain.User;
import com.zxiang.project.system.user.service.IUserService;

/**
 * 服务商 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Controller
@RequestMapping("/client/repair")
public class RepairController extends BaseController {
	private String prefix = "client/repair";
	@Autowired
	private IUserService userService;
	@Autowired
	private IRepairService repairService;

	@RequiresPermissions("client:repair:view")
	@GetMapping()
	public String repair() {
		return prefix + "/repair";
	}

	/**
	 * 查询服务商列表
	 */
	@DataFilter(personAlias = "b.user_id")
	@RequiresPermissions("client:repair:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Repair repair) {
		startPage();
		List<Repair> list = repairService.selectRepairList(repair);
		return getDataTable(list);
	}

	/**
	 * 新增服务商
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap) {
		List<User> payUserList = userService.selectUserListByUserType(UserConstants.USER_TYPE_ADVERTISE,
				UserConstants.USER_TYPE_AGENT, UserConstants.USER_TYPE_JOIN, UserConstants.USER_TYPE_REPAIR);
		mmap.put("payUserList", payUserList); // 购机推荐人
		return prefix + "/add";
	}

	/**
	 * 新增保存服务商
	 */
	@RequiresPermissions("client:repair:add")
	@Log(title = "服务商", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Repair repair) {
		return toAjax(repairService.insertRepair(repair));
	}

	/**
	 * 修改服务商
	 */
	@GetMapping("/edit/{repairId}")
	public String edit(@PathVariable("repairId") Integer repairId, ModelMap mmap) {
		Repair repair = repairService.selectRepairById(repairId);
		mmap.put("repair", repair);
		List<User> payUserList = userService.selectUserListByUserType(UserConstants.USER_TYPE_ADVERTISE,
				UserConstants.USER_TYPE_AGENT, UserConstants.USER_TYPE_JOIN, UserConstants.USER_TYPE_REPAIR);
		mmap.put("payUserList", payUserList); // 购机推荐人
		return prefix + "/edit";
	}

	/**
	 * 修改保存服务商
	 */
	@RequiresPermissions("client:repair:edit")
	@Log(title = "服务商", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Repair repair) {
		return toAjax(repairService.updateRepair(repair));
	}

	/**
	 * 修改服务商参数配置
	 */
	@GetMapping("/editParam/{repairId}")
	public String editParam(@PathVariable("repairId") Integer repairId, ModelMap mmap) {
		Repair repair = repairService.selectRepairById(repairId);
		mmap.put("repair", repair);
		return prefix + "/editParam";
	}

	/**
	 * 删除服务商
	 */
	@RequiresPermissions("client:repair:remove")
	@Log(title = "服务商", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
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

	/**
	 * 修改服务商参数配置
	 */
	@GetMapping("/repairArea/{repairId}")
	public String repairArea(@PathVariable("repairId") Integer repairId, ModelMap mmap) {
		List<RepairArea> repairAreaList = repairService.selectrepairAreasById(repairId);
		mmap.put("repairAreaList", repairAreaList);
		mmap.put("repairId", repairId);
		return prefix + "/repairArea";
	}

	/**
	 * 新增服务商网点区域
	 */
	@PostMapping("/saveRepairArea")
	@ResponseBody
	public AjaxResult saveRepairArea(@RequestBody RepairArea repairArea) {
		try {
			repairArea = repairService.saveRepairArea(repairArea);
			AjaxResult json = new AjaxResult();
			json.put("repairArea", repairArea);
			json.put("code", 0);
			return json;
		} catch (Exception e) {
			return AjaxResult.error();
		}
	}

	/**
	 * 删除服务商网点区域
	 */
	@PostMapping("/deleteRepairArea/{repairAreaId}")
	@ResponseBody
	public AjaxResult deleteRepairArea(@PathVariable("repairAreaId") Integer repairAreaId, ModelMap mmap) {
		try {
			repairService.deleteRepairArea(repairAreaId);
			return AjaxResult.success();
		} catch (Exception e) {
			return AjaxResult.error();
		}
	}

	/**
	 * 根据城市查询服务网点
	 */
	@RequestMapping("/selectRepairByCity")
	@ResponseBody
	public TableDataInfo selectRepairByCity(@RequestBody Map<String, Object> params) {
		String city = (String) params.get("city");
		String county = (String) params.get("county");
		List<Repair> list = repairService.selectRepairByCity(Long.parseLong(city), Long.parseLong(county));
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
	@RequiresPermissions("client:repair:batchParamSet")
	@PostMapping("/batchEditParam")
	@ResponseBody
	public AjaxResult batchEditParam(Repair repair) {
		try {
			repairService.batchEditParam(repair);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error();
		}
		return AjaxResult.success();
	}
}
