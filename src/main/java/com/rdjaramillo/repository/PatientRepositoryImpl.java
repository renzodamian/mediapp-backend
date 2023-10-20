package com.rdjaramillo.repository;

import com.rdjaramillo.model.Patient;
import org.springframework.stereotype.Repository;

@Repository
public class PatientRepositoryImpl  implements IPatientRepository{

    @Override
    public String sayHello (Patient patient){
        return  "Hello " + patient.getFirstName() + " ";
    }
}
