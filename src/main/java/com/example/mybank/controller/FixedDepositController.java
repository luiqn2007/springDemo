package com.example.mybank.controller;

import com.example.mybank.domain.FixedDepositDetails;
import com.example.mybank.service.FixedDepositService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private FixedDepositService fixedDepositService;

    @GetMapping(path = "/list")
    public ModelAndView listFixedDeposits() {
        Map<String, List<FixedDepositDetails>> modelData = new HashMap<>();
        modelData.put("fdList", fixedDepositService.getFixedDeposits());
        return new ModelAndView("fixedDepositList", modelData);
    }

    @PostMapping(params = "fdAction=createFDForm")
    public ModelAndView showOpenFixedDepositForm() {
        FixedDepositDetails fixedDepositDetails = FixedDepositDetails.builder()
                .email("You must enter a valid email")
                .build();
        ModelMap modelData = new ModelMap();
        modelData.addAttribute(fixedDepositDetails);
        return new ModelAndView("createFixedDepositForm", modelData);
    }

    @PostMapping(params = "fdAction=create")
    public ModelAndView openFixedDeposit(@RequestParam Map<String, String> params) {
        String depositAmount = params.get("depositAmount");
        String tenure = params.get("tenure");
        String email = params.get("email");

        Map<String, Object> modelData = new HashMap<>();
        int depositAmountValue = checkAndGetInteger("depositAmount", depositAmount, 1000, modelData);
        int tenureValue = checkAndGetInteger("tenure", tenure, 12, modelData);

        if (StringUtils.isBlank(email)) {
            modelData.put("error.email", "must not be blank");
        } else if (!email.contains("@")) {
            modelData.put("error.email", "not a well-formed email address");
        }

        FixedDepositDetails fixedDepositDetails = FixedDepositDetails.builder()
                .depositAmount(depositAmountValue)
                .tenure(tenureValue)
                .email(email)
                .build();
        if (modelData.isEmpty()) {
            fixedDepositService.createFixedDeposit(fixedDepositDetails);
            return new ModelAndView("redirect:/fixedDeposit/list");
        } else {
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

        Map<String, Object> modelData = new HashMap<>();
        int depositAmountValue = checkAndGetInteger("depositAmount", depositAmount, 1000, modelData);
        int tenureValue = checkAndGetInteger("tenure", tenure, 12, modelData);

        if (email == null || "".equalsIgnoreCase(email)) {
            modelData.put("error.email", "must not be blank");
        } else if (!email.contains("@")) {
            modelData.put("error.email", "not a well-formed email address");
        }

        FixedDepositDetails fixedDepositDetails = FixedDepositDetails.builder()
                .depositAmount(depositAmountValue)
                .tenure(tenureValue)
                .email(email)
                .build();
        if (modelData.isEmpty()) {
            fixedDepositService.createFixedDeposit(fixedDepositDetails);
            return new ModelAndView("redirect:/fixedDeposit/list");
        } else {
            modelData.put("fixedDepositDetails", fixedDepositDetails);
            return new ModelAndView("createFixedDepositForm", modelData);
        }
    }

    private int checkAndGetInteger(String name, String value, int min, Map<String, Object> modelData) {
        if (!NumberUtils.isCreatable(value)) {
            modelData.put("error." + name, "enter a valid number");
        }
        int i = NumberUtils.toInt(value);
        if (i < min) {
            modelData.put("error." + value, "must be greater than or equal to " + min);
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
