package com.example.square.service;

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

    public void createPayment() {
        SquareClient squareClient = new SquareClient.Builder()
                .accessToken("YOUR_ACCESS_TOKEN")
                .environment(Environment.SANDBOX)
                .build();

        PaymentsApi paymentsApi = squareClient.getPaymentsApi();

        CreatePaymentRequest createPaymentRequest = new CreatePaymentRequest
                .Builder("SOURCE_ID", "YOUR_IDEMPOTENCY_KEY")
                .amountMoney(new Money.Builder()
                        .amount(100L)
                        .currency("USD")
                        .build())
                .build();

        try {
            CreatePaymentResponse payment = paymentsApi.createPayment(createPaymentRequest);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
