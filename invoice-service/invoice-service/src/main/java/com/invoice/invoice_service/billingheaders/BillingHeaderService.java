package com.invoice.invoice_service.billingheaders;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.invoice.invoice_service.billing.BillingService;
import com.invoice.invoice_service.common.RequestDto;
import com.invoice.invoice_service.common.ResponseWrapper;
import com.invoice.invoice_service.paymentinfo.PaymentInfoDto;
import com.invoice.invoice_service.paymentinfo.PaymentInfoRepo;
import com.invoice.invoice_service.paymentinfo.PaymentInformation;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.transaction.Transactional;

@Service
public class BillingHeaderService {

	private final BillingHeaderRepository repository;

	private final PaymentInfoRepo paymentInfoRepo;

	private final BillingService billingService;

	private final RestTemplate restTemplate;

	public BillingHeaderService(BillingHeaderRepository repository, PaymentInfoRepo paymentInfoRepo,
			BillingService billingService, RestTemplate restTemplate) {
		this.repository = repository;
		this.paymentInfoRepo = paymentInfoRepo;
		this.billingService = billingService;
		this.restTemplate = restTemplate;
	}

	@Transactional
	@CircuitBreaker(name = "verifyService", fallbackMethod = "sendFallBackResponse")
	public ResponseWrapper saveAndProcessData(RequestDto requestDto) {
		ResponseWrapper wrapper = restTemplate.getForObject("http://localhost:8082/verify", ResponseWrapper.class);
		if (requestDto != null && isValidWrapperResponse(wrapper)) {
			BillingHeaderDto dto = requestDto.getBillingHeaderDto();
			PaymentInfoDto paymentInfoDto = requestDto.getBillingHeaderDto().getPaymentInformation();
			billingService.saveBillings(requestDto);
			saveBillingHeaders(dto, paymentInfoDto);
		} else if (wrapper == null) {
			wrapper = wrapMessageNullResponse();
		}
		return wrapper;
	}

	public ResponseWrapper sendFallBackResponse(Exception e) {
		return new ResponseWrapper(HttpStatus.GATEWAY_TIMEOUT.value(), "DownTime: Under maintainence!");
	}

	private static ResponseWrapper wrapMessageNullResponse() {
		ResponseWrapper responseWrapper = new ResponseWrapper();
		responseWrapper.setResponse("Unexpected Failure.");
		responseWrapper.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return responseWrapper;
	}

	private static boolean isValidWrapperResponse(ResponseWrapper wrapper) {
		return wrapper != null && wrapper.getStatusCode() == 200;
	}

	private void saveBillingHeaders(BillingHeaderDto dto, PaymentInfoDto paymentInfoDto) {
		BillingHeader billingHeader = new BillingHeader();
		PaymentInformation information = new PaymentInformation();

		billingHeader.setBillingId(dto.getBillingId());
		billingHeader.setCompanyId(dto.getCompanyId());
		billingHeader.setCurrencyCode(dto.getCurrencyCode());
		billingHeader.setDocumentType(dto.getDocumentType());

		information.setPaymentMethod(paymentInfoDto.getPaymentMethod());
		information.setCardNumberType(paymentInfoDto.getCardNumberType());
		billingHeader.setPaymentId(information);

		repository.save(billingHeader);
		paymentInfoRepo.save(information);
	}

}
