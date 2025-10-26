package com.order.order.common;

public class ErrorResponse implements OrderResponse {

    private final String errorMessage;


    public ErrorResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
