package com.file.sharing.rest.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * @author Alexandru Mihai
 * @created Jan 7, 2018
 * 
 */
@JsonInclude(value = Include.NON_NULL)
public class PageResultDTO<T> {

	private final List<T> result;

	private final long totalPageCount;

	private final long pageSize;

	private PageResultDTO(Builder<T> builder) {
		this.result = Collections.unmodifiableList(new ArrayList<>(builder.result));
		this.totalPageCount = builder.totalPageCount;
		this.pageSize = builder.pageSize;
	}

	public List<T> getResult() {
		return result;
	}

	public long getTotalPageCount() {
		return totalPageCount;
	}

	public long getPageSize() {
		return pageSize;
	}

	@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "with")
	public static class Builder<T> {

		private List<T> result;

		private long totalPageCount;

		private long pageSize;

		public Builder<T> withResult(List<T> result) {
			this.result = result;
			return this;
		}

		public Builder<T> withTotalPageCount(long totalPageCount) {
			this.totalPageCount = totalPageCount;
			return this;
		}

		public Builder<T> withPageSize(long pageSize) {
			this.pageSize = pageSize;
			return this;
		}

		public PageResultDTO<T> build() {
			return new PageResultDTO<>(this);
		}
	}

}
