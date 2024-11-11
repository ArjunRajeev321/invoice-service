package com.invoice.invoice_service.common;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.invoice.invoice_service.billing.BillingLinesDto;
import com.invoice.invoice_service.billingheaders.BillingHeaderDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestDto {

	@JsonProperty("billing_header")
	private BillingHeaderDto billingHeaderDto;
	
	@JsonProperty("billing_lines")
	private List<BillingLinesDto> billingLinesDto;

}
