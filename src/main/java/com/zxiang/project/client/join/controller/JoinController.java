package com.zxiang.project.client.join.controller;

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

import com.zxiang.common.constant.UserConstants;
import com.zxiang.framework.aspectj.lang.annotation.DataFilter;
import com.zxiang.framework.aspectj.lang.annotation.Log;
import com.zxiang.framework.aspectj.lang.enums.BusinessType;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.domain.AjaxResult;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.project.client.join.domain.Join;
import com.zxiang.project.client.join.service.IJoinService;
import com.zxiang.project.system.user.domain.User;
import com.zxiang.project.system.user.service.IUserService;

/**
 * 机主 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Controller
@RequestMapping("/client/join")
public class JoinController extends BaseController
{
    private String prefix = "client/join";
	
	@Autowired
	private IJoinService joinService;
	@Autowired
	private IUserService userService;
	
	@RequiresPermissions("client:join:view")
	@GetMapping()
	public String join()
	{
	    return prefix + "/join";
	}
	
	/**
	 * 查询机主列表
	 */
	@DataFilter(personAlias="b.user_id")
	@RequiresPermissions("client:join:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Join join)
	{
		startPage();
        List<Join> list = joinService.selectJoinList(join);
		return getDataTable(list);
	}
	
	/**
	 * 新增机主
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap) {
		List<User> payUserList = userService.selectUserListByUserType(UserConstants.USER_TYPE_ADVERTISE,
				UserConstants.USER_TYPE_AGENT,UserConstants.USER_TYPE_JOIN,UserConstants.USER_TYPE_REPAIR);
		mmap.put("payUserList", payUserList); // 购机推荐人
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存机主
	 */
	@RequiresPermissions("client:join:add")
	@Log(title = "机主", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Join join) {		
		return toAjax(joinService.insertJoin(join));
	}

	/**
	 * 修改机主
	 */
	@GetMapping("/edit/{joinId}")
	public String edit(@PathVariable("joinId") Integer joinId, ModelMap mmap)
	{
		Join join = joinService.selectJoinById(joinId);
		mmap.put("join", join);
		List<User> payUserList = userService.selectUserListByUserType(UserConstants.USER_TYPE_ADVERTISE,
				UserConstants.USER_TYPE_AGENT,UserConstants.USER_TYPE_JOIN,UserConstants.USER_TYPE_REPAIR);
		mmap.put("payUserList", payUserList); // 购机推荐人
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存机主
	 */
	@RequiresPermissions("client:join:edit")
	@Log(title = "机主", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Join join) {		
		return toAjax(joinService.updateJoin(join));
	}
	/**
	 * 修改机主参数配置
	 */
	@GetMapping("/editParam/{joinId}")
	public String editParam(@PathVariable("joinId") Integer joinId, ModelMap mmap)
	{
		Join join = joinService.selectJoinById(joinId);
		mmap.put("join", join);
		return prefix + "/editParam";
	}
	
	/**
	 * 删除机主
	 */
	@RequiresPermissions("client:join:remove")
	@Log(title = "机主", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {		
		return toAjax(joinService.deleteJoinByIds(ids));
	}
	
	/**
	 * 查找机主下拉框数据
	 */
	@RequestMapping("/getDropBoxJoinList")
    @ResponseBody
    public TableDataInfo getDropBoxJoinList() {
		List<Join> list = joinService.selectDropBoxList();
		return getDataTable(list);
    }
	
}
