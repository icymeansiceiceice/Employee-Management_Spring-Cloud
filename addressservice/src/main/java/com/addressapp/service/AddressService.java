package com.addressapp.service;



import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.addressapp.entity.Address;
import com.addressapp.repo.AddressRepo;
import com.addressapp.response.AddressResponse;

@Service
public class AddressService {
    

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private ModelMapper modelMapper;

    public AddressResponse getAddressByUserID(int id){
        Address address = addressRepo.findAddressbyEmployeeId(id).get(0);
        AddressResponse res = modelMapper.map(address, AddressResponse.class);
        return res;
    }

    public List<AddressResponse> getAllAddress(){
       List<Address> addresssList = addressRepo.findAll();
       List<AddressResponse> addressTesponseList = Arrays.asList(modelMapper.map(addresssList,AddressResponse[].class));
        return addressTesponseList;
    }


}
