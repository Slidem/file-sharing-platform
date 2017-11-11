package com.file.sharing.jms.commons.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.file.sharing.jms.commons.JmsCommonsBasePackageScan;

/**
 * @author Alexandru Mihai
 * @created Oct 21, 2017
 */
@Configuration
@ComponentScan(basePackageClasses = { JmsCommonsBasePackageScan.class })
public class JmsCommonsConfiguration {

}
