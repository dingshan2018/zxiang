package com.zxiang.project.client.repair.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxiang.common.constant.UserConstants;
import com.zxiang.common.exception.RRException;
import com.zxiang.common.support.Convert;
import com.zxiang.common.utils.PinyinUtil;
import com.zxiang.common.utils.security.ShiroUtils;
import com.zxiang.framework.shiro.service.PasswordService;
import com.zxiang.project.client.repair.domain.Repair;
import com.zxiang.project.client.repair.domain.RepairArea;
import com.zxiang.project.client.repair.mapper.RepairAreaMapper;
import com.zxiang.project.client.repair.mapper.RepairMapper;
import com.zxiang.project.settle.coefficient.domain.Coefficient;
import com.zxiang.project.settle.coefficient.service.ICoefficientService;
import com.zxiang.project.system.dept.domain.Dept;
import com.zxiang.project.system.dept.mapper.DeptMapper;
import com.zxiang.project.system.role.service.IRoleService;
import com.zxiang.project.system.user.domain.User;
import com.zxiang.project.system.user.mapper.UserMapper;

/**
 * 服务商 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Service
public class RepairServiceImpl implements IRepairService {
	@Autowired
	private RepairMapper repairMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private PasswordService passwordService;
	@Autowired
	private DeptMapper deptMapper;
	@Autowired
	private RepairAreaMapper repairAreaMapper;
	@Autowired
	private IRoleService iroleService;
	
	@Autowired
	private ICoefficientService coefficientService;

	/**
	 * 查询服务商信息
	 * 
	 * @param repairId
	 *            服务商ID
	 * @return 服务商信息
	 */
	@Override
	public Repair selectRepairById(Integer repairId) {
		return repairMapper.selectRepairById(repairId);
	}

	/**
	 * 查询服务商列表
	 * 
	 * @param repair
	 *            服务商信息
	 * @return 服务商集合
	 */
	@Override
	public List<Repair> selectRepairList(Repair repair) {
		return repairMapper.selectRepairList(repair);
	}

	/**
	 * 新增服务商
	 * 
	 * @param repair
	 *            服务商信息
	 * @return 结果
	 */
	@Override
	public int insertRepair(Repair repair) {
		User user = null;
		if (StringUtils.isNotBlank(repair.getManagerPhone())) {
			// 根据管理者新增用户
			user = userMapper.selectUserByPhoneNumber(repair.getManagerPhone());
			if (user != null) {
				throw new RRException(String.format("该手机号[%s]对应的用户已存在", repair.getManagerPhone()));
			}
			user = new User();
			user.randomSalt();
			user.setPhonenumber(repair.getManagerPhone());
			user.setLoginName(repair.getManagerPhone());
			user.setUserName(repair.getManagerName());
			String password = PinyinUtil.getPinYinHeadChar(repair.getManagerName()) + repair.getManagerPhone();
			user.setPassword(passwordService.encryptPassword(user.getLoginName(), password, user.getSalt()));
			user.setCreateBy(ShiroUtils.getLoginName());
			user.setUserType(UserConstants.USER_TYPE_REPAIR);
			Dept dept = new Dept();
			dept.setDeptName(UserConstants.DEPT_NAME);
			List<Dept> depts = deptMapper.selectDeptList(dept);
			if (depts != null && depts.size() > 0) {
				user.setDeptId(depts.get(0).getDeptId());
			}
			userMapper.insertUser(user);
			repair.setManagerId(user.getUserId().intValue());
			// 设置默认角色
			iroleService.setDefaultRole(user, UserConstants.defaultRoleKey.get(UserConstants.USER_TYPE_REPAIR));
		}
		repair.setCreateTime(new Date());
		repair.setCreateBy(ShiroUtils.getLoginName());
		// 设置默认参数
		Coefficient coefficient = coefficientService.selectCoefficientByType("4");
		repair.setAdRate(coefficient.getAdRate());
		repair.setAdCarouselRate(coefficient.getAdCarouselRate());
		repair.setScanRate(coefficient.getScanRate());
		repair.setPromDirectRate(coefficient.getPromDirectRate());
		repair.setPromIndirectRate(coefficient.getPromIndirectRate());
		repair.setPromPaperRate(coefficient.getPromPaperRate());
		repair.setPromotionRate(coefficient.getPromotionRate());
		repair.setSubsidyRate(coefficient.getSubsidyRate());
		int i = repairMapper.insertRepair(repair);
		if (user != null) {
			user.setPuserId(repair.getRepairId());
			userMapper.updateUser(user);
		}
		return i;
	}

	/**
	 * 修改服务商
	 * 
	 * @param repair
	 *            服务商信息
	 * @return 结果
	 */
	@Override
	public int updateRepair(Repair repair) {
		/*
		 * if(StringUtils.isNotBlank(repair.getManagerPhone())) { // 根据管理者新增用户 User user
		 * = userMapper.selectUserByPhoneNumber(repair.getManagerPhone()); if(user ==
		 * null) { user = new User(); user.randomSalt();
		 * user.setPhonenumber(repair.getManagerPhone());
		 * user.setLoginName(repair.getManagerPhone());
		 * user.setUserName(repair.getManagerName());
		 * user.setPassword(passwordService.encryptPassword(user.getLoginName(),
		 * repair.getManagerPhone(), user.getSalt()));
		 * user.setCreateBy(ShiroUtils.getLoginName());
		 * user.setUserType(UserConstants.USER_TYPE_REPAIR);
		 * userMapper.insertUser(user);
		 * repair.setManagerId(user.getUserId().intValue()); } }
		 */
		repair.setUpdateTime(new Date());
		repair.setUpdateBy(ShiroUtils.getLoginName());
		return repairMapper.updateRepair(repair);
	}

	/**
	 * 删除服务商对象
	 * 
	 * @param ids
	 *            需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteRepairByIds(String ids) {
		String[] idList = Convert.toStrArray(ids);
		Repair repair = null;
		User user = null;
		for (String id : idList) {
			repair = repairMapper.selectRepairById(Integer.valueOf(id));
			if (repair != null && StringUtils.isNotBlank(repair.getManagerPhone())) {
				user = userMapper.selectUserByPhoneNumber(repair.getManagerPhone());
				if (user == null) {
					continue;
				}
				userMapper.deleteUserById(user.getUserId());
				iroleService.deleteRoleByUserId(user.getUserId());
			}
		}
		return repairMapper.deleteRepairByIds(idList);
	}

	@Override
	public List<Repair> selectDropBoxList() {
		return repairMapper.selectDropBoxList();
	}

	@Override
	public List<RepairArea> selectrepairAreasById(Integer repairId) {
		return repairAreaMapper.selectRepairAreaList(repairId);
	}

	@Override
	public RepairArea saveRepairArea(RepairArea repairArea) {
		repairAreaMapper.saveRepairArea(repairArea);
		return repairArea;
	}

	@Override
	public void deleteRepairArea(Integer repairAreaId) {
		repairAreaMapper.deleteRepairArea(repairAreaId);
	}

	/**
	 * 根据城市查询服务网点 先根据区县查询，若没有则根据城市查询
	 */
	@Override
	public List<Repair> selectRepairByCity(long city, long countyId) {
		Map<String, Object> qryParam = new HashMap<>();
		qryParam.put("countyId", countyId);
		List<Repair> repairList = repairMapper.selectRepairByCountyOrCity(qryParam);
		if (repairList == null || repairList.size() <= 0) {
			qryParam.clear();
			qryParam.put("city", city);
			repairList = repairMapper.selectRepairByCountyOrCity(qryParam);
		}
		return repairList;
	}

	@Override
	public void batchEditParam(Repair repair) {
		String[] idList = Convert.toStrArray(repair.getIds());
		for (String id : idList) {
			repair.setRepairId(Integer.valueOf(id));
			repairMapper.batchEditParam(repair);
		}
	}

}
