package com.rdjaramillo.controller;

import com.rdjaramillo.dto.PatientDTO;
import com.rdjaramillo.model.Patient;
import com.rdjaramillo.service.IPatientService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {

    //@Autowired
    private final IPatientService service;
    private final ModelMapper mapper;

    @GetMapping()
    public ResponseEntity<List<PatientDTO>> findAll(){
        ModelMapper modelMapper = new ModelMapper();
        List<PatientDTO>  lst = service.findAll().stream().map(
                this::convertToDto).toList();
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> findById(@PathVariable("id")Integer id){
        Patient obj = service.findById(id);
        return  new ResponseEntity<>(convertToDto(obj), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<PatientDTO> save(@RequestBody PatientDTO dto){
        Patient obj = service.save(convertToEntity(dto));
        //localhost:8080/patient/3
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdPatient()).toUri();
        return ResponseEntity.created(location).build();//.body(obj)
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> update(@PathVariable("id") Integer id, @RequestBody PatientDTO dto){
        Patient obj=  service.update(mapper.map(dto,Patient.class) ,id);
        return new ResponseEntity<>(convertToDto(obj),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete(@PathVariable("id") Integer id){

        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private PatientDTO convertToDto(Patient obj){
        return mapper.map(obj, PatientDTO.class);
    }

    private Patient convertToEntity(PatientDTO dto){
        return mapper.map(dto, Patient.class);
    }
}
