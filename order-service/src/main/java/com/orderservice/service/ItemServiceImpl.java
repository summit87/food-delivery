package com.orderservice.service;

import com.commons.utils.GenericBuilder;
import com.orderservice.controller.response.ItemMetaInfo;
import com.orderservice.dao.ItemDetailsRepositoryDAO;
import com.orderservice.entity.ItemDetailEntity;
import com.orderservice.models.AvailableItem;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements IItemService {
	
	private final ItemDetailsRepositoryDAO itemDetailsRepositoryDAO;
	
	public ItemServiceImpl(ItemDetailsRepositoryDAO itemDetailsRepositoryDAO) {
		this.itemDetailsRepositoryDAO = itemDetailsRepositoryDAO;
	}
	
	private static AvailableItem buildAvailableItemDetails(
		ItemDetailEntity itemDetailEntity) {
		return GenericBuilder.of(AvailableItem::new)
			.with(AvailableItem::setItemPrice,
				itemDetailEntity.getItemPrice())
			.with(AvailableItem::setItemAvailabilityStatus,
				itemDetailEntity.getItemAvailabilityStatus())
			.with(AvailableItem::setItemName,
				itemDetailEntity.getItemName())
			.with(AvailableItem::setItemType,
				itemDetailEntity.getItemType())
			.build();
	}
	
	@Override
	public List<AvailableItem> getAvailableItem() {
		
		List<ItemDetailEntity> all = itemDetailsRepositoryDAO.findAll();
		
		return all.stream()
			.map(itemDetailEntity -> GenericBuilder.of(AvailableItem::new)
				.with(AvailableItem::setItemId,
					itemDetailEntity.getItemId())
				.with(AvailableItem::setItemName,
					itemDetailEntity.getItemName())
				.with(AvailableItem::setItemPrice,
					itemDetailEntity.getItemPrice())
				.with(AvailableItem::setItemAvailabilityStatus,
					itemDetailEntity.getItemAvailabilityStatus())
				.with(AvailableItem::setItemType,
					itemDetailEntity.getItemType())
				.build())
			.collect(Collectors.toList());
	}
	
	@Override
	public AvailableItem getItemDetailsByItemId(String itemId) {
		Optional<ItemDetailEntity> itemDetail =
			itemDetailsRepositoryDAO.findById(itemId);
		if (!itemDetail.isPresent()) {
			throw new RuntimeException("Item not found");
		}
		ItemDetailEntity itemDetailEntity = itemDetail.get();
		return buildAvailableItemDetails(itemDetailEntity);
	}
	
	@Override
	public List<AvailableItem> getAvailableItemsById(List<String> ids) {
		return itemDetailsRepositoryDAO.findAllByItemIdIsIn(ids).stream()
			.map(ItemServiceImpl::buildAvailableItemDetails).collect(
				Collectors.toList());
	}
	
	@Override
	public List<ItemMetaInfo> getItemMetaInfos(List<String> itemIds) {
		
		return getAvailableItemsById(itemIds).stream()
			.map(availableItem -> GenericBuilder.of(ItemMetaInfo::new)
				.with(ItemMetaInfo::setItemName,
					availableItem.getItemName())
				.with(ItemMetaInfo::setItemCost,
					availableItem.getItemPrice())
				.build()).collect(
				Collectors.toList());
	}
	
}
