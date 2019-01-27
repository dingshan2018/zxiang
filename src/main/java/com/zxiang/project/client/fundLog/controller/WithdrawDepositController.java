package com.zxiang.project.client.fundLog.controller;

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

import com.zxiang.framework.aspectj.lang.annotation.DataFilter;
import com.zxiang.framework.aspectj.lang.annotation.Log;
import com.zxiang.framework.aspectj.lang.enums.BusinessType;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.domain.AjaxResult;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.project.client.fundLog.domain.WithdrawDeposit;
import com.zxiang.project.client.fundLog.service.IWithdrawDepositService;

/**
 * 提现记录 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-12-07
 */
@Controller
@RequestMapping("/client/withdrawDeposit")
public class WithdrawDepositController extends BaseController
{
    private String prefix = "client/fundLog";
	
	@Autowired
	private IWithdrawDepositService withdrawDepositService;
	
	@RequiresPermissions("client:withdrawDeposit:view")
	@GetMapping()
	public String withdrawDeposit()
	{
	    return prefix + "/withdrawDeposit";
	}
	
	/**
	 * 查询提现记录列表
	 */
	@DataFilter(personAlias="b.user_id")
	@RequiresPermissions("client:withdrawDeposit:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WithdrawDeposit withdrawDeposit)
	{
		startPage();
        List<WithdrawDeposit> list = withdrawDepositService.selectWithdrawDepositList(withdrawDeposit);
		return getDataTable(list);
	}
	
	/**
	 * 新增提现记录
	 */
	@GetMapping("/add")
	public String add() {
	    return prefix + "/add";
	}
	/**
	 * 跳转确认转账页面
	 */
	@GetMapping("/toConfirmAccount/{id}")
	public String toConfirmAccount(@PathVariable("id") Integer id, ModelMap mmap) {
		WithdrawDeposit wd = withdrawDepositService.selectWithdrawDepositById(id);
		mmap.put("id", id);
		mmap.put("clientName", wd.getClientName());
		mmap.put("bankAccount", wd.getBankAccount());
		mmap.put("bankReceiver", wd.getBankReceiver());
		mmap.put("bankName", wd.getBankName());
		mmap.put("money", wd.getMoney());
		return prefix + "/confirmAccount";
	}
	@PostMapping("/confirmAccount") // 提现处理中
	@RequiresPermissions("client:confirm:account")
	@ResponseBody
	public AjaxResult confirmAccount(WithdrawDeposit withdrawDeposit) {
		withdrawDepositService.updateWithdrawDeposit(withdrawDeposit);
		return AjaxResult.success("提现已提交处理...");
	}
	/**
	 * 新增保存提现记录
	 */
	@RequiresPermissions("client:withdrawDeposit:add")
	@Log(title = "提现记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(WithdrawDeposit withdrawDeposit){		
		return toAjax(withdrawDepositService.insertWithdrawDeposit(withdrawDeposit));
	}

	/**
	 * 修改提现记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		WithdrawDeposit withdrawDeposit = withdrawDepositService.selectWithdrawDepositById(id);
		mmap.put("withdrawDeposit", withdrawDeposit);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存提现记录
	 */
	@RequiresPermissions("client:withdrawDeposit:edit")
	@Log(title = "提现记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WithdrawDeposit withdrawDeposit)
	{		
		return toAjax(withdrawDepositService.updateWithdrawDeposit(withdrawDeposit));
	}
	
	/**
	 * 删除提现记录
	 */
	@RequiresPermissions("client:withdrawDeposit:remove")
	@Log(title = "提现记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(withdrawDepositService.deleteWithdrawDepositByIds(ids));
	}
	
}
