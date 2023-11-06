package com.rdjaramillo.service;

import com.rdjaramillo.model.Patient;

import java.util.List;

public interface IPatientService extends ICRUD <Patient, Integer> {

    List<Patient> getUCIPatients();


}
