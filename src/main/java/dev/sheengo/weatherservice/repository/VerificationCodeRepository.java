package dev.sheengo.weatherservice.repository;

import dev.sheengo.weatherservice.domains.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {
    @Query("SELECT v FROM VerificationCode v WHERE v.email = :email AND v.deleted = false")
    List<VerificationCode> findAllByEmailAndDeleted(String email);

    @Query("SELECT v FROM VerificationCode v WHERE v.code = :activationCode")
    Optional<VerificationCode> findByCode(String activationCode);

    @Query("SELECT v FROM VerificationCode v WHERE v.email = :email")
    List<VerificationCode> findAllByEmail(String email);
}