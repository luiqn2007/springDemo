package com.example.mybank_xml.service;

public interface TransferFundsService {

    void transferFunds();

    String getWebServiceUrl();

    boolean isActive();

    long getTimeout();

    int getNumberOfRetrialAttempts();

    void setWebServiceUrl(String webServiceUrl);

    void setActive(boolean active);

    void setTimeout(long timeout);

    void setNumberOfRetrialAttempts(int numberOfRetrialAttempts);
}
