package com.invoice.invoice_service.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.invoice_service.billingHeaders.BillingHeaderService;
import com.invoice.invoice_service.common.RequestDto;
import com.invoice.invoice_service.common.ResponseWrapper;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping(value = "/invoice")
public class InvoiceController {

	@Autowired
	private BillingHeaderService headerService;

	@GetMapping
	public String getStringName() {
		return "abc";
	}

	@PostMapping
	@CircuitBreaker(name = "verifyService", fallbackMethod = "sendFallBackResponse")
	public ResponseWrapper save(@RequestBody RequestDto requestDto) {
		ResponseWrapper rw = new ResponseWrapper();
		ResponseWrapper wrapper = headerService.saveAndProcessData(requestDto);
		if (wrapper != null) {
			wrapResponse(rw, wrapper);
		}
		Optional.ofNullable(wrapper).ifPresent(w -> wrapResponse(rw, wrapper));
		return rw;
	}

	private void wrapResponse(ResponseWrapper rw, ResponseWrapper wrapper) {
		rw.setResponse(wrapper.getResponse());
		rw.setStatusCode(wrapper.getStatusCode());
	}

	public ResponseWrapper sendFallBackResponse(Exception e) {
		return new ResponseWrapper(HttpStatus.OK.value(), "DownTime: Under maintainence!");
	}
}
