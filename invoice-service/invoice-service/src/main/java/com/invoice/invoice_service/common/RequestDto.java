package com.invoice.invoice_service.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.invoice.invoice_service.billingHeader.dto.BillingHeaderDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestDto {
	
	@JsonProperty("billing_header")
	private BillingHeaderDto billingHeaderDto;

}
