package com.file.sharing.rest.dto;

import java.util.Set;

/**
 * @author Alexandru Mihai
 * @created Jan 7, 2018
 * 
 */
public class ItemSearchDTO {

	private Integer pageNumber;

	private Integer pageSize;

	private String orderValue;

	private String orderCriteria;

	private String itemName;

	private Set<String> categories;

	private String extension;

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(String orderValue) {
		this.orderValue = orderValue;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Set<String> getCategories() {
		return categories;
	}

	public void setCategories(Set<String> categories) {
		this.categories = categories;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getOrderCriteria() {
		return orderCriteria;
	}

	public void setOrderCriteria(String orderCriteria) {
		this.orderCriteria = orderCriteria;
	}

}
