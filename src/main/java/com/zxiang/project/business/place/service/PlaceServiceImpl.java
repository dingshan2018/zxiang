package com.zxiang.project.business.place.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxiang.common.support.Convert;
import com.zxiang.common.utils.excel.EXCELObject;
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
	    return getAutoCodeNum(place);
	}
	
	/**
	 * 根据地区编码查询某地区最大场所流水号并+1后返回
	 * 若有该地区编号则加1，若没有则初始1开始
	 * @param county
	 * @return
	 */
	public synchronized int getAutoCodeNum(Place place){
		Integer county = place.getCounty();
		String placeCode = "";
		
		String currentMaxCode = placeMapper.getMaxPlaceCode(county);
		if(com.zxiang.common.utils.StringUtils.isNotEmpty(currentMaxCode)){
			String currentMax = currentMaxCode.substring(6);
			int currentMaxNum = Integer.parseInt(currentMax);
			++currentMaxNum;
			placeCode = String.format("%06d", currentMaxNum);
		}else{
			placeCode = String.format("%06d", 1);
		}
		
		placeCode = county + placeCode;
		place.setPlaceCode(placeCode);
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

	@Override
	public void queryExport(HashMap<String, String> params, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List erportList = placeMapper.queryExport(params);
      	String realPath = request.getSession().getServletContext().getRealPath("/file/temp");
  		EXCELObject s = new EXCELObject();
  		s.seteFilePath(realPath);
  		//表头名称
  		String[] titH = { "ID", "场所名称", "场所编号","场所类型","场景",
  				"详细地址", "省份","城市", "区县","服务网点",
  				"地级市代理", "区县代理","维修员", "送纸员","投放数量",
  				"场所状态","备注"};
  		//SQL方法查询出的字段名称
  		String[] titN = { "place_id","name","place_code","place_type","sceneName",
  				"address","provinceName","cityName","countyName","servicePointName",
  				"agentLevel1","agentLevel2","repairName","supplyName","device_count",
  				"status", "note"};
  		String[] width= 
  			   {"15","20","20","20","20",
  				"20","20","20","20","20",
  				"20","20","20","20","20",
  				"20","20"};
  		s.setWidth(width);
  		s.setFname("场所数据"); // sheet栏名称
  		s.setTitle("场所数据"); // Excel内容标题名称
  		s.setTitH(titH);
  		s.setTitN(titN);
  		s.setDataList(erportList);
  		File exportFile = null;
  		exportFile = s.setData();
  		//Excel文件名称
  		String excelName = "场所数据" + System.currentTimeMillis() + ".xls";
  		s.exportExcel("场所数据", excelName, exportFile, request, response);
	}
	
}
