package com.rdjaramillo.service;

import com.rdjaramillo.model.Consult;
import com.rdjaramillo.model.Exam;

import java.util.List;

public interface IConsultService extends ICRUD<Consult, Integer> {

    Consult saveTransactional(Consult consult, List<Exam> exams);
}
