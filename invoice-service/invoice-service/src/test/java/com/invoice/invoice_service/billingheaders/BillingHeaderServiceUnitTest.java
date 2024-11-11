package com.invoice.invoice_service.billingheaders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.invoice.invoice_service.billing.BillingService;
import com.invoice.invoice_service.common.AbstractJunitData;
import com.invoice.invoice_service.common.ResponseWrapper;
import com.invoice.invoice_service.paymentinfo.PaymentInfoRepo;

@ExtendWith(SpringExtension.class)
class BillingHeaderServiceUnitTest extends AbstractJunitData {

	@Mock
	private BillingHeaderRepository repository;

	@Mock
	private PaymentInfoRepo paymentInfoRepo;;

	@Mock
	private BillingService billingService;

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private BillingHeaderService service;

	private final String VERIFY_URL = "http://localhost:8082/verify";

	@Test
	void testSaveAndProcessData() throws JsonMappingException, JsonProcessingException {
		when(restTemplate.getForObject(VERIFY_URL, ResponseWrapper.class))
				.thenReturn(createResponseWrapper(HttpStatus.OK.value(), "SAVED!"));
		assertEquals(HttpStatus.OK.value(), service.saveAndProcessData(getRequestDto()).getStatusCode());
		verify(repository, times(1)).save(any());
		verify(paymentInfoRepo, times(1)).save(any());

		when(restTemplate.getForObject(VERIFY_URL, ResponseWrapper.class)).thenReturn(null);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				service.saveAndProcessData(getRequestDto()).getStatusCode());

		when(restTemplate.getForObject(VERIFY_URL, ResponseWrapper.class))
				.thenReturn(createResponseWrapper(HttpStatus.BAD_REQUEST.value(), "!"));
		assertEquals(HttpStatus.BAD_REQUEST.value(), service.saveAndProcessData(getRequestDto()).getStatusCode());
	}

}
