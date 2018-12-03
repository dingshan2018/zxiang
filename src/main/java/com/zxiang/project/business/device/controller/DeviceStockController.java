package com.zxiang.project.business.device.controller;

import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxiang.common.constant.UserConstants;
import com.zxiang.framework.aspectj.lang.annotation.DataFilter;
import com.zxiang.framework.aspectj.lang.annotation.Log;
import com.zxiang.framework.aspectj.lang.enums.BusinessType;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.domain.AjaxResult;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.project.business.device.domain.Device;
import com.zxiang.project.business.device.service.IDeviceService;
import com.zxiang.project.record.tradeOrder.domain.TradeOrder;
import com.zxiang.project.record.tradeOrder.service.ITradeOrderService;
import com.zxiang.project.system.user.domain.User;
import com.zxiang.project.system.user.service.IUserService;

/**
 * 共享设备 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
@Controller
@RequestMapping("/business/deviceStock")
public class DeviceStockController extends BaseController
{
	Logger logger = Logger.getLogger(DeviceStockController.class);
			
    private String prefix = "business/device";
    
	@Autowired
	private IDeviceService deviceService;
	@Autowired
	private IUserService userService;
	@Autowired
	private ITradeOrderService tradeOrderService;
	
	@RequiresPermissions("business:deviceStock:view")
	@GetMapping()
	public String deviceStock()
	{
	    return prefix + "/deviceStock";
	}
	
	/**
	 * 查询设备库存列表
	 */
	@DataFilter(placeAlias="place_id")
	@RequiresPermissions("business:device:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Device device)
	{
		startPage();
		
        List<Device> list = deviceService.selectDeviceStockList(device);
		return getDataTable(list);
	}
	
	/**
	 * 新增共享设备
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap)
	{
		User queryUser = new User();
		queryUser.setUserType(UserConstants.USER_TYPE_JOIN);
		List<User> userListJoin = userService.selectUserList(queryUser);
		mmap.put("userList", userListJoin);
		
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存共享设备
	 */
	@RequiresPermissions("business:device:add")
	@Log(title = "共享设备", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Device device)
	{		
		String createor = getUser().getUserName();
		long userId = getUserId();
		
		device.setCreateBy(createor+"("+userId+")");
		device.setCreateTime(new Date());
		return toAjax(deviceService.insertDevice(device));
	}

	/**
	 * 删除共享设备
	 */
	@RequiresPermissions("business:device:remove")
	@Log(title = "共享设备", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(deviceService.deleteDeviceByIds(ids));
	}
	
	/**
	 * 暂时不用这种，先使用需要根据订单号选择数量的/outStockByTradeId方法
	 * 库存设备出库
	 */
	@RequiresPermissions("business:deviceStock:outStock")
	@Log(title = "库存设备出库", businessType = BusinessType.UPDATE)
	@PostMapping("/outStock")
	@ResponseBody
	public AjaxResult outStock(String ids)
	{
		int number = deviceService.outStock(ids);
		return success("成功出库" + number + " 台设备");
	}
	
	
	/**
	 * 库存设备出库--跳转界面
	 */
	@GetMapping("/outStockByTradeId")
	public String outStockByTradeId(ModelMap mmap)
	{
		//查询机主
		List<User> userListJoin = userService.selectBuyer();
		mmap.put("userListJoin", userListJoin);
		//查询推荐人（目前用所有用户）
		List<User> userList = userService.selectUserList(new User());
		mmap.put("userList", userList);
		//查询购机已付款订单
		//List<TradeOrder> tradeOrderList = tradeOrderService.selectUnSendList(null);
		//mmap.put("tradeOrderList", tradeOrderList);
		Device device = new Device();
		mmap.put("deviceList", deviceService.selectDeviceStockList(device));
		
	    return prefix + "/outStock";
	}
	/**
	 * 库存设备出库
	 */
	@RequiresPermissions("business:deviceStock:outStock")
	@Log(title = "库存设备出库", businessType = BusinessType.UPDATE)
	@PostMapping("/outStockByTradeId")
	@ResponseBody
	public AjaxResult outStockByTradeId(String ids,Integer tradeOrderId,Integer promotionerId)
	{
		logger.info("devices:"+ids+",tradeOrderId:"+tradeOrderId+",promotionerId:"+promotionerId);
		String operatorUser = getUser().getUserName()+"("+getUserId()+")";
		
		TradeOrder tradeOrder = tradeOrderService.selectTradeOrderById(tradeOrderId);
		if(tradeOrder != null){
			//若已经发货则不再发货 发货状态（0 未发货 1 已发货 2 部分发货 ）
			if("1".equals(tradeOrder.getSendStatus())){
				return error("所选订单已发货!");
			}
			//若已经退款也不再发货 订单状态（0 待支付 1 已支付 2 支付失败 3 已退款 4 退款失败）
			if("3".equals(tradeOrder.getOrderStatus())){
				return error("所选订单已退款!");
			}
			
			try {
				int num = deviceService.outStockByTradeId(ids, tradeOrder,operatorUser,promotionerId);
				return success("成功出库" + num + " 台设备");
			} catch (Exception e) {
				e.printStackTrace();
				return error(e.getMessage());
			}
			
		}else{
			return error("所选订单不存在!");
		}
		
	}
	
}
