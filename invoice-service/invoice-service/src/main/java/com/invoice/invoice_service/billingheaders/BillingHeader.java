package com.invoice.invoice_service.billingheaders;

import java.io.Serializable;

import com.invoice.invoice_service.paymentinfo.PaymentInformation;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "BILLING_HEADER", schema = "test_schema")
@Setter
@Getter
public class BillingHeader implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2355243953029770915L;

	/**
	 * 
	 */
	

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@SequenceGenerator(sequenceName = "BILL_SEQ", allocationSize = 1, name = "seq")
	private Long id;

	@Column(name = "billing_id")
	private String billingId;

	@Column(name = "document_type")
	private String documentType;

	@Column(name = "currency_code")
	private String currencyCode;

	@Column(name = "company_id")
	private String companyId;

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private PaymentInformation paymentId;

}
