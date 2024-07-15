package com.example.Vaccination.service;

import com.example.Vaccination.dao.MainDao;
import com.example.Vaccination.dto.CustomerDto;
import com.example.Vaccination.dto.RecordDto;
import com.example.Vaccination.dto.VaccineDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
@Service
@RequiredArgsConstructor
public class MainService {

    private final MainDao dao;
    public List<CustomerDto> getAllCustomers() {
        return dao.getAllCustomers();
    }

    public void registVaccination(VaccineDto vaccination) {
        dao.insertVaccinateion(vaccination);
    }

    public List<RecordDto> getAllRecords() {
        return dao.getAllRecords();
    }

    public void getVaccines(Model model) {
        List<HashMap<String,Object>> dtos = dao.fetchVaccines();
        dtos.forEach(
                (dto)->{
                    dto.get("vaccineCode");
                    System.out.println(dto.toString());
                }
        );
        model.addAttribute("list",dtos);

    }
}
