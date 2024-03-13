package com.example.mybank.controller;

import com.example.mybank.service.ServiceTemplate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestControllerImpl implements UserRequestController {

    private ServiceTemplate serviceTemplate;
}
