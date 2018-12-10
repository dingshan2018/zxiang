package com.zxiang.project.business.server.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zxiang.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 接入服务器表 zx_server
 * 
 * @author ZXiang
 * @date 2018-11-20
 */
public class Server extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 接入服务器主键 */
	private Integer serverId;
	/** TCP外网IP */
	private String accessIp;
	/** TCP外网端口 */
	private String accessPort;
	/** TCP内网IP */
	private String localIp;
	/** TCP内网端口 */
	private String localPort;
	/** HTTP外网IP */
	private String httpIp;
	/** HTTP外网端口 */
	private String httpPort;
	/** 当前设备连接数 */
	private Integer connection;
	/**  */
	private String status;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;

	public void setServerId(Integer serverId) 
	{
		this.serverId = serverId;
	}

	public Integer getServerId() 
	{
		return serverId;
	}
	public void setAccessIp(String accessIp) 
	{
		this.accessIp = accessIp;
	}

	public String getAccessIp() 
	{
		return accessIp;
	}
	public void setAccessPort(String accessPort) 
	{
		this.accessPort = accessPort;
	}

	public String getAccessPort() 
	{
		return accessPort;
	}
	public void setLocalIp(String localIp) 
	{
		this.localIp = localIp;
	}

	public String getLocalIp() 
	{
		return localIp;
	}
	public void setLocalPort(String localPort) 
	{
		this.localPort = localPort;
	}

	public String getLocalPort() 
	{
		return localPort;
	}
	public void setHttpPort(String httpPort) 
	{
		this.httpPort = httpPort;
	}

	public String getHttpPort() 
	{
		return httpPort;
	}
	public void setConnection(Integer connection) 
	{
		this.connection = connection;
	}

	public Integer getConnection() 
	{
		return connection;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setUpdateTime(Date updateTime) 
	{
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() 
	{
		return updateTime;
	}

    public String getHttpIp() {
		return httpIp;
	}

	public void setHttpIp(String httpIp) {
		this.httpIp = httpIp;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("serverId", getServerId())
            .append("accessIp", getAccessIp())
            .append("accessPort", getAccessPort())
            .append("localIp", getLocalIp())
            .append("localPort", getLocalPort())
            .append("httpPort", getHttpPort())
            .append("connection", getConnection())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
