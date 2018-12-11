package com.zxiang.project.advertise.adReleaseRange.controller;

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
import com.zxiang.project.advertise.adReleaseRange.domain.AdReleaseRange;
import com.zxiang.project.advertise.adReleaseRange.service.IAdReleaseRangeService;

/**
 * 广告投放范围 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-11-07
 */
@Controller
@RequestMapping("/settle/adReleaseRange")
public class AdReleaseRangeController extends BaseController
{
    private String prefix = "settle/adReleaseRange";
	
	@Autowired
	private IAdReleaseRangeService adReleaseRangeService;
	
	@RequiresPermissions("settle:adReleaseRange:view")
	@GetMapping()
	public String adReleaseRange()
	{
	    return prefix + "/adReleaseRange";
	}
	
	/**
	 * 查询广告投放范围列表
	 */
	@RequiresPermissions("settle:adReleaseRange:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(AdReleaseRange adReleaseRange)
	{
		startPage();
        List<AdReleaseRange> list = adReleaseRangeService.selectAdReleaseRangeList(adReleaseRange);
		return getDataTable(list);
	}
	
	/**
	 * 新增广告投放范围
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存广告投放范围
	 */
	@RequiresPermissions("settle:adReleaseRange:add")
	@Log(title = "广告投放范围", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AdReleaseRange adReleaseRange)
	{		
		return toAjax(adReleaseRangeService.insertAdReleaseRange(adReleaseRange));
	}

	/**
	 * 修改广告投放范围
	 */
	@GetMapping("/edit/{adReleaseRangeId}")
	public String edit(@PathVariable("adReleaseRangeId") Integer adReleaseRangeId, ModelMap mmap)
	{
		AdReleaseRange adReleaseRange = adReleaseRangeService.selectAdReleaseRangeById(adReleaseRangeId);
		mmap.put("adReleaseRange", adReleaseRange);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存广告投放范围
	 */
	@RequiresPermissions("settle:adReleaseRange:edit")
	@Log(title = "广告投放范围", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(AdReleaseRange adReleaseRange)
	{		
		return toAjax(adReleaseRangeService.updateAdReleaseRange(adReleaseRange));
	}
	
	/**
	 * 删除广告投放范围
	 */
	@RequiresPermissions("settle:adReleaseRange:remove")
	@Log(title = "广告投放范围", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(adReleaseRangeService.deleteAdReleaseRangeByIds(ids));
	}
	
	/**
     *	查询是否有投放设备,有则预约过
     */
    @PostMapping("/getOrderNum/{adScheduleId}")
    @ResponseBody
    public int getOrderNum(@PathVariable("adScheduleId") Integer adScheduleId)
    {
    	int orderNum = adReleaseRangeService.getOrderNum(adScheduleId);
        return orderNum;
    }
    
	
}
