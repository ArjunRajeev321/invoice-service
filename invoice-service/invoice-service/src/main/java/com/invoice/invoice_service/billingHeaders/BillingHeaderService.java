package com.invoice.invoice_service.billingHeaders;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.invoice.invoice_service.billing.BillingLine;
import com.invoice.invoice_service.billing.BillingLinesDto;
import com.invoice.invoice_service.billing.BillingRepo;
import com.invoice.invoice_service.billing.billingLineInfo.BillingLineInfoDto;
import com.invoice.invoice_service.billing.priceInfo.PriceInformationDto;
import com.invoice.invoice_service.common.RequestDto;
import com.invoice.invoice_service.common.ResponseWrapper;
import com.invoice.invoice_service.paymentInfo.PaymentInfoDto;
import com.invoice.invoice_service.paymentInfo.PaymentInfoRepo;
import com.invoice.invoice_service.paymentInfo.PaymentInformation;

import jakarta.transaction.Transactional;

@Service
public class BillingHeaderService {

	@Autowired
	private BillingHeaderRepository repository;

	@Autowired
	private PaymentInfoRepo paymentInfoRepo;

	@Autowired
	private BillingRepo billingRepo;

	@Autowired
	private RestTemplate restTemplate;

	@Transactional
	public ResponseWrapper saveAndProcessData(RequestDto requestDto) {
		ResponseWrapper wrapper = restTemplate.getForObject("http://localhost:8082/verify", ResponseWrapper.class);
		if (requestDto != null && wrapper.getStatusCode() == 200) {
			BillingHeaderDto dto = requestDto.getBillingHeaderDto();
			PaymentInfoDto paymentInfoDto = requestDto.getBillingHeaderDto().getPaymentInformation();

			List<BillingLinesDto> billingLinesDto = requestDto.getBillingLinesDto();

			billingLinesDto.stream().forEach(a -> {

				BillingLine billingLine = new BillingLine();
				BillingLineInfoDto b = a.getBillingLineInformation();
				billingLine.setActionType(b.getActionType());
				billingLine.setLineType(b.getLineType());
				billingLine.setProductType(b.getProductType());
				
				PriceInformationDto price = a.getPriceInformation();
				billingLine.setTotalPrice(price.getTotalPrice().getAmount());
				billingLine.setBasePrice(price.getBasePrice().getAmount());
				billingRepo.save(billingLine);
				
			});

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
