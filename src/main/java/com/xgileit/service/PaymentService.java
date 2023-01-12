package com.xgileit.service;

import com.xgileit.model.PaymentRequest;
import lombok.extern.slf4j.Slf4j;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Base64;

@Service
@Slf4j
public class PaymentService {

    @Autowired
    private RestTemplate restTemplate;

    public Object createPayment(PaymentRequest request) throws URISyntaxException {
        String url = "https://online.yoco.com/v1/charges/";
        HttpEntity<PaymentRequest> requestEntity = new HttpEntity<>(request, this.buildRequestHeaders());
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        this.restTemplate.setRequestFactory(requestFactory);
        log.info("request {}", new Gson().toJson(request));
        ResponseEntity<Object> response = this.restTemplate.exchange(new URI(url), HttpMethod.POST,
                requestEntity, Object.class);
        log.info("response {}", new Gson().toJson(response));
        return response;
    }

    private HttpHeaders buildRequestHeaders() {
        String credential = "sk_test_960bfde0VBrLlpK098e4ffeb53e1:";
        String encodedCredential = new String(Base64.getEncoder().encodeToString(credential.getBytes()));
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        requestHeaders.add("Authorization", "Basic " + encodedCredential);

		return requestHeaders;
	}
}
