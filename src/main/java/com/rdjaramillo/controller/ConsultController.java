package com.rdjaramillo.controller;

import com.rdjaramillo.dto.ConsultDTO;
import com.rdjaramillo.dto.ConsultListExamDTO;
import com.rdjaramillo.model.Consult;
import com.rdjaramillo.model.Exam;
import com.rdjaramillo.service.IConsultService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/consult")
@RequiredArgsConstructor
public class ConsultController {

    //@Autowired
    private final IConsultService service;

    @Qualifier("consultMapper")
    private final ModelMapper mapper;
    @GetMapping()
    public ResponseEntity<List<ConsultDTO>> findAll(){
        List<ConsultDTO>  lst = service.findAll().stream().map(this::convertToDto).toList();
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultDTO> findById(@PathVariable("id")Integer id){
        Consult obj = service.findById(id);
        return  new ResponseEntity<>(convertToDto(obj), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ConsultDTO> save(@RequestBody ConsultListExamDTO dto){
        Consult cons = this.convertToEntity(dto.getConsult());
        //List<Exam> exams = dto.getLstExam().stream().map(e -> mapper.map(e, Exam.class)).toList();
        List<Exam> exams = mapper.map(dto.getLstExam(), new TypeToken<List<Exam>>(){}.getType());

        Consult obj = service.saveTransactional(cons, exams);
        //localhost:8080/consult/3
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdConsult()).toUri();
        return ResponseEntity.created(location).build();//.body(obj)
    }


    @PutMapping("/{id}")
    public ResponseEntity<ConsultDTO> update(@PathVariable("id") Integer id, @RequestBody ConsultDTO dto){

        Consult obj=  service.update(mapper.map(dto, Consult.class) ,id);
        return new ResponseEntity<>(convertToDto(obj),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete(@PathVariable("id") Integer id){

        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    private ConsultDTO convertToDto(Consult obj){
        return mapper.map(obj, ConsultDTO.class);
    }

    private Consult convertToEntity(ConsultDTO dto){
        return mapper.map(dto, Consult.class);
    }
}
