package com.invoice.invoice_service.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractJunitData {

	private final String template = """
						{
			    "billing_header": {
			        "billing_id": "AAPA",
			        "company_id": "485761",
			        "document_type": "INVOICE",
			        "currency_code": "EUR",
			        "payment_information": {
			            "payment_method": "DEBIT CARD",
			            "card_number_type": "4111XXXXXXXX1215"
			        }
			    },
			    "billing_lines": [
			        {
			            "billing_line_information": {
			                "action_type": "ISSUED",
			                "product_type": "CAR",
			                "line_type": "PRODUCT"
			            },
			            "price_information": {
			                "total_price": {
			                    "amount": "12.90",
			                    "currency_code": "EUR"
			                },
			                "base_price": {
			                    "amount": "9.00",
			                    "currency_code": "EUR"
			                }
			            }
			        }
			    ]
			}
			""";

	public RequestDto getRequestDto() throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(this.template, RequestDto.class);
	}

	public ResponseWrapper createResponseWrapper(int statusCode, String response) {
		return new ResponseWrapper(statusCode, response);
	}

}
