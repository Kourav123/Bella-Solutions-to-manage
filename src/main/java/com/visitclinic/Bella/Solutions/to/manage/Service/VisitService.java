package com.visitclinic.Bella.Solutions.to.manage.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visitclinic.Bella.Solutions.to.manage.Entity.Visit;
import com.visitclinic.Bella.Solutions.to.manage.Repository.VisitRepository;

@Service
public class VisitService {
    @Autowired
    private VisitRepository visitRepository;

    public List<Visit> getAllVisits() {
        return visitRepository.findAll();
    }

    public void saveVisit(Visit visit) {
        visitRepository.save(visit);
    }

  
}