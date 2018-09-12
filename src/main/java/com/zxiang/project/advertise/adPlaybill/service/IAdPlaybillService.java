package com.zxiang.project.advertise.adPlaybill.service;

import com.zxiang.project.advertise.adPlaybill.domain.AdPlaybill;
import java.util.List;

/**
 * 节目单 服务层
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public interface IAdPlaybillService 
{
	/**
     * 查询节目单信息
     * 
     * @param playbillId 节目单ID
     * @return 节目单信息
     */
	public AdPlaybill selectAdPlaybillById(String playbillId);
	
	/**
     * 查询节目单列表
     * 
     * @param adPlaybill 节目单信息
     * @return 节目单集合
     */
	public List<AdPlaybill> selectAdPlaybillList(AdPlaybill adPlaybill);
	
	/**
     * 新增节目单
     * 
     * @param adPlaybill 节目单信息
     * @return 结果
     */
	public int insertAdPlaybill(AdPlaybill adPlaybill);
	
	/**
     * 修改节目单
     * 
     * @param adPlaybill 节目单信息
     * @return 结果
     */
	public int updateAdPlaybill(AdPlaybill adPlaybill);
		
	/**
     * 删除节目单信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAdPlaybillByIds(String ids);
	
}
