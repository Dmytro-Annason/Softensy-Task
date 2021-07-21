package com.nekoder.Softensy.Task.repositories;

import com.nekoder.Softensy.Task.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
}
