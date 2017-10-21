package com.file.sharing.jms.commons.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class TopicsRespository {

	@Value("${topic.user-info:t.user-info}")
	private String userTopic;

	public String getUserTopic() {
		return userTopic;
	}

}
