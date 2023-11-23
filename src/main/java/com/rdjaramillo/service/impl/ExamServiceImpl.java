package com.rdjaramillo.service.impl;

import com.rdjaramillo.model.Exam;
import com.rdjaramillo.repository.IGenericRepository;
import com.rdjaramillo.repository.IExamRepository;
import com.rdjaramillo.service.IExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ExamServiceImpl extends CRUDImpl<Exam, Integer> implements IExamService {


   //@Autowired
    private final IExamRepository repository;


    @Override
    protected IGenericRepository<Exam, Integer> getRepository() {

        return repository;
    }
}
