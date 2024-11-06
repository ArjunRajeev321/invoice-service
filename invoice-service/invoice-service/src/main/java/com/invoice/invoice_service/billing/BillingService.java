package com.invoice.invoice_service.billing;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoice.invoice_service.billing.billingLineInfo.BillingLineInfoDto;
import com.invoice.invoice_service.billing.priceInfo.PriceInformationDto;
import com.invoice.invoice_service.common.RequestDto;

@Service
public class BillingService {

	@Autowired
	private BillingRepo billingRepo;

	public void saveBillings(RequestDto requestDto) {
		List<BillingLinesDto> billingLinesDto = requestDto.getBillingLinesDto();
		billingLinesDto.stream().map(a -> {
			return buildBillingLine(a);
		}).forEach(billingRepo::save);
	}

	private BillingLine buildBillingLine(BillingLinesDto a) {
		BillingLine billingLine = new BillingLine();
		BillingLineInfoDto b = a.getBillingLineInformation();
		billingLine.setActionType(b.getActionType());
		billingLine.setLineType(b.getLineType());
		billingLine.setProductType(b.getProductType());

		PriceInformationDto price = a.getPriceInformation();
		billingLine.setTotalPrice(price.getTotalPrice().getAmount());
		billingLine.setBasePrice(price.getBasePrice().getAmount());

		return billingLine;
	}
}
