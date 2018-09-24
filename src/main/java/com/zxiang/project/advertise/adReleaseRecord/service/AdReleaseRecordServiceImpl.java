package com.zxiang.project.advertise.adReleaseRecord.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zxiang.project.advertise.adReleaseRecord.mapper.AdReleaseRecordMapper;
import com.zxiang.project.advertise.adReleaseRecord.domain.AdReleaseRecord;
import com.zxiang.project.advertise.adReleaseRecord.service.IAdReleaseRecordService;
import com.zxiang.common.support.Convert;

/**
 * 广告投放设备 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-25
 */
@Service
public class AdReleaseRecordServiceImpl implements IAdReleaseRecordService 
{
	@Autowired
	private AdReleaseRecordMapper adReleaseRecordMapper;

	/**
     * 查询广告投放设备信息
     * 
     * @param releaseDeviceId 广告投放设备ID
     * @return 广告投放设备信息
     */
    @Override
	public AdReleaseRecord selectAdReleaseRecordById(Integer releaseDeviceId)
	{
	    return adReleaseRecordMapper.selectAdReleaseRecordById(releaseDeviceId);
	}
	
	/**
     * 查询广告投放设备列表
     * 
     * @param adReleaseRecord 广告投放设备信息
     * @return 广告投放设备集合
     */
	@Override
	public List<AdReleaseRecord> selectAdReleaseRecordList(AdReleaseRecord adReleaseRecord)
	{
	    return adReleaseRecordMapper.selectAdReleaseRecordList(adReleaseRecord);
	}
	
    /**
     * 新增广告投放设备
     * 
     * @param adReleaseRecord 广告投放设备信息
     * @return 结果
     */
	@Override
	public int insertAdReleaseRecord(AdReleaseRecord adReleaseRecord)
	{
	    return adReleaseRecordMapper.insertAdReleaseRecord(adReleaseRecord);
	}
	
	/**
     * 修改广告投放设备
     * 
     * @param adReleaseRecord 广告投放设备信息
     * @return 结果
     */
	@Override
	public int updateAdReleaseRecord(AdReleaseRecord adReleaseRecord)
	{
	    return adReleaseRecordMapper.updateAdReleaseRecord(adReleaseRecord);
	}

	/**
     * 删除广告投放设备对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAdReleaseRecordByIds(String ids)
	{
		return adReleaseRecordMapper.deleteAdReleaseRecordByIds(Convert.toStrArray(ids));
	}
	
}
