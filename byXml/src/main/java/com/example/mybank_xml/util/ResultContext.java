package com.example.mybank_xml.util;

import lombok.Getter;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ResultContext<T> {

    private final String methodToInvoke;
    private final DeferredResult<T> result = new DeferredResult<>();
    private final Map<String, Object> params = new HashMap<>();

    public ResultContext(String methodToInvoke) {
        this.methodToInvoke = methodToInvoke;
    }
}
