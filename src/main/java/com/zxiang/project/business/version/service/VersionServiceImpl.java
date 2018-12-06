package com.zxiang.project.business.version.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.zxiang.common.exception.RRException;
import com.zxiang.common.support.Convert;
import com.zxiang.project.advertise.adSchedule.domain.AdSchedule;
import com.zxiang.project.advertise.utils.AdHttpResult;
import com.zxiang.project.advertise.utils.Tools;
import com.zxiang.project.advertise.utils.constant.AdConstant;
import com.zxiang.project.business.version.domain.Version;
import com.zxiang.project.business.version.mapper.VersionMapper;

/**
 * 版本 服务层实现
 * 
 * @author ZXiang
 * @date 2018-12-06
 */
@Service
public class VersionServiceImpl implements IVersionService 
{
	Logger logger = Logger.getLogger(VersionServiceImpl.class);
			
	@Autowired
	private VersionMapper versionMapper;

	/**
     * 查询版本信息
     * 
     * @param sysVerId 版本ID
     * @return 版本信息
     */
    @Override
	public Version selectVersionById(Integer sysVerId)
	{
	    return versionMapper.selectVersionById(sysVerId);
	}
	
	/**
     * 查询版本列表
     * 
     * @param version 版本信息
     * @return 版本集合
     */
	@Override
	public List<Version> selectVersionList(Version version)
	{
	    return versionMapper.selectVersionList(version);
	}
	
    /**
     * 新增版本
     * 
     * @param version 版本信息
     * @return 结果
     */
	@Override
	public int insertVersion(Version version)
	{
	    return versionMapper.insertVersion(version);
	}
	
	/**
     * 修改版本
     * 
     * @param version 版本信息
     * @return 结果
     */
	@Override
	public int updateVersion(Version version)
	{
	    return versionMapper.updateVersion(version);
	}

	/**
     * 删除版本对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteVersionByIds(String ids)
	{
		return versionMapper.deleteVersionByIds(Convert.toStrArray(ids));
	}

	@Override
	@Transactional
	public int uploadSave(HttpServletRequest request, List<MultipartFile> files, String operatorUser) {
		int saveNum = 0;
		try {
			String sysVerCode = request.getParameter("sysVerCode");
			String subject = request.getParameter("subject");
			String oldArtifactCode = request.getParameter("oldArtifactCode");
			String sysVerSubject = request.getParameter("sysVerSubject");
			String sysVerInfo = request.getParameter("sysVerInfo");
			
			//1.上传素材文件
			if (!files.isEmpty()) {
		    	//TODO 调用上传文件的接口
		    	String result = null;
		    	//返回结果封装
				AdHttpResult adHttp = Tools.analysisResult(result);
				if(AdConstant.RESPONSE_CODE_SUCCESS.equals(adHttp.getCode())){
					JSONObject jsonResult =  (JSONObject) adHttp.get("data");
					String filepath = jsonResult.getString("filepath");
					//TODO 业务处理 保存素材文件
					
				}else{
					logger.error("调用上传文件接口失败!" + adHttp.toString());
					throw new RRException("调用上传文件接口失败!");
				} 
		    } else {
		        logger.error("文件不能为空!");
		        throw new RRException("文件不能为空!");
		    }
			return saveNum;
			
		} catch (Exception e) {
			logger.error("materialUpload error: " + e);
			throw e;
		}
	
	}
	
}
