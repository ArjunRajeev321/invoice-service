package com.invoice.invoice_service.billingHeader.entity;

import java.io.Serializable;
import java.util.Objects;

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
@Table(name = "BILLING_HEADER", schema = "test_schema")
@Setter
@Getter
public class BillingHeader implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq")
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

	@Override
	public int hashCode() {
		return Objects.hash(billingId, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BillingHeader other = (BillingHeader) obj;
		return Objects.equals(billingId, other.billingId) && Objects.equals(id, other.id);
	}

}
