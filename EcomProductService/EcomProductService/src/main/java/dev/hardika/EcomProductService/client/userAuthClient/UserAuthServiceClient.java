package dev.hardika.EcomProductService.client.userAuthClient;

import dev.hardika.EcomProductService.client.userAuthClient.dto.ValidateRequestDto;
import dev.hardika.EcomProductService.client.userAuthClient.dto.ValidateResponseDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserAuthServiceClient {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

//    @Value("${userservice.api.base.url}")
//    private String userServiceAPIBaseUlr;
//
//    @Value("${userservice.api.user.validatetoken}")
//    private String userServiceAPIValidateToken;

    public ValidateResponseDto validate(String token, Long userId) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ValidateRequestDto requestDto = new ValidateRequestDto();
        requestDto.setToken(token);
        requestDto.setUserId(userId);

        ResponseEntity<ValidateResponseDto> response =
                restTemplate.postForEntity("http://localhost:8081/auth/validate", requestDto, ValidateResponseDto.class);

        return response.getBody();
    }


}
