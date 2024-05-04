package com.example.mybank_xml.controller;

import com.example.mybank_xml.domain.BankAccountDetails;
import com.example.mybank_xml.domain.FixedDepositDetails;
import com.example.mybank_xml.service.FixedDepositService;
import jakarta.validation.Valid;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.SimpleErrors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.example.mybank_xml.domain.FixedDepositDetails.MIN_DEPOSIT_AMOUNT;
import static com.example.mybank_xml.domain.FixedDepositDetails.MIN_TENURE;

@Setter
@SessionAttributes(value = {"newFixedDepositDetails", "editableFixedDepositDetails"}, types = FixedDepositDetails.class)
public class FixedDepositController {

    private static final Logger LOGGER = LogManager.getLogger();

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
    public ModelAndView viewFixedDepositDetails(@RequestParam FixedDepositDetails fixedDepositId) {
//        FixedDepositDetails fixedDepositDetails = fixedDepositService.getFixedDepositDetails(fixedDepositId);
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("editableFixedDepositDetails", fixedDepositId);
        modelMap.put("errors", new SimpleErrors(fixedDepositId));
        LOGGER.info("viewFixedDepositDetails() method: Fixed deposit details loaded from data store. Showing form for editing the loaded fixed deposit.");
        return new ModelAndView("editFixedDepositForm", modelMap);
    }

    @GetMapping(params = "fdAction=close")
    public String closeFixedDeposit(@RequestParam int fixedDepositId) {
        // fixedDepositService.closeFixedDeposit(fdId);
        return "redirect:/fixedDeposit/list";
    }

    @GetMapping(params = "fdAction=upload")
    public String upload(@RequestParam Optional<String> message, Model model) {
        model.addAttribute("uploadMessage", message.orElse("Please upload a file: "));
        return "upload";
    }

    @PostMapping("/upload")
    public String receiveUploadedFile(@RequestParam MultipartFile myFileField) throws IOException {
        // do something
        byte[] bytes = myFileField.getBytes();
        String values = new String(bytes);
        System.out.println(values);
        return "redirect:/fixedDeposit?fdAction=upload";
    }

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
