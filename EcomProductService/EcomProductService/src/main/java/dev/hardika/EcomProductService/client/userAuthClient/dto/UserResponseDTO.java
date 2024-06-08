package dev.hardika.EcomProductService.client.userAuthClient.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponseDTO {
    private String username;
    private String email;
    private List<Role> role;

}
