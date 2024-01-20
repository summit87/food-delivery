package com.fooddelivery.authserver.restaurent;

import com.commons.utils.GenericBuilder;
import com.fooddelivery.authserver.restaurent.dao.RestaurantProfileRepository;
import com.fooddelivery.authserver.restaurent.entity.RestaurantAddress;
import com.fooddelivery.authserver.restaurent.entity.RestaurantProfile;
import jakarta.transaction.Transactional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RestaurantProfileServiceImpl implements IRestaurantProfileService {

  private RestaurantProfileRepository restaurantProfileRepository;

  public RestaurantProfileServiceImpl(RestaurantProfileRepository restaurantProfileRepository) {
    this.restaurantProfileRepository = restaurantProfileRepository;
  }

  @Override
  @Transactional(rollbackOn = {Exception.class, DataIntegrityViolationException.class})
  public RestaurantProfile createProfile(com.commons.model.RestaurantProfile restaurantProfile) {

    try {
      log.info(
          "Restaurant name {} received for profile registration",
          restaurantProfile.getRestaurantName());

      String profileId = UUID.randomUUID().toString();
      com.fooddelivery.authserver.restaurent.entity.RestaurantProfile restaurantProfile1 =
          GenericBuilder.of( com.fooddelivery.authserver.restaurent.entity.RestaurantProfile::new)
              .with(
                  com.fooddelivery.authserver.restaurent.entity.RestaurantProfile::setRestaurantName,
                  restaurantProfile.getRestaurantName())
              .with(
                  com.fooddelivery.authserver.restaurent.entity.RestaurantProfile::setEmailAddress,
                  restaurantProfile.getRestaurantContact().getEmailAddress())
              .with(
                  com.fooddelivery.authserver.restaurent.entity.RestaurantProfile::setPrmryMobNum,
                  restaurantProfile.getRestaurantContact().getPrimaryMobileNumber())
              .with(
                  com.fooddelivery.authserver.restaurent.entity.RestaurantProfile::setSecndyMobNum,
                  restaurantProfile.getRestaurantContact().getSecondaryMobileNumber())
              .build();

      Set<RestaurantAddress> restaurantAddress =
          restaurantProfile.getRestaurantAddress().stream()
              .map(address -> createRestaurantAddressList(address, restaurantProfile1))
              .collect(Collectors.toSet());
      restaurantProfile1.setRestaurantAddresses(restaurantAddress);
      RestaurantProfile savedProfile = restaurantProfileRepository.save(restaurantProfile1);
      log.info(
          "Restaurant profile created for restaurant name {} and restaurant id {}",
          savedProfile.getRestaurantName(),
          savedProfile.getRestaurantId());
      return savedProfile;
    } catch (DataIntegrityViolationException exception) {
      log.error(
          " profile for restaurant name {} already exist !!",
          restaurantProfile.getRestaurantName(),
          exception);
      throw new RuntimeException(exception.getMessage());
    } catch (Exception exception) {
      log.error(
          "Failed to create restaurant profile for restaurant name {}",
          restaurantProfile.getRestaurantName(),
          exception);
      throw new RuntimeException(exception.getMessage());
    }
  }

  private static RestaurantAddress createRestaurantAddressList(
      com.commons.model.RestaurantAddress restaurantProfile1, RestaurantProfile profile1) {
    return GenericBuilder.of(RestaurantAddress::new)
        .with(RestaurantAddress::setAddress, restaurantProfile1.getAddress())
        .with(RestaurantAddress::setLandmark, restaurantProfile1.getNearestLandMark())
        .with(RestaurantAddress::setPinCode, restaurantProfile1.getPinCode())
        .with(RestaurantAddress::setRestaurant, profile1)
        .with(RestaurantAddress::setStreetName, restaurantProfile1.getStreetName())
        .build();
  }
}
