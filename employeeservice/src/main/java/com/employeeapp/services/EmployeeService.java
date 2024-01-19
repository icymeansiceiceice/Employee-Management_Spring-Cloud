package com.employeeapp.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

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
    private WebClient webClient;

    //private RestTemplate restTemplate;

    // public EmployeeService(RestTemplateBuilder restTemplateBuilder,@Value("${addressservice.base.url}") String addressBaseUrl){
    //     this.restTemplate = restTemplateBuilder.rootUri(addressBaseUrl).build();
    // }



    public EmployeeResponse getEmployeeByID(int id){
        Employee employee = employeeRepo.findById(id).get();
        EmployeeResponse res = modelMapper.map(employee, EmployeeResponse.class);
        AddressResponse addressResponse = callingAddressResponseUsingWebClient(id);
        res.setAddressResponse(addressResponse);
        return res;
    }

    //sync
    // private AddressResponse callingAddressResponseUsingRestTemplate(int id){
    //     return restTemplate.getForObject("/address/{id}", AddressResponse.class, id);
    // }
    
    //async
    private AddressResponse callingAddressResponseUsingWebClient(int id){
        return webClient.get().uri("/address/"+id).retrieve().bodyToMono(AddressResponse.class).block();
    }

}
