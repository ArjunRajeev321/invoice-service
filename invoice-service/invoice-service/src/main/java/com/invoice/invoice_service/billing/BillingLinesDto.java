package com.invoice.invoice_service.billing;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.invoice.invoice_service.billing.billinglineinfo.BillingLineInfoDto;
import com.invoice.invoice_service.billing.priceinfo.PriceInformationDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BillingLinesDto {

	@JsonProperty("billing_line_information")
	private BillingLineInfoDto billingLineInformation;
	
	@JsonProperty("price_information")
	private PriceInformationDto priceInformation;
}
