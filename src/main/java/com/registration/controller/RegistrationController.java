package com.registration.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.registration.exception.InvalidRegistrationDetails;
import com.registration.registrationservice.RegistrationService;
import com.registration.request.RegistrationRequest;
import com.registration.response.ErrorResponse;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
	
	@Autowired
	private RegistrationService registrationService;
	
	Logger logger = Logger.getLogger(RegistrationController.class);
	
	ErrorResponse error = new ErrorResponse();
	
	@PostMapping("/saveRegistration")
	public ResponseEntity<Object> saveRegistration(@RequestBody RegistrationRequest registrationRequest) {
		logger.debug("Incoming request : " + registrationRequest);
		try {

			if (registrationRequest == null || registrationRequest.getNotificationType() == null
					|| registrationRequest.getNotificationType().isEmpty()) {
				logger.error("Invalid request");

				throw new InvalidRegistrationDetails("Register details should not be empty or null");
			}

			return registrationService.saveRegistration(registrationRequest);

		} catch (InvalidRegistrationDetails e) {

			logger.error("Exception caught : " + e.getMessage());
			error.setStatusCode("409");
			error.setMessage("Exception caught!");
			error.setStatusMessage(e.getMessage());
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}

	}

}
