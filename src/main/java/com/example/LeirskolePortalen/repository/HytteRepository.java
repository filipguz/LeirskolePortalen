package com.example.LeirskolePortalen.repository;

import com.example.LeirskolePortalen.model.Hytte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HytteRepository extends JpaRepository<Hytte, Long> {
    List<Hytte> findByLeirId(Long leirId);
}
