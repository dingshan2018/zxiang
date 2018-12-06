package com.zxiang.project.business.version.controller;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.zxiang.framework.aspectj.lang.annotation.Log;
import com.zxiang.framework.aspectj.lang.enums.BusinessType;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.domain.AjaxResult;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.project.business.version.domain.Version;
import com.zxiang.project.business.version.service.IVersionService;

/**
 * 版本 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-12-06
 */
@Controller
@RequestMapping("/business/version")
public class VersionController extends BaseController
{
    private String prefix = "business/version";
	
	@Autowired
	private IVersionService versionService;
	
	@RequiresPermissions("business:version:view")
	@GetMapping()
	public String version()
	{
	    return prefix + "/version";
	}
	
	/**
	 * 查询版本列表
	 */
	@RequiresPermissions("business:version:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Version version)
	{
		startPage();
        List<Version> list = versionService.selectVersionList(version);
		return getDataTable(list);
	}
	
	/**
	 * 新增版本
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存版本
	 */
	@RequiresPermissions("business:version:add")
	@Log(title = "版本", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Version version)
	{		
		return toAjax(versionService.insertVersion(version));
	}

	/**
	 * 修改版本
	 */
	@GetMapping("/edit/{sysVerId}")
	public String edit(@PathVariable("sysVerId") Integer sysVerId, ModelMap mmap)
	{
		Version version = versionService.selectVersionById(sysVerId);
		mmap.put("version", version);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存版本
	 */
	@RequiresPermissions("business:version:edit")
	@Log(title = "版本", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Version version)
	{		
		return toAjax(versionService.updateVersion(version));
	}
	
	/**
	 * 删除版本
	 */
	@RequiresPermissions("business:version:remove")
	@Log(title = "版本", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(versionService.deleteVersionByIds(ids));
	}
	
	/**
     * 更新包上传保存
     */
	@RequiresPermissions("business:version:add")
	@Log(title = "更新包上传保存", businessType = BusinessType.UPDATE)
	@RequestMapping(value = "/uploadSave", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult uploadSave(HttpServletRequest request)
    {
    	 try {
			String operatorUser = getUser().getUserName()+"("+getUserId()+")";	
			
			 List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
			 return toAjax(versionService.uploadSave(request,files,operatorUser));
		} catch (Exception e) {
			e.printStackTrace();
			return error();
		}
    }
}
