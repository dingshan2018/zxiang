package com.zxiang.project.client.join.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxiang.common.support.Convert;
import com.zxiang.common.utils.security.ShiroUtils;
import com.zxiang.project.client.join.domain.Join;
import com.zxiang.project.client.join.mapper.JoinMapper;

/**
 * 加盟商 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Service
public class JoinServiceImpl implements IJoinService 
{
	@Autowired
	private JoinMapper joinMapper;

	/**
     * 查询加盟商信息
     * 
     * @param joinId 加盟商ID
     * @return 加盟商信息
     */
    @Override
	public Join selectJoinById(Integer joinId)
	{
	    return joinMapper.selectJoinById(joinId);
	}
	
	/**
     * 查询加盟商列表
     * 
     * @param join 加盟商信息
     * @return 加盟商集合
     */
	@Override
	public List<Join> selectJoinList(Join join)
	{
	    return joinMapper.selectJoinList(join);
	}
	
    /**
     * 新增加盟商
     * 
     * @param join 加盟商信息
     * @return 结果
     */
	@Override
	public int insertJoin(Join join) {
		join.setCreateTime(new Date());
		join.setCreateBy(ShiroUtils.getLoginName());
	    return joinMapper.insertJoin(join);
	}
	
	/**
     * 修改加盟商
     * 
     * @param join 加盟商信息
     * @return 结果
     */
	@Override
	public int updateJoin(Join join) {
		join.setUpdateTime(new Date());
		join.setUpdateBy(ShiroUtils.getLoginName());
	    return joinMapper.updateJoin(join);
	}

	/**
     * 删除加盟商对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteJoinByIds(String ids)
	{
		return joinMapper.deleteJoinByIds(Convert.toStrArray(ids));
	}
	
}
