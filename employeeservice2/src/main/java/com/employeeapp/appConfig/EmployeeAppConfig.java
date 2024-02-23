package com.employeeapp.appConfig;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeAppConfig {
    
    @Value("${addressservice.base.url}") 
    private String addressBaseUrl;

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }



}
