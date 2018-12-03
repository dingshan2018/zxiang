package com.zxiang.project.system.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxiang.common.constant.UserConstants;
import com.zxiang.common.exception.RRException;
import com.zxiang.common.support.Convert;
import com.zxiang.common.utils.StringUtils;
import com.zxiang.common.utils.security.ShiroUtils;
import com.zxiang.framework.shiro.service.PasswordService;
import com.zxiang.framework.web.domain.AjaxResult;
import com.zxiang.project.client.advertise.domain.Advertise;
import com.zxiang.project.client.advertise.mapper.AdvertiseMapper;
import com.zxiang.project.client.agent.domain.Agent;
import com.zxiang.project.client.agent.mapper.AgentMapper;
import com.zxiang.project.client.join.domain.Join;
import com.zxiang.project.client.join.mapper.JoinMapper;
import com.zxiang.project.client.repair.domain.Repair;
import com.zxiang.project.client.repair.mapper.RepairMapper;
import com.zxiang.project.client.wxuser.domain.WxUser;
import com.zxiang.project.client.wxuser.mapper.WxUserMapper;
import com.zxiang.project.system.dept.domain.Dept;
import com.zxiang.project.system.dept.mapper.DeptMapper;
import com.zxiang.project.system.post.domain.Post;
import com.zxiang.project.system.post.mapper.PostMapper;
import com.zxiang.project.system.role.domain.Role;
import com.zxiang.project.system.role.mapper.RoleMapper;
import com.zxiang.project.system.role.service.IRoleService;
import com.zxiang.project.system.user.domain.User;
import com.zxiang.project.system.user.domain.UserPost;
import com.zxiang.project.system.user.domain.UserRole;
import com.zxiang.project.system.user.mapper.UserMapper;
import com.zxiang.project.system.user.mapper.UserPostMapper;
import com.zxiang.project.system.user.mapper.UserRoleMapper;

/**
 * 用户 业务层处理
 * 
 * @author zxiang
 */
@Service
public class UserServiceImpl implements IUserService
{
    @Autowired
    private UserMapper userMapper;
    @Autowired
	private DeptMapper deptMapper;
    @Autowired
	private IRoleService iroleService;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private WxUserMapper wxUserMapper;

    @Autowired
    private PostMapper postMapper;
    @Autowired
    private AdvertiseMapper advertiseMapper;
    @Autowired
    private AgentMapper agentMapper;
    @Autowired
    private JoinMapper joinMapper;
    @Autowired
    private RepairMapper repairMapper;

    @Autowired
    private UserPostMapper userPostMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private PasswordService passwordService;

    /**
     * 根据条件分页查询用户对象
     * 
     * @param user 用户信息
     * 
     * @return 用户信息集合信息
     */
    @Override
    public List<User> selectUserList(User user)
    {
        return userMapper.selectUserList(user);
    }

    /**
     * 通过用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public User selectUserByLoginName(String userName)
    {
        return userMapper.selectUserByLoginName(userName);
    }

    /**
     * 通过手机号码查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public User selectUserByPhoneNumber(String phoneNumber)
    {
        return userMapper.selectUserByPhoneNumber(phoneNumber);
    }

    /**
     * 通过邮箱查询用户
     * 
     * @param email 邮箱
     * @return 用户对象信息
     */
    @Override
    public User selectUserByEmail(String email)
    {
        return userMapper.selectUserByEmail(email);
    }

    /**
     * 通过用户ID查询用户
     * 
     * @param userId 用户ID
     * @return 用户对象信息
     */
    @Override
    public User selectUserById(Long userId)
    {
        return userMapper.selectUserById(userId);
    }

    /**
     * 通过用户ID删除用户
     * 
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public int deleteUserById(Long userId)
    {
        // 删除用户与角色关联
        userRoleMapper.deleteUserRoleByUserId(userId);
        // 删除用户与岗位表
        userPostMapper.deleteUserPostByUserId(userId);
        return userMapper.deleteUserById(userId);
    }

    /**
     * 批量删除用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteUserByIds(String ids) throws Exception
    {
        Long[] userIds = Convert.toLongArray(ids);
        for (Long userId : userIds)
        {
            if (User.isAdmin(userId))
            {
                throw new Exception("不允许删除超级管理员用户");
            }
            iroleService.deleteRoleByUserId(userId);
        }
        return userMapper.deleteUserByIds(userIds);
    }

    /**
     * 新增保存用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int insertUser(User user)
    {
        user.randomSalt();
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        user.setCreateBy(ShiroUtils.getLoginName());
        int rows = 0;
        if(UserConstants.clientMap.containsKey(user.getUserType())) { // 非系统用户
        	Dept dept = new Dept();
			dept.setDeptName(UserConstants.DEPT_NAME);
			List<Dept> depts = deptMapper.selectDeptList(dept);
			if(depts != null && depts.size() > 0) {
				user.setDeptId(depts.get(0).getDeptId());
			}
			// 新增用户信息
			rows = userMapper.insertUser(user);
			if(user.getRoleIds() == null || user.getRoleIds().length == 0) {
				// 设置默认角色
				iroleService.setDefaultRole(user, UserConstants.clientMap.get(user.getUserType()));
			}else {
				// 新增用户与角色管理
				insertUserRole(user);
			}
        }else{
        	// 新增用户信息
        	rows = userMapper.insertUser(user);
        	// 新增用户岗位关联
        	insertUserPost(user);
        	// 新增用户与角色管理
        	insertUserRole(user);
        }
        return rows;
    }

    /**
     * 修改保存用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUser(User user)
    {
        Long userId = user.getUserId();
        user.setUpdateBy(ShiroUtils.getLoginName());
        // 删除用户与角色关联
        userRoleMapper.deleteUserRoleByUserId(userId);
        // 新增用户与角色管理
        insertUserRole(user);
        // 删除用户与岗位关联
        userPostMapper.deleteUserPostByUserId(userId);
        // 新增用户与岗位管理
        insertUserPost(user);
        return userMapper.updateUser(user);
    }

    /**
     * 修改用户个人详细信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUserInfo(User user)
    {
        return userMapper.updateUser(user);
    }

    /**
     * 修改用户密码
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int resetUserPwd(User user)
    {
        user.randomSalt();
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        return updateUserInfo(user);
    }

    /**
     * 新增用户角色信息
     * 
     * @param user 用户对象
     */
    public void insertUserRole(User user)
    {
        // 新增用户与角色管理
        List<UserRole> list = new ArrayList<UserRole>();
        for (Long roleId : user.getRoleIds())
        {
            UserRole ur = new UserRole();
            ur.setUserId(user.getUserId());
            ur.setRoleId(roleId);
            list.add(ur);
        }
        if (list.size() > 0)
        {
            userRoleMapper.batchUserRole(list);
        }
    }

    /**
     * 新增用户岗位信息
     * 
     * @param user 用户对象
     */
    public void insertUserPost(User user) {
    	if(user.getPostIds() == null) {
    		return;
    	}
        // 新增用户与岗位管理
        List<UserPost> list = new ArrayList<UserPost>();
        for (Long postId : user.getPostIds())
        {
            UserPost up = new UserPost();
            up.setUserId(user.getUserId());
            up.setPostId(postId);
            list.add(up);
        }
        if (list.size() > 0)
        {
            userPostMapper.batchUserPost(list);
        }
    }

    /**
     * 校验用户名称是否唯一
     * 
     * @param loginName 用户名
     * @return
     */
    @Override
    public String checkLoginNameUnique(String loginName)
    {
        int count = userMapper.checkLoginNameUnique(loginName);
        if (count > 0)
        {
            return UserConstants.USER_NAME_NOT_UNIQUE;
        }
        return UserConstants.USER_NAME_UNIQUE;
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param phonenumber 用户名
     * @return
     */
    @Override
    public String checkPhoneUnique(User user)
    {
        Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        User info = userMapper.checkPhoneUnique(user.getPhonenumber());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue())
        {
            return UserConstants.USER_PHONE_NOT_UNIQUE;
        }
        return UserConstants.USER_PHONE_UNIQUE;
    }

    /**
     * 校验email是否唯一
     *
     * @param email 用户名
     * @return
     */
    @Override
    public String checkEmailUnique(User user)
    {
        Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        User info = userMapper.checkEmailUnique(user.getEmail());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue())
        {
            return UserConstants.USER_EMAIL_NOT_UNIQUE;
        }
        return UserConstants.USER_EMAIL_UNIQUE;
    }

    /**
     * 查询用户所属角色组
     * 
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public String selectUserRoleGroup(Long userId)
    {
        List<Role> list = roleMapper.selectRolesByUserId(userId);
        StringBuffer idsStr = new StringBuffer();
        for (Role role : list)
        {
            idsStr.append(role.getRoleName()).append(",");
        }
        if (StringUtils.isNotEmpty(idsStr.toString()))
        {
            return idsStr.substring(0, idsStr.length() - 1);
        }
        return idsStr.toString();
    }

    /**
     * 查询用户所属岗位组
     * 
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public String selectUserPostGroup(Long userId)
    {
        List<Post> list = postMapper.selectPostsByUserId(userId);
        StringBuffer idsStr = new StringBuffer();
        for (Post post : list)
        {
            idsStr.append(post.getPostName()).append(",");
        }
        if (StringUtils.isNotEmpty(idsStr.toString()))
        {
            return idsStr.substring(0, idsStr.length() - 1);
        }
        return idsStr.toString();
    }

	@Override
	public List<User> selectUserListByUserType(String... userType) {
		return userMapper.selectUserListByUserType(userType);
	}

	@Override
	public List<User> getDropBoxUserList() {
		return userMapper.selectDropBoxList();
	}

	@Override
	public String saleManClent(String userType,Integer cliendId) {
		String client = null;
		if(UserConstants.USER_TYPE_JOIN.equals(userType)) {
			Join join = joinMapper.selectJoinById(cliendId);
			client = join == null ? null : join.getJoinerName();
		}else if(UserConstants.USER_TYPE_AGENT.equals(userType)) {
			Agent agent = agentMapper.selectAgentById(cliendId);
			client = agent == null ? null : agent.getAgentName();
		}else if(UserConstants.USER_TYPE_REPAIR.equals(userType)){
			 Repair repair = repairMapper.selectRepairById(cliendId);
			 client = repair == null ? null : repair.getRepairName();
		}else if(UserConstants.USER_TYPE_ADVERTISE.equals(userType)) {
			Advertise advertise = advertiseMapper.selectAdvertiseById(cliendId);
			client = advertise == null ? null : advertise.getAdvertisorName();
		}
		return client;
	}

	/**
	 *	根据公众号来获取用户
	 */
	@Override
	public User selectUserByOpenId(String openId) {
		return userMapper.selectUserByOpenId(openId);
	}

	/**
	 * 查找加盟商业务员（机主）
	 */
	@Override
	public List<User> selectBuyer() {
		return userMapper.selectBuyer();
	}

	@Override
	public void setRoleName(List<User> list) {
		if(list == null || list.size() == 0) {
			return;
		}
		for (User user : list) {
			List<Role> rolelist = roleMapper.selectRolesByUserId(user.getUserId());
			if(rolelist == null || rolelist.size() == 0) {
				continue;
			}
	        String[] roleName = new String[rolelist.size()];
	        for (int i=0;i<rolelist.size();i++) {
	            roleName[i] = rolelist.get(i).getRoleName();
	        }
	        user.setRoleNames(roleName);
		}
	}

	@Override
	public AjaxResult wxLoginSelectRoles(String openId) {
		WxUser wxUser = wxUserMapper.selectObjectByOpenId(openId);
		if(wxUser == null || wxUser.getUserId() == null) {
			throw new RRException("该用户未绑定");
		}
		AjaxResult json = new AjaxResult();
        json.put("code", 0);
        // 查询头像
        json.put("headImg", wxUser.getHeadImgUrl());
        json.put("name", wxUser.getNickname());
		List<String> roles = wxUserMapper.selectPermsByUserId(wxUser.getUserId());
		if(roles == null || roles.size() == 0) {
			return json;
		}
		json.put("msg", roles);
		return json;
	}
}
