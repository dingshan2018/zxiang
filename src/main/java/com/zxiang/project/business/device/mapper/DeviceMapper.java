package com.zxiang.project.business.device.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zxiang.project.business.device.domain.Device;	

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
	 * 查询库存设备列表
	 * @param device
	 * @return
	 */
	public List<Device> selectDeviceStockList(Device device);
	
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
	 * @param status 
	 */
	public List<Device> selectDropBoxList(@Param("status") String status);

	/**
	 * 获取设备当前最大编号
	 */
	public String getMaxDeviceCode(String placeId);

	/**
	 * 根据场所ID查询已投放设备列表
	 */
	public List<Device> getDeviceByPlaceId(String placeId);

	/**
	 * 根据地区ID查找所有已投放设备列表
	 */
	public List<Device> getDeviceByareaId(@Param("province")String province,@Param("city") String city, @Param("county")String county);

	/**
	 * 更新设备的adUrl	
	 * @param adUrl 广告URL
	 * @param terminalId 设备ID
	 */
	public int updateAdUrlByTid(@Param("adUrl")String adUrl,@Param("terminalIds")String[] terminalIds);
	
	/**
	 * 更新设备的qrUrl
	 * @param qrUrl 二维码URL
	 * @param deviceIds  设备ID
	 * @param adScheduleId  广告计划ID
	 */
	public int updateQrUrl(@Param("qrUrl")String qrUrl, 
			@Param("deviceIds")String[] deviceIds, @Param("adScheduleId")Integer adScheduleId);

	/**
	 * 校验设备资产编号是否唯一
	 * @param deviceSn
	 * @return
	 */
	public int checkDeviceSnUnique(String deviceSn);

	/**
	 * 库存设备出库
	 * @param ids
	 * @param integer 
	 * @return
	 */
	public int outStock(@Param("array")String[] ids, @Param("ownerId")Integer ownerId);

	public int selectTotal(Map<String, Object> param);

	/**
	 * 查询要导出的运营设备数据列表
	 * @param params
	 * @return
	 */
	public List queryExport(HashMap<String, String> params);

	public Map<String, Object> selectDeviceInfo(Device devParam);

	
}