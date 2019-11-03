package com.zxiang.project.client.shopper.controller;

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
import com.zxiang.project.client.join.domain.Join;
import com.zxiang.project.client.join.service.IJoinService;
import com.zxiang.project.client.shopper.domain.Shopper;
import com.zxiang.project.client.shopper.service.IShopperService;
import com.zxiang.project.system.user.domain.User;
import com.zxiang.project.system.user.service.IUserService;

/**
 * 机主 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Controller
@RequestMapping("/client/shopper")
public class ShopperController extends BaseController {
	private String prefix = "client/shopper";

	@Autowired
	private IShopperService joinService;
	@Autowired
	private IUserService userService;

	@RequiresPermissions("client:shopper:view")
	@GetMapping()
	public String join() {
		return prefix + "/shopper";
	}

	/**
	 * 查询机主列表
	 */
	@DataFilter(personAlias = "b.user_id")
	@RequiresPermissions("client:shopper:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Shopper join) {
		startPage();
		List<Shopper> list = joinService.selectShopperList(join);
		return getDataTable(list);
	}

	/**
	 * 新增机主
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap) {
//		List<User> payUserList = userService.selectUserListByUserType(UserConstants.USER_TYPE_ADVERTISE,
//				UserConstants.USER_TYPE_AGENT, UserConstants.USER_TYPE_JOIN, UserConstants.USER_TYPE_REPAIR);
//		mmap.put("payUserList", payUserList); // 购机推荐人
		return prefix + "/add";
	}

	/**
	 * 新增保存机主
	 */
	@RequiresPermissions("client:shopper:add")
	@Log(title = "店主", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Shopper join) {
		return toAjax(joinService.insertShopper(join));
	}

	/**
	 * 修改机主
	 */
	@GetMapping("/edit/{shopperId}")
	public String edit(@PathVariable("shopperId") Integer joinId, ModelMap mmap) {
		Shopper join = joinService.selectShopperById(joinId);
		mmap.put("shopper", join);
//		List<User> payUserList = userService.selectUserListByUserType(UserConstants.USER_TYPE_ADVERTISE,
//				UserConstants.USER_TYPE_AGENT, UserConstants.USER_TYPE_JOIN, UserConstants.USER_TYPE_REPAIR);
//		mmap.put("payUserList", payUserList); // 购机推荐人
		return prefix + "/edit";
	}

	/**
	 * 修改保存机主
	 */
	@RequiresPermissions("client:shopper:edit")
	@Log(title = "店主", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Shopper join) {
		return toAjax(joinService.updateShopper(join));
	}

	/**
	 * 修改机主参数配置弹框
	 */
	@GetMapping("/editParam/{shopperId}")
	public String editParam(@PathVariable("shopperId") Integer joinId, ModelMap mmap) {
		Shopper join = joinService.selectShopperById(joinId);
		mmap.put("shopper", join);
		return prefix + "/editParam";
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
	@RequiresPermissions("client:shopper:batchParamSet")
	@PostMapping("/batchEditParam")
	@ResponseBody
	public AjaxResult batchEditParam(Shopper shopper) {
		try {
			joinService.batchEditParam(shopper);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error();
		}
		return AjaxResult.success();
	}

	/**
	 * 删除机主
	 */
	@RequiresPermissions("client:shopper:remove")
	@Log(title = "店主", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(joinService.deleteShopperByIds(ids));
	}

	/**
	 * 查找机主下拉框数据
	 */
	@RequestMapping("/getDropBoxJoinList")
	@ResponseBody
	public TableDataInfo getDropBoxJoinList() {
		List<Shopper> list = joinService.selectDropBoxList();
		return getDataTable(list);
	}

}
