package org.lesson.projectwork.security;

import org.lesson.projectwork.model.MuseumUser;
import org.lesson.projectwork.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class DatabaseUserDetails implements UserDetails {
    private final Integer id;
    private final String username;
    private final String password;
    private final Set<GrantedAuthority> authorities;

    public DatabaseUserDetails(MuseumUser museumUser) {
        this.id = museumUser.getId();
        this.username = museumUser.getEmail();
        this.password = museumUser.getPassword();
        authorities= new HashSet<>();
        for (Role role: museumUser.getRoleSet()){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
    }



    @Override
    public String getUsername() {
        return username;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
