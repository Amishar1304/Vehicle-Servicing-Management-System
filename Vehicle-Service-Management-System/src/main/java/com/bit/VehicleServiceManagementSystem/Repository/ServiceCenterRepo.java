package com.bit.VehicleServiceManagementSystem.Repository;

import com.bit.VehicleServiceManagementSystem.entity.ServiceCenter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceCenterRepo extends JpaRepository<ServiceCenter, Long> {
}
