package com.employeeapp.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.employeeapp.response.AddressResponse;

@FeignClient(name = "addressClient",url = "http://localhost:8081",path = "/address-app/api")
public interface AddressClient {
    

    @GetMapping("/address/{id}")
    public ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable("id") int id);
}
