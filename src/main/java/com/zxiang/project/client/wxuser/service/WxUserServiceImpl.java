package com.zxiang.project.client.wxuser.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxiang.common.constant.Const;
import com.zxiang.common.support.Convert;
import com.zxiang.common.utils.DateUtils;
import com.zxiang.project.business.device.mapper.DeviceMapper;
import com.zxiang.project.business.tissueRecord.mapper.TissueRecordMapper;
import com.zxiang.project.client.advertise.mapper.AdvertiseMapper;
import com.zxiang.project.client.agent.mapper.AgentMapper;
import com.zxiang.project.client.wxuser.domain.WxUser;
import com.zxiang.project.client.wxuser.mapper.WxUserMapper;

/**
 * 微信粉丝 服务层实现
 * 
 * @author ZXiang
 * @date 2018-11-03
 */
@Service
public class WxUserServiceImpl implements IWxUserService 
{
	@Autowired
	private WxUserMapper wxUserMapper;
	@Autowired
	private DeviceMapper deviceMapper;
	@Autowired
	private AgentMapper agentMapper;
	@Autowired
	private AdvertiseMapper advertiseMapper;
	@Autowired
	private TissueRecordMapper tissueRecordMapper;

	/**
     * 查询微信粉丝信息
     * 
     * @param wxUserId 微信粉丝ID
     * @return 微信粉丝信息
     */
    @Override
	public WxUser selectWxUserById(Integer wxUserId)
	{
	    return wxUserMapper.selectWxUserById(wxUserId);
	}
	
	/**
     * 查询微信粉丝列表
     * 
     * @param wxUser 微信粉丝信息
     * @return 微信粉丝集合
     */
	@Override
	public List<WxUser> selectWxUserList(WxUser wxUser)
	{
	    return wxUserMapper.selectWxUserList(wxUser);
	}
	
    /**
     * 新增微信粉丝
     * 
     * @param wxUser 微信粉丝信息
     * @return 结果
     */
	@Override
	public int insertWxUser(WxUser wxUser)
	{
	    return wxUserMapper.insertWxUser(wxUser);
	}
	
	/**
     * 修改微信粉丝
     * 
     * @param wxUser 微信粉丝信息
     * @return 结果
     */
	@Override
	public int updateWxUser(WxUser wxUser)
	{
	    return wxUserMapper.updateWxUser(wxUser);
	}

	/**
     * 删除微信粉丝对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWxUserByIds(String ids)
	{
		return wxUserMapper.deleteWxUserByIds(Convert.toStrArray(ids));
	}

	@Override
	public Map<String, Object> systemIndicators() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tissueTotal", tissueRecordMapper.selectTotal(null)); // 累计出纸量
		map.put("fansTotal", wxUserMapper.queryFansTotalBySex(null, null)); // 累计粉丝量
		map.put("deviceTotal", deviceMapper.selectTotal()); // 设备总量
		map.put("agentTotal", agentMapper.selectTotal()); // 代理商总数
		map.put("fansManTotal", wxUserMapper.queryFansTotalBySex(Const.SEX_MAN, null)); // 累计男粉丝总量
		map.put("fansWomanTotal", wxUserMapper.queryFansTotalBySex(Const.SEX_WOMAN, null)); // 累计女粉丝总量
		map.put("fansNotSexTotal", wxUserMapper.queryFansTotalBySex(Const.SEX_NOT, null)); // 未知性别
		map.put("advertiseTotal", advertiseMapper.selectTotal()); // 广告主总量
		return map;
	}

	@Override
	public Map<String, Object> tissueStatistical() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>> ();
		Map<String, Object> data = null;
		Calendar c = null;
		String selDate = null;
		for (int i = 6; i >= 0; i--) {
			data = new HashMap<String, Object>();
			c = Calendar.getInstance();
			c.add(Calendar.DAY_OF_MONTH, -i);
			selDate = DateUtils.formate(c.getTime());
			data.put("tissueTotal", tissueRecordMapper.selectTotal(selDate)); // 单日出纸总量
			data.put("fansManTotal", wxUserMapper.queryFansTotalBySex(Const.SEX_MAN, selDate)); // 单日累计男粉丝总量
			data.put("fansWomanTotal", wxUserMapper.queryFansTotalBySex(Const.SEX_WOMAN, selDate)); // 单日累计女粉丝总量
			data.put("fansNotSexTotal", wxUserMapper.queryFansTotalBySex(Const.SEX_NOT, selDate)); // 单日未知性别
			data.put("selDate", selDate);
			list.add(data);
		}
		map.put("dataList", list);
		return map;
	}


	@Override
	public int updateUserIdNull(Integer wcUserId) {
		return wxUserMapper.updateUserIdNull(wcUserId);
	}
}
