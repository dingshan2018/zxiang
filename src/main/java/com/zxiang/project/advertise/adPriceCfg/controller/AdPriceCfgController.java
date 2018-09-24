package com.zxiang.project.advertise.adPriceCfg.controller;

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
import com.zxiang.project.advertise.adPriceCfg.domain.AdPriceCfg;
import com.zxiang.project.advertise.adPriceCfg.service.IAdPriceCfgService;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.framework.web.domain.AjaxResult;

/**
 * 投放价格 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-25
 */
@Controller
@RequestMapping("/advertise/adPriceCfg")
public class AdPriceCfgController extends BaseController
{
    private String prefix = "advertise/adPriceCfg";
	
	@Autowired
	private IAdPriceCfgService adPriceCfgService;
	
	@RequiresPermissions("advertise:adPriceCfg:view")
	@GetMapping()
	public String adPriceCfg()
	{
	    return prefix + "/adPriceCfg";
	}
	
	/**
	 * 查询投放价格列表
	 */
	@RequiresPermissions("advertise:adPriceCfg:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(AdPriceCfg adPriceCfg)
	{
		startPage();
        List<AdPriceCfg> list = adPriceCfgService.selectAdPriceCfgList(adPriceCfg);
		return getDataTable(list);
	}
	
	/**
	 * 新增投放价格
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存投放价格
	 */
	@RequiresPermissions("advertise:adPriceCfg:add")
	@Log(title = "投放价格", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AdPriceCfg adPriceCfg)
	{		
		return toAjax(adPriceCfgService.insertAdPriceCfg(adPriceCfg));
	}

	/**
	 * 修改投放价格
	 */
	@GetMapping("/edit/{priceCfgId}")
	public String edit(@PathVariable("priceCfgId") Integer priceCfgId, ModelMap mmap)
	{
		AdPriceCfg adPriceCfg = adPriceCfgService.selectAdPriceCfgById(priceCfgId);
		mmap.put("adPriceCfg", adPriceCfg);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存投放价格
	 */
	@RequiresPermissions("advertise:adPriceCfg:edit")
	@Log(title = "投放价格", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(AdPriceCfg adPriceCfg)
	{		
		return toAjax(adPriceCfgService.updateAdPriceCfg(adPriceCfg));
	}
	
	/**
	 * 删除投放价格
	 */
	@RequiresPermissions("advertise:adPriceCfg:remove")
	@Log(title = "投放价格", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(adPriceCfgService.deleteAdPriceCfgByIds(ids));
	}
	
}
