package io.project.app.eventcare.repositories;


import io.project.app.eventcare.domain.PatientState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientStateJpaRepository extends JpaRepository<PatientState, Long> {
    
}
