package com.file.sharing.rest.factory;

import com.file.sharing.common.dto.UserDTO;
import com.file.sharing.core.objects.UserInfo;

public interface UserDtoFactory {

	UserDTO fromUserInfo(UserInfo userInfo);

}
