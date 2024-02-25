package com.employeeapp.openfeignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.employeeapp.response.AddressResponse;


@FeignClient(name = "ADDRESS-APP",path = "/address-app/api/")
public interface AddressClient {
    
    @GetMapping("/address/{employeeId}")
    public ResponseEntity<AddressResponse> getAddressByID(@PathVariable("employeeId") int id);

    @GetMapping("/addresses")    
    public ResponseEntity<List<AddressResponse>> getAllAddress();
}
