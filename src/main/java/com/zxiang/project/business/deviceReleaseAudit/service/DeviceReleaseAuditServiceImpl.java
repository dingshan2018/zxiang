package com.zxiang.project.business.deviceReleaseAudit.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.zxiang.common.exception.RRException;
import com.zxiang.common.support.Convert;
import com.zxiang.project.business.device.domain.Device;
import com.zxiang.project.business.device.mapper.DeviceMapper;
import com.zxiang.project.business.deviceReleaseAudit.domain.DeviceReleaseAudit;
import com.zxiang.project.business.deviceReleaseAudit.mapper.DeviceReleaseAuditMapper;
import com.zxiang.project.business.place.domain.Place;
import com.zxiang.project.business.place.mapper.PlaceMapper;
import com.zxiang.project.business.server.service.IServerService;
import com.zxiang.project.business.terminal.domain.Terminal;
import com.zxiang.project.business.terminal.mapper.TerminalMapper;

/**
 * 设备投放审核 服务层实现
 * 
 * @author ZXiang
 * @date 2018-12-12
 */
@Service
public class DeviceReleaseAuditServiceImpl implements IDeviceReleaseAuditService 
{
	@Autowired
	private DeviceReleaseAuditMapper deviceReleaseAuditMapper;
	@Autowired
	private DeviceMapper deviceMapper;
	@Autowired
	private PlaceMapper placeMapper;
	@Autowired
	private IServerService serverService;
	@Autowired
	private TerminalMapper terminalMapper;
	
	/** 投放审核-通过 */
	public static final String AUDIT_SUCC = "1";
	/** 投放审核-不通过 */
	public static final String AUDIT_FAIL = "2";
	
	/** 设备投放申请状态-申请通过 */
	public static final String RELEASE_SUCC = "2";
	/** 设备投放申请状态-申请失败 */
	public static final String RELEASE_FAIL = "3";

	/**
     * 查询设备投放审核信息
     * 
     * @param auditId 设备投放审核ID
     * @return 设备投放审核信息
     */
    @Override
	public DeviceReleaseAudit selectDeviceReleaseAuditById(Integer auditId)
	{
	    return deviceReleaseAuditMapper.selectDeviceReleaseAuditById(auditId);
	}
	
	/**
     * 查询设备投放审核列表
     * 
     * @param deviceReleaseAudit 设备投放审核信息
     * @return 设备投放审核集合
     */
	@Override
	public List<DeviceReleaseAudit> selectDeviceReleaseAuditList(DeviceReleaseAudit deviceReleaseAudit)
	{
	    return deviceReleaseAuditMapper.selectDeviceReleaseAuditList(deviceReleaseAudit);
	}
	
    /**
     * 新增设备投放审核
     * 
     * @param deviceReleaseAudit 设备投放审核信息
     * @return 结果
     */
	@Override
	public int insertDeviceReleaseAudit(DeviceReleaseAudit deviceReleaseAudit)
	{
	    return deviceReleaseAuditMapper.insertDeviceReleaseAudit(deviceReleaseAudit);
	}
	
	/**
     * 修改设备投放审核
     * 
     * @param deviceReleaseAudit 设备投放审核信息
     * @return 结果
     */
	@Override
	public int updateDeviceReleaseAudit(DeviceReleaseAudit deviceReleaseAudit)
	{
	    return deviceReleaseAuditMapper.updateDeviceReleaseAudit(deviceReleaseAudit);
	}

	/**
     * 删除设备投放审核对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDeviceReleaseAuditByIds(String ids)
	{
		return deviceReleaseAuditMapper.deleteDeviceReleaseAuditByIds(Convert.toStrArray(ids));
	}

	/**
	 * 设备投放审核保存
	 */
	@Override
	@Transactional
	public int batchAuditSave(String ids, String approved, String approvedRemark,String operatorUser) {

		String[] auditIds = Convert.toStrArray(ids);
		//1.更新审核表数据
		int updateAudit = deviceReleaseAuditMapper.batchUpdateAudit(auditIds,approved,approvedRemark,operatorUser);
		//2.若审核通过则更新设备投放场所信息
		//3.更新场所投放设备数量信息
		if(AUDIT_SUCC.equals(approved)){
			//更新设备信息
			for (int i = 0; i < auditIds.length; i++) {
				DeviceReleaseAudit deviceReleaseAudit = selectDeviceReleaseAuditById(Integer.parseInt(auditIds[i]));
				Device device  = deviceMapper.selectDeviceById(deviceReleaseAudit.getDeviceId());
				device.setPlaceId(String.valueOf(deviceReleaseAudit.getPlaceId()));
				device.setStatus("02");
				device.setReleaseStatus(RELEASE_SUCC);
				device.setReleaseTime(new Date());
				deviceMapper.updateDevice(device);
				
				//终端绑定场所信息
				if(device.getTerminalId() != null){
					Terminal terminal = terminalMapper.selectTerminalById(device.getTerminalId());
					if(terminal != null ){
						terminal.setPlaceId(deviceReleaseAudit.getPlaceId());
						terminalMapper.updateTerminal(terminal);
					}
				}
				
				//下发上线信息给终端02
				Terminal terminal = terminalMapper.selectTerminalById(device.getTerminalId());
				if(terminal != null ){
					JSONObject reqJson = new JSONObject();
					reqJson.put("termCode",terminal.getTerminalCode());
					reqJson.put("status","02");//
					reqJson.put("command","26");//参数下发命令0x1A,转十进制为26	
					try {
						serverService.issuedCommand(terminal,reqJson);
					} catch (IOException e) {
						e.printStackTrace();
						continue;
					}
				}
			}
			//更新场所信息
			for (int i = 0; i < auditIds.length; i++) {
				DeviceReleaseAudit deviceReleaseAudit = selectDeviceReleaseAuditById(Integer.parseInt(auditIds[i]));
				updatePlaceCount(deviceReleaseAudit.getPlaceId());
			}
			
		}else if(AUDIT_FAIL.equals(approved)){
			//更新设备信息
			for (int i = 0; i < auditIds.length; i++) {
				DeviceReleaseAudit deviceReleaseAudit = selectDeviceReleaseAuditById(Integer.parseInt(auditIds[i]));
				Device device  = deviceMapper.selectDeviceById(deviceReleaseAudit.getDeviceId());
				device.setReleaseStatus(RELEASE_FAIL);
				deviceMapper.updateDevice(device);
			}
			//审核失败场所信息不需要更新
			
		}else{
			throw new RRException("审核状态异常,审核失败!");
		}
		
		return updateAudit;
	}

	/**
	 * 设备投放时更新场所投放数量
	 * @param placeId
	 * @return
	 */
	public int updatePlaceCount(Integer placeId){
		Place place = placeMapper.selectPlaceById(placeId);
		if(place != null){
			Integer deviceCount = place.getDeviceCount();
			if(deviceCount != null){
				++deviceCount;
			}else{
				deviceCount = 1;
			}
			place.setDeviceCount(deviceCount);
			return placeMapper.updatePlace(place);
		}
		return 0;
	}
	
	/**
	 * 通过设备ID查询审批数据
	 */
	@Override
	public DeviceReleaseAudit selectAuditByDeviceId(Integer deviceId) {
		return deviceReleaseAuditMapper.selectAuditByDeviceId(deviceId);
	}
	
}
