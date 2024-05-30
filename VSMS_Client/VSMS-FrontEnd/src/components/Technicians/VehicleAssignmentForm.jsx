import React, { useState } from "react";
import Header from "../Header";
import { assignVehicleToTechnician } from "../utils/ApiFuntions"; 

function VehicleAssignmentForm() {
  const [vehicleId, setVehicleId] = useState('');
  const [technicianId, setTechnicianId] = useState('');
  const [successMessage, setSuccessMessage] = useState('');
  const [errorMessage, setErrorMessage] = useState('');

  const handleFormSubmit = async (e) => {
    e.preventDefault();

    try {
      // Send data to API to assign vehicle to technician
      const response = await assignVehicleToTechnician({ 
        vehicle: { vehicle_id: vehicleId }, 
        technician: { technician_id: technicianId } 
      });

      // Check if assignment was successful
      if (response && response.assignmentId) {
        setSuccessMessage("Vehicle assigned successfully.");
        setErrorMessage('');
        // Reset the form fields
        setVehicleId('');
        setTechnicianId('');
      } else {
        setErrorMessage("Failed to assign vehicle.");
        setSuccessMessage('');
      }
    } catch (error) {
      console.error("Error assigning vehicle:", error);
      setErrorMessage("An error occurred while assigning vehicle.");
      setSuccessMessage('');
    }
  };

  return (
    <>
      <Header />
      <div className="flex items-center justify-center min-h-screen bg-gray-100">
        <div className="bg-white p-6 rounded shadow-md w-full max-w-md">
          <h2 className="text-center text-2xl font-bold mb-4">Assign Vehicle to Technician</h2>
          <form onSubmit={handleFormSubmit} className="space-y-4">
            <div className="flex flex-col">
              <label htmlFor="vehicleId" className="text-gray-700">Vehicle ID:</label>
              <input
                type="number"
                id="vehicleId"
                value={vehicleId}
                onChange={(e) => setVehicleId(e.target.value)}
                required
                className="border border-gray-300 rounded-md px-3 py-2 mt-1 focus:outline-none focus:ring-1 focus:ring-gray-400"
              />
            </div>
            <div className="flex flex-col">
              <label htmlFor="technicianId" className="text-gray-700">Technician ID:</label>
              <input
                type="number"
                id="technicianId"
                value={technicianId}
                onChange={(e) => setTechnicianId(e.target.value)}
                required
                className="border border-gray-300 rounded-md px-3 py-2 mt-1 focus:outline-none focus:ring-1 focus:ring-gray-400"
              />
            </div>
            <button type="submit" className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
              Submit
            </button>
          </form>
          {successMessage && <p className="text-green-500 mt-4 text-center">{successMessage}</p>}
          {errorMessage && <p className="text-red-500 mt-4 text-center">{errorMessage}</p>}
        </div>
      </div>
    </>
  );
}

export default VehicleAssignmentForm;
