package com.example.restaurantservice.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "restaurant_name")
public class RestaurantName extends AuditModel {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name = "rstrnt_name", length = 200)
    private String restaurantName;

    @Column(name = "is_rstrnt_active")
    private Boolean isRestaurantActive;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "category")
    private String category;

    @Column(name = "user_name", length = 20)
    private String userName;

    @Column(name = "email_id", length = 100)
    private String emailId;

    @Column(name = "password", length = 20)
    private String password;

    @Column(name = "meta_info")
    private String metaInfo;

    @Column(name = "create_by", length = 20)
    private String createBy;

    @Column(name = "updated_by", length = 20)
    private String updatedBy;

}