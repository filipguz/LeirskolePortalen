package com.example.LeirskolePortalen.repository;

import com.example.LeirskolePortalen.model.Bruker;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BrukerRepository extends JpaRepository<Bruker, Long> {
    Optional<Bruker> findByBrukernavn(String brukernavn);
}
