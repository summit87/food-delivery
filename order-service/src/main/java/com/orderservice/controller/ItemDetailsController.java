package com.orderservice.controller;

import com.orderservice.models.AvailableItem;
import com.orderservice.service.IItemService;
import com.orderservice.service.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "item")
public class ItemDetailsController implements IItemDetailsController {

	private IItemService itemServiceImpl;

	public ItemDetailsController(IItemService itemServiceImpl) {
		this.itemServiceImpl = itemServiceImpl;
	}

	@Override
	public List<AvailableItem> getItemDetails() {
		return itemServiceImpl.getAvailableItem();
	}

	@Override
	public AvailableItem getItemById(String id) {
		return itemServiceImpl.getItemDetailsByItemId(id);
	}
}
