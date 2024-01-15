package com.addressapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.addressapp.response.AddressResponse;

@RestController
public class AddressController {
    

    @GetMapping("/address/{employeeId}")
    public ResponseEntity<AddressResponse> getAddressByID(@PathVariable("employeeId") int id){
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }


}
