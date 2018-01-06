package com.file.sharing.core.search.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import com.file.sharing.core.objects.file.FileCategories;
import com.file.sharing.core.search.ItemSearch;
import com.file.sharing.core.search.ItemSearchOrder;
import com.file.sharing.core.search.OrderValue;
import com.file.sharing.core.search.PageSearch;

/**
 * @author Alexandru Mihai
 * @created Dec 23, 2017
 * 
 */
public class ItemSearchImpl implements ItemSearch {

	private static final Map<ItemSearchOrder, OrderValue> DEFAULT_ORDER_CRITERIA_MAP = getDefaultOrderCriteriaMap();

	private PageSearch pageSearch;

	private Map<ItemSearchOrder, OrderValue> orderCriteriaMap;

	private String itemName;

	private List<FileCategories> categories;

	private String extension;

	private ItemSearchImpl() {
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	/**
	 * @author Alexandru Mihai
	 * @created Dec 23, 2017
	 * 
	 */
	public static class Builder {

		private PageSearch pageSearch;

		private Map<ItemSearchOrder, OrderValue> orderCriteriaMap = new EnumMap<>(DEFAULT_ORDER_CRITERIA_MAP);

		private String itemName;

		private List<FileCategories> categories;

		private String extension;

		public Builder withPageSearch(PageSearch pageSearch) {
			this.pageSearch = Objects.requireNonNull(pageSearch);
			return this;
		}

		public Builder withOrderCriteria(ItemSearchOrder criteria, OrderValue order) {
			orderCriteriaMap.put(Objects.requireNonNull(criteria), Objects.requireNonNull(order));
			return this;
		}

		public Builder withItemName(String itemName) {
			this.itemName = itemName;
			return this;
		}

		public Builder withCategories(List<FileCategories> categories) {
			this.categories = categories;
			return this;
		}

		public Builder withExtension(String extension) {
			this.extension = extension;
			return this;
		}
		
		/**
		 * @return
		 * 
		 * @throws IllegalArgumentException if categories is empty
		 */
		public ItemSearch build() {
			ItemSearchImpl itemSearch = new ItemSearchImpl();

			itemSearch.extension = this.extension;
			itemSearch.itemName = this.itemName;

			if (this.categories != null && this.categories.isEmpty()) {
				throw new IllegalArgumentException("categories cannot be empty");
			}
			//@formatter:off
			itemSearch.categories = Optional.ofNullable(this.categories)
					                        .map(c -> Collections.unmodifiableList(new ArrayList<>(c)))
					                        .orElse(null);
			//@formatter:on

			itemSearch.pageSearch = Objects.requireNonNull(this.pageSearch);
			
			itemSearch.orderCriteriaMap = Collections.unmodifiableMap(this.orderCriteriaMap);
			
			return itemSearch;
		}
	}

	@Override
	public PageSearch getPageSearch() {
		return pageSearch;
	}

	@Override
	public Map<ItemSearchOrder, OrderValue> getOrderCriteriaMap() {
		return orderCriteriaMap;
	}

	@Override
	public Optional<String> getItemName() {
		return Optional.ofNullable(itemName);
	}

	@Override
	public Optional<List<FileCategories>> getCategories() {
		return Optional.ofNullable(categories);
	}

	@Override
	public Optional<String> getExtension() {
		return Optional.ofNullable(extension);
	}

	@Override
	public String toString() {
		return "ItemSearchImpl [pageSearch=" + pageSearch + ", orderCriteriaMap=" + orderCriteriaMap + ", itemName="
				+ itemName + ", categories=" + categories + ", extension=" + extension + "]";
	}

	private static Map<ItemSearchOrder, OrderValue> getDefaultOrderCriteriaMap() {
		Map<ItemSearchOrder, OrderValue> defaultMap = new EnumMap<>(ItemSearchOrder.class);

		defaultMap.put(ItemSearchOrder.BY_ULOAD_DATE, OrderValue.DESC);
		defaultMap.put(ItemSearchOrder.BY_NAME, OrderValue.ASC);

		return defaultMap;
	}


}
