package com.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orderservice.utils.DatabaseConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = DatabaseConstants.OREDER_ITEM_DETAILS)
@Getter
@Setter
@ToString
@NoArgsConstructor
public class OrderItemDetails {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator"
	)
	private UUID id;

	@Column(name = "item_id")
	private String itemId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
			@JoinColumn(name = "orderId",referencedColumnName = "order_id")
	})
	@JsonIgnore
	private OrderDetails orderDetails;
}
