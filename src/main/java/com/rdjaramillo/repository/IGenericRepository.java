package com.rdjaramillo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
//Al momento que se complile la aplicacion no trate de generear un bean si no
// al momento de crear, la app genere quien lo necesite.
//Generico ideal para representar diferente tipos de datos T y el tipo de la llave primary ID
public interface IGenericRepository<T,ID> extends JpaRepository<T,ID>{
}
