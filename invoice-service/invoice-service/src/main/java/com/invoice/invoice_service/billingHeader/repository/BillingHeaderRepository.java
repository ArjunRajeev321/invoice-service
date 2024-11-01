package com.invoice.invoice_service.billingHeader.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.invoice.invoice_service.billingHeader.entity.BillingHeader;

@Repository
public interface BillingHeaderRepository extends JpaRepository<BillingHeader, Long>{

}
