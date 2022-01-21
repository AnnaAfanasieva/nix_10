package ua.com.alevel.config.security.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import ua.com.alevel.config.security.SecurityService;

@Service
public class SecurityServiceImpl implements SecurityService {

    //TODO доделать
    private final AuthenticationManager authenticationManager;

    public SecurityServiceImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public boolean isAuthenticated() {
        return false;
    }

    @Override
    public void autoLogin(String username, String password) {

    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }
}
