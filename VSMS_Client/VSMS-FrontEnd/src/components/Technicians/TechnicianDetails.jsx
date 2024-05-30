import React, { useState, useEffect } from "react";
import { getAllTechnician, getTechnicianById, deleteTechnicianById, updateTechnicianById } from "../utils/ApiFuntions"; // Adjusted import for updateTechnicianById
import Header from "../Header";
import Footer from "../Footer";

function TechnicianDetails() {
  const [technicianData, setTechnicianData] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [rowsPerPage] = useState(10);
  const [technicianId, setTechnicianId] = useState(""); 
  const [buttonClicked, setButtonClicked] = useState(false); 
  const [technicianToDelete, setTechnicianToDelete] = useState(null);
  const [showConfirmationModal, setShowConfirmationModal] = useState(false);
  const [editTechnician, setEditTechnician] = useState(null);
  const [editedName, setEditedName] = useState("");
  const [editedContact, setEditedContact] = useState("");
  const [editedShiftStatus, setEditedShiftStatus] = useState("Free"); // Default shift status

  useEffect(() => {
    if (buttonClicked) {
      handleGetTechnicianData();
    }
  }, [buttonClicked]);

  const handleGetAllTechnician = async () => {
    try {
      const data = await getAllTechnician();
      setTechnicianData(data);
      setButtonClicked(true);
    } catch (error) {
      console.error("Error fetching technician data:", error);
    }
  };

  const handleGetTechnicianById = async () => {
    try {
      const data = await getTechnicianById(technicianId);
      setTechnicianData(data ? [data] : []);
      setButtonClicked(true);
    } catch (error) {
      console.error("Error fetching technician data:", error);
    }
  };

  const handleGetTechnicianData = () => {
    if (technicianId === "") {
      handleGetAllTechnician(); // Call the function to get all technicians
    } else {
      handleGetTechnicianById(); // Call the function to get technician by ID
    }
  };

  const handleDeleteTechnician = async () => {
    try {
      if (technicianToDelete) {
        await deleteTechnicianById(technicianToDelete.technician_id);
        setTechnicianData(technicianData.filter((technician) => technician.technician_id !== technicianToDelete.technician_id));
        setTechnicianToDelete(null);
        setShowConfirmationModal(false);
      }
    } catch (error) {
      console.error("Error deleting technician:", error.message);
    }
  };

  const handleEditTechnician = async () => {
    try {
      if (editTechnician) {
        const updatedTechnician = { ...editTechnician, name: editedName, contact: editedContact, shiftStatus: editedShiftStatus };
        await updateTechnicianById(updatedTechnician.technician_id, { name: editedName, contact: editedContact, shiftStatus: editedShiftStatus }); // Assuming updateTechnicianById accepts technician ID and updated data
        setTechnicianData(technicianData.map((technician) => {
          if (technician.technician_id === updatedTechnician.technician_id) {
            return updatedTechnician;
          }
          return technician;
        }));
        setEditTechnician(null);
        setEditedName("");
        setEditedContact("");
        setEditedShiftStatus("Free"); // Reset shift status after editing
      }
    } catch (error) {
      console.error("Error editing technician:", error.message);
    }
  };

  const handleInputChange = (e) => {
    setTechnicianId(e.target.value);
  };

  const paginate = (pageNumber) => setCurrentPage(pageNumber);

  const indexOfLastRow = currentPage * rowsPerPage;
  const indexOfFirstRow = indexOfLastRow - rowsPerPage;
  const currentRows = technicianData.slice(indexOfFirstRow, indexOfLastRow);

  return (
    <>
      <Header />
      <section className="rounded-md bg-black/80 p-2">
        <div className="flex items-center justify-center bg-white px-8 py-20 sm:px-6 sm:py-20 lg:px-10">
          <div className="container mx-auto">
            <h1 className="text-3xl font-bold mb-4">Technician Data</h1>
            <div className="flex mb-4">
              <input
                className="px-4 py-2 border rounded mr-2"
                type="number"
                placeholder="Enter Technician ID"
                value={technicianId}
                onChange={handleInputChange}
              />
              <button
                className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded mr-2"
                onClick={handleGetTechnicianById}
              >
                Get By ID
              </button>
              <button
                className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
                onClick={handleGetAllTechnician}
              >
                Get All
              </button>
            </div>
            {currentRows.length > 0 ? (
              <table className="table-auto">
                <thead>
                  <tr>
                    <th className="px-4 py-2">Technician ID</th>
                    <th className="px-4 py-2">Technician Name</th>
                    <th className="px-4 py-2">Contact</th>
                    <th className="px-4 py-2">Shift Status</th>
                    <th className="px-4 py-2">Actions</th>
                  </tr>
                </thead>
                <tbody>
                  {currentRows.map((technician) => (
                    <tr key={technician.technician_id}>
                      <td className="border px-4 py-2">{technician.technician_id}</td>
                      <td className="border px-4 py-2">{technician.name}</td>
                      <td className="border px-4 py-2">{technician.contact}</td>
                      <td className="border px-4 py-2">{technician.shiftStatus}</td>
                      <td className="border px-4 py-2">
                        <button
                          className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded mr-2"
                          onClick={() => {
                            setTechnicianToDelete(technician);
                            setShowConfirmationModal(true);
                          }}
                        >
                          Delete
                        </button>
                        <button
                          className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
                          onClick={() => {
                            setEditTechnician(technician);
                            setEditedName(technician.name);
                            setEditedContact(technician.contact);
                            setEditedShiftStatus(technician.shiftStatus); // Set the current shift status
                          }}
                        >
                          Edit
                        </button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            ) : (
              <p>No data available.</p>
            )}
            <div className="flex justify-center mt-4">
              {technicianData && technicianData.length > rowsPerPage && (
                <ul className="flex list-none">
                  {Array.from({ length: Math.ceil((technicianData ? technicianData.length : 0) / rowsPerPage) }, (_, i) => (
                    <li key={i} className="mr-2">
                      <button
                        onClick={() => paginate(i + 1)}
                        className={`px-3 py-1 bg-blue-500 text-white font-bold rounded ${
                          currentPage === i + 1 ? "bg-blue-700" : ""
                        }`}
                      >
                        {i + 1}
                      </button>
                    </li>
                  ))}
                </ul>
              )}
            </div>
            {editTechnician && (
              <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
                <div className="bg-white p-8 rounded-md">
                  <h2 className="text-xl font-bold mb-4">Edit Technician</h2>
                  <div className="mb-4">
                    <label className="block mb-2">Name:</label>
                    <input
                      type="text"
                      className="border rounded w-full px-3 py-2"
                      value={editedName}
                      onChange={(e) => setEditedName(e.target.value)}
                    />
                  </div>
                  <div className="mb-4">
                    <label className="block mb-2">Contact:</label>
                    <input
                      type="text"
                      className="border rounded w-full px-3 py-2"
                      value={editedContact}
                      onChange={(e) => setEditedContact(e.target.value)}
                    />
                  </div>
                  <div className="mb-4">
                    <label className="block mb-2">Shift Status:</label>
                    <select
                      className="border rounded w-full px-3 py-2"
                      value={editedShiftStatus}
                      onChange={(e) => setEditedShiftStatus(e.target.value)}
                    >
                      <option value="Free">Free</option>
                      <option value="Busy">Busy</option>
                    </select>
                  </div>
                  <div className="flex justify-between">
                    <button
                      className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
                      onClick={handleEditTechnician}
                    >
                      Save
                    </button>
                    <button
                      className="bg-gray-500 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded"
                      onClick={() => setEditTechnician(null)}
                    >
                      Cancel
                    </button>
                  </div>
                </div>
              </div>
            )}
            {showConfirmationModal && (
              <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
                <div className="bg-white p-8 rounded-md">
                  <h2 className="text-xl font-bold mb-4">Confirm Deletion</h2>
                  <p className="mb-4">Are you sure you want to delete this technician?</p>
                  <div className="flex justify-between">
                    <button
                      className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded mr-2"
                      onClick={handleDeleteTechnician}
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
          </div>
        </div>
      </section>
      <Footer />
    </>
  );
}

export default TechnicianDetails;
