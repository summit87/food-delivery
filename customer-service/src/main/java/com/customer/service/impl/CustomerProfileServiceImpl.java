package com.customer.service.impl;

import com.commons.customer.CustomerAddress;
import com.commons.customer.CustomerContact;
import com.commons.customer.CustomerProfile;
import com.commons.customer.Name;
import com.commons.utils.GenericBuilder;
import com.customer.dao.CustomerProfileRepository;
import com.customer.entity.CustomerProfileEntity;
import com.customer.exception.CustomerDuplicateRecord;
import com.customer.model.CustomerDetails;
import com.customer.service.CustomerProfileService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class CustomerProfileServiceImpl implements CustomerProfileService {


    private final CustomerProfileRepository customerProfileRepository;

    public CustomerProfileServiceImpl(CustomerProfileRepository customerProfileRepository) {
        this.customerProfileRepository = customerProfileRepository;
    }

    @Override
    public void saveCustomerProfile(CustomerDetails customerDetails, HttpHeaders headers) throws Exception {
        
        CustomerProfileEntity profileEntity = GenericBuilder.of(CustomerProfileEntity::new)
            .with(CustomerProfileEntity::setFirstName,
                customerDetails.getCustomerProfile().getName().getFirstName())
            .with(CustomerProfileEntity::setLastName,
                customerDetails.getCustomerProfile().getName().getLastName())
            .with(CustomerProfileEntity::setPassword,
                customerDetails.getUserSecrets().getPassword())
            .with(CustomerProfileEntity::setUserId, customerDetails.getUserSecrets().getUserId())
            .with(CustomerProfileEntity::setHouseNumber,
                customerDetails.getCustomerProfile().getCustomerAddress().getHouseNumber())
            .with(CustomerProfileEntity::setStreetName,
                customerDetails.getCustomerProfile().getCustomerAddress().getStreetName())
            .with(CustomerProfileEntity::setNearestLandMark,
                customerDetails.getCustomerProfile().getCustomerAddress().getNearestLandMark())
            .with(CustomerProfileEntity::setZipCode,
                customerDetails.getCustomerProfile().getCustomerAddress().getZipCode())
            .with(CustomerProfileEntity::setCountryCode,
                customerDetails.getCustomerProfile().getCustomerAddress().getCountryCode())
            .with(CustomerProfileEntity::setPrimaryMobileNumber,
                customerDetails.getCustomerProfile().getCustomerContact()
                    .getPrimaryMobileNumber())
            .with(CustomerProfileEntity::setSecondaryMobileNumber,
                customerDetails.getCustomerProfile().getCustomerContact()
                    .getSecondaryMobileNumber())
            
            .build();
        try {
            customerProfileRepository.save(profileEntity);
        } catch (DataIntegrityViolationException ex) {
            String constraintName = ((ConstraintViolationException) ex.getCause()).getConstraintName();
            throw new CustomerDuplicateRecord(constraintName);
        }
        log.info("User profile for user id {} created",
            customerDetails.getUserSecrets().getUserId());
    }

    @Override
    public CustomerDetails findCustomerProfileByEmailId(String emailId) throws Exception {
        CustomerProfileEntity profileEntity
                = customerProfileRepository.findById(emailId).orElseThrow(
                () -> new RuntimeException(String.format("User id %s not found", emailId)));
        return GenericBuilder.of(CustomerDetails::new)
            .with(CustomerDetails::setCustomerProfile, GenericBuilder.of(CustomerProfile::new)
                .with(CustomerProfile::setCustomerContact,
                    GenericBuilder.of(CustomerContact::new).build())
                .with(CustomerProfile::setCustomerAddress,
                    GenericBuilder.of(CustomerAddress::new).build())
                .with(CustomerProfile::setName, GenericBuilder.of(Name::new).build())
                .build())
            .build();
    }
}
