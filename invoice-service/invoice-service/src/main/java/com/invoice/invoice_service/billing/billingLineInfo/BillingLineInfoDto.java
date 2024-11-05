package com.invoice.invoice_service.billing.billingLineInfo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BillingLineInfoDto {

	@JsonProperty("action_type")
	private String actionType;

	@JsonProperty("product_type")
	private String productType;

	@JsonProperty("line_type")
	private String lineType;

}
