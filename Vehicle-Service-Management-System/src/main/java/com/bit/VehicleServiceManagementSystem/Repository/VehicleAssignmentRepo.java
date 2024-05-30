package com.bit.VehicleServiceManagementSystem.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bit.VehicleServiceManagementSystem.entity.VehicleAssignment;

@Repository
public interface VehicleAssignmentRepo extends JpaRepository<VehicleAssignment, Long>{
//	@Query("SELECT va FROM VehicleAssignment va JOIN FETCH va.technician JOIN FETCH va.vehicle")
//    List<VehicleAssignment> findAllWithTechnicianAndVehicle();

}
