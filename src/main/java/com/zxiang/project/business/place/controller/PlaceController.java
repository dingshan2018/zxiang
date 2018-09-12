package com.zxiang.project.business.place.controller;

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
import com.zxiang.project.business.place.domain.Place;
import com.zxiang.project.business.place.service.IPlaceService;

/**
 * 场所管理 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
@Controller
@RequestMapping("/business/place")
public class PlaceController extends BaseController
{
    private String prefix = "business/place";
	
	@Autowired
	private IPlaceService placeService;
	
	@RequiresPermissions("business:place:view")
	@GetMapping()
	public String place()
	{
	    return prefix + "/place";
	}
	
	/**
	 * 查询场所管理列表
	 */
	@RequiresPermissions("business:place:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Place place)
	{
		startPage();
        List<Place> list = placeService.selectPlaceList(place);
		return getDataTable(list);
	}
	
	/**
	 * 新增场所管理
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存场所管理
	 */
	@RequiresPermissions("business:place:add")
	@Log(title = "场所管理", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Place place)
	{		
		return toAjax(placeService.insertPlace(place));
	}

	/**
	 * 修改场所管理
	 */
	@GetMapping("/edit/{placeId}")
	public String edit(@PathVariable("placeId") Integer placeId, ModelMap mmap)
	{
		Place place = placeService.selectPlaceById(placeId);
		mmap.put("place", place);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存场所管理
	 */
	@RequiresPermissions("business:place:edit")
	@Log(title = "场所管理", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Place place)
	{		
		return toAjax(placeService.updatePlace(place));
	}
	
	/**
	 * 删除场所管理
	 */
	@RequiresPermissions("business:place:remove")
	@Log(title = "场所管理", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(placeService.deletePlaceByIds(ids));
	}
	
}
