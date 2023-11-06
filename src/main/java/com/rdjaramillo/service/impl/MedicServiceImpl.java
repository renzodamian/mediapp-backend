package com.rdjaramillo.service.impl;

import com.rdjaramillo.model.Medic;
import com.rdjaramillo.repository.IGenericRepository;
import com.rdjaramillo.repository.IMedicRepository;
import com.rdjaramillo.service.IMedicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MedicServiceImpl extends CRUDImpl<Medic, Integer> implements IMedicService {


   //@Autowired
    private final IMedicRepository repository;


    @Override
    protected IGenericRepository<Medic, Integer> getRepository() {

        return repository;
    }
}
