package com.example.LeirskolePortalen.repository;

import com.example.LeirskolePortalen.model.Risiko;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RisikoRepository extends JpaRepository<Risiko, Long> {
}