package com.example.Iamport_example.Dto.Iamport;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PaymentDto {
    @JsonProperty("response")
    private Response response;
    @JsonProperty("code")
    private int code;

    @Data
    public static class Response {
        @JsonProperty("vbank_issued_at")
        private int vbank_issued_at;
        @JsonProperty("vbank_date")
        private int vbank_date;
        @JsonProperty("user_agent")
        private String user_agent;
        @JsonProperty("status")
        private String status;
        @JsonProperty("started_at")
        private int started_at;
        @JsonProperty("receipt_url")
        private String receipt_url;
        @JsonProperty("pg_tid")
        private String pg_tid;
        @JsonProperty("pg_provider")
        private String pg_provider;
        @JsonProperty("pg_id")
        private String pg_id;
        @JsonProperty("pay_method")
        private String pay_method;
        @JsonProperty("paid_at")
        private int paid_at;
        @JsonProperty("name")
        private String name;
        @JsonProperty("merchant_uid")
        private String merchant_uid;
        @JsonProperty("imp_uid")
        private String imp_uid;
        @JsonProperty("failed_at")
        private int failed_at;
        @JsonProperty("escrow")
        private boolean escrow;
        @JsonProperty("currency")
        private String currency;
        @JsonProperty("channel")
        private String channel;
        @JsonProperty("cash_receipt_issued")
        private boolean cash_receipt_issued;
        @JsonProperty("card_quota")
        private int card_quota;
        @JsonProperty("cancelled_at")
        private int cancelled_at;
        @JsonProperty("cancel_receipt_urls")
        private List<String> cancel_receipt_urls;
        @JsonProperty("cancel_history")
        private List<String> cancel_history;
        @JsonProperty("cancel_amount")
        private int cancel_amount;
        @JsonProperty("buyer_tel")
        private String buyer_tel;
        @JsonProperty("buyer_name")
        private String buyer_name;
        @JsonProperty("buyer_email")
        private String buyer_email;
        @JsonProperty("buyer_addr")
        private String buyer_addr;
        @JsonProperty("apply_num")
        private String apply_num;
        @JsonProperty("amount")
        private int amount;
    }
}
