package com.file.sharing.core.config;

import static com.file.sharing.core.objects.Constants.ITEMS_EXECUTOR;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Alexandru Mihai
 * @created Nov 18, 2017
 */
@Configuration
@EnableAsync
public class AsyncConfig {


	/**
	 * @param properties
	 * @return
	 */
	@Bean(name = ITEMS_EXECUTOR)
	public Executor itemThreadPoolTaskExecutor(ItemExecutorProperties properties) {
		return new ThreadPoolExecutor(
				properties.getCorePoolSize(),
				properties.getMaxPoolSize(), 
				properties.getKeepAliveTime(), TimeUnit.SECONDS,
				new ArrayBlockingQueue<>(properties.getMaxRequestCapacity()),
				new ThreadPoolExecutor.CallerRunsPolicy());
	}

}
