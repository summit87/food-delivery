package com.orderservice.dao;

import com.orderservice.entity.ItemDetailEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDetailsRepositoryDAO
	extends JpaRepository<ItemDetailEntity, String> {
	
	List<ItemDetailEntity> findAllByItemIdIsIn(List<String> ids);
}
