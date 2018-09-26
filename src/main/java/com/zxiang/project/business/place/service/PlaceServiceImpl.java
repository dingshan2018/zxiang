package com.zxiang.project.business.place.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxiang.common.support.Convert;
import com.zxiang.project.business.place.domain.Place;
import com.zxiang.project.business.place.mapper.PlaceMapper;

/**
 * 场所管理 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
@Service
public class PlaceServiceImpl implements IPlaceService 
{
	@Autowired
	private PlaceMapper placeMapper;

	/**
     * 查询场所管理信息
     * 
     * @param placeId 场所管理ID
     * @return 场所管理信息
     */
    @Override
	public Place selectPlaceById(Integer placeId)
	{
	    return placeMapper.selectPlaceById(placeId);
	}
	
	/**
     * 查询场所管理列表
     * 
     * @param place 场所管理信息
     * @return 场所管理集合
     */
	@Override
	public List<Place> selectPlaceList(Place place)
	{
	    return placeMapper.selectPlaceList(place);
	}
	
    /**
     * 新增场所管理
     * 
     * @param place 场所管理信息
     * @return 结果
     */
	@Override
	public int insertPlace(Place place)
	{
	    return placeMapper.insertPlace(place);
	}
	
	/**
     * 修改场所管理
     * 
     * @param place 场所管理信息
     * @return 结果
     */
	@Override
	public int updatePlace(Place place)
	{
	    return placeMapper.updatePlace(place);
	}

	/**
     * 删除场所管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePlaceByIds(String ids)
	{
		return placeMapper.deletePlaceByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<Place> selectDropBoxList() {
		return placeMapper.selectDropBoxList();
	}

	@Override
	public String checkPlaceCodeUnique(String placeCode) {
		 int count = placeMapper.checkPlaceCodeUnique(placeCode);
        if (count > 0)
        {
            return "1";
        }
        return "0";
	}
	
}
