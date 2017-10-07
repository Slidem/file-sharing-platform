package com.file.sharing.core.factory;

import com.file.sharing.core.entity.User;
import com.file.sharing.core.objects.UserInfo;

public interface UserInfoFactory {

	public UserInfo fromEntity(User user);

}
