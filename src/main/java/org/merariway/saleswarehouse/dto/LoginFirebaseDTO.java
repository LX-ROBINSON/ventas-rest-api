package org.merariway.saleswarehouse.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginFirebaseDTO(@NotBlank(message = "El UID no puede estar vacio") String uid,
                               @Email(message = "El formato del correo es incorrecto") String email,
                               @NotBlank(message = "El nombre de usuario es obligatorio") String displayName) {
}
