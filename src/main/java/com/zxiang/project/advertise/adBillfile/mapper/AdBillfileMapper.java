package com.zxiang.project.advertise.adBillfile.mapper;

import com.zxiang.project.advertise.adBillfile.domain.AdBillfile;
import java.util.List;	

/**
 * 节目单所生成的文件 数据层
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
public interface AdBillfileMapper 
{
	/**
     * 查询节目单所生成的文件信息
     * 
     * @param billfileId 节目单所生成的文件ID
     * @return 节目单所生成的文件信息
     */
	public AdBillfile selectAdBillfileById(String billfileId);
	
	/**
     * 查询节目单所生成的文件列表
     * 
     * @param adBillfile 节目单所生成的文件信息
     * @return 节目单所生成的文件集合
     */
	public List<AdBillfile> selectAdBillfileList(AdBillfile adBillfile);
	
	/**
     * 新增节目单所生成的文件
     * 
     * @param adBillfile 节目单所生成的文件信息
     * @return 结果
     */
	public int insertAdBillfile(AdBillfile adBillfile);
	
	/**
     * 修改节目单所生成的文件
     * 
     * @param adBillfile 节目单所生成的文件信息
     * @return 结果
     */
	public int updateAdBillfile(AdBillfile adBillfile);
	
	/**
     * 删除节目单所生成的文件
     * 
     * @param billfileId 节目单所生成的文件ID
     * @return 结果
     */
	public int deleteAdBillfileById(String billfileId);
	
	/**
     * 批量删除节目单所生成的文件
     * 
     * @param billfileIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteAdBillfileByIds(String[] billfileIds);
	
}