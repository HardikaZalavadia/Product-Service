//package dev.hardika.EcomProductService.service.userAuthClient;
//
//import dev.hardika.EcomProductService.client.userAuthClient.UserAuthServiceClient;
//import dev.hardika.EcomProductService.dto.userClientDTO.LoginRequestDTO;
//import dev.hardika.EcomProductService.dto.userClientDTO.SignUpRequestDTO;
//import dev.hardika.EcomProductService.dto.userClientDTO.UserResponseDTO;
//import dev.hardika.EcomProductService.exception.clientException.UserNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserAuthServiceImpl {
//    @Autowired
//    private  UserAuthServiceClient userAuthServiceClient;
//
//    public   UserResponseDTO signUp(SignUpRequestDTO signUpRequestDTO) {
//        UserResponseDTO userResponseDTO = userAuthServiceClient.singUpUser(signUpRequestDTO);
//        return userResponseDTO;
//    }
//
//    public UserResponseDTO login(LoginRequestDTO loginRequestDTO) throws UserNotFoundException {
//        if(loginRequestDTO.getEmail()== null || loginRequestDTO.getEmail().isEmpty()){
//            throw new UserNotFoundException("User Name should not be empty or null");
//        }
//        if(loginRequestDTO.getPassword() == null || loginRequestDTO.getPassword().isEmpty()){
//            throw new UserNotFoundException("Password should not be empty or null");
//        }
//        UserResponseDTO userResponseDTO = userAuthServiceClient.loginUser(loginRequestDTO);
//        return userResponseDTO;
//    }
//}
