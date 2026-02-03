package com.transverify.transactions.services;

import com.transverify.transactions.domain.entities.User;
import com.transverify.transactions.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> findByIBAN(String IBAN) {
        return userRepository.findByIBAN(IBAN);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

}
