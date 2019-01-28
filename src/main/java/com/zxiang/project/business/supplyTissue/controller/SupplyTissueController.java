package com.zxiang.project.business.supplyTissue.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxiang.common.constant.UserConstants;
import com.zxiang.framework.aspectj.lang.annotation.DataFilter;
import com.zxiang.framework.aspectj.lang.annotation.Log;
import com.zxiang.framework.aspectj.lang.enums.BusinessType;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.domain.AjaxResult;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.project.business.device.service.IDeviceService;
import com.zxiang.project.business.place.service.IPlaceService;
import com.zxiang.project.business.supplyTissue.domain.SupplyTissue;
import com.zxiang.project.business.supplyTissue.service.ISupplyTissueService;
import com.zxiang.project.system.user.domain.User;
import com.zxiang.project.system.user.service.IUserService;

/**
 * 补纸记录 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
@Controller
@RequestMapping("/business/supplyTissue")
public class SupplyTissueController extends BaseController
{
    private String prefix = "business/supplyTissue";
    
	@Autowired
	private ISupplyTissueService supplyTissueService;
	
	@Autowired 
	private IPlaceService placeService;
	@Autowired
	private IDeviceService deviceService;
	@Autowired
	private IUserService userService;
	
	@RequiresPermissions("business:supplyTissue:view")
	@GetMapping()
	public String supplyTissue()
	{
	    return prefix + "/supplyTissue";
	}
	
	/**
	 * 查询补纸记录列表
	 */
	@DataFilter(placeAlias="zst.place_id")
	@RequiresPermissions("business:supplyTissue:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SupplyTissue supplyTissue)
	{
		startPage();
		User user =getUser();
		String userType = user.getUserType();
		if(userType.equals(UserConstants.USER_TYPE_JOIN)) {
			supplyTissue.setUserId(user.getUserId()+"");
		}
        List<SupplyTissue> list = supplyTissueService.selectSupplyTissueList(supplyTissue);
		return getDataTable(list);
	}
	
	/**
	 * 新增补纸记录
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap)
	{
		User queryUser = new User();
		queryUser.setUserType(UserConstants.USER_TYPE_REPAIR);
		List<User> userList = userService.selectUserList(queryUser);
		mmap.put("userList", userList);
		
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存补纸记录
	 */
	@RequiresPermissions("business:supplyTissue:add")
	@Log(title = "补纸记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SupplyTissue supplyTissue)
	{		
		String createor = getUser().getUserName();
		long userId = getUserId();
		
		supplyTissue.setCreateBy(createor+"("+userId+")");
		supplyTissue.setCreateTime(new Date());
		return toAjax(supplyTissueService.supplyTissueSave(supplyTissue));
	}

	/**
	 * 修改补纸记录
	 */
	@GetMapping("/edit/{supplyTissueId}")
	public String edit(@PathVariable("supplyTissueId") Integer supplyTissueId, ModelMap mmap)
	{
		SupplyTissue supplyTissue = supplyTissueService.selectSupplyTissueById(supplyTissueId);
		mmap.put("supplyTissue", supplyTissue);
		mmap.put("deviceDropBoxList", deviceService.selectDropBoxList(null));
		mmap.put("placeDropBoxList", placeService.selectDropBoxList());
		
		User queryUser = new User();
		queryUser.setUserType(UserConstants.USER_TYPE_REPAIR);
		List<User> userList = userService.selectUserList(queryUser);
		mmap.put("userList", userList);
		
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存补纸记录
	 */
	@RequiresPermissions("business:supplyTissue:edit")
	@Log(title = "补纸记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SupplyTissue supplyTissue)
	{		
		return toAjax(supplyTissueService.updateSupplyTissue(supplyTissue));
	}
	
	/**
	 * 删除补纸记录
	 */
	@RequiresPermissions("business:supplyTissue:remove")
	@Log(title = "补纸记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(supplyTissueService.deleteSupplyTissueByIds(ids));
	}
	
	/**
	 * 导出Excel
	 * 
	 */
	@DataFilter(placeAlias="zst.place_id")
	@RequestMapping("/excelExport")
	public void excelExport(@RequestParam HashMap<String, String> params, 
			HttpServletResponse response,HttpServletRequest request){
		try {
			User user =getUser();
			String userType = user.getUserType();
			if(userType.equals(UserConstants.USER_TYPE_JOIN)) {
				params.put("userId", user.getUserId()+"");
			}
			supplyTissueService.queryExport(params, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
