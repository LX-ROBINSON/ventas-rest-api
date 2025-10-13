package org.merariway.saleswarehouse.service.auth;

import org.merariway.saleswarehouse.dao.auth.AuthFirebaseRepository;
import org.merariway.saleswarehouse.dto.LoginFirebaseDTO;
import org.merariway.saleswarehouse.entity.RoleEntity;
import org.merariway.saleswarehouse.entity.UserEntity;
import org.merariway.saleswarehouse.security.RolesEnum;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthFirebaseServiceImpl implements AuthFirebaseService {

    private final AuthFirebaseRepository repoAuth;

    public AuthFirebaseServiceImpl(AuthFirebaseRepository repoAuth) {
        this.repoAuth = repoAuth;
    }

    @Override
    public void registerEmail(LoginFirebaseDTO loginDTO) {
        RoleEntity role = new RoleEntity(RolesEnum.ROLE_CUSTOMER.getId(), RolesEnum.ROLE_CUSTOMER.name());

        UserEntity user = mappingEntity(loginDTO);
        user.setRole(role);

        repoAuth.save(user);
    }

    @Override
    public UserEntity findUserByUID(LoginFirebaseDTO loginDTO) {
        Optional<UserEntity> optionalUser = repoAuth.findUserByUID(loginDTO.uid());

        if (optionalUser.isEmpty()) {
            UserEntity user = mappingEntity(loginDTO);
            return repoAuth.save(user);
        }
        return optionalUser.get();
    }

    private UserEntity mappingEntity(LoginFirebaseDTO loginDTO) {
        UserEntity mapping = new UserEntity();
        mapping.setUidFirebase(loginDTO.uid());
        mapping.setEmail(loginDTO.email());
        mapping.setDisplayName(loginDTO.displayName());

        return mapping;
    }
}
