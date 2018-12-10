package com.zxiang.project.settle.settlementParam.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxiang.common.utils.Query;
import com.zxiang.framework.aspectj.lang.annotation.Log;
import com.zxiang.framework.aspectj.lang.enums.BusinessType;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.domain.AjaxResult;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.project.settle.settlementParam.domain.SettlementParam;
import com.zxiang.project.settle.settlementParam.service.ISettlementParamService;

/**
 * 结算系数配置 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-25
 */
@Controller
@RequestMapping("/settle/settlementParam")
public class SettlementParamController extends BaseController
{
    private String prefix = "settle/settlementParam";
	
	@Autowired
	private ISettlementParamService settlementParamService;
	
	@RequiresPermissions("settle:settlementParam:view")
	@GetMapping()
	public String settlementParam()
	{
	    return prefix + "/settlementParam";
	}
	
	/**
	 * 查询结算系数配置列表
	 */
	@RequiresPermissions("settle:settlementParam:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SettlementParam settlementParam)
	{
		startPage();
        List<SettlementParam> list = settlementParamService.selectSettlementParamList(settlementParam);
		return getDataTable(list);
	}
	
	/**
	 * 新增结算系数配置
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存结算系数配置
	 */
	@RequiresPermissions("settle:settlementParam:add")
	@Log(title = "结算系数配置", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SettlementParam settlementParam)
	{		
		return toAjax(settlementParamService.insertSettlementParam(settlementParam));
	}

	/**
	 * 修改结算系数配置
	 */
	@GetMapping("/edit/{settlementParamId}")
	public String edit(@PathVariable("settlementParamId") Integer settlementParamId, ModelMap mmap)
	{
		SettlementParam settlementParam = settlementParamService.selectSettlementParamById(settlementParamId);
		mmap.put("settlementParam", settlementParam);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存结算系数配置
	 */
	@RequiresPermissions("settle:settlementParam:edit")
	@Log(title = "结算系数配置", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SettlementParam settlementParam)
	{		
		return toAjax(settlementParamService.updateSettlementParam(settlementParam));
	}
	
	/**
	 * 删除结算系数配置
	 */
	@RequiresPermissions("settle:settlementParam:remove")
	@Log(title = "结算系数配置", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(settlementParamService.deleteSettlementParamByIds(ids));
	}
	
	
	
	/**
	 * 场所列表
	 */
	@PostMapping("/selectzxplacelist")
	@ResponseBody
	public Map<String, Object> selectzxplacelist(@RequestParam Map<String, Object> params)
	{
		Query query = new Query(params);
		int totalCount = settlementParamService.queryplaceTotal(query);
		List<HashMap<String, Object>> list = settlementParamService.selectzxplace(query);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCount", totalCount);
		map.put("limit", params.get("limit"));
		map.put("page", params.get("page"));
		map.put("list", list);
		return map;
	}
	/**
	 * 广告计划列表
	 */
	@PostMapping("/selecadschedulelist")
	@ResponseBody
	public Map<String, Object> selecadschedulelist(@RequestParam Map<String, Object> params)
	{
		Query query = new Query(params);
		int totalCount = settlementParamService.queryadscheduleTotal(query);
		List<HashMap<String, Object>> list = settlementParamService.selecadschedulelist(query);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCount", totalCount);
		map.put("limit", params.get("limit"));
		map.put("page", params.get("page"));
		map.put("list", list);
		return map;
	}
	
	/**
	 * 广告计划点击排名
	 */
	@PostMapping("/scheduleStatistics")
	@ResponseBody
	public Map<String, Object> scheduleStatistics(@RequestParam Map<String, Object> params)
	{
		List<HashMap<String, Object>> list = settlementParamService.scheduleStatistics(params);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return map;
	}
	
	
	
	/**
	 * 出纸记录列表
	 */
	@PostMapping("/selectzxtissuerecordlist")
	@ResponseBody
	public Map<String, Object> selectzxtissuerecordlist(@RequestParam Map<String, Object> params)
	{
		Query query = new Query(params);
		int totalCount = settlementParamService.querytissuerecordTotal(query);
		List<HashMap<String, Object>> list = settlementParamService.selectzxtissuerecordlist(query);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCount", totalCount);
		map.put("limit", params.get("limit"));
		map.put("page", params.get("page"));
		map.put("list", list);
		return map;
	}
	
	
	/**
	 * 出纸场所排名
	 */
	@PostMapping("/tissuerecordStatistics")
	@ResponseBody
	public Map<String, Object> tissuerecordStatistics(@RequestParam Map<String, Object> params)
	{
		List<HashMap<String, Object>> list = settlementParamService.tissuerecordStatistics(params);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return map;
	}
	
	
	/**
	 * 收益统计列表
	 */
	@PostMapping("/selecuserincomelist")
	@ResponseBody
	public Map<String, Object> selecuserincomelist(@RequestParam Map<String, Object> params)
	{
		Query query = new Query(params);
		int totalCount = settlementParamService.queryuserincomeTotal(query);
		List<HashMap<String, Object>> list = settlementParamService.selecuserincomelist(query);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCount", totalCount);
		map.put("limit", params.get("limit"));
		map.put("page", params.get("page"));
		map.put("list", list);
		return map;
	}
	
	
	/**
	 * 设备列表
	 */
	@PostMapping("/selecdevicelist")
	@ResponseBody
	public Map<String, Object> selecdevicelist(@RequestParam Map<String, Object> params)
	{
		Query query = new Query(params);
		int totalCount = settlementParamService.querydeviceTotal(query);
		List<HashMap<String, Object>> list = settlementParamService.selecdevicelist(query);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCount", totalCount);
		map.put("limit", params.get("limit"));
		map.put("page", params.get("page"));
		map.put("list", list);
		return map;
	}
	
	
	/**
	 * 设备投放设备查询
	 */
	@PostMapping("/selectdeviceAll")
	@ResponseBody
	public Map<String, Object> selectdeviceAll(@RequestParam Map<String, Object> params)
	{
		List<HashMap<String, Object>> list = settlementParamService.selectdeviceAll(params);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return map;
	}
	
	/**
	 * 设备投放从场所查询
	 */
	@PostMapping("/selectplaceAll")
	@ResponseBody
	public Map<String, Object> selectplaceAll(@RequestParam Map<String, Object> params)
	{
		List<HashMap<String, Object>> list = settlementParamService.selectplaceAll(params);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return map;
	}
	
	
	/**
	 * 设备投放从场所查询
	 */
	@PostMapping("/deviceSave")
	@ResponseBody
	public Map<String, Object> deviceSave(@RequestParam Map<String, Object> params)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int i = settlementParamService.deviceSave(params);
			if(i>0) {
				map.put("code", "0000");
				map.put("msg", "操作成功");
			}else {
				map.put("code", "9999");
				map.put("msg", "操作失败");
			}
			
		} catch (Exception e) {
			map.put("code", "9999");
			map.put("msg", "操作失败");
		}
		return map;
	}
}
