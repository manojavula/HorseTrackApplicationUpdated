package com.manu.horsetrack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manu.horsetrack.entity.Horse;

/**
 * 
 * @author manoj
 * HorseRepository class to get list of horses and find the horse by horse name
 */
public interface HorseRepository extends JpaRepository<Horse, Integer>{

	List<Horse> findAll();
	Horse findByHorseNumberEquals(int horseNumber);	

}
