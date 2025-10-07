package org.merariway.saleswarehouse.service.auth;

import org.merariway.saleswarehouse.dao.auth.AuthFirebaseRepository;
import org.merariway.saleswarehouse.dto.LoginFirebaseDTO;
import org.merariway.saleswarehouse.entity.RoleEntity;
import org.merariway.saleswarehouse.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthFirebaseService {

    private final AuthFirebaseRepository repoAuth;

    public AuthFirebaseService(AuthFirebaseRepository repoAuth) {
        this.repoAuth = repoAuth;
    }

    public void registerEmail(LoginFirebaseDTO loginDTO) {
        UserEntity user = mappingEntity(loginDTO);
        user.setRole(new RoleEntity());

        repoAuth.registerEmail(user);
    }

    private UserEntity mappingEntity(LoginFirebaseDTO loginDTO) {
        UserEntity mapping = new UserEntity();
        mapping.setUidFirebase(loginDTO.uid());
        mapping.setEmail(loginDTO.email());
        mapping.setDisplayName(loginDTO.displayName());

        return mapping;
    }
}
