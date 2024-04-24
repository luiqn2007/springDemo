package com.example.mybank.controller;

import com.example.mybank.domain.BankAccountDetails;
import com.example.mybank.domain.FixedDepositDetails;
import com.example.mybank.service.FixedDepositService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/fixedDeposit")
@SessionAttributes(value = {"newFixedDepositDetails", "editableFixedDepositDetails"}, types = FixedDepositDetails.class)
public class FixedDepositController {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final int MIN_DEPOSIT_ACCOUNT = 1000;
    private static final int MIN_TENURE = 6;

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
    public String deleteFixedDeposit(@RequestParam int fixedDepositId) {
        fixedDepositService.deleteFixedDeposit(fixedDepositId);
        return "redirect:/fixedDeposit/list";
    }

    @ModelAttribute("newFixedDepositDetails")
    public FixedDepositDetails getNewFixedDepositDetails() {
        FixedDepositDetails fixedDepositDetails = FixedDepositDetails.builder()
                // todo: use session to get accountId
                .bankAccountId(BankAccountDetails.builder().accountId(1).build())
                .depositAmount(MIN_DEPOSIT_ACCOUNT)
                .tenure(MIN_TENURE)
                .email("You must enter a valid email")
                .build();
        LOGGER.info("getFixedDepositDetails() called, return a new instance of FixedDepositDetails.");
        return fixedDepositDetails;
    }

    @PostMapping(params = "fdAction=createFDForm")
    public String showOpenFixedDepositForm(Model model) {
        model.addAttribute("errors", Map.of());
        return "createFixedDepositForm";
    }

    @PostMapping(params = "fdAction=create")
    public String openFixedDeposit(@ModelAttribute("newFixedDepositDetails") FixedDepositDetails fixedDepositDetails, Model model, SessionStatus sessionStatus) {
        LOGGER.info("openFixedDeposit() called, fixedDepositDetails {}", fixedDepositDetails);
        Map<String, String> errors = validateFixedDepositDetails(fixedDepositDetails);
        if (errors.isEmpty()) {
            fixedDepositService.createFixedDeposit(fixedDepositDetails);
            sessionStatus.setComplete();
            return "redirect:/fixedDeposit/list";
        } else {
            model.addAttribute("errors", errors);
            return "createFixedDepositForm";
        }
    }

    @PostMapping(params = "fdAction=edit")
    public String editDeposit(@ModelAttribute("editableFixedDepositDetails") FixedDepositDetails fixedDepositDetails, Model model, SessionStatus sessionStatus) {
        Map<String, String> errors = validateFixedDepositDetails(fixedDepositDetails);
        if (errors.isEmpty()) {
            fixedDepositService.createFixedDeposit(fixedDepositDetails);
            sessionStatus.setComplete();
            return "redirect:/fixedDeposit/list";
        } else {
            model.addAttribute("errors", errors);
            return "editFixedDepositForm";
        }
    }

    @GetMapping(params = "fdAction=view")
    public ModelAndView viewFixedDepositDetails(@RequestParam int fixedDepositId) {
        FixedDepositDetails fixedDepositDetails = fixedDepositService.getFixedDepositDetails(fixedDepositId);
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("fixedDepositDetails", fixedDepositDetails);
        LOGGER.info("viewFixedDepositDetails() method: Fixed deposit details loaded from data store. Showing form for editing the loaded fixed deposit.");
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

    @GetMapping(params = "fdAction=close")
    public String closeFixedDeposit(@RequestParam int fixedDepositId) {
        // fixedDepositService.closeFixedDeposit(fdId);
        return "redirect:/fixedDeposit/list";
    }

    private Map<String, String> validateFixedDepositDetails(FixedDepositDetails fixedDepositDetails) {

        Map<String, String> errors = new HashMap<>();

        if (fixedDepositDetails.getDepositAmount() < MIN_DEPOSIT_ACCOUNT) {
            errors.put("depositAmount", "must be greater than or equal to " + MIN_DEPOSIT_ACCOUNT);
        }

        if (fixedDepositDetails.getTenure() < MIN_TENURE) {
            errors.put("tenure", "must be greater than or equal to " + MIN_TENURE);
        }

        String email = fixedDepositDetails.getEmail();
        if (email == null || "".equalsIgnoreCase(email)) {
            errors.put("email", "must not be blank");
        } else if (!email.contains("@")) {
            errors.put("email", "not a well-formed email address");
        }

        return errors;
    }
}
