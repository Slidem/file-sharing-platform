package com.file.sharing.core.objects;

/**
 * @author Alexandru Mihai
 * @created Nov 18, 2017
 */
public final class Constants {

	public static final String ITEMS_EXECUTOR = "itemThreadPoolTaskExecutor";
	
	public static final String ITEM_ACTION_TOPIC_DEST = "itemActionTopicDest";

	public static final String STORAGE_INFO_CACHE_NAME = "storageInfo";

	public static final String USER_INFO_CACHE_NAME = "userInfo";

	public static final String DUPLICATE_SUFFIX_STRING = "Copy";

    public static final String DUPLICATE_SUFFIX_STRING_PATTERN = " (" + DUPLICATE_SUFFIX_STRING + " %d)";

    private Constants() {
	}
	
}
