package com.zxiang.project.advertise.releaseDevice.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zxiang.common.support.Convert;
import com.zxiang.common.utils.security.ShiroUtils;
import com.zxiang.project.advertise.adSchedule.domain.AdSchedule;
import com.zxiang.project.advertise.adSchedule.mapper.AdScheduleMapper;
import com.zxiang.project.advertise.releaseDevice.domain.ReleaseDevice;
import com.zxiang.project.advertise.releaseDevice.mapper.ReleaseDeviceMapper;
import com.zxiang.project.advertise.utils.constant.AdConstant;

/**
 * 投放终端配置 服务层实现
 * 
 * @author ZXiang
 * @date 2018-11-22
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class ReleaseDeviceServiceImpl implements IReleaseDeviceService 
{
	@Autowired
	private ReleaseDeviceMapper releaseDeviceMapper;
	@Autowired
	private AdScheduleMapper adScheduleMapper;
	/**
     * 查询投放终端配置信息
     * 
     * @param releaseDeviceId 投放终端配置ID
     * @return 投放终端配置信息
     */
    @Override
	public ReleaseDevice selectReleaseDeviceById(Integer releaseDeviceId)
	{
	    return releaseDeviceMapper.selectReleaseDeviceById(releaseDeviceId);
	}
	
	/**
     * 查询投放终端配置列表
     * 
     * @param releaseDevice 投放终端配置信息
     * @return 投放终端配置集合
     */
	@Override
	public List<ReleaseDevice> selectReleaseDeviceList(ReleaseDevice releaseDevice)
	{
	    return releaseDeviceMapper.selectReleaseDeviceList(releaseDevice);
	}
	
    /**
     * 新增投放终端配置
     * 
     * @param releaseDevice 投放终端配置信息
     * @return 结果
     */
	@Override
	public int insertReleaseDevice(ReleaseDevice releaseDevice)
	{
	    int ret = releaseDeviceMapper.insertReleaseDevice(releaseDevice);
	    AdSchedule schedule = adScheduleMapper.selectAdScheduleById(releaseDevice.getScheduleId());
	    ReleaseDevice releaseDevice1 = new ReleaseDevice();
		releaseDevice1.setScheduleId(schedule.getAdScheduleId());
		List<ReleaseDevice> deviceList = this.releaseDeviceMapper.selectReleaseDeviceList(releaseDevice1);
		schedule.setReleaseTermNum(deviceList.size());
		schedule.setUpdateBy(ShiroUtils.getLoginName());
		schedule.setUpdateTime(new Date());
		adScheduleMapper.updateAdSchedule(schedule);
	    return ret;
	}
	
	/**
     * 修改投放终端配置
     * 
     * @param releaseDevice 投放终端配置信息
     * @return 结果
     */
	@Override
	public int updateReleaseDevice(ReleaseDevice releaseDevice)
	{
	    return releaseDeviceMapper.updateReleaseDevice(releaseDevice);
	}

	/**
     * 删除投放终端配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteReleaseDeviceByIds(String ids)
	{
		ReleaseDevice releaseDevice = releaseDeviceMapper.selectReleaseDeviceById(Integer.parseInt(Convert.toStrArray(ids)[0]));
		AdSchedule schedule = adScheduleMapper.selectAdScheduleById(releaseDevice.getScheduleId());
		if("01".equals(schedule.getReleasePosition())) {
			schedule.setPayStatus(AdConstant.AD_HAS_PAY);
			schedule.setReleaseStatus(AdConstant.AD_WAIT_REPUBLISH);
			schedule.setUpdateBy(ShiroUtils.getLoginName());
			schedule.setUpdateTime(new Date());
			adScheduleMapper.updateAdSchedule(schedule);
		}
		
		int ret = releaseDeviceMapper.deleteReleaseDeviceByIds(Convert.toStrArray(ids));
		ReleaseDevice releaseDevice1 = new ReleaseDevice();
		releaseDevice1.setScheduleId(schedule.getAdScheduleId());
		List<ReleaseDevice> deviceList = this.releaseDeviceMapper.selectReleaseDeviceList(releaseDevice1);
		schedule.setReleaseTermNum(deviceList.size());
		schedule.setUpdateBy(ShiroUtils.getLoginName());
		schedule.setUpdateTime(new Date());
		adScheduleMapper.updateAdSchedule(schedule);
		return ret;
	}

	@Override
	public int batchInsert(List<ReleaseDevice> devices) throws  Exception {
		AdSchedule schedule = adScheduleMapper.selectAdScheduleById(devices.get(0).getScheduleId());
		releaseDeviceMapper.batchInsert(devices);
		if("01".equals(schedule.getReleasePosition())) {
			schedule.setPayStatus(AdConstant.AD_HAS_PAY);
			schedule.setReleaseStatus(AdConstant.AD_WAIT_REPUBLISH);
		}
		ReleaseDevice releaseDevice = new ReleaseDevice();
		releaseDevice.setScheduleId(schedule.getAdScheduleId());
		List<ReleaseDevice> deviceList = this.releaseDeviceMapper.selectReleaseDeviceList(releaseDevice);
		schedule.setReleaseTermNum(deviceList.size());
		schedule.setUpdateBy(ShiroUtils.getLoginName());
		schedule.setUpdateTime(new Date());
		adScheduleMapper.updateAdSchedule(schedule);
		return 1;
	}

	@Override
	public void deleteReleaseDeviceByScheduleId(Integer adScheduleId) {
		releaseDeviceMapper.deleteReleaseDeviceByScheduleId(adScheduleId);
	}
	
}
