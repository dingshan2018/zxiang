package com.zxiang.project.advertise.adResource.controller;

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
import com.zxiang.project.advertise.adResource.domain.AdResource;
import com.zxiang.project.advertise.adResource.service.IAdResourceService;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.framework.web.domain.AjaxResult;

/**
 * 素材维护 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Controller
@RequestMapping("/advertise/adResource")
public class AdResourceController extends BaseController
{
    private String prefix = "advertise/adResource";
	
	@Autowired
	private IAdResourceService adResourceService;
	
	@RequiresPermissions("advertise:adResource:view")
	@GetMapping()
	public String adResource()
	{
	    return prefix + "/adResource";
	}
	
	/**
	 * 查询素材维护列表
	 */
	@RequiresPermissions("advertise:adResource:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(AdResource adResource)
	{
		startPage();
        List<AdResource> list = adResourceService.selectAdResourceList(adResource);
		return getDataTable(list);
	}
	
	/**
	 * 新增素材维护
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存素材维护
	 */
	@RequiresPermissions("advertise:adResource:add")
	@Log(title = "素材维护", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AdResource adResource)
	{		
		return toAjax(adResourceService.insertAdResource(adResource));
	}

	/**
	 * 修改素材维护
	 */
	@GetMapping("/edit/{resourceId}")
	public String edit(@PathVariable("resourceId") String resourceId, ModelMap mmap)
	{
		AdResource adResource = adResourceService.selectAdResourceById(resourceId);
		mmap.put("adResource", adResource);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存素材维护
	 */
	@RequiresPermissions("advertise:adResource:edit")
	@Log(title = "素材维护", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(AdResource adResource)
	{		
		return toAjax(adResourceService.updateAdResource(adResource));
	}
	
	/**
	 * 删除素材维护
	 */
	@RequiresPermissions("advertise:adResource:remove")
	@Log(title = "素材维护", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(adResourceService.deleteAdResourceByIds(ids));
	}
	
}
