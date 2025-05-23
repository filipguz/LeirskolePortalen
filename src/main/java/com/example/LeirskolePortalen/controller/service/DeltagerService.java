package com.example.LeirskolePortalen.controller.service;

import com.example.LeirskolePortalen.model.Deltaker;
import com.example.LeirskolePortalen.model.Leir;
import com.example.LeirskolePortalen.repository.DeltakerRepository;
import com.example.LeirskolePortalen.repository.LeirRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class DeltagerService {

    private final LeirRepository leirRepository;
    private final DeltakerRepository deltakerRepository;

    @Autowired
    public DeltagerService(LeirRepository leirRepository, DeltakerRepository deltakerRepository) {
        this.leirRepository = leirRepository;
        this.deltakerRepository = deltakerRepository;
    }

    public void lesFraExcel(MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Hopper over header

                String navn = row.getCell(0).getStringCellValue();
                Long leirId = (long) row.getCell(1).getNumericCellValue();

                Deltaker d = new Deltaker();
                d.setNavn(navn);
                d.setLeir(leirRepository.findById(leirId).orElse(null));
                deltakerRepository.save(d);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
