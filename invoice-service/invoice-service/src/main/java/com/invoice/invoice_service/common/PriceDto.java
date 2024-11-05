package com.invoice.invoice_service.common;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriceDto {

	@JsonProperty("amount")
	private BigDecimal amount;
	
	@JsonProperty("currency_code")
	private String currencyCode;
}
