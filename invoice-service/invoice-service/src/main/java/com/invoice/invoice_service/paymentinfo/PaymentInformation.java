package com.invoice.invoice_service.paymentinfo;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "PAYMENT_INFORMATION", schema = "test_schema")
public class PaymentInformation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3619541789804393181L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq2")
	@SequenceGenerator(sequenceName = "BILL_SEQ2", allocationSize = 1, name = "seq2")
	private Long id;
	
	@Column(name = "payment_method")
	private String paymentMethod;
	
	@Column(name = "card_number_type")
	private String cardNumberType;

}
