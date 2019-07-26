package com.zxiang.project.business.place.service;

import com.zxiang.project.business.place.domain.Place;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 场所管理 服务层
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
public interface IPlaceService 
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
     * 删除场所管理信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePlaceByIds(String ids);

	/**
	 * 查询场所下拉框数据列表
	 */
	public List<Place> selectDropBoxList();

	/**
     * 校验场所编号
     */
	public String checkPlaceCodeUnique(String placeCode);

	/**
	 * 导出Excel文件
	 * @param params
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	public void queryExport(HashMap<String, String> params, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
