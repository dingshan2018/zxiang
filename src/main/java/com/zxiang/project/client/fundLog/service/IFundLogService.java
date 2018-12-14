package com.zxiang.project.client.fundLog.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.ui.ModelMap;

import com.zxiang.project.client.fundLog.domain.FundLog;

/**
 * 资金流水 服务层
 * 
 * @author ZXiang
 * @date 2018-12-05
 */
public interface IFundLogService 
{
	/**
     * 查询资金流水信息
     * 
     * @param payId 资金流水ID
     * @return 资金流水信息
     */
	public FundLog selectFundLogById(Integer payId);
	
	/**
     * 查询资金流水列表
     * 
     * @param fundLog 资金流水信息
     * @return 资金流水集合
     */
	public List<FundLog> selectFundLogList(FundLog fundLog);
	
	/**
     * 新增资金流水
     * 
     * @param fundLog 资金流水信息
     * @return 结果
     */
	public int insertFundLog(FundLog fundLog);
	
	/**
     * 修改资金流水
     * 
     * @param fundLog 资金流水信息
     * @return 结果
     */
	public int updateFundLog(FundLog fundLog);
		
	/**
     * 删除资金流水信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFundLogByIds(String ids);
	/**
	 * 显示客户信息
	 * @param clientId
	 * @param clientType
	 */
	public void showClientInfo(Integer clientId,String clientType,ModelMap mmap);
	/**
	 * 收益统计记录
	 * @param clientId  主体id
	 * @param clientType 主体类型
	 * @param money 金额
	 */
	public void incomeRecord(Integer clientId,String clientType,BigDecimal money);
	/**
	 * 客户申请提现
	 * @param clientId  主体id
	 * @param clientType 主体类型
	 * @param money 金额
	 */
	public void clientWithdraw(Integer clientId,String clientType,BigDecimal money);
	/**
	 * 客户提现确认
	 * @param id 提现记录id
	 */
	public void sureClientWithdraw(Integer id,String drawStatus);
	/**
	 * 广告发布冻结资金
	 * @param advertiseId  主体id
	 * @param money 金额
	 */
	public void adPublishFrozen(Integer advertiseId,BigDecimal money);
	
	
}
