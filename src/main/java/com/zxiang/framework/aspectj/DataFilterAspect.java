package com.zxiang.framework.aspectj;


import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.zxiang.common.constant.ShiroConstants;
import com.zxiang.common.constant.UserConstants;
import com.zxiang.common.exception.RRException;
import com.zxiang.common.utils.security.ShiroUtils;
import com.zxiang.framework.aspectj.lang.annotation.DataFilter;
import com.zxiang.framework.web.domain.BaseEntity;
import com.zxiang.project.system.user.domain.User;

/**
 * 数据过滤，切面处理类
 *
 * @author ZHUYUN
 * @email 939961241@qq.com
 * @date 2017年10月23日 下午13:33:35
 */
@Aspect
@Component
public class DataFilterAspect {
//    @Autowired
//    private SysRoleDeptService sysRoleDeptService;
	private static Logger logger = LoggerFactory.getLogger(DataFilterAspect.class);
    @Pointcut("@annotation(com.zxiang.framework.aspectj.lang.annotation.DataFilter)")
    public void dataFilterCut() {

    }

    @Before("dataFilterCut()")
    public void dataFilter(JoinPoint point) throws Throwable {
        //获取参数
        Object params = point.getArgs()[0];
        if (params != null && (params instanceof BaseEntity || params instanceof Map)) {
            User user = ShiroUtils.getUser();
            //系统用户不做数据权限过滤
            if(!UserConstants.USER_TYPE_SYS.equals(user.getUserType())) {
            	String filterSql = getFilterSQL(user, point);
            	logger.info("数据权限："+filterSql);
            	if(params instanceof Map) {
            		Map paramMap = (Map)params;
            		paramMap.put("filterSql", filterSql);
            	}else if(params instanceof BaseEntity) {
            		BaseEntity base = (BaseEntity) params;
                	base.setFilterSql(filterSql);
            	}
            	
            }
            return;
        }

        throw new RRException("数据权限接口的参数必须集成BaseEntity，且不能为NULL");
    }

    /**
     * 获取数据过滤的SQL
     */
    private String getFilterSQL(User user, JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        DataFilter dataFilter = signature.getMethod().getAnnotation(DataFilter.class);

        String userAlias = dataFilter.userAlias();
        String deptAlias = dataFilter.deptAlias();
        String personAlias = dataFilter.personAlias();
        String placeAlias = dataFilter.placeAlias();

        
        //获取用户会话权限
    	String placeSets = (String)ShiroUtils.getSession().getAttribute(ShiroConstants.PLACE_DATA_FILTER);
    	String deptSets = (String)ShiroUtils.getSession().getAttribute(ShiroConstants.DEPT_DATA_FILTER);
    	String personSets = (String)ShiroUtils.getSession().getAttribute(ShiroConstants.PERSON_DATA_FILTER);

        
        StringBuilder filterSql = new StringBuilder();
        filterSql.append(" and ( ");
        if (StringUtils.isNotEmpty(deptAlias) || StringUtils.isNotBlank(userAlias) || StringUtils.isNotEmpty(placeAlias) || StringUtils.isNotEmpty(personAlias)){
        //    filterSql.append(" 1 = 1 ");
            if (StringUtils.isNotEmpty(deptAlias)) {
                //取出登录用户部门权限
                String alias = deptSets;
            //    filterSql.append(" and ");
                filterSql.append(deptAlias);
                filterSql.append(" in ");
                filterSql.append(" ( ");
                filterSql.append(alias);
                filterSql.append(" ) ");
                if (StringUtils.isNotBlank(userAlias) || StringUtils.isNotBlank(placeAlias) || StringUtils.isNotBlank(personAlias)) {
                    if (dataFilter.self()) {
                        filterSql.append(" or ");

                    } else {
                        filterSql.append(" and ");
                    }
                }
            }
            if (StringUtils.isNotBlank(placeAlias)) {
            	String alias = placeSets;
                //    filterSql.append(" and ");
                filterSql.append(placeAlias);
                filterSql.append(" in ");
                filterSql.append(" ( ");
                filterSql.append(alias);
                filterSql.append(" ) ");
            	if (StringUtils.isNotBlank(userAlias) || StringUtils.isNotBlank(personAlias)) {
                    if (dataFilter.self()) {
                        filterSql.append(" or ");

                    } else {
                        filterSql.append(" and ");
                    }
                }
            }
            if (StringUtils.isNotBlank(personAlias)) {
            	String alias = personSets;
                //    filterSql.append(" and ");
                filterSql.append(personAlias);
                filterSql.append(" in ");
                filterSql.append(" ( ");
                filterSql.append(alias);
                filterSql.append(" ) ");
            	if (StringUtils.isNotBlank(userAlias)) {
                    if (dataFilter.self()) {
                        filterSql.append(" or ");

                    } else {
                        filterSql.append(" and ");
                    }
                }
            }
            if (StringUtils.isNotBlank(userAlias)) {
                //没有部门数据权限，也能查询本人数据
                filterSql.append(userAlias);
                filterSql.append(" = ");
                filterSql.append(user.getUserId());
                filterSql.append(" ");
            }
            
        }  else {
            return "";
        }
        filterSql.append(" ) ");
        
        return filterSql.toString();
    }

    /**
     * 取出用户权限
     *
     * @param userId 登录用户Id
     * @return 权限
     */
    private String getAliasByUser(Long userId) {
//        @SuppressWarnings("unchecked")
//        List<Long> roleOrglist = sysRoleDeptService.queryDeptIdListByUserId(userId);
//        StringBuilder roleStr = new StringBuilder();
//        String alias = "";
//        if (roleOrglist != null && !roleOrglist.isEmpty()) {
//            for (Long roleId : roleOrglist) {
//                roleStr.append(",");
//                roleStr.append("'");
//                roleStr.append(roleId);
//                roleStr.append("'");
//            }
//            alias = roleStr.toString().substring(1, roleStr.length());
//        }
//        return alias;
        return "";
    }
}
