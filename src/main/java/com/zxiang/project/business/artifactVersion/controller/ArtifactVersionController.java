package com.zxiang.project.business.artifactVersion.controller;

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
import com.zxiang.project.business.artifactVersion.domain.ArtifactVersion;
import com.zxiang.project.business.artifactVersion.service.IArtifactVersionService;

/**
 * 系统组件 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-12-06
 */
@Controller
@RequestMapping("/business/artifactVersion")
public class ArtifactVersionController extends BaseController
{
    private String prefix = "business/artifactVersion";
	
	@Autowired
	private IArtifactVersionService artifactVersionService;
	
	@RequiresPermissions("business:artifactVersion:view")
	@GetMapping()
	public String artifactVersion()
	{
	    return prefix + "/artifactVersion";
	}
	
	/**
	 * 查询系统组件列表
	 */
	@RequiresPermissions("business:artifactVersion:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ArtifactVersion artifactVersion)
	{
		startPage();
        List<ArtifactVersion> list = artifactVersionService.selectArtifactVersionList(artifactVersion);
		return getDataTable(list);
	}
	
	/**
	 * 新增系统组件
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存系统组件
	 */
	@RequiresPermissions("business:artifactVersion:add")
	@Log(title = "系统组件", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ArtifactVersion artifactVersion)
	{		
		return toAjax(artifactVersionService.insertArtifactVersion(artifactVersion));
	}

	/**
	 * 修改系统组件
	 */
	@GetMapping("/edit/{artifactVerId}")
	public String edit(@PathVariable("artifactVerId") Integer artifactVerId, ModelMap mmap)
	{
		ArtifactVersion artifactVersion = artifactVersionService.selectArtifactVersionById(artifactVerId);
		mmap.put("artifactVersion", artifactVersion);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存系统组件
	 */
	@RequiresPermissions("business:artifactVersion:edit")
	@Log(title = "系统组件", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ArtifactVersion artifactVersion)
	{		
		return toAjax(artifactVersionService.updateArtifactVersion(artifactVersion));
	}
	
	/**
	 * 删除系统组件
	 */
	@RequiresPermissions("business:artifactVersion:remove")
	@Log(title = "系统组件", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(artifactVersionService.deleteArtifactVersionByIds(ids));
	}
	
}
