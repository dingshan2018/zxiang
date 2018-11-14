package com.zxiang.project.client.wxuser.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zxiang.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 微信粉丝表 zx_wx_user
 * 
 * @author ZXiang
 * @date 2018-11-03
 */
public class WxUser extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer wxUserId;
	/** 系统用户ID */
	private Integer userId;
	/** 是否关注 */
	private Integer subscribe;
	/** 微信账号 */
	private String openId;
	/** 微信昵称 */
	private String nickname;
	/** 性别 */
	private String sexDesc;
	/** 性别代号 0:未知,1:男,2:女 */
	private Integer sex;
	/** 语言 */
	private String language;
	/** 城市 */
	private String city;
	/** 省份 */
	private String province;
	/** 国籍 */
	private String country;
	/** 头像 */
	private String headImgUrl;
	/** 关注时间 */
	private Date subscribeTime;
	/** 微信统一账号 */
	private String unionId;
	/** 备注 */
	private String remark;
	/**  */
	private String createBy;
	/**  */
	private Date createTime;
	/**  */
	private String updateBy;
	/**  */
	private Date updateTime;

	public void setWxUserId(Integer wxUserId) 
	{
		this.wxUserId = wxUserId;
	}

	public Integer getWxUserId() 
	{
		return wxUserId;
	}
	public void setUserId(Integer userId) 
	{
		this.userId = userId;
	}

	public Integer getUserId() 
	{
		return userId;
	}
	public void setSubscribe(Integer subscribe) 
	{
		this.subscribe = subscribe;
	}

	public Integer getSubscribe() 
	{
		return subscribe;
	}
	public void setOpenId(String openId) 
	{
		this.openId = openId;
	}

	public String getOpenId() 
	{
		return openId;
	}
	public void setNickname(String nickname) 
	{
		this.nickname = nickname;
	}

	public String getNickname() 
	{
		return nickname;
	}
	public void setSexDesc(String sexDesc) 
	{
		this.sexDesc = sexDesc;
	}

	public String getSexDesc() 
	{
		return sexDesc;
	}
	public void setSex(Integer sex) 
	{
		this.sex = sex;
	}

	public Integer getSex() 
	{
		return sex;
	}
	public void setLanguage(String language) 
	{
		this.language = language;
	}

	public String getLanguage() 
	{
		return language;
	}
	public void setCity(String city) 
	{
		this.city = city;
	}

	public String getCity() 
	{
		return city;
	}
	public void setProvince(String province) 
	{
		this.province = province;
	}

	public String getProvince() 
	{
		return province;
	}
	public void setCountry(String country) 
	{
		this.country = country;
	}

	public String getCountry() 
	{
		return country;
	}
	public void setHeadImgUrl(String headImgUrl) 
	{
		this.headImgUrl = headImgUrl;
	}

	public String getHeadImgUrl() 
	{
		return headImgUrl;
	}
	public void setSubscribeTime(Date subscribeTime) 
	{
		this.subscribeTime = subscribeTime;
	}

	public Date getSubscribeTime() 
	{
		return subscribeTime;
	}
	public void setUnionId(String unionId) 
	{
		this.unionId = unionId;
	}

	public String getUnionId() 
	{
		return unionId;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}
	public void setCreateBy(String createBy) 
	{
		this.createBy = createBy;
	}

	public String getCreateBy() 
	{
		return createBy;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setUpdateBy(String updateBy) 
	{
		this.updateBy = updateBy;
	}

	public String getUpdateBy() 
	{
		return updateBy;
	}
	public void setUpdateTime(Date updateTime) 
	{
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() 
	{
		return updateTime;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("wxUserId", getWxUserId())
            .append("userId", getUserId())
            .append("subscribe", getSubscribe())
            .append("openId", getOpenId())
            .append("nickname", getNickname())
            .append("sexDesc", getSexDesc())
            .append("sex", getSex())
            .append("language", getLanguage())
            .append("city", getCity())
            .append("province", getProvince())
            .append("country", getCountry())
            .append("headImgUrl", getHeadImgUrl())
            .append("subscribeTime", getSubscribeTime())
            .append("unionId", getUnionId())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
