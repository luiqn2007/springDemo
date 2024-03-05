package com.example.mybank;

import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;

@ManagedResource(objectName = "fixed_deposit_service:name=FixedDepositService")
public class FixedDepositService {

    private boolean active;

    @Autowired
    private transient JmsTemplate jmsTemplate;

    @Cacheable("fixedDeposit")
    public FixedDepositDetails getFixedDepositDetails() {
        return null;
    }

    @Transactional
    @Secured({"SAVINGS_ACCOUNT_CUSTOMER", "APPLICATION_ADMIN"})
    public boolean createFixedDeposit(FixedDepositDetails depositDetails) {
        // todo do something
        return false;
    }

    @ManagedOperation
    public void activateService() {
        active = true;
    }

    @ManagedOperation
    public void deactiveService() {
        active = false;
    }

    public void save() {}

    public boolean submitRequest(Request request) {
        jmsTemplate.convertAndSend(request);
        return false;
    }
}
