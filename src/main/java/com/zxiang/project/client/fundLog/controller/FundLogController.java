package com.zxiang.project.client.fundLog.controller;

import java.io.IOException;
import java.math.BigDecimal;
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

import com.alibaba.fastjson.JSONObject;
import com.zxiang.common.utils.StringUtils;
import com.zxiang.framework.aspectj.lang.annotation.Log;
import com.zxiang.framework.aspectj.lang.enums.BusinessType;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.domain.AjaxResult;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.project.advertise.utils.AdHttpResult;
import com.zxiang.project.advertise.utils.Tools;
import com.zxiang.project.advertise.utils.constant.AdConstant;
import com.zxiang.project.client.fundLog.domain.FundLog;
import com.zxiang.project.client.fundLog.service.IFundLogService;

/**
 * 资金流水 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-12-05
 */
@Controller
@RequestMapping("/client/fundLog")
public class FundLogController extends BaseController
{
    private String prefix = "client/fundLog";
	
	@Autowired
	private IFundLogService fundLogService;
	
	@RequiresPermissions("client:fundLog:view")
	@GetMapping("/{clientType}/{clientId}")
	public String fundLog(@PathVariable Integer clientId,@PathVariable String clientType, ModelMap mmap) {
		mmap.put("clientId", clientId);
    	mmap.put("clientType", clientType);
    	fundLogService.showClientInfo(clientId, clientType, mmap);
	    return prefix + "/fundLog";
	}
	@GetMapping("/fundTopUp/{advertiseId}") // 充值
	public String getMoney(@PathVariable Integer advertiseId,ModelMap mmap) {
		mmap.put("advertiseId", advertiseId);
		return prefix + "/toUpQrCode";
	}
	@GetMapping("/buildPayQrCode") // 生成支付二维码
	@ResponseBody
	public AjaxResult buildPayQrCode(@RequestParam String advertiseId,@RequestParam String total_fee ) {
		try {
//			String url = AdConstant.AD_PAY_PREFIX;
			String url = "http://mp.bp.zcloudtechs.cn/wx/wxpay/dingshanIncomeCode";
			Map<String, String> paramsMap = new HashMap<String, String>();
			paramsMap.put("total_fee", total_fee);
			paramsMap.put("advertiseId", advertiseId);
			paramsMap.put("order_type", AdConstant.PAY_TYPE_TO_UP);
			
			String requst = Tools.paramsToString(paramsMap);
			String result = Tools.doPostForm(url, requst);
			
			AdHttpResult adHttp = Tools.analysisResult(result);
			JSONObject jsonResult =  (JSONObject) adHttp.get("data");
			String qrCode = jsonResult.getString("qrCode");
			if(StringUtils.isNotEmpty(qrCode)){
				return AjaxResult.success(qrCode);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return AjaxResult.error();
	}
	@GetMapping("/getMoney/{clientType}/{clientId}") // 提现弹框
	public String getMoney(@PathVariable Integer clientId,@PathVariable String clientType, ModelMap mmap) {
		mmap.put("clientId", clientId);
		mmap.put("clientType", clientType);
		fundLogService.showClientInfo(clientId, clientType, mmap);
		return prefix + "/moneyDialogs";
	}
	@PostMapping("/moneyHandle") // 提现处理中
	@RequiresPermissions("client:withdraw:deposit")
	@ResponseBody
	public AjaxResult moneyHandle(@RequestParam Integer clientId,@RequestParam String clientType,@RequestParam BigDecimal money) {
		fundLogService.clientWithdraw(clientId, clientType, money);
		return AjaxResult.success("提现已提交处理...");
	}
	
	/**
	 * 查询资金流水列表
	 */
	@RequiresPermissions("client:fundLog:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(FundLog fundLog)
	{
		startPage();
        List<FundLog> list = fundLogService.selectFundLogList(fundLog);
		return getDataTable(list);
	}
	
	/**
	 * 新增资金流水
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存资金流水
	 */
	@RequiresPermissions("client:fundLog:add")
	@Log(title = "资金流水", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(FundLog fundLog)
	{		
		return toAjax(fundLogService.insertFundLog(fundLog));
	}

	/**
	 * 修改资金流水
	 */
	@GetMapping("/edit/{payId}")
	public String edit(@PathVariable("payId") Integer payId, ModelMap mmap)
	{
		FundLog fundLog = fundLogService.selectFundLogById(payId);
		mmap.put("fundLog", fundLog);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存资金流水
	 */
	@RequiresPermissions("client:fundLog:edit")
	@Log(title = "资金流水", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(FundLog fundLog)
	{		
		return toAjax(fundLogService.updateFundLog(fundLog));
	}
	
	/**
	 * 删除资金流水
	 */
	@RequiresPermissions("client:fundLog:remove")
	@Log(title = "资金流水", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(fundLogService.deleteFundLogByIds(ids));
	}
	
}
