package com.zxiang.project.record.tradeOrder.controller;

import java.util.ArrayList;
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
import com.zxiang.project.business.device.domain.Device;
import com.zxiang.project.record.tradeOrder.domain.TradeOrder;
import com.zxiang.project.record.tradeOrder.service.ITradeOrderService;

/**
 * 订单 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-11-15
 */
@Controller
@RequestMapping("/settle/tradeOrder")
public class TradeOrderController extends BaseController
{
    private String prefix = "settle/tradeOrder";
	
	@Autowired
	private ITradeOrderService tradeOrderService;
	
	@RequiresPermissions("settle:tradeOrder:view")
	@GetMapping()
	public String tradeOrder()
	{
	    return prefix + "/tradeOrder";
	}
	
	/**
	 * 查询订单列表
	 */
	@RequiresPermissions("settle:tradeOrder:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TradeOrder tradeOrder)
	{
		startPage();
        List<TradeOrder> list = tradeOrderService.selectTradeOrderList(tradeOrder);
		return getDataTable(list);
	}
	
	/**
	 * 新增订单
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存订单
	 */
	@RequiresPermissions("settle:tradeOrder:add")
	@Log(title = "订单", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TradeOrder tradeOrder)
	{		
		return toAjax(tradeOrderService.insertTradeOrder(tradeOrder));
	}

	/**
	 * 修改订单
	 */
	@GetMapping("/edit/{tradeOrderId}")
	public String edit(@PathVariable("tradeOrderId") Integer tradeOrderId, ModelMap mmap)
	{
		TradeOrder tradeOrder = tradeOrderService.selectTradeOrderById(tradeOrderId);
		mmap.put("tradeOrder", tradeOrder);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存订单
	 */
	@RequiresPermissions("settle:tradeOrder:edit")
	@Log(title = "订单", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TradeOrder tradeOrder)
	{		
		return toAjax(tradeOrderService.updateTradeOrder(tradeOrder));
	}
	
	/**
	 * 删除订单
	 */
	@RequiresPermissions("settle:tradeOrder:remove")
	@Log(title = "订单", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(tradeOrderService.deleteTradeOrderByIds(ids));
	}
	
	/**
	 * 根据订单ID查找数据
	 */
	@RequestMapping("/getTradeById")
    @ResponseBody
    public TableDataInfo getTradeById(@RequestBody Map<String, Object> params) {
		String tradeId = (String) params.get("tradeId");
		List<TradeOrder> list = new ArrayList<>();
		TradeOrder tradeOrder = tradeOrderService.selectTradeOrderById(Integer.parseInt(tradeId));
		list.add(tradeOrder);
		return getDataTable(list);
    }
}
