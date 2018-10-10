package com.zxiang.project.settle.deviceIncomeDaily.service;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
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
				String promotioner_id = order.get("promotioner_id")+""; //推荐人
				String buyer_id = order.get("buyer_id")+"";//机主
				String isincome = order.get("isincome")+""; //是否是当天售出
				//获取推荐人人员信息
				HashMap<String, Object> user = iUserIncomeService.selectzxsellerlist(promotioner_id);
				//计算每日设备推广费用
				deviceorder(isincome,promotioner_id,user);
				//计算每日出纸费用（二维码推广告）
				tissuedata(device,buyer_id);
				//计算广告费用
				addata(map,buyer_id);
			}
		}
	}
	
	//计算每日设备推广费用
	public void deviceorder(String isincome,String seller_id,HashMap<String, Object> order) {
		//判断是否是前一天售出的，01代表是    00代表不是
		UserIncome userIncome = new UserIncome();
		if(isincome.equals("01")){
			userIncome.setPromotionIncomeRate(1000.00);
			if(order.get("puser_id") !=null && order.get("puser_id") !="" ){
				userIncome.setPromotionIncomeRate(500.00);
			}
		}else{
			userIncome.setPromotionIncomeRate(0.00);
		}
		//插入数据
		userIncome.setCoperatorId(Integer.valueOf(seller_id));
		List<UserIncome> userlist =iUserIncomeService.selectUserIncome(userIncome);
		if(userlist.size()>0){
			UserIncome income = userlist.get(0);
			userIncome.setIncomeId(income.getIncomeId());
			iUserIncomeService.updateUserIncome(userIncome);
		}else{
			iUserIncomeService.insertUserIncome(userIncome);
		}
	}
	
	//计算每日出纸费用
	public void tissuedata(HashMap<String, Object> map,String buyerid) {
		//获取机主的信息
		HashMap<String, Object> user = iUserIncomeService.selectzxsellerlist(buyerid);
		List<HashMap<String, Object>> tissuelist =selectzxtissuerecordlist(map);
		String placeId  = map.get("place_id") + ""; //场所Id
		int tissuenum = tissuelist.size();
		
		//(需要判断是否有广告)插入机主出纸二维码广告数据(每次出纸收益0.3元) 和  服务收益（0.025元）
		UserIncome userIncome = new UserIncome();
		userIncome.setCoperatorId(Integer.valueOf(buyerid));
		List<UserIncome> userlist =iUserIncomeService.selectUserIncome(userIncome);
		userIncome.setAdIncomeRate(tissuenum*0.3);
		userIncome.setScanIncomeRate(tissuenum*0.025);
		if(userlist.size()>0){
			UserIncome income = userlist.get(0);
			userIncome.setIncomeId(income.getIncomeId());
			iUserIncomeService.updateUserIncome(userIncome);
		}else{
			iUserIncomeService.insertUserIncome(userIncome);
		}
		
		//插入代理商出纸二维码广告数据（地级市代理每次出纸收益0.02元，县区、县级市代理每次出纸收益0.05元）和  服务收益（0.025元）
		UserIncome userIncome1 = new UserIncome();
		userIncome.setCoperatorId(Integer.valueOf(buyerid));
		List<UserIncome> userlist1 =iUserIncomeService.selectUserIncome(userIncome);
		userIncome.setAdIncomeRate(tissuenum*0.05);
		userIncome.setAdIncomeRate(tissuenum*0.025);
		if(userlist1.size()>0){
			UserIncome income = userlist1.get(0);
			userIncome.setIncomeId(income.getIncomeId());
			iUserIncomeService.updateUserIncome(userIncome);
		}else{
			iUserIncomeService.insertUserIncome(userIncome);
		}
		
		//插入服务商出纸二维码广告数据（每次出纸收益0.05元）
		UserIncome userIncome2 = new UserIncome();
		userIncome.setCoperatorId(Integer.valueOf(buyerid));
		List<UserIncome> userlist2 =iUserIncomeService.selectUserIncome(userIncome);
		userIncome.setAdIncomeRate(tissuenum*0.05);
		if(userlist2.size()>0){
			UserIncome income = userlist2.get(0);
			userIncome.setIncomeId(income.getIncomeId());
			iUserIncomeService.updateUserIncome(userIncome);
		}else{
			iUserIncomeService.insertUserIncome(userIncome);
		}
		
	}
	//计算广告费用
	public void addata(HashMap<String, Object> map,String buyerid) {
		List<HashMap<String, Object>> releaserecordlist =selectreleaserecordlist(map);//广告投放设备
		String placeId  = map.get("place_id") + ""; //场所Id
		for(HashMap<String, Object> releaserecord : releaserecordlist) {
			double price = Double.valueOf(releaserecord.get("price")+""); //广告价格
			//获取推广计划
			HashMap<String, Object> selecadschedule = selecadschedulelist(Integer.valueOf(releaserecord.get("schedule_id").toString()));
			
			//插入机主广告数据(视频广告投放金额40% , 轮播广告投放金额40%)
			UserIncome userIncome = new UserIncome();
			userIncome.setCoperatorId(Integer.valueOf(buyerid));
			List<UserIncome> userlist =iUserIncomeService.selectUserIncome(userIncome);
			userIncome.setAdIncomeRate(0.3);
			if(userlist.size()>0){
				UserIncome income = userlist.get(0);
				userIncome.setIncomeId(income.getIncomeId());
				iUserIncomeService.updateUserIncome(userIncome);
			}else{
				iUserIncomeService.insertUserIncome(userIncome);
			}
			
			//插入代理商广告数据（地级市代理地区所属机子视频广告投放金额2%、地区所属机子轮播广告投放金额2%，县区、县级市代理地区所属机子视频广告投放金额3%、地区所属机子轮播广告投放金额3%）
			UserIncome userIncome1 = new UserIncome();
			userIncome.setCoperatorId(Integer.valueOf(buyerid));
			List<UserIncome> userlist1 =iUserIncomeService.selectUserIncome(userIncome);
			userIncome.setAdIncomeRate(0.05);
			if(userlist1.size()>0){
				UserIncome income = userlist1.get(0);
				userIncome.setIncomeId(income.getIncomeId());
				iUserIncomeService.updateUserIncome(userIncome);
			}else{
				iUserIncomeService.insertUserIncome(userIncome);
			}
			
			//插入服务商广告数据（所服务的机子视频广告投放金额3%,所服务的机子轮播广告投放金额3%）
			UserIncome userIncome2 = new UserIncome();
			userIncome.setCoperatorId(Integer.valueOf(buyerid));
			List<UserIncome> userlist2 =iUserIncomeService.selectUserIncome(userIncome);
			userIncome.setAdIncomeRate(0.05);
			if(userlist2.size()>0){
				UserIncome income = userlist2.get(0);
				userIncome.setIncomeId(income.getIncomeId());
				iUserIncomeService.updateUserIncome(userIncome);
			}else{
				iUserIncomeService.insertUserIncome(userIncome);
			}	
		}
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
    //广告播放价格
	@Override
	public List<HashMap<String, Object>> selectreleaserecordlist(HashMap<String, Object> map) {
		return deviceIncomeDailyMapper.selectreleaserecordlist(map);
	}
	 //推广计划
	@Override
	public HashMap<String, Object> selecadschedulelist(Integer scheduleId) {
		List<HashMap<String, Object>> list = deviceIncomeDailyMapper.selecadschedulelist(scheduleId);
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}
}
