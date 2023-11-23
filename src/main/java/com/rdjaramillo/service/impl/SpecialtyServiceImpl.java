package com.rdjaramillo.service.impl;

import com.rdjaramillo.model.Specialty;
import com.rdjaramillo.repository.ISpecialtyRepository;
import com.rdjaramillo.repository.IGenericRepository;
import com.rdjaramillo.service.ISpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SpecialtyServiceImpl extends CRUDImpl<Specialty, Integer> implements ISpecialtyService {


   //@Autowired
    private final ISpecialtyRepository repository;


    @Override
    protected IGenericRepository<Specialty, Integer> getRepository() {

        return repository;
    }
}
