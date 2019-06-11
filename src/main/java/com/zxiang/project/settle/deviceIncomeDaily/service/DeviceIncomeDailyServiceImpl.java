package com.zxiang.project.settle.deviceIncomeDaily.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.zxiang.common.constant.Const;
import com.zxiang.common.constant.RateConstants;
import com.zxiang.common.constant.UserConstants;
import com.zxiang.common.exception.RRException;
import com.zxiang.common.support.Convert;
import com.zxiang.project.business.device.domain.Device;
import com.zxiang.project.business.device.mapper.DeviceMapper;
import com.zxiang.project.business.tissueRecord.mapper.TissueRecordMapper;
import com.zxiang.project.client.advertise.domain.Advertise;
import com.zxiang.project.client.advertise.mapper.AdvertiseMapper;
import com.zxiang.project.client.agent.domain.Agent;
import com.zxiang.project.client.agent.mapper.AgentMapper;
import com.zxiang.project.client.fundLog.domain.FundLog;
import com.zxiang.project.client.fundLog.mapper.FundLogMapper;
import com.zxiang.project.client.fundLog.service.IFundLogService;
import com.zxiang.project.client.join.domain.Join;
import com.zxiang.project.client.join.mapper.JoinMapper;
import com.zxiang.project.client.repair.domain.Repair;
import com.zxiang.project.client.repair.mapper.RepairMapper;
import com.zxiang.project.settle.deviceIncomeDaily.domain.DeviceIncomeDaily;
import com.zxiang.project.settle.deviceIncomeDaily.mapper.DeviceIncomeDailyMapper;
import com.zxiang.project.settle.userExtension.domain.UserExtension;
import com.zxiang.project.settle.userExtension.mapper.UserExtensionMapper;
import com.zxiang.project.settle.userExtension.service.IUserExtensionService;
import com.zxiang.project.settle.userIncome.domain.UserIncome;
import com.zxiang.project.settle.userIncome.mapper.UserIncomeMapper;
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
	
	@Autowired
	private FundLogMapper fundLogMapper;
	
	@Autowired
	private UserIncomeMapper userIncomeMapper;
	@Autowired
	private UserExtensionMapper userExtensionMapper;
	
	@Autowired
	private AgentMapper agentMapper;
	@Autowired
	private JoinMapper joinMapper;
	@Autowired
	private RepairMapper repairMapper;
	@Autowired
	private AdvertiseMapper advertiseMapper;
	@Autowired
	private TissueRecordMapper tissueRecordMapper;
	@Autowired
	private DeviceMapper deviceMapper;
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
	public List<HashMap<String, Object>> selectCurrentdeviceorderlist(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return deviceIncomeDailyMapper.selectCurrentdeviceorderlist(map);
	}

	@Override
	public List<HashMap<String, Object>> selectzxtissuerecordlist(String deviceId,String scheduleId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("deviceId", deviceId);
		map.put("scheduleId", scheduleId);
		return deviceIncomeDailyMapper.selectzxtissuerecordlist(map);
	}
	
	@Override
	public List<HashMap<String, Object>> selectcurrenttissuerecordlist(HashMap<String,Object> map) {
		return deviceIncomeDailyMapper.selectcurrenttissuerecordlist(map);
	}
	
	@Override
	public int selectzxtissuerecordAll(String deviceId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("deviceId", deviceId);
		return deviceIncomeDailyMapper.selectzxtissuerecordAll(map);
	}
	
	@Override
	public int selectcurrenttissuerecordAll(HashMap<String,Object> map) {
		return deviceIncomeDailyMapper.selectcurrenttissuerecordAll(map);
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
				String buyer_id = device.get("owner_id")+"";//机主
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
			//增加每日统计出纸次数
			Device deviceEntity = deviceMapper.selectDeviceById(Integer.parseInt(device.get("device_id")+""));
			HashMap<String,String> paramMap = new HashMap<String,String>();
			paramMap.put("deviceId", device.get("device_id")+"");
			HashMap<String,Object> tissueMap = tissueRecordMapper.tissueCount(paramMap);
			if(tissueMap.containsKey("paperSum")) {
				deviceEntity.setTotalCnt(Long.parseLong(tissueMap.get("paperSum")+""));
			}else {
				deviceEntity.setTotalCnt(0l);
			}
			if(tissueMap.containsKey("validPaperSum")) {
				deviceEntity.setValidCnt(Long.parseLong(tissueMap.get("validPaperSum")+""));
			}else {
				deviceEntity.setValidCnt(0l);
			}
			if(tissueMap.containsKey("invalidPaperSum")) {
				deviceEntity.setInvalidCnt(Long.parseLong(tissueMap.get("invalidPaperSum")+""));
			}else {
				deviceEntity.setInvalidCnt(0l);
			}
			deviceMapper.updateDevice(deviceEntity);
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
	
	//计算每日设备推广费用
	public void deviceCurorder(String isincome,String seller_id,HashMap<String, Object> map,HashMap<String, Object> order,Date currentTime) {
		
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
									insertCurdata(fee,"02",type,0.0,1,user,currentTime);
									insertCurUserextensiondata(type,0.0,1, user,currentTime);
								}
							}
							user = getusedata(seller_id,"","");
							if(user!=null) {
								insertCurUserextensiondata(RateConstants.BUYING_MACHINE,0.0,1, user,currentTime);
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
	public void tissueCurdata(HashMap<String, Object> map,String buyerid,int tissuenumAll,int tissuenum,String promotionerh5,Date currentTime) {
		//获取机主的信息
		HashMap<String, Object> user = getusedata(buyerid,"","");
		int deviceId = Integer.valueOf(map.get("device_id")+""); //设备id
		if(user.get("leader_id") !=null && user.get("leader_id") !="" ){ //直推人
			//获取推荐人人员信息
			 user = getusedata(user.get("leader_id")+"","","");
			if(com.zxiang.common.utils.StringUtils.isNotNull(user)){
				double serve_rate = Double.valueOf((user==null||user.get("serveRate")==null)?"0.0":user.get("serveRate")+"");
				insertCurdata(tissuenumAll*serve_rate,"03",RateConstants.SERVE_INCOME,0.0,tissuenumAll,user,currentTime);
				if(user.get("leader_id") !=null && user.get("leader_id") !="" ){ //间推人
					 user = getusedata(user.get("leader_id")+"","","");
					if(com.zxiang.common.utils.StringUtils.isNotNull(user)){
						 serve_rate = Double.valueOf((user==null||user.get("serveRate")==null)?"0.0":user.get("serveRate")+"");
						insertCurdata(tissuenumAll*serve_rate,"03",RateConstants.SERVE_INCOME,0.0,tissuenumAll,user,currentTime);
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
					insertCurdata(rate*tissuenumAll,"01",RateConstants.RATETYPE_PAPERINCOME,0.0,tissuenumAll,user,currentTime);
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
		   					insertCurdata(rate*tissuenumAll,"01",RateConstants.RATETYPE_PAPERINCOME,0.0,tissuenumAll,user,currentTime);
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
		   					   insertCurdata(rate*tissuenumAll,"01",RateConstants.RATETYPE_PAPERINCOME,0.0,tissuenumAll,user,currentTime);
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
					insertCurdata(-tissuenum*rate,"02",RateConstants.RATETYPE_PROMPAPERINCOME,0.0,tissuenum,user,currentTime);
					insertCurUserextensiondata(RateConstants.RATETYPE_PROMPAPERINCOME,0.0,tissuenum,user,currentTime);
			    }
		   }
		//---------------------设备昨日出纸数量---------------------------------
		DeviceIncomeDaily deviceIncomeDaily = new DeviceIncomeDaily();
		deviceIncomeDaily.setDeviceId(deviceId);
		deviceIncomeDaily.setSumDate(currentTime);
		List<DeviceIncomeDaily> deviceIncomeDailylist = deviceIncomeDailyMapper.selectCurDeviceIncomeDaily(deviceIncomeDaily);
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
			deviceIncomeDailyMapper.insertCurDeviceIncomeDaily(deviceIncomeDaily);
//			insertDeviceIncomeDaily(deviceIncomeDaily);
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
	 * 计算广告费用
	 * */
	public void adCurdata(HashMap<String, Object> map,String buyerid,int tissuenum,Date currentTime) {
		boolean isplace = false;
		int deviceId = Integer.valueOf(map.get("device_id")+""); //设备id
		HashMap<String, Object> placemap =  null;
        if(com.zxiang.common.utils.StringUtils.isNotNull(map.get("place_id"))) {
        	placemap =  selectzxplace(map.get("place_id") + ""); //获取地点
        	isplace = true;
		}
        map.put("currentTime", currentTime);
        List<HashMap<String, Object>> releaserecordlist = deviceIncomeDailyMapper.selectcurreleaserecordlist(map);//广告投放设备
		for(HashMap<String, Object> releaserecord : releaserecordlist) {
			double price = Double.valueOf(releaserecord.get("price")==null?"0.0":releaserecord.get("price")+""); //广告价格
			//--------------------------------------客户昨日收入-----------------------------------------------
			//获取推广计划
			HashMap<String, Object> selecadschedule = selecadschedulelist(Integer.valueOf(releaserecord.get("schedule_id").toString()));
			if(selecadschedule!=null) {
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
							insertCurdata(price*rate,"02",RateConstants.RATETYPE_PROMOTIONINCOME,price,0,user,currentTime);
							insertCurUserextensiondata(RateConstants.RATETYPE_PROMOTIONINCOME,price,0,user,currentTime);
						}
					}
					//-----------------------广告收益--------------
					//插入机主广告数据(视频广告投放金额40% , 轮播广告投放金额40%)
					user = getusedata(buyerid,"","");
					if(user!=null) {
						rate = Double.valueOf(user.get("adCarouselRate")==null?"0.0":user.get("adCarouselRate")+"");
						insertCurdata(rate*price,"01",RateConstants.RATETYPE_ADCAROUSELINCOME,price,0,user,currentTime);
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
							insertCurdata(rate*price,"01",RateConstants.RATETYPE_ADCAROUSELINCOME,price,0,user,currentTime);
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
							   insertCurdata(rate*price,"01",RateConstants.RATETYPE_ADCAROUSELINCOME,price,0,user,currentTime);
						 }
					}
					break;
				case "02":
					//--------------推广收益-----------------------
					if(ispromotioner) {
						rate = Double.valueOf(user.get("promotionRate")==null?"0.0":user.get("promotionRate")+"");
						insertCurdata(price*rate,"02",RateConstants.RATETYPE_PROMOTIONINCOME,price,0,user,currentTime);
						insertCurUserextensiondata(RateConstants.RATETYPE_PROMOTIONINCOME,price,0,user,currentTime);
					}
					//-----------------------广告收益--------------
					//插入机主广告数据(视频广告投放金额40% , 轮播广告投放金额40%)
					user = getusedata(buyerid,"","");
					if(user!=null) {
						rate =Double.valueOf(user.get("adRate")==null?"0.0":user.get("adRate")+"");
						insertCurdata(rate*price,"01",RateConstants.RATETYPE_ADINCOME,price,0,user,currentTime);
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
			   					insertCurdata(rate*price,"01",RateConstants.RATETYPE_ADINCOME,price,0,user,currentTime);
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
			   					  insertCurdata(rate*price,"01",RateConstants.RATETYPE_ADINCOME,price,0,user,currentTime);
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
				deviceIncomeDaily.setSumDate(currentTime);
				List<DeviceIncomeDaily> deviceIncomeDailylist = deviceIncomeDailyMapper.selectCurDeviceIncomeDaily(deviceIncomeDaily);
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
					deviceIncomeDaily.setSumDate(currentTime);
					deviceIncomeDailyMapper.insertCurDeviceIncomeDaily(deviceIncomeDaily);
//					insertDeviceIncomeDaily(deviceIncomeDaily);
				}
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
	
	/**
	 * 
	 * 计算推广代理收益(直推代理分润，代理费的15%)
	 *
	 * */
	public void curpromotionagent(Date currentTime) {
		//获取昨日添加得代理商
		 HashMap<String, Object> map = new HashMap<String, Object>();
		 map.put("currentTime", currentTime);
		 List<HashMap<String, Object>> promotionagentlist = userIncomeMapper.selectcuragentlist(map);
		 for(HashMap<String, Object> promotionagent : promotionagentlist) {
			 if(com.zxiang.common.utils.StringUtils.isNotNull(promotionagent.get("promotor_id"))) {
			   double agency_fee = Double.valueOf(promotionagent.get("agency_fee")==null?"0.0":promotionagent.get("agency_fee")+"");
			   HashMap<String, Object>  user = getusedata(promotionagent.get("promotor_id")+"","","");
			   if(user!=null) {
				   double rate = Double.valueOf(user.get("directAgentRate")==null?"0.0":user.get("directAgentRate")+"");
				   insertCurdata(agency_fee*rate,"02",RateConstants.RATETYPE_DIRECTAGENTINCOME,agency_fee,0,user,currentTime);
				   insertCurUserextensiondata(RateConstants.RATETYPE_DIRECTAGENTINCOME,agency_fee,0,user,currentTime);
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
				if(com.zxiang.common.utils.StringUtils.isNull(promotionerdata)) {
					return new HashMap<String, Object>();
				}
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
			   if(promotionerdata == null || !promotionerdata.containsKey("puser_id")) {
					return new HashMap<String, Object>();
			   }	
			   puser_id = promotionerdata.get("puser_id")+""; //主体ID
			   user_type = promotionerdata.get("user_type")+""; //用户类型
		}
		
		
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
	
	public void insertCurdata(double price,String type,String ratetype,double incomeprice,int incomenum, HashMap<String, Object> user,Date currentTime) {
	    //投放方式01广告收益   02推广收益  03扫码服务收益 04办公补贴
		UserIncome userIncome = new UserIncome();
		if(!user.containsKey("coperatorId")) {
			return;
		}
		userIncome.setCoperatorId(Integer.valueOf(user.get("coperatorId")+""));
		userIncome.setCoperatorType(user.get("coperatorType")+"");
		userIncome.setSumDate(currentTime);
		List<UserIncome> userlist =userIncomeMapper.selectCurrentUserIncome(userIncome);
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
			userIncomeMapper.updateUserIncome(userIncome);
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
			userIncomeMapper.insertCurUserIncome(userIncome);
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
	
	public void insertCurUserextensiondata(String ratetype,double incomeprice,int incomenum, HashMap<String, Object> user,Date currentTime) {
	    //投放方式01广告收益   02推广收益  03扫码服务收益 04办公补贴
	    UserExtension userIncome = new UserExtension();
	    if(!user.containsKey("userId")) {
	    	return;
	    }
		userIncome.setCoperatorId(Integer.valueOf(user.get("userId")+""));
		userIncome.setCoperatorType(user.get("coperatorType")+"");
		List<UserExtension> userlist =userExtensionMapper.selectUserExtension(userIncome);
		
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
			userExtensionMapper.updateUserExtension(userIncome);
		}else{
			userIncome.setCoperatorName(user.get("userName")+"");
			userIncome.setPuserId(Integer.valueOf(user.get("coperatorId")+""));
			userIncome.setPuserName(user.get("coperatorName")+"");
			userExtensionMapper.insertUserExtension(userIncome);
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

	@Override
	public void restatisticaldata(Date currentTime) {
		//推广出纸数 得收益是公司得所以减去
		// 办公费用就是代理费得*系数
		//另外单个二维码第一次出纸为有效，第2-5次出纸为无效出纸 不计算出纸收益
		//获取所有设备信息
		UserIncome userIncome = new UserIncome();
		userIncome.setSumDate(currentTime);
		List<UserIncome> userIncomeList = userIncomeMapper.selectCurrentUserIncome(userIncome);
		DeviceIncomeDaily deviceIncomeDaily = new DeviceIncomeDaily();
		deviceIncomeDaily.setSumDate(currentTime);
		List<DeviceIncomeDaily> deviceIncomeDailylist = deviceIncomeDailyMapper.selectCurDeviceIncomeDaily(deviceIncomeDaily);
		if(userIncomeList.size()>0 || deviceIncomeDailylist.size()>0) {
			return;
		}
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<HashMap<String, Object>> devicelist =selectzxdevicelist(map);
	    //计算前一日售出设备的金额
		for (HashMap<String, Object> device : devicelist) {
			map = new HashMap<String, Object>();
			map.put("deviceId", device.get("device_id"));
			map.put("currentTime",currentTime);
			List<HashMap<String, Object>> orderlist =selectCurrentdeviceorderlist(map);
			if(orderlist.size()>0){
				HashMap<String, Object> order = orderlist.get(0);
				String buyer_id = device.get("owner_id")+"";//机主
				String isincome = order.get("isincome")+""; //是否是当天售出
				
				HashMap<String,Object> tissueMap = new HashMap<String,Object>();
				tissueMap.put("deviceId", device.get("device_id"));
				tissueMap.put("scheduleId", "");
				tissueMap.put("currentTime",currentTime);
				List<HashMap<String, Object>> tissuenumlist= selectcurrenttissuerecordlist(tissueMap); //出纸数量
				int tissuenum = tissuenumlist.size();
				tissueMap.clear();
				tissueMap.put("deviceId", device.get("device_id"));
				tissueMap.put("currentTime",currentTime);
				int tissuenumAll = selectcurrenttissuerecordAll(tissueMap);
				//计算每日设备推广费用
				deviceCurorder(isincome,buyer_id,device,order,currentTime);
				if(tissuenumAll>0) {
					String	promotionerh5 ="";
					if(tissuenum>0) {
						promotionerh5 = tissuenumlist.get(0).get("promotioner").toString();
					}
					//计算每日出纸费用
					tissueCurdata(device,buyer_id,tissuenumAll,tissuenum,promotionerh5,currentTime);
				}
				//计算广告费用
				adCurdata(device,buyer_id,tissuenum,currentTime);
			}
		}
		//需要推广代理人（查询前一天加入代理商）
		curpromotionagent(currentTime);
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("currentTime", currentTime);
		List<HashMap<String, Object>> UsertotalIncomelist = userIncomeMapper.selectCurUsertotalIncome(param);
		for(HashMap<String, Object> UsertotalIncome : UsertotalIncomelist) {
			BigDecimal totalIncome = new BigDecimal(UsertotalIncome.get("totalIncome")+"");
			String puserId = UsertotalIncome.get("puserId")+"";
			String puserType = UsertotalIncome.get("puserType")+"";
			incomeCurRecord(Integer.valueOf(puserId), puserType, totalIncome,currentTime);
		}
		 
	}
	
	public void incomeCurRecord(Integer clientId, String clientType, BigDecimal money,Date currentTime) {

			logger.info("收益统计，clientId："+clientId+",clientType："+clientType+",money："+money);
			if(money == null || money.compareTo(new BigDecimal(0)) < 0) {
				throw new RRException("收益余额须大于0，clientId："+clientId+",clientType："+clientType);
			}
			BigDecimal balance = null;
			BigDecimal frozenBalance = null;
			if(UserConstants.USER_TYPE_JOIN.equals(clientType)) { // 机主
				Join join = joinMapper.selectJoinById(clientId);
				if(join == null) {
					throw new RRException("未找到客户，clientId："+clientId+",clientType："+clientType);
				}
				balance = join.getBalance() == null ? money : join.getBalance().add(money);
				frozenBalance = join.getFrozenBalance() == null ? new BigDecimal(0) : join.getFrozenBalance();
				joinMapper.updateBalance(clientId, balance, null);
			} else if(UserConstants.USER_TYPE_AGENT.equals(clientType)) { // 代理商
				Agent agent = agentMapper.selectAgentById(clientId);
				if(agent == null) {
					throw new RRException("未找到客户，clientId："+clientId+",clientType："+clientType);
				}
				balance = agent.getBalance() == null ? money : agent.getBalance().add(money);
				frozenBalance = agent.getFrozenBalance() == null ? new BigDecimal(0) : agent.getFrozenBalance();
				agentMapper.updateBalance(clientId, balance, null);
			} else if(UserConstants.USER_TYPE_REPAIR.equals(clientType)) { // 服务商
				Repair repair = repairMapper.selectRepairById(clientId);
				if(repair == null) {
					throw new RRException("未找到客户，clientId："+clientId+",clientType："+clientType);
				}
				balance = repair.getBalance() == null ? money : repair.getBalance().add(money);
				frozenBalance = repair.getFrozenBalance() == null ? new BigDecimal(0) : repair.getFrozenBalance();
				repairMapper.updateBalance(clientId, balance, null);
			} else {
				throw new RRException("客户类型有误，clientType："+clientType);
			}
			balance = balance.subtract(frozenBalance).setScale(2, BigDecimal.ROUND_HALF_UP); // 四舍五入 保留两位
			money = money.setScale(2, BigDecimal.ROUND_HALF_UP); // 四舍五入 保留两位
			FundLog fundLog = new FundLog();
			fundLog.setBalance(balance.toString()); // 可用余额
			fundLog.setTotalFee("+"+money);
			fundLog.setClientId(clientId);
			fundLog.setClientType(clientType);
			fundLog.setContent("收益");
			fundLog.setType(Const.FUND_INCOME);
			fundLog.setStatus(Const.STATUS_SUCCESS);
			Calendar cal = Calendar.getInstance();
			cal.setTime(currentTime);
			cal.add(Calendar.DAY_OF_YEAR, 1);
			fundLog.setCreateTime(cal.getTime());
			fundLogMapper.insertFundLog(fundLog);
	}
	
	@Override
	@Transactional
	public Map<String,Object> reCalc(DeviceIncomeDaily deviceIncomeDaily) {
		Map<String,Object> retMap = new HashMap<String,Object>();
		try {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		// 判断条件
		String beginTimeStr = deviceIncomeDaily.getBeginTime();
		String endTimeStr = deviceIncomeDaily.getEndTime();
		if(StringUtils.isEmpty(beginTimeStr) && StringUtils.isEmpty(endTimeStr)) {
			retMap.put("code", -1);
			retMap.put("message", "开始时间，结束时间都为空");
			return retMap;
		}else {
			if(StringUtils.isEmpty(beginTimeStr)) {
				beginTimeStr = endTimeStr;
			}
			if(StringUtils.isEmpty(endTimeStr)) {
				endTimeStr = beginTimeStr;
			}
		}
		Date beginTime;
		Date endTime ;
		
		beginTime = df.parse(beginTimeStr);
		endTime = df.parse(endTimeStr);
		if(endTime.getTime()<beginTime.getTime()) {
			retMap.put("code", -1);
			retMap.put("message", "开始时间大于结束时间，请重新选择");
			return retMap;
		} 
		
		
		Date currentTime = beginTime;
		Calendar cal = Calendar.getInstance();
		cal.setTime(beginTime);
		cal.add(Calendar.DAY_OF_YEAR, 1);
		FundLog lastfundLog = new FundLog();
		lastfundLog.setCreateTime(cal.getTime());
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		// 逐天调整
		while(currentTime.getTime()<=endTime.getTime()) {
			cal.setTime(currentTime);
			cal.add(Calendar.DAY_OF_YEAR, 1);
			//		    删除今天收益流水 （假删除）
			FundLog fundLog = new FundLog();
//			fundLog.setBeginTime(cal.getTime());//流水是第二天早上生成，所以当天收益流水是记录在第二天
			fundLog.setBeginTime(cal.getTime());
			cal.add(Calendar.DAY_OF_YEAR, 1);
			fundLog.setEndTime(cal.getTime());
			fundLog.setType(Const.FUND_INCOME);
			this.fundLogMapper.deleteWrongFundLog(fundLog);
			//    取出昨日最后一笔总金额和冻结金额
			DeviceIncomeDaily wrongIncomeDaily = new DeviceIncomeDaily();
			wrongIncomeDaily.setSumDate(currentTime);
			this.deviceIncomeDailyMapper.deleteWrongDeviceIncome(wrongIncomeDaily);
			UserIncome wrongUserIncome = new UserIncome();
			wrongUserIncome.setSumDate(currentTime);
			this.userIncomeMapper.deleteWrongUserIncome(wrongUserIncome);
			UserExtension wrongUserExtension = new UserExtension();
			wrongUserExtension.setSumDate(currentTime);
			this.userExtensionMapper.deleteWrongExtendIncome(wrongUserExtension);
			//      计算今日的收益流水（进行今日的日统计定时重跑算法）
			logger.debug("开始时间："+System.currentTimeMillis()+"统计日期："+df1.format(currentTime));
			restatisticaldata(currentTime);
			logger.debug("结束时间："+System.currentTimeMillis()+"统计日期："+df1.format(currentTime));
			currentTime = cal.getTime();
		}
		//	     取出开始统计时间后面有效的流水
		FundLog wrongLog = new FundLog();
		wrongLog.setBeginTime(beginTime);
		cal.setTime(beginTime);
		cal.add(Calendar.DAY_OF_YEAR, 1);
		wrongLog.setBeginTime(cal.getTime());
		List<FundLog> wrongFundLogs = this.fundLogMapper.selectWrongFundLogList(wrongLog);
		//    逐笔统计核算，调整余额
		BigDecimal balance = null;
		BigDecimal frozenBalance = null;
		HashMap<String,Object> clientMap = new HashMap<String,Object>(); 
		if(wrongFundLogs!=null && wrongFundLogs.size()>0) {
			Integer i = 1;
//		     将今日的余额和上次今日已删除最后一笔的总金额和冻结金额计算差值，并调整到后面所有流水
			for(FundLog wrongFundLog : wrongFundLogs) {
				String clientType = wrongFundLog.getClientType();
				Integer clientId = wrongFundLog.getClientId();
				logger.info("id:"+wrongFundLog.getPayId()+"本次金额："+wrongFundLog.getTotalFee());
				BigDecimal money = new BigDecimal(wrongFundLog.getTotalFee());
				logger.info("第"+i+"条流水调整，clientId："+clientId+",clientType："+clientType+",money："+money);
				String key = clientType+"_"+clientId;
				
				if(UserConstants.USER_TYPE_JOIN.equals(clientType)) { // 机主
					Join join = joinMapper.selectJoinById(clientId);
					if(join == null) {
						throw new RRException("未找到客户，clientId："+clientId+",clientType："+clientType);
					}
					if(!clientMap.containsKey(key)) {
						lastfundLog.setClientId(clientId);
						lastfundLog.setClientType(clientType);
						FundLog lastJoinLog = this.fundLogMapper.selectLastFundLog(lastfundLog);
						join.setBalance(new BigDecimal(lastJoinLog!=null?lastJoinLog.getTotalBalance():"0.0"));
						join.setFrozenBalance(new BigDecimal(lastJoinLog!=null?lastJoinLog.getFreezeBalance():"0.0"));
	
						clientMap.put(key,join);
					}
					balance = join.getBalance() == null ? money : join.getBalance().add(money);
					frozenBalance = join.getFrozenBalance() == null ? new BigDecimal(0) : join.getFrozenBalance();
					joinMapper.updateBalance(clientId, balance, null);
				} else if(UserConstants.USER_TYPE_AGENT.equals(clientType)) { // 代理商
					Agent agent = agentMapper.selectAgentById(clientId);
					if(agent == null) {
						throw new RRException("未找到客户，clientId："+clientId+",clientType："+clientType);
					}
					if(!clientMap.containsKey(key)) {
						lastfundLog.setClientId(clientId);
						lastfundLog.setClientType(clientType);
						FundLog lastAgentLog = this.fundLogMapper.selectLastFundLog(lastfundLog);
						agent.setBalance(new BigDecimal(lastAgentLog!=null?lastAgentLog.getTotalBalance():"0.0"));
						agent.setFrozenBalance(new BigDecimal(lastAgentLog!=null?lastAgentLog.getFreezeBalance():"0.0"));
						clientMap.put(key,agent);
					}
					balance = agent.getBalance() == null ? money : agent.getBalance().add(money);
					frozenBalance = agent.getFrozenBalance() == null ? new BigDecimal(0) : agent.getFrozenBalance();
					agentMapper.updateBalance(clientId, balance, null);
				} else if(UserConstants.USER_TYPE_REPAIR.equals(clientType)) { // 服务商
					Repair repair = repairMapper.selectRepairById(clientId);
					if(repair == null) {
						throw new RRException("未找到客户，clientId："+clientId+",clientType："+clientType);
					}
					if(!clientMap.containsKey(key)) {
						lastfundLog.setClientId(clientId);
						lastfundLog.setClientType(clientType);
						FundLog lastRepairLog = this.fundLogMapper.selectLastFundLog(lastfundLog);
						repair.setBalance(new BigDecimal(lastRepairLog!=null?lastRepairLog.getTotalBalance():"0.0"));
						repair.setFrozenBalance(new BigDecimal(lastRepairLog!=null?lastRepairLog.getFreezeBalance():"0.0"));
						clientMap.put(key,repair);
					}
					balance = repair.getBalance() == null ? money : repair.getBalance().add(money);
					frozenBalance = repair.getFrozenBalance() == null ? new BigDecimal(0) : repair.getFrozenBalance();
					repairMapper.updateBalance(clientId, balance, null);
				} else if(UserConstants.USER_TYPE_ADVERTISE.equals(clientType)) { // 广告商
					Advertise advertise = advertiseMapper.selectAdvertiseById(clientId);
					if(advertise == null) {
						throw new RRException("未找到客户，clientId："+clientId+",clientType："+clientType);
					}
					if(!clientMap.containsKey(key)) {
						lastfundLog.setClientId(clientId);
						lastfundLog.setClientType(clientType);
						FundLog lastAdvertiseLog = this.fundLogMapper.selectLastFundLog(lastfundLog);
						advertise.setBalance(new BigDecimal(lastAdvertiseLog!=null?lastAdvertiseLog.getTotalBalance():"0.0"));
						advertise.setFrozenBalance(new BigDecimal(lastAdvertiseLog!=null?lastAdvertiseLog.getFreezeBalance():"0.0"));
						clientMap.put(key,advertise);
					}
					balance = advertise.getBalance() == null ? money : advertise.getBalance().add(money);
					frozenBalance = advertise.getFrozenBalance() == null ? new BigDecimal(0) : advertise.getFrozenBalance();
					advertiseMapper.updateBalance(clientId, balance, null);
				} else {
					throw new RRException("客户类型有误，clientType："+clientType);
				}
				wrongFundLog.setTotalBalance(balance.toString());
				wrongFundLog.setFreezeBalance(frozenBalance.toString());
				balance = balance.subtract(frozenBalance).setScale(2, BigDecimal.ROUND_HALF_UP); // 四舍五入 保留两位
				money = money.setScale(2, BigDecimal.ROUND_HALF_UP); // 四舍五入 保留两位
				wrongFundLog.setBalance(balance.toString()); // 可用余额
				
				fundLogMapper.updateFundLog(wrongFundLog);
				i++;
			}
		}
		
		retMap.put("code", 0);
		retMap.put("message", "分润统计重算成功");
		return retMap;
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			retMap.put("code", -1);
			retMap.put("message", e.getMessage());
			return retMap;
		}
	}
	
}
