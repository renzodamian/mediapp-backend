package com.rdjaramillo.controller;

import com.rdjaramillo.dto.ExamDTO;
import com.rdjaramillo.model.Exam;
import com.rdjaramillo.service.IExamService;
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
@RequestMapping("/exam")
@RequiredArgsConstructor
public class ExamController {

    //@Autowired
    private final IExamService service;

    @Qualifier("defaulMapper")
    private final ModelMapper mapper;
    @GetMapping()
    public ResponseEntity<List<ExamDTO>> findAll(){
        List<ExamDTO>  lst = service.findAll().stream().map(this::convertToDto).toList();
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamDTO> findById(@PathVariable("id")Integer id){
        Exam obj = service.findById(id);
        return  new ResponseEntity<>(convertToDto(obj), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ExamDTO> save(@RequestBody ExamDTO dto){
        Exam obj = service.save(convertToEntity(dto));
        //localhost:8080/exam/3
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdExam()).toUri();
        return ResponseEntity.created(location).build();//.body(obj)
    }


    @PutMapping("/{id}")
    public ResponseEntity<ExamDTO> update(@PathVariable("id") Integer id, @RequestBody ExamDTO dto) throws Exception {
        Exam obj = service.update(convertToEntity(dto), id);
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.OK);
        /*Exam obj=  service.update(mapper.map(dto, Exam.class) ,id);
        return new ResponseEntity<>(convertToDto(obj),HttpStatus.OK);*/
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete(@PathVariable("id") Integer id){

        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    private ExamDTO convertToDto(Exam obj){
        return mapper.map(obj, ExamDTO.class);
    }

    private Exam convertToEntity(ExamDTO dto){
        return mapper.map(dto, Exam.class);
    }
}
