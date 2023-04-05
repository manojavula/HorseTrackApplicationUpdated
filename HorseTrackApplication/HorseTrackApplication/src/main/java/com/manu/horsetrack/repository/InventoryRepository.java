package com.manu.horsetrack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manu.horsetrack.entity.Inventory;

/**
 * 
 * @author manoj
 * Inventory Repository class to get the list of Inventories and find the inventory by denomination
 */
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
	
	  List<Inventory> findAll();
	  Inventory findByDenominationEquals(int denomination);
	
}
