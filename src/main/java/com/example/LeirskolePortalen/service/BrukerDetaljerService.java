package com.example.LeirskolePortalen.service;


import com.example.LeirskolePortalen.model.Bruker;
import com.example.LeirskolePortalen.repository.BrukerRepository;
import com.example.LeirskolePortalen.security.BrukerUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BrukerDetaljerService implements UserDetailsService {

    @Autowired
    private BrukerRepository brukerRepo;

    @Override
    public UserDetails loadUserByUsername(String brukernavn) throws UsernameNotFoundException {
        Bruker bruker = brukerRepo.findByBrukernavn(brukernavn)
                .orElseThrow(() -> new UsernameNotFoundException("Bruker ikke funnet"));

        return new BrukerUserDetails(bruker); // 👈 bruker din egen klasse
    }
}