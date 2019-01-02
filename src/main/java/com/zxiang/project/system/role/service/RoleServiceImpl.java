package com.zxiang.project.system.role.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxiang.common.constant.UserConstants;
import com.zxiang.common.exception.RRException;
import com.zxiang.common.support.Convert;
import com.zxiang.common.utils.StringUtils;
import com.zxiang.common.utils.security.ShiroUtils;
import com.zxiang.project.system.role.domain.Role;
import com.zxiang.project.system.role.domain.RoleMenu;
import com.zxiang.project.system.role.mapper.RoleMapper;
import com.zxiang.project.system.role.mapper.RoleMenuMapper;
import com.zxiang.project.system.user.domain.User;
import com.zxiang.project.system.user.domain.UserRole;
import com.zxiang.project.system.user.mapper.UserRoleMapper;

/**
 * 角色 业务层处理
 * 
 * @author zxiang
 */
@Service
public class RoleServiceImpl implements IRoleService
{

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 根据条件分页查询角色数据
     * 
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    @Override
    public List<Role> selectRoleList(Role role)
    {
        return roleMapper.selectRoleList(role);
    }

    /**
     * 根据用户ID查询权限
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectRoleKeys(Long userId)
    {
        List<Role> perms = roleMapper.selectRolesByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (Role perm : perms)
        {
            if (StringUtils.isNotNull(perms))
            {
                permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 根据用户ID查询角色
     * 
     * @param userId 用户ID
     * @return 角色列表
     */
    @Override
    public List<Role> selectRolesByUserId(Long userId)
    {
        List<Role> userRoles = roleMapper.selectRolesByUserId(userId);
        List<Role> roles = roleMapper.selectRolesAll();
        for (Role role : roles)
        {
            for (Role userRole : userRoles)
            {
                if (role.getRoleId().longValue() == userRole.getRoleId().longValue())
                {
                    role.setFlag(true);
                    break;
                }
            }
        }
        return roles;
    }

    /**
     * 查询所有角色
     * 
     * @return 角色列表
     */
    @Override
    public List<Role> selectRoleAll()
    {
        return roleMapper.selectRolesAll();
    }

    /**
     * 通过角色ID查询角色
     * 
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    @Override
    public Role selectRoleById(Long roleId)
    {
        return roleMapper.selectRoleById(roleId);
    }

    /**
     * 通过角色ID删除角色
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    @Override
    public boolean deleteRoleById(Long roleId)
    {
        return roleMapper.deleteRoleById(roleId) > 0 ? true : false;
    }

    /**
     * 批量删除角色信息
     * 
     * @param ids 需要删除的数据ID
     * @throws Exception
     */
    @Override
    public int deleteRoleByIds(String ids) throws Exception
    {
        Long[] roleIds = Convert.toLongArray(ids);
        for (Long roleId : roleIds)
        {
            Role role = selectRoleById(roleId);
            if (countUserRoleByRoleId(roleId) > 0)
            {
                throw new Exception(String.format("%1$s已分配,不能删除", role.getRoleName()));
            }
        }
        return roleMapper.deleteRoleByIds(roleIds);
    }

    /**
     * 新增保存角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public int insertRole(Role role)
    {
        role.setCreateBy(ShiroUtils.getLoginName());
        // 新增角色信息
        roleMapper.insertRole(role);
        ShiroUtils.clearCachedAuthorizationInfo();
        return insertRoleMenu(role);
    }

    /**
     * 修改保存角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public int updateRole(Role role)
    {
        role.setUpdateBy(ShiroUtils.getLoginName());
        // 修改角色信息
        roleMapper.updateRole(role);
        // 删除角色与菜单关联
        roleMenuMapper.deleteRoleMenuByRoleId(role.getRoleId());
        ShiroUtils.clearCachedAuthorizationInfo();
        return insertRoleMenu(role);
    }

    /**
     * 新增角色菜单信息
     * 
     * @param role 角色对象
     */
    public int insertRoleMenu(Role role)
    {
        int rows = 1;
        // 新增用户与角色管理
        List<RoleMenu> list = new ArrayList<RoleMenu>();
        for (Long menuId : role.getMenuIds())
        {
            RoleMenu rm = new RoleMenu();
            rm.setRoleId(role.getRoleId());
            rm.setMenuId(menuId);
            list.add(rm);
        }
        if (list.size() > 0)
        {
            rows = roleMenuMapper.batchRoleMenu(list);
        }
        return rows;
    }

    /**
     * 校验角色名称是否唯一
     * 
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public String checkRoleNameUnique(Role role)
    {
        Long roleId = StringUtils.isNull(role.getRoleId()) ? -1L : role.getRoleId();
        Role info = roleMapper.checkRoleNameUnique(role.getRoleName());
        if (StringUtils.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue())
        {
            return UserConstants.ROLE_NAME_NOT_UNIQUE;
        }
        return UserConstants.ROLE_NAME_UNIQUE;
    }
    
    /**
     * 校验角色权限是否唯一
     * 
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public String checkRoleKeyUnique(Role role)
    {
        Long roleId = StringUtils.isNull(role.getRoleId()) ? -1L : role.getRoleId();
        Role info = roleMapper.checkRoleKeyUnique(role.getRoleKey());
        if (StringUtils.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue())
        {
            return UserConstants.ROLE_KEY_NOT_UNIQUE;
        }
        return UserConstants.ROLE_KEY_UNIQUE;
    }

    /**
     * 通过角色ID查询角色使用数量
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    @Override
    public int countUserRoleByRoleId(Long roleId)
    {
        return userRoleMapper.countUserRoleByRoleId(roleId);
    }

	@Override
	public void setDefaultRole(User user, String clientName) {
		Role role = roleMapper.checkRoleNameUnique(clientName);
		if(role == null) {
			throw new RRException(String.format("[%s]未设置默认权限", clientName));
		}
		UserRole userRole = new UserRole();
		userRole.setRoleId(role.getRoleId());
		userRole.setUserId(user.getUserId());
		userRoleMapper.insertUserRole(userRole);
	}

	@Override
	public int deleteRoleByUserId(Long userId) {
		return userRoleMapper.deleteUserRoleByUserId(userId);
	}

	@Override
	public List<Role> selectRoleByUserType(String userType, Long userId,int flag) { // 1、添加  2、修改
		List<Role> roles = null;
		List<Role> userRoles = null;
		if(UserConstants.USER_TYPE_AGENT.equals(userType) || UserConstants.USER_TYPE_JOIN.equals(userType)) { // 代理商
			if(flag == 1) {
				List<String> list = new ArrayList<String>();
				if(UserConstants.USER_TYPE_AGENT.equals(userType)){
					list.add(UserConstants.ROLE_NAME_AGENT_SALESMAN);//
				}else if(UserConstants.USER_TYPE_JOIN.equals(userType)) {
					list.add(UserConstants.ROLE_NAME_JOIN_SALESMAN);
				}
				roles = roleMapper.selectByRoleName(list);
			}else {
				roles = roleMapper.selectRolesByUserId(userId);
			}
			if(roles == null) {
				return null;
			}
			for (Role role : roles) {
				role.setFlag(true);
				role.setStatus("1");
			}
			return roles;
		}else if(UserConstants.USER_TYPE_REPAIR.equals(userType)) { // 服务商
			userRoles = roleMapper.selectRolesByUserId(userId);
			if(userRoles != null && userRoles.size() > 0) {
				for (Role role : userRoles) {
					if (UserConstants.ROLE_NAME_REPAIR.equals(role.getRoleName())) {
						role.setFlag(true);
						role.setStatus("1");
						return userRoles;
					}
				}
			}
			
			List<String> list = new ArrayList<String>();
			list.add(UserConstants.ROLE_NAME_REPAIR_SALESMAN);
			list.add(UserConstants.ROLE_NAME_TISSUESMAN);
			list.add(UserConstants.ROLE_NAME_SERVICEMAN);
			roles = roleMapper.selectByRoleName(list);
			if(roles == null) {
				return null;
			}
			if(flag == 1) {
				for (Role role : roles) {
					if (UserConstants.ROLE_NAME_REPAIR_SALESMAN.equals(role.getRoleName())) {
						role.setFlag(true);
						return roles;
					}
				}
			}else{
				for (Role role : roles) {
					for (Role userRole : userRoles)  {
						if (role.getRoleId().longValue() == userRole.getRoleId().longValue()) {
							role.setFlag(true);
							break;
						}
					}
				}
			}
			return roles;
		}
		return null;
	}

}
