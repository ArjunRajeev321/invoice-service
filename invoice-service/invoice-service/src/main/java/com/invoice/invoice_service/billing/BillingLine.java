package com.invoice.invoice_service.billing;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "BILLING_LINE", schema = "test_schema")
public class BillingLine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6213571615369030745L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq4")
	@SequenceGenerator(sequenceName = "billLineSeq", allocationSize = 1, name = "seq4")
	private Long id;

	@Column(name = "action_type")
	private String actionType;

	@Column(name = "product_type")
	private String productType;

	@Column(name = "line_type")
	private String lineType;

	@Column(name = "total_price")
	private BigDecimal totalPrice;

	@Column(name = "base_price")
	private BigDecimal basePrice;

}
