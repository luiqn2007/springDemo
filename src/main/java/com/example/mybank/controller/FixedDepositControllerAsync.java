package com.example.mybank.controller;

import com.example.mybank.domain.FixedDepositDetails;
import com.example.mybank.service.FixedDepositService;
import com.example.mybank.util.ResultContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;

@Controller
@RequestMapping("/fixedDepositAsync")
public class FixedDepositControllerAsync {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String LIST_METHOD = "getFixedDepositList";
    private static final String GET_FD_METHOD = "getFixedDeposit";

    @Autowired
    private FixedDepositService fixedDepositService;

    private final Queue<ResultContext<?>> deferredResultQueue = new ConcurrentLinkedQueue<>();

    @GetMapping(path = "/list1")
    public Callable<ModelAndView> listFixedDeposits1() {
        return () -> {
            Thread.sleep(5000);
            Map<String, Object> modelData = new HashMap<>();
            modelData.put("fdList", fixedDepositService.getFixedDeposits());
            return new ModelAndView("fixedDeposit/list", modelData);
        };
    }

    @GetMapping(path = "/list2")
    public DeferredResult<ResponseEntity<List<FixedDepositDetails>>> listFixedDeposits2() {
        ResultContext<ResponseEntity<List<FixedDepositDetails>>> context = new ResultContext<>(LIST_METHOD);
        deferredResultQueue.add(context);
        return context.getResult();
    }

    @Scheduled(fixedRate = 1000)
    public void processResults() {
        while (!deferredResultQueue.isEmpty()) {
            ResultContext<?> context = deferredResultQueue.poll();
            switch (context.getMethodToInvoke()) {
                case LIST_METHOD:
                    var result = (DeferredResult<ResponseEntity<List<FixedDepositDetails>>>) context.getResult();
                    List<FixedDepositDetails> fixedDeposits = fixedDepositService.getFixedDeposits();
                    var response = new ResponseEntity<>(fixedDeposits, HttpStatus.OK);
                    result.setResult(response);
                    break;
            }
        }
    }
}
