package com.zxiang.project.advertise.adMaterial.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxiang.common.support.Convert;
import com.zxiang.project.advertise.adMaterial.domain.AdMaterial;
import com.zxiang.project.advertise.adMaterial.mapper.AdMaterialMapper;
import com.zxiang.project.advertise.adSchedule.domain.AdSchedule;
import com.zxiang.project.advertise.adSchedule.domain.ElementType;
import com.zxiang.project.advertise.adSchedule.service.IAdScheduleService;

/**
 * 广告投放素材 服务层实现
 * 
 * @author ZXiang
 * @date 2018-11-08
 */
@Service
public class AdMaterialServiceImpl implements IAdMaterialService 
{
	@Autowired
	private AdMaterialMapper adMaterialMapper;
	@Autowired
	private IAdScheduleService adScheduleService;
	
	/**
     * 查询广告投放素材信息
     * 
     * @param adMaterialId 广告投放素材ID
     * @return 广告投放素材信息
     */
    @Override
	public AdMaterial selectAdMaterialById(Integer adMaterialId)
	{
	    return adMaterialMapper.selectAdMaterialById(adMaterialId);
	}
	
	/**
     * 查询广告投放素材列表
     * 
     * @param adMaterial 广告投放素材信息
     * @return 广告投放素材集合
     */
	@Override
	public List<AdMaterial> selectAdMaterialList(AdMaterial adMaterial)
	{
	    return adMaterialMapper.selectAdMaterialList(adMaterial);
	}
	
    /**
     * 新增广告投放素材
     * 
     * @param adMaterial 广告投放素材信息
     * @return 结果
     */
	@Override
	public int insertAdMaterial(AdMaterial adMaterial)
	{
	    return adMaterialMapper.insertAdMaterial(adMaterial);
	}
	
	/**
     * 修改广告投放素材
     * 
     * @param adMaterial 广告投放素材信息
     * @return 结果
     */
	@Override
	public int updateAdMaterial(AdMaterial adMaterial)
	{
	    return adMaterialMapper.updateAdMaterial(adMaterial);
	}

	/**
     * 删除广告投放素材对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAdMaterialByIds(String ids)
	{
		return adMaterialMapper.deleteAdMaterialByIds(Convert.toStrArray(ids));
	}

	@Override
	public int getMaxBatch(Integer adScheduleId) {
		return adMaterialMapper.selectMaxBatch(adScheduleId);
	}

	/**
	 *  根据广告ID查询最新批次素材列表
	 */
	@Override
	public List<AdMaterial> selectMaxListByAdSchId(Integer adScheduleId) {
		return adMaterialMapper.selectMaxListByAdSchId(adScheduleId);
	}

	/**
	 * 根据广告ID查询该广告下所有素材列表
	 */
	@Override
	public List<AdMaterial> selectListByAdSchId(Integer adScheduleId) {
		return adMaterialMapper.selectListByAdSchId(adScheduleId);
	}

	@Override
	public String judgeAllType(Integer adScheduleId) throws IOException {
		AdSchedule adschedule = adScheduleService.selectAdScheduleById(adScheduleId);
		if(adschedule != null){
			//获取不同的广告已上传类型
			List<AdMaterial> materialList = adMaterialMapper.getDistinctList(adScheduleId);
			if(materialList.size() > 0){
				//将已有的素材类型作为一个list，待会获取到广告的所有模板判断是否contains，没有则返回没有哪个模板的元素
				List<String> hasTypeList = new ArrayList<>();
				for (AdMaterial adMaterial : materialList) {
					hasTypeList.add(adMaterial.getRemark());
				}
				
				//获取广告模板元素
				List<ElementType> elementList = adScheduleService.getElementList(adschedule.getThemeTemplateId());
				if(elementList.size() > 0){
					for (ElementType elementType : elementList) {
						String elementName = elementType.getElementName();
						if(hasTypeList.contains(elementName)){
							continue;
						}else{
							return elementName;
						}
					}
				}
			}
		}
		//如果全部都有则说明素材上传完整
		return "allHas";
	}
	
}
