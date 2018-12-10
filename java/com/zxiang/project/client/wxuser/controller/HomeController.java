package com.zxiang.project.client.wxuser.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.project.client.wxuser.service.IWxUserService;

/**
 * 首页操作处理
 * 
 * @author ZXiang
 * @date 2018-11-03
 */
@Controller
@RequestMapping("/home")
public class HomeController extends BaseController
{
	
	@Autowired
	private IWxUserService wxUserService;
	
	/**
	 * 系统指标
	 */
	@PostMapping("/systemIndicators") 
	@ResponseBody
	public Map<String, Object> systemIndicators() {	
		return wxUserService.systemIndicators();
	}
	/**
	 * 出纸统计
	 */
	@PostMapping("/tissueStatistical") 
	@ResponseBody
	public Map<String, Object> tissueStatistical() {	
		return wxUserService.tissueStatistical();
	}

	
}
