package com.zxiang.project.client.repair.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxiang.common.constant.UserConstants;
import com.zxiang.common.support.Convert;
import com.zxiang.common.utils.security.ShiroUtils;
import com.zxiang.framework.shiro.service.PasswordService;
import com.zxiang.project.client.repair.domain.Repair;
import com.zxiang.project.client.repair.mapper.RepairMapper;
import com.zxiang.project.system.dept.domain.Dept;
import com.zxiang.project.system.dept.mapper.DeptMapper;
import com.zxiang.project.system.user.domain.User;
import com.zxiang.project.system.user.mapper.UserMapper;

/**
 * 服务商 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Service
public class RepairServiceImpl implements IRepairService 
{
	@Autowired
	private RepairMapper repairMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
    private PasswordService passwordService;
	@Autowired
	private DeptMapper deptMapper;

	/**
     * 查询服务商信息
     * 
     * @param repairId 服务商ID
     * @return 服务商信息
     */
    @Override
	public Repair selectRepairById(Integer repairId)
	{
	    return repairMapper.selectRepairById(repairId);
	}
	
	/**
     * 查询服务商列表
     * 
     * @param repair 服务商信息
     * @return 服务商集合
     */
	@Override
	public List<Repair> selectRepairList(Repair repair)
	{
	    return repairMapper.selectRepairList(repair);
	}
	
    /**
     * 新增服务商
     * 
     * @param repair 服务商信息
     * @return 结果
     */
	@Override
	public int insertRepair(Repair repair) {
		if(StringUtils.isNotBlank(repair.getManagerPhone())) {
			// 根据管理者新增用户
			User user = userMapper.selectUserByPhoneNumber(repair.getManagerPhone());
			if(user == null) {
				user = new User();
				user.randomSalt();
				user.setPhonenumber(repair.getManagerPhone());
				user.setLoginName(repair.getManagerPhone());
				user.setUserName(repair.getManagerName());
				user.setPassword(passwordService.encryptPassword(user.getLoginName(), repair.getManagerPhone(), user.getSalt()));
		        user.setCreateBy(ShiroUtils.getLoginName());
		        user.setUserType(UserConstants.USER_TYPE_REPAIR);
		        Dept dept = new Dept();
		        dept.setDeptName(UserConstants.DEPT_NAME);
		        List<Dept> depts = deptMapper.selectDeptList(dept);
		        if(depts != null && depts.size() > 0) {
		        	user.setDeptId(depts.get(0).getDeptId());
		        }
		        userMapper.insertUser(user);
		        repair.setManagerId(user.getUserId().intValue());
			}
		}
		repair.setCreateTime(new Date());
		repair.setCreateBy(ShiroUtils.getLoginName());
	    return repairMapper.insertRepair(repair);
	}
	
	/**
     * 修改服务商
     * 
     * @param repair 服务商信息
     * @return 结果
     */
	@Override
	public int updateRepair(Repair repair) {
		if(StringUtils.isNotBlank(repair.getManagerPhone())) {
			// 根据管理者新增用户
			User user = userMapper.selectUserByPhoneNumber(repair.getManagerPhone());
			if(user == null) {
				user = new User();
				user.randomSalt();
				user.setPhonenumber(repair.getManagerPhone());
				user.setLoginName(repair.getManagerPhone());
				user.setUserName(repair.getManagerName());
				user.setPassword(passwordService.encryptPassword(user.getLoginName(), repair.getManagerPhone(), user.getSalt()));
		        user.setCreateBy(ShiroUtils.getLoginName());
		        user.setUserType(UserConstants.USER_TYPE_REPAIR);
		        userMapper.insertUser(user);
		        repair.setManagerId(user.getUserId().intValue());
			}
		}
		repair.setUpdateTime(new Date());
		repair.setUpdateBy(ShiroUtils.getLoginName());
	    return repairMapper.updateRepair(repair);
	}

	/**
     * 删除服务商对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteRepairByIds(String ids)
	{
		return repairMapper.deleteRepairByIds(Convert.toStrArray(ids));
	}
	
}
