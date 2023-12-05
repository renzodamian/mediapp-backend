package com.rdjaramillo.service.impl;

import com.rdjaramillo.model.Consult;
import com.rdjaramillo.model.Exam;
import com.rdjaramillo.repository.IConsultExamRepo;
import com.rdjaramillo.repository.IGenericRepository;
import com.rdjaramillo.repository.IConsultRepository;
import com.rdjaramillo.service.IConsultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ConsultServiceImpl extends CRUDImpl<Consult, Integer> implements IConsultService {


   //@Autowired
    private final IConsultRepository consultRepository;
    private final IConsultExamRepo ceRepo;


    @Override
    protected IGenericRepository<Consult, Integer> getRepository() {

        return consultRepository;
    }

    @Transactional
    @Override
    public Consult saveTransactional(Consult consult, List<Exam> exams) {
        consultRepository.save(consult);//Guardado Maestro Detalle
        exams.forEach(exam -> ceRepo.saveExam(consult.getIdConsult(), exam.getIdExam()));
        return consult;
    }

    @Override
    public List<Consult> search(String dni, String fullname) {
        return consultRepository.search(dni, fullname);
    }

    @Override
    public List<Consult> searchByDates(LocalDateTime date1, LocalDateTime date2) {
        final int OFFSET_DAYS =1;//Add on day
        return consultRepository.searchByDates(date1, date2.plusDays(OFFSET_DAYS));
    }
}
