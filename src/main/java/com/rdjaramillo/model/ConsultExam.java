package com.rdjaramillo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(ConsultExamPK.class)
@Table(name = "tbl_consult_exam")
public class ConsultExam {

    @Id
    private Consult consult;

    @Id
    private Exam exam;


}
