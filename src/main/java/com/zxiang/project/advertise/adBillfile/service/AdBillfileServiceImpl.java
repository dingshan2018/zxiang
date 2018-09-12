package com.zxiang.project.advertise.adBillfile.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zxiang.project.advertise.adBillfile.mapper.AdBillfileMapper;
import com.zxiang.project.advertise.adBillfile.domain.AdBillfile;
import com.zxiang.project.advertise.adBillfile.service.IAdBillfileService;
import com.zxiang.common.support.Convert;

/**
 * 节目单所生成的文件 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Service
public class AdBillfileServiceImpl implements IAdBillfileService 
{
	@Autowired
	private AdBillfileMapper adBillfileMapper;

	/**
     * 查询节目单所生成的文件信息
     * 
     * @param billfileId 节目单所生成的文件ID
     * @return 节目单所生成的文件信息
     */
    @Override
	public AdBillfile selectAdBillfileById(String billfileId)
	{
	    return adBillfileMapper.selectAdBillfileById(billfileId);
	}
	
	/**
     * 查询节目单所生成的文件列表
     * 
     * @param adBillfile 节目单所生成的文件信息
     * @return 节目单所生成的文件集合
     */
	@Override
	public List<AdBillfile> selectAdBillfileList(AdBillfile adBillfile)
	{
	    return adBillfileMapper.selectAdBillfileList(adBillfile);
	}
	
    /**
     * 新增节目单所生成的文件
     * 
     * @param adBillfile 节目单所生成的文件信息
     * @return 结果
     */
	@Override
	public int insertAdBillfile(AdBillfile adBillfile)
	{
	    return adBillfileMapper.insertAdBillfile(adBillfile);
	}
	
	/**
     * 修改节目单所生成的文件
     * 
     * @param adBillfile 节目单所生成的文件信息
     * @return 结果
     */
	@Override
	public int updateAdBillfile(AdBillfile adBillfile)
	{
	    return adBillfileMapper.updateAdBillfile(adBillfile);
	}

	/**
     * 删除节目单所生成的文件对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAdBillfileByIds(String ids)
	{
		return adBillfileMapper.deleteAdBillfileByIds(Convert.toStrArray(ids));
	}
	
}
