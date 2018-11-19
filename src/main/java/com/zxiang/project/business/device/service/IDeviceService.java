package com.zxiang.project.business.device.service;

import java.util.List;

import com.zxiang.project.business.device.domain.Device;
import com.zxiang.project.record.tradeOrder.domain.TradeOrder;

/**
 * 共享设备 服务层
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
public interface IDeviceService 
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
     * 删除共享设备信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDeviceByIds(String ids);

	/**
	 * 查找设备下拉框数据
	 * @param status 
	 */
	public List<Device> selectDropBoxList(String status);

	/**
	 * 共享是设备投放
	 * @param device
	 * @return
	 */
	public int releaseUpdateDevice(Device device);
	
	/**
	 * 共享设备撤机
	 * @param device
	 * @return
	 */
	public int removeDeviceUpdate(Device device);

	/**
	 * 设备换板
	 * @param device
	 * @return
	 */
	public int changeDevice(Device device,String operatorUser);

	/**
	 * 设备补纸
	 * @param device
	 * @param operatorUser
	 * @return
	 */
	public int supplyTissueAdd(Device device, String operatorUser);

	/**
	 * 根据场所ID查询已投放设备列表
	 * @param placeId
	 * @return
	 */
	public List<Device> getDeviceByPlaceId(String placeId);

	/**
	 * 根据地区ID查找所有已投放设备列表
	 * @param province
	 * @param city
	 * @param county
	 * @return
	 */
	public List<Device> getDeviceByareaId(String province, String city, String county);

	/**
	 * 保存Excel设备资产编号数据
	 * @param sheetList
	 * @param operatorUser
	 * @return
	 */
	public int saveBatchImport(List<Object> sheetList, String operatorUser);
	
	/**
     * 校验设备资产编号是否唯一
     */
	public String checkDeviceSnUnique(String deviceSn);

	/**
	 * 查找库存设备列表
	 * @param device
	 * @return
	 */
	public List<Device> selectDeviceStockList(Device device);

	/**
	 * 库存设备出库
	 * @param ids
	 * @return
	 */
	public int outStock(String ids);
	
	/**
	 * 通过订单号选择出库设备
	 * @param ids
	 * @param tradeOrder
	 * @return
	 */
	public int outStockByTradeId(String ids,TradeOrder tradeOrder,String operatorUser) throws Exception;
	
}
