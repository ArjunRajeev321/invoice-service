package com.invoice.invoice_service.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.invoice.invoice_service.billingHeaders.BillingHeaderDto;
import com.invoice.invoice_service.paymentInfo.PaymentInfoDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestDto {

	@JsonProperty("billing_header")
	private BillingHeaderDto billingHeaderDto;

	@JsonProperty("payment_information")
	private PaymentInfoDto paymentInfoDto;

}
