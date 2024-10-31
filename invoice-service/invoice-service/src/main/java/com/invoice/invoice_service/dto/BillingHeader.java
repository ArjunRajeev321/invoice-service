package com.invoice.invoice_service.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BillingHeader {

	private String billingId;
	private String documentType;
	private String currencyCode;
	private String companyId;
	private PaymentInformation paymentInformation;

}
