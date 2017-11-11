package com.file.sharing.rest.context;

/**
 * @author Alexandru Mihai
 * @created Oct 29, 2017
 */
public interface ContextConfigurer {

	/**
	 * @param builder
	 */
	void configure(ContextImpl.ContextBuidler builder);

}
