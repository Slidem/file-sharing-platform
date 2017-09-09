package com.file.sharing.entities.impl;

import java.util.Objects;

import com.file.sharing.objects.Role;

public class BaseUser {

	private final Integer id;

	private final String email;

	private final String password;

	private final Role role;

	protected BaseUser(AbstractBaseClientBuilder<?, ?> builder) {
		this.id = builder.id;
		this.email = Objects.requireNonNull(builder.email);
		this.password = Objects.requireNonNull(builder.password);
		this.role = Objects.requireNonNull(builder.role);
	}

	public Integer getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public Role getRole() {
		return role;
	}

	// >>>>>>>>>>>>>>>>>> BASE CLIENT BUILDER <<<<<<<<<<<<<<<<<<<<

	public static class BaseClientBuilder extends AbstractBaseClientBuilder<BaseUser, BaseClientBuilder> {

		@Override
		public BaseUser build() {
			return new BaseUser(getThis());
		}

	}

	// >>>>>>>>>>>>>>>>>>>>> ABSTRACT BUILDER <<<<<<<<<<<<<<<<<<<

	protected abstract static class AbstractBaseClientBuilder<T extends BaseUser, B extends AbstractBaseClientBuilder<?, ?>> {

		private Integer id;

		private String email;

		private String password;

		private Role role;

		public B setId(Integer id) {
			this.id = id;
			return getThis();
		}

		public B setEmail(String email) {
			this.email = email;
			return getThis();
		}

		public B setPassword(String password) {
			this.password = password;
			return getThis();
		}

		public B setRole(Role role) {
			this.role = role;
			return getThis();
		}

		@SuppressWarnings("unchecked")
		protected B getThis() {
			return (B) this;
		}

		public abstract T build();
	}

}
