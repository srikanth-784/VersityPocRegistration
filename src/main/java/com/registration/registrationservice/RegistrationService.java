package com.registration.registrationservice;

import org.springframework.http.ResponseEntity;

import com.registration.request.RegistrationRequest;

public interface RegistrationService {

	public ResponseEntity<Object> saveRegistration(RegistrationRequest registrationRequest);

}
