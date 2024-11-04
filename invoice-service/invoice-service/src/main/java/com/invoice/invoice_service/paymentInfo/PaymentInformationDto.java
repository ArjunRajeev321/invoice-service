package com.invoice.invoice_service.paymentInfo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentInformationDto {

	@JsonProperty("payment_method")
	private String paymentMethod;
	
}
