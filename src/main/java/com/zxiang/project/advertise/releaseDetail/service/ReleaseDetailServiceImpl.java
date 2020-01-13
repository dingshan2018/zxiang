package com.zxiang.project.advertise.releaseDetail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxiang.common.support.Convert;
import com.zxiang.project.advertise.releaseDetail.domain.ReleaseDetail;
import com.zxiang.project.advertise.releaseDetail.mapper.ReleaseDetailMapper;

/**
 * 广告播放明细 服务层实现
 * 
 * @author ZXiang
 * @date 2020-01-11
 */
@Service
public class ReleaseDetailServiceImpl implements IReleaseDetailService 
{
	@Autowired
	private ReleaseDetailMapper releaseDetailMapper;

	/**
     * 查询广告播放明细信息
     * 
     * @param releaseDetailId 广告播放明细ID
     * @return 广告播放明细信息
     */
    @Override
	public ReleaseDetail selectReleaseDetailById(Integer releaseDetailId)
	{
	    return releaseDetailMapper.selectReleaseDetailById(releaseDetailId);
	}
	
	/**
     * 查询广告播放明细列表
     * 
     * @param releaseDetail 广告播放明细信息
     * @return 广告播放明细集合
     */
	@Override
	public List<ReleaseDetail> selectReleaseDetailList(ReleaseDetail releaseDetail)
	{
	    return releaseDetailMapper.selectReleaseDetailList(releaseDetail);
	}
	
    /**
     * 新增广告播放明细
     * 
     * @param releaseDetail 广告播放明细信息
     * @return 结果
     */
	@Override
	public int insertReleaseDetail(ReleaseDetail releaseDetail)
	{
	    return releaseDetailMapper.insertReleaseDetail(releaseDetail);
	}
	
	/**
     * 修改广告播放明细
     * 
     * @param releaseDetail 广告播放明细信息
     * @return 结果
     */
	@Override
	public int updateReleaseDetail(ReleaseDetail releaseDetail)
	{
	    return releaseDetailMapper.updateReleaseDetail(releaseDetail);
	}

	/**
     * 删除广告播放明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteReleaseDetailByIds(String ids)
	{
		return releaseDetailMapper.deleteReleaseDetailByIds(Convert.toStrArray(ids));
	}
	
}
