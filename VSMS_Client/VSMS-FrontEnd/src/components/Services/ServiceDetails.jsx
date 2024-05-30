import React, { useState, useEffect } from 'react';
import Header from '../Header';
import { serviceDetails } from "../utils/ApiFuntions";

function ServiceDetails() {
  const [serviceDetailsData, setServiceDetailsData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    async function fetchServiceDetails() {
      try {
        const data = await serviceDetails(); // Removed ID parameter
        setServiceDetailsData(data);
        setLoading(false);
      } catch (error) {
        console.error('Error fetching service details:', error);
        setError(error.message);
        setLoading(false);
      }
    }

    fetchServiceDetails();
  }, []);

  return (
    <>
      <Header />
      <div>
        <section className="flex-grow p-2 min-h-screen flex flex-col">
          <div className="flex items-center justify-center bg-white px-4 py-10 sm:px-6 sm:py-20 lg:px-8">
            <div className="container mx-auto">
              <h1 className="text-3xl font-bold mb-4">Service Details</h1>
              {loading ? (
                <p>Loading...</p>
              ) : error ? (
                <p>Error: {error}</p>
              ) : serviceDetailsData.length === 0 ? (
                <p>No service details available.</p>
              ) : (
                <div>
                  {serviceDetailsData.map(vehicle => (
                    <div key={vehicle.vehicle_id} className="mb-4">
                      <h2 className="text-xl font-semibold">{vehicle.vehicleRegNo}</h2>
                      <p>Technician: {vehicle.technician.name}</p>
                      <p>Contact: {vehicle.technician.contact}</p>
                      {/* Add more vehicle details here */}
                    </div>
                  ))}
                </div>
              )}
            </div>
          </div>
        </section>
      </div>
      {/* Assuming Footer component is imported */}
      {/* <Footer /> */}
    </>
  );
}

export default ServiceDetails;
