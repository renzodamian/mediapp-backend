package com.rdjaramillo.controller;

import com.rdjaramillo.dto.PatientDTO;
import com.rdjaramillo.model.Patient;
import com.rdjaramillo.service.IPatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
//@CrossOrigin(origins="http://localhost:4200")
public class PatientController {

    //@Autowired
    private final IPatientService service;

    @Qualifier("defaulMapper")
    private final ModelMapper mapper;

    @GetMapping()
    public ResponseEntity<List<PatientDTO>> findAll(){
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
    public ResponseEntity<PatientDTO> save(@Valid @RequestBody PatientDTO dto){
        Patient obj = service.save(convertToEntity(dto));
        //localhost:8080/patient/3
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdPatient()).toUri();
        return ResponseEntity.created(location).build();//.body(obj)
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> update(@Valid  @PathVariable("id") Integer id, @RequestBody PatientDTO dto) throws Exception {
        dto.setIdPatient(id); //SE DEBE AGREGAR ESTO
        Patient obj = service.update(convertToEntity(dto), id);
        return new ResponseEntity<>(convertToDto(obj),HttpStatus.OK);
        /*Patient obj=  service.update(mapper.map(dto,Patient.class) ,id);
        return new ResponseEntity<>(convertToDto(obj),HttpStatus.OK);*/
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<PatientDTO>findByIdHateoas(@PathVariable("id") Integer id){
        EntityModel<PatientDTO> resource = EntityModel.of(convertToDto(service.findById(id)));
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));

        resource.add(link1.withRel("patien- info"));
        return  resource;
    }

    private PatientDTO convertToDto(Patient obj){
        return mapper.map(obj, PatientDTO.class);
    }

    private Patient convertToEntity(PatientDTO dto){
        return mapper.map(dto, Patient.class);
    }
}
