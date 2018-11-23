package com.zxiang.project.system.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxiang.common.exception.RRException;
import com.zxiang.common.utils.ServletUtils;
import com.zxiang.common.utils.StringUtils;
import com.zxiang.framework.shiro.token.UsernamePasswordOauthToken;
import com.zxiang.framework.web.controller.BaseController;
import com.zxiang.framework.web.domain.AjaxResult;
import com.zxiang.project.system.user.service.IUserService;

/**
 * 登录验证
 * 
 * @author zxiang
 */
@Controller
public class LoginController extends BaseController {
	
	@Autowired
    private IUserService userService;

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response)
    {
        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtils.isAjaxRequest(request))
        {
            return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }

        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public AjaxResult ajaxLogin(String username, String password, Boolean rememberMe)
    {
    	UsernamePasswordOauthToken token = new UsernamePasswordOauthToken(username, password, rememberMe,null);
    	Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);
            return success();
        }
        catch (AuthenticationException e)
        {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage()))
            {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }
    
    @PostMapping("/wxLogin")
    @ResponseBody
    public AjaxResult ajaxLogin(String openId){
    	UsernamePasswordOauthToken token = new UsernamePasswordOauthToken(null, null, null,openId);
    	Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            List<String> list =  userService.wxLoginSelectRoles(openId);
            AjaxResult json = new AjaxResult();
            json.put("msg", list);
            json.put("code", 0);
            return json;
        } catch (AuthenticationException e) {
            String msg = "该微信用户未绑定";
            if (StringUtils.isNotEmpty(e.getMessage()))  {
                msg = e.getMessage();
            }
            return error(msg);
        } catch (RRException e) {
	    	return error(e.getMessage());
	    }
    }


	@GetMapping("/unauth")
    public String unauth()
    {
        return "/error/unauth";
    }
}
