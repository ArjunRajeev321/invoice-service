package com.invoice.invoice_service.billingHeader.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BillingHeaderDto {
	
	@JsonProperty("billing_id")
	private String billingId;
	
	@JsonProperty("document_type")
	private String documentType;
	
	@JsonProperty("currency_code")
	private String currencyCode;
	
	@JsonProperty("company_id")
	private String companyId;
	
//	@JsonProperty("payment_information")
//	private PaymentInformationDto paymentInformation;
	

}
