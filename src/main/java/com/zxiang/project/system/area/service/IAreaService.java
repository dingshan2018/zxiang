package com.zxiang.project.system.area.service;

import com.zxiang.project.system.area.domain.Area;
import java.util.List;

/**
 * 公共：区域 服务层
 * 
 * @author ZXiang
 * @date 2018-09-21
 */
public interface IAreaService 
{
	/**
     * 查询公共：区域信息
     * 
     * @param id 公共：区域ID
     * @return 公共：区域信息
     */
	public Area selectAreaById(Long id);
	
	/**
     * 查询公共：区域列表
     * 
     * @param area 公共：区域信息
     * @return 公共：区域集合
     */
	public List<Area> selectAreaList(Area area);
	
	/**
     * 新增公共：区域
     * 
     * @param area 公共：区域信息
     * @return 结果
     */
	public int insertArea(Area area);
	
	/**
     * 修改公共：区域
     * 
     * @param area 公共：区域信息
     * @return 结果
     */
	public int updateArea(Area area);
		
	/**
     * 删除公共：区域信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAreaByIds(String ids);
	
}
