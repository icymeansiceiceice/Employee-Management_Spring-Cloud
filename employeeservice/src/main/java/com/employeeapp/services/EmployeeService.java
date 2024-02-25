package com.employeeapp.services;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.employeeapp.entity.Employee;
import com.employeeapp.openfeignclient.AddressClient;
import com.employeeapp.repo.EmployeeRepo;
import com.employeeapp.response.AddressResponse;
import com.employeeapp.response.EmployeeResponse;

@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;

    // @Autowired
    // private WebClient webClient;

    @Autowired
    private RestTemplate restTemplate;

  //  @Autowired
  //  private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private AddressClient addressClient;

    // public EmployeeService(RestTemplateBuilder restTemplateBuilder,@Value("${addressservice.base.url}") String addressBaseUrl){
    //     this.restTemplate = restTemplateBuilder.rootUri(addressBaseUrl).build();
    // }

    public List<EmployeeResponse>getAllEmployee(){
        List<Employee> employeeList = employeeRepo.findAll();
        List<EmployeeResponse> employeeResponseList = Arrays.asList(modelMapper.map(employeeList,EmployeeResponse[].class));

        ResponseEntity<List<AddressResponse>> allAddress = addressClient.getAllAddress();
        List<AddressResponse> addressResponse = allAddress.getBody();

        employeeResponseList.forEach(employee->{
            for(AddressResponse addressRes : addressResponse){
                if(addressRes.getId() == employee.getId()){
                    employee.setAddressResponse(addressRes);
                }
            }
        });
         return employeeResponseList;
    }

    public EmployeeResponse getEmployeeByID(int id){
        Employee employee = employeeRepo.findById(id).get();
        EmployeeResponse res = modelMapper.map(employee, EmployeeResponse.class);
       
       // AddressResponse addressResponse = callingAddressResponseUsingWebClient(id);
    //    AddressResponse addressResponse = callingAddressResponseUsingRestTemplate(id);
    AddressResponse addressResponse = addressClient.getAddressByID(id).getBody();
        res.setAddressResponse(addressResponse);
        return res;
    }

 //   sync
    private AddressResponse callingAddressResponseUsingRestTemplate(int id){
        // List<ServiceInstance> instances = discoveryClient.getInstances("address-app");
        // ServiceInstance serviceInstance = instances.get(0);
        // String uri = serviceInstance.getUri().toString();

        ServiceInstance serviceInstance = loadBalancerClient.choose("address-app");
        String uri = serviceInstance.getUri().toString();
        String contextRoot = serviceInstance.getMetadata().get("configPath");

        System.out.println("this is uri >>>>>>>>>>>"+uri);


        // return restTemplate.getForObject(uri+"/address-app/api/address/{id}", AddressResponse.class, id);

        return restTemplate.getForObject("http://ADDRESS-APP/address-app/api/address/{id}", AddressResponse.class, id);
    }
    
    //async
    // private AddressResponse callingAddressResponseUsingWebClient(int id){
    //     return webClient.get().uri("/address/"+id).retrieve().bodyToMono(AddressResponse.class).block();
    // }

}
