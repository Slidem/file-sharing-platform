package com.file.sharing.core.service;

import com.file.sharing.core.entity.User;
import com.file.sharing.core.objects.UserInfo;

public interface UserService {

	public UserInfo getUserInfoByEmail(String email);

	public UserInfo getUserInfoByUserId(Integer userId);

	public void saveUser(User user);

}
