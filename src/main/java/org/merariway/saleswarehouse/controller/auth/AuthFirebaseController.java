package org.merariway.saleswarehouse.controller.auth;

import org.merariway.saleswarehouse.dto.LoginFirebaseDTO;
import org.merariway.saleswarehouse.service.auth.AuthFirebaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthFirebaseController {

    private final AuthFirebaseService authService;


    public AuthFirebaseController(AuthFirebaseService authService) {
        this.authService = authService;
    }

    @PostMapping("/registerEmail")
    public ResponseEntity<String> registerEmail(@RequestBody LoginFirebaseDTO loginFirebaseDTO) {
        authService.registerEmail(loginFirebaseDTO);

        return new ResponseEntity<>(
                "Correo creado e iniciado correctamente",
                HttpStatus.OK
        );
    }
}
