package com.zxiang.project.system.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zxiang.framework.web.domain.AjaxResult;
import com.zxiang.project.system.user.domain.User;

/**
 * 用户 业务层
 * 
 * @author zxiang
 */
public interface IUserService
{

    /**
     * 根据条件分页查询用户对象
     * 
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    public List<User> selectUserList(User user);

    /**
     * 通过用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
    public User selectUserByLoginName(String userName);

    /**
     * 通过手机号码查询用户
     * 
     * @param phoneNumber 手机号码
     * @return 用户对象信息
     */
    public User selectUserByPhoneNumber(String phoneNumber);

    /**
     * 通过邮箱查询用户
     * 
     * @param email 邮箱
     * @return 用户对象信息
     */
    public User selectUserByEmail(String email);

    /**
     * 通过用户ID查询用户
     * 
     * @param userId 用户ID
     * @return 用户对象信息
     */
    public User selectUserById(Long userId);

    /**
     * 通过用户ID删除用户
     * 
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteUserById(Long userId);

    /**
     * 批量删除用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     * @throws Exception 异常
     */
    public int deleteUserByIds(String ids) throws Exception;

    /**
     * 保存用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int insertUser(User user);

    /**
     * 保存用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int updateUser(User user);

    /**
     * 修改用户详细信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int updateUserInfo(User user);

    /**
     * 修改用户密码信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int resetUserPwd(User user);

    /**
     * 校验用户名称是否唯一
     * 
     * @param loginName 登录名称
     * @return 结果
     */
    public String checkLoginNameUnique(String loginName);

    /**
     * 校验手机号码是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    public String checkPhoneUnique(User user);

    /**
     * 校验email是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    public String checkEmailUnique(User user);

    /**
     * 根据用户ID查询用户所属角色组
     * 
     * @param userId 用户ID
     * @return 结果
     */
    public String selectUserRoleGroup(Long userId);

    /**
     * 根据用户ID查询用户所属岗位组
     * 
     * @param userId 用户ID
     * @return 结果
     */
    public String selectUserPostGroup(Long userId);
    /**
     * 根据用户类型查询用户对象
     * 
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    public List<User> selectUserListByUserType(String... str);

    /**
   	 * 查询用户下拉框数据列表
   	 */
	public List<User> getDropBoxUserList(User user);
	
	/**
	 * 查询业务员的主体名称
	 * @param user
	 * @return
	 */
	public String saleManClent(String userType,Integer cliendId);

	/**
	 * 根据公众号来获取用户信息
	 * @param openId
	 * @return
	 */
	public User selectUserByOpenId(String openId);

	/**
	 * 查找机主业务员（机主）
	 * @return
	 */
	public List<User> selectBuyer();

	public void setRoleName(List<User> list);
	/**
	 * 微信登录获取角色信息
	 * @param openId
	 * @return
	 */
	public AjaxResult wxLoginSelectRoles(String openId);

	/**
	 * 根据区县查询服务商员工信息,区县没有数据时根据城市来查
	 * @param parseLong
	 * @return
	 */
	public List<User> selectUserByCity(long city,long countyId);
	
	public List<HashMap<String, Object>> selectJoinPlace(String userId);
	
    public List<HashMap<String, Object>> selectzxagent(String agentId);
	
	public List<HashMap<String, Object>> selectzxrepairarea(String repairId);

	/**
	 * 根据服务商ID获取服务商员工
	 * @param params
	 * @return
	 */
	public List<User> selectUserByRepairId(Map<String, Object> params);
	
}
