package ru.dev.A1.A1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dev.A1.A1.data.UserRepository;
import ru.dev.A1.A1.models.User;

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {

    private UserRepository repo;
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public UserRepositoryUserDetailsService(UserRepository repo){
        this.repo = repo;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUsername(username);

        /*if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException(username + "not found");*/

        if(user == null)throw new UsernameNotFoundException(username + " not found");

        return user;
    }
}
