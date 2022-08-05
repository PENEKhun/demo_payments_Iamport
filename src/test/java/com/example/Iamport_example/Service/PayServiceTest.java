package com.example.Iamport_example.Service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PayServiceTest {
    private PayService payService;

    @Test
    void getIamportAccessToken() {
        String token = payService.getIamportAccessToken();
        System.out.printf("token : %s ", token);
    }

    @Test
    void getIamportPaymentData() {
    }
}