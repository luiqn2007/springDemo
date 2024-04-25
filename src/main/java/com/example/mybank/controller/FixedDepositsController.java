package com.example.mybank.controller;

import com.example.mybank.domain.FixedDepositDetails;
import com.example.mybank.service.FixedDepositService;
import com.google.gson.Gson;
import com.google.gson.JsonSerializer;
import com.google.gson.stream.JsonWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

// REST 风格
@Controller
@RequestMapping("/fixedDeposits")
public class FixedDepositsController {

    private Gson gson = new Gson();

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

    @GetMapping(params = "accountId")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<FixedDepositDetails> getFixedDepositDetailsByAccount(@RequestParam int accountId) {
        return fixedDepositService.findFixedDepositsByBankAccount(accountId);
    }

//    @PostMapping
//    @PutMapping(params = "id")
//    @DeleteMapping(params = "id")
}
