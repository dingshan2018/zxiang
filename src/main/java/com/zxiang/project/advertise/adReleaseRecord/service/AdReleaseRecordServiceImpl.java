package com.zxiang.project.advertise.adReleaseRecord.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zxiang.project.advertise.adReleaseRecord.mapper.AdReleaseRecordMapper;
import com.zxiang.project.advertise.adReleaseRecord.domain.AdReleaseRecord;
import com.zxiang.project.advertise.adReleaseRecord.service.IAdReleaseRecordService;
import com.zxiang.common.support.Convert;
import com.zxiang.common.utils.excel.EXCELObject;

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

	@Override
	public void queryExport(HashMap<String, String> params, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List erportList = adReleaseRecordMapper.queryExport(params);
      	String realPath = request.getSession().getServletContext().getRealPath("/file/temp");
  		EXCELObject s = new EXCELObject();
  		s.seteFilePath(realPath);
  		//表头名称
  		String[] titH = { "ID", "投放名称", "投放设备","下载时间","播放时间",
  				"播放状态", "播放价格"};
  		//数据库字段名称
  		String[] titN = { "release_device_id","scheduleName","deviceSn","down_time","play_time",
  				"status", "price"};
  		String[] width= 
  			   {"15","20","20","20","20",
  				"20","20"};
  		s.setWidth(width);
  		s.setFname("广告播放记录"); // sheet栏名称
  		s.setTitle("广告播放记录"); // Excel内容标题名称
  		s.setTitH(titH);
  		s.setTitN(titN);
  		s.setDataList(erportList);
  		File exportFile = null;
  		exportFile = s.setData();
  		//Excel文件名称
  		String excelName = "广告播放记录" + System.currentTimeMillis() + ".xls";
  		s.exportExcel("广告播放记录", excelName, exportFile, request, response);
	}
	
}
