package com.rdjaramillo.controller;

import com.rdjaramillo.model.Patient;
import com.rdjaramillo.service.IPatienService;
import com.rdjaramillo.service.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    IPatienService patientService;

    @GetMapping("/all")
    public String sayHelloREST(){
        Patient p = new Patient(1,"Renzo");
        return patientService.sayHelloLogic(p);
    }
}
