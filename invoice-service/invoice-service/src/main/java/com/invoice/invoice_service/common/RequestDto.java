package com.invoice.invoice_service.common;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.invoice.invoice_service.billing.BillingLinesDto;
import com.invoice.invoice_service.billingHeaders.BillingHeaderDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestDto {

	@JsonProperty("billing_header")
	private BillingHeaderDto billingHeaderDto;

//	@JsonProperty("payment_information")
//	private PaymentInfoDto paymentInfoDto;
	
	@JsonProperty("billing_lines")
	private List<BillingLinesDto> billingLinesDto;

}
