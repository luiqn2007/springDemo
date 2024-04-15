package com.example.mybank.controller;

import com.example.mybank.service.FixedDepositService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Profile("!mongodb")
@Controller("fixedDeposits")
@RequestMapping("/fixedDeposit")
public class FixedDepositControllerImpl implements FixedDepositController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Resource(name = "fixedDepositService")
    private FixedDepositService fixedDepositService;

    @Override
    public ModelAndView listFixedDeposits() {
        return null;
    }

    @Override
    public ModelAndView showOpenFixedDepositForm() {
        return null;
    }

    @Override
    public ModelAndView openFixedDeposit(Map<String, String> params) {
        return null;
    }

    @Override
    public ModelAndView editDeposit(MultiValueMap<String, String> params) {
        return null;
    }

    @Override
    public ModelAndView closeFixedDeposit(int fdId) {
        return null;
    }

    @Override
    public ModelAndView viewFixedDepositDetails(HttpServletRequest request) {
        return null;
    }
}
