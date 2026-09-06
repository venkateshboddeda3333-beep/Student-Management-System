package com.example.studentmanagement.controller;

import org.springframework.web.bind.annotation.*;
import com.example.studentmanagement.model.Passport;
import com.example.studentmanagement.service.PassportService;

@RestController
@RequestMapping("/passports")
public class PassportController {

    private final PassportService service;

    public PassportController(PassportService service) {
        this.service = service;
    }

    @PostMapping
    public Passport createPassport(@RequestBody Passport passport) {
        return service.createPassport(passport);
    }

    @GetMapping("/{id}")
    public Passport getPassport(@PathVariable Long id) {
        return service.getPassport(id);
    }
}