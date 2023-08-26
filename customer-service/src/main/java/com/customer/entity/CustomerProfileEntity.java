package com.customer.entity;


import static com.customer.utils.DatabaseConstants.CUSTOMER_PROFILE;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = CUSTOMER_PROFILE,uniqueConstraints = {
	@UniqueConstraint(columnNames = {"user_id", "pr_mobnbr"})})
@Getter
@Setter
@NoArgsConstructor
public class CustomerProfileEntity extends AuditModel{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "user_password")
	private String password;
	
	@Column(name = "pr_mobnbr")
	private String primaryMobNumber;
	
	@Column(name = "sec_mobnbr")
	private String secondaryMobNumber;
	
	@Column(name = "cntry_code")
	private String countryCode;
	
	@Column(name = "house_nbr")
	private String houseNbr;
	
	@Column(name = "street_name")
	private String streetName;
	
	@Column(name = "land_mark")
	private String landMark;
	
	@Column(name = "zip_code")
	private String zipCode;
	
	@Column(name = "is_account_verified")
	private boolean isAccntVerified;
	
}
