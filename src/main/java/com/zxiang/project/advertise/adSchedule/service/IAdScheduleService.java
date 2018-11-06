package com.zxiang.project.advertise.adSchedule.service;

import com.zxiang.project.advertise.adSchedule.domain.AdSchedule;
import com.zxiang.project.advertise.adSchedule.domain.ThemeTemplate;

import java.util.List;
import java.util.Map;

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
	 * 广告投放预约保存
	 * @param adSchedule
	 */
	public int orderSave(AdSchedule adSchedule);
	
	/**
	 * 广告投放审核
	 * @param adSchedule
	 * @param operatorUser 
	 */
	public int auditSave(AdSchedule adSchedule, String operatorUser);

	
	/**
	 * 广告投放发布
	 * @param adSchedule
	 */
	public int releaseOnlineSave(AdSchedule adSchedule);

	
	/**
	 * 获取模板详细信息列表
	 */
	public List<ThemeTemplate> getThemeList();

	/**
	 * 新增广告并且保存推广计划
	 * @param adSchedule
	 * @return
	 */
	public int saveAdTemplates(AdSchedule adSchedule);

	/**
	 * 素材上传调用HTTP接口保存文件
	 * 
	 * @param files
	 * @param adScheduleId
	 * @return
	 */
	public int materialUpload(List<MultipartFile> files, String adScheduleId);
	

}
