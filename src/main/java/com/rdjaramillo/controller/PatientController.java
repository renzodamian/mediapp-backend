package com.rdjaramillo.controller;

import com.rdjaramillo.model.Patient;
import com.rdjaramillo.service.IPatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {

    //@Autowired
    private final IPatientService service;

    @GetMapping()
    public ResponseEntity<List<Patient>> findAll(){
        List<Patient>  lst = service.findAll();
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> findById(@PathVariable("id")Integer id){
        Patient obj = service.findById(id);
        return  new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Patient> save(@RequestBody Patient patient){
        Patient obj = service.save(patient);
        //localhost:8080/patient/3
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdPatient()).toUri();
        return ResponseEntity.created(location).build();//.body(obj)
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> update(@PathVariable("id") Integer id, @RequestBody Patient patient){

        Patient obj=  service.update(patient ,id);
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete(@PathVariable("id") Integer id){

        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
