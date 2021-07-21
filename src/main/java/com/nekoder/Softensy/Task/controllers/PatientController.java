package com.nekoder.Softensy.Task.controllers;

import com.nekoder.Softensy.Task.exceptions.ObjectIsNotExistsException;
import com.nekoder.Softensy.Task.models.Patient;
import com.nekoder.Softensy.Task.repositories.PatientRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {
    private final PatientRepository repository;

    public PatientController(PatientRepository patientRepository) {
        this.repository = patientRepository;
    }

    @GetMapping("/patient/getAllPatients")
    List<Patient> getAllPatients(){
        return repository.findAll();
    }

    @GetMapping("/patient/getPatient/{id}")
    Patient getPatient(@PathVariable Long id){
        return repository.findById(id).orElseThrow(()->new ObjectIsNotExistsException(id));
    }

    @PostMapping("/patient/addPatient")
    Patient addPatient(@RequestBody Patient newPatient){
        if(newPatient.getId()!=null&&repository.findById(newPatient.getId()).isPresent())
            return null;
        return repository.save(newPatient);
    }

    @PutMapping("/patient/edit/{id}")
    Patient editPatient(@RequestBody Patient newPatient, @PathVariable Long id) {

        return repository.findById(id)
                .map(patient -> {
                    patient.setFirstName(newPatient.getFirstName());
                    patient.setLastName(newPatient.getLastName());
                    patient.setPatronymic(newPatient.getPatronymic());
                    patient.setBirthDay(newPatient.getBirthDay());
                    patient.setPhone(newPatient.getPhone());
                    patient.setDoctor(newPatient.getDoctor());
                    return repository.save(patient);
                })
                .orElseGet(() -> {
                    newPatient.setId(id);
                    return repository.save(newPatient);
                });
    }

    @DeleteMapping("/patient/{id}")
    void deletePatient(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
