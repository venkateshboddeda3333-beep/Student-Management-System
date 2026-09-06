package com.example.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.studentmanagement.model.Passport;

public interface PassportRepository extends 
JpaRepository<Passport, Long> {

}