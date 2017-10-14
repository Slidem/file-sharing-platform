package com.file.sharing.core.business;

import java.util.Optional;

import com.file.sharing.core.entity.User;
import com.file.sharing.core.objects.UserInfo;

/**
 * @author Alexandru
 *
 */
public interface UserBusiness {

	public Optional<UserInfo> getUserInfoByEmail(String email);

	public Optional<UserInfo> getUserInfoByUserId(Integer userId);

	public void saveUser(User user);

}
