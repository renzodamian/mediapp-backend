package com.rdjaramillo.controller;

import com.rdjaramillo.model.Medic;
import com.rdjaramillo.service.IMedicService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping()
    public ResponseEntity<List<Medic>> findAll(){
        List<Medic>  lst = service.findAll();
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medic> findById(@PathVariable("id")Integer id){
        Medic obj = service.findById(id);
        return  new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Medic> save(@RequestBody Medic medic){
        Medic obj = service.save(medic);
        //localhost:8080/medic/3
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdMedic()).toUri();
        return ResponseEntity.created(location).build();//.body(obj)
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medic> update(@PathVariable("id") Integer id, @RequestBody Medic medic){

        Medic obj=  service.update(medic ,id);
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete(@PathVariable("id") Integer id){

        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
