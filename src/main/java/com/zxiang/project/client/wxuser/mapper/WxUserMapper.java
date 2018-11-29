package com.zxiang.project.client.wxuser.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zxiang.project.client.wxuser.domain.WxUser;	

/**
 * 微信粉丝 数据层
 * 
 * @author ZXiang
 * @date 2018-11-03
 */
public interface WxUserMapper 
{
	/**
     * 查询微信粉丝信息
     * 
     * @param wxUserId 微信粉丝ID
     * @return 微信粉丝信息
     */
	public WxUser selectWxUserById(Integer wxUserId);
	
	/**
     * 查询微信粉丝列表
     * 
     * @param wxUser 微信粉丝信息
     * @return 微信粉丝集合
     */
	public List<WxUser> selectWxUserList(WxUser wxUser);
	
	/**
     * 新增微信粉丝
     * 
     * @param wxUser 微信粉丝信息
     * @return 结果
     */
	public int insertWxUser(WxUser wxUser);
	
	/**
     * 修改微信粉丝
     * 
     * @param wxUser 微信粉丝信息
     * @return 结果
     */
	public int updateWxUser(WxUser wxUser);
	
	/**
     * 删除微信粉丝
     * 
     * @param wxUserId 微信粉丝ID
     * @return 结果
     */
	public int deleteWxUserById(Integer wxUserId);
	
	/**
     * 批量删除微信粉丝
     * 
     * @param wxUserIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteWxUserByIds(String[] wxUserIds);
	
	int queryFansTotalBySex(@Param("sex")Integer sex,@Param("selDate")String selDate);

	public Integer queryByUserId(@Param("userId")Long userId);

	public int updateUserIdNull(Integer wcUserId);

	public WxUser selectByOpenId(String openId);
	/**
	 * 微信登录查询用户角色
	 * @param userId
	 * @return
	 */
	public List<String> selectPermsByUserId(Integer userId);

	public Integer selectUserIdByOpenId(String openId);
	public WxUser selectObjectByOpenId(String openId);
	
}