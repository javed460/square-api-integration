package com.example.square.service;

import com.example.square.config.SquareConfiguration;
import com.squareup.square.Environment;
import com.squareup.square.SquareClient;
import com.squareup.square.api.PaymentsApi;
import com.squareup.square.exceptions.ApiException;
import com.squareup.square.models.CreatePaymentRequest;
import com.squareup.square.models.CreatePaymentResponse;
import com.squareup.square.models.Money;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SquarePaymentService {
    private final SquareConfiguration squareConfiguration;

    public String createPayment(Long amount) {
        SquareClient squareClient = new SquareClient.Builder()
                .accessToken(squareConfiguration.getAccessToken())
                .environment(Environment.SANDBOX)
                .build();

        PaymentsApi paymentsApi = squareClient.getPaymentsApi();

        CreatePaymentRequest createPaymentRequest = new CreatePaymentRequest
                .Builder("SOURCE_ID", "YOUR_IDEMPOTENCY_KEY")
                .amountMoney(new Money.Builder()
                        .amount(amount)
                        .currency("USD")
                        .build())
                .build();

        try {
            CreatePaymentResponse payment = paymentsApi.createPayment(createPaymentRequest);
            return payment.getPayment().getId();
        } catch (ApiException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
