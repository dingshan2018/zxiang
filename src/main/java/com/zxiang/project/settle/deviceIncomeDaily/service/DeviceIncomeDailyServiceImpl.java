package com.zxiang.project.settle.deviceIncomeDaily.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.zxiang.common.constant.RateConstants;
import com.zxiang.common.constant.UserConstants;
import com.zxiang.common.support.Convert;
import com.zxiang.project.client.fundLog.service.IFundLogService;
import com.zxiang.project.settle.deviceIncomeDaily.domain.DeviceIncomeDaily;
import com.zxiang.project.settle.deviceIncomeDaily.mapper.DeviceIncomeDailyMapper;
import com.zxiang.project.settle.userExtension.domain.UserExtension;
import com.zxiang.project.settle.userExtension.service.IUserExtensionService;
import com.zxiang.project.settle.userIncome.domain.UserIncome;
import com.zxiang.project.settle.userIncome.service.IUserIncomeService;

/**
 * 设备收入日统计 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Service("deviceIncomeDailyService")
public class DeviceIncomeDailyServiceImpl implements IDeviceIncomeDailyService 
{
	private static final Logger logger = LoggerFactory.getLogger(DeviceIncomeDailyServiceImpl.class);
	@Autowired
	private DeviceIncomeDailyMapper deviceIncomeDailyMapper;
	@Autowired
	private IUserIncomeService iUserIncomeService;
	@Autowired
	private IUserExtensionService userExtensionService;
	@Autowired
	private IFundLogService iFundLogService;
	
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
	public List<HashMap<String, Object>> selectzxtissuerecordlist(String deviceId,String scheduleId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("deviceId", deviceId);
		map.put("scheduleId", scheduleId);
		return deviceIncomeDailyMapper.selectzxtissuerecordlist(map);
	}
	
	@Override
	public int selectzxtissuerecordAll(String deviceId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("deviceId", deviceId);
		return deviceIncomeDailyMapper.selectzxtissuerecordAll(map);
	}

	/**
	 * 每日统计数据
	 * 
	 * */
	@Override
	public void statisticaldata() {
		//推广出纸数 得收益是公司得所以减去
		// 办公费用就是代理费得*系数
		//另外单个二维码第一次出纸为有效，第2-5次出纸为无效出纸 不计算出纸收益
		//获取所有设备信息
		UserIncome userIncome = new UserIncome();
		List<UserIncome> userIncomeList = iUserIncomeService.selectUserIncome(userIncome);
		DeviceIncomeDaily deviceIncomeDaily = new DeviceIncomeDaily();
		List<DeviceIncomeDaily> deviceIncomeDailylist = deviceIncomeDailyMapper.selectDeviceIncomeDaily(deviceIncomeDaily);
		if(userIncomeList.size()>0 || deviceIncomeDailylist.size()>0) {
			return;
		}
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<HashMap<String, Object>> devicelist =selectzxdevicelist(map);
	    //计算前一日售出设备的金额
		for (HashMap<String, Object> device : devicelist) {
			map = new HashMap<String, Object>();
			map.put("deviceId", device.get("device_id"));
			List<HashMap<String, Object>> orderlist =selectzxdeviceorderlist(map);
			if(orderlist.size()>0){
				HashMap<String, Object> order = orderlist.get(0);
				String buyer_id = order.get("buyer_id")+"";//机主
				String isincome = order.get("isincome")+""; //是否是当天售出
				
				List<HashMap<String, Object>> tissuenumlist= selectzxtissuerecordlist(device.get("device_id")+"",""); //出纸数量
				int tissuenum = tissuenumlist.size();
				int tissuenumAll = selectzxtissuerecordAll(device.get("device_id")+"");
				//计算每日设备推广费用
				deviceorder(isincome,buyer_id,device,order);
				if(tissuenumAll>0) {
					String	promotionerh5 ="";
					if(tissuenum>0) {
						promotionerh5 = tissuenumlist.get(0).get("promotioner").toString();
					}
					//计算每日出纸费用
					tissuedata(device,buyer_id,tissuenumAll,tissuenum,promotionerh5);
				}
				//计算广告费用
				addata(device,buyer_id,tissuenum);
			}
		}
		//需要推广代理人（查询前一天加入代理商）
		promotionagent();
		
		List<HashMap<String, Object>> UsertotalIncomelist = iUserIncomeService.selectUsertotalIncome();
		for(HashMap<String, Object> UsertotalIncome : UsertotalIncomelist) {
			BigDecimal totalIncome = new BigDecimal(UsertotalIncome.get("totalIncome")+"");
			String puserId = UsertotalIncome.get("puserId")+"";
			String puserType = UsertotalIncome.get("puserType")+"";
			iFundLogService.incomeRecord(Integer.valueOf(puserId), puserType, totalIncome);
		}
		 
	}
	
	//计算每日设备推广费用
	public void deviceorder(String isincome,String seller_id,HashMap<String, Object> map,HashMap<String, Object> order) {
	
		int deviceId = Integer.valueOf(map.get("device_id")+""); //设备id
		//判断是否是前一天售出的，01代表是    00代表不是
		double fee = 0.0;
		double price = 0.0;//设备销售价格
		if(com.zxiang.common.utils.StringUtils.isNotNull(seller_id)){
			HashMap<String, Object> promotionerMap = getpromotionerdata(seller_id);
			if(com.zxiang.common.utils.StringUtils.isNotNull(promotionerMap)){
				//获取推荐人人员信息
				HashMap<String, Object> user = getusedata(promotionerMap.get("promotor_id")+"","","");
				if(com.zxiang.common.utils.StringUtils.isNotNull(user)){
					String type = RateConstants.RATETYPE_PROMDIRECTINCOME;
					if(isincome.equals("01")){
						fee = Double.valueOf(user.get("promIndirectRate")==null?"0.0":user.get("promIndirectRate")+"");
						//插入直推数据
						insertdata(fee,"02",type,0.0,1,user);
						insertUserextensiondata(type,0.0,1, user);
						if(promotionerMap.get("leader_id") !=null && promotionerMap.get("leader_id") !="" ){
							//获取推荐人人员信息
							 user = getusedata(promotionerMap.get("leader_id")+"","","");
							if(com.zxiang.common.utils.StringUtils.isNotNull(user)){
								fee = Double.valueOf(user.get("promIndirectRate")==null?"0.0":user.get("promIndirectRate")+"");
								type = RateConstants.RATETYPE_PROMINDIRECTINCOME;
								//插入间推数据
								insertdata(fee,"02",type,0.0,1,user);
								insertUserextensiondata(type,0.0,1, user);
							}
						}
						user = getusedata(seller_id,"","");
						if(user!=null) {
							insertUserextensiondata(RateConstants.BUYING_MACHINE,0.0,1, user);
						}
						
					}
					
				}
			}
		}
		
		//---------------------设备昨日销售价格收入---------------------------------
		if(isincome.equals("01")){
			if(com.zxiang.common.utils.StringUtils.isNotNull(order.get("price"))) {
				price = Double.valueOf(order.get("price")==null?"0.0":order.get("price")+"");
				DeviceIncomeDaily deviceIncomeDaily = new DeviceIncomeDaily();
				deviceIncomeDaily.setDeviceId(deviceId);
				List<DeviceIncomeDaily> deviceIncomeDailylist = deviceIncomeDailyMapper.selectDeviceIncomeDaily(deviceIncomeDaily);
				deviceIncomeDaily.setSellIncome(price);//销售价格
				if(deviceIncomeDailylist.size()>0){
					DeviceIncomeDaily income = deviceIncomeDailylist.get(0);
					deviceIncomeDaily.setIncomeId(income.getIncomeId());
					updateDeviceIncomeDaily(deviceIncomeDaily);
				}else{
					if(com.zxiang.common.utils.StringUtils.isNotNull(map.get("terminal_id"))) {
						deviceIncomeDaily.setTerminalId(Integer.valueOf(map.get("terminal_id")+""));
					}
					if(com.zxiang.common.utils.StringUtils.isNotNull(map.get("place_id"))) {
						deviceIncomeDaily.setPlaceId(Integer.valueOf(map.get("place_id") + ""));
					}
					insertDeviceIncomeDaily(deviceIncomeDaily);
				}
			}
		}
	}
	
	/**
	 * 计算每日出纸费用服务收益
	 * */
	public void tissuedata(HashMap<String, Object> map,String buyerid,int tissuenumAll,int tissuenum,String promotionerh5) {
		//获取机主的信息
		HashMap<String, Object> user = getusedata(buyerid,"","");
		int deviceId = Integer.valueOf(map.get("device_id")+""); //设备id
		if(user.get("leader_id") !=null && user.get("leader_id") !="" ){ //直推人
			//获取推荐人人员信息
			 user = getusedata(user.get("leader_id")+"","","");
			if(com.zxiang.common.utils.StringUtils.isNotNull(user)){
				double serve_rate = Double.valueOf((user==null||user.get("serveRate")==null)?"0.0":user.get("serveRate")+"");
				insertdata(tissuenumAll*serve_rate,"03",RateConstants.SERVE_INCOME,0.0,tissuenumAll,user);
				if(user.get("leader_id") !=null && user.get("leader_id") !="" ){ //间推人
					 user = getusedata(user.get("leader_id")+"","","");
					if(com.zxiang.common.utils.StringUtils.isNotNull(user)){
						 serve_rate = Double.valueOf((user==null||user.get("serveRate")==null)?"0.0":user.get("serveRate")+"");
						insertdata(tissuenumAll*serve_rate,"03",RateConstants.SERVE_INCOME,0.0,tissuenumAll,user);
					}
				}
			}
		}
		
/*		double serve_rate = Double.valueOf((user==null||user.get("serveRate")==null)?"0.0":user.get("serveRate")+"");
		//------------------------客户昨日收入--------------------------------------------------------
		// 机主 服务收益（0.025元）
		insertdata(tissuenumAll*serve_rate,"03",RateConstants.SERVE_INCOME,0.0,tissuenumAll,user);
		
		if(com.zxiang.common.utils.StringUtils.isNotNull(map.get("place_id"))) {
			//代理商服务收益（0.025元）
			HashMap<String, Object> promotionagenmap = new HashMap<String, Object>();
			HashMap<String, Object> placemap =  selectzxplace(map.get("place_id")+""); //获取地点
			List<HashMap<String, Object>> promotionagentlist = new ArrayList<HashMap<String, Object>>();
			 promotionagenmap.put("placeId", placemap.get("county"));
			 promotionagentlist = iUserIncomeService.selectzxagentlist(promotionagenmap);
			for(HashMap<String, Object> promotionagent : promotionagentlist) {
				String agentId = promotionagent.get("agent_id")+"";
				user = getusedata("",agentId,UserConstants.USER_TYPE_AGENT);
				if(user!=null) {
					serve_rate = Double.valueOf(user.get("serveRate")==null?"0.0":user.get("serveRate")+"");
					insertdata(tissuenumAll*serve_rate,"03",RateConstants.SERVE_INCOME,0.0,tissuenumAll,user);
				}
				
			}
		}*/
		    //出纸二维码广告收益
			if(tissuenumAll>0) {
				HashMap<String, Object> promotionagenmap = new HashMap<String, Object>();
				HashMap<String, Object> repairmap = new HashMap<String, Object>();
				List<HashMap<String, Object>> promotionagentlist = new ArrayList<HashMap<String, Object>>();
				List<HashMap<String, Object>> repairlist = new ArrayList<HashMap<String, Object>>();
				 double rate = 0.0f;
				//-----------------------广告收益--------------
				//插入机主广告数据每次出纸收益0.3元
				user = getusedata(buyerid,"","");
				if(user!=null) {
					rate = Double.valueOf(user.get("scanRate")==null?"0.0":user.get("scanRate")+"");
					insertdata(rate*tissuenumAll,"01",RateConstants.RATETYPE_PAPERINCOME,0.0,tissuenumAll,user);
				}
	            if(com.zxiang.common.utils.StringUtils.isNotNull(map.get("place_id"))) {
	            	HashMap<String, Object> placemap =  selectzxplace(map.get("place_id") + ""); //获取地点
	            	//插入代理商广告数据（地级市代理地区所服务的机子每次出纸收益0.02元，县区、县级市代理地区所服务的机子每次出纸收益0.05元）
					    promotionagenmap.put("placeId", placemap.get("county"));
					    promotionagentlist = iUserIncomeService.selectzxagentlist(promotionagenmap);
	   				for(HashMap<String, Object> promotionagent : promotionagentlist) {
	   					String agentId = promotionagent.get("agent_id")+"";
	   					user = getusedata("",agentId,UserConstants.USER_TYPE_AGENT);
	   					if(user!=null) {
	   						rate = Double.valueOf(user.get("scanRate")==null?"0.0":user.get("scanRate")+"");
		   					insertdata(rate*tissuenumAll,"01",RateConstants.RATETYPE_PAPERINCOME,0.0,tissuenumAll,user);
	   					}
	   					
	   				}
	   				//插入服务商广告数据所服务的机子每次出纸收益0.05元
	   				 repairmap.put("countyId", placemap.get("county"));
	   				 repairlist = iUserIncomeService.selectzxrepairarealist(repairmap);
	   				 for(HashMap<String, Object> repair : repairlist) {
	   					   String repairId = repair.get("repair_id")+"";
	   					   user = getusedata("",repairId,UserConstants.USER_TYPE_REPAIR);
	   					   if(user!=null) {
	   						   rate = Double.valueOf(user.get("scanRate")==null?"0.0":user.get("scanRate")+"");
		   					   insertdata(rate*tissuenumAll,"01",RateConstants.RATETYPE_PAPERINCOME,0.0,tissuenumAll,user);
	   					   }
	   					   
	   				 }
				}
			}
		
		
		  //h5广告费用
		   if(tissuenum>0) {
				//--------------推广收益-----------------------
			    user = getusedata(promotionerh5,"","");
			    double rate = 0.0f;
			    if(user!=null) {
			    	rate =  Double.valueOf((user==null||user.get("promPaperRate")==null)?"0.0":user.get("promPaperRate")+"");
					insertdata(-tissuenum*rate,"02",RateConstants.RATETYPE_PROMPAPERINCOME,0.0,tissuenum,user);
					insertUserextensiondata(RateConstants.RATETYPE_PROMPAPERINCOME,0.0,tissuenum,user);
			    }
		   }
		//---------------------设备昨日出纸数量---------------------------------
		DeviceIncomeDaily deviceIncomeDaily = new DeviceIncomeDaily();
		deviceIncomeDaily.setDeviceId(deviceId);
		List<DeviceIncomeDaily> deviceIncomeDailylist = deviceIncomeDailyMapper.selectDeviceIncomeDaily(deviceIncomeDaily);
		deviceIncomeDaily.setScanIncome(tissuenumAll);//出纸数量
		if(deviceIncomeDailylist.size()>0){
			DeviceIncomeDaily income = deviceIncomeDailylist.get(0);
			deviceIncomeDaily.setIncomeId(income.getIncomeId());
			updateDeviceIncomeDaily(deviceIncomeDaily);
		}else{
			if(com.zxiang.common.utils.StringUtils.isNotNull(map.get("terminal_id"))) {
				deviceIncomeDaily.setTerminalId(Integer.valueOf(map.get("terminal_id")+""));
			}
			if(com.zxiang.common.utils.StringUtils.isNotNull(map.get("place_id"))) {
				deviceIncomeDaily.setPlaceId(Integer.valueOf(map.get("place_id") + ""));
			}
			insertDeviceIncomeDaily(deviceIncomeDaily);
		}
		
	}
	/**
	 * 计算广告费用
	 * */
	public void addata(HashMap<String, Object> map,String buyerid,int tissuenum) {
		boolean isplace = false;
		int deviceId = Integer.valueOf(map.get("device_id")+""); //设备id
		HashMap<String, Object> placemap =  null;
        if(com.zxiang.common.utils.StringUtils.isNotNull(map.get("place_id"))) {
        	placemap =  selectzxplace(map.get("place_id") + ""); //获取地点
        	isplace = true;
		}
        List<HashMap<String, Object>> releaserecordlist =selectreleaserecordlist(map);//广告投放设备
		for(HashMap<String, Object> releaserecord : releaserecordlist) {
			double price = Double.valueOf(releaserecord.get("price")==null?"0.0":releaserecord.get("price")+""); //广告价格
			//--------------------------------------客户昨日收入-----------------------------------------------
			//获取推广计划
			HashMap<String, Object> selecadschedule = selecadschedulelist(Integer.valueOf(releaserecord.get("schedule_id").toString()));
			String release_type = selecadschedule.get("release_position").toString(); //投放方式01终端轮播  02终端视频  03H5广告       (二维码广告还分公司（免费）和外部)
			boolean ispromotioner = true; 
			int	 promotioner = 0;
			HashMap<String, Object> user = null ;
			if(com.zxiang.common.utils.StringUtils.isNull(selecadschedule.get("promotioner"))) {
				ispromotioner = false;
			}else {
				 promotioner = Integer.valueOf(selecadschedule.get("promotioner")+""); //推荐人
				 user = getusedata(promotioner+"","","");
			}
			HashMap<String, Object> promotionagenmap = new HashMap<String, Object>();
			HashMap<String, Object> repairmap = new HashMap<String, Object>();
			List<HashMap<String, Object>> promotionagentlist = new ArrayList<HashMap<String, Object>>();
			List<HashMap<String, Object>> repairlist = new ArrayList<HashMap<String, Object>>();
			
			double rate = 0.0;//系数
			switch (release_type) {
			case "01":
				//--------------推广收益-----------------------
				if(ispromotioner) {
					if(user!=null) {
						rate = Double.valueOf(user.get("promotionRate")==null?"0.0":user.get("promotionRate")+"");
						insertdata(price*rate,"02",RateConstants.RATETYPE_PROMOTIONINCOME,price,0,user);
						insertUserextensiondata(RateConstants.RATETYPE_PROMOTIONINCOME,price,0,user);
					}
				}
				//-----------------------广告收益--------------
				//插入机主广告数据(视频广告投放金额40% , 轮播广告投放金额40%)
				user = getusedata(buyerid,"","");
				if(user!=null) {
					rate = Double.valueOf(user.get("adCarouselRate")==null?"0.0":user.get("adCarouselRate")+"");
					insertdata(rate*price,"01",RateConstants.RATETYPE_ADCAROUSELINCOME,price,0,user);
				}

				if(isplace) {
					//插入代理商广告数据（地级市代理地区所属机子视频广告投放金额2%、地区所属机子轮播广告投放金额2%，县区、县级市代理地区所属机子视频广告投放金额3%、地区所属机子轮播广告投放金额3%）
					 promotionagenmap.put("placeId", placemap.get("county"));
					 promotionagentlist = iUserIncomeService.selectzxagentlist(promotionagenmap);
					for(HashMap<String, Object> promotionagent : promotionagentlist) {
						String agentId = promotionagent.get("agent_id")+"";
						user = getusedata("",agentId,UserConstants.USER_TYPE_AGENT);
						if(user==null) {
							continue;
						}
						rate =  Double.valueOf(user.get("adCarouselRate")==null?"0.0":user.get("adCarouselRate")+"");
						insertdata(rate*price,"01",RateConstants.RATETYPE_ADCAROUSELINCOME,price,0,user);
					}
					//插入服务商广告数据（所服务的机子视频广告投放金额3%,所服务的机子轮播广告投放金额3%）
					 repairmap.put("countyId", placemap.get("county"));
					 repairlist = iUserIncomeService.selectzxrepairarealist(repairmap);
					 for(HashMap<String, Object> repair : repairlist) {
						   String repairId = repair.get("repair_id")+"";
						   user = getusedata("",repairId,UserConstants.USER_TYPE_REPAIR);
						   if(user==null) {
							   continue;
						   }
						   rate = Double.valueOf(user.get("adCarouselRate")==null?"0.0":user.get("adCarouselRate")+"");
						   insertdata(rate*price,"01",RateConstants.RATETYPE_ADCAROUSELINCOME,price,0,user);
					 }
				}
				break;
			case "02":
				//--------------推广收益-----------------------
				if(ispromotioner) {
					rate = Double.valueOf(user.get("promotionRate")==null?"0.0":user.get("promotionRate")+"");
					insertdata(price*rate,"02",RateConstants.RATETYPE_PROMOTIONINCOME,price,0,user);
					insertUserextensiondata(RateConstants.RATETYPE_PROMOTIONINCOME,price,0,user);
				}
				//-----------------------广告收益--------------
				//插入机主广告数据(视频广告投放金额40% , 轮播广告投放金额40%)
				user = getusedata(buyerid,"","");
				if(user!=null) {
					rate =Double.valueOf(user.get("adRate")==null?"0.0":user.get("adRate")+"");
					insertdata(rate*price,"01",RateConstants.RATETYPE_ADINCOME,price,0,user);
				}
				
	
                if(isplace) {
                	//插入代理商广告数据（地级市代理地区所属机子视频广告投放金额2%、地区所属机子轮播广告投放金额2%，县区、县级市代理地区所属机子视频广告投放金额3%、地区所属机子轮播广告投放金额3%）
	   				 promotionagenmap.put("placeId", placemap.get("county"));
	   				 promotionagentlist = iUserIncomeService.selectzxagentlist(promotionagenmap);
	   				for(HashMap<String, Object> promotionagent : promotionagentlist) {
	   					String agentId = promotionagent.get("agent_id")+"";
	   					user = getusedata("",agentId,UserConstants.USER_TYPE_AGENT);
	   					if(user!=null) {
	   						rate = Double.valueOf(user.get("adRate")==null?"0.0":user.get("adRate")+"");
		   					insertdata(rate*price,"01",RateConstants.RATETYPE_ADINCOME,price,0,user);
	   					}
	   				}
	   				//插入服务商广告数据（所服务的机子视频广告投放金额3%,所服务的机子轮播广告投放金额3%）
	   				 repairmap.put("countyId", placemap.get("county"));
	   				 repairlist = iUserIncomeService.selectzxrepairarealist(repairmap);
	   				 for(HashMap<String, Object> repair : repairlist) {
	   					   String repairId = repair.get("repair_id")+"";
	   					   user = getusedata("",repairId,UserConstants.USER_TYPE_REPAIR);
	   					   if(user!=null) {
	   						  rate = Double.valueOf(user.get("adRate")==null?"0.0":user.get("adRate")+"");
		   					  insertdata(rate*price,"01",RateConstants.RATETYPE_ADINCOME,price,0,user);
	   					   }
	   				 }
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
				if(com.zxiang.common.utils.StringUtils.isNotNull(map.get("terminal_id"))) {
					deviceIncomeDaily.setTerminalId(Integer.valueOf(map.get("terminal_id")+""));
				}
				if(com.zxiang.common.utils.StringUtils.isNotNull(map.get("place_id"))) {
					deviceIncomeDaily.setPlaceId(Integer.valueOf(map.get("place_id") + ""));
				}
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
			 if(com.zxiang.common.utils.StringUtils.isNotNull(promotionagent.get("promotor_id"))) {
			   double agency_fee = Double.valueOf(promotionagent.get("agency_fee")==null?"0.0":promotionagent.get("agency_fee")+"");
			   HashMap<String, Object>  user = getusedata(promotionagent.get("promotor_id")+"","","");
			   if(user!=null) {
				   double rate = Double.valueOf(user.get("directAgentRate")==null?"0.0":user.get("directAgentRate")+"");
				   insertdata(agency_fee*rate,"02",RateConstants.RATETYPE_DIRECTAGENTINCOME,agency_fee,0,user);
				   insertUserextensiondata(RateConstants.RATETYPE_DIRECTAGENTINCOME,agency_fee,0,user);
			   }
			 }
		 }
		
	}
	
	//获取购机推荐人信息
	public HashMap<String, Object> getpromotionerdata(String buyerid) {
		HashMap<String, Object> getpromotioner= new HashMap<String, Object>();
		String puser_id = "";
		String user_type = "";
		HashMap<String, Object> promotionerdata = new HashMap<String, Object>();
		if(com.zxiang.common.utils.StringUtils.isNotNull(buyerid)) {
		   promotionerdata = iUserIncomeService.selectzxsellerlist(buyerid);
		}
		if(promotionerdata == null || !promotionerdata.containsKey("puser_id")) {
			return new HashMap<String, Object>();
		}
		puser_id = promotionerdata.get("puser_id")+""; //主体ID
		user_type = promotionerdata.get("user_type")+""; //用户类型
		HashMap<String, Object> puser = new HashMap<String, Object>();
		puser.put("joinId", puser_id);
		List<HashMap<String, Object>> user=iUserIncomeService.selectzxjoinlist(puser);
		if(user.size()>0) {
			HashMap<String, Object> userdata =  user.get(0);
			if(com.zxiang.common.utils.StringUtils.isNotNull(userdata.get("leader_id"))) {
				getpromotioner.put("promotor_id", userdata.get("leader_id"));
				promotionerdata = iUserIncomeService.selectzxsellerlist(userdata.get("leader_id")+"");
				puser_id = promotionerdata.get("puser_id")+""; //主体ID
				user_type = promotionerdata.get("user_type")+""; //用户类型
				List<HashMap<String, Object>> user1 = new ArrayList<HashMap<String, Object>>();
				if(user_type.equals(UserConstants.USER_TYPE_JOIN)) {
					puser.put("joinId", puser_id);
					user1=iUserIncomeService.selectzxjoinlist(puser);
				}else if(user_type.equals(UserConstants.USER_TYPE_REPAIR)) {
					puser.put("repairId", puser_id);
					user1=iUserIncomeService.selectzxrepairlist(puser);
				}else if(user_type.equals(UserConstants.USER_TYPE_AGENT)) {
					puser.put("agentId", puser_id);
					user1=iUserIncomeService.selectzxagent(puser);
				}
				if(user1.size()>0) {
					HashMap<String, Object> userdata1 =  user1.get(0);
					if(com.zxiang.common.utils.StringUtils.isNotNull(userdata1.get("leader_id"))) {
						getpromotioner.put("leader_id", userdata1.get("leader_id"));
					}
				}
				return getpromotioner;
			}
		}
		return new HashMap<String, Object>();
	}
	
	
	//获取用户信息
	public HashMap<String, Object> getusedata(String buyerid,String puser_id,String user_type) {
		HashMap<String, Object> promotionerdata = new HashMap<String, Object>();
		if(com.zxiang.common.utils.StringUtils.isNotNull(buyerid)) {
			   promotionerdata = iUserIncomeService.selectzxsellerlist(buyerid);
		}
		if(promotionerdata == null || !promotionerdata.containsKey("puser_id")) {
				return new HashMap<String, Object>();
		}
		puser_id = promotionerdata.get("puser_id")+""; //主体ID
		user_type = promotionerdata.get("user_type")+""; //用户类型
		HashMap<String, Object> puser = new HashMap<String, Object>();
		List<HashMap<String, Object>> user = new ArrayList<HashMap<String, Object>>();
		if(user_type.equals(UserConstants.USER_TYPE_JOIN)) {
			puser.put("joinId", puser_id);
			user=iUserIncomeService.selectzxjoinlist(puser);
		}else if(user_type.equals(UserConstants.USER_TYPE_REPAIR)) {
			puser.put("repairId", puser_id);
			user=iUserIncomeService.selectzxrepairlist(puser);
		}else if(user_type.equals(UserConstants.USER_TYPE_AGENT)) {
			puser.put("agentId", puser_id);
			user=iUserIncomeService.selectzxagent(puser);
		}
		if(user.size()>0) {
			HashMap<String, Object> userdata =  user.get(0);
			userdata.put("coperatorType", user_type);
			userdata.put("userId", promotionerdata.get("user_id"));
			userdata.put("userName", promotionerdata.get("user_name"));
			return userdata;
		}
		return new HashMap<String, Object>();
	}
	
	
	//收益   price ：收益值      buyerid ：客户id   type ：收益类型     ratetype ：系数类型     incomeprice：收益基数（小数型）  incomenum ：基数数量     user：客户信息
	public void insertdata(double price,String type,String ratetype,double incomeprice,int incomenum, HashMap<String, Object> user) {
		    //投放方式01广告收益   02推广收益  03扫码服务收益 04办公补贴
			UserIncome userIncome = new UserIncome();
			if(!user.containsKey("coperatorId")) {
				return;
			}
			userIncome.setCoperatorId(Integer.valueOf(user.get("coperatorId")+""));
			userIncome.setCoperatorType(user.get("coperatorType")+"");
			List<UserIncome> userlist =iUserIncomeService.selectUserIncome(userIncome);
			if(type.equals("01")) {
				userIncome.setAdIncomeRate(price);//广告收益
			}else if(type.equals("02")) {
				userIncome.setPromotionIncomeRate(price);//推广收益
			}else if(type.equals("03")){
				userIncome.setScanIncomeRate(price);//扫码服务收益
			}else{
				userIncome.setSubsidyIncomeRate(price);
			}
			
			if(ratetype.equals(RateConstants.RATETYPE_ADINCOME)) {
				userIncome.setAdIncome(incomeprice); //视频广告基数
			}else if(ratetype.equals(RateConstants.RATETYPE_ADCAROUSELINCOME)) {
				userIncome.setAdCarouselIncome(incomeprice);//轮播广告基数
			}else if(ratetype.equals(RateConstants.RATETYPE_PROMDIRECTINCOME)) {
				userIncome.setPromDirectIncome(incomenum);//直推机子数量
			}else if(ratetype.equals(RateConstants.RATETYPE_PROMINDIRECTINCOME)) {
				userIncome.setPromIndirectIncome(incomenum);//间推机子数量
			}else if(ratetype.equals(RateConstants.RATETYPE_PAPERINCOME)) {
				userIncome.setPaperIncome(incomenum);//出纸数量
			}else if(ratetype.equals(RateConstants.SERVE_INCOME)) {
				userIncome.setServeIncome(incomenum);//服务出纸数量
			}else if(ratetype.equals(RateConstants.RATETYPE_DIRECTAGENTINCOME)) {
				userIncome.setDirectAgentIncome(incomeprice);//直推代理基数
			}else if(ratetype.equals(RateConstants.RATETYPE_SUBSIDYINCOME)){
				userIncome.setSubsidyIncome(incomeprice);//招商金额
			}else if(ratetype.equals(RateConstants.RATETYPE_PROMPAPERINCOME)){
				userIncome.setPromPaperIncome(incomenum);//推广二维码广告
			}else if(ratetype.equals(RateConstants.RATETYPE_PROMOTIONINCOME)){
				userIncome.setPromotionIncome(incomeprice);//推广广告基数
			}
			
			if(userlist.size()>0){
				UserIncome income = userlist.get(0);
				userIncome.setIncomeId(income.getIncomeId());
				iUserIncomeService.updateUserIncome(userIncome);
			}else{
				userIncome.setCoperatorName(user.get("coperatorName")+"");
				userIncome.setAdRate(Float.valueOf(user.get("adRate")+"")); //视频广告系数
				userIncome.setAdCarouselRate(Float.valueOf(user.get("adCarouselRate")+""));//轮播广告系数
				userIncome.setScanRate(Float.valueOf(user.get("scanRate")+""));//二维码广告系数
				userIncome.setPromDirectRate(Float.valueOf(user.get("promDirectRate")+""));//直推机子分润
				userIncome.setPromIndirectRate(Float.valueOf(user.get("promIndirectRate")+""));//间推机子分润
				userIncome.setPromPaperRate(Float.valueOf(user.get("promPaperRate")+""));//推广出纸收益
				userIncome.setPromotionRate(Float.valueOf(user.get("promotionRate")+""));//推广广告系数
				userIncome.setDirectAgentRate(Float.valueOf(user.get("directAgentRate")+""));//直推代理分润系数
				userIncome.setServeRate(Float.valueOf(user.get("serveRate")+""));//服务出纸收益
				userIncome.setSubsidyRate(Float.valueOf(user.get("subsidyRate")+""));//办公补贴
				iUserIncomeService.insertUserIncome(userIncome);
			}		
			
	}
	
	    //ratetype ：系数类型     incomeprice：收益基数（小数型）  incomenum ：基数数量     user：客户信息
	public void insertUserextensiondata(String ratetype,double incomeprice,int incomenum, HashMap<String, Object> user) {
		    //投放方式01广告收益   02推广收益  03扫码服务收益 04办公补贴
		    UserExtension userIncome = new UserExtension();
		    if(!user.containsKey("userId")) {
		    	return;
		    }
			userIncome.setCoperatorId(Integer.valueOf(user.get("userId")+""));
			userIncome.setCoperatorType(user.get("coperatorType")+"");
			List<UserExtension> userlist =userExtensionService.selectUserExtension(userIncome);
			
		    if(ratetype.equals(RateConstants.RATETYPE_PROMDIRECTINCOME)) {
				userIncome.setPromDirectIncome(incomenum);//直推机子数量
			}else if(ratetype.equals(RateConstants.RATETYPE_PROMINDIRECTINCOME)) {
				userIncome.setPromIndirectIncome(incomenum);//间推机子数量
			}else if(ratetype.equals(RateConstants.RATETYPE_DIRECTAGENTINCOME)) {
				userIncome.setDirectAgentIncome(incomeprice);//直推代理基数
			}else if(ratetype.equals(RateConstants.RATETYPE_PROMPAPERINCOME)){
				userIncome.setPromPaperIncome(incomenum);//推广二维码广告
			}else if(ratetype.equals(RateConstants.RATETYPE_PROMOTIONINCOME)){
				userIncome.setPromotionIncome(incomeprice);//推广广告基数
			}else if(ratetype.equals(RateConstants.BUYING_MACHINE)){
				userIncome.setBuyingMachine(incomenum);//购机数量
			}
		    
			if(userlist.size()>0){
				UserExtension income = userlist.get(0);
				userIncome.setIncomeId(income.getIncomeId());
				userExtensionService.updateUserExtension(userIncome);
			}else{
				userIncome.setCoperatorName(user.get("userName")+"");
				userIncome.setPuserId(Integer.valueOf(user.get("coperatorId")+""));
				userIncome.setPuserName(user.get("coperatorName")+"");
				userExtensionService.insertUserExtension(userIncome);
			}		
			
	}
	
	

	//场所管理
	@Override
	public HashMap<String, Object> selectzxplace(String placeId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("placeId", placeId);
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
	
	public float datatofloat(double num) {
		DecimalFormat   df   =     new   DecimalFormat( "########0.00 ");// 
		String   temp     =   df.format(num); 
		return Float.valueOf(temp);
		
	}
	public double datatodouble(double num) {
		DecimalFormat   df   =     new   DecimalFormat( "###########0.00 ");//   16位整数位，两小数位
		String   temp     =   df.format(num); 
		return Double.valueOf(temp);
	}
	
}
