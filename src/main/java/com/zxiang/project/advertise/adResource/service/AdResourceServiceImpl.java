package com.zxiang.project.advertise.adResource.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zxiang.project.advertise.adResource.mapper.AdResourceMapper;
import com.zxiang.project.advertise.adResource.domain.AdResource;
import com.zxiang.project.advertise.adResource.service.IAdResourceService;
import com.zxiang.common.support.Convert;

/**
 * 素材维护 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Service
public class AdResourceServiceImpl implements IAdResourceService 
{
	@Autowired
	private AdResourceMapper adResourceMapper;

	/**
     * 查询素材维护信息
     * 
     * @param resourceId 素材维护ID
     * @return 素材维护信息
     */
    @Override
	public AdResource selectAdResourceById(String resourceId)
	{
	    return adResourceMapper.selectAdResourceById(resourceId);
	}
	
	/**
     * 查询素材维护列表
     * 
     * @param adResource 素材维护信息
     * @return 素材维护集合
     */
	@Override
	public List<AdResource> selectAdResourceList(AdResource adResource)
	{
	    return adResourceMapper.selectAdResourceList(adResource);
	}
	
    /**
     * 新增素材维护
     * 
     * @param adResource 素材维护信息
     * @return 结果
     */
	@Override
	public int insertAdResource(AdResource adResource)
	{
	    return adResourceMapper.insertAdResource(adResource);
	}
	
	/**
     * 修改素材维护
     * 
     * @param adResource 素材维护信息
     * @return 结果
     */
	@Override
	public int updateAdResource(AdResource adResource)
	{
	    return adResourceMapper.updateAdResource(adResource);
	}

	/**
     * 删除素材维护对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAdResourceByIds(String ids)
	{
		return adResourceMapper.deleteAdResourceByIds(Convert.toStrArray(ids));
	}
	
}
