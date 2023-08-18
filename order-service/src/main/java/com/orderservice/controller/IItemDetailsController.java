package com.orderservice.controller;

import com.orderservice.models.AvailableItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

public interface IItemDetailsController {
	@GetMapping(path = "/allItem")
	public List<AvailableItem> getItemDetails();

	@GetMapping(path = "/{id}")
	public AvailableItem getItemById(@PathVariable("id") String id);
}
