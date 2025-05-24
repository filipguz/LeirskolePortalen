package com.example.LeirskolePortalen.service;

import com.example.LeirskolePortalen.model.Bruker;
import com.example.LeirskolePortalen.repository.BrukerRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

    @Service
    public class BrukerDetaljerService implements UserDetailsService {
        private final BrukerRepository repo;

        public BrukerDetaljerService(BrukerRepository repo) {
            this.repo = repo;
        }

        @Override
        public UserDetails loadUserByUsername(String brukernavn) throws UsernameNotFoundException {
            Bruker b = repo.findByBrukernavn(brukernavn)
                    .orElseThrow(() -> new UsernameNotFoundException("Ikke funnet"));
            return User.builder()
                    .username(b.getBrukernavn())
                    .password(b.getPassord()) // må være kryptert!
                    .roles(b.getRolle().name())
                    .build();
        }
    }



