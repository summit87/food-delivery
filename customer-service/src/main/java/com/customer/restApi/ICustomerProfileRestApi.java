package com.customer.restApi;

import com.customer.model.CustomerDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import static com.customer.utils.Constants.RestApiConstants.CREATE_CUSTOMER_PROFILE;
import static com.customer.utils.Constants.RestApiConstants.CUSTOMER_PROFILE_MAPPING;


@RestController
@RequestMapping(name = CUSTOMER_PROFILE_MAPPING)
public interface ICustomerProfileRestApi {

    @PostMapping(value = CREATE_CUSTOMER_PROFILE)
    void createUserProfile(@RequestBody CustomerDetails customerDetails, @RequestHeader HttpHeaders headers) throws Exception;
}
