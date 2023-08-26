package com.customer.service.impl;

import com.customer.dao.CustomerProfileRepository;
import com.customer.entity.CustomerProfileEntity;
import com.customer.model.CustomerDetails;
import com.customer.service.CustomerProfileService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;


@Service
public class CustomerProfileServiceImpl implements CustomerProfileService {


    private final CustomerProfileRepository customerProfileRepository;

    public CustomerProfileServiceImpl(CustomerProfileRepository customerProfileRepository) {
        this.customerProfileRepository = customerProfileRepository;
    }

    @Override
    public void saveCustomerProfile(CustomerDetails customerDetails, HttpHeaders headers) throws Exception {

    }

    @Override
    public CustomerProfileEntity findCustomerProfileByEmailId(String emailId) throws Exception {
        CustomerProfileEntity customerProfileEntityMono
                = customerProfileRepository.findById(emailId).orElseThrow(
                () -> new RuntimeException(String.format("User id %s not found", emailId)));
        return customerProfileEntityMono;
    }
}
