package dev.hardika.EcomProductService.client.userAuthClient.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateResponseDto {
    private UserResponseDTO userResponseDTO;
    private SessionStatus sessionStatus;
}
