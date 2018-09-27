package com.zxiang.project.business.place.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mchange.lang.StringUtils;
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
     * 新增场所管理,场所管理编号按照地区流水号增长
     * 
     * @param place 场所管理信息
     * @return 结果
     */
	@Override
	public int insertPlace(Place place)
	{
		Integer county = place.getCounty();
		String placeCode = getAutoCodeNum(county);
		placeCode = county + placeCode;
		place.setPlaceCode(placeCode);		
		
	    return placeMapper.insertPlace(place);
	}
	
	/**
	 * 根据地区编码查询某地区最大场所流水号并+1后返回
	 * 若有该地区编号则加1，若没有则初始1开始
	 * @param county
	 * @return
	 */
	public synchronized String getAutoCodeNum(Integer county){
		String currentMaxCode = placeMapper.getMaxPlaceCode(county);
		if(com.zxiang.common.utils.StringUtils.isNotEmpty(currentMaxCode)){
			String currentMax = currentMaxCode.substring(6);
			int currentMaxNum = Integer.parseInt(currentMax);
			++currentMaxNum;
			return String.format("%06d", currentMaxNum);
		}
		
		return String.format("%06d", 1);
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
