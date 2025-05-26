package com.example.LeirskolePortalen.repository;

import com.example.LeirskolePortalen.model.Deltaker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeltakerRepository extends JpaRepository<Deltaker, Long> {
    long countByHytteIsNull(); // hvis du har denne fra før

    List<Deltaker> findByLeirId(Long leirId); // legg til denne
}