package com.zxiang.project.advertise.releaseDetail.mapper;

import java.util.List;

import com.zxiang.project.advertise.releaseDetail.domain.ReleaseDetail;	

/**
 * 广告播放明细 数据层
 * 
 * @author ZXiang
 * @date 2020-01-11
 */
public interface ReleaseDetailMapper 
{
	/**
     * 查询广告播放明细信息
     * 
     * @param releaseDetailId 广告播放明细ID
     * @return 广告播放明细信息
     */
	public ReleaseDetail selectReleaseDetailById(Integer releaseDetailId);
	
	/**
     * 查询广告播放明细列表
     * 
     * @param releaseDetail 广告播放明细信息
     * @return 广告播放明细集合
     */
	public List<ReleaseDetail> selectReleaseDetailList(ReleaseDetail releaseDetail);
	
	/**
     * 新增广告播放明细
     * 
     * @param releaseDetail 广告播放明细信息
     * @return 结果
     */
	public int insertReleaseDetail(ReleaseDetail releaseDetail);
	
	/**
     * 修改广告播放明细
     * 
     * @param releaseDetail 广告播放明细信息
     * @return 结果
     */
	public int updateReleaseDetail(ReleaseDetail releaseDetail);
	
	/**
     * 删除广告播放明细
     * 
     * @param releaseDetailId 广告播放明细ID
     * @return 结果
     */
	public int deleteReleaseDetailById(Integer releaseDetailId);
	
	/**
     * 批量删除广告播放明细
     * 
     * @param releaseDetailIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteReleaseDetailByIds(String[] releaseDetailIds);
	
}