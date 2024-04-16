package com.example.mybank.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.MultiValueMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

public interface FixedDepositController {

    ModelAndView listFixedDeposits();

    ModelAndView showOpenFixedDepositForm();

    ModelAndView openFixedDeposit(Map<String, String> params);

    ModelAndView editDeposit(MultiValueMap<String, String> params);

    String closeFixedDeposit(int fdId);

    ModelAndView viewFixedDepositDetails(HttpServletRequest request);

    String handleException(Exception ex);
}
