package com.zxiang.project.advertise.adSchedule.service;

import com.zxiang.project.advertise.adSchedule.domain.AdSchedule;
import com.zxiang.project.advertise.adSchedule.domain.ElementType;
import com.zxiang.project.advertise.adSchedule.domain.ThemeTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

/**
 * 广告投放 服务层
 * 
 * @author ZXiang
 * @date 2018-09-24
 */
public interface IAdScheduleService 
{
	/**
     * 查询广告投放信息
     * 
     * @param adScheduleId 广告投放ID
     * @return 广告投放信息
     */
	public AdSchedule selectAdScheduleById(Integer adScheduleId);
	
	/**
     * 查询广告投放列表
     * 
     * @param adSchedule 广告投放信息
     * @return 广告投放集合
     */
	public List<AdSchedule> selectAdScheduleList(AdSchedule adSchedule);
	
	/**
     * 新增广告投放
     * 
     * @param adSchedule 广告投放信息
     * @return 结果
     */
	public int insertAdSchedule(AdSchedule adSchedule);
	
	/**
     * 修改广告投放
     * 
     * @param adSchedule 广告投放信息
     * @return 结果
     */
	public int updateAdSchedule(AdSchedule adSchedule);
		
	/**
     * 删除广告投放信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAdScheduleByIds(String ids);

	
	/**
	 * 获取模板详细信息列表
	 */
	public List<ThemeTemplate> getThemeList();
	
	/**
	 * 通过模板ID获取模板元素
	 * @param themeTemplateId
	 * @return
	 * @throws IOException 
	 */
	public List<ElementType> getElementList(String themeTemplateId) throws IOException;

	/**
	 * 新增广告并且保存推广计划
	 * @param adSchedule
	 * @return
	 */
	public int saveAdTemplates(AdSchedule adSchedule) throws Exception;

	/**
	 * 素材上传调用HTTP接口保存文件
	 * 
	 * @param files
	 * @param adScheduleId
	 * @param elementId 
	 * @param operatorUser
	 * @return
	 */
	public int materialUpload(List<MultipartFile> files, String adScheduleId, 
			String elementId,String operatorUser) throws Exception;
	
	/**
	 * 广告投放预约保存
	 * @param adSchedule
	 * @param operatorUser 
	 */
	public int orderSave(AdSchedule adSchedule, String operatorUser) throws Exception;
	
	/**
	 * 广告投放审核
	 * @param adSchedule
	 * @param operatorUser 
	 */
	public int auditSave(AdSchedule adSchedule, String operatorUser) throws Exception;

	
	/**
	 * 广告投放发布
	 * @param adSchedule
	 */
	public int releaseOnlineSave(AdSchedule adSchedule);

	/**
	 * 导出Excel
	 * @param params
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	public void queryExport(HashMap<String, String> params, HttpServletRequest request, HttpServletResponse response) throws Exception;


}
