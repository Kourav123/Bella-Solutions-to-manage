package com.visitclinic.Bella.Solutions.to.manage.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.visitclinic.Bella.Solutions.to.manage.Entity.Visit;
@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
    
}