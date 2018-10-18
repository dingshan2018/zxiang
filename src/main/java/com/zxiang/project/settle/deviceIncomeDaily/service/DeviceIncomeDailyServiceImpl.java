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
		double fee = 0.0;
		if(isincome.equals("01")){
			price = Double.valueOf(map.get("price")+"");
			fee = 1000.00;
			if(order.get("suuser_id") !=null && order.get("suuser_id") !="" ){
				fee = 500.00;
			}
		}
		//插入数据
		insertdata(fee,seller_id,"02");
		
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
	 * 计算每日出纸费用服务收益
	 * */
	public void tissuedata(HashMap<String, Object> map,String buyerid,int tissuenum) {
		//获取机主的信息
		HashMap<String, Object> user = iUserIncomeService.selectzxsellerlist(buyerid);
		int placeId  = Integer.valueOf(map.get("place_id") + ""); //场所Id
		int deviceId = Integer.valueOf(map.get("device_id")+""); //设备id
		
		//------------------------客户昨日收入--------------------------------------------------------
		// 机主 服务收益（0.025元）
		insertdata(tissuenum*0.025,buyerid,"03");
		
		//代理商服务收益（0.025元）
		HashMap<String, Object> promotionagenmap = new HashMap<String, Object>();
		List<HashMap<String, Object>> promotionagentlist = new ArrayList<HashMap<String, Object>>();
		 promotionagenmap.put("placeId", placeId);
		 promotionagentlist = iUserIncomeService.selectzxagentlist(promotionagenmap);
		for(HashMap<String, Object> promotionagent : promotionagentlist) {
			String level =  promotionagent.get("level") + ""; //代理等级 1 一级代理  2 二级代理
			String promotionauser = promotionagent.get("user_id")+"";
			insertdata(tissuenum*0.025,promotionauser,"03");
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
			String release_type = selecadschedule.get("release_type").toString(); //投放方式01终端轮播  02终端视频  03H5广告       (二维码广告还分公司（免费）和外部)
			int  advertiser = Integer.valueOf(selecadschedule.get("advertiser")+""); //广告商
			int	 promotioner = Integer.valueOf(selecadschedule.get("promotioner")+""); //推荐人
			HashMap<String, Object> promotionerdata = iUserIncomeService.selectzxsellerlist(promotioner+"");
			String puser_id = selecadschedule.get("puser_id")+""; //主体ID
			String user_type = promotionerdata.get("user_type")+""; //用户类型
			HashMap<String, Object> puser = new HashMap<String, Object>();
			List<HashMap<String, Object>> user = new ArrayList<HashMap<String, Object>>();
			HashMap<String, Object> promotionagenmap = new HashMap<String, Object>();
			HashMap<String, Object> repairmap = new HashMap<String, Object>();
			List<HashMap<String, Object>> promotionagentlist = new ArrayList<HashMap<String, Object>>();
			List<HashMap<String, Object>> repairlist = new ArrayList<HashMap<String, Object>>();
			double rate = 0.0;
			switch (release_type) {
			case "01":
				//--------------推广收益-----------------------
				if(user_type.equals(UserConstants.USER_TYPE_JOIN)) {
					puser.put("joinId", puser_id);
					user=iUserIncomeService.selectzxjoinlist(puser);
					rate = 0.15;
				}else if(user_type.equals(UserConstants.USER_TYPE_REPAIR)) {
					puser.put("repairId", puser_id);
					user=iUserIncomeService.selectzxrepairlist(puser);
					rate = 0.15;
				}else if(user_type.equals(UserConstants.USER_TYPE_AGENT)) {
					puser.put("userId", promotioner);
					user=iUserIncomeService.selectzxagentlist(puser);
					rate = 0.15;
				}
				insertdata(price*rate,promotioner+"","02");
				
				//-----------------------广告收益--------------
				//插入机主广告数据(视频广告投放金额40% , 轮播广告投放金额40%)
				insertdata(0.4*price,buyerid,"01");
	
				//插入代理商广告数据（地级市代理地区所属机子视频广告投放金额2%、地区所属机子轮播广告投放金额2%，县区、县级市代理地区所属机子视频广告投放金额3%、地区所属机子轮播广告投放金额3%）
				 promotionagenmap.put("placeId", placeId);
				 promotionagentlist = iUserIncomeService.selectzxagentlist(promotionagenmap);
				for(HashMap<String, Object> promotionagent : promotionagentlist) {
					String level =  promotionagent.get("level") + ""; //代理等级 1 一级代理  2 二级代理
					String promotionauser = promotionagent.get("user_id")+"";
					insertdata(0.03*price,promotionauser,"01");
				}
				//插入服务商广告数据（所服务的机子视频广告投放金额3%,所服务的机子轮播广告投放金额3%）
				 repairmap.put("countyId", placeId);
				 repairlist = iUserIncomeService.selectzxrepairarealist(repairmap);
				 for(HashMap<String, Object> repair : repairlist) {
					   String repairId = repair.get("repair_id")+"";
					   HashMap<String, Object> repairusermap = iUserIncomeService.selectuserbypuserId(repairId);
					   String repairuser = repairusermap.get("user_id")+"";
					   insertdata(0.03*price,repairuser,"01");
				 }
				break;
			case "02":
				//--------------推广收益-----------------------
				if(user_type.equals(UserConstants.USER_TYPE_JOIN)) {
					puser.put("joinId", puser_id);
					user=iUserIncomeService.selectzxjoinlist(puser);
					rate = 0.15;
				}else if(user_type.equals(UserConstants.USER_TYPE_REPAIR)) {
					puser.put("repairId", puser_id);
					user=iUserIncomeService.selectzxrepairlist(puser);
					rate = 0.15;
				}else if(user_type.equals(UserConstants.USER_TYPE_AGENT)) {
					puser.put("userId", promotioner);
					user=iUserIncomeService.selectzxagentlist(puser);
					rate = 0.15;
				}
				insertdata(price*rate,promotioner+"","02");
				
				//-----------------------广告收益--------------
				//插入机主广告数据(视频广告投放金额40% , 轮播广告投放金额40%)
				insertdata(0.4*price,buyerid,"01");
	
				//插入代理商广告数据（地级市代理地区所属机子视频广告投放金额2%、地区所属机子轮播广告投放金额2%，县区、县级市代理地区所属机子视频广告投放金额3%、地区所属机子轮播广告投放金额3%）
				 promotionagenmap.put("placeId", placeId);
				 promotionagentlist = iUserIncomeService.selectzxagentlist(promotionagenmap);
				for(HashMap<String, Object> promotionagent : promotionagentlist) {
					String level =  promotionagent.get("level") + ""; //代理等级 1 一级代理  2 二级代理
					String promotionauser = promotionagent.get("user_id")+"";
					insertdata(0.03*price,promotionauser,"01");
				}
				//插入服务商广告数据（所服务的机子视频广告投放金额3%,所服务的机子轮播广告投放金额3%）
				 repairmap.put("countyId", placeId);
				 repairlist = iUserIncomeService.selectzxrepairarealist(repairmap);
				 for(HashMap<String, Object> repair : repairlist) {
					   String repairId = repair.get("repair_id")+"";
					   HashMap<String, Object> repairusermap = iUserIncomeService.selectuserbypuserId(repairId);
					   String repairuser = repairusermap.get("user_id")+"";
					   insertdata(0.03*price,repairuser,"01");
				 }
				break;
			case "03":
				//--------------推广收益-----------------------
				if(user_type.equals(UserConstants.USER_TYPE_JOIN)) {
					puser.put("joinId", puser_id);
					user=iUserIncomeService.selectzxjoinlist(puser);
					rate = 0.7;
				}else if(user_type.equals(UserConstants.USER_TYPE_REPAIR)) {
					puser.put("repairId", puser_id);
					user=iUserIncomeService.selectzxrepairlist(puser);
					rate = 0.7;
				}else if(user_type.equals(UserConstants.USER_TYPE_AGENT)) {
					puser.put("userId", promotioner);
					user=iUserIncomeService.selectzxagentlist(puser);
					rate = 0.7;
				}
				insertdata(tissuenum*rate,promotioner+"","02");
				
				//-----------------------广告收益--------------
				//插入机主广告数据每次出纸收益0.3元
				insertdata(0.3*tissuenum,buyerid,"01");
	
				//插入代理商广告数据（地级市代理地区所服务的机子每次出纸收益0.02元，县区、县级市代理地区所服务的机子每次出纸收益0.05元）
				 promotionagenmap.put("placeId", placeId);
				 promotionagentlist = iUserIncomeService.selectzxagentlist(promotionagenmap);
				for(HashMap<String, Object> promotionagent : promotionagentlist) {
					String level =  promotionagent.get("level") + ""; //代理等级 1 一级代理  2 二级代理
					String promotionauser = promotionagent.get("user_id")+"";
					insertdata(0.02*tissuenum,promotionauser,"01");
				}
				//插入服务商广告数据所服务的机子每次出纸收益0.05元
				 repairmap.put("countyId", placeId);
				 repairlist = iUserIncomeService.selectzxrepairarealist(repairmap);
				 for(HashMap<String, Object> repair : repairlist) {
					   String repairId = repair.get("repair_id")+"";
					   HashMap<String, Object> repairusermap = iUserIncomeService.selectuserbypuserId(repairId);
					   String repairuser = repairusermap.get("user_id")+"";
					   insertdata(0.05*price,repairuser,"01");
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
			   insertdata(agency_fee*0.15,promotionagent.get("promotor_id")+"","02");
			 }
		 }
		
	}
	
	//收益
	public void insertdata(double price,String buyerid,String type) {
		    //投放方式01广告收益   02推广收益  03扫码服务收益
			UserIncome userIncome = new UserIncome();
			userIncome.setCoperatorId(Integer.valueOf(buyerid));
			List<UserIncome> userlist =iUserIncomeService.selectUserIncome(userIncome);
			if(type.equals("01")) {
				userIncome.setAdIncomeRate(price);
			}else if(type.equals("02")) {
				userIncome.setPromotionIncomeRate(price);
			}else {
				userIncome.setScanIncomeRate(price);
			}
			if(userlist.size()>0){
				UserIncome income = userlist.get(0);
				userIncome.setIncomeId(income.getIncomeId());
				iUserIncomeService.updateUserIncome(userIncome);
			}else{
				iUserIncomeService.insertUserIncome(userIncome);
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
