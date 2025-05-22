package com.example.LeirskolePortalen.repository;


import com.example.LeirskolePortalen.model.Innkvarering;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InnkvareringRepository extends JpaRepository<Innkvarering, Long> {
    List<Innkvarering> findByHytte_LeirId(Long leirId);
}
