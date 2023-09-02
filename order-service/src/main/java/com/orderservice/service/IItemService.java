package com.orderservice.service;

import com.orderservice.controller.response.ItemMetaInfo;
import com.orderservice.models.AvailableItem;
import java.util.List;


public interface IItemService {
	
	List<AvailableItem> getAvailableItem();
	
	AvailableItem getItemDetailsByItemId(String itemId);
	
	List<AvailableItem> getAvailableItemsById(List<String> ids);
	
	List<ItemMetaInfo> getItemMetaInfos(List<String> collect);
}
