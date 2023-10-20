package com.rdjaramillo.service;

import com.rdjaramillo.model.Patient;
import com.rdjaramillo.repository.IPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl  implements IPatienService{
    @Autowired
    IPatientRepository patientRepository;

    @Override
    public String sayHelloLogic (Patient patient){
        if(patient != null){
            return patientRepository.sayHello(patient);
        }else {
            return "There is not patient";
        }
    }
}
