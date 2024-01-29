package org.lesson.projectwork.security;

import org.lesson.projectwork.model.MuseumUser;
import org.lesson.projectwork.repository.MuseumUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {
    @Autowired
    private MuseumUserRepository museumUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MuseumUser> museumUser=museumUserRepository.findByEmail(username);
        if (museumUser.isPresent()){
            return  new DatabaseUserDetails(museumUser.get());
        }
        else {
            throw new UsernameNotFoundException(username);
        }
    }
}
