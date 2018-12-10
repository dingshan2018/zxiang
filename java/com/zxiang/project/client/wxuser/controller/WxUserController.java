package com.zxiang.project.client.wxuser.controller;

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
import com.zxiang.project.client.wxuser.domain.WxUser;
import com.zxiang.project.client.wxuser.service.IWxUserService;

/**
 * 微信粉丝 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-11-03
 */
@Controller
@RequestMapping("/client/wxUser")
public class WxUserController extends BaseController
{
    private String prefix = "client/wxuser";
	
	@Autowired
	private IWxUserService wxUserService;
	
	@RequiresPermissions("client:wxUser:view")
	@GetMapping()
	public String wxUser()
	{
	    return prefix + "/wxuser";
	}
	
	/**
	 * 查询微信粉丝列表
	 */
	@RequiresPermissions("client:wxUser:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WxUser wxUser)
	{
		startPage();
        List<WxUser> list = wxUserService.selectWxUserList(wxUser);
		return getDataTable(list);
	}
	
	/**
	 * 新增微信粉丝
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存微信粉丝
	 */
	@RequiresPermissions("client:wxUser:add")
	@Log(title = "微信粉丝", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(WxUser wxUser)
	{		
		return toAjax(wxUserService.insertWxUser(wxUser));
	}

	/**
	 * 修改微信粉丝
	 */
	@GetMapping("/edit/{wxUserId}")
	public String edit(@PathVariable("wxUserId") Integer wxUserId, ModelMap mmap)
	{
		WxUser wxUser = wxUserService.selectWxUserById(wxUserId);
		mmap.put("wxUser", wxUser);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存微信粉丝
	 */
	@RequiresPermissions("client:wxUser:edit")
	@Log(title = "微信粉丝", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WxUser wxUser)
	{		
		return toAjax(wxUserService.updateWxUser(wxUser));
	}
	
	/**
	 * 删除微信粉丝
	 */
	@RequiresPermissions("client:wxUser:remove")
	@Log(title = "微信粉丝", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(wxUserService.deleteWxUserByIds(ids));
	}
	
}
