package com.xgileit.model;

import lombok.Data;

@Data
public class PaymentRequest {
    private String token;
    private int amountInCents;
    private String currency;
}
