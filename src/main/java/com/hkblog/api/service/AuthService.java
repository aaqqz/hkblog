package com.hkblog.api.service;

import com.hkblog.api.domain.Session;
import com.hkblog.api.domain.User;
import com.hkblog.api.exception.InvalidSigninInformation;
import com.hkblog.api.repository.UserRepository;
import com.hkblog.api.request.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    @Transactional
    public Long signin(Login login) {
        User user = userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword())
                .orElseThrow(() -> new InvalidSigninInformation());
        Session session = user.addSession();

        return user.getId();
    }

}
