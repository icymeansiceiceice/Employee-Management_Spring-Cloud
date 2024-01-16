package com.employeeapp.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.employeeapp.entity.Employee;
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
    private RestTemplate restTemplate;


    public EmployeeResponse getEmployeeByID(int id){
        Employee employee = employeeRepo.findById(id).get();
        EmployeeResponse res = modelMapper.map(employee, EmployeeResponse.class);
        AddressResponse addressResponse = restTemplate.getForObject("http://localhost:8080/address/{id}", AddressResponse.class, id);
        res.setAddressResponse(addressResponse);
        return res;
    }
}
