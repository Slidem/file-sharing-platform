package com.file.sharing.jms.commons.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 * @author Alexandru Mihai
 * @created Nov 18, 2017
 */
@Repository
public class TopicsRespository {

	@Value("${topic.user-info:t.user-info}")
	private String userTopic;

	@Value("${topic.item-action:t.item-action}")
	private String itemActionTopic;

	public String getUserTopic() {
		return userTopic;
	}

	public String getItemActionTopic() {
		return itemActionTopic;
	}

}
