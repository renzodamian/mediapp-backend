package com.rdjaramillo.controller;

import com.rdjaramillo.dto.MedicDTO;
import com.rdjaramillo.dto.PatientDTO;
import com.rdjaramillo.model.Medic;
import com.rdjaramillo.model.Patient;
import com.rdjaramillo.service.IMedicService;
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
@RequestMapping("/medic")
@RequiredArgsConstructor
public class MedicController {

    //@Autowired
    private final IMedicService service;

    @Qualifier("medicMapper")
    private final ModelMapper mapper;
    @GetMapping()
    public ResponseEntity<List<MedicDTO>> findAll(){
        List<MedicDTO>  lst = service.findAll().stream().map(this::convertToDto).toList();
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicDTO> findById(@PathVariable("id")Integer id){
        Medic obj = service.findById(id);
        return  new ResponseEntity<>(convertToDto(obj), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<MedicDTO> save(@RequestBody MedicDTO dto){
        Medic obj = service.save(convertToEntity(dto));
        //localhost:8080/medic/3
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdMedic()).toUri();
        return ResponseEntity.created(location).build();//.body(obj)
    }


    @PutMapping("/{id}")
    public ResponseEntity<MedicDTO> update(@PathVariable("id") Integer id, @RequestBody MedicDTO dto){

        Medic obj=  service.update(mapper.map(dto, Medic.class) ,id);
        return new ResponseEntity<>(convertToDto(obj),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete(@PathVariable("id") Integer id){

        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    private MedicDTO convertToDto(Medic obj){
        return mapper.map(obj, MedicDTO.class);
    }

    private Medic convertToEntity(MedicDTO dto){
        return mapper.map(dto, Medic.class);
    }
}
