package com.zxiang.project.settle.deviceIncomeDaily.service;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxiang.common.support.Convert;
import com.zxiang.project.settle.deviceIncomeDaily.domain.DeviceIncomeDaily;
import com.zxiang.project.settle.deviceIncomeDaily.mapper.DeviceIncomeDailyMapper;
import com.zxiang.project.settle.userIncome.domain.UserIncome;
import com.zxiang.project.settle.userIncome.service.IUserIncomeService;

/**
 * 设备收入日统计 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Service
public class DeviceIncomeDailyServiceImpl implements IDeviceIncomeDailyService 
{
	private static final Logger logger = LoggerFactory.getLogger(DeviceIncomeDailyServiceImpl.class);
	@Autowired
	private DeviceIncomeDailyMapper deviceIncomeDailyMapper;
	@Autowired
	private IUserIncomeService iUserIncomeService;
    
	/**
     * 查询设备收入日统计信息
     * 
     * @param incomeId 设备收入日统计ID
     * @return 设备收入日统计信息
     */
    @Override
	public DeviceIncomeDaily selectDeviceIncomeDailyById(Integer incomeId)
	{
	    return deviceIncomeDailyMapper.selectDeviceIncomeDailyById(incomeId);
	}
	
	/**
     * 查询设备收入日统计列表
     * 
     * @param deviceIncomeDaily 设备收入日统计信息
     * @return 设备收入日统计集合
     */
	@Override
	public List<DeviceIncomeDaily> selectDeviceIncomeDailyList(DeviceIncomeDaily deviceIncomeDaily)
	{
	    return deviceIncomeDailyMapper.selectDeviceIncomeDailyList(deviceIncomeDaily);
	}
	
    /**
     * 新增设备收入日统计
     * 
     * @param deviceIncomeDaily 设备收入日统计信息
     * @return 结果
     */
	@Override
	public int insertDeviceIncomeDaily(DeviceIncomeDaily deviceIncomeDaily)
	{
	    return deviceIncomeDailyMapper.insertDeviceIncomeDaily(deviceIncomeDaily);
	}
	
	/**
     * 修改设备收入日统计
     * 
     * @param deviceIncomeDaily 设备收入日统计信息
     * @return 结果
     */
	@Override
	public int updateDeviceIncomeDaily(DeviceIncomeDaily deviceIncomeDaily)
	{
	    return deviceIncomeDailyMapper.updateDeviceIncomeDaily(deviceIncomeDaily);
	}

	/**
     * 删除设备收入日统计对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDeviceIncomeDailyByIds(String ids)
	{
		return deviceIncomeDailyMapper.deleteDeviceIncomeDailyByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<HashMap<String, Object>> selectzxdevicelist(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return deviceIncomeDailyMapper.selectzxdevicelist(map);
	}

	@Override
	public List<HashMap<String, Object>> selectzxdeviceorderlist(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return deviceIncomeDailyMapper.selectzxdeviceorderlist(map);
	}

	@Override
	public List<HashMap<String, Object>> selectzxtissuerecordlist(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return deviceIncomeDailyMapper.selectzxtissuerecordlist(map);
	}

	/**
	 * 每日统计数据
	 * 
	 * */
	@Override
	public void statisticaldata() {
		//获取所有设备信息
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<HashMap<String, Object>> devicelist =selectzxdevicelist(map);
	    //计算前一日售出设备的金额
		for (HashMap<String, Object> device : devicelist) {
			map = new HashMap<String, Object>();
			map.put("deviceId", device.get("device_id"));
			List<HashMap<String, Object>> orderlist =selectzxdeviceorderlist(map);
			if(orderlist.size()>0){
				HashMap<String, Object> order = orderlist.get(0);
				String seller_id = order.get("seller_id")+""; //销售人员
				String buyer_id = order.get("buyer_id")+"";//机主
				String isincome = order.get("isincome")+"";
				//获取销售人员信息
				iUserIncomeService.selectzxsellerlist(seller_id);
				//判断是否是前一天售出的，01代表是    00代表不是
				UserIncome userIncome = new UserIncome();
				if(isincome.equals("01")){
					userIncome.setPromotionIncomeRate(1000.00);
					if(order.get("promotioner_id") !=null && order.get("promotioner_id") !="" ){
						userIncome.setPromotionIncomeRate(500.00);
					}
				}else{
					userIncome.setPromotionIncomeRate(0.00);
				}
				//插入数据
				userIncome.setCoperatorId(Integer.valueOf(seller_id));
				List<UserIncome> userlist =iUserIncomeService.selectUserIncome(userIncome);
				if(userlist.size()>0){
					iUserIncomeService.updateUserIncome(userIncome);
				}else{
					iUserIncomeService.insertUserIncome(userIncome);
				}
				
				//计算每日出纸费用
				tissuedata(device,buyer_id);
				//计算广告费用
				addata(map);
			}
		}
		
		
	}
	
	//计算每日出纸费用
	public void tissuedata(HashMap<String, Object> map,String buyerid) {
		//获取机主的信息
		map = new HashMap<String, Object>();
		map.put("joinerId", buyerid);
		List<HashMap<String, Object>> joinlist = iUserIncomeService.selectzxjoinlist(map);
		HashMap<String, Object> join = joinlist.get(0);

		List<HashMap<String, Object>> tissue =selectzxtissuerecordlist(map);
	}
	//计算广告费用
	public void addata(HashMap<String, Object> map) {
		List<HashMap<String, Object>> devicelist =selectzxdevicelist(map);
		
	}

	//场所管理
	@Override
	public HashMap<String, Object> selectzxplace(HashMap<String, Object> map) {
		List<HashMap<String, Object>> zxplacelist = deviceIncomeDailyMapper.selectzxplace(map);
		if(zxplacelist.size()>0){
			return zxplacelist.get(0);
		}
		return null;
	}
}
