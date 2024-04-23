package com.example.mybank.controller;

import com.example.mybank.domain.FixedDepositDetails;
import com.example.mybank.service.FixedDepositService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/fixedDeposit")
public class FixedDepositController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private FixedDepositService fixedDepositService;

    @GetMapping(path = "/list")
    @ModelAttribute("fdList")
    public List<FixedDepositDetails> listFixedDeposits() {
        return fixedDepositService.getFixedDeposits();
    }

    @GetMapping(params = "fdAction=delete")
    public String deleteFixedDeposit(@RequestParam int depositId) {
        fixedDepositService.deleteFixedDeposit(depositId);
        return "redirect:/fixedDeposit/list";
    }

    @PostMapping(params = "fdAction=createFDForm")
    public ModelAndView showOpenFixedDepositForm() {
        ModelMap modelData = new ModelMap();
        modelData.addAttribute("errors", Map.of());
        return new ModelAndView("createFixedDepositForm", modelData);
    }

    @ModelAttribute("newFixedDepositDetails")
    public FixedDepositDetails getFixedDepositDetails() {
        FixedDepositDetails fixedDepositDetails = FixedDepositDetails.builder()
                .email("You must enter a valid email")
                .build();
        LOGGER.info("getFixedDepositDetails() called, return a new instance of FixedDepositDetails");
        return fixedDepositDetails;
    }

    @PostMapping(params = "fdAction=create")
    public ModelAndView openFixedDeposit(@RequestParam Map<String, String> params) {
        String depositAmount = params.get("depositAmount");
        String tenure = params.get("tenure");
        String email = params.get("email");

        Map<String, String> errorMessage = new HashMap<>();
        Map<String, Object> modelData = new HashMap<>();
        int depositAmountValue = checkAndGetInteger("depositAmount", depositAmount, 1000, errorMessage);
        int tenureValue = checkAndGetInteger("tenure", tenure, 12, errorMessage);

        if (StringUtils.isBlank(email)) {
            errorMessage.put("email", "must not be blank");
        } else if (!email.contains("@")) {
            errorMessage.put("email", "not a well-formed email address");
        }

        FixedDepositDetails fixedDepositDetails = FixedDepositDetails.builder()
                .depositAmount(depositAmountValue)
                .tenure(tenureValue)
                .email(email)
                .build();
        if (errorMessage.isEmpty()) {
            fixedDepositService.createFixedDeposit(fixedDepositDetails);
            return new ModelAndView("redirect:/fixedDeposit/list");
        } else {
            modelData.put("errors", errorMessage);
            modelData.put("fixedDepositDetails", fixedDepositDetails);
            return new ModelAndView("createFixedDepositForm", modelData);
        }
    }

    @PostMapping(params = "fdAction=edit")
    public ModelAndView editDeposit(@RequestParam  MultiValueMap<String, String> params) {
        String depositAmount = params.get("depositAmount").get(0);
        String tenure = params.get("tenure").get(0);
        String email = params.get("email").get(0);
        String id = params.get("id").get(0);

        Map<String, String> errorMessage = new HashMap<>();
        Map<String, Object> modelData = new HashMap<>();
        int depositAmountValue = checkAndGetInteger("depositAmount", depositAmount, 1000, errorMessage);
        int tenureValue = checkAndGetInteger("tenure", tenure, 12, errorMessage);

        if (email == null || "".equalsIgnoreCase(email)) {
            errorMessage.put("email", "must not be blank");
        } else if (!email.contains("@")) {
            errorMessage.put("email", "not a well-formed email address");
        }

        FixedDepositDetails fixedDepositDetails = FixedDepositDetails.builder()
                .depositAmount(depositAmountValue)
                .tenure(tenureValue)
                .email(email)
                .build();
        if (errorMessage.isEmpty()) {
            fixedDepositService.createFixedDeposit(fixedDepositDetails);
            return new ModelAndView("redirect:/fixedDeposit/list");
        } else {
            modelData.put("fixedDepositDetails", fixedDepositDetails);
            modelData.put("errors", errorMessage);
            return new ModelAndView("editFixedDepositForm", modelData);
        }
    }

    private int checkAndGetInteger(String name, String value, int min, Map<String, String> errorMessage) {
        if (!NumberUtils.isCreatable(value)) {
            errorMessage.put(name, "enter a valid number");
            return min;
        }
        int i = NumberUtils.toInt(value);
        if (i < min) {
            errorMessage.put(value, "must be greater than or equal to " + min);
            return min;
        }
        return i;
    }

    @GetMapping(params = "fdAction=close")
    public String closeFixedDeposit(@RequestParam("fixedDepositId") int fdId) {
        // fixedDepositService.closeFixedDeposit(fdId);
        return "redirect:/fixedDeposit/list";
    }

    @GetMapping(params = "fdAction=view")
    public ModelAndView viewFixedDepositDetails(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("fixedDepositId"));
        FixedDepositDetails fixedDepositDetails = fixedDepositService.getFixedDepositDetails(id);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute(fixedDepositDetails);
        return new ModelAndView("editFixedDepositForm", modelMap);
    }

    @ExceptionHandler
    public ModelAndView handleException(Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        return new ModelAndView("error", "error", sw.toString());
    }
}
