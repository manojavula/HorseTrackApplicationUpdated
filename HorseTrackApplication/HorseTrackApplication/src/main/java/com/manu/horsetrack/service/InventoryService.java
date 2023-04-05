package com.manu.horsetrack.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.manu.horsetrack.entity.Inventory;
import com.manu.horsetrack.entity.Wager;
import com.manu.horsetrack.repository.InventoryRepository;

@Service
public class InventoryService {
	
	  @Value("${restock.amount}")
	  private int restockAmount;

	  @Autowired
	  private InventoryRepository inventoryRepository;

	  public void restock() {
	    List<Inventory> inventories = inventoryRepository.findAll();

	    inventories.stream()
	      .forEach(inventory-> {
	        inventory.setBillCount(restockAmount);
	        inventoryRepository.save(inventory);
	      });
	  }

	  public void decrementInventory(int denomination, int amount) {

	    Inventory inventory = inventoryRepository.findByDenominationEquals(denomination);

	    int currentBillCount = inventory.getBillCount();
	    if ((currentBillCount - amount) >= 0) {
	      inventory.setBillCount(currentBillCount - amount);
	      inventoryRepository.save(inventory);
	    }
	  }

	  public boolean sufficientFunds(int amountWon) {
		  
		  List<Integer> denoms = this.getInventory()
                  .stream()
                  .map(Inventory::getDenomination)
                  .collect(Collectors.toList());
		  Collections.reverse(denoms);
		  int totalAmount = 0;
		  int winningAmount = amountWon;
		  for (Integer denomination : denoms) {
		      int bill = denomination;
		      for (int cnt = getInventory(bill).getBillCount(); cnt >0; cnt--) {
		        int totalAmountOfBills = bill * cnt;
		        if (amountWon >= totalAmountOfBills) {		          
		        	amountWon -= totalAmountOfBills;
		        	totalAmount = totalAmount+totalAmountOfBills;
		          break;
		        }
		      }
		  }
		 //System.out.println(totalAmount +"----"+winningAmount); 
		 if ( totalAmount == winningAmount ) {
	    		return true;
	    } else {
	    		return false;
	    }
		  
	  }

	  public List<Inventory>  getInventory() {
	    return inventoryRepository.findAll();
	  }

	  public Inventory  getInventory(int denomination) {
	    return inventoryRepository.findByDenominationEquals(denomination);
	  }
	
}
