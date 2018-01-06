package com.file.sharing.core.objects;

import java.util.List;

/**
 * 
 * @author Alexandru Mihai
 * @created Dec 24, 2017
 * 
 */
public interface PageResult<T> {

	/**
	 * @return the results for a given page
	 */
	List<T> getResult();

	/**
	 * @return the total number of pages for the given result, based on the page size of a result.
	 */
	long getTotalPageCount();

	/**
	 * @return number of records of one page.
	 */
	long getPageSize();

	/**
	 * @return true if the page result contains no records, false otherwise
	 */
	boolean isEmpty();

}
