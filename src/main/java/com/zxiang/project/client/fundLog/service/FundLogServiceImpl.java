package com.zxiang.project.client.fundLog.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.zxiang.common.constant.Const;
import com.zxiang.common.constant.UserConstants;
import com.zxiang.common.exception.RRException;
import com.zxiang.common.support.Convert;
import com.zxiang.project.client.advertise.domain.Advertise;
import com.zxiang.project.client.advertise.mapper.AdvertiseMapper;
import com.zxiang.project.client.agent.domain.Agent;
import com.zxiang.project.client.agent.mapper.AgentMapper;
import com.zxiang.project.client.fundLog.domain.FundLog;
import com.zxiang.project.client.fundLog.mapper.FundLogMapper;
import com.zxiang.project.client.join.domain.Join;
import com.zxiang.project.client.join.mapper.JoinMapper;
import com.zxiang.project.client.repair.domain.Repair;
import com.zxiang.project.client.repair.mapper.RepairMapper;

/**
 * 资金流水 服务层实现
 * 
 * @author ZXiang
 * @date 2018-12-05
 */
@Service
public class FundLogServiceImpl implements IFundLogService {
	Logger logger = Logger.getLogger(FundLogServiceImpl.class);
	@Autowired
	private FundLogMapper fundLogMapper;
	@Autowired
	private AdvertiseMapper advertiseMapper;
	@Autowired
	private AgentMapper agentMapper;
	@Autowired
	private JoinMapper joinMapper;
	@Autowired
	private RepairMapper repairMapper;

	/**
     * 查询资金流水信息
     * 
     * @param payId 资金流水ID
     * @return 资金流水信息
     */
    @Override
	public FundLog selectFundLogById(Integer payId) {
	    return fundLogMapper.selectFundLogById(payId);
	}
	
	/**
     * 查询资金流水列表
     * 
     * @param fundLog 资金流水信息
     * @return 资金流水集合
     */
	@Override
	public List<FundLog> selectFundLogList(FundLog fundLog) {
	    return fundLogMapper.selectFundLogList(fundLog);
	}
	
    /**
     * 新增资金流水
     * 
     * @param fundLog 资金流水信息
     * @return 结果
     */
	@Override
	public int insertFundLog(FundLog fundLog) {
	    return fundLogMapper.insertFundLog(fundLog);
	}
	
	/**
     * 修改资金流水
     * 
     * @param fundLog 资金流水信息
     * @return 结果
     */
	@Override
	public int updateFundLog(FundLog fundLog) {
	    return fundLogMapper.updateFundLog(fundLog);
	}

	/**
     * 删除资金流水对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteFundLogByIds(String ids) {
		return fundLogMapper.deleteFundLogByIds(Convert.toStrArray(ids));
	}

	@Override
	@Transactional
	public void incomeRecord(Integer clientId, String clientType, BigDecimal money) {
		logger.info("收益统计，clientId："+clientId+",clientType："+clientType+",money："+money);
		if(money == null || money.compareTo(new BigDecimal(0)) <= 0) {
			throw new RRException("收益余额须大于0，clientId："+clientId+",clientType："+clientType);
		}
		BigDecimal balance = null;
		if(UserConstants.USER_TYPE_JOIN.equals(clientType)) { // 加盟商
			Join join = joinMapper.selectJoinById(clientId);
			if(join == null) {
				throw new RRException("未找到客户，clientId："+clientId+",clientType："+clientType);
			}
			balance = join.getBalance() == null ? money : join.getBalance().add(money);
			joinMapper.updateBalance(clientId, balance, null);
		} else if(UserConstants.USER_TYPE_AGENT.equals(clientType)) { // 代理商
			Agent agent = agentMapper.selectAgentById(clientId);
			if(agent == null) {
				throw new RRException("未找到客户，clientId："+clientId+",clientType："+clientType);
			}
			balance = agent.getBalance() == null ? money : agent.getBalance().add(money);
			agentMapper.updateBalance(clientId, balance, null);
		} else if(UserConstants.USER_TYPE_REPAIR.equals(clientType)) { // 服务商
			Repair repair = repairMapper.selectRepairById(clientId);
			if(repair == null) {
				throw new RRException("未找到客户，clientId："+clientId+",clientType："+clientType);
			}
			balance = repair.getBalance() == null ? money : repair.getBalance().add(money);
			repairMapper.updateBalance(clientId, balance, null);
		} else if(UserConstants.USER_TYPE_ADVERTISE.equals(clientType)) { // 广告商
			Advertise advertise = advertiseMapper.selectAdvertiseById(clientId);
			if(advertise == null) {
				throw new RRException("未找到客户，clientId："+clientId+",clientType："+clientType);
			}
			balance = advertise.getBalance() == null ? money : advertise.getBalance().add(money);
			advertiseMapper.updateBalance(clientId, balance, null);
		} else {
			throw new RRException("客户类型有误，clientType："+clientType);
		}
		balance = balance.setScale(2, BigDecimal.ROUND_HALF_UP); // 四舍五入 保留两位
		money = money.setScale(2, BigDecimal.ROUND_HALF_UP); // 四舍五入 保留两位
		FundLog fundLog = new FundLog();
		fundLog.setBalance(balance.compareTo(new BigDecimal(0)) == -1 ? balance.toString() :("+"+balance));
		fundLog.setTotalFee("+"+money);
		fundLog.setClientId(clientId);
		fundLog.setClientType(clientType);
		fundLog.setContent("收益");
		fundLog.setType(Const.FUND_INCOME);
		fundLog.setStatus(Const.STATUS_SUCCESS);
		fundLog.setCreateTime(new Date());
		fundLogMapper.insertFundLog(fundLog);
	}

	@Override
	public void showClientInfo(Integer clientId, String clientType, ModelMap mmap) {
		if(UserConstants.USER_TYPE_JOIN.equals(clientType)) { // 加盟商
			Join join = joinMapper.selectJoinById(clientId);
			mmap.put("balance", join == null || join.getBalance()==null ? 0.00 : join.getBalance());
			mmap.put("frozenBalance", join == null || join.getFrozenBalance()==null ? 0.00 : join.getFrozenBalance());
			mmap.put("clientName", join.getJoinerName());
		} else if(UserConstants.USER_TYPE_AGENT.equals(clientType)) { // 代理商
			Agent agent = agentMapper.selectAgentById(clientId);
			mmap.put("balance", agent == null || agent.getBalance()==null ? 0.00 : agent.getBalance());
			mmap.put("frozenBalance", agent == null || agent.getFrozenBalance()==null ? 0.00 : agent.getFrozenBalance());
			mmap.put("clientName", agent.getAgentName());
		} else if(UserConstants.USER_TYPE_REPAIR.equals(clientType)) { // 服务商
			Repair repair = repairMapper.selectRepairById(clientId);
			mmap.put("balance", repair == null || repair.getBalance()==null ? 0.00 : repair.getBalance());
			mmap.put("frozenBalance", repair == null || repair.getFrozenBalance()==null ? 0.00 : repair.getFrozenBalance());
			mmap.put("clientName", repair.getRepairName());
		} else if(UserConstants.USER_TYPE_ADVERTISE.equals(clientType)) { // 广告商
			Advertise advertise = advertiseMapper.selectAdvertiseById(clientId);
			mmap.put("balance", advertise == null || advertise.getBalance()==null ? 0.00 : advertise.getBalance());
			mmap.put("frozenBalance", advertise == null || advertise.getFrozenBalance()==null ? 0.00 : advertise.getFrozenBalance());
			mmap.put("clientName", advertise.getAdvertisorName());
		} else {
			mmap.put("balance", 0.00);
			mmap.put("frozenBalance", 0.00);
			mmap.put("clientName", "未知");
		}
		
	}
	
}
