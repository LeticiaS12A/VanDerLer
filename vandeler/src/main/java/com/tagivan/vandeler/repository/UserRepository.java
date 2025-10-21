package com.tagivan.vandeler.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tagivan.vandeler.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Usado no login (JWT)
    Optional<User> findByEmail(String email);

    // Verifica se já existe um email (para impedir cadastros duplicados)
    boolean existsByEmail(String email);

    // Verifica se existe outro usuário com o mesmo email (para PUT/PATCH)
    @Query("SELECT COUNT(u) > 0 " +
           "FROM User u " +
           "WHERE u.id <> :id " +
           "AND (:email IS NOT NULL AND u.email = :email)")
    boolean existsByEmail(@Param("email") String email, @Param("id") Long id);
}