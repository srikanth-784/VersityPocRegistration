package com.registration.request;

import com.registration.model.RegistrationModel;

public class RegistrationRequest {

	RegistrationModel registration;
	String notificationType;

	public RegistrationModel getRegistration() {
		return registration;
	}

	public void setRegistration(RegistrationModel registration) {
		this.registration = registration;
	}

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	@Override
	public String toString() {
		return "RegistrationRequest [registration=" + registration + ", notificationType=" + notificationType + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((notificationType == null) ? 0 : notificationType.hashCode());
		result = prime * result + ((registration == null) ? 0 : registration.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistrationRequest other = (RegistrationRequest) obj;
		if (notificationType == null) {
			if (other.notificationType != null)
				return false;
		} else if (!notificationType.equals(other.notificationType))
			return false;
		if (registration == null) {
			if (other.registration != null)
				return false;
		} else if (!registration.equals(other.registration))
			return false;
		return true;
	}

}
