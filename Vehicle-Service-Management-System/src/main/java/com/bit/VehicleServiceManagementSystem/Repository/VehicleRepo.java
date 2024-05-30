package com.bit.VehicleServiceManagementSystem.Repository;

import com.bit.VehicleServiceManagementSystem.entity.Vehicle;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface VehicleRepo extends JpaRepository<Vehicle, Long> {
	//for service record
	Optional<Vehicle> findByVehicleRegNo(String vehicleRegNo);
	
}
