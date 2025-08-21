package com.securebank.securenank.Controller;

import com.securebank.securenank.DTO.appUserLoginDTO;
import com.securebank.securenank.Service.LoginService;
import com.securebank.securenank.Utils.JsonResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService  loginService){
        this.loginService =  loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody appUserLoginDTO user){
        String token = loginService.login(user);
        return JsonResponse.sendJsonSuccessResponse(token);
    }
}
