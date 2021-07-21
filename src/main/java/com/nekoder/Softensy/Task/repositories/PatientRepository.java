package com.nekoder.Softensy.Task.repositories;

import com.nekoder.Softensy.Task.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {
}
