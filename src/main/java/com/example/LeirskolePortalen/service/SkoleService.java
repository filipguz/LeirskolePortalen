package com.example.LeirskolePortalen.service;

import com.example.LeirskolePortalen.model.Skole;
import com.example.LeirskolePortalen.repository.SkoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkoleService {

    @Autowired
    private SkoleRepository skoleRepository;

    public void save(Skole skole) {
        skoleRepository.save(skole);
    }

    public List<Skole> finnAlle() {
        return skoleRepository.findAll();
    }
}
