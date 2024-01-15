package com.addressapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.addressapp.entity.Address; 

@Repository
public interface AddressRepo extends JpaRepository<Address,Integer> {
    
        @Query(value = "SELECT ea.lane1,ea.lane2,ea.state,ea.zip FROM testingcloud.address ea join testingcloud.employee e on e.id = ea.employee_id where ea.employee_id = :employeeId",nativeQuery = true)
        List<Address> findAddressbyEmployeeId(@Param("employeeId") String employeeId);

}
