package com.rdjaramillo.service.impl;

import com.rdjaramillo.exception.ModelNotFoundException;
import com.rdjaramillo.repository.IGenericRepository;
import com.rdjaramillo.service.ICRUD;

import java.lang.reflect.Method;
import java.util.List;

public abstract class CRUDImpl<T,ID> implements ICRUD<T,ID>{

    // Repositorio que sea dinamico, segun quien lo invoque
    // pueda comportace de diferentes formas (POLIMORFISMO).
    // Puedo crear un metodo abstracto que segun quien lo implemente
    // devulve una instancia segun lo corresponda.
    protected  abstract IGenericRepository<T,ID> getRepository();
    @Override
    public T save(T t) {
        return getRepository().save(t);
    }

    @Override
    public T update(T t, ID id) throws  Exception {
        Class<?> clazz = t.getClass();
        String className = t.getClass().getSimpleName();
        String methodName = "setId" + className;  //genera el setIdPatient, setIdExam, etc
        Method setIdMethod = clazz.getMethod(methodName, id.getClass());
        setIdMethod.invoke(t, id);

        getRepository()
                .findById(id)
                .orElseThrow(()->new ModelNotFoundException(("ID NOT FOUND" +id)));
        return getRepository().save(t);
    }

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    public T findById(ID id) {
        return getRepository()
                .findById(id)
                .orElseThrow(()-> new ModelNotFoundException("ID NOT FOUND: " + id));

    }

    @Override
    public void delete(ID id) {
        getRepository()
                .findById(id)
                .orElseThrow(()->new ModelNotFoundException(("ID NOT FOUND" +id)));
        getRepository().deleteById(id);

    }
}
