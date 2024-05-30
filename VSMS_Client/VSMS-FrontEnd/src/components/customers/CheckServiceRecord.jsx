import React, { useState } from "react";
import Header from "../Header";

function CheckServiceRecord() {
  const [phoneNumber, setPhoneNumber] = useState("");
  const [customerDetails, setCustomerDetails] = useState(null);
  const [errorMessage, setErrorMessage] = useState("");

  const fetchServiceRecords = async () => {
    try {
      const response = await fetch(`http://localhost:8080/customers/getByContact/${phoneNumber}`);
      if (!response.ok) {
        throw new Error(`Failed to fetch customer details: ${response.statusText}`);
      }
      const data = await response.json();
      setCustomerDetails(data);
      setErrorMessage("");
    } catch (error) {
      setCustomerDetails(null);
      setErrorMessage(`Error: ${error.message}`);
    }
  };

  return (
    <>
      <Header />
      <div className="flex-grow p-2 min-h-screen flex flex-col items-center">
        <div className="flex w-full items-center justify-center mt-8 md:mt-0 md:w-1/3 space-x-2">
          <input
            className="flex h-10 w-full rounded-md border border-black/30 bg-transparent px-3 py-2 text-sm placeholder:text-gray-600 focus:outline-none focus:ring-1 focus:ring-black/30 focus:ring-offset-1 disabled:cursor-not-allowed disabled:opacity-50"
            type="text"
            placeholder="Phone Number"
            value={phoneNumber}
            onChange={(e) => setPhoneNumber(e.target.value)}
          />
          <button
            type="button"
            className="rounded-md bg-black px-3 py-2 text-sm font-semibold text-white shadow-sm hover:bg-black/80 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-black"
            onClick={fetchServiceRecords}
          >
            Get Details
          </button>
        </div>
        <div className="mt-4">
          {errorMessage && <p>{errorMessage}</p>}
          {customerDetails && (
            <div>
              <h2>Customer Details:</h2>
              <p>Name: {customerDetails.customer.customerName}</p>
              <p>Contact: {customerDetails.customer.customerContact}</p>
              <h3>Service Records:</h3>
              <table className="border-collapse w-full">
                <thead>
                  <tr>
                    <th className="border border-gray-400 px-4 py-2">Date</th>
                    <th className="border border-gray-400 px-4 py-2">Amount</th>
                    <th className="border border-gray-400 px-4 py-2">Oil Change</th>
                    <th className="border border-gray-400 px-4 py-2">Washing</th>
                    <th className="border border-gray-400 px-4 py-2">Other Amenities</th>
                  </tr>
                </thead>
                <tbody>
                  {customerDetails.serviceRecords.map((record, index) => (
                    <tr key={index}>
                      <td className="border border-gray-400 px-4 py-2">{record.date}</td>
                      <td className="border border-gray-400 px-4 py-2">{record.serviceAmount}</td>
                      <td className="border border-gray-400 px-4 py-2">{record.isOilChange ? 'Yes' : 'No'}</td>
                      <td className="border border-gray-400 px-4 py-2">{record.isWashing ? 'Yes' : 'No'}</td>
                      <td className="border border-gray-400 px-4 py-2">{record.otherAmenities}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          )}
        </div>
      </div>
    </>
  );
}

export default CheckServiceRecord;
