package com.zxiang.project.client.shopper.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxiang.common.constant.UserConstants;
import com.zxiang.common.exception.RRException;
import com.zxiang.common.support.Convert;
import com.zxiang.common.utils.PinyinUtil;
import com.zxiang.common.utils.security.ShiroUtils;
import com.zxiang.framework.shiro.service.PasswordService;
import com.zxiang.project.client.join.domain.Join;
import com.zxiang.project.client.join.mapper.JoinMapper;
import com.zxiang.project.client.shopper.domain.Shopper;
import com.zxiang.project.client.shopper.mapper.ShopperMapper;
import com.zxiang.project.settle.coefficient.domain.Coefficient;
import com.zxiang.project.settle.coefficient.service.ICoefficientService;
import com.zxiang.project.system.dept.domain.Dept;
import com.zxiang.project.system.dept.mapper.DeptMapper;
import com.zxiang.project.system.role.service.IRoleService;
import com.zxiang.project.system.user.domain.User;
import com.zxiang.project.system.user.mapper.UserMapper;

/**
 * 机主 服务层实现
 * 
 * @author ZXiang
 * @date 2018-09-11
 */
@Service
public class ShopperServiceImpl implements IShopperService {
	@Autowired
	private ShopperMapper joinMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private PasswordService passwordService;
	@Autowired
	private DeptMapper deptMapper;
	@Autowired
	private IRoleService iroleService;
	
	@Autowired
	private ICoefficientService coefficientService;

	/**
	 * 查询机主信息
	 * 
	 * @param joinId
	 *            机主ID
	 * @return 机主信息
	 */
	@Override
	public Shopper selectShopperById(Integer joinId) {
		return joinMapper.selectShopperById(joinId);
	}

	/**
	 * 查询机主列表
	 * 
	 * @param join
	 *            机主信息
	 * @return 机主集合
	 */
	@Override
	public List<Shopper> selectShopperList(Shopper join) {
		return joinMapper.selectShopperList(join);
	}

	/**
	 * 新增机主
	 * 
	 * @param join
	 *            机主信息
	 * @return 结果
	 */
	@Override
	public int insertShopper(Shopper join) {
		User user = null;
		if (StringUtils.isNotBlank(join.getManagerPhone())) {
			// 根据管理者新增用户
			user = userMapper.selectUserByPhoneNumber(join.getManagerPhone());
			if (user != null) {
				throw new RRException(String.format("该手机号[%s]对应的用户已存在", join.getManagerPhone()));
			}
			user = new User();
			user.randomSalt();
			user.setPhonenumber(join.getManagerPhone());
			user.setLoginName(join.getManagerPhone());
			user.setUserName(join.getManagerName());
			String password = PinyinUtil.getPinYinHeadChar(join.getManagerName()) + join.getManagerPhone();
			user.setPassword(passwordService.encryptPassword(user.getLoginName(), password, user.getSalt()));
			user.setCreateBy(ShiroUtils.getLoginName());
			user.setUserType(UserConstants.USER_TYPE_SHOPPER);

			Dept dept = new Dept();
			dept.setDeptName(UserConstants.DEPT_NAME);
			List<Dept> depts = deptMapper.selectDeptList(dept);
			if (depts != null && depts.size() > 0) {
				user.setDeptId(depts.get(0).getDeptId());
			}
			userMapper.insertUser(user);
			join.setManagerId(user.getUserId().intValue());
			// 设置默认角色
			iroleService.setDefaultRole(user, UserConstants.defaultRoleKey.get(UserConstants.USER_TYPE_SHOPPER));
		}
		join.setCreateTime(new Date());
		join.setCreateBy(ShiroUtils.getLoginName());
		// 设置默认参数
		Coefficient coefficient = coefficientService.selectCoefficientByType("5");
		join.setAdRate(coefficient.getAdRate());
		join.setAdCarouselRate(coefficient.getAdCarouselRate());
		join.setScanRate(coefficient.getScanRate());
		join.setPromDirectRate(coefficient.getPromDirectRate());
		join.setPromIndirectRate(coefficient.getPromIndirectRate());
		join.setPromPaperRate(coefficient.getPromPaperRate());
		join.setPromotionRate(coefficient.getPromotionRate());
		join.setServeRate(coefficient.getServeRate());
		int i = joinMapper.insertShopper(join);
		if (user != null) {
			user.setPuserId(join.getShopperId());
			userMapper.updateUser(user);
		}
		return i;
	}

	/**
	 * 修改机主
	 * 
	 * @param join
	 *            机主信息
	 * @return 结果
	 */
	@Override
	public int updateShopper(Shopper join) {
		/*
		 * if(StringUtils.isNotBlank(join.getManagerPhone())) { // 根据管理者新增用户 User user =
		 * userMapper.selectUserByPhoneNumber(join.getManagerPhone()); if(user == null)
		 * { user = new User(); user.randomSalt();
		 * user.setPhonenumber(join.getManagerPhone());
		 * user.setLoginName(join.getManagerPhone());
		 * user.setUserName(join.getManagerName());
		 * user.setPassword(passwordService.encryptPassword(user.getLoginName(),
		 * join.getManagerPhone(), user.getSalt()));
		 * user.setCreateBy(ShiroUtils.getLoginName());
		 * user.setUserType(UserConstants.USER_TYPE_JOIN); userMapper.insertUser(user);
		 * join.setJoinerId(user.getUserId().intValue()); } }
		 */
		join.setUpdateTime(new Date());
		join.setUpdateBy(ShiroUtils.getLoginName());
		return joinMapper.updateShopper(join);
	}

	/**
	 * 删除机主对象
	 * 
	 * @param ids
	 *            需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteShopperByIds(String ids) {
		String[] idList = Convert.toStrArray(ids);
		Shopper join = null;
		User user = null;
		for (String id : idList) {
			join = joinMapper.selectShopperById(Integer.valueOf(id));
			if (join != null && StringUtils.isNotBlank(join.getManagerPhone())) {
				user = userMapper.selectUserByPhoneNumber(join.getManagerPhone());
				if (user == null) {
					continue;
				}
				userMapper.deleteUserById(user.getUserId());
				iroleService.deleteRoleByUserId(user.getUserId());
			}
		}
		return joinMapper.deleteShopperByIds(idList);
	}

	@Override
	public List<Shopper> selectDropBoxList() {
		return joinMapper.selectDropBoxList();
	}

	@Override
	public void batchEditParam(Shopper join) {
		String[] idList = Convert.toStrArray(join.getIds());
		for (String id : idList) {
			join.setShopperId(Integer.valueOf(id));
			joinMapper.batchEditParam(join);
		}
	}

}