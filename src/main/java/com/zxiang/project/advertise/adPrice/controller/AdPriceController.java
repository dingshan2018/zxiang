package com.zxiang.project.advertise.adPrice.controller;

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
import com.zxiang.project.advertise.adPrice.domain.AdPrice;
import com.zxiang.project.advertise.adPrice.service.IAdPriceService;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.framework.web.domain.AjaxResult;

/**
 * 投放价格 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Controller
@RequestMapping("/advertise/adPrice")
public class AdPriceController extends BaseController
{
    private String prefix = "advertise/adPrice";
	
	@Autowired
	private IAdPriceService adPriceService;
	
	@RequiresPermissions("advertise:adPrice:view")
	@GetMapping()
	public String adPrice()
	{
	    return prefix + "/adPrice";
	}
	
	/**
	 * 查询投放价格列表
	 */
	@RequiresPermissions("advertise:adPrice:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(AdPrice adPrice)
	{
		startPage();
        List<AdPrice> list = adPriceService.selectAdPriceList(adPrice);
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
	@RequiresPermissions("advertise:adPrice:add")
	@Log(title = "投放价格", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AdPrice adPrice)
	{		
		return toAjax(adPriceService.insertAdPrice(adPrice));
	}

	/**
	 * 修改投放价格
	 */
	@GetMapping("/edit/{priceId}")
	public String edit(@PathVariable("priceId") Integer priceId, ModelMap mmap)
	{
		AdPrice adPrice = adPriceService.selectAdPriceById(priceId);
		mmap.put("adPrice", adPrice);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存投放价格
	 */
	@RequiresPermissions("advertise:adPrice:edit")
	@Log(title = "投放价格", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(AdPrice adPrice)
	{		
		return toAjax(adPriceService.updateAdPrice(adPrice));
	}
	
	/**
	 * 删除投放价格
	 */
	@RequiresPermissions("advertise:adPrice:remove")
	@Log(title = "投放价格", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(adPriceService.deleteAdPriceByIds(ids));
	}
	
}
