package com.rdjaramillo.repository;

import com.rdjaramillo.model.ConsultExam;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IConsultExamRepo  extends IGenericRepository<ConsultExam, Integer> {
    //@Transactional
    @Modifying
    @Query(value = "INSERT INTO tbl_consult_exam(id_consult, id_exam) VALUES (:idConsult, :idExam)" , nativeQuery = true)
    Integer saveExam(@Param("idConsult") Integer idConsult, @Param("idExam") Integer idExam);
}

