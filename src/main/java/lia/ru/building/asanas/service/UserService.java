package lia.ru.building.asanas.service;

import lia.ru.building.asanas.models.User;
import lia.ru.building.asanas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User currentUser = userRepository.findByUsername(username);

        if (currentUser == null)
            throw new UsernameNotFoundException("User not authorized.");
        else
            return currentUser;

    }

}
