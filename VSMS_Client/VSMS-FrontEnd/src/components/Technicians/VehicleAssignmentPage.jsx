import React, { useState, useEffect } from "react";
import { getAllVehicleAssignments, deleteVehicleAssignment } from "../utils/ApiFuntions";
import Header from "../Header";
import Footer from "../Footer";

function VehicleAssignmentPage() {
  const [assignments, setAssignments] = useState([]);
  const [showConfirmationModal, setShowConfirmationModal] = useState(false);
  const [assignmentToDelete, setAssignmentToDelete] = useState(null);

  useEffect(() => {
    fetchAssignments();
  }, []);

  const fetchAssignments = async () => {
    try {
      const assignmentsData = await getAllVehicleAssignments();
      console.log(assignmentsData);
      setAssignments(assignmentsData);
    } catch (error) {
      console.error("Error fetching vehicle assignments:", error);
    }
  };

  const handleDelete = async () => {
    try {
      if (assignmentToDelete) {
        await deleteVehicleAssignment(assignmentToDelete.assignmentId);
        setAssignments(assignments.filter(assignment => assignment.assignmentId !== assignmentToDelete.assignmentId));
        setAssignmentToDelete(null);
        setShowConfirmationModal(false);
      }
    } catch (error) {
      console.error("Error deleting vehicle assignment:", error);
    }
  };

  const handleDeleteClick = (assignment) => {
    setAssignmentToDelete(assignment);
    setShowConfirmationModal(true);
  };

  return (
    <>
      <Header />
      <section className="rounded-md bg-black/80 p-2">
        <div className="flex items-center justify-center bg-white px-8 py-20 sm:px-6 sm:py-20 lg:px-10">
          <div className="container mx-auto">
            <h2 className="text-3xl font-bold mb-4">Vehicle Assignments</h2>
            <table className="table-auto w-full">
              <thead>
                <tr>
                  <th className="px-4 py-2">Assignment ID</th>
                  <th className="px-4 py-2">Vehicle ID</th>
                  <th className="px-4 py-2">Vehicle Registration Number</th>
                  <th className="px-4 py-2">Technician ID</th>
                  <th className="px-4 py-2">Technician Name</th>
                  <th className="px-4 py-2">Technician Contact</th>
                  <th className="px-4 py-2">Actions</th>
                </tr>
              </thead>
              <tbody>
                {assignments.map((assignment) => (
                  <tr key={assignment.assignmentId}>
                    <td className="border px-4 py-2">{assignment.assignmentId}</td>
                    <td className="border px-4 py-2">{assignment.vehicle.vehicle_id}</td>
                    <td className="border px-4 py-2">{assignment.vehicle.vehicleRegNo}</td>
                    <td className="border px-4 py-2">{assignment.technician.technician_id}</td>
                    <td className="border px-4 py-2">{assignment.technician.name}</td>
                    <td className="border px-4 py-2">{assignment.technician.contact}</td>
                    <td className="border px-4 py-2">
                      <button
                        onClick={() => handleDeleteClick(assignment)}
                        className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded"
                      >
                        Delete
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      </section>
      <Footer />

      {showConfirmationModal && (
        <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
          <div className="bg-white p-8 rounded-md">
            <h2 className="text-xl font-bold mb-4">Confirm Deletion</h2>
            <p className="mb-4">Are you sure you want to delete this assignment?</p>
            <div className="flex justify-between">
              <button
                className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded mr-2"
                onClick={handleDelete}
              >
                Yes
              </button>
              <button
                className="bg-gray-500 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded"
                onClick={() => setShowConfirmationModal(false)}
              >
                No
              </button>
            </div>
          </div>
        </div>
      )}
    </>
  );
}

export default VehicleAssignmentPage;
