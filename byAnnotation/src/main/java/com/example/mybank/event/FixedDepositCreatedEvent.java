package com.example.mybank.event;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class FixedDepositCreatedEvent implements Event {

    private Map<String, Object> eventData;

    @Override
    public String getEventType() {
        return "FixedDepositCreatedEvent";
    }
}
