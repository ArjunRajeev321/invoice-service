package com.invoice.invoice_service.paymentinfo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentInformationDto {

	@JsonProperty("payment_method")
	private String paymentMethod;
	
}
