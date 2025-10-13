package org.merariway.saleswarehouse.controller.auth;

import jakarta.validation.Valid;
import org.merariway.saleswarehouse.dto.LoginFirebaseDTO;
import org.merariway.saleswarehouse.service.auth.AuthFirebaseService;
import org.merariway.saleswarehouse.service.auth.AuthFirebaseServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthFirebaseController {

    private final AuthFirebaseService authService;

    public AuthFirebaseController(AuthFirebaseServiceImpl authService) {
        this.authService = authService;
    }

    @PostMapping("/register-email")
    public ResponseEntity<String> registerEmail(@Valid @RequestBody LoginFirebaseDTO loginFirebaseDTO) {
        authService.registerEmail(loginFirebaseDTO);

        return new ResponseEntity<>(
                "El correo se creo correctamente",
                HttpStatus.OK
        );
    }

    @GetMapping("/test")
    public String test() {
        return "Tienes acceso";
    }
}
