package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Validated
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    Optional<User> findById(Long id);

    /**
     * работает как и specification потому что сочетание логики в операторах is null or =
     */
    @Query("SELECT u FROM User u WHERE (:name is null or u.name = :name) " +
            "and (:lastName is null or u.lastName = :lastName)" +
            "and (:surname is null or u.surname = :surname)" +
            "and (:phone is null or u.phoneNumber = :phone)" +
            "and (:email is null or u.email = :email)")
    User findUserByNameAndPhoneAndEmail(@Param("name") String name,
                                          @Param("lastName") String lastName,
                                          @Param("surname") String surname,
                                          @Param("phone") String phone,
                                          @Param("email") String email);
}
