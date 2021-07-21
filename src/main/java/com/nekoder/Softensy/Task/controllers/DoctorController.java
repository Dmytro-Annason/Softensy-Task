package com.nekoder.Softensy.Task.controllers;

import com.nekoder.Softensy.Task.exceptions.ObjectIsNotExistsException;
import com.nekoder.Softensy.Task.models.Doctor;
import com.nekoder.Softensy.Task.repositories.DoctorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoctorController {
    private final DoctorRepository repository;

    public DoctorController(DoctorRepository doctorRepository) {
        this.repository = doctorRepository;
    }

    @GetMapping("/doctor/getAllDoctors")
    List<Doctor> getAllDoctors(){
        return repository.findAll();
    }

    @GetMapping("/doctor/getDoctor/{id}")
    Doctor getDoctor(@PathVariable Long id){
        return repository.findById(id).orElseThrow(()->new ObjectIsNotExistsException(id));
    }

    @PostMapping("/doctor/addDoctor")
    Doctor addDoctor(@RequestBody Doctor newDoctor){
        if(newDoctor.getId()!=null&&repository.findById(newDoctor.getId()).isPresent())
            return null;
        return repository.save(newDoctor);
    }

    @PutMapping("/doctor/edit/{id}")
    Doctor editDoctor(@RequestBody Doctor newDoctor, @PathVariable Long id) {

        return repository.findById(id)
                .map(doctor -> {
                    doctor.setFirstName(newDoctor.getFirstName());
                    doctor.setLastName(newDoctor.getLastName());
                    doctor.setPatronymic(newDoctor.getPatronymic());
                    doctor.setBirthDay(newDoctor.getBirthDay());
                    doctor.setPhone(newDoctor.getPhone());
                    doctor.setPosition(newDoctor.getPosition());
                    return repository.save(doctor);
                })
                .orElseGet(() -> {
                    newDoctor.setId(id);
                    return repository.save(newDoctor);
                });
    }

    @DeleteMapping("/doctor/{id}")
    void deleteDoctor(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
