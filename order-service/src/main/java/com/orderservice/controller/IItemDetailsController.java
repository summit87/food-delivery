package com.orderservice.controller;

import com.orderservice.models.AvailableItem;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface IItemDetailsController {
	
	@GetMapping(path = "/allItem")
	public List<AvailableItem> getItemDetails();
	
	@GetMapping(path = "/{id}")
	public AvailableItem getItemById(@PathVariable("id") String id);
}
