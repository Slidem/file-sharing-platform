package com.file.sharing.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Alexandru Mihai
 * @created Nov 18, 2017
 */
@Component
@ConfigurationProperties(prefix = "item.threadpool")
public class ItemExecutorProperties {

	private Integer corePoolSize;

	private Integer maxPoolSize;

	private Long keepAliveTime;

	private Integer maxRequestCapacity;

	public Integer getCorePoolSize() {
		return corePoolSize;
	}

	public void setCorePoolSize(Integer corePoolSize) {
		this.corePoolSize = corePoolSize;
	}

	public Integer getMaxPoolSize() {
		return maxPoolSize;
	}

	public void setMaxPoolSize(Integer maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}

	public Long getKeepAliveTime() {
		return keepAliveTime;
	}

	public void setKeepAliveTime(Long keepAliveTime) {
		this.keepAliveTime = keepAliveTime;
	}

	public Integer getMaxRequestCapacity() {
		return maxRequestCapacity;
	}

	public void setMaxRequestCapacity(Integer maxRequestCapacity) {
		this.maxRequestCapacity = maxRequestCapacity;
	}

}
