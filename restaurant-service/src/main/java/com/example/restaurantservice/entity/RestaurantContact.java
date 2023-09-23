package com.example.restaurantservice.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "restaurant_contact")
public class RestaurantContact  extends AuditModel{
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name = "restaurant_id", nullable = false)
    private UUID restaurantId;

    @Column(name = "address_id", nullable = false)
    private UUID addressId;

    @Column(name = "primary_mobile", length = 20)
    private String primaryMobile;

    @Column(name = "secondary_mobile", length = 20)
    private String secondaryMobile;

    @Column(name = "is_mob_notiftn_ative")
    private Boolean isMobNotiftnAtive;

    @Column(name = "is_email_notiftn_active")
    private Boolean isEmailNotificationActive;

    @Column(name = "create_by", length = 20)
    private String createBy;

    @Column(name = "updated_by", length = 20)
    private String updatedBy;

}