package com.example.mybank.chapter03.controller;

import com.example.mybank.chapter03.service.ServiceTemplate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestControllerImpl implements UserRequestController {

    private ServiceTemplate serviceTemplate;
}
