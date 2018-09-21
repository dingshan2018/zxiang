package com.zxiang.project.system.area.controller;

import java.util.List;
import java.util.Map;

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
import com.zxiang.framework.aspectj.lang.annotation.Log;
import com.zxiang.framework.aspectj.lang.enums.BusinessType;
import com.zxiang.project.system.area.domain.Area;
import com.zxiang.project.system.area.service.IAreaService;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.framework.web.domain.AjaxResult;

/**
 * 公共：区域 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-21
 */
@Controller
@RequestMapping("/system/area")
public class AreaController extends BaseController
{
    private String prefix = "system/area";
	
	@Autowired
	private IAreaService areaService;
	
	@RequiresPermissions("system:area:view")
	@GetMapping()
	public String area()
	{
	    return prefix + "/area";
	}
	
	/**
	 * 查询公共：区域列表
	 */
	@RequiresPermissions("system:area:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Area area)
	{
		startPage();
        List<Area> list = areaService.selectAreaList(area);
		return getDataTable(list);
	}
	
	/**
	 * 新增公共：区域
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存公共：区域
	 */
	@RequiresPermissions("system:area:add")
	@Log(title = "公共：区域", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Area area)
	{		
		return toAjax(areaService.insertArea(area));
	}

	/**
	 * 修改公共：区域
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		Area area = areaService.selectAreaById(id);
		mmap.put("area", area);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存公共：区域
	 */
	@RequiresPermissions("system:area:edit")
	@Log(title = "公共：区域", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Area area)
	{		
		return toAjax(areaService.updateArea(area));
	}
	
	/**
	 * 删除公共：区域
	 */
	@RequiresPermissions("system:area:remove")
	@Log(title = "公共：区域", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(areaService.deleteAreaByIds(ids));
	}
	
	@RequestMapping("/getDropBoxAreaList")
    @ResponseBody
    public TableDataInfo getDropBoxAreaList(@RequestBody Map<String, Object> params) {
		String parentId = (String) params.get("parentId");
		System.out.println("parentId:"+parentId);
		List<Area> list = areaService.selectDropBoxList(Long.parseLong(parentId));
		System.out.println("list:"+list.size());
		return getDataTable(list);
    }
}
