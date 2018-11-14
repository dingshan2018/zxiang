package com.zxiang.framework.shiro.realm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zxiang.common.constant.ShiroConstants;
import com.zxiang.common.exception.user.CaptchaException;
import com.zxiang.common.exception.user.RoleBlockedException;
import com.zxiang.common.exception.user.UserBlockedException;
import com.zxiang.common.exception.user.UserNotExistsException;
import com.zxiang.common.exception.user.UserPasswordNotMatchException;
import com.zxiang.common.exception.user.UserPasswordRetryLimitExceedException;
import com.zxiang.common.utils.security.ShiroUtils;
import com.zxiang.framework.shiro.service.LoginService;
import com.zxiang.framework.shiro.token.UsernamePasswordOauthToken;
import com.zxiang.project.business.place.domain.Place;
import com.zxiang.project.business.place.service.IPlaceService;
import com.zxiang.project.system.dept.domain.Dept;
import com.zxiang.project.system.dept.service.IDeptService;
import com.zxiang.project.system.menu.service.IMenuService;
import com.zxiang.project.system.role.service.IRoleService;
import com.zxiang.project.system.user.domain.User;
import com.zxiang.project.system.user.service.IUserService;

/**
 * 自定义Realm 处理登录 权限
 * 
 * @author zxiang
 */
public class UserRealm extends AuthorizingRealm
{
    private static final Logger log = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private LoginService loginService;
    
    @Autowired
    private IDeptService deptService;
    
    @Autowired
    private IPlaceService placeService;
    
    @Autowired
    private IUserService userService;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0)
    {
        Long userId = ShiroUtils.getUserId();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 角色加入AuthorizationInfo认证对象
        info.setRoles(roleService.selectRoleKeys(userId));
        // 权限加入AuthorizationInfo认证对象
        info.setStringPermissions(menuService.selectPermsByUserId(userId));
        return info;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
    {
    	UsernamePasswordOauthToken upToken = (UsernamePasswordOauthToken) token;
        String username = upToken.getUsername();
        String password = "";
        String openId = upToken.getOpenId();
        if (upToken.getPassword() != null)
        {
            password = new String(upToken.getPassword());
        }

        User user = null;
        try
        {
            user = loginService.login(username, password, openId);
        }
        catch (CaptchaException e)
        {
            throw new AuthenticationException(e.getMessage(), e);
        }
        catch (UserNotExistsException e)
        {
            throw new UnknownAccountException(e.getMessage(), e);
        }
        catch (UserPasswordNotMatchException e)
        {
            throw new IncorrectCredentialsException(e.getMessage(), e);
        }
        catch (UserPasswordRetryLimitExceedException e)
        {
            throw new ExcessiveAttemptsException(e.getMessage(), e);
        }
        catch (UserBlockedException e)
        {
            throw new LockedAccountException(e.getMessage(), e);
        }
        catch (RoleBlockedException e)
        {
            throw new LockedAccountException(e.getMessage(), e);
        }
        catch (Exception e)
        {
            log.info("对用户[" + username + "]进行登录验证..验证未通过{}", e.getMessage());
            throw new AuthenticationException(e.getMessage(), e);
        }
        
        // 把当前用户放入到session中
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession(true);
        
        //计算人员权限以及主体权限
        HashSet<String> personSets = new HashSet<String>();
        HashSet<String> placeSets = new HashSet<String>();
        HashSet<String> deptSets = new HashSet<String>();
        String userType = user.getUserType();
        Integer puserId = user.getPuserId();
        Long deptId = user.getDeptId();
        if(!"00".equals(userType) && puserId>0) {
        	User userParam = new User();
        	userParam.setDelFlag("0");
//        	userParam.setStatus("0");
        	userParam.setPuserId(puserId);
        	List<User> users = userService.selectUserList(userParam);
        	if(users!=null && users.size()>0) {
        		for(User person : users) {
        			personSets.add(person.getUserId()+"");
        		}
        	}
        	
        }
        personSets.add(user.getUserId()+"");
        //计算场所权限
        Place placeParam = new Place();
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("person", ","+personSets.toString()+",");
        List<Place> places = placeService.selectPlaceList(placeParam);
        if(places!=null && places.size()>0) {
        	for(Place p : places) {
        		placeSets.add(p.getPlaceId()+"");
        	}
        }
        
        //计算部门权限
        if(deptId>0) {
        	Dept dept = deptService.selectDeptById(deptId);
        	Dept dp = new Dept();
        	dp.setAncestors(dept.getAncestors()+",");
        	dp.setStatus("0");
        	List<Dept> depts = deptService.selectDeptList(dp);
        	if(depts!=null && depts.size()>0) {
        		for(Dept tp : depts) {
        			deptSets.add(tp.getDeptId()+"");
        		}
        	}
        	deptSets.add(deptId+"");
        }
        session.setAttribute(ShiroConstants.PLACE_DATA_FILTER, placeSets.toString());
        session.setAttribute(ShiroConstants.PERSON_DATA_FILTER, personSets.toString());
        session.setAttribute(ShiroConstants.DEPT_DATA_FILTER, deptSets.toString());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }

    /**
     * 清理缓存权限
     */
    public void clearCachedAuthorizationInfo()
    {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }

}
