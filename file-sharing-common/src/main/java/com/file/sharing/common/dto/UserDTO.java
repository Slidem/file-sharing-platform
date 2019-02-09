package com.file.sharing.common.dto;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.file.sharing.common.security.Roles;
import com.file.sharing.common.user.AccountStatus;
import com.file.sharing.common.user.UserAccountType;

/**
 * 
 * @author Alexandru
 *
 */
@JsonInclude(Include.NON_NULL)
public class UserDTO {

	private Integer id;

	private String name;

	private String surname;

	private String email;

	private String password;

	private byte[] picture;

	private UserAccountType type;

	private AccountStatus status;

	private Roles role;

	// ---------------------- GETTERS --------------------------

	public Integer getId() {
		return id;
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

	public UserAccountType getType() {
		return type;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public Roles getRole() {
		return role;
	}

	public String getPassword() {
		return password;
	}

	// ------------------------------- BUILDER ---------------------

	/**
	 * @author Alexandru
	 *
	 */
	public static class Builder {

		private UserDTO userDto;

		/**
		 * @param userDto
		 */
		public Builder() {
			this.userDto = new UserDTO();
		}

		public Builder withName(String name) {
			this.userDto.name = Objects.requireNonNull(name);
			return this;
		}

		public Builder withSurname(String surname) {
			this.userDto.surname = Objects.requireNonNull(surname);
			return this;
		}

		public Builder withEmail(String email) {
			this.userDto.email = Objects.requireNonNull(email);
			return this;
		}

		public Builder withPicture(byte[] picture) {
			this.userDto.picture = picture;
			return this;
		}

		public Builder withPassword(String password) {
			this.userDto.password = Objects.requireNonNull(password);
			return this;
		}

		public Builder withType(UserAccountType type) {
			this.userDto.type = type;
			return this;
		}

		public Builder withStatus(AccountStatus status) {
			this.userDto.status = status;
			return this;
		}

		public Builder withRole(Roles role) {
			this.userDto.role = role;
			return this;
		}

		public UserDTO build() {
			return this.userDto;
		}

	}

}
