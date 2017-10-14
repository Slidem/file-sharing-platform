package com.file.sharing.core.business.impl;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.file.sharing.core.business.UserBusiness;
import com.file.sharing.core.dao.UsersDao;
import com.file.sharing.core.entity.User;
import com.file.sharing.core.factory.UserInfoFactory;
import com.file.sharing.core.objects.UserInfo;

@Component
public class UserBusinessImpl implements UserBusiness {

	private final UsersDao usersDao;

	private final UserInfoFactory userInfoFactory;

	@Autowired
	public UserBusinessImpl(UsersDao usersDao, UserInfoFactory userInfoFactory) {
		this.usersDao = usersDao;
		this.userInfoFactory = userInfoFactory;
	}

	@Override
	public Optional<UserInfo> getUserInfoByEmail(String email) {
		return getUserInfo(() -> usersDao.findByEmail(Objects.requireNonNull(email)));
	}

	@Override
	public Optional<UserInfo> getUserInfoByUserId(Integer userId) {
		return getUserInfo(() -> usersDao.find(Objects.requireNonNull(userId)));
	}

	private Optional<UserInfo> getUserInfo(Supplier<Optional<User>> userEntitySupplier) {
		Optional<User> user = userEntitySupplier.get();
		if (user.isPresent()) {
			return Optional.of(userInfoFactory.fromEntity(user.get()));
		}
		return Optional.empty();
	}

	@Override
	public void saveUser(User user) {
		usersDao.save(user);
	}

}
