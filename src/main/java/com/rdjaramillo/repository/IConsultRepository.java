package com.rdjaramillo.repository;

import com.rdjaramillo.model.Consult;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;


public interface IConsultRepository extends IGenericRepository<Consult, Integer> {

    //Creamod un Metodo llamado search que va a recibir un dni y un nombre, que va a dar como resultado una list
    //Usamos un Query JPQL: la sintaxis por la cual trabaja Java para hacer consultas
   @Query("FROM Consult c " +
           "WHERE c.patient.dni = :dni OR " +
           "LOWER(c.patient.firstName) LIKE %:fullname%  " +
           "OR LOWER(c.patient.lastName) LIKE %:fullname%")
    List<Consult> search (@Param("dni") String dni,
                          @Param("fullname") String fullname);

   @Query("FROM Consult c " +
           "WHERE c.consultDate " +
           "BETWEEN :date1 AND :date2")
   List<Consult> searchByDates(
           @Param("date1")LocalDateTime date1,
           @Param("date2")LocalDateTime date2);
}
