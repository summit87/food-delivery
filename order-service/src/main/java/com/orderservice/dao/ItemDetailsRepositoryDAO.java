package com.orderservice.dao;

import com.orderservice.entity.ItemDetailEntity;
import com.orderservice.models.AvailableItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemDetailsRepositoryDAO
		extends JpaRepository<ItemDetailEntity, String> {

	List<ItemDetailEntity> findAllByItemIdIsIn(List<String> ids);
}
