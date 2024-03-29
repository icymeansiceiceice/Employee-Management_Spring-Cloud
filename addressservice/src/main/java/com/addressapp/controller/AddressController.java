package com.addressapp.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.addressapp.response.AddressResponse;
import com.addressapp.service.AddressService;

@RestController
public class AddressController {
    
    @Autowired
    private AddressService addressService;


    @GetMapping("/addresses")    
    public ResponseEntity<List<AddressResponse>> getAllAddress(){
        List<AddressResponse> addresslist = null;
        return ResponseEntity.status(HttpStatus.OK).body(addresslist);
    }

    @GetMapping("/address/{employeeId}")
    public ResponseEntity<AddressResponse> getAddressByID(@PathVariable("employeeId") int id){
        return ResponseEntity.status(HttpStatus.OK).body(addressService.getAddressByUserID(id));
    }


}
