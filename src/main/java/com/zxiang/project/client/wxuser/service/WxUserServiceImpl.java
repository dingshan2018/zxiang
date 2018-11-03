package com.zxiang.project.client.wxuser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxiang.common.support.Convert;
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
	
}
