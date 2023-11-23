package com.rdjaramillo.controller;

import com.rdjaramillo.dto.SpecialtyDTO;
import com.rdjaramillo.model.Specialty;
import com.rdjaramillo.service.ISpecialtyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/specialty")
@RequiredArgsConstructor
public class SpecialtyController {

    //@Autowired
    private final ISpecialtyService service;

    @Qualifier("defaulMapper")
    private final ModelMapper mapper;
    @GetMapping()
    public ResponseEntity<List<SpecialtyDTO>> findAll(){
        List<SpecialtyDTO>  lst = service.findAll().stream().map(this::convertToDto).toList();
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialtyDTO> findById(@PathVariable("id")Integer id){
        Specialty obj = service.findById(id);
        return  new ResponseEntity<>(convertToDto(obj), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<SpecialtyDTO> save(@RequestBody SpecialtyDTO dto){
        Specialty obj = service.save(convertToEntity(dto));
        //localhost:8080/specialty/3
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdSpecialty()).toUri();
        return ResponseEntity.created(location).build();//.body(obj)
    }


    @PutMapping("/{id}")
    public ResponseEntity<SpecialtyDTO> update(@PathVariable("id") Integer id, @RequestBody SpecialtyDTO dto){

        Specialty obj=  service.update(mapper.map(dto, Specialty.class) ,id);
        return new ResponseEntity<>(convertToDto(obj),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete(@PathVariable("id") Integer id){

        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    private SpecialtyDTO convertToDto(Specialty obj){
        return mapper.map(obj, SpecialtyDTO.class);
    }

    private Specialty convertToEntity(SpecialtyDTO dto){
        return mapper.map(dto, Specialty.class);
    }
}
