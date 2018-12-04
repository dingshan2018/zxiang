package com.zxiang.project.client.advertise.controller;

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

import com.zxiang.framework.aspectj.lang.annotation.DataFilter;
import com.zxiang.framework.aspectj.lang.annotation.Log;
import com.zxiang.framework.aspectj.lang.enums.BusinessType;
import com.zxiang.project.client.advertise.domain.Advertise;
import com.zxiang.project.client.advertise.service.IAdvertiseService;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.framework.web.domain.AjaxResult;

/**
 * 广告商 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Controller
@RequestMapping("/client/advertise")
public class AdvertiseController extends BaseController
{
    private String prefix = "client/advertise";
	
	@Autowired
	private IAdvertiseService advertiseService;
	
	@RequiresPermissions("client:advertise:view")
	@GetMapping()
	public String advertise()
	{
	    return prefix + "/advertise";
	}
	
	/**
	 * 查询广告商列表
	 */
	@DataFilter(personAlias="b.user_id")
	@RequiresPermissions("client:advertise:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Advertise advertise)
	{
		startPage();
        List<Advertise> list = advertiseService.selectAdvertiseList(advertise);
		return getDataTable(list);
	}
	
	/**
	 * 新增广告商
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存广告商
	 */
	@RequiresPermissions("client:advertise:add")
	@Log(title = "广告商", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Advertise advertise)
	{		
		return toAjax(advertiseService.insertAdvertise(advertise));
	}

	/**
	 * 修改广告商
	 */
	@GetMapping("/edit/{advertiseId}")
	public String edit(@PathVariable("advertiseId") Integer advertiseId, ModelMap mmap)
	{
		Advertise advertise = advertiseService.selectAdvertiseById(advertiseId);
		mmap.put("advertise", advertise);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存广告商
	 */
	@RequiresPermissions("client:advertise:edit")
	@Log(title = "广告商", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Advertise advertise)
	{		
		return toAjax(advertiseService.updateAdvertise(advertise));
	}
	
	/**
	 * 删除广告商
	 */
	@RequiresPermissions("client:advertise:remove")
	@Log(title = "广告商", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(advertiseService.deleteAdvertiseByIds(ids));
	}
	
	/**
	 * 查找广告商下拉框数据
	 */
	@RequestMapping("/getDropBoxAdvertiseList")
    @ResponseBody
    public TableDataInfo getDropBoxJoinList() {
		List<Advertise> list = advertiseService.selectDropBoxList();
		return getDataTable(list);
    }
}
