package com.example.LeirskolePortalen.service;

import com.example.LeirskolePortalen.repository.DeltakerRepository;
import com.example.LeirskolePortalen.repository.LeirRepository;
import org.springframework.stereotype.Service;

@Service
public class DeltagerService {

    private final LeirRepository leirRepository;
    private final DeltakerRepository deltakerRepository;

    public DeltagerService(LeirRepository leirRepository, DeltakerRepository deltakerRepository) {
        this.leirRepository = leirRepository;
        this.deltakerRepository = deltakerRepository;
    }

    // Excel-import er midlertidig deaktivert
    // Du kan aktivere igjen senere og bruke leirRepository trygt
}
