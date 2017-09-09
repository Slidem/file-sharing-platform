package com.file.sharing.business;

import org.springframework.security.crypto.password.PasswordEncoder;

public interface FileSharingPassEncoder {

	PasswordEncoder getPassowrdEncoder();

}
