package com.bit.VehicleServiceManagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bit.VehicleServiceManagementSystem.entity.VehicleEquipments;

@Repository
public interface VehicleEquipmentsRepo extends JpaRepository<VehicleEquipments, Integer> {

}
