package com.zxiang.common.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户常量信息
 * 
 * @author zxiang
 */
public class UserConstants
{

    /** 正常状态 */
    public static final String NORMAL = "0";

    /** 异常状态 */
    public static final String EXCEPTION = "1";

    /** 用户封禁状态 */
    public static final String USER_BLOCKED = "1";

    /** 角色封禁状态 */
    public static final String ROLE_BLOCKED = "1";

    /** 部门正常状态 */
    public static final String DEPT_NORMAL = "0";

    /**
     * 用户名长度限制
     */
    public static final int USERNAME_MIN_LENGTH = 2;
    public static final int USERNAME_MAX_LENGTH = 20;

    /** 登录名称是否唯一的返回结果码 */
    public final static String USER_NAME_UNIQUE = "0";
    public final static String USER_NAME_NOT_UNIQUE = "1";

    /** 手机号码是否唯一的返回结果 */
    public final static String USER_PHONE_UNIQUE = "0";
    public final static String USER_PHONE_NOT_UNIQUE = "1";

    /** e-mail 是否唯一的返回结果 */
    public final static String USER_EMAIL_UNIQUE = "0";
    public final static String USER_EMAIL_NOT_UNIQUE = "1";

    /** 部门名称是否唯一的返回结果码 */
    public final static String DEPT_NAME_UNIQUE = "0";
    public final static String DEPT_NAME_NOT_UNIQUE = "1";

    /** 角色名称是否唯一的返回结果码 */
    public final static String ROLE_NAME_UNIQUE = "0";
    public final static String ROLE_NAME_NOT_UNIQUE = "1";

    /** 岗位名称是否唯一的返回结果码 */
    public final static String POST_NAME_UNIQUE = "0";
    public final static String POST_NAME_NOT_UNIQUE = "1";

    /** 角色权限是否唯一的返回结果码 */
    public final static String ROLE_KEY_UNIQUE = "0";
    public final static String ROLE_KEY_NOT_UNIQUE = "1";

    /** 岗位编码是否唯一的返回结果码 */
    public final static String POST_CODE_UNIQUE = "0";
    public final static String POST_CODE_NOT_UNIQUE = "1";

    /** 菜单名称是否唯一的返回结果码 */
    public final static String MENU_NAME_UNIQUE = "0";
    public final static String MENU_NAME_NOT_UNIQUE = "1";

    /** 字典类型是否唯一的返回结果码 */
    public final static String DICT_TYPE_UNIQUE = "0";
    public final static String DICT_TYPE_NOT_UNIQUE = "1";

    /** 参数键名是否唯一的返回结果码 */
    public final static String CONFIG_KEY_UNIQUE = "0";
    public final static String CONFIG_KEY_NOT_UNIQUE = "1";

    /**
     * 密码长度限制
     */
    public static final int PASSWORD_MIN_LENGTH = 5;
    public static final int PASSWORD_MAX_LENGTH = 20;

    /**
     * 手机号码格式限制
     */
    public static final String MOBILE_PHONE_NUMBER_PATTERN = "^0{0,1}(13[0-9]|15[0-9]|14[0-9]|18[0-9])[0-9]{8}$";

    /**
     * 邮箱格式限制
     */
    public static final String EMAIL_PATTERN = "^((([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+(\\.([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]))))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))@((([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?";

    /** 用户类型：系统用户 * */
    public static final String USER_TYPE_SYS = "00";
    /** 用户类型：加盟商 * */
    public static final String USER_TYPE_JOIN = "02";
    /** 用户类型：代理商 * */
    public static final String USER_TYPE_AGENT = "03";
    /** 用户类型：服务商 * */
    public static final String USER_TYPE_REPAIR = "04";
    /** 用户类型：广告商 * */
    public static final String USER_TYPE_ADVERTISE = "05";
    /** 用户类型：加盟商业务员 * */
    public static final String USER_TYPE_JOIN_SALESMAN = "12";
    /** 用户类型：代理商业务员 * */
    public static final String USER_TYPE_AGENT_SALESMAN = "13";
    /** 用户类型：服务商业务员 * */
    public static final String USER_TYPE_REPAIR_SALESMAN = "14";
    /** 用户类型：广告商业务员 * */
    public static final String USER_TYPE_ADVERTISE_SALESMAN = "15";
    /** 用户类型：服务商补纸员 * */
    public static final String USER_TYPE_TISSUE = "16";
    /** 用户类型：服务商维修员 * */
    public static final String USER_TYPE_SERVICE = "17";
    
    
    public static final String DEPT_NAME = "合作伙伴";
    public static final String ROLE_NAME_AGENT = "代理商";
    public static final String ROLE_NAME_AGENT_SALESMAN = "代理商业务员";
    public static final String ROLE_NAME_JOIN = "加盟商";
    public static final String ROLE_NAME_JOIN_SALESMAN = "加盟商业务员";
    public static final String ROLE_NAME_REPAIR = "服务商";
    public static final String ROLE_NAME_REPAIR_SALESMAN = "服务商业务员";
    public static final String ROLE_NAME_AD = "广告商";
    public static final String ROLE_NAME_AD_SALESMAN = "广告商业务员";
    
    public static Map<String,String> clientMap = new HashMap<String,String>();
    static {
    	clientMap.put(USER_TYPE_JOIN, ROLE_NAME_JOIN);
    	clientMap.put(USER_TYPE_AGENT, ROLE_NAME_AGENT);
    	clientMap.put(USER_TYPE_REPAIR, ROLE_NAME_REPAIR);
    	clientMap.put(USER_TYPE_ADVERTISE, ROLE_NAME_AD);
    	
    	clientMap.put(USER_TYPE_JOIN_SALESMAN, ROLE_NAME_JOIN_SALESMAN);
    	clientMap.put(USER_TYPE_AGENT_SALESMAN, ROLE_NAME_AGENT_SALESMAN);
    	clientMap.put(USER_TYPE_REPAIR_SALESMAN, ROLE_NAME_REPAIR_SALESMAN);
    	clientMap.put(USER_TYPE_ADVERTISE_SALESMAN, ROLE_NAME_AD_SALESMAN);
    	
    }
}
