package com.invoice.invoice_service.billing.priceInfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.invoice.invoice_service.common.PriceDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PriceInformationDto {

	@JsonProperty("total_price")
	private PriceDto totalPrice;

	@JsonProperty("base_price")
	private PriceDto basePrice;

}
