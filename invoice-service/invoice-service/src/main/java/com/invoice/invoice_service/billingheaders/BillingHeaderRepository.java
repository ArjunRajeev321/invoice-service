package com.invoice.invoice_service.billingheaders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingHeaderRepository extends JpaRepository<BillingHeader, Long>{

}
