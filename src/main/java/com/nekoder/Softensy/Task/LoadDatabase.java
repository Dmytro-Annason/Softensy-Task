package com.nekoder.Softensy.Task;

import com.nekoder.Softensy.Task.models.Doctor;
import com.nekoder.Softensy.Task.models.Patient;
import com.nekoder.Softensy.Task.repositories.DoctorRepository;
import com.nekoder.Softensy.Task.repositories.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    final DoctorRepository doctorRepository;
    final PatientRepository patientRepository;

    private List<Doctor> doctors = new ArrayList<>();
    private List<Patient> patients = new ArrayList<>();

    public LoadDatabase(DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    @Bean
    CommandLineRunner initDatabase() {
        doctors = doctorRepository.findAll();

        return args -> {

            if (doctorRepository.findAll().size() == 0) {
                fillDoctors();
                for (Doctor doctor : doctors) {
                    log.info("Add doctor" + doctorRepository.save(doctor));
                }
            }
            if (patientRepository.findAll().size() == 0) {
                fillPatients();
                for (Patient patient : patients) {
                    log.info("Add patient" + patientRepository.save(patient));
                }
            }
        };
    }

    private void fillDoctors() {
        doctors.add(new Doctor("Doctor", "Aibolit", "Petrovich", "VetDoctor", new Date(), "+380669876543"));
        doctors.add(new Doctor("Nikolai", "Sobolev", "Ivanovich", "Pediatr", new Date(), "+380669876544"));
        doctors.add(new Doctor("Aleksey", "Popov", "Nikolayevich", "Lor", new Date(), "+380665676544"));
        doctors.add(new Doctor("Dmitriy", "Shevchenko", "Yuriyovich", "Intern", new Date(), "+380669878464"));
    }

    private void fillPatients() {
        patients.add(new Patient("Yula", "Petrenko", "Igorevna", new Date(), doctors.get(0), "+380994567839"));
        patients.add(new Patient("Mila", "Kovalchuk", "Viktorovna", new Date(), doctors.get(0), "+380992957839"));
        patients.add(new Patient("Ignat", "Petrushkin", "Vladislavovich", new Date(), doctors.get(0), "+380994567839"));
    }
}
