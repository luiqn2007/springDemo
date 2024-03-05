package com.example.mybank;

public class FixedDepositContainer {

    // 被依赖对象
    private FixedDepositService service;


    // 依赖通过构造函数注入
    public FixedDepositContainer(FixedDepositService service) {
        this.service = service;
    }

    public boolean submit() {
        service.save();
        return true;
    }
}
