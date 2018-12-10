package com.zxiang.project.business.supplyTissue.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zxiang.common.support.Convert;
import com.zxiang.common.utils.excel.EXCELObject;
import com.zxiang.project.business.device.domain.Device;
import com.zxiang.project.business.device.mapper.DeviceMapper;
import com.zxiang.project.business.supplyTissue.domain.SupplyTissue;
import com.zxiang.project.business.supplyTissue.mapper.SupplyTissueMapper;

/**
 * 补纸记录 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
@Service
public class SupplyTissueServiceImpl implements ISupplyTissueService 
{
	@Autowired
	private SupplyTissueMapper supplyTissueMapper;
	@Autowired
	private DeviceMapper deviceMapper;

	/**
     * 查询补纸记录信息
     * 
     * @param supplyTissueId 补纸记录ID
     * @return 补纸记录信息
     */
    @Override
	public SupplyTissue selectSupplyTissueById(Integer supplyTissueId)
	{
	    return supplyTissueMapper.selectSupplyTissueById(supplyTissueId);
	}
	
	/**
     * 查询补纸记录列表
     * 
     * @param supplyTissue 补纸记录信息
     * @return 补纸记录集合
     */
	@Override
	public List<SupplyTissue> selectSupplyTissueList(SupplyTissue supplyTissue)
	{
	    return supplyTissueMapper.selectSupplyTissueList(supplyTissue);
	}
	
    /**
     * 新增补纸记录
     * 
     * @param supplyTissue 补纸记录信息
     * @return 结果
     */
	@Override
	public int insertSupplyTissue(SupplyTissue supplyTissue)
	{
	    return supplyTissueMapper.insertSupplyTissue(supplyTissue);
	}
	
	/**
     * 修改补纸记录
     * 
     * @param supplyTissue 补纸记录信息
     * @return 结果
     */
	@Override
	public int updateSupplyTissue(SupplyTissue supplyTissue)
	{
	    return supplyTissueMapper.updateSupplyTissue(supplyTissue);
	}

	/**
     * 删除补纸记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSupplyTissueByIds(String ids)
	{
		return supplyTissueMapper.deleteSupplyTissueByIds(Convert.toStrArray(ids));
	}

	@Override
	public void queryExport(HashMap<String, String> params, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List erportList = supplyTissueMapper.queryExport(params);
      	String realPath = request.getSession().getServletContext().getRealPath("/file/temp");
  		EXCELObject s = new EXCELObject();
  		s.seteFilePath(realPath);
  		//表头名称
  		String[] titH = { "ID", "补纸设备", "补纸数量","补纸场所","补纸人员",
  				"创建者", "创建时间"};
  		//数据库字段名称
  		String[] titN = { "supply_tissue_id","deviceSn","tissue_count","placeName","supplierName",
  				"create_by", "create_time"};
  		String[] width= 
  			   {"15","20","20","20","20",
  				"20","20"};
  		s.setWidth(width);
  		s.setFname("补纸记录"); // sheet栏名称
  		s.setTitle("补纸记录"); // Excel内容标题名称
  		s.setTitH(titH);
  		s.setTitN(titN);
  		s.setDataList(erportList);
  		File exportFile = null;
  		exportFile = s.setData();
  		//Excel文件名称
  		String excelName = "补纸记录" + System.currentTimeMillis() + ".xls";
  		s.exportExcel("补纸记录", excelName, exportFile, request, response);
	}

	/**
	 * 设备补纸保存
	 */
	@Override
	@Transactional
	public int supplyTissueSave(SupplyTissue supplyTissue) {
		Device device = deviceMapper.selectDeviceById(supplyTissue.getDeviceId());
		if(device != null){
			Integer remainLen = device.getRemainLen();
			if(remainLen == null){
				remainLen = 0;
			}
			remainLen = remainLen + supplyTissue.getTissueCount();
			device.setRemainLen(remainLen);
			deviceMapper.updateDevice(device);
		}
		return supplyTissueMapper.insertSupplyTissue(supplyTissue);
	}
	
}
