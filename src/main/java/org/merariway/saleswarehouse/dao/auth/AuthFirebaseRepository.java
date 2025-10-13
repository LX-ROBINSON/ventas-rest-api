package org.merariway.saleswarehouse.dao.auth;

import org.merariway.saleswarehouse.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuthFirebaseRepository extends JpaRepository<UserEntity, Integer> {

    @Query(value = "SELECT * FROM user WHERE uid_firebase=?1", nativeQuery = true)
    Optional<UserEntity> findUserByUID(String uid);
}
