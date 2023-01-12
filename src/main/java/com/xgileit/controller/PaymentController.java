package com.xgileit.controller;

import com.xgileit.model.PaymentRequest;
import com.xgileit.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService service;

    private @ResponseBody ResponseEntity<Object> makePayment(@RequestBody PaymentRequest request){
        try {
            return new ResponseEntity<>(service.createPayment(request), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
