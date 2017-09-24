package com.file.sharing.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonInclude(value = Include.NON_NULL)
@JsonDeserialize(builder = UserDTO.Builder.class)
public class UserDTO {

	private final String firstName;

	private final String lastName;

	private final String email;

	private final String password;

	public UserDTO(Builder builder) {
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.email = builder.email;
		this.password = builder.password;
	}

	@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "set")
	public static final class Builder {
		private String firstName;

		private String lastName;

		private String email;

		private String password;

		public Builder setFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder setLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder setEmail(String email) {
			this.email = email;
			return this;
		}

		public Builder setPassword(String password) {
			this.password = password;
			return this;
		}

		public UserDTO build() {
			return new UserDTO(this);
		}
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "UserDTO [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}

}
