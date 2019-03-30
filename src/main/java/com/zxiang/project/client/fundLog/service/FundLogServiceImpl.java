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
import com.zxiang.project.client.fundLog.domain.WithdrawDeposit;
import com.zxiang.project.client.fundLog.mapper.FundLogMapper;
import com.zxiang.project.client.fundLog.mapper.WithdrawDepositMapper;
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
	@Autowired
	private WithdrawDepositMapper withdrawDepositMapper;

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
	public void showClientInfo(Integer clientId, String clientType, ModelMap mmap) {
		if(UserConstants.USER_TYPE_JOIN.equals(clientType)) { // 机主
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

	
	@Override
	@Transactional
	public void incomeRecord(Integer clientId, String clientType, BigDecimal money) {
		logger.info("收益统计，clientId："+clientId+",clientType："+clientType+",money："+money);
		if(money == null || money.compareTo(new BigDecimal(0)) <= 0) {
			throw new RRException("收益余额须大于0，clientId："+clientId+",clientType："+clientType);
		}
		BigDecimal balance = null;
		BigDecimal frozenBalance = null;
		if(UserConstants.USER_TYPE_JOIN.equals(clientType)) { // 机主
			Join join = joinMapper.selectJoinById(clientId);
			if(join == null) {
				throw new RRException("未找到客户，clientId："+clientId+",clientType："+clientType);
			}
			balance = join.getBalance() == null ? money : join.getBalance().add(money);
			frozenBalance = join.getFrozenBalance() == null ? new BigDecimal(0) : join.getFrozenBalance();
			joinMapper.updateBalance(clientId, balance, null);
		} else if(UserConstants.USER_TYPE_AGENT.equals(clientType)) { // 代理商
			Agent agent = agentMapper.selectAgentById(clientId);
			if(agent == null) {
				throw new RRException("未找到客户，clientId："+clientId+",clientType："+clientType);
			}
			balance = agent.getBalance() == null ? money : agent.getBalance().add(money);
			frozenBalance = agent.getFrozenBalance() == null ? new BigDecimal(0) : agent.getFrozenBalance();
			agentMapper.updateBalance(clientId, balance, null);
		} else if(UserConstants.USER_TYPE_REPAIR.equals(clientType)) { // 服务商
			Repair repair = repairMapper.selectRepairById(clientId);
			if(repair == null) {
				throw new RRException("未找到客户，clientId："+clientId+",clientType："+clientType);
			}
			balance = repair.getBalance() == null ? money : repair.getBalance().add(money);
			frozenBalance = repair.getFrozenBalance() == null ? new BigDecimal(0) : repair.getFrozenBalance();
			repairMapper.updateBalance(clientId, balance, null);
		} else if(UserConstants.USER_TYPE_ADVERTISE.equals(clientType)) { // 广告商
			Advertise advertise = advertiseMapper.selectAdvertiseById(clientId);
			if(advertise == null) {
				throw new RRException("未找到客户，clientId："+clientId+",clientType："+clientType);
			}
			balance = advertise.getBalance() == null ? money : advertise.getBalance().add(money);
			frozenBalance = advertise.getFrozenBalance() == null ? new BigDecimal(0) : advertise.getFrozenBalance();
			advertiseMapper.updateBalance(clientId, balance, null);
		} else {
			throw new RRException("客户类型有误，clientType："+clientType);
		}
		balance = balance.subtract(frozenBalance).setScale(2, BigDecimal.ROUND_HALF_UP); // 四舍五入 保留两位
		money = money.setScale(2, BigDecimal.ROUND_HALF_UP); // 四舍五入 保留两位
		FundLog fundLog = new FundLog();
		fundLog.setBalance(balance.toString()); // 可用余额
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
	@Transactional
	public void adPublishFrozen(Integer advertiseId, BigDecimal money) {
		Advertise advertise = advertiseMapper.selectAdvertiseById(advertiseId);
		if(advertise == null) {
			throw new RRException("未找到广告主体信息");
		}
		BigDecimal frozenBalance = advertise.getFrozenBalance() == null ? new BigDecimal(0) : advertise.getFrozenBalance();
		if(advertise.getBalance() == null || advertise.getBalance().subtract(frozenBalance).compareTo(money) == -1) { // 可用余额 = 账户余额 - 冻结余额
			throw new RRException("账户余额不足");
		}
		if(frozenBalance.floatValue()<0) {//解冻
			BigDecimal balance = advertise.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP);
			frozenBalance = frozenBalance.subtract(money).setScale(2, BigDecimal.ROUND_HALF_UP);
			advertiseMapper.updateBalance(advertiseId, balance, frozenBalance); // 更新账户余额
			// 生成资金流水记录
			FundLog fundLog = new FundLog();
			fundLog.setBalance(balance.subtract(frozenBalance).toString()); // 可用余额
			fundLog.setTotalFee(money+"");
			fundLog.setClientId(advertiseId);
			fundLog.setClientType(UserConstants.USER_TYPE_ADVERTISE);
			fundLog.setContent("调整广告发布资金解冻");
			fundLog.setType(Const.FUND_FREEZE);
			fundLog.setStatus(Const.STATUS_SUCCESS);
			fundLog.setCreateTime(new Date());
			fundLogMapper.insertFundLog(fundLog);
		}else {//冻结
			BigDecimal balance = advertise.getBalance().setScale(2, BigDecimal.ROUND_HALF_UP);
			frozenBalance = frozenBalance.add(money).setScale(2, BigDecimal.ROUND_HALF_UP);
			advertiseMapper.updateBalance(advertiseId, balance, frozenBalance); // 更新账户余额
			// 生成资金流水记录
			FundLog fundLog = new FundLog();
			fundLog.setBalance(balance.subtract(frozenBalance).toString()); // 可用余额
			fundLog.setTotalFee("-"+Math.abs(money.floatValue()));
			fundLog.setClientId(advertiseId);
			fundLog.setClientType(UserConstants.USER_TYPE_ADVERTISE);
			fundLog.setContent("广告发布资金冻结");
			fundLog.setType(Const.FUND_FREEZE);
			fundLog.setStatus(Const.STATUS_SUCCESS);
			fundLog.setCreateTime(new Date());
			fundLogMapper.insertFundLog(fundLog);
		}
	}

	@Override
	@Transactional
	public void clientWithdraw(Integer clientId, String clientType, BigDecimal money) {
		if(money == null || money.compareTo(new BigDecimal(0)) <= 0) {
			throw new RRException("提现余额须大于0");
		}
		BigDecimal frozenBalance = null;
		BigDecimal balance = null;
		String clientName = null;
		String managerPhone = null;
		String bankAccount = null;
		String bankName = null;
		String bankReceiver = null;
		if(UserConstants.USER_TYPE_JOIN.equals(clientType)) { // 机主
			Join join = joinMapper.selectJoinById(clientId);
			if(join == null) {
				throw new RRException("未找到客户");
			}
			frozenBalance = join.getFrozenBalance() == null ? new BigDecimal(0) : join.getFrozenBalance();
			if(join.getBalance() == null || join.getBalance().subtract(frozenBalance).compareTo(money) == -1) {
				throw new RRException("可提现余额不足");
			}
			balance = join.getBalance();
			frozenBalance = frozenBalance.add(money);
			joinMapper.updateBalance(clientId, null, frozenBalance);
			clientName = join.getJoinerName();
			managerPhone = join.getManagerPhone();
			bankAccount = join.getBankAccount();
			bankName = join.getBankName();
			bankReceiver = join.getBankReceiver();
		} else if(UserConstants.USER_TYPE_AGENT.equals(clientType)) { // 代理商
			Agent agent = agentMapper.selectAgentById(clientId);
			if(agent == null) {
				throw new RRException("未找到客户");
			}
			frozenBalance = agent.getFrozenBalance() == null ? new BigDecimal(0) : agent.getFrozenBalance();
			if(agent.getBalance() == null || agent.getBalance().subtract(frozenBalance).compareTo(money) == -1) {
				throw new RRException("可提现余额不足");
			}
			balance = agent.getBalance();
			frozenBalance = frozenBalance.add(money);
			agentMapper.updateBalance(clientId, null, frozenBalance);
			clientName = agent.getAgentName();
			managerPhone = agent.getManagerPhone();
			bankAccount = agent.getBankAccount();
			bankName = agent.getBankName();
			bankReceiver = agent.getBankReceiver();
		} else if(UserConstants.USER_TYPE_REPAIR.equals(clientType)) { // 服务商
			Repair repair = repairMapper.selectRepairById(clientId);
			if(repair == null) {
				throw new RRException("未找到客户");
			}
			frozenBalance = repair.getFrozenBalance() == null ? new BigDecimal(0) : repair.getFrozenBalance();
			if(repair.getBalance() == null || repair.getBalance().subtract(frozenBalance).compareTo(money) == -1) {
				throw new RRException("可提现余额不足");
			}
			balance = repair.getBalance();
			frozenBalance = frozenBalance.add(money);
			repairMapper.updateBalance(clientId, null, frozenBalance);
			clientName = repair.getRepairName();
			managerPhone = repair.getManagerPhone();
			bankAccount = repair.getBankAccount();
			bankName = repair.getBankName();
			bankReceiver = repair.getBankReceiver();
		} else if(UserConstants.USER_TYPE_ADVERTISE.equals(clientType)) { // 广告商
			Advertise advertise = advertiseMapper.selectAdvertiseById(clientId);
			if(advertise == null) {
				throw new RRException("未找到客户");
			}
			frozenBalance = advertise.getFrozenBalance() == null ? new BigDecimal(0) : advertise.getFrozenBalance();
			if(advertise.getBalance() == null || advertise.getBalance().subtract(frozenBalance).compareTo(money) == -1) {
				throw new RRException("可提现余额不足");
			}
			balance = advertise.getBalance();
			frozenBalance = frozenBalance.add(money);
			advertiseMapper.updateBalance(clientId, null, frozenBalance);
			clientName = advertise.getAdvertisorName();
			managerPhone = advertise.getManagerPhone();
			bankAccount = advertise.getBankAccount();
			bankName = advertise.getBankName();
			bankReceiver = advertise.getBankReceiver();
		} else {
			logger.error("客户类型有误，clientType："+clientType);
			throw new RRException("客户类型有误");
		}
		balance = balance.setScale(2, BigDecimal.ROUND_HALF_UP); // 四舍五入 保留两位
		money = money.setScale(2, BigDecimal.ROUND_HALF_UP); // 四舍五入 保留两位
		// 资金流水
		FundLog fundLog = new FundLog();
		fundLog.setBalance(balance.subtract(frozenBalance).toString()); // 可用余额
		fundLog.setTotalFee("-"+money);
		fundLog.setClientId(clientId);
		fundLog.setClientType(clientType);
		fundLog.setContent("提现");
		fundLog.setType(Const.FUND_WITHDRAW_DEPOSIT);
		fundLog.setStatus("0");
		fundLog.setCreateTime(new Date());
		fundLogMapper.insertFundLog(fundLog);
		// 提现记录
		WithdrawDeposit wd = new WithdrawDeposit();
		wd.setFundLogId(fundLog.getPayId());
		wd.setClientId(clientId);
		wd.setClientType(clientType);
		wd.setClientName(clientName);
		wd.setMoney(money);
		wd.setBalance(balance.subtract(money));
		wd.setManagerPhone(managerPhone);
		wd.setBankAccount(bankAccount);
		wd.setBankName(bankName);
		wd.setBankReceiver(bankReceiver);
		wd.setStatus("1");
		wd.setCreateTime(new Date());
		withdrawDepositMapper.insertWithdrawDeposit(wd);
	}

	@Override
	public void sureClientWithdraw(Integer id,String drawStatus,String remark) {
		WithdrawDeposit wd = withdrawDepositMapper.selectWithdrawDepositById(id);
		if(wd == null) {
			throw new RRException("无提现记录");
		}
		String status = Const.WITHDRAW_SUCCESS.equals(drawStatus) ? Const.STATUS_SUCCESS : Const.STATUS_FAIL;
		fundLogMapper.updateStatus(wd.getFundLogId(), status,remark);
		if(UserConstants.USER_TYPE_JOIN.equals(wd.getClientType())) { // 机主
			Join join = joinMapper.selectJoinById(wd.getClientId());
			if(join == null) {
				throw new RRException("未找到客户");
			}
			if(Const.WITHDRAW_SUCCESS.equals(drawStatus)) {
				joinMapper.updateBalance(wd.getClientId(), join.getBalance().subtract(wd.getMoney()), join.getFrozenBalance().subtract(wd.getMoney()));
			}else {
				joinMapper.updateBalance(wd.getClientId(), null, join.getFrozenBalance().subtract(wd.getMoney()));
			}
		} else if(UserConstants.USER_TYPE_AGENT.equals(wd.getClientType())) { // 代理商
			Agent agent = agentMapper.selectAgentById(wd.getClientId());
			if(agent == null) {
				throw new RRException("未找到客户");
			}
			if(Const.WITHDRAW_SUCCESS.equals(drawStatus)) {
				agentMapper.updateBalance(wd.getClientId(), agent.getBalance().subtract(wd.getMoney()), agent.getFrozenBalance().subtract(wd.getMoney()));
			}else {
				agentMapper.updateBalance(wd.getClientId(), null, agent.getFrozenBalance().subtract(wd.getMoney()));
			}
		} else if(UserConstants.USER_TYPE_REPAIR.equals(wd.getClientType())) { // 服务商
			Repair repair = repairMapper.selectRepairById(wd.getClientId());
			if(repair == null) {
				throw new RRException("未找到客户");
			}
			if(Const.WITHDRAW_SUCCESS.equals(drawStatus)) {
				repairMapper.updateBalance(wd.getClientId(), repair.getBalance().subtract(wd.getMoney()), repair.getFrozenBalance().subtract(wd.getMoney()));
			}else {
				repairMapper.updateBalance(wd.getClientId(), null, repair.getFrozenBalance().subtract(wd.getMoney()));
			}
		} else if(UserConstants.USER_TYPE_ADVERTISE.equals(wd.getClientType())) { // 广告商
			Advertise advertise = advertiseMapper.selectAdvertiseById(wd.getClientId());
			if(advertise == null) {
				throw new RRException("未找到客户");
			}
			if(Const.WITHDRAW_SUCCESS.equals(drawStatus)) {
				advertiseMapper.updateBalance(wd.getClientId(), advertise.getBalance().subtract(wd.getMoney()), advertise.getFrozenBalance().subtract(wd.getMoney()));
			}else {
				advertiseMapper.updateBalance(wd.getClientId(), null, advertise.getFrozenBalance().subtract(wd.getMoney()));
			}
		} else {
			throw new RRException("未找到客户");
		}
	}

	@Override
	public void advertiseToUp(Integer advertiseId, BigDecimal money,String status) {
		if(money == null || money.compareTo(new BigDecimal(0)) <= 0) {
			throw new RRException("充值须大于0");
		}
		Advertise advertise = advertiseMapper.selectAdvertiseById(advertiseId);
		if(advertise == null) {
			throw new RRException("未找到客户");
		}
		BigDecimal balance = advertise.getBalance() == null ? new BigDecimal(0) : advertise.getBalance();
		BigDecimal frozenBalance = advertise.getFrozenBalance() == null ? new BigDecimal(0) : advertise.getFrozenBalance();
		if("1".equals(status)) {
			balance = balance.add(money);
			advertiseMapper.updateBalance(advertiseId, balance, null);
		}
		balance = balance.setScale(2, BigDecimal.ROUND_HALF_UP); // 四舍五入 保留两位
		// 充值记录
		FundLog fundLog = new FundLog();
		fundLog.setBalance(balance.subtract(frozenBalance).toString()); // 可用余额
		fundLog.setTotalFee("+"+money);
		fundLog.setClientId(advertiseId);
		fundLog.setClientType(UserConstants.USER_TYPE_ADVERTISE);
		fundLog.setContent("充值");
		fundLog.setType(Const.FUND_TOP_UP);
		fundLog.setStatus(status);
		fundLog.setCreateTime(new Date());
		fundLogMapper.insertFundLog(fundLog);
	}
	
}
