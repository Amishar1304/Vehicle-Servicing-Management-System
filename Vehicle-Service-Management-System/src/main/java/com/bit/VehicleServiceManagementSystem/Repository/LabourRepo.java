package com.bit.VehicleServiceManagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bit.VehicleServiceManagementSystem.entity.Labour;

@Repository
public interface LabourRepo extends JpaRepository<Labour, Integer> {
	

}
