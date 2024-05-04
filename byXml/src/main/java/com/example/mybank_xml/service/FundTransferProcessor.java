package com.example.mybank_xml.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FundTransferProcessor {

    private FundTransferService sameBankImmediateFundTransferService;

    private FundTransferService diffBankImmediateFundTransferService;

    private FundTransferService sameBankNormalFundTransferService;

    private FundTransferService diffBankNormalFundTransferService;
}
