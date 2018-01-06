package com.file.sharing.core.objects.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.file.sharing.core.objects.PageResult;

/**
 * @author Alexandru Mihai
 * @created Dec 24, 2017
 * 
 */
public class PageResultImpl<T> implements PageResult<T> {

	private final List<T> result;

	private final long totalPageCount;

	private final long pageSize;

	private PageResultImpl(List<T> result, long totalPageCount, long pageSize) {
		this.result = Collections.unmodifiableList(new ArrayList<>(result));
		this.totalPageCount = totalPageCount;
		this.pageSize = pageSize;

		checkResult();
		checkToatalPageCount();
		checkPageSize();
	}

	/**
	 * @param result
	 * @param totalPageCount
	 * @param pageSize
	 * @return
	 * 
	 * @throws IllegalArgumentException
	 *             if the passed arguments are not consistent with a page result.
	 */
	public static <T> PageResult<T> of(List<T> result, long totalPageCount, long pageSize) {
		return new PageResultImpl<>(result, totalPageCount, pageSize);
	}

	public static <T> PageResult<T> emptyResult() {
		return new PageResultImpl<>(Collections.emptyList(), 0, 0);
	}

	@Override
	public List<T> getResult() {
		return result;
	}

	@Override
	public long getTotalPageCount() {
		return totalPageCount;
	}

	@Override
	public long getPageSize() {
		return pageSize;
	}

	@Override
	public boolean isEmpty() {
		return result.isEmpty() && totalPageCount == 0 && pageSize == 0;
	}

	private void checkPageSize() {
		if (pageSize < 0) {
			throw new IllegalArgumentException("Page size has to be a positive number.");
		}

		if (pageSize < result.size()) {
			throw new IllegalArgumentException("Page size should be gt or eq with the size of the result");
		}
	}

	private void checkToatalPageCount() {
		if (totalPageCount < 0) {
			throw new IllegalArgumentException("Total page count has to be a positive number.");
		}

		if (totalPageCount == 0 && !result.isEmpty()) {
			throw new IllegalArgumentException(
					"Total page count cannot be 0 when result has records. Should be at least 1");
		}
	}

	private void checkResult() {
		if (result == null) {
			throw new IllegalArgumentException("Result cannot be null.");
		}
	}

	@Override
	public String toString() {
		return "PageResultImpl [result=" + result + ", totalPageCount=" + totalPageCount + ", pageSize=" + pageSize
				+ "]";
	}

}
