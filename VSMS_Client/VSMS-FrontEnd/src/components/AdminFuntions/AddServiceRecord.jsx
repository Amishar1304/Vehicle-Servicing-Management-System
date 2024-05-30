import React, { useState, useEffect } from "react";
import Header from "../Header";
import { addServiceRecord, getAllVehicles, getAllTechnicians } from "../utils/ApiFuntions";

function AddServiceRecord() {
  const [vehicleOptions, setVehicleOptions] = useState([]);
  const [technicianOptions, setTechnicianOptions] = useState([]);
  const [customerName, setCustomerName] = useState("");
  const [vehicleId, setVehicleId] = useState("");
  const [technicianName, setTechnicianName] = useState("");
  const [serviceAmount, setServiceAmount] = useState(0);
  const [oilChange, setOilChange] = useState(false);
  const [washing, setWashing] = useState(false);
  const [otherAmenities, setOtherAmenities] = useState([]);
  const [servicePackages, setServicePackages] = useState([]);
  const [errors, setErrors] = useState({});

  useEffect(() => {
    fetchVehicleOptions();
    fetchTechnicianOptions();
  }, []);

  const fetchVehicleOptions = async () => {
    try {
      const vehicles = await getAllVehicles();
      const options = vehicles.map((vehicle) => ({
        value: vehicle.vehicle_id,
        label: vehicle.vehicle_id,
      }));
      setVehicleOptions(options);
    } catch (error) {
      console.error("Error fetching vehicle options:", error);
    }
  };

  const fetchTechnicianOptions = async () => {
    try {
      const technicians = await getAllTechnicians();
      const options = technicians.map((technician) => ({
        value: technician.name,
        label: technician.name,
      }));
      setTechnicianOptions(options);
    } catch (error) {
      console.error("Error fetching technician options:", error);
    }
  };


  const handleCheckboxChange = (event) => {
    const { name, checked, value } = event.target;
    const amenityPrice = parseFloat(value);

    if (name === "oilChange") {
      setOilChange(checked);
      setServiceAmount((prevAmount) =>
        checked ? prevAmount + 200 : prevAmount - 200
      );
    } else if (name === "washing") {
      setWashing(checked);
      setServiceAmount((prevAmount) =>
        checked ? prevAmount + 100 : prevAmount - 100
      );
    } else {
      if (checked) {
        setOtherAmenities((prevAmenities) => [
          ...prevAmenities,
          { name, price: amenityPrice },
        ]);
        setServiceAmount((prevAmount) => prevAmount + amenityPrice);
      } else {
        setOtherAmenities((prevAmenities) =>
          prevAmenities.filter((amenity) => amenity.name !== name)
        );
        setServiceAmount((prevAmount) => prevAmount - amenityPrice);
      }
    }
  };

  const handleServicePackageChange = (event) => {
    const { checked, value } = event.target;
    const packagePrice = parseInt(value);

    if (checked) {
      setServicePackages((prevPackages) => [...prevPackages, packagePrice]);
      setServiceAmount((prevAmount) => prevAmount + packagePrice);
    } else {
      setServicePackages((prevPackages) =>
        prevPackages.filter((price) => price !== packagePrice)
      );
      setServiceAmount((prevAmount) => prevAmount - packagePrice);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const newErrors = {};
      if (!customerName) newErrors.customerName = "Customer name is required";
      if (!vehicleId) newErrors.vehicleId = "Vehicle ID is required";
      if (!technicianName) newErrors.technicianName = "Technician name is required";
      if (!serviceAmount) newErrors.serviceAmount = "Servicing amount is required";

      if (Object.keys(newErrors).length > 0) {
        setErrors(newErrors);
        return;
      }

      setErrors({});

      const serviceRecordData = {
        customerName,
        serviceAmount,
        isOilChange: oilChange ? "True" : "False",
        isWashing: washing ? "True" : "False",
        otherAmenities: otherAmenities.map((amenity) => `${amenity.name} - Rs. ${amenity.price}`).join(", "),
        vehicle: { vehicle_id: vehicleId },
        technicianName,
      };

      const success = await addServiceRecord(serviceRecordData);
      if (success) {
        setCustomerName("");
        setServiceAmount(0);
        setOilChange(false);
        setWashing(false);
        setOtherAmenities([]);
        setVehicleId("");
        setTechnicianName("");
        setServicePackages([]);
      } else {
        console.error("Failed to add service record");
      }
    } catch (error) {
      console.error("Error adding service record:", error);
    }
  };

  return (
    <>
      <Header />
      <div>
        <section className="rounded-md bg-black/80 p-2">
          <div className="flex items-center justify-center bg-white px-4 py-10 sm:px-6 sm:py-20 lg:px-8">
            <div className="grid grid-cols-2 gap-8 xl:mx-auto xl:w-full xl:max-w-2xl 2xl:max-w-3xl">
              <div>
                <h2 className="text-2xl font-bold leading-tight text-black">
                  Add Vehicle Service Details
                </h2>
                <form className="mt-8" onSubmit={handleSubmit}>
                  <div className="space-y-5">
                    <div>
                      <label htmlFor="customerName" className="text-base font-medium text-gray-900">
                        Customer Name
                      </label>
                      <div className="mt-2">
                        <input
                          id="customerName"
                          type="text"
                          className={`flex h-10 w-full rounded-md border border-gray-300 bg-transparent px-3 py-2 text-sm placeholder:text-gray-400 focus:outline-none focus:ring-1 focus:ring-gray-400 focus:ring-offset-1 ${
                            errors.customerName ? "border-red-500" : ""
                          }`}
                          value={customerName}
                         
                          onChange={(e) => setCustomerName(e.target.value)}
                          placeholder="Customer Name"
                        />
                        {errors.customerName && (
                          <p className="text-red-500 text-xs mt-1">{errors.customerName}</p>
                        )}
                      </div>
                    </div>
                    <div>
                      <label htmlFor="vehicleId" className="text-base font-medium text-gray-900">
                        Vehicle ID
                      </label>
                      <div className="mt-2">
                        <select
                          id="vehicleId"
                          className={`flex h-10 w-full rounded-md border border-gray-300 bg-transparent px-3 py-2 text-sm placeholder:text-gray-400 focus:outline-none focus:ring-1 focus:ring-gray-400 focus:ring-offset-1 ${
                            errors.vehicleId ? "border-red-500" : ""
                          }`}
                          value={vehicleId}
                          onChange={(e) => setVehicleId(e.target.value)}
                        >
                          <option value="">Select</option>
                          {vehicleOptions.map((option) => (
                            <option key={option.value} value={option.value}>
                              {option.label}
                            </option>
                          ))}
                        </select>
                        {errors.vehicleId && (
                          <p className="text-red-500 text-xs mt-1">{errors.vehicleId}</p>
                        )}
                      </div>
                    </div>
                    <div>
                      <label className="text-base font-medium text-gray-900">
                        Oil Change (Rs. 200)
                      </label>
                      <div className="mt-2">
                        <input
                          type="checkbox"
                          id="oilChange"
                          name="oilChange"
                          value="200"
                          onChange={handleCheckboxChange}
                          checked={oilChange}
                        />
                        <label htmlFor="oilChange">Changed</label>
                      </div>
                    </div>
                    <div>
                      <label className="text-base font-medium text-gray-900">
                        Washing (Rs. 100)
                      </label>
                      <div className="mt-2">
                        <input
                          type="checkbox"
                          id="washing"
                          name="washing"
                          value="100"
                          onChange={handleCheckboxChange}
                          checked={washing}
                        />
                        <label htmlFor="washing">Done</label>
                      </div>
                    </div>
                    <div>
                      <label className="text-base font-medium text-gray-900">
                        Service Package
                      </label>
                      <div className="mt-2 space-y-2">
                        <div>
                          <input
                            type="checkbox"
                            id="basicMaintenance"
                            name="Basic Maintenance: Oil change, fluid top-ups, visual inspections"
                            value="5000"
                            onChange={handleServicePackageChange}
                            checked={servicePackages.includes(5000)}
                          />
                          <label htmlFor="basicMaintenance">
                            1. Basic Maintenance: Oil change, fluid top-ups, visual inspections (Rs. 5000)
                          </label>
                        </div>
                        <div>
                          <input
                            type="checkbox"
                            id="intermediateService"
                            name="Intermediate Service: Adds filter replacements, tire rotation, brake inspection"
                            value="10000"
                            onChange={handleServicePackageChange}
                            checked={servicePackages.includes(10000)}
                          />
                          <label htmlFor="intermediateService">
                            2. Intermediate Service: Adds filter replacements, tire rotation, brake inspection (Rs. 10000)
                          </label>
                        </div>
                        <div>
                          <input
                            type="checkbox"
                            id="advancedService"
                            name="Advanced Service: Includes major component checks, diagnostic scans, alignment"
                            value="15000"
                            onChange={handleServicePackageChange}
                            checked={servicePackages.includes(15000)}
                          />
                          <label htmlFor="advancedService">
                            3. Advanced Service: Includes major component checks, diagnostic scans, alignment (Rs. 15000)
                          </label>
                        </div>
                      </div>
                    </div>
                    <div>
                      <label className="text-base font-medium text-gray-900">
                        Other Amenities
                      </label>
                      <div className="mt-2 space-y-2">
                        <div>
                          <input
                            type="checkbox"
                            id="wheelAlignment"
                            name="Wheel Alignment"
                            value="500"
                            onChange={handleCheckboxChange}
                            checked={otherAmenities.some((amenity) => amenity.name === "Wheel Alignment")}
                          />
                          <label htmlFor="wheelAlignment">Wheel Alignment (Rs. 500)</label>
                        </div>
                        <div>
                          <input
                            type="checkbox"
                            id="engineDiagnostics"
                            name="Engine Diagnostics"
                            value="1000"
                            onChange={handleCheckboxChange}
                            checked={otherAmenities.some((amenity) => amenity.name === "Engine Diagnostics")}
                          />
                          <label htmlFor="engineDiagnostics">Engine Diagnostics (Rs. 1000)</label>
                        </div>
                      </div>
                    </div>
                    <div>
                      <label htmlFor="technicianName" className="text-base font-medium text-gray-900">
                        Technician Name
                      </label>
                      <div className="mt-2">
                        <select
                          id="technicianName"
                          className={`flex h-10 w-full rounded-md border border-gray-300 bg-transparent px-3 py-2 text-sm placeholder:text-gray-400 focus:outline-none focus:ring-1 focus:ring-gray-400 focus:ring-offset-1 ${
                            errors.technicianName ? "border-red-500" : ""
                          }`}
                          value={technicianName}
                          onChange={(e) => setTechnicianName(e.target.value)}
                        >
                          <option value="">Select</option>
                          {technicianOptions.map((option) => (
                            <option key={option.value} value={option.value}>
                              {option.label}
                            </option>
                          ))}
                        </select>
                        {errors.technicianName && (
                          <p className="text-red-500 text-xs mt-1">{errors.technicianName}</p>
                        )}
                      </div>
                    </div>
                    <div>
                      <label htmlFor="serviceAmount" className="text-base font-medium text-gray-900">
                        Servicing Amount
                      </label>
                      <div className="mt-2">
                        <input
                          id="serviceAmount"
                          type="number"
                          className={`flex h-10 w-full rounded-md border border-gray-300 bg-transparent px-3 py-2 text-sm placeholder:text-gray-400 focus:outline-none focus:ring-1 focus:ring-gray-400 focus:ring-offset-1 ${
                            errors.serviceAmount ? "border-red-500" : ""
                          }`}
                          value={serviceAmount}
                          onChange={(e) => setServiceAmount(parseFloat(e.target.value))}
                          placeholder="Servicing Amount"
                          readOnly
                        />
                        {errors.serviceAmount && (
                          <p className="text-red-500 text-xs mt-1">{errors.serviceAmount}</p>
                        )}
                      </div>
                    </div>
                    <div>
                      <button
                        type="submit"
                        className="inline-flex w-full items-center justify-center rounded-md bg-black px-3.5 py-2.5 font-semibold text-white"
                      >
                        Add Service Record
                      </button>
                    </div>
                  </div>
                </form>
              </div>
              <div className="h-full w-full">
                <img
                  className="mx-auto w-full rounded-lg object-cover"
                  src="https://images.unsplash.com/photo-1605902711622-cfb43c4437c7?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=774&q=80"
                  alt=""
                />
              </div>
            </div>
          </div>
        </section>
      </div>
    </>
  );
}

export default AddServiceRecord;
