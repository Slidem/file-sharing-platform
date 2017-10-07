package com.file.sharing.core.objects;

import java.util.Objects;

import com.file.sharing.common.security.Roles;

public class UserInfo {

	private final Integer userId;

	private final String name;

	private final String surname;

	private final String email;

	private final byte[] picture;

	private final AccStatsInfo accStatsInfo;

	private final Roles role;

	private UserInfo(Builder builder) {
		this.userId = Objects.requireNonNull(builder.userId);
		this.name = Objects.requireNonNull(builder.name);
		this.surname = Objects.requireNonNull(builder.surname);
		this.email = Objects.requireNonNull(builder.email);
		this.picture = builder.picture;
		this.accStatsInfo = Objects.requireNonNull(builder.accStatsInfo);
		this.role = Objects.requireNonNull(builder.role);
	}

	public static final class Builder {

		private Integer userId;

		private String name;

		private String surname;

		private String email;

		private byte[] picture;

		private AccStatsInfo accStatsInfo;

		private Roles role;

		public Builder setUserId(Integer userId) {
			this.userId = userId;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setSurname(String surname) {
			this.surname = surname;
			return this;
		}

		public Builder setEmail(String email) {
			this.email = email;
			return this;
		}

		public Builder setPicture(byte[] picture) {
			this.picture = picture;
			return this;
		}

		public Builder setAccStatsInfo(AccStatsInfo accStatsInfo) {
			this.accStatsInfo = accStatsInfo;
			return this;
		}

		public Builder setRole(Roles role) {
			this.role = role;
			return this;
		}

		public UserInfo build() {
			return new UserInfo(this);
		}

	}

	public Integer getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getEmail() {
		return email;
	}

	public byte[] getPicture() {
		return picture;
	}

	public AccStatsInfo getAccStatsInfo() {
		return accStatsInfo;
	}

	public Roles getRole() {
		return role;
	}

}
