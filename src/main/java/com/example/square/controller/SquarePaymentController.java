package com.example.square.controller;

import com.example.square.dto.AmountDTO;
import com.example.square.service.SquarePaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/square")
@RequiredArgsConstructor
public class SquarePaymentController {
    private final SquarePaymentService squarePaymentService;

    @PostMapping("/create-payment")
    public String createPayment(@RequestBody AmountDTO amountDTO) {
        return squarePaymentService.createPayment(amountDTO.getAmount());
    }
    
}
