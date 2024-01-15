package com.employeeapp.controller;

import org.springframework.web.bind.annotation.RestController;

import com.employeeapp.response.EmployeeResponse;
import com.employeeapp.services.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    
    
    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeDetails(@PathVariable("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployeeByID(id));
    }
    
  

}
