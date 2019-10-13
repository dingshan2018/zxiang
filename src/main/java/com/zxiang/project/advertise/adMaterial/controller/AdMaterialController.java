package com.zxiang.project.advertise.adMaterial.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zxiang.common.utils.StringUtils;
import com.zxiang.framework.aspectj.lang.annotation.Log;
import com.zxiang.framework.aspectj.lang.enums.BusinessType;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.domain.AjaxResult;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.project.advertise.adMaterial.domain.AdMaterial;
import com.zxiang.project.advertise.adMaterial.service.IAdMaterialService;
import com.zxiang.project.advertise.adSchedule.domain.AdSchedule;
import com.zxiang.project.advertise.adSchedule.domain.MaterialResult;

/**
 * 广告投放素材 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-11-08
 */
@Controller
@RequestMapping("/advertise/adMaterial")
public class AdMaterialController extends BaseController
{
    private String prefix = "advertise/adMaterial";
	
	@Autowired
	private IAdMaterialService adMaterialService;
	
	@Autowired
	private IAdMaterialService materialService;
	
	@RequiresPermissions("advertise:adMaterial:view")
	@GetMapping()
	public String adMaterial()
	{
	    return prefix + "/adMaterial2";
	}
	
	/**
	 * 查询广告投放素材列表
	 */
	@RequiresPermissions("advertise:adMaterial:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(AdMaterial adMaterial)
	{
		startPage();
        List<AdMaterial> list = adMaterialService.selectAdMaterialList(adMaterial);
		return getDataTable(list);
	}
	
	/**
	 * 广告投放审核
	 */
	@GetMapping("/audit/{adMaterialId}")
	public String audit(@PathVariable("adMaterialId") Integer adMaterialId, ModelMap mmap)
	{
		AdMaterial admaterial = adMaterialService.selectAdMaterialById(adMaterialId);
		mmap.put("adMaterial", admaterial);
		
	    return prefix + "/audit";
	}
	
	
	/**
     * 获取广告素材信息
     */
    @RequestMapping("/selectInfo/{adMaterialId}")
    @ResponseBody
    public AdMaterial selectInfo(@PathVariable("adMaterialId") Integer adScheduleId) {
    	AdMaterial adMaterial = adMaterialService.selectAdMaterialById(adScheduleId);
        return adMaterial;
    }
	
	@GetMapping("/previewFileNameByAdSchId/{adScheduleId}")
	public String previewFileNameByAdSchId(@PathVariable("adScheduleId") Integer adScheduleId, ModelMap mmap)
	{
		mmap.put("adScheduleId", adScheduleId);
	    //return prefix + "/adMaterial";//生成代码时路径不对
	    return "advertise/adMaterial/adMaterial";
	}
	
	/**
	 * 查询广告投放素材列表
	 */
	@PostMapping("/list2/{adScheduleId}")
	@ResponseBody
	public TableDataInfo list2(@PathVariable("adScheduleId") Integer adScheduleId,AdMaterial adMaterial)
	{
		startPage();
		adMaterial.setAdScheduleId(adScheduleId);
        List<AdMaterial> list = adMaterialService.selectAdMaterialList(adMaterial);
		return getDataTable(list);
	}
	
	/**
	 * 新增广告投放素材
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
     * 素材上传保存
     */
	@RequiresPermissions("advertise:adMaterial:add")
	@Log(title = "广告素材上传保存", businessType = BusinessType.UPDATE)
	@RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult add(HttpServletRequest request)
    {
    	 try {
			//String elementId = request.getParameter("elementId");
			//String elementName = request.getParameter("elementName");//素材类型,保存在ad_material表remark字段用来判断价格
			String operatorUser = getUser().getUserName()+"("+getUserId()+")";	
			
			 List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
			 
			if (!files.isEmpty()) {
				String result = materialService.uploadMaterial(files,operatorUser,null);
				// 调用上传文件的接口
				// 返回结果封装
				if(StringUtils.isBlank(result)) {
					return error("素材服务器上传异常");
				}else {
					
					JSONObject materialObject = JSONObject.parseObject(result);
					
					if ("0".equals(materialObject.getString("code"))) {
						JSONObject dataObject = materialObject.getJSONObject("data");
						if(dataObject!=null) {
							JSONArray materialArray = dataObject.getJSONArray("materials");
							if(materialArray.size()>0) {
								List<MaterialResult> retMaterials = materialArray.toJavaList(MaterialResult.class);
								materialService.batchAddMaterial(retMaterials,operatorUser);
							}else {
								throw new Exception("不存在图片、视频素材上传");
							}
						}
						
						
					
						return success("素材上传成功");
					}else {
						return error("素材上传失败");
					}
				}	
			}
			return error("素材文件不存在");
		} catch (Exception e) {
			e.printStackTrace();
			return error();
		}
    }
    	

	/**
	 * 修改广告投放素材
	 */
	@GetMapping("/edit/{adMaterialId}")
	public String edit(@PathVariable("adMaterialId") Integer adMaterialId, ModelMap mmap)
	{
		AdMaterial adMaterial = adMaterialService.selectAdMaterialById(adMaterialId);
		mmap.put("adMaterial", adMaterial);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存广告投放素材
	 */
	@RequiresPermissions("advertise:adMaterial:edit")
	@Log(title = "广告投放素材", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(AdMaterial adMaterial)
	{		
		return toAjax(adMaterialService.updateAdMaterial(adMaterial));
	}
	
	/**
	 * 删除广告投放素材
	 */
	@RequiresPermissions("advertise:adMaterial:remove")
	@Log(title = "广告投放素材", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(adMaterialService.deleteAdMaterialByIds(ids));
	}
	
	/**
	 * 删除广告投放素材
	 */
	@RequiresPermissions("advertise:adMaterial:remove")
	@Log(title = "广告投放素材", businessType = BusinessType.DELETE)
	@PostMapping( "/removeScheduleMaterial")
	@ResponseBody
	public AjaxResult remove2(AdMaterial adMaterial)
	{		
		return toAjax(adMaterialService.deleteAdMaterial(adMaterial));
	}
	
	/**
     *	查询素材表最大上传批次号
     */
    @PostMapping("/getMaxBatch/{adScheduleId}")
    @ResponseBody
    public int getMaxBatch(@PathVariable("adScheduleId") Integer adScheduleId)
    {
        return adMaterialService.getMaxBatch(adScheduleId);
    }
    
    /**
	 * 根据广告ID查询最新批次素材列表
	 */
	@RequestMapping("/selectMaxListByAdSchId")
    @ResponseBody
    public TableDataInfo selectMaxListByAdSchId(@RequestBody Map<String, Object> params) {
		String adScheduleId = (String) params.get("adScheduleId");
		List<AdMaterial> list = adMaterialService.selectMaxListByAdSchId(Integer.parseInt(adScheduleId));
		return getDataTable(list);
    }
    
	/**
	 * 根据广告ID查询该广告下所有素材列表
	 */
	@RequestMapping("/selectListByAdSchId")
    @ResponseBody
    public TableDataInfo selectListByAdSchId(@RequestBody Map<String, Object> params) {
		String adScheduleId = (String) params.get("adScheduleId");
		List<AdMaterial> list = adMaterialService.selectListByAdSchId(Integer.parseInt(adScheduleId));
		return getDataTable(list);
    }
	
	/**
     *	查询模板元素各种类型是否都有上传
     */
    @PostMapping("/judgeAllType/{adScheduleId}")
    @ResponseBody
    public String judgeAllType(@PathVariable("adScheduleId") Integer adScheduleId)
    {
        try {
			return adMaterialService.judgeAllType(adScheduleId);
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		}
    }
    
    /**
     *	查询模板元素各种类型是否都有上传
     */
    @PostMapping("/judgeAllType2/{adScheduleId}")
    @ResponseBody
    public String judgeAllType2(@PathVariable("adScheduleId") Integer adScheduleId)
    {
//        try {
			return "allHas";
//		} catch (IOException e) {
//			e.printStackTrace();
//			return "error";
//		}
    }
    
    /**
	 * 广告投放审核保存
	 */
	@RequiresPermissions("advertise:admaterial:audit")
	@Log(title = "广告素材审核保存", businessType = BusinessType.UPDATE)
	@PostMapping("/auditSave")
	@ResponseBody
	public AjaxResult auditSave(AdMaterial adMaterial)
	{
		try {
			String operatorUser = getUser().getUserName()+"("+getUserId()+")";	
			return toAjax(adMaterialService.auditSave(adMaterial,operatorUser));
		} catch (Exception e) {
			e.printStackTrace();
			return error();
		}
	}
	/**
	 * 广告投放审核保存
	 */
	@RequiresPermissions("advertise:admaterial:share")
	@Log(title = "广告素材分享", businessType = BusinessType.UPDATE)
	@PostMapping("/share")
	@ResponseBody
	public AjaxResult share(AdMaterial adMaterial)
	{
		try {
			String operatorUser = getUser().getUserName()+"("+getUserId()+")";	
			return toAjax(adMaterialService.share(adMaterial,operatorUser));
		} catch (Exception e) {
			e.printStackTrace();
			return error();
		}
	}
}
