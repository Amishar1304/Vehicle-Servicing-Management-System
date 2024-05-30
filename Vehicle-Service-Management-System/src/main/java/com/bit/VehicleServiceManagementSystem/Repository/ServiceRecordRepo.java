package com.bit.VehicleServiceManagementSystem.Repository;

import com.bit.VehicleServiceManagementSystem.entity.Customer;
import com.bit.VehicleServiceManagementSystem.entity.ServiceRecord;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRecordRepo extends JpaRepository<ServiceRecord, Long> {

	List<ServiceRecord> findByVehicle_Customer(Customer customer);
}
