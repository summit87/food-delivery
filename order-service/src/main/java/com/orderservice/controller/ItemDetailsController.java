package com.orderservice.controller;

import com.orderservice.models.AvailableItem;
import com.orderservice.service.IItemService;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "item")
public class ItemDetailsController implements IItemDetailsController {
	
	private IItemService itemServiceImpl;
	private final ObservationRegistry observationRegistry;
	
	public ItemDetailsController(IItemService itemServiceImpl,
		ObservationRegistry observationRegistry) {
		this.itemServiceImpl = itemServiceImpl;
		this.observationRegistry = observationRegistry;
	}
	
	@Override
	public List<AvailableItem> getItemDetails() {
		return Observation.createNotStarted("item-details", observationRegistry)
			.observe(() -> itemServiceImpl.getAvailableItem());
	}
	
	@Override
	public AvailableItem getItemById(String id) {
		return itemServiceImpl.getItemDetailsByItemId(id);
	}
}
