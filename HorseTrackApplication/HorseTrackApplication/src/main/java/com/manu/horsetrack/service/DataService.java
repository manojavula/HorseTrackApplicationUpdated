package com.manu.horsetrack.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.manu.horsetrack.commons.RaceStatus;
import com.manu.horsetrack.entity.Horse;
import com.manu.horsetrack.entity.Inventory;
import com.manu.horsetrack.repository.HorseRepository;
import com.manu.horsetrack.repository.InventoryRepository;

@Service
public class DataService {
	
	  @Value("${max.horses}")
	  private int maxHorses;

	  @Autowired
	  private HorseRepository horseRepository;

	  @Autowired
	  private InventoryRepository inventoryRepository;

	  public DataService() {
	  }

	  public void loadHorses() {
		
		  
		List<Horse>  horses = Stream.of(
				new Horse(1, "That Darn Gray Cat", 5, RaceStatus.WON),
				new Horse(2, "Fort Utopia", 10, RaceStatus.LOST),
				new Horse(3, "Count Sheep", 9, RaceStatus.LOST),
				new Horse(4, "Ms Traitour", 4, RaceStatus.LOST),
				new Horse(5, "Real Princess", 3, RaceStatus.LOST),
				new Horse(6, "Pa Kettle", 5, RaceStatus.LOST),
				new Horse(7, "Gin Stinger", 6, RaceStatus.LOST)
				).collect(Collectors.toList());
		
		horseRepository.saveAll(horses);
		/*  
	    // Check value maxHorses for loading correct number of horses
	    horseRepository.save(new Horse(1, "That Darn Gray Cat", 5, RaceStatus.WON));
	    horseRepository.save(new Horse(2, "Fort Utopia", 10, RaceStatus.LOST));
	    horseRepository.save(new Horse(3, "Count Sheep", 9, RaceStatus.LOST));
	    horseRepository.save(new Horse(4, "Ms Traitour", 4, RaceStatus.LOST));
	    horseRepository.save(new Horse(5, "Real Princess", 3, RaceStatus.LOST));
	    horseRepository.save(new Horse(6, "Pa Kettle", 5, RaceStatus.LOST));
	    horseRepository.save(new Horse(7, "Gin Stinger", 6, RaceStatus.LOST));
	*/
	  }

	  public void loadInventory() {
		  
		List<Inventory>  inventoryList = Stream.of(  
				new Inventory(1,10),
				new Inventory(5,10),
				new Inventory(10,10),
				new Inventory(20,10),
				new Inventory(100,10)
				).collect(Collectors.toList());
		inventoryRepository.saveAll(inventoryList);
		
		
		/*		
	    inventoryRepository.save(new Inventory(1,10));
	    inventoryRepository.save(new Inventory(5,10));
	    inventoryRepository.save(new Inventory(10,10));
	    inventoryRepository.save(new Inventory(20,10));
	    inventoryRepository.save(new Inventory(100,10));
	    
	    */
	  }

	  public void startup() {

	    loadHorses();
	    loadInventory();

	  }

}
