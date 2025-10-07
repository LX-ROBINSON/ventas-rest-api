package org.merariway.saleswarehouse.dao.auth;

import org.merariway.saleswarehouse.entity.UserEntity;

public interface AuthFirebaseRepository {

    void registerEmail(UserEntity user);
}
