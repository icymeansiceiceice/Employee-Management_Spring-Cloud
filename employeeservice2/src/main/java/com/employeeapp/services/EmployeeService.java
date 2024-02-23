package com.employeeapp.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.employeeapp.entity.Employee;
import com.employeeapp.feignclient.AddressClient;
import com.employeeapp.repo.EmployeeRepo;
import com.employeeapp.response.AddressResponse;
import com.employeeapp.response.EmployeeResponse;

@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AddressClient addressClient;




    public EmployeeResponse getEmployeeByID(int id){
        Employee employee = employeeRepo.findById(id).get();
        EmployeeResponse res = modelMapper.map(employee, EmployeeResponse.class);
        ResponseEntity<AddressResponse> addressResponseEntity = addressClient.getAddressByEmployeeId(id);
        AddressResponse addressResponse = addressResponseEntity.getBody();
        res.setAddressResponse(addressResponse);
        return res;
    }



}
