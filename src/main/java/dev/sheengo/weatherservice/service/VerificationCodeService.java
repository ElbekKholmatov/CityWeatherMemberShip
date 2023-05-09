package dev.sheengo.weatherservice.service;

import dev.sheengo.weatherservice.domains.VerificationCode;
import dev.sheengo.weatherservice.repository.VerificationCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VerificationCodeService {
    private final VerificationCodeRepository verificationCodeRepository;

    public List<VerificationCode> findAllByEmailAndDeleted(String email) {
        return verificationCodeRepository.findAllByEmailAndDeleted(email);
    }

    public void save(VerificationCode verificationCode) {
        verificationCodeRepository.save(verificationCode);
    }

    public Optional<VerificationCode> findByCode(String activationCode) {
        return verificationCodeRepository.findByCode(activationCode);
    }
}
