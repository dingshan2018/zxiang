package com.zxiang.project.business.device.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zxiang.common.support.Convert;
import com.zxiang.project.business.changeTerminal.domain.ChangeTerminal;
import com.zxiang.project.business.changeTerminal.mapper.ChangeTerminalMapper;
import com.zxiang.project.business.device.domain.Device;
import com.zxiang.project.business.device.mapper.DeviceMapper;
import com.zxiang.project.business.place.domain.Place;
import com.zxiang.project.business.place.mapper.PlaceMapper;
import com.zxiang.project.business.terminal.domain.Terminal;
import com.zxiang.project.business.terminal.mapper.TerminalMapper;

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
     * 新增共享设备
     * 
     * @param device 共享设备信息
     * @return 结果
     */
	@Override
	public int insertDevice(Device device)
	{
		int deviceSave = deviceMapper.insertDevice(device);
		//设备绑定终端后也要修改终端数据对应设备ID的值，设备不能再次绑定该终端
		Terminal terminal = terminalMapper.selectTerminalById(device.getTerminalId());
		terminal.setDeviceId(device.getDeviceId());
		terminalMapper.updateTerminal(terminal);
		
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
	public List<Device> selectDropBoxList() {
		return deviceMapper.selectDropBoxList();
	}

	@Override
	public int releaseUpdateDevice(Device device) {
		return getAutoCodeNum(device);
	}
	
	/**
	 * 设备编号为场所编号加3为序号
	 * @param device
	 * @return
	 */
	public synchronized int getAutoCodeNum(Device device){
		//1.根据场所ID查询场所编号和地区编号
		String placeId = device.getPlaceId();
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
		device.setStatus("2");
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
		Terminal terminal = terminalMapper.selectTerminalById(device.getTerminalId());
		terminal.setDeviceId(device.getDeviceId());
		terminal.setPlaceId(Integer.parseInt(placeId));
		terminalMapper.updateTerminal(terminal);
		
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

}
