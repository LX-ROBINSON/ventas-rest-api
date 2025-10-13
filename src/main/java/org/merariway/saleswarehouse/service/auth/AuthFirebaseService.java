package org.merariway.saleswarehouse.service.auth;

import org.merariway.saleswarehouse.dto.LoginFirebaseDTO;
import org.merariway.saleswarehouse.entity.UserEntity;

import java.util.Optional;

public interface AuthFirebaseService {

    void registerEmail(LoginFirebaseDTO loginDTO);

    UserEntity findUserByUID(LoginFirebaseDTO loginDTO);
}
