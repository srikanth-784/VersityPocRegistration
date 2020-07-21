package com.registration.registrationserviceImpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.registration.controller.RegistrationController;
import com.registration.exception.InvalidRegistrationDetails;
import com.registration.model.RegistrationModel;
import com.registration.registrationservice.FeignService;
import com.registration.registrationservice.RegistrationService;
import com.registration.repository.RegistrationRepository;
import com.registration.request.RegistrationRequest;
import com.registration.response.ErrorResponse;
import com.registration.response.SuccessResponse;

import feign.Response;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	Logger logger = Logger.getLogger(RegistrationController.class);
	@Autowired
	private RegistrationRepository registrationRepository;

	@Autowired
	Environment environment;

	@Autowired
	private FeignService service;

	ErrorResponse error = new ErrorResponse();
	
	@Override
	public ResponseEntity<Object> saveRegistration(RegistrationRequest registrationRequest) {

		try {
			String val = "" + ((int) (Math.random() * 9000) + 1000);

			RegistrationModel registration = registrationRequest.getRegistration();
			registration.setOtp(val);
			if (registrationRequest.getRegistration().getEmailId() != null
					&& registrationRequest.getRegistration().getPhoneNumber() != null
					&& registrationRequest.getNotificationType().equalsIgnoreCase("save")) {
				registrationRepository.save(registration);

				String emailId = registrationRequest.getRegistration().getEmailId();
				String otp = registrationRequest.getRegistration().getOtp();

				String mailRequest = "{\"mail\":{\"toMail\":\"" + emailId + " \",\"otp\":\"" + otp
						+ "\"},\"notificationType\":\"mail\"}";

				Response obj = service.getEmails(mailRequest);

				obj.close();
			} else {
				throw new InvalidRegistrationDetails("Mail or Number or NotificationType should not be null");
			}

			SuccessResponse successResponse = new SuccessResponse();
			successResponse.setMessage("OTP send successFully");
			successResponse.setStatusCode("200");
			return new ResponseEntity<>(successResponse, HttpStatus.OK);
			
		} catch (InvalidRegistrationDetails e) {

			logger.error("Exception caught : " + e.getMessage());
			error.setStatusCode("409");
			error.setMessage("Exception caught!");
			error.setStatusMessage(e.getMessage());
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}

	}

}
