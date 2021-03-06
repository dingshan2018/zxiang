package com.zxiang.project.business.place.mapper;

import com.zxiang.project.business.place.domain.Place;
import java.util.List;	

/**
 * 场所管理 数据层
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
public interface PlaceMapper 
{
	/**
     * 查询场所管理信息
     * 
     * @param placeId 场所管理ID
     * @return 场所管理信息
     */
	public Place selectPlaceById(Integer placeId);
	
	/**
     * 查询场所管理列表
     * 
     * @param place 场所管理信息
     * @return 场所管理集合
     */
	public List<Place> selectPlaceList(Place place);
	
	/**
     * 新增场所管理
     * 
     * @param place 场所管理信息
     * @return 结果
     */
	public int insertPlace(Place place);
	
	/**
     * 修改场所管理
     * 
     * @param place 场所管理信息
     * @return 结果
     */
	public int updatePlace(Place place);
	
	/**
     * 删除场所管理
     * 
     * @param placeId 场所管理ID
     * @return 结果
     */
	public int deletePlaceById(Integer placeId);
	
	/**
     * 批量删除场所管理
     * 
     * @param placeIds 需要删除的数据ID
     * @return 结果
     */
	public int deletePlaceByIds(String[] placeIds);
	
}