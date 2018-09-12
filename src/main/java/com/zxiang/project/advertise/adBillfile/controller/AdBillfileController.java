package com.zxiang.project.advertise.adBillfile.controller;

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
import com.zxiang.project.advertise.adBillfile.domain.AdBillfile;
import com.zxiang.project.advertise.adBillfile.service.IAdBillfileService;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.framework.web.domain.AjaxResult;

/**
 * 节目单所生成的文件 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Controller
@RequestMapping("/advertise/adBillfile")
public class AdBillfileController extends BaseController
{
    private String prefix = "advertise/adBillfile";
	
	@Autowired
	private IAdBillfileService adBillfileService;
	
	@RequiresPermissions("advertise:adBillfile:view")
	@GetMapping()
	public String adBillfile()
	{
	    return prefix + "/adBillfile";
	}
	
	/**
	 * 查询节目单所生成的文件列表
	 */
	@RequiresPermissions("advertise:adBillfile:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(AdBillfile adBillfile)
	{
		startPage();
        List<AdBillfile> list = adBillfileService.selectAdBillfileList(adBillfile);
		return getDataTable(list);
	}
	
	/**
	 * 新增节目单所生成的文件
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存节目单所生成的文件
	 */
	@RequiresPermissions("advertise:adBillfile:add")
	@Log(title = "节目单所生成的文件", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AdBillfile adBillfile)
	{		
		return toAjax(adBillfileService.insertAdBillfile(adBillfile));
	}

	/**
	 * 修改节目单所生成的文件
	 */
	@GetMapping("/edit/{billfileId}")
	public String edit(@PathVariable("billfileId") String billfileId, ModelMap mmap)
	{
		AdBillfile adBillfile = adBillfileService.selectAdBillfileById(billfileId);
		mmap.put("adBillfile", adBillfile);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存节目单所生成的文件
	 */
	@RequiresPermissions("advertise:adBillfile:edit")
	@Log(title = "节目单所生成的文件", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(AdBillfile adBillfile)
	{		
		return toAjax(adBillfileService.updateAdBillfile(adBillfile));
	}
	
	/**
	 * 删除节目单所生成的文件
	 */
	@RequiresPermissions("advertise:adBillfile:remove")
	@Log(title = "节目单所生成的文件", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(adBillfileService.deleteAdBillfileByIds(ids));
	}
	
}
