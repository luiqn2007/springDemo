package com.example.mybank.controller;

import com.example.mybank.domain.FixedDepositDetails;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.example.mybank.domain.FixedDepositDetails.*;

public class FixedDepositValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return FixedDepositDetails.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FixedDepositDetails fixedDepositDetails = (FixedDepositDetails) target;

//        if (fixedDepositDetails.getDepositAmount() < MIN_DEPOSIT_AMOUNT) {
//            errors.rejectValue("depositAmount", "error.depositAmount.less", "must be greater than or equal to " + MIN_DEPOSIT_AMOUNT);
//        }

//        if (fixedDepositDetails.getDepositAmount() > MAX_DEPOSIT_AMOUNT) {
//            errors.rejectValue("depositAmount", "error.depositAmount.less", "must be smaller than or equal to " + MAX_DEPOSIT_AMOUNT);
//        }

        if (fixedDepositDetails.getTenure() < MIN_TENURE) {
            errors.rejectValue("tenure", "error.tenure.less", "must be greater than or equal to " + MIN_TENURE);
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.email.blank", "must not be blank");
        if (!errors.hasFieldErrors("email") && !fixedDepositDetails.getEmail().contains("@")) {
            errors.rejectValue("email", "error.email.format", "not a well-formed email address");
        }
    }
}
