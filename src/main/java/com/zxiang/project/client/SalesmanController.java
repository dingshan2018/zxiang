package com.zxiang.project.client;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxiang.common.constant.UserConstants;
import com.zxiang.common.utils.StringUtils;
import com.zxiang.common.utils.poi.ExcelUtil;
import com.zxiang.framework.aspectj.lang.annotation.Log;
import com.zxiang.framework.aspectj.lang.enums.BusinessType;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.domain.AjaxResult;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.project.system.post.service.IPostService;
import com.zxiang.project.system.role.service.IRoleService;
import com.zxiang.project.system.user.domain.User;
import com.zxiang.project.system.user.service.IUserService;

/**
 * 业务员信息
 * 
 * @author zxiang
 */
@Controller
@RequestMapping("/client/salesman")
public class SalesmanController extends BaseController
{
    private String prefix = "client/salesman";

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPostService postService;

    @RequiresPermissions("client:salesman:view")
    @GetMapping("/{userType}/{puserId}") // chiendType:业务员类型   业务员对应主体id
    public String salesman(@PathVariable String userType,@PathVariable Integer puserId, ModelMap mmap) {
    	mmap.put("userType", userType);
    	mmap.put("puserId", puserId);
    	return prefix + "/salesman";
    }

    @RequiresPermissions("client:salesman:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(User user)
    {
        startPage();
        List<User> list = userService.selectUserList(user);
        return getDataTable(list);
    }
    /**
	 * 新增业务员
	 */
	@GetMapping("/toAddSalesman/{userType}/{puserId}")
	public String toAddSalesman(@PathVariable String userType,@PathVariable Integer puserId, ModelMap mmap) {
		mmap.put("userType", userType);
		mmap.put("puserId", puserId);
		mmap.put("cliendName", userService.saleManClent(userType, puserId));
		mmap.put("roles", roleService.selectRoleAll());
		return prefix+"/addSalesman";
	}
    @Log(title = "业务员管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("client:salesman:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(User user) throws Exception
    {
        try
        {
            List<User> list = userService.selectUserList(user);
            ExcelUtil<User> util = new ExcelUtil<User>(User.class);
            return util.exportExcel(list, "user");
        }
        catch (Exception e)
        {
            return error("导出Excel失败，请联系网站管理员！");
        }
    }

    /**
     * 新增业务员
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        mmap.put("roles", roleService.selectRoleAll());
        mmap.put("posts", postService.selectPostAll());
        List<User> userList = userService.selectUserListByUserType(UserConstants.USER_TYPE_ADVERTISE,
				UserConstants.USER_TYPE_AGENT,UserConstants.USER_TYPE_JOIN,UserConstants.USER_TYPE_REPAIR);
		mmap.put("userList", userList);
        return prefix + "/add";
    }

    /**
     * 新增保存业务员
     */
    @RequiresPermissions("client:salesman:add")
    @Log(title = "业务员管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public AjaxResult addSave(User user)
    {
        if (StringUtils.isNotNull(user.getUserId()) && User.isAdmin(user.getUserId()))
        {
            return error("不允许修改超级管理员业务员");
        }
        return toAjax(userService.insertUser(user));
    }

    /**
     * 修改业务员
     */
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId, ModelMap mmap) {
    	User user = userService.selectUserById(userId);
    	mmap.put("user", user);
    	if(user.getUserType().startsWith("1")) { // 1开头表示业务员
    		mmap.put("clientName", userService.saleManClent(user.getUserType(), user.getPuserId()));
    	}
        mmap.put("roles", roleService.selectRolesByUserId(userId));
        mmap.put("posts", postService.selectPostsByUserId(userId));
        List<User> userList = userService.selectUserListByUserType(UserConstants.USER_TYPE_ADVERTISE,
				UserConstants.USER_TYPE_AGENT,UserConstants.USER_TYPE_JOIN,UserConstants.USER_TYPE_REPAIR);
		mmap.put("userList", userList);
        return prefix + "/edit";
    }

    /**
     * 修改保存业务员
     */
    @RequiresPermissions("client:salesman:edit")
    @Log(title = "业务员管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public AjaxResult editSave(User user)  {
        if (StringUtils.isNotNull(user.getUserId()) && User.isAdmin(user.getUserId())) {
            return error("不允许修改超级管理员业务员");
        }
        return toAjax(userService.updateUser(user));
    }

    @RequiresPermissions("client:salesman:remove")
    @Log(title = "业务员管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        try
        {
            return toAjax(userService.deleteUserByIds(ids));
        }
        catch (Exception e)
        {
            return error(e.getMessage());
        }
    }
}