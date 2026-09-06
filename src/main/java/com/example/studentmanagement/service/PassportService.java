package com.example.studentmanagement.service;

import org.springframework.stereotype.Service;

import com.example.studentmanagement.model.Passport;
import com.example.studentmanagement.repository.PassportRepository;

@Service
public class PassportService {

    private final PassportRepository repository;

    public PassportService(PassportRepository repository) {
        this.repository = repository;
    }

    public Passport createPassport(Passport passport) {
        return repository.save(passport);
    }

    public Passport getPassport(Long id) {
        return repository.findById(id).orElse(null);
    }
}