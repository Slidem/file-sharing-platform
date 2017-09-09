package com.file.sharing.entities.impl;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {

	// ----------------------------------------------
	private static final long serialVersionUID = 1L;
	// ----------------------------------------------

	private final String userName;

	private final String passowrd;

	private final boolean accountNonExpired;

	private final boolean accountNonLocked;

	private final boolean credentialsNonExpired;

	private final boolean enabled;

	private final Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Builder builder) {
		this.userName = builder.userName;
		this.passowrd = builder.passowrd;
		this.accountNonExpired = builder.accountNonExpired;
		this.accountNonLocked = builder.accountNonLocked;
		this.credentialsNonExpired = builder.credentialsNonExpired;
		this.enabled = builder.enabled;
		this.authorities = builder.authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return passowrd;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public static final class Builder {

		private String userName;

		private String passowrd;

		private boolean accountNonExpired;

		private boolean accountNonLocked;

		private boolean credentialsNonExpired;

		private boolean enabled;

		private Collection<? extends GrantedAuthority> authorities;

		public Builder setUserName(String userName) {
			this.userName = userName;
			return this;
		}

		public Builder setPassowrd(String passowrd) {
			this.passowrd = passowrd;
			return this;
		}

		public Builder setAccountNonExpired(boolean accountNonExpired) {
			this.accountNonExpired = accountNonExpired;
			return this;
		}

		public Builder setAccountNonLocked(boolean accountNonLocked) {
			this.accountNonLocked = accountNonLocked;
			return this;
		}

		public Builder setCredentialsNonExpired(boolean credentialsNonExpired) {
			this.credentialsNonExpired = credentialsNonExpired;
			return this;
		}

		public Builder setEnabled(boolean enabled) {
			this.enabled = enabled;
			return this;
		}

		public Builder setAuthorities(Collection<? extends GrantedAuthority> authorities) {
			this.authorities = authorities;
			return this;
		}

		public UserDetails build() {
			return new UserDetailsImpl(this);
		}

	}
}
