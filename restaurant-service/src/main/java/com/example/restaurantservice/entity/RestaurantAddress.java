package com.example.restaurantservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
@Table(name = "restaurant_address")
public class RestaurantAddress extends AuditModel {

  @Id
  @Column(name = "restaurant_address_id", nullable = false, length = 50)
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  private String restaurantAddressId;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumns({@JoinColumn(name = "restaurantId", referencedColumnName = "restaurant_id")})
  private RestaurantProfile restaurant;

  @Column(name = "address", length = 50)
  private String address;

  @Column(name = "street_name", length = 20)
  private String streetName;

  @Column(name = "landmark", length = 20)
  private String landmark;

  @Column(name = "pin_code", length = 20)
  private String pinCode;

  @Column(name = "create_by", length = 20)
  private String createBy;

  @Column(name = "updated_by", length = 20)
  private String updatedBy;
}
