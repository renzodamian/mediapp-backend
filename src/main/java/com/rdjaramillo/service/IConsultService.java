package com.rdjaramillo.service;

import com.rdjaramillo.model.Consult;
import com.rdjaramillo.model.Exam;

import java.time.LocalDateTime;
import java.util.List;

public interface IConsultService extends ICRUD<Consult, Integer> {

    Consult saveTransactional(Consult consult, List<Exam> exams);

    //Lista de consultas
    List<Consult> search (String dni, String fullname);

    List<Consult> searchByDates (LocalDateTime date1, LocalDateTime date2);

}
