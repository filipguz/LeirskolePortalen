package com.example.LeirskolePortalen.security;

import com.example.LeirskolePortalen.model.Bruker;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class BrukerUserDetails implements UserDetails {

    private final Bruker bruker;

    public BrukerUserDetails(Bruker bruker) {
        this.bruker = bruker;
    }

    public Bruker getBruker() {
        return bruker;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // eller legg til roller her
    }

    @Override
    public String getPassword() {
        return bruker.getPassord();
    }

    @Override
    public String getUsername() {
        return bruker.getBrukernavn();
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
