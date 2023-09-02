package com.orderservice.controller;

import com.orderservice.models.AvailableItem;
import com.orderservice.service.IItemService;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
