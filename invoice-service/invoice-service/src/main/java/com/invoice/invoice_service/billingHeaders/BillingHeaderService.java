package com.invoice.invoice_service.billingHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.invoice.invoice_service.common.RequestDto;
import com.invoice.invoice_service.common.ResponseWrapper;
import com.invoice.invoice_service.paymentInfo.PaymentInfoDto;
import com.invoice.invoice_service.paymentInfo.PaymentInfoRepo;
import com.invoice.invoice_service.paymentInfo.PaymentInformation;

@Service
public class BillingHeaderService {

	@Autowired
	private BillingHeaderRepository repository;

	@Autowired
	private PaymentInfoRepo paymentInfoRepo;

	@Autowired
	private RestTemplate restTemplate;

	public ResponseWrapper saveAndProcessData(RequestDto requestDto) {
		ResponseWrapper wrapper = restTemplate.getForObject("http://localhost:8082/verify", ResponseWrapper.class);
		if (requestDto != null && wrapper.getStatusCode() == 200) {
			BillingHeaderDto dto = requestDto.getBillingHeaderDto();
			PaymentInfoDto paymentInfoDto = requestDto.getBillingHeaderDto().getPaymentInformation();
			BillingHeader billingHeader = new BillingHeader();
			billingHeader.setBillingId(dto.getBillingId());
			billingHeader.setCompanyId(dto.getCompanyId());
			billingHeader.setCurrencyCode(dto.getCurrencyCode());
			billingHeader.setDocumentType(dto.getDocumentType());
			
			PaymentInformation information = new PaymentInformation();
			information.setPaymentMethod(paymentInfoDto.getPaymentMethod());
			information.setCardNumberType(paymentInfoDto.getCardNumberType());
			billingHeader.setPaymentId(information);
			
			

			repository.save(billingHeader);
			paymentInfoRepo.save(information);
		}
		return wrapper;
	}

}
