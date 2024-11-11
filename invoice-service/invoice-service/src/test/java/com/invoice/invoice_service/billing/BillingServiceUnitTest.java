package com.invoice.invoice_service.billing;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.invoice.invoice_service.common.AbstractJunitData;

@ExtendWith(SpringExtension.class)
class BillingServiceUnitTest extends AbstractJunitData {

	@Mock
	BillingRepo billingRepo;

	@InjectMocks
	BillingService service;

	@Test
	void testSaveBillings() throws JsonMappingException, JsonProcessingException {
		service.saveBillings(getRequestDto());
		assertNotNull(getRequestDto());
		verify(billingRepo).save(any(BillingLine.class));
		verify(billingRepo, times(1)).save(any(BillingLine.class));
	}

}
