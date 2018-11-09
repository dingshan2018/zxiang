package com.zxiang.project.advertise.adMaterial.controller;

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
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.domain.AjaxResult;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.project.advertise.adMaterial.domain.AdMaterial;
import com.zxiang.project.advertise.adMaterial.service.IAdMaterialService;

/**
 * 广告投放素材 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-11-08
 */
@Controller
@RequestMapping("/settle/adMaterial")
public class AdMaterialController extends BaseController
{
    private String prefix = "settle/adMaterial";
	
	@Autowired
	private IAdMaterialService adMaterialService;
	
	@RequiresPermissions("settle:adMaterial:view")
	@GetMapping()
	public String adMaterial()
	{
	    return prefix + "/adMaterial";
	}
	
	/**
	 * 查询广告投放素材列表
	 */
	@RequiresPermissions("settle:adMaterial:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(AdMaterial adMaterial)
	{
		startPage();
        List<AdMaterial> list = adMaterialService.selectAdMaterialList(adMaterial);
		return getDataTable(list);
	}
	
	/**
	 * 新增广告投放素材
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存广告投放素材
	 */
	@RequiresPermissions("settle:adMaterial:add")
	@Log(title = "广告投放素材", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AdMaterial adMaterial)
	{		
		return toAjax(adMaterialService.insertAdMaterial(adMaterial));
	}

	/**
	 * 修改广告投放素材
	 */
	@GetMapping("/edit/{adMaterialId}")
	public String edit(@PathVariable("adMaterialId") Integer adMaterialId, ModelMap mmap)
	{
		AdMaterial adMaterial = adMaterialService.selectAdMaterialById(adMaterialId);
		mmap.put("adMaterial", adMaterial);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存广告投放素材
	 */
	@RequiresPermissions("settle:adMaterial:edit")
	@Log(title = "广告投放素材", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(AdMaterial adMaterial)
	{		
		return toAjax(adMaterialService.updateAdMaterial(adMaterial));
	}
	
	/**
	 * 删除广告投放素材
	 */
	@RequiresPermissions("settle:adMaterial:remove")
	@Log(title = "广告投放素材", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(adMaterialService.deleteAdMaterialByIds(ids));
	}
	
	/**
     *	查询素材表最大上传批次号
     */
    @PostMapping("/getMaxBatch/{adScheduleId}")
    @ResponseBody
    public int getMaxBatch(@PathVariable("adScheduleId") Integer adScheduleId)
    {
        return adMaterialService.getMaxBatch(adScheduleId);
    }
    
    /**
	 * 根据广告ID查询最新批次素材列表
	 */
	@RequestMapping("/selectListByAdSchId")
    @ResponseBody
    public TableDataInfo selectListByAdSchId(@RequestBody Map<String, Object> params) {
		String adScheduleId = (String) params.get("adScheduleId");
		List<AdMaterial> list = adMaterialService.selectListByAdSchId(Integer.parseInt(adScheduleId));
		return getDataTable(list);
    }
    
}
