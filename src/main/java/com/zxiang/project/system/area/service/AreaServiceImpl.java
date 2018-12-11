package com.zxiang.project.system.area.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zxiang.project.system.area.mapper.AreaMapper;
import com.zxiang.project.system.area.domain.Area;
import com.zxiang.project.system.area.service.IAreaService;
import com.zxiang.common.support.Convert;

/**
 * 公共：区域 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-21
 */
@Service
public class AreaServiceImpl implements IAreaService 
{
	@Autowired
	private AreaMapper areaMapper;

	/**
     * 查询公共：区域信息
     * 
     * @param id 公共：区域ID
     * @return 公共：区域信息
     */
    @Override
	public Area selectAreaById(Long id)
	{
	    return areaMapper.selectAreaById(id);
	}
	
	/**
     * 查询公共：区域列表
     * 
     * @param area 公共：区域信息
     * @return 公共：区域集合
     */
	@Override
	public List<Area> selectAreaList(Area area)
	{
	    return areaMapper.selectAreaList(area);
	}
	
    /**
     * 新增公共：区域
     * 
     * @param area 公共：区域信息
     * @return 结果
     */
	@Override
	public int insertArea(Area area)
	{
	    return areaMapper.insertArea(area);
	}
	
	/**
     * 修改公共：区域
     * 
     * @param area 公共：区域信息
     * @return 结果
     */
	@Override
	public int updateArea(Area area)
	{
	    return areaMapper.updateArea(area);
	}

	/**
     * 删除公共：区域对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAreaByIds(String ids)
	{
		return areaMapper.deleteAreaByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<Area> selectDropBoxList(long parentId) {
		return areaMapper.selectDropBoxList(parentId);
	}
	
}
