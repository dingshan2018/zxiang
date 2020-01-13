package com.zxiang.project.advertise.releaseDetail.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import com.zxiang.project.advertise.releaseDetail.domain.ReleaseDetail;
import com.zxiang.project.advertise.releaseDetail.service.IReleaseDetailService;

/**
 * 广告播放明细 信息操作处理
 * 
 * @author ZXiang
 * @date 2020-01-11
 */
@Controller
@RequestMapping("/advertise/releaseDetail")
public class ReleaseDetailController extends BaseController
{
    private String prefix = "advertise/releaseDetail";
	
	@Autowired
	private IReleaseDetailService releaseDetailService;
	
	@RequiresPermissions("settle:releaseDetail:view")
	@GetMapping()
	public String releaseDetail()
	{
	    return prefix + "/releaseDetail";
	}
	
	/**
	 * 查询广告播放明细列表
	 */
	@RequiresPermissions("settle:releaseDetail:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ReleaseDetail releaseDetail)
	{
		startPage();
		if(releaseDetail.getParams()!=null && releaseDetail.getParams().containsKey("beginTime") && StringUtils.isNotBlank(releaseDetail.getParams().get("beginTime")+"")) {
			releaseDetail.getParams().put("beginTime", releaseDetail.getParams().get("beginTime")+" 00:00:00");
		}
		if(releaseDetail.getParams()!=null && releaseDetail.getParams().containsKey("endTime") && StringUtils.isNotBlank(releaseDetail.getParams().get("endTime")+"")) {
			releaseDetail.getParams().put("endTime", releaseDetail.getParams().get("endTime")+" 23:59:59");
		}
        List<ReleaseDetail> list = releaseDetailService.selectReleaseDetailList(releaseDetail);
		return getDataTable(list);
	}
	
	/**
	 * 新增广告播放明细
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存广告播放明细
	 */
	@RequiresPermissions("settle:releaseDetail:add")
	@Log(title = "广告播放明细", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ReleaseDetail releaseDetail)
	{		
		return toAjax(releaseDetailService.insertReleaseDetail(releaseDetail));
	}

	/**
	 * 修改广告播放明细
	 */
	@GetMapping("/edit/{releaseDetailId}")
	public String edit(@PathVariable("releaseDetailId") Integer releaseDetailId, ModelMap mmap)
	{
		ReleaseDetail releaseDetail = releaseDetailService.selectReleaseDetailById(releaseDetailId);
		mmap.put("releaseDetail", releaseDetail);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存广告播放明细
	 */
	@RequiresPermissions("settle:releaseDetail:edit")
	@Log(title = "广告播放明细", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ReleaseDetail releaseDetail)
	{		
		return toAjax(releaseDetailService.updateReleaseDetail(releaseDetail));
	}
	
	/**
	 * 删除广告播放明细
	 */
	@RequiresPermissions("settle:releaseDetail:remove")
	@Log(title = "广告播放明细", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(releaseDetailService.deleteReleaseDetailByIds(ids));
	}
	
}
