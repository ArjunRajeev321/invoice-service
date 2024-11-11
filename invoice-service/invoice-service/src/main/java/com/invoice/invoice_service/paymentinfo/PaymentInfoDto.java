package com.invoice.invoice_service.paymentinfo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentInfoDto {
	
	@JsonProperty("payment_method")
	private String paymentMethod;
	
	@JsonProperty("card_number_type")
	private String cardNumberType;

}
