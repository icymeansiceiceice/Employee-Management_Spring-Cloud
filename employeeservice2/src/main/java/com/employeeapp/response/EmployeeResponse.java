package com.employeeapp.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {

    private int id;
    private String name;
    private String email;
    private String bloodGroup;
    private AddressResponse addressResponse;
}
