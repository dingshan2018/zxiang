package com.zxiang.project.business.device.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zxiang.common.exception.RRException;
import com.zxiang.common.support.Convert;
import com.zxiang.common.utils.StringUtils;
import com.zxiang.project.business.changeTerminal.domain.ChangeTerminal;
import com.zxiang.project.business.changeTerminal.mapper.ChangeTerminalMapper;
import com.zxiang.project.business.device.domain.Device;
import com.zxiang.project.business.device.mapper.DeviceMapper;
import com.zxiang.project.business.place.domain.Place;
import com.zxiang.project.business.place.mapper.PlaceMapper;
import com.zxiang.project.business.supplyTissue.domain.SupplyTissue;
import com.zxiang.project.business.supplyTissue.mapper.SupplyTissueMapper;
import com.zxiang.project.business.terminal.domain.Terminal;
import com.zxiang.project.business.terminal.mapper.TerminalMapper;
import com.zxiang.project.record.deviceOrder.domain.DeviceOrder;
import com.zxiang.project.record.deviceOrder.mapper.DeviceOrderMapper;
import com.zxiang.project.record.tradeOrder.domain.TradeOrder;
import com.zxiang.project.record.tradeOrder.mapper.TradeOrderMapper;

/**
 * 共享设备 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
@Service
@Transactional
public class DeviceServiceImpl implements IDeviceService 
{
	@Autowired
	private DeviceMapper deviceMapper;
	@Autowired
	private PlaceMapper placeMapper;
	@Autowired
	private TerminalMapper terminalMapper;
	@Autowired 
	private ChangeTerminalMapper TerminalMapper;
	@Autowired
	private SupplyTissueMapper supplyTissueMapper;
	@Autowired
	private DeviceOrderMapper deviceOrderMapper;
	@Autowired
	private TradeOrderMapper tradeOrderMapper;
	
	/**
     * 查询共享设备信息
     * 
     * @param deviceId 共享设备ID
     * @return 共享设备信息
     */
    @Override
	public Device selectDeviceById(Integer deviceId)
	{
	    return deviceMapper.selectDeviceById(deviceId);
	}
	
	/**
     * 查询共享设备列表
     * 
     * @param device 共享设备信息
     * @return 共享设备集合
     */
	@Override
	public List<Device> selectDeviceList(Device device)
	{
	    return deviceMapper.selectDeviceList(device);
	}
	
	/**
	 * 查询库存设备列表
	 */
	@Override
	public List<Device> selectDeviceStockList(Device device) {
		return deviceMapper.selectDeviceStockList(device);
	}
	
    /**
     * 新增共享设备
     * 
     * @param device 共享设备信息
     * @return 结果
     */
	@Override
	public int insertDevice(Device device)
	{
		device.setStatus("04");
		device.setDeviceType("共享纸巾机");
		int deviceSave = deviceMapper.insertDevice(device);
		//设备绑定终端后也要修改终端数据对应设备ID的值，设备不能再次绑定该终端
		//终端不再平台绑定，由微信公众号去做绑定操作
		/*Terminal terminal = terminalMapper.selectTerminalById(device.getTerminalId());
		terminal.setDeviceId(device.getDeviceId());
		terminalMapper.updateTerminal(terminal);*/
		
		return deviceSave;
	}
	
	
	/**
     * 修改共享设备
     * 
     * @param device 共享设备信息
     * @return 结果
     */
	@Override
	public int updateDevice(Device device)
	{
	    return deviceMapper.updateDevice(device);
	}

	/**
     * 删除共享设备对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDeviceByIds(String ids)
	{
		return deviceMapper.deleteDeviceByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<Device> selectDropBoxList(String status) {
		return deviceMapper.selectDropBoxList(status);
	}

	@Override
	public int releaseUpdateDevice(Device device) {
		return getAutoCodeNum(device);
	}
	
	/**
	 * 设备编号为场所编号加3位序号
	 * @param device
	 * @return
	 */
	public synchronized int getAutoCodeNum(Device device){
		Device oldDevice = deviceMapper.selectDeviceById(device.getDeviceId());
		String placeId = device.getPlaceId();
		if(oldDevice != null){
			String oldPlaceId = oldDevice.getPlaceId();
			if(placeId.equals(oldPlaceId)){
				device.setStatus("02");
				return deviceMapper.updateDevice(device);
			}
		}
		//1.根据场所ID查询场所编号和地区编号
		Place placeEntity = placeMapper.selectPlaceById(Integer.parseInt(placeId));
		String placeCode = placeEntity.getPlaceCode();

		//2.根据地区编号查询该场所下最大设备编号
		String currentMaxCode = deviceMapper.getMaxDeviceCode(placeId);
		String deviceCode = "";
		if(com.zxiang.common.utils.StringUtils.isNotEmpty(currentMaxCode)){
			//设备编号为15位，场所编号为12位,若场所下存在设备编号则获取当前最大设备数量
			String currentMax = currentMaxCode.substring(12);
			int currentMaxNum = Integer.parseInt(currentMax);
			++currentMaxNum;
			deviceCode = String.format("%03d", currentMaxNum);
		}else{
			deviceCode = String.format("%03d", 1);
		}
		
		//3.最大设备编号生成后插入新的流水设备编号
		deviceCode = placeCode + deviceCode;
		device.setDeviceCode(deviceCode);
		device.setReleaseTime(new Date());
		device.setStatus("02");
		//4.场所投放的设备数量+1
		Place place = placeMapper.selectPlaceById(Integer.parseInt(placeId));
		Integer deviceCount = place.getDeviceCount();
		if(deviceCount != null){
			++deviceCount;
		}else{
			deviceCount = 1;
		}
		place.setDeviceCount(deviceCount);
		placeMapper.updatePlace(place);
		
		//终端数据也将绑定的设备编号信息插入
		/**终端不再平台绑定，由微信公众号去做绑定操作
		/*Terminal terminal = terminalMapper.selectTerminalById(device.getTerminalId());
		terminal.setDeviceId(device.getDeviceId());
		terminal.setPlaceId(Integer.parseInt(placeId));
		terminalMapper.updateTerminal(terminal);*/
		
		return deviceMapper.updateDevice(device);
	}
	
	@Override
	public int removeDeviceUpdate(Device device) {
		//设置撤机时间
		device.setRemoveTime(new Date());
		//场所投放的设备数量-1
		Place place = placeMapper.selectPlaceById(Integer.parseInt(device.getPlaceId()));
		Integer deviceCount = place.getDeviceCount();
		if(deviceCount != null){
			--deviceCount;
		}else{
			deviceCount = 0;
		}
		place.setDeviceCount(deviceCount);
		placeMapper.updatePlace(place);
		
		return deviceMapper.updateDevice(device);
	}

	@Override
	public int changeDevice(Device device,String operatorUser) {
		// 终端更换记录表插入数据 
		ChangeTerminal changeTerminal = new ChangeTerminal();
		changeTerminal.setOldTerminalId(device.getTerminalId());
		changeTerminal.setNewTerminalId(device.getNewTerminalId());
		changeTerminal.setOldVolumn(device.getOldVolumn());
		changeTerminal.setRemainPaper(device.getRemainPaper());
		changeTerminal.setChangerId(device.getChangerId());
		changeTerminal.setCreateBy(operatorUser);
		changeTerminal.setCreateTime(new Date());
		TerminalMapper.insertChangeTerminal(changeTerminal);
		//换板后旧终端状态置为无效
		Terminal oldTerminal = terminalMapper.selectTerminalById(device.getTerminalId());
		oldTerminal.setStatus("2");
		terminalMapper.updateTerminal(oldTerminal);
		//若设备已经投放，则新终端也要插入设备编号和场所编号信息，但场所投放数量不再增加设备count
		Terminal newTerminal = terminalMapper.selectTerminalById(device.getNewTerminalId());
		newTerminal.setDeviceId(device.getDeviceId());
		if(device.getPlaceId() != null){
			newTerminal.setPlaceId(Integer.parseInt(device.getPlaceId()));
		}
		terminalMapper.updateTerminal(newTerminal);
		
		// 最后设备更新终端字段
		device.setTerminalId(device.getNewTerminalId());
		return deviceMapper.updateDevice(device);
	}

	@Override
	public int supplyTissueAdd(Device device, String operatorUser) {
		
		Integer deviceId = device.getDeviceId(); 
		Integer placeId = Integer.parseInt(device.getPlaceId());
		Integer tissueCount = device.getTissueCount();
		Integer supplierId = device.getSupplierId();
		
		SupplyTissue supplyTissue = new SupplyTissue();
		supplyTissue.setDeviceId(deviceId);
		supplyTissue.setPlaceId(placeId);
		supplyTissue.setTissueCount(tissueCount);
		supplyTissue.setSupplierId(supplierId);
		supplyTissue.setCreateBy(operatorUser);
		supplyTissue.setCreateTime(new Date());
		return supplyTissueMapper.insertSupplyTissue(supplyTissue );
	}

	@Override
	public List<Device> getDeviceByPlaceId(String placeId) {
		return deviceMapper.getDeviceByPlaceId(placeId);
	}

	@Override
	public List<Device> getDeviceByareaId(String province, String city, String county) {
		if(StringUtils.isEmpty(province) && StringUtils.isEmpty(city) && StringUtils.isEmpty(county)){
			return null;
		}
		return deviceMapper.getDeviceByareaId(province, city, county);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int saveBatchImport(List<Object> sheetList, String operatorUser) {
		int saveCount = 0;
		//用于存放本批次已插入的数据进行保存
		List<String> hasCodeList = new ArrayList<>(); 
				
		for (Object object : sheetList) {
			List<Object> deviceList = (List<Object>) object;
			if(deviceList.size() > 0 && null != deviceList.get(0)){
				String deviceSn = (String) deviceList.get(0);
				if(org.apache.commons.lang.StringUtils.isNotBlank(deviceSn)){
					//设备资产编号去重,如果本Excel已经导入过这个设备编号就不再保存数据
					if(hasCodeList.contains(deviceSn)){
						continue;
					}
					
					int saveNum = checkAndSaveDeviceSn(deviceSn,operatorUser);
					saveCount = saveCount +  saveNum;
					if(saveNum == 1){
						hasCodeList.add(deviceSn);
					}
				}
			}
		}
		return saveCount;
	}

	public int checkAndSaveDeviceSn(String deviceSn,String operatorUser){
		String codeExist = checkDeviceSnUnique(deviceSn);
		//设备资产编号不存在则保存,返回插入成功数量
		if("0".equals(codeExist)){
			Device device = new Device();
			device.setDeviceSn(deviceSn);
			device.setStatus("04");
			device.setDeviceType("共享纸巾机");
			device.setCreateBy(operatorUser);
			device.setCreateTime(new Date());
		    return deviceMapper.insertDevice(device);
		}
		//终端编号存在则不保存，直接返回0
		return 0;
	}

	@Override
	public String checkDeviceSnUnique(String deviceSn) {
		int count = deviceMapper.checkDeviceSnUnique(deviceSn);
        if (count > 0)
        {
            return "1";
        }
        return "0";
	}

	@Override
	public int outStock(String ids) {
		return deviceMapper.outStock(Convert.toStrArray(ids),0);
	}

	@Override
	public int outStockByTradeId(String ids, TradeOrder tradeOrder,String operatorUser) throws Exception{
		//(js校验)2.界面选择订单设备数量，最多只能选择订单数量total_cnt台数的设备进行出库
		//3.将选择的设备置为出库状态；订单数量累加，判断是否达到total_cnt若达到订单数量则将send_status置为1，否则置为2
		//此处需要注意，要先判断设备当前状态是否为库存，避免并发时被其他人出库了,或使用下述SQL简单 ;如果更新的设备数量与选择设备数量相同则为成功，否则报失败回滚
		//update `zx_device` set status="01" where status="04" and device_id in ()
		int updateDeviceNum = deviceMapper.outStock(Convert.toStrArray(ids),tradeOrder.getUserId());
		if(updateDeviceNum < Convert.toStrArray(ids).length){
			//更新的设备数量应该等于选择的设备数，否则为设备已出库或者被删除,抛异常
			throw new RRException("操作失败,有所选设备已出货,请刷新重试!");
		}
		//获取设备销售表已有多少台设备，与更新的设备数相加判断是否全部出货完成
		int deviceOrderNum = deviceOrderMapper.selectByTradeId(tradeOrder.getTradeOrderId());
		
		//与更新的设备数相加判断是否全部出货完成
		int totalCnt = tradeOrder.getTotalCnt();
		int nowCount = updateDeviceNum + deviceOrderNum;
		if(nowCount > totalCnt){
			//设备台数不应该大于订单数,抛异常
			throw new RRException("操作失败,已出货设备数量大于订单数量!");
		}
		if(nowCount == totalCnt){
			//订单表状态改为已发货
			tradeOrder.setSendStatus("1");
		}
		if(nowCount < totalCnt && nowCount > 0){
			//订单表状态改为部分发货
			tradeOrder.setSendStatus("2");
		}
		tradeOrderMapper.updateTradeOrder(tradeOrder);
		
		// 4.订单销售记录表插入记录
		List<DeviceOrder> deviceOrderList = new ArrayList<>();
		String[] devices = Convert.toStrArray(ids);
		if(devices.length > 0){
			for (String device : devices) {
				//查询这台设备已有信息
				Device dev = deviceMapper.selectDeviceById(Integer.parseInt(device));
				//要插入的设备订单数据
				DeviceOrder deviceOrder = new DeviceOrder();
				deviceOrder.setDeviceId(Integer.parseInt(device));
				deviceOrder.setTradeOrderId(tradeOrder.getTradeOrderId());
				deviceOrder.setTerminalCode(dev.getTerminalCode());
				deviceOrder.setPrice(tradeOrder.getTotalFee());
				deviceOrder.setStatus(tradeOrder.getOrderStatus());
				deviceOrder.setBuyerId(tradeOrder.getUserId());
				deviceOrder.setBuyerOpenId(tradeOrder.getOpenId());
				deviceOrder.setCreateBy(operatorUser);
				deviceOrder.setCreateTime(new Date());
				
				deviceOrderList.add(deviceOrder);
			}
			//mapper.xml 批量插入数据
			deviceOrderMapper.batchInsert(deviceOrderList);
		}
		
		return updateDeviceNum;
	}

}
