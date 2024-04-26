package com.example.mybank.controller;

import com.example.mybank.domain.BankAccountDetails;
import com.example.mybank.domain.FixedDepositDetails;
import com.example.mybank.service.FixedDepositService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.SimpleErrors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.example.mybank.domain.FixedDepositDetails.MIN_DEPOSIT_AMOUNT;
import static com.example.mybank.domain.FixedDepositDetails.MIN_TENURE;

@Controller
@RequestMapping("/fixedDeposit")
@SessionAttributes(value = {"newFixedDepositDetails", "editableFixedDepositDetails"}, types = FixedDepositDetails.class)
public class FixedDepositController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private FixedDepositService fixedDepositService;


    // private FixedDepositValidator validator = new FixedDepositValidator();

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
        Calendar calendar = new GregorianCalendar();
        Date creation = calendar.getTime();
        calendar.add(Calendar.MONTH, MIN_TENURE);
        Date maturity = calendar.getTime();
        FixedDepositDetails fixedDepositDetails = FixedDepositDetails.builder()
                // todo: use session to get accountId
                .bankAccountId(BankAccountDetails.builder().accountId(1).build())
                .depositAmount(MIN_DEPOSIT_AMOUNT)
                .tenure(MIN_TENURE)
                .active("Y")
                .creationDate(creation)
                .maturityDate(maturity)
                .email("You must enter a valid email")
                .build();
        LOGGER.info("getFixedDepositDetails() called, return a new instance of FixedDepositDetails.");
        return fixedDepositDetails;
    }

    @PostMapping(params = "fdAction=createFDForm")
    public String showOpenFixedDepositForm(Model model) {
        model.addAttribute("errors", new SimpleErrors(model));
        return "createFixedDepositForm";
    }

    @PostMapping(params = "fdAction=create")
    public String openFixedDeposit(@Valid @ModelAttribute("newFixedDepositDetails") FixedDepositDetails fixedDepositDetails,
                                   BindingResult bindingResult, Errors errors,
                                   Model model, SessionStatus sessionStatus) {
        LOGGER.info("openFixedDeposit() called, fixedDepositDetails {}", fixedDepositDetails);
        if (bindingResult.hasErrors() || errors.hasErrors()) {
            errors.addAllErrors(bindingResult);
            model.addAttribute("errors", errors);
            LOGGER.error("openFixedDeposit() called, errors {}", errors);
            return "createFixedDepositForm";
        } else {
            fixedDepositService.createFixedDeposit(fixedDepositDetails);
            sessionStatus.setComplete();
            return "redirect:/fixedDeposit/list";
        }
    }

    @PostMapping(params = "fdAction=edit")
    public String editDeposit(@Valid @ModelAttribute("editableFixedDepositDetails") FixedDepositDetails fixedDepositDetails,
                              BindingResult bindingResult, Errors errors,
                              Model model, SessionStatus sessionStatus) {
        if (bindingResult.hasErrors() || errors.hasErrors()) {
            errors.addAllErrors(bindingResult);
            model.addAttribute("errors", errors);
            LOGGER.error("editDeposit() called, errors {}", errors);
            return "editFixedDepositForm";
        } else {
            fixedDepositService.createFixedDeposit(fixedDepositDetails);
            sessionStatus.setComplete();
            return "redirect:/fixedDeposit/list";
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

    @GetMapping(params = "fdAction=close")
    public String closeFixedDeposit(@RequestParam int fixedDepositId) {
        // fixedDepositService.closeFixedDeposit(fdId);
        return "redirect:/fixedDeposit/list";
    }

    // REST

    // Config

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"), true));
        binder.setDisallowedFields("depositAmount");
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
