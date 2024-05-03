package com.example.mybank.controller;

import com.example.mybank.domain.FixedDepositDetails;
import com.example.mybank.service.FixedDepositService;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

// REST 风格
@Controller
@RequestMapping("/fixedDeposits")
public class FixedDepositsController {

    private final Gson gson = new Gson();

    @Autowired
    private FixedDepositService fixedDepositService;

    @GetMapping
    public ResponseEntity<List<FixedDepositDetails>> getFixedDepositDetails() {
        return ResponseEntity.ok(fixedDepositService.getFixedDeposits());
    }

    @GetMapping(params = "id")
    public void getFixedDepositDetailsById(@RequestParam int id, HttpServletResponse response) throws IOException {
        FixedDepositDetails fixedDepositDetails = fixedDepositService.getFixedDepositDetails(id);
        String data = gson.toJson(fixedDepositDetails);
        response.getWriter().write(data);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
