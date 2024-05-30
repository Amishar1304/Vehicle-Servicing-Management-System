package com.bit.VehicleServiceManagementSystem.entity;

import jakarta.persistence.*;

@Entity
public class VehicleAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "assignment_id")
    private Long assignmentId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "technician_id")
    private Technician technician;

    public VehicleAssignment() {
        super();
    }

    public VehicleAssignment(Long assignmentId, Vehicle vehicle, Technician technician) {
        super();
        this.assignmentId = assignmentId;
        this.vehicle = vehicle;
        this.technician = technician;
    }

    public Long getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Long assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Technician getTechnician() {
        return technician;
    }

    public void setTechnician(Technician technician) {
        this.technician = technician;
    }

    @Override
    public String toString() {
        return "VehicleAssignment [assignmentId=" + assignmentId + ", vehicle=" + vehicle + ", technician=" + technician + "]";
    }
}
