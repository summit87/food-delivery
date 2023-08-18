package com.orderservice.service;

import com.orderservice.controller.response.ItemMetaInfo;
import com.orderservice.models.AvailableItem;
import org.springframework.stereotype.Service;

import java.util.List;



public interface IItemService {

	List<AvailableItem> getAvailableItem();
	AvailableItem getItemDetailsByItemId(String itemId);

	List<AvailableItem> getAvailableItemsById(List<String> ids);

	List<ItemMetaInfo> getItemMetaInfos(List<String> collect);
}
