package com.zxiang.project.advertise.adPlaybill.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zxiang.project.advertise.adPlaybill.mapper.AdPlaybillMapper;
import com.zxiang.project.advertise.adPlaybill.domain.AdPlaybill;
import com.zxiang.project.advertise.adPlaybill.service.IAdPlaybillService;
import com.zxiang.common.support.Convert;

/**
 * 节目单 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Service
public class AdPlaybillServiceImpl implements IAdPlaybillService 
{
	@Autowired
	private AdPlaybillMapper adPlaybillMapper;

	/**
     * 查询节目单信息
     * 
     * @param playbillId 节目单ID
     * @return 节目单信息
     */
    @Override
	public AdPlaybill selectAdPlaybillById(String playbillId)
	{
	    return adPlaybillMapper.selectAdPlaybillById(playbillId);
	}
	
	/**
     * 查询节目单列表
     * 
     * @param adPlaybill 节目单信息
     * @return 节目单集合
     */
	@Override
	public List<AdPlaybill> selectAdPlaybillList(AdPlaybill adPlaybill)
	{
	    return adPlaybillMapper.selectAdPlaybillList(adPlaybill);
	}
	
    /**
     * 新增节目单
     * 
     * @param adPlaybill 节目单信息
     * @return 结果
     */
	@Override
	public int insertAdPlaybill(AdPlaybill adPlaybill)
	{
	    return adPlaybillMapper.insertAdPlaybill(adPlaybill);
	}
	
	/**
     * 修改节目单
     * 
     * @param adPlaybill 节目单信息
     * @return 结果
     */
	@Override
	public int updateAdPlaybill(AdPlaybill adPlaybill)
	{
	    return adPlaybillMapper.updateAdPlaybill(adPlaybill);
	}

	/**
     * 删除节目单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAdPlaybillByIds(String ids)
	{
		return adPlaybillMapper.deleteAdPlaybillByIds(Convert.toStrArray(ids));
	}
	
}
