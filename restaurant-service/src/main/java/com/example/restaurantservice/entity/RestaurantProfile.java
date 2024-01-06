package com.example.restaurantservice.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
@Table(
    name = "restaurant_profile",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_name", "prmry_mob_num"})})
public class RestaurantProfile extends AuditModel {

  @Id
  @Column(name = "restaurant_id")
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  private String restaurantId;

  @Column(name = "restaurant_name", length = 50)
  private String restaurantName;

  @Column(name = "prmry_mob_num", length = 20)
  private String prmryMobNum;

  @Column(name = "secndy_mob_num", length = 20)
  private String secndyMobNum;

  @Column(name = "email_address", length = 20)
  private String emailAddress;

  @Column(name = "create_by", length = 20)
  private String createBy;

  @Column(name = "updated_by", length = 20)
  private String updatedBy;

  @OneToMany(
      fetch = FetchType.EAGER,
      mappedBy = "restaurant",
      cascade = {CascadeType.ALL})
  private Set<RestaurantAddress> restaurantAddresses = new LinkedHashSet<>();
}
