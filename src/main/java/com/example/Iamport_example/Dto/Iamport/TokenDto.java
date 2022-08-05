package com.example.Iamport_example.Dto.Iamport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TokenDto {

    @JsonCreator
    public TokenDto(@JsonProperty("code") int code, @JsonProperty("response") Response response) {
        this.code = code;
        this.response = response;
    }

    private int code;
    private Response response;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    @Data
    public static class Response {

        private int expired_at;
        private int now;
        private String access_token;

        @JsonCreator
        public Response(@JsonProperty("expired_at") int expired_at, @JsonProperty("now") int now, @JsonProperty("access_token") String access_token) {
            this.expired_at = expired_at;
            this.now = now;
            this.access_token = access_token;
        }
    }

}
