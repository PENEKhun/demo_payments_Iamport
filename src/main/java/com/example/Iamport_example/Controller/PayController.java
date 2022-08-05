package com.example.Iamport_example.Controller;

import com.example.Iamport_example.Dto.Iamport.PaymentDto;
import com.example.Iamport_example.Dto.Iamport.RequestDto;
import com.example.Iamport_example.InsteadDB;
import com.example.Iamport_example.Service.PayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PayController {
    private final PayService payService;

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/payments/complete")
    public String completeMapping(RequestDto request) {
      log.info("imp_uid : " + request.getImp_uid());
      log.info("merchant_uid : " + request.getMerchant_uid());

      String accessToken = payService.getIamportAccessToken();
      if (accessToken == null)
          throw new NullPointerException("아임포트 access token 가져오기 실패");

      log.info("Iamport accessToken : " + accessToken);
      PaymentDto payments = payService.getIamportPaymentData(request.getImp_uid(), accessToken);

      if (payments.getCode() == -1) {
          throw new IllegalStateException("결제 오류");
      }

      InsteadDB.item item = new InsteadDB.item();

      if (payments.getResponse().getMerchant_uid().equals(item.name))
          throw new IllegalStateException("상품이 일치하지 않음");

      if (payments.getResponse().getAmount() != item.price)
          throw new IllegalStateException("결제 금액이 일치하지 않음");

      log.info("결제 성공");

    return "success";
    }

}
