package com.zxiang.project.business.device.mapper;

import com.zxiang.project.business.device.domain.Device;
import java.util.List;

import org.apache.ibatis.annotations.Param;	

/**
 * 共享设备 数据层
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
public interface DeviceMapper 
{
	/**
     * 查询共享设备信息
     * 
     * @param deviceId 共享设备ID
     * @return 共享设备信息
     */
	public Device selectDeviceById(Integer deviceId);
	
	/**
     * 查询共享设备列表
     * 
     * @param device 共享设备信息
     * @return 共享设备集合
     */
	public List<Device> selectDeviceList(Device device);
	
	/**
     * 新增共享设备
     * 
     * @param device 共享设备信息
     * @return 结果
     */
	public int insertDevice(Device device);
	
	/**
     * 修改共享设备
     * 
     * @param device 共享设备信息
     * @return 结果
     */
	public int updateDevice(Device device);
	
	/**
     * 删除共享设备
     * 
     * @param deviceId 共享设备ID
     * @return 结果
     */
	public int deleteDeviceById(Integer deviceId);
	
	/**
     * 批量删除共享设备
     * 
     * @param deviceIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteDeviceByIds(String[] deviceIds);

	/**
	 * 查找设备下拉框数据
	 */
	public List<Device> selectDropBoxList();

	/**
	 * 获取设备当前最大编号
	 */
	public String getMaxDeviceCode(String placeId);

	/**
	 * 根据场所ID查询设备列表
	 */
	public List<Device> getDeviceByPlaceId(String placeId);

	/**
	 * 通过地区ID查询设备列表
	 */
	public List<Device> getDeviceByareaId(@Param("province")String province,@Param("city") String city, @Param("county")String county);

	/**
	 * 更新设备的adUrl
	 * @param adUrl
	 * @param terminalId
	 */
	public int updateAdUrlByTid(@Param("adUrl")String adUrl, @Param("terminalIds")String[] terminalIds);

	/**
	 * 校验设备资产编号是否唯一
	 * @param deviceSn
	 * @return
	 */
	public int checkDeviceSnUnique(String deviceSn);
	
}