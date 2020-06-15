package com.zxiang.project.business.place.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.zxiang.common.constant.UserConstants;
import com.zxiang.framework.aspectj.lang.annotation.DataFilter;
import com.zxiang.framework.aspectj.lang.annotation.Log;
import com.zxiang.framework.aspectj.lang.enums.BusinessType;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.domain.AjaxResult;
import com.zxiang.framework.web.page.TableDataInfo;
import com.zxiang.project.business.place.domain.Place;
import com.zxiang.project.business.place.service.IPlaceService;
import com.zxiang.project.client.repair.domain.Repair;
import com.zxiang.project.client.repair.service.IRepairService;
import com.zxiang.project.system.area.domain.Area;
import com.zxiang.project.system.area.service.IAreaService;
import com.zxiang.project.system.user.domain.User;
import com.zxiang.project.system.user.mapper.UserMapper;
import com.zxiang.project.system.user.service.IUserService;

/**
 * 场所管理 信息操作处理
 * 
 * @author ZXiang
 * @date 2018-09-09
 */
@Controller
@RequestMapping("/business/place")
public class PlaceController extends BaseController
{
    private String prefix = "business/place";
	
	@Autowired
	private IPlaceService placeService;
	@Autowired
	private IAreaService areaService;
	@Autowired
	private IUserService userService;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private IRepairService repairService;
	
	@RequiresPermissions("business:place:view")
	@GetMapping()
	public String place()
	{
	    return prefix + "/place";
	}
	
	/**
	 * 查询场所管理列表
	 */
	@DataFilter(placeAlias="place_id")
	@RequiresPermissions("business:place:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Place place)
	{
		startPage();
        List<Place> list = placeService.selectPlaceList(place);
		return getDataTable(list);
	}
	
	/**
	 * 新增场所管理
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap)
	{
		List<User> userListAll = userService.selectUserListByUserType(UserConstants.USER_TYPE_ADVERTISE,UserConstants.USER_TYPE_PARTNER,
				UserConstants.USER_TYPE_AGENT,UserConstants.USER_TYPE_JOIN,UserConstants.USER_TYPE_REPAIR);
		mmap.put("userListAll", userListAll);
		//维修员送纸员根据场所选择了所在城市后进行加载
		mmap.put("tissueLen", "200");
		mmap.put("tissuePrice","0.3");
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存场所管理
	 */
	@RequiresPermissions("business:place:add")
	@Log(title = "场所管理", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Place place)
	{		
		String createor = getUser().getUserName();
		long userId = getUserId();
		
		place.setCreateBy(createor+"("+userId+")");
		place.setCreateTime(new Date());
		return toAjax(placeService.insertPlace(place));
	}

	/**
	 * 修改场所管理
	 */
	@GetMapping("/edit/{placeId}")
	public String edit(@PathVariable("placeId") Integer placeId, ModelMap mmap)
	{
		Place place = placeService.selectPlaceById(placeId);
		if(StringUtils.isEmpty(place.getTissueLen()+"")) {
			place.setTissueLen(200);
		}
		if(StringUtils.isEmpty(place.getTissuePrice()+"")) {
			place.setTissuePrice(0.3f);
		}
		mmap.put("place", place);
		
		mmap.put("placeDropBoxList", placeService.selectDropBoxList());
		
//		mmap.put("provinceDropBoxList", areaService.selectDropBoxList(0L));
//		mmap.put("cityDropBoxList", areaService.selectDropBoxList(place.getProvince()));
//		mmap.put("countyDropBoxList", areaService.selectDropBoxList(place.getCity()));
		
		List<Area> provinceList = areaService.selectDropBoxList(0);
		mmap.put("provinceList", provinceList == null ? new ArrayList<Area>() : provinceList);
		if (place.getProvince() != null) {
			List<Area> cityList = areaService.selectDropBoxList(place.getProvince());
			mmap.put("cityList", cityList);
		}
		if (place.getCity() != null) {
			List<Area> countyList = areaService.selectDropBoxList(place.getCity());
			mmap.put("countyList", countyList == null ? new ArrayList<Area>() : countyList);
		}
		
		
		List<User> userListAll = userService.selectUserListByUserType(UserConstants.USER_TYPE_ADVERTISE,UserConstants.USER_TYPE_PARTNER,
				UserConstants.USER_TYPE_AGENT,UserConstants.USER_TYPE_JOIN,UserConstants.USER_TYPE_REPAIR);
		mmap.put("userListAll", userListAll);
		
		List<Repair> repairList = repairService.selectRepairByCity(place.getCity(),place.getCounty());
		mmap.put("repairList", repairList);
		//List<User> userListRepair = userService.selectUserByCity(place.getCity(),place.getCounty());
		if(place.getServicePoint() != null){
			Map<String, Object> qryParam = new HashMap<>();
			qryParam.put("repairId", place.getServicePoint()+"");
			List<User> userListRepair = userService.selectUserByRepairId(qryParam);
			mmap.put("userListRepair", userListRepair);
		}
		
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存场所管理
	 */
	@RequiresPermissions("business:place:edit")
	@Log(title = "场所管理", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Place place)
	{		
		String updateor = getUser().getUserName();
		long userId = getUserId();
		
		place.setUpdateBy(updateor+"("+userId+")");
		place.setUpdateTime(new Date());
		return toAjax(placeService.updatePlace(place));
	}
	
	/**
	 * 删除场所管理
	 */
	@RequiresPermissions("business:place:remove")
	@Log(title = "场所管理", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(placeService.deletePlaceByIds(ids));
	}
	
	/**
	 * 查询场所下拉框数据列表
	 */
	@RequestMapping("/getDropBoxPlaceList")
    @ResponseBody
    public TableDataInfo getDropBoxPlaceList() {
		List<Place> list  = placeService.selectDropBoxList();
		return getDataTable(list);
    }
	
	/**
     * 校验场所编号
     */
    @PostMapping("/checkPlaceCodeUnique")
    @ResponseBody
    public String checkPlaceCodeUnique(Place place)
    {
        return placeService.checkPlaceCodeUnique(place.getPlaceCode());
    }
    
    /**
	 * 场所详情
	 */
	@GetMapping("/placeDetail/{placeId}")
	public String adDetail(@PathVariable("placeId") Integer placeId, ModelMap mmap)
	{
		Place place = placeService.selectPlaceById(placeId);
		mmap.put("place", place);
		
		User queryUser = new User();
		List<User> userListAll = userMapper.selectUserList(queryUser);//userService.selectUserList(queryUser);
		mmap.put("userListAll", userListAll);
		
		queryUser.setUserType(UserConstants.USER_TYPE_REPAIR);
		List<User> userListRepair = userMapper.selectUserList(queryUser);//userService.selectUserList(queryUser);
		mmap.put("userListRepair", userListRepair);
		
		List<Repair> repairList = repairService.selectRepairByCity(place.getCity(),place.getCounty());
		mmap.put("repairList", repairList);
		
	    return prefix + "/placeDetail";
	}
	
	/**
	 * 导出Excel
	 * 注意数据权限要与查询列表一致
	 */
	@DataFilter(placeAlias="place_id")
	@RequestMapping("/excelExport")
	public void excelExport(@RequestParam HashMap<String, String> params, 
			HttpServletResponse response,HttpServletRequest request){
		try {
			placeService.queryExport(params, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
