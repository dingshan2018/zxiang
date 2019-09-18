package com.zxiang.project.advertise.adMaterial.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zxiang.common.support.Convert;
import com.zxiang.project.advertise.adMaterial.domain.AdMaterial;
import com.zxiang.project.advertise.adMaterial.mapper.AdMaterialMapper;
import com.zxiang.project.advertise.adSchedule.domain.AdSchedule;
import com.zxiang.project.advertise.adSchedule.domain.ElementType;
import com.zxiang.project.advertise.adSchedule.domain.MaterialResult;
import com.zxiang.project.advertise.adSchedule.service.IAdScheduleService;
import com.zxiang.project.advertise.utils.HttpclientUtil;
import com.zxiang.project.advertise.utils.SignUtil;
import com.zxiang.project.advertise.utils.constant.AdConstant;
import com.zxiang.project.system.config.domain.Config;
import com.zxiang.project.system.config.mapper.ConfigMapper;

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
	@Autowired
	private ConfigMapper configMapper;
	
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
	
	@Override
	public String uploadMaterial(List<MultipartFile> files,String operator,String materialText) throws Exception {
    	Config config = new Config();
		config.setConfigKey("AD_NEW_URL");
		Config retConfig = configMapper.selectConfig(config);
		if(retConfig==null) {
			throw new Exception("新广告排期地址未生成");
		}
		String rootUrl = retConfig.getConfigValue();
		try {
			String postUrl = rootUrl + AdConstant.AD_URL_MATERIAL;
			Map<String,String> param = new HashMap<String,String>();
			param.put("belong", operator);
			if(StringUtils.isNotBlank(materialText)) {
				param.put("materialText", materialText);
			}
			HashMap<String,String> headerMap = new HashMap<String,String>();
			config.setConfigKey("AD_API_APPID");
			Config cConfig = this.configMapper.selectConfig(config);
			String appId = cConfig.getConfigValue();
			headerMap.put("appid", appId);
			config.setConfigKey("AD_API_SECRECT");
			cConfig = this.configMapper.selectConfig(config);
			String appSecrect = cConfig.getConfigValue();
			String timestamp = new Date().getTime()+"";
			headerMap.put("timestamp", timestamp);
			headerMap.put("nonce", appId+"_"+RandomUtils.nextInt(new Random(), 10000)+"_"+timestamp);
			headerMap.put("sign", SignUtil.createSign(headerMap,appSecrect));
			return HttpclientUtil.upload(postUrl, files, param, headerMap);
		} catch (Exception e) {
			throw e;
		}
    }

	@Override
	public void batchAddMaterial(List<MaterialResult> retMaterials, String operator) {
		int saveNum = 0;
		try {
			// 1.上传素材文件
			if(retMaterials.size()>0) {
				Integer sequence = 0;
				//新附件上传，每次全量更新
				for(MaterialResult retMaterial : retMaterials) {
					AdMaterial adMaterial = new AdMaterial();
					adMaterial.setPreview(retMaterial.getPreviewUrl());
					adMaterial.setBatch(1);
					adMaterial.setSequence(++sequence);
					adMaterial.setCreateBy(operator);
					adMaterial.setCreateTime(new Date());
					adMaterial.setRemark("新增广告素材");
					adMaterial.setFileName(retMaterial.getMaterialName());
					adMaterial.setExtMaterialId(retMaterial.getMaterialId());
					adMaterial.setExtMaterialType(retMaterial.getType());
					
					if("1".equals(retMaterial.getType())) {
						adMaterial.setFileSize(retMaterial.getFileSize());
						adMaterial.setRemark("视频");
					}else if("2".equals(retMaterial.getType())) {
						adMaterial.setFileSize(retMaterial.getFileSize());
						adMaterial.setRemark("图片");
					}else {
						adMaterial.setMaterialText(retMaterial.getMaterialText());
						adMaterial.setRemark("文本");
					}
					adMaterial.setStatus(AdConstant.MaterialAuditStatus.UNAUDIT.getValue());
					adMaterial.setShare(AdConstant.MaterialShareStatus.UNSHARE.getValue());
					adMaterialMapper.insertAdMaterial(adMaterial);
				}
			}

		} catch (Exception e) {
			throw e;
		}
		
	}

	@Override
	public int auditSave(AdMaterial adMaterial, String operatorUser) {
		Integer adMaterialId = adMaterial.getAdMaterialId();
		AdMaterial material = this.adMaterialMapper.selectAdMaterialById(adMaterialId);
		if(material == null) {
			return 0;
		}
		material.setStatus(adMaterial.getStatus());
		material.setAuditBy(operatorUser);
		material.setAuditOpinion(adMaterial.getAuditOpinion());
		material.setAuditTime(new Date());
		material.setUpdateBy(operatorUser);
		material.setUpdateTime(new Date());
		this.adMaterialMapper.updateAdMaterial(material);
		return 1;
	}

	@Override
	public int share(AdMaterial adMaterial, String operatorUser) {
		Integer adMaterialId = adMaterial.getAdMaterialId();
		AdMaterial material = this.adMaterialMapper.selectAdMaterialById(adMaterialId);
		if(material == null) {
			return -1;
		}
		material.setShare(adMaterial.getShare());
		material.setUpdateBy(operatorUser);
		material.setUpdateTime(new Date());
		this.adMaterialMapper.updateAdMaterial(material);
		return 0;
	}
	
}
