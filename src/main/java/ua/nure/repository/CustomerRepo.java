package ua.nure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ua.nure.entity.Customer;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    Optional<Customer> findOneByEmail(String email);

    boolean existsByEmail(String email);

    @Modifying
    @Query("UPDATE Customer SET password = ?1  WHERE id = ?2")
    void updateForPassword(String pass, Integer id);
}
