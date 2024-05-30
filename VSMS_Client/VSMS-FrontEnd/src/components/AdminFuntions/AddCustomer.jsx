import React, { useState } from "react";
import Header from "../Header";
import { addCustomerAndVehicles } from "../utils/ApiFuntions";

function AddCustomer() {
  const [customerName, setCustomerName] = useState('');
  const [customerContact, setCustomerContact] = useState('');
  const [vehicleType, setVehicleType] = useState('');
  const [stateCode, setStateCode] = useState('');
  const [vehicleRegNo, setVehicleRegNo] = useState('');
  const [errors, setErrors] = useState({}); // Initialize errors state

  const [showSuccessMessage, setShowSuccessMessage] = useState(false); // State for success message

  const handleCustomerSubmit = async (e) => {
    e.preventDefault();

    // Show success message immediately
    setShowSuccessMessage(true);

    // Validation checks
    const newErrors = {};
    if (!customerName.trim()) {
      newErrors.customerName = "Full Name is required";
    }
    if (!customerContact.trim()) {
      newErrors.customerContact = "Phone Number is required";
    } else if (!/^\d{10}$/.test(customerContact)) {
      newErrors.customerContact = "Invalid phone number";
    }
    if (vehicleType === '') {
      newErrors.vehicleType = "Vehicle type is required";
    }
    if (stateCode === '') {
      newErrors.stateCode = "State code is required";
    }
    if (!vehicleRegNo.trim()) {
      newErrors.vehicleRegNo = "Vehicle Registration Number is required";
    }

    if (Object.keys(newErrors).length > 0) {
      setErrors(newErrors);
      return;
    }

    // Clear errors when there are no validation errors
    setErrors({});

    const customerData = {
      customerName: customerName,
      customerContact: customerContact,
      vehicles: [{
        vehicleType: vehicleType,
        vehicleRegNo: stateCode + vehicleRegNo,
      }]
    };



    try {
      const response = await addCustomerAndVehicles(customerData);
      if (!response.success) {
         // Show success message
         setShowSuccessMessage(true);
         // Reset form fields after 3 seconds
         setTimeout(() => {
           setShowSuccessMessage(false);
           setCustomerName('');
           setCustomerContact('');
           setVehicleType('');
           setStateCode('');
           setVehicleRegNo('');
           
         }, 1000);
      } else {
        // Handle error, maybe show an error message
        if (response.errors) {
          // Set errors received from backend
          setErrors(response.errors);
        } else {
          console.error('Failed to add customer');
        }
      }
    } catch (error) {
      console.error('Error adding customer:', error);
    }
  };

  return (
    <>
      <Header />
      <div>
        <section className="rounded-md bg-black/80 p-2">
          <div className="flex items-center justify-center bg-white px-4 py-10 sm:px-6 sm:py-20 lg:px-8">
            <div className="grid grid-cols-2 gap-8 xl:mx-auto xl:w-full xl:max-w-2xl 2xl:max-w-3xl">
              {/* First Form */}
              <div>
                <h2 className="text-2xl font-bold leading-tight text-black">Add Customer and Vehicle Details</h2>
                <form onSubmit={handleCustomerSubmit} className="mt-8">
                  <div className="space-y-5">
                    {/* Customer Details */}
                    <div>
                      <label htmlFor="fullName" className="text-base font-medium text-gray-900">Full Name</label>
                      <div className="mt-2">
                        <input
                          className={`flex h-10 w-full rounded-md border border-gray-300 bg-transparent px-3 py-2 text-sm placeholder:text-gray-400 focus:outline-none focus:ring-1 focus:ring-gray-400 focus:ring-offset-1 ${errors.customerName ? "border-red-500" : ""}`}
                          type="text"
                          value={customerName}
                          onChange={(e) => setCustomerName(e.target.value)}
                          placeholder="Full Name"
                          id="fullName"
                        />
                        {errors.customerName && <p className="text-red-500 text-xs mt-1">{errors.customerName}</p>}
                      </div>
                    </div>
                    <div>
                      <label htmlFor="phoneNumber" className="text-base font-medium text-gray-900">Phone Number</label>
                      <div className="mt-2">
                        <input
                          className={`flex h-10 w-full rounded-md border border-gray-300 bg-transparent px-3 py-2 text-sm placeholder:text-gray-400 focus:outline-none focus:ring-1 focus:ring-gray-400 focus:ring-offset-1 ${errors.customerContact ? "border-red-500" : ""}`}
                          type="tel"
                          value={customerContact}
                          onChange={(e) => setCustomerContact(e.target.value)}
                          placeholder="Phone Number"
                          id="phoneNumber"
                        />
                        {errors.customerContact && <p className="text-red-500 text-xs mt-1">{errors.customerContact}</p>}
                      </div>
                    </div>
                    {/* Vehicle Details */}
                    <div>
                      <label htmlFor="vehicleType" className="text-base font-medium text-gray-900">Vehicle type</label>
                      <div className="mt-2">
                        <select
                          id="vehicleType"
                          className={`flex h-10 w-full rounded-md border border-gray-300 bg-transparent px-3 py-2 text-sm placeholder:text-gray-400 focus:outline-none focus:ring-1 focus:ring-gray-400 focus:ring-offset-1 ${errors.vehicleType ? "border-red-500" : ""}`}
                          value={vehicleType}
                          onChange={(e) => setVehicleType(e.target.value)}
                        >
                          <option value="">Select</option>
                          <option value="two_wheeler">Two Wheeler</option>
                          <option value="four_wheeler">Four Wheeler</option>
                        </select>
                        {errors.vehicleType && <p className="text-red-500 text-xs mt-1">{errors.vehicleType}</p>}
                      </div>
                    </div>
                    <div>
                      <label htmlFor="stateCode" className="text-base font-medium text-gray-900">State Code</label>
                      <div className="mt-2">
                        <select
                          id="stateCode"
                          className={`flex h-10 w-full rounded-md border border-gray-300 bg-transparent px-3 py-2 text-sm placeholder:text-gray-400 focus:outline-none focus:ring-1 focus:ring-gray-400 focus:ring-offset-1 ${errors.stateCode ? "border-red-500" : ""}`}
                          value={stateCode}
                          onChange={(e) => setStateCode(e.target.value)}
                        >
                          <option value="">Select State</option>
                          <option value="BH">BH</option>
                          <option value="RJ">RJ</option>
                          <option value="WB">WB</option>
                        </select>
                        {errors.stateCode && <p className="text-red-500 text-xs mt-1">{errors.stateCode}</p>}
                      </div>
                    </div>
                    <div>
                      <label htmlFor="registrationNumber" className="text-base font-medium text-gray-900">Vehicle Registration Number</label>
                      <div className="mt-2">
                        <input
                          className={`flex h-10 w-full rounded-md border border-gray-300 bg-transparent px-3 py-2 text-sm placeholder:text-gray-400 focus:outline-none focus:ring-1 focus:ring-gray-400 focus:ring-offset-1 ${errors.vehicleRegNo ? "border-red-500" : ""}`}
                          type="text"
                          value={vehicleRegNo}
                          onChange={(e) => setVehicleRegNo(e.target.value)}
                          placeholder="Enter Last 4 Digits of Vehicle Registration Number"
                          id="registrationNumber"
                        />
                        {errors.vehicleRegNo && <p className="text-red-500 text-xs mt-1">{errors.vehicleRegNo}</p>}
                      </div>
                    </div>
                    {/* Save Button */}
                    <div>
                      <button
                        type="submit"
                        className="inline-flex w-full items-center justify-center rounded-md bg-black px-3.5 py-2.5 font-semibold leading-7 text-white hover:bg-black/80"
                      >
                        Save
                      </button>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </section>
      </div>
      {/* Success message */}
      {showSuccessMessage && (
        <div className="fixed inset-0 flex items-end justify-center px-4 py-6 pointer-events-none sm:p-6 sm:items-start sm:justify-end">
          <div className="max-w-sm w-full bg-white shadow-lg rounded-lg pointer-events-auto">
            <div className="rounded-lg shadow-xs overflow-hidden">
              <div className="p-4">
                <div className="flex items-center">
                  <div className="ml-3 w-0 flex-1 pt-0.5">
                    <p className="text-sm font-medium text-gray-900">Details saved!</p>
                  </div>
                  <div className="ml-4 flex-shrink-0 flex">
                    <button
                      onClick={() => setShowSuccessMessage(false)}
                      className="inline-flex text-gray-400 focus:outline-none focus:text-gray-500 transition ease-in-out duration-150"
                    >
                      <svg className="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
                        <path fillRule="evenodd" d="M6.293 6.293a1 1 0 011.414 0L10 8.586l2.293-2.293a1 1 0 111.414 1.414L11.414 10l2.293 2.293a1 1 0 11-1.414 1.414L10 11.414l-2.293 2.293a1 1 0 01-1.414-1.414L8.586 10 6.293 7.707a1 1 0 010-1.414z" clipRule="evenodd" />
                      </svg>
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      )}
    </>
  );
}

export default AddCustomer;
