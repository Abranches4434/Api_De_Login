package com.login.apiLogin.Service;

import com.login.apiLogin.Model.Login;
import com.login.apiLogin.Repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private LoginRepository loginRepository;

    @Value("${user.username}")
    private String username;

    @Value("${user.password}")
    private String password;

    public void createUser() {
        if (!exists()) {
            loginRepository.save(new Login(username, new BCryptPasswordEncoder().encode(password)));
        }
    }

    public boolean exists() {
        return loginRepository.existsByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Login login = loginRepository.findByUsername(username);

        if (login == null) {
            throw new UsernameNotFoundException("Este login não existe!");
        }

        return new User(login.getUsername(), login.getPassword(), new ArrayList<>());
    }

}
