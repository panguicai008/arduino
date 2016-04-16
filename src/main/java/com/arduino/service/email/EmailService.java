package com.arduino.service.email;

public interface EmailService {

	void sendCode(String email, String code) throws Exception;

}
