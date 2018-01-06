package com.file.sharing.core.search.impl;

import com.file.sharing.core.search.PageSearch;

/**
 * @author Alexandru Mihai
 * @created Dec 23, 2017
 * 
 */
public class PageSearchImpl implements PageSearch {

	private int pageNumber;

	private int pageSize;

	private PageSearchImpl(int pageNumber, int pageSize) {
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}

	public static PageSearchImpl of(int pageNumber, int pageSize) {
		return new PageSearchImpl(pageNumber, pageSize);
	}

	@Override
	public int getPageNumber() {
		return pageNumber;
	}

	@Override
	public int getPageSize() {
		return pageSize;
	}

	@Override
	public String toString() {
		return "PageSearchImpl [pageNumber=" + pageNumber + ", pageSize=" + pageSize + "]";
	}

}
