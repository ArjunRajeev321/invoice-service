package com.invoice.invoice_service.controller;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.invoice_service.billingheaders.BillingHeaderService;
import com.invoice.invoice_service.common.RequestDto;
import com.invoice.invoice_service.common.ResponseWrapper;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@RestController
@RequestMapping(value = "/invoice")
public class InvoiceController {

	private final BillingHeaderService headerService;

	public InvoiceController(BillingHeaderService headerService) {
		this.headerService = headerService;
	}

	@GetMapping
	@TimeLimiter(name = "rateLimiter", fallbackMethod = "timeoutFallbackResponse")
	public CompletableFuture<String> callExternalService() throws InterruptedException {
		return headerService.callExternalService();

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

	public ResponseWrapper sendFallBackResponse() {
		return new ResponseWrapper(HttpStatus.GATEWAY_TIMEOUT.value(), "DownTime: Under maintainence!");
	}

	public CompletableFuture<String> timeoutFallbackResponse() {
		return CompletableFuture.completedFuture("Sorry Timeout occurred!");
	}
}
