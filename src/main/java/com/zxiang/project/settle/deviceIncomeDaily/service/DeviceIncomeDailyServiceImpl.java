package com.zxiang.project.settle.deviceIncomeDaily.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.zxiang.common.constant.UserConstants;
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
		
		//昨日设备收入计算是不是今天售出得价格    广告收益   出纸数
		
		
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
				
				List<HashMap<String, Object>> tissuelist =selectzxtissuerecordlist(device);
				int tissuenum = tissuelist.size(); //出纸数量
				//获取推荐人人员信息
				HashMap<String, Object> user = iUserIncomeService.selectzxsellerlist(promotioner_id);
				//计算每日设备推广费用
				deviceorder(isincome,promotioner_id,user,device);
				//计算每日出纸费用（二维码推广告）
				tissuedata(device,buyer_id,tissuenum);
				//计算广告费用
				addata(map,buyer_id,tissuenum);
			}
		}
		
		//需要推广代理人（查询前一天加入代理商）
		promotionagent();
	}
	
	//计算每日设备推广费用
	public void deviceorder(String isincome,String seller_id,HashMap<String, Object> order,HashMap<String, Object> map) {
		int placeId  = Integer.valueOf(map.get("place_id") + ""); //场所Id
		int deviceId = Integer.valueOf(map.get("device_id")+""); //设备id
		double price = 0.0;//设备销售价格
		//判断是否是前一天售出的，01代表是    00代表不是
		UserIncome userIncome = new UserIncome();
		if(isincome.equals("01")){
			price = Double.valueOf(map.get("price")+"");
			userIncome.setPromotionIncomeRate(1000.00);
			if(order.get("suuser_id") !=null && order.get("suuser_id") !="" ){
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
		
		//---------------------设备昨日销售价格收入---------------------------------
		DeviceIncomeDaily deviceIncomeDaily = new DeviceIncomeDaily();
		deviceIncomeDaily.setDeviceId(deviceId);
		List<DeviceIncomeDaily> deviceIncomeDailylist = deviceIncomeDailyMapper.selectDeviceIncomeDaily(deviceIncomeDaily);
		deviceIncomeDaily.setSellIncome(price);//销售价格
		if(deviceIncomeDailylist.size()>0){
			DeviceIncomeDaily income = deviceIncomeDailylist.get(0);
			deviceIncomeDaily.setIncomeId(income.getIncomeId());
			updateDeviceIncomeDaily(deviceIncomeDaily);
		}else{
			int terminalId = Integer.valueOf(map.get("terminal_id")+""); //终端ID（板卡ID）
			deviceIncomeDaily.setTerminalId(terminalId);
			deviceIncomeDaily.setPlaceId(placeId);
			insertDeviceIncomeDaily(deviceIncomeDaily);
		}
		
	}
	
	/**
	 * 计算每日出纸费用
	 * */
	public void tissuedata(HashMap<String, Object> map,String buyerid,int tissuenum) {
		//获取机主的信息
		HashMap<String, Object> user = iUserIncomeService.selectzxsellerlist(buyerid);
		//List<HashMap<String, Object>> tissuelist =selectzxtissuerecordlist(map);
		int placeId  = Integer.valueOf(map.get("place_id") + ""); //场所Id
		int deviceId = Integer.valueOf(map.get("device_id")+""); //设备id
		//int tissuenum = tissuelist.size();
		
		//------------------------客户昨日收入--------------------------------------------------------
		//(需要判断是否有广告)插入机主出纸二维码广告数据(每次出纸收益0.3元) 和  服务收益（0.025元）
		UserIncome userIncome = new UserIncome();
		userIncome.setCoperatorId(Integer.valueOf(buyerid));
		List<UserIncome> userlist =iUserIncomeService.selectUserIncome(userIncome);
		//userIncome.setAdIncomeRate(tissuenum*0.3);
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
		
		
		//---------------------设备昨日出纸数量---------------------------------
		DeviceIncomeDaily deviceIncomeDaily = new DeviceIncomeDaily();
		deviceIncomeDaily.setDeviceId(deviceId);
		List<DeviceIncomeDaily> deviceIncomeDailylist = deviceIncomeDailyMapper.selectDeviceIncomeDaily(deviceIncomeDaily);
		deviceIncomeDaily.setScanIncome(tissuenum);//出纸数量
		if(deviceIncomeDailylist.size()>0){
			DeviceIncomeDaily income = deviceIncomeDailylist.get(0);
			deviceIncomeDaily.setIncomeId(income.getIncomeId());
			updateDeviceIncomeDaily(deviceIncomeDaily);
		}else{
			int terminalId = Integer.valueOf(map.get("terminal_id")+""); //终端ID（板卡ID）
			deviceIncomeDaily.setTerminalId(terminalId);
			deviceIncomeDaily.setPlaceId(placeId);
			insertDeviceIncomeDaily(deviceIncomeDaily);
		}
		
	}
	/**
	 * 计算广告费用
	 * */
	public void addata(HashMap<String, Object> map,String buyerid,int tissuenum) {
		List<HashMap<String, Object>> releaserecordlist =selectreleaserecordlist(map);//广告投放设备
		int placeId  = Integer.valueOf(map.get("place_id") + ""); //场所Id
		int deviceId = Integer.valueOf(map.get("device_id")+""); //设备id
		for(HashMap<String, Object> releaserecord : releaserecordlist) {
			double price = Double.valueOf(releaserecord.get("price")+""); //广告价格
			//--------------------------------------客户昨日收入-----------------------------------------------
			//获取推广计划
			HashMap<String, Object> selecadschedule = selecadschedulelist(Integer.valueOf(releaserecord.get("schedule_id").toString()));
			String release_type = selecadschedule.get("release_type").toString(); //投放方式01终端轮播  02终端视频  03H5广告
			int  advertiser = Integer.valueOf(selecadschedule.get("advertiser")+""); //广告商
			int	 promotioner = Integer.valueOf(selecadschedule.get("promotioner")+""); //推荐人
			HashMap<String, Object> promotionerdata = iUserIncomeService.selectzxsellerlist(promotioner+"");
			String puser_id = selecadschedule.get("puser_id")+""; //主体ID
			String user_type = promotionerdata.get("user_type")+""; //用户类型
			HashMap<String, Object> puser = new HashMap<String, Object>();
			List<HashMap<String, Object>> user = new ArrayList<HashMap<String, Object>>();
			switch (release_type) {
			case "01":
				if(user_type.equals(UserConstants.USER_TYPE_JOIN)) {
					puser.put("joinId", puser_id);
					user=iUserIncomeService.selectzxjoinlist(puser);
				}else if(user_type.equals(UserConstants.USER_TYPE_REPAIR)) {
					puser.put("repairId", puser_id);
					user=iUserIncomeService.selectzxrepairlist(puser);
				}else if(user_type.equals(UserConstants.USER_TYPE_AGENT)) {
					puser.put("userId", promotioner);
					user=iUserIncomeService.selectzxagentlist(puser);
				}
				//插入机主广告数据(视频广告投放金额40% , 轮播广告投放金额40%)和  推广视频，轮播图广告收益  15% 和(需要判断是否有广告)插入机主出纸二维码广告数据(每次出纸收益0.3元)
				UserIncome carouseluserIncome = new UserIncome();
				carouseluserIncome.setCoperatorId(Integer.valueOf(buyerid));
				List<UserIncome> carouseluserlist =iUserIncomeService.selectUserIncome(carouseluserIncome);
				carouseluserIncome.setAdIncomeRate(0.4*price);
				if(carouseluserlist.size()>0){
					UserIncome income = carouseluserlist.get(0);
					carouseluserIncome.setIncomeId(income.getIncomeId());
					iUserIncomeService.updateUserIncome(carouseluserIncome);
				}else{
					iUserIncomeService.insertUserIncome(carouseluserIncome);
				}
				//插入代理商广告数据（地级市代理地区所属机子视频广告投放金额2%、地区所属机子轮播广告投放金额2%，县区、县级市代理地区所属机子视频广告投放金额3%、地区所属机子轮播广告投放金额3%）和推广视频，轮播图广告收益  15%
				 HashMap<String, Object> promotionagenmap = new HashMap<String, Object>();
				 promotionagenmap.put("placeId", placeId);
				 List<HashMap<String, Object>> promotionagentlist = iUserIncomeService.selectzxagentlist(promotionagenmap);
				for(HashMap<String, Object> promotionagent : promotionagentlist) {
					String level =  promotionagent.get("level") + ""; //代理等级 1 一级代理  2 二级代理
					String promotionauser = promotionagent.get("user_id")+"";
					UserIncome carouseluserIncome1 = new UserIncome();
					carouseluserIncome.setCoperatorId(Integer.valueOf(promotionauser));
					List<UserIncome> carouseluserlist1 =iUserIncomeService.selectUserIncome(carouseluserIncome);
					carouseluserIncome.setAdIncomeRate(0.05);
					if(carouseluserlist1.size()>0){
						UserIncome income = carouseluserlist1.get(0);
						carouseluserIncome.setIncomeId(income.getIncomeId());
						iUserIncomeService.updateUserIncome(carouseluserIncome);
					}else{
						iUserIncomeService.insertUserIncome(carouseluserIncome);
					}
				}
				//插入服务商广告数据（所服务的机子视频广告投放金额3%,所服务的机子轮播广告投放金额3%）和 推广视频，轮播图广告收益  15%
				 HashMap<String, Object> repairmap = new HashMap<String, Object>();
				 repairmap.put("countyId", placeId);
				 List<HashMap<String, Object>> repairlist = iUserIncomeService.selectzxrepairarealist(repairmap);
				 for(HashMap<String, Object> repair : repairlist) {
					   String repairId = repair.get("repair_id")+"";
					   HashMap<String, Object> repairusermap = iUserIncomeService.selectuserbypuserId(repairId);
					   String repairuser = repairusermap.get("user_id")+"";
					    UserIncome carouseluserIncome2 = new UserIncome();
						carouseluserIncome.setCoperatorId(Integer.valueOf(repairuser));
						List<UserIncome> carouseluserlist2 =iUserIncomeService.selectUserIncome(carouseluserIncome);
						carouseluserIncome.setAdIncomeRate(0.05);
						if(carouseluserlist2.size()>0){
							UserIncome income = carouseluserlist2.get(0);
							carouseluserIncome.setIncomeId(income.getIncomeId());
							iUserIncomeService.updateUserIncome(carouseluserIncome);
						}else{
							iUserIncomeService.insertUserIncome(carouseluserIncome);
						}
				 }
				
				
				break;
			case "02":
				if(user_type.equals(UserConstants.USER_TYPE_JOIN)) {
					puser.put("joinId", puser_id);
					user=iUserIncomeService.selectzxjoinlist(puser);
				}else if(user_type.equals(UserConstants.USER_TYPE_REPAIR)) {
					puser.put("repairId", puser_id);
					user=iUserIncomeService.selectzxrepairlist(puser);
				}else if(user_type.equals(UserConstants.USER_TYPE_AGENT)) {
					puser.put("userId", promotioner);
					user=iUserIncomeService.selectzxagentlist(puser);
				}
				//插入机主广告数据(视频广告投放金额40% , 轮播广告投放金额40%)和  推广视频，轮播图广告收益  15% 和(需要判断是否有广告)插入机主出纸二维码广告数据(每次出纸收益0.3元)
				UserIncome aduserIncome = new UserIncome();
				aduserIncome.setCoperatorId(Integer.valueOf(buyerid));
				List<UserIncome> aduserlist =iUserIncomeService.selectUserIncome(aduserIncome);
				aduserIncome.setAdIncomeRate(0.4*price);
				if(aduserlist.size()>0){
					UserIncome income = aduserlist.get(0);
					aduserIncome.setIncomeId(income.getIncomeId());
					iUserIncomeService.updateUserIncome(aduserIncome);
				}else{
					iUserIncomeService.insertUserIncome(aduserIncome);
				}
				//插入代理商广告数据（地级市代理地区所属机子视频广告投放金额2%、地区所属机子轮播广告投放金额2%，县区、县级市代理地区所属机子视频广告投放金额3%、地区所属机子轮播广告投放金额3%）和推广视频，轮播图广告收益  15%
				UserIncome aduserIncome1 = new UserIncome();
				aduserIncome.setCoperatorId(Integer.valueOf(buyerid));
				List<UserIncome> aduserlist1 =iUserIncomeService.selectUserIncome(aduserIncome);
				aduserIncome.setAdIncomeRate(0.05);
				if(aduserlist1.size()>0){
					UserIncome income = aduserlist1.get(0);
					aduserIncome.setIncomeId(income.getIncomeId());
					iUserIncomeService.updateUserIncome(aduserIncome);
				}else{
					iUserIncomeService.insertUserIncome(aduserIncome);
				}
				//插入服务商广告数据（所服务的机子视频广告投放金额3%,所服务的机子轮播广告投放金额3%）和 推广视频，轮播图广告收益  15%
				UserIncome aduserIncome2 = new UserIncome();
				aduserIncome.setCoperatorId(Integer.valueOf(buyerid));
				List<UserIncome> aduserlist2 =iUserIncomeService.selectUserIncome(aduserIncome);
				aduserIncome.setAdIncomeRate(0.05);
				if(aduserlist2.size()>0){
					UserIncome income = aduserlist2.get(0);
					aduserIncome.setIncomeId(income.getIncomeId());
					iUserIncomeService.updateUserIncome(aduserIncome);
				}else{
					iUserIncomeService.insertUserIncome(aduserIncome);
				}
				break;
			case "03":
				if(user_type.equals(UserConstants.USER_TYPE_JOIN)) {
					puser.put("joinId", puser_id);
					user=iUserIncomeService.selectzxjoinlist(puser);
				}else if(user_type.equals(UserConstants.USER_TYPE_REPAIR)) {
					puser.put("repairId", puser_id);
					user=iUserIncomeService.selectzxrepairlist(puser);
				}else if(user_type.equals(UserConstants.USER_TYPE_AGENT)) {
					puser.put("userId", promotioner);
					user=iUserIncomeService.selectzxagentlist(puser);
				}
				//插入机主广告数据(视频广告投放金额40% , 轮播广告投放金额40%)和  推广视频，轮播图广告收益  15% 和(需要判断是否有广告)插入机主出纸二维码广告数据(每次出纸收益0.3元)
				UserIncome userIncome = new UserIncome();
				userIncome.setCoperatorId(Integer.valueOf(buyerid));
				List<UserIncome> userlist =iUserIncomeService.selectUserIncome(userIncome);
				userIncome.setAdIncomeRate(0.3*tissuenum);
				if(userlist.size()>0){
					UserIncome income = userlist.get(0);
					userIncome.setIncomeId(income.getIncomeId());
					iUserIncomeService.updateUserIncome(userIncome);
				}else{
					iUserIncomeService.insertUserIncome(userIncome);
				}
				//插入代理商广告数据（地级市代理地区所属机子视频广告投放金额2%、地区所属机子轮播广告投放金额2%，县区、县级市代理地区所属机子视频广告投放金额3%、地区所属机子轮播广告投放金额3%）和推广视频，轮播图广告收益  15%
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
				//插入服务商广告数据（所服务的机子视频广告投放金额3%,所服务的机子轮播广告投放金额3%）和 推广视频，轮播图广告收益  15%
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
				break;

			default:
				break;
			}
			
			//---------------------设备昨日广告收入---------------------------------
			DeviceIncomeDaily deviceIncomeDaily = new DeviceIncomeDaily();
			deviceIncomeDaily.setDeviceId(deviceId);
			List<DeviceIncomeDaily> deviceIncomeDailylist = deviceIncomeDailyMapper.selectDeviceIncomeDaily(deviceIncomeDaily);
			deviceIncomeDaily.setAdIncome(price);//广告收入
			if(deviceIncomeDailylist.size()>0){
				DeviceIncomeDaily income = deviceIncomeDailylist.get(0);
				deviceIncomeDaily.setIncomeId(income.getIncomeId());
				updateDeviceIncomeDaily(deviceIncomeDaily);
			}else{
				int terminalId = Integer.valueOf(map.get("terminal_id")+""); //终端ID（板卡ID）
				deviceIncomeDaily.setTerminalId(terminalId);
				deviceIncomeDaily.setPlaceId(placeId);
				insertDeviceIncomeDaily(deviceIncomeDaily);
			}
		}
	}
	
	//广告收益
	public void insertaddata(double price,String buyerid,int placeId,int tissuenum,String type) {
			//插入机主广告数据(视频广告投放金额40% , 轮播广告投放金额40%)和  推广视频，轮播图广告收益  15% 和(需要判断是否有广告)插入机主出纸二维码广告数据(每次出纸收益0.3元)
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
			userIncome.setAdIncomeRate(tissuenum*0.3);
			//插入代理商广告数据（地级市代理地区所属机子视频广告投放金额2%、地区所属机子轮播广告投放金额2%，县区、县级市代理地区所属机子视频广告投放金额3%、地区所属机子轮播广告投放金额3%）和推广视频，轮播图广告收益  15%
			 HashMap<String, Object> map = new HashMap<String, Object>();
			 map.put("placeId", placeId);
			 List<HashMap<String, Object>> promotionagentlist = iUserIncomeService.selectzxagentlist(map);
			for(HashMap<String, Object> promotionagent : promotionagentlist) {
				UserIncome userIncome1 = new UserIncome();
				userIncome.setCoperatorId(Integer.valueOf(promotionagent.get("user_id").toString()));
				List<UserIncome> userlist1 =iUserIncomeService.selectUserIncome(userIncome1);
				userIncome.setAdIncomeRate(0.05);
				if(userlist1.size()>0){
					UserIncome income = userlist1.get(0);
					userIncome1.setIncomeId(income.getIncomeId());
					iUserIncomeService.updateUserIncome(userIncome1);
				}else{
					iUserIncomeService.insertUserIncome(userIncome1);
				}
			}
			//插入服务商广告数据（所服务的机子视频广告投放金额3%,所服务的机子轮播广告投放金额3%）和 推广视频，轮播图广告收益  15%
			UserIncome userIncome2 = new UserIncome();
			userIncome.setCoperatorId(Integer.valueOf(buyerid));
			List<UserIncome> userlist2 =iUserIncomeService.selectUserIncome(userIncome2);
			userIncome.setAdIncomeRate(0.05);
			if(userlist2.size()>0){
				UserIncome income = userlist2.get(0);
				userIncome2.setIncomeId(income.getIncomeId());
				iUserIncomeService.updateUserIncome(userIncome2);
			}else{
				iUserIncomeService.insertUserIncome(userIncome2);
			}	
	}
	//推广广告（推广人收益）
	public void insertqrcodeaddata(double price,String buyerid,int placeId,int tissuenum,String type) {
		//插入机主广告数据(视频广告投放金额40% , 轮播广告投放金额40%)和  推广视频，轮播图广告收益  15% 和(需要判断是否有广告)插入机主出纸二维码广告数据(每次出纸收益0.3元)
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
		userIncome.setAdIncomeRate(tissuenum*0.3);
		//插入代理商广告数据（地级市代理地区所属机子视频广告投放金额2%、地区所属机子轮播广告投放金额2%，县区、县级市代理地区所属机子视频广告投放金额3%、地区所属机子轮播广告投放金额3%）和推广视频，轮播图广告收益  15%
		 HashMap<String, Object> map = new HashMap<String, Object>();
		 map.put("placeId", placeId);
		 List<HashMap<String, Object>> promotionagentlist = iUserIncomeService.selectzxagentlist(map);
		for(HashMap<String, Object> promotionagent : promotionagentlist) {
			UserIncome userIncome1 = new UserIncome();
			userIncome.setCoperatorId(Integer.valueOf(promotionagent.get("user_id").toString()));
			List<UserIncome> userlist1 =iUserIncomeService.selectUserIncome(userIncome1);
			userIncome.setAdIncomeRate(0.05);
			if(userlist1.size()>0){
				UserIncome income = userlist1.get(0);
				userIncome1.setIncomeId(income.getIncomeId());
				iUserIncomeService.updateUserIncome(userIncome1);
			}else{
				iUserIncomeService.insertUserIncome(userIncome1);
			}
		}
		//插入服务商广告数据（所服务的机子视频广告投放金额3%,所服务的机子轮播广告投放金额3%）和 推广视频，轮播图广告收益  15%
		UserIncome userIncome2 = new UserIncome();
		userIncome.setCoperatorId(Integer.valueOf(buyerid));
		List<UserIncome> userlist2 =iUserIncomeService.selectUserIncome(userIncome2);
		userIncome.setAdIncomeRate(0.05);
		if(userlist2.size()>0){
			UserIncome income = userlist2.get(0);
			userIncome2.setIncomeId(income.getIncomeId());
			iUserIncomeService.updateUserIncome(userIncome2);
		}else{
			iUserIncomeService.insertUserIncome(userIncome2);
		}	
}
	
	
	/**
	 * 
	 * 计算推广代理收益(直推代理分润，代理费的15%)
	 *
	 * */
	public void promotionagent() {
		//获取昨日添加得代理商
		 HashMap<String, Object> map = new HashMap<String, Object>();
		 map.put("promotionagent", "1");
		 List<HashMap<String, Object>> promotionagentlist = iUserIncomeService.selectzxagentlist(map);
		 for(HashMap<String, Object> promotionagent : promotionagentlist) {
			 if(com.zxiang.common.utils.StringUtils.isNull(promotionagent.get("promotor_id"))) {
			   double agency_fee = Double.valueOf(promotionagent.get("agency_fee")+"");
			   UserIncome userIncome = new UserIncome();
					//插入数据
				userIncome.setCoperatorId(Integer.valueOf(promotionagent.get("promotor_id")+""));
				List<UserIncome> userlist =iUserIncomeService.selectUserIncome(userIncome);
				userIncome.setPromotionIncomeRate(agency_fee*0.15);
				if(userlist.size()>0){
					UserIncome income = userlist.get(0);
					userIncome.setIncomeId(income.getIncomeId());
					iUserIncomeService.updateUserIncome(userIncome);
				}else{
					iUserIncomeService.insertUserIncome(userIncome);
				}
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
