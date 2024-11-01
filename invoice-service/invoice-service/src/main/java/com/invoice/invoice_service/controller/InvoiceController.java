package com.invoice.invoice_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.invoice.invoice_service.billingHeader.dto.BillingHeaderDto;
import com.invoice.invoice_service.billingHeader.entity.BillingHeader;
import com.invoice.invoice_service.billingHeader.repository.BillingHeaderRepository;
import com.invoice.invoice_service.common.RequestDto;
import com.invoice.invoice_service.common.ResponseWrapper;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping(value = "/invoice")
public class InvoiceController {

	@Autowired
	private BillingHeaderRepository repository;

	@Autowired
	private RestTemplate restTemplate;

	@PostMapping
	@CircuitBreaker(name = "verifyService", fallbackMethod = "sendFallBackResponse")
	public ResponseWrapper save(@RequestBody RequestDto requestDto) {
		ResponseWrapper rw = new ResponseWrapper();
		ResponseWrapper wrapper = restTemplate.getForObject("http://localhost:8082/verify", ResponseWrapper.class);
		if (requestDto != null && wrapper.getStatusCode() == 200) {

			BillingHeaderDto dto = requestDto.getBillingHeaderDto();
			BillingHeader billingHeader = new BillingHeader();
			billingHeader.setBillingId(dto.getBillingId());
			billingHeader.setCompanyId(dto.getCompanyId());
			billingHeader.setCurrencyCode(dto.getCurrencyCode());
			billingHeader.setDocumentType(dto.getDocumentType());

			repository.save(billingHeader);
			wrapResponse(rw, wrapper);
		} else {
			wrapResponse(rw, wrapper);
		}

		return rw;
	}

	private void wrapResponse(ResponseWrapper x, ResponseWrapper wrapper) {
		x.setResponse(wrapper.getResponse());
		x.setStatusCode(wrapper.getStatusCode());
	}

	public ResponseWrapper sendFallBackResponse(Exception e) {
		return new ResponseWrapper(HttpStatus.OK.value(), "DownTime: Under maintainence!");
	}
}
