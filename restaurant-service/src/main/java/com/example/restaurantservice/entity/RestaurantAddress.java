package com.example.restaurantservice.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "restaurant_address")
public class RestaurantAddress extends AuditModel{
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name = "restaurant_id")
    private UUID restaurantId;

    @Column(name = "country_code", length = 5)
    private String countryCode;

    @Column(name = "state", length = 50)
    private String state;

    @Column(name = "city", length = 50)
    private String city;

    @Column(name = "area_name", length = 40)
    private String areaName;

    @Column(name = "street", length = 20)
    private String street;

    @Column(name = "zip_code", length = 10)
    private String zipCode;

    @Column(name = "is_address_active")
    private Boolean isAddressActive;

    @Column(name = "create_by", length = 20)
    private String createBy;

    @Column(name = "updated_by", length = 20)
    private String updatedBy;

}