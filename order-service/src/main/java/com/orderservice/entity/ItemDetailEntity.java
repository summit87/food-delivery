package com.orderservice.entity;

import com.commons.enums.ItemAvailabilityStatus;
import com.orderservice.utils.DatabaseConstants;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = DatabaseConstants.ITEM_DETAILS)
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ItemDetailEntity {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
		name = "UUID",
		strategy = "org.hibernate.id.UUIDGenerator"
	)
	private String itemId;
	
	@Column(name = "item_name")
	private String itemName;
	
	@Column(name = "availability_status")
	@Enumerated(EnumType.STRING)
	private ItemAvailabilityStatus itemAvailabilityStatus;
	@Column(name = "item_price")
	private BigDecimal itemPrice;
	@Column(name = "item_type")
	private String itemType;
	
}
