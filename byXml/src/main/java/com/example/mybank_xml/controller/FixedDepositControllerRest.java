package com.example.mybank_xml.controller;

import com.example.mybank_xml.domain.FixedDepositDetails;
import com.example.mybank_xml.service.FixedDepositService;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

// REST 风格
@Setter
public class FixedDepositControllerRest {

    private final Gson gson = new Gson();

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
