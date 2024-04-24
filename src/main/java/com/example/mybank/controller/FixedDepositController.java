package com.example.mybank.controller;

import com.example.mybank.domain.BankAccountDetails;
import com.example.mybank.domain.FixedDepositDetails;
import com.example.mybank.service.FixedDepositService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        List<FixedDepositDetails> fixedDeposits = fixedDepositService.getFixedDeposits();
        LOGGER.info("listFixedDeposits() called, return a list of FixedDepositDetails");
        return fixedDeposits;
    }

    @GetMapping(params = "fdAction=delete")
    public String deleteFixedDeposit(@RequestParam int depositId) {
        fixedDepositService.deleteFixedDeposit(depositId);
        return "redirect:/fixedDeposit/list";
    }

    @PostMapping(params = "fdAction=createFDForm")
    public String showOpenFixedDepositForm(Model model) {
        model.addAttribute("errors", Map.of());
        return "createFixedDepositForm";
    }

    @ModelAttribute("fixedDepositDetails")
    public FixedDepositDetails getFixedDepositDetails() {
        FixedDepositDetails fixedDepositDetails = FixedDepositDetails.builder()
                .id(1)
                // todo: use session to get accountId
                .bankAccountId(BankAccountDetails.builder().accountId(1).build())
                .depositAmount(1000)
                .tenure(12)
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

        Map<String, String> errors = new HashMap<>();
        Map<String, Object> modelData = new HashMap<>();
        int depositAmountValue = checkAndGetInteger("depositAmount", depositAmount, 1000, errors);
        int tenureValue = checkAndGetInteger("tenure", tenure, 12, errors);
        int accountId = 1; // todo: use session to get accountId

        if (StringUtils.isBlank(email)) {
            errors.put("email", "must not be blank");
        } else if (!email.contains("@")) {
            errors.put("email", "not a well-formed email address");
        }

        FixedDepositDetails fixedDepositDetails = FixedDepositDetails.builder()
                .bankAccountId(BankAccountDetails.builder().accountId(accountId).build())
                .depositAmount(depositAmountValue)
                .tenure(tenureValue)
                .email(email)
                .build();

        if (errors.isEmpty()) {
            LOGGER.info("insert fixedDepositDetails {}", fixedDepositDetails);
            fixedDepositService.createFixedDeposit(fixedDepositDetails);
            return new ModelAndView("redirect:/fixedDeposit/list");
        } else {
            modelData.put("errors", errors);
            modelData.put("fixedDepositDetails", fixedDepositDetails);
            return new ModelAndView("createFixedDepositForm", modelData);
        }
    }

    @PostMapping(params = "fdAction=edit")
    public ModelAndView editDeposit(@RequestParam  MultiValueMap<String, String> params) {
        String depositAmount = params.get("depositAmount").get(0);
        String tenure = params.get("tenure").get(0);
        String email = params.get("email").get(0);

        Map<String, String> errors = new HashMap<>();
        Map<String, Object> modelData = new HashMap<>();
        int depositAmountValue = checkAndGetInteger("depositAmount", depositAmount, 1000, errors);
        int tenureValue = checkAndGetInteger("tenure", tenure, 12, errors);

        if (email == null || "".equalsIgnoreCase(email)) {
            errors.put("email", "must not be blank");
        } else if (!email.contains("@")) {
            errors.put("email", "not a well-formed email address");
        }

        FixedDepositDetails fixedDepositDetails = FixedDepositDetails.builder()
                .depositAmount(depositAmountValue)
                .tenure(tenureValue)
                .email(email)
                .build();
        if (errors.isEmpty()) {
            fixedDepositService.createFixedDeposit(fixedDepositDetails);
            return new ModelAndView("redirect:/fixedDeposit/list");
        } else {
            modelData.put("fixedDepositDetails", fixedDepositDetails);
            modelData.put("errors", errors);
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
            errorMessage.put(name, "must be greater than or equal to " + min);
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
        LOGGER.error("Exception occurred", ex);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        return new ModelAndView("error", "error", sw.toString());
    }
}
