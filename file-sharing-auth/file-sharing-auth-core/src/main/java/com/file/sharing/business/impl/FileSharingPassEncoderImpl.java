package com.file.sharing.business.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.file.sharing.business.FileSharingPassEncoder;

@Component
public class FileSharingPassEncoderImpl implements FileSharingPassEncoder {

	@Override
	public PasswordEncoder getPassowrdEncoder() {
		return new BCryptPasswordEncoder();
	}

}
