package com.rdjaramillo.service.impl;

import com.rdjaramillo.model.Patient;
import com.rdjaramillo.repository.IGenericRepository;
import com.rdjaramillo.repository.IPatientRepository;
import com.rdjaramillo.repository.PatientRepositoryImpl;
import com.rdjaramillo.service.IPatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PatientServiceImpl extends CRUDImpl<Patient, Integer> implements IPatientService {


   //@Autowired
    private final IPatientRepository repository;


    @Override
    protected IGenericRepository<Patient, Integer> getRepository() {
        return repository;
    }

    @Override
    public List<Patient> getUCIPatients() {
        return null;
    }
}
