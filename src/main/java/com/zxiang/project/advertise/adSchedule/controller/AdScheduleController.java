package com.zxiang.project.advertise.adSchedule.controller;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.zxiang.framework.aspectj.lang.annotation.Log;
import com.zxiang.framework.aspectj.lang.enums.BusinessType;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.domain.AjaxResult;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.project.advertise.adSchedule.domain.AdSchedule;
import com.zxiang.project.advertise.adSchedule.service.IAdScheduleService;

/**
 * 广告投放 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-24
 */
@Controller
@RequestMapping("/advertise/adSchedule")
public class AdScheduleController extends BaseController
{
    private String prefix = "advertise/adSchedule";
	
	@Autowired
	private IAdScheduleService adScheduleService;
	
	@RequiresPermissions("advertise:adSchedule:view")
	@GetMapping()
	public String adSchedule()
	{
	    return prefix + "/adSchedule";
	}
	
	/**
	 * 查询广告投放列表
	 */
	@RequiresPermissions("advertise:adSchedule:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(AdSchedule adSchedule)
	{
		startPage();
        List<AdSchedule> list = adScheduleService.selectAdScheduleList(adSchedule);
		return getDataTable(list);
	}
	
	/**
	 * 新增广告投放
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存广告投放
	 */
	@RequiresPermissions("advertise:adSchedule:add")
	@Log(title = "广告投放", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AdSchedule adSchedule)
	{		
		String operatorUser = getUser().getUserName()+"("+getUserId()+")";	
		adSchedule.setStatus("01");//待预约
		adSchedule.setCreateBy(operatorUser);
		adSchedule.setCreateTime(new Date());
		adSchedule.setIsDel("0");
		
		return toAjax(adScheduleService.insertAdSchedule(adSchedule));
	}

	/**
	 * 修改广告投放
	 */
	@GetMapping("/edit/{adScheduleId}")
	public String edit(@PathVariable("adScheduleId") Integer adScheduleId, ModelMap mmap)
	{
		AdSchedule adSchedule = adScheduleService.selectAdScheduleById(adScheduleId);
		mmap.put("adSchedule", adSchedule);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存广告投放
	 */
	@RequiresPermissions("advertise:adSchedule:edit")
	@Log(title = "广告投放", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(AdSchedule adSchedule)
	{		
		String operatorUser = getUser().getUserName()+"("+getUserId()+")";	
		adSchedule.setUpdateBy(operatorUser);
		adSchedule.setUpdateTime(new Date());
		
		return toAjax(adScheduleService.updateAdSchedule(adSchedule));
	}
	
	/**
	 * 删除广告投放
	 */
	@RequiresPermissions("advertise:adSchedule:remove")
	@Log(title = "广告投放", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(adScheduleService.deleteAdScheduleByIds(ids));
	}
	
	/**
	 * 素材上传
	 */
	@GetMapping("/materialUpload")
	public String materialUpload()
	{
	    return prefix + "/materialUpload";
	}
	
	/**
     * 素材上传保存
     */
	@RequestMapping(value = "/materialUploadSave", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult materialUploadSave(HttpServletRequest request)
    {
    	int saveCount = 0;
    	
    	 List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("fileUpload");
         MultipartFile file = null;
         for (int i = 0; i < files.size(); ++i) {
        	 saveCount++;
             file = files.get(i);
             System.out.println("file:"+file.getOriginalFilename());
             //TODO 保存文件动作
             if (!file.isEmpty()) {
            	 
             } else {
                 System.out.println("文件不能为空!");
             }
         }
        
        return success("成功上传 "+ saveCount +" 份文件!");
    }
	
}
