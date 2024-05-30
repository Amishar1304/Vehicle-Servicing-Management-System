import React, { useState, useEffect } from "react";
import { getAllCustomers, generatePdf, deleteCustomerById, updateCustomerById, getCustomerById } from "../utils/ApiFuntions";
import Header from "../Header";
import Footer from "../Footer";

function GetCustomers() {
  const [customerData, setCustomerData] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [rowsPerPage] = useState(10);
  const [editMode, setEditMode] = useState(false);
  const [editedRecord, setEditedRecord] = useState(null);
  const [customerId, setCustomerId] = useState(""); 
  const [buttonClicked, setButtonClicked] = useState(false); 
  const [showConfirmationModal, setShowConfirmationModal] = useState(false);
  const [customerToDelete, setCustomerToDelete] = useState(null);

  useEffect(() => {
    if (buttonClicked) {
      handleGetCustomerData();
    }
  }, [buttonClicked]);

  const handleGetAllCustomers = async () => {
    try {
      const data = await getAllCustomers();
      setCustomerData(data);
      setButtonClicked(true);
    } catch (error) {
      console.error("Error fetching customer data:", error);
    }
  };

  const handleGetCustomerById = async () => {
    try {
      const data = await getCustomerById(customerId);
      setCustomerData(data ? [data] : []);
      setButtonClicked(true);
    } catch (error) {
      console.error("Error fetching customer data:", error);
    }
  };

  const handleGetCustomerData = () => {
    if (customerId === "") {
      handleGetAllCustomers();
    } else {
      handleGetCustomerById();
    }
  };

  const handleDownloadPdf = async (customerId) => {
    try {
      if (customerId) { // Check if customerId is defined
        await generatePdf(customerId);
      } else {
        console.error("Customer ID is undefined");
      }
    } catch (error) {
      console.error("Error downloading PDF:", error);
    }
  };
  

  const handleDeleteCustomer = async () => {
    try {
      if (customerToDelete) {
        await deleteCustomerById(customerToDelete.customer_id);
        setCustomerData(customerData.filter((customer) => customer.customer_id !== customerToDelete.customer_id));
        setCustomerToDelete(null);
        setShowConfirmationModal(false);
      }
    } catch (error) {
      console.error("Error deleting customer:", error.message);
    }
  };

  const handleEditCustomer = (customer) => {
    setEditedRecord(customer);
    setEditMode(true);
  };

  const handleSaveChanges = async () => {
    try {
      await updateCustomerById(editedRecord.customer_id, editedRecord);
      handleGetAllCustomers(); 
      setEditMode(false);
      setEditedRecord(null);
    } catch (error) {
      console.error("Error updating customer data:", error);
    }
  };
  

  const handleCancelEdit = () => {
    setEditMode(false);
    setEditedRecord(null);
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setEditedRecord((prevRecord) => ({
      ...prevRecord,
      [name]: value,
    }));
  };
  
  const handleGetAll = () => {
    setCustomerId(""); 
    setButtonClicked(true);
  };

  const paginate = (pageNumber) => setCurrentPage(pageNumber);

  const indexOfLastRow = currentPage * rowsPerPage;
  const indexOfFirstRow = indexOfLastRow - rowsPerPage;
  const currentRows = customerData.slice(indexOfFirstRow, indexOfLastRow);

  return (
    <>
      <Header />
      <section className="rounded-md bg-black/80 p-2">
        <div className="flex items-center justify-center bg-white px-8 py-20 sm:px-6 sm:py-20 lg:px-10">
          <div className="container mx-auto">
            <h1 className="text-3xl font-bold mb-4">Customer Data</h1>
            <div className="flex mb-4">
              <input
                className="px-4 py-2 border rounded mr-2"
                type="number"
                placeholder="Enter Customer ID"
                value={customerId}
                onChange={(e) => setCustomerId(e.target.value)}
              />
              <button
                className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded mr-2"
                onClick={handleGetCustomerById}
              >
                Get By ID
              </button>
              <button
                className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
                onClick={handleGetAll}
              >
                Get All
              </button>
            </div>
            {currentRows.length > 0 ? (
              <table className="table-auto">
                <thead>
                  <tr>
                    <th className="px-4 py-2">Customer ID</th>
                    <th className="px-4 py-2">Customer Name</th>
                    <th className="px-4 py-2">Contact</th>
                   
                    <th className="px-4 py-2">Vehicle</th>
                    <th className="px-4 py-2">Type</th>
                    <th className="px-4 py-2">Service Records</th>
                    <th className="px-4 py-2">Actions</th>
                  </tr>
                </thead>
                <tbody>
                  {currentRows.map((customer) => (
                    <tr key={customer.customer_id}>
                      <td className="border px-4 py-2">{customer.customer_id}</td>
                      <td className="border px-4 py-2">{customer.customerName}</td>
                      <td className="border px-4 py-2">{customer.customerContact}</td>
          
                      <td className="border px-4 py-2">
                        {customer.vehicles.length > 0 ? (
                          customer.vehicles.map((vehicle) => (
                            <div key={vehicle.vehicle_id}>{vehicle.vehicleRegNo}</div>
                          ))
                        ) : (
                          <div>No vehicles</div>
                        )}
                      </td>
                      <td className="border px-4 py-2">
                        {customer.vehicles.length > 0 ? (
                          customer.vehicles.map((vehicle) => (
                            <div key={vehicle.vehicle_id}>{vehicle.vehicleType}</div>
                          ))
                        ) : (
                          <div>N/A</div>
                        )}
                      </td>
                      <td className="border px-4 py-2">
                        {customer.vehicles.length > 0 ? (
                          customer.vehicles.map((vehicle) =>
                            vehicle.serviceRecords.length > 0 ? (
                              <ul key={vehicle.vehicle_id}>
                                {vehicle.serviceRecords.map((record) => (
                                  <li key={record.serviceRecord_id}>
                                    Date: {record.date}, Amount: ₹{record.serviceAmount}, OilChange:{" "}
                                    {record.isOilChange ? "Yes" : "No"}, Washing:{" "}
                                    {record.isWashing ? "Yes" : "No"}, OtherAmenities: {record.otherAmenities}
                                  </li>
                                ))}
                              </ul>
                            ) : (
                              <div key={vehicle.vehicle_id}>No service records</div>
                            )
                          )
                        ) : (
                          <div>No vehicles</div>
                        )}
                      </td>
                      <td className="border px-4 py-2 flex">
                        <button
                          className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded mr-2"
                          onClick={() => handleEditCustomer(customer)}
                        >
                          Edit
                        </button>
                        <button
                          className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded mr-2"
                          onClick={() => {
                            setCustomerToDelete(customer);
                            setShowConfirmationModal(true);
                          }}
                        >
                          Delete
                        </button>
                        <button
                          className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
                          onClick={() => handleDownloadPdf(customer.customer_id)}
                        >
                          Download PDF
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
              {customerData && customerData.length > rowsPerPage && (
                <ul className="flex list-none">
                  {Array.from({ length: Math.ceil((customerData ? customerData.length : 0) / rowsPerPage) }, (_, i) => (
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
          </div>
        </div>
      </section>
      <Footer />
      {editMode && (
        <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
          <div className="bg-white p-8 rounded-md max-h-full overflow-auto">
            <h2 className="text-xl font-bold mb-4">Edit Customer</h2>
            <form onSubmit={(e) => e.preventDefault()}>
              <div className="mb-4">
                <label htmlFor="customerName" className="block text-sm font-medium text-gray-700">
                  Customer Name
                </label>
                <input
                  type="text"
                  name="customerName"
                  id="customerName"
                  className="mt-1 p-2 border rounded-md w-full"
                  value={editedRecord ? editedRecord.customerName : ""}
                  onChange={handleInputChange}
                />
              </div>
              <div className="mb-4">
                <label htmlFor="customerContact" className="block text-sm font-medium text-gray-700">
                  Contact
                </label>
                <input
                  type="text"
                  name="customerContact"
                  id="customerContact"
                  className="mt-1 p-2 border rounded-md w-full"
                  value={editedRecord ? editedRecord.customerContact : ""}
                  onChange={handleInputChange}
                />
              </div>
              <div className="mt-4">
                <button
                  className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded mr-2"
                  onClick={handleSaveChanges}
                >
                  Save Changes
                </button>
                <button
                  className="bg-gray-500 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded"
                  onClick={handleCancelEdit}
                >
                  Cancel
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
      {showConfirmationModal && (
        <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
          <div className="bg-white p-8 rounded-md">
            <h2 className="text-xl font-bold mb-4">Confirm Deletion</h2>
            <p className="mb-4">Are you sure you want to delete this customer?</p>
            <div className="flex justify-between">
              <button
                className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded mr-2"
                onClick={handleDeleteCustomer}
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

export default GetCustomers;
