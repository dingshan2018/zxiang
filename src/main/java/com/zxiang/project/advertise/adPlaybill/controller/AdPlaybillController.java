package com.zxiang.project.advertise.adPlaybill.controller;

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
import com.zxiang.project.advertise.adPlaybill.domain.AdPlaybill;
import com.zxiang.project.advertise.adPlaybill.service.IAdPlaybillService;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.framework.web.domain.AjaxResult;

/**
 * 节目单 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Controller
@RequestMapping("/advertise/adPlaybill")
public class AdPlaybillController extends BaseController
{
    private String prefix = "advertise/adPlaybill";
	
	@Autowired
	private IAdPlaybillService adPlaybillService;
	
	@RequiresPermissions("advertise:adPlaybill:view")
	@GetMapping()
	public String adPlaybill()
	{
	    return prefix + "/adPlaybill";
	}
	
	/**
	 * 查询节目单列表
	 */
	@RequiresPermissions("advertise:adPlaybill:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(AdPlaybill adPlaybill)
	{
		startPage();
        List<AdPlaybill> list = adPlaybillService.selectAdPlaybillList(adPlaybill);
		return getDataTable(list);
	}
	
	/**
	 * 新增节目单
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存节目单
	 */
	@RequiresPermissions("advertise:adPlaybill:add")
	@Log(title = "节目单", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AdPlaybill adPlaybill)
	{		
		return toAjax(adPlaybillService.insertAdPlaybill(adPlaybill));
	}

	/**
	 * 修改节目单
	 */
	@GetMapping("/edit/{playbillId}")
	public String edit(@PathVariable("playbillId") String playbillId, ModelMap mmap)
	{
		AdPlaybill adPlaybill = adPlaybillService.selectAdPlaybillById(playbillId);
		mmap.put("adPlaybill", adPlaybill);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存节目单
	 */
	@RequiresPermissions("advertise:adPlaybill:edit")
	@Log(title = "节目单", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(AdPlaybill adPlaybill)
	{		
		return toAjax(adPlaybillService.updateAdPlaybill(adPlaybill));
	}
	
	/**
	 * 删除节目单
	 */
	@RequiresPermissions("advertise:adPlaybill:remove")
	@Log(title = "节目单", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(adPlaybillService.deleteAdPlaybillByIds(ids));
	}
	
}
